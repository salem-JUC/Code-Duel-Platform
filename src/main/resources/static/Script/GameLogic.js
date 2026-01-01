// GameLogic.js - Real-time competitive coding duel


class GameLogic {
    constructor() {
        this.stompClient = null;
        this.matchId = null;
        this.playerId = null;
        this.playerUsername = null;
        this.opponentId = null;
        this.opponentUsername = null;
        this.currentChallenge = {
            title : null,
            description : null,
            sample : null
        };
        this.playerHealth = 3;
        this.opponentHealth = 3;
        this.isGameActive = false;
        this.diffculty = null;
        this.language = null;
    }

    // Initialize game
    init(user) {
        this.getMatchInfo(user)
        this.setupWebSocket();
        // let status = {
        //     title : "Even or Odd",
        //     description : "Write a function that adds two numbers",
        //     sample : "5,7",
        //     expectedOutput : "12",
        //     difficulty : "Easy",
        //     programmingLanguage : "java",
        //     playerName : "BOB",
        //     secondName : "M7",
        //     playerScore : 3,
        //     secondScore : 3
        // }

        this.setupUIEvents();
        this.updateUI();
    }

    // Get match info from URL and storage
    getMatchInfo(user) {
        const urlParams = new URLSearchParams(window.location.search);
        this.matchId = urlParams.get('matchId');
        console.log("Match Id is : ", this.matchId)
        this.playerId = user.userID;
        this.playerUsername = user.username;
        console.log("Player : " , user)
        if (this.matchId == null || this.playerId == null) {
            window.location.href = '/Login.html';
        }
    }
    

    // Setup WebSocket connection
    setupWebSocket() {
        const socket = new SockJS('/ws');
        this.stompClient = Stomp.over(socket);
        
        this.stompClient.connect({
        type : "MATCH"
        ,userId:this.playerId
        ,matchId : this.matchId}, (frame) => {
            console.log('Connected to WebSocket');
            this.subscribeToChannels();
            this.sendReadyNotification();
        }, (error) => {
            console.error('WebSocket error:', error);
            this.showGameError('Connection lost. Reconnecting...');
            setTimeout(() => this.setupWebSocket(), 500000);
        });
    }

    // Subscribe to all WebSocket channels
    subscribeToChannels() {
        // Initial game state
        this.stompClient.subscribe(`/user/queue/match/start`, (message) => {
            const status = JSON.parse(message.body);
            this.handleGameStart(status);
        });

        // Hit notifications
        this.stompClient.subscribe(`/topic/match/${this.matchId}/hit`, (message) => {
            const hit = JSON.parse(message.body);
            this.handleHitEvent(hit);
        });

        // New challenge notifications
        this.stompClient.subscribe(`/topic/match/${this.matchId}/challenge`, (message) => {
            this.currentChallenge = JSON.parse(message.body);
            this.updateChallengeDisplay();
        });

        // Submission responses
        this.stompClient.subscribe(`/user/queue/submission`, (message) => {
            const response = JSON.parse(message.body);
            this.handleSubmissionResponse(response);
        });

        // Match ended
        this.stompClient.subscribe(`/topic/match/${this.matchId}/ended`, (message) => {
            const result = JSON.parse(message.body);
            this.handleMatchEnd(result);
        });

        // Error messages
        this.stompClient.subscribe(`/user/queue/errors`, (message) => {
            const error = JSON.parse(message.body);
            this.showGameError(error.message || error);
        });
    }

    // Notify server we're ready
    sendReadyNotification() {
        this.stompClient.send(`/app/match/${this.matchId}/ready`);
    }

    // Handle initial game state
    handleGameStart(status) {
        // "difficulty": "Easy",
        // "programmingLanguage": "Java",
        // "title": "Even or Odd",
        // "description": "Reada number then print if an integer is even or odd.",
        // "sample": "Input: 4 Output: Even",
        // "playerName": "SalemX",
        // "playerScore": 3,
        // "secondName": "MohX",
        // "secondScore": 3
        console.log("Starting Status" , status)
        this.currentChallenge.title = status.title;
        this.currentChallenge.description = status.description;
        this.currentChallenge.sample = status.sample;
        this.diffculty = status.difficulty;
        this.language = status.programmingLanguage;
        this.playerUsername = status.playerName;
        this.opponentId = status.secondId;
        this.opponentUsername = status.secondName;
        this.playerHealth = status.playerScore;
        this.opponentHealth = status.secondScore;
        this.isGameActive = true;
        this.updateUI();
        this.updateChallengeDisplay();
    }

    // Handle code submission
    handleSubmit() {
        
        if (!this.isGameActive) return;
        this.showGameSuccess("⌛ Your submission is being evaluated");
        const code = document.getElementById('codeEditor').value.trim();
        if (!code) {
            this.showGameError("Please write some code first!");
            document.getElementById("submitBtn").disabled = false;
            return;
        }

        this.stompClient.send(`/app/match/${this.matchId}/submit`, {}, JSON.stringify({
            challengeId: this.currentChallenge.id,
            code: code
        }));
        
        // Clear editor but keep it enabled for next attempt
        document.getElementById('codeEditor').value = ""

    }

    // Process hit event
    handleHitEvent(hit) {
        console.log(hit)
        const isAttacker = hit.hittingPlayerId === this.playerId;
        
        // Play animations
        if (isAttacker) {
            this.showGameSuccess("👊 You attacked " + this.opponentUsername)
            overworld.Player.performAttackP(overworld.Oponent);
            gameSounds.playSFX('hit');
            this.playerHealth = hit.player1Health;
            this.opponentHealth = hit.player2Health;
        } else {
            this.showGameError("⚠️ "+this.opponentUsername + " attacked you")
            overworld.Player.performAttackO(overworld.Oponent);
            gameSounds.playSFX('hit');
            this.playerHealth = hit.player2Health;
            this.opponentHealth = hit.player1Health;
        }
        document.getElementById('codeEditor').value = ""
        
        this.updateUI();
        
        // Check for game over
        if (this.playerHealth <= 0 || this.opponentHealth <= 0) {
            this.isGameActive = false;
        }
    }

    // Handle submission response
    handleSubmissionResponse(response) {
        if (response.accepted) {
            console.log("submission correct")
            this.showGameSuccess("✅️ Correct solution");
        } else {
            this.showGameError("❌ Wrong solution" + (response.message ? ": " + response.message : ""));
        }
    }

    attack(){
        console.log("attack animation function")
        
    }


    // Handle match end
    handleMatchEnd(result) {
        if (result.winnerId === this.playerId) {
            overworld.Player.WinnerP(overworld.Oponent);
            this.showGameSuccess("🎉 You won the match!", 6000);
            gameSounds.playSFX('win');
        } else if (result.winnerName) {
            overworld.Player.opponentWin(overworld.Oponent);
            this.showGameError("You have Lost !!", 6000);
        }
        
        this.isGameActive = false;
        
        setTimeout(() => {
            window.location.href = '/MatchMakingMenu.html';
        }, 6000);
    }

    // UI Updates
    updateUI() {
        let healthLevels = ['🖤🖤🖤', '❤️🖤🖤', '❤️❤️🖤', '❤️❤️❤️'];
        document.getElementById('p1UsernameDiv').textContent = this.playerUsername;
        document.getElementById('p2UsernameDiv').textContent = this.opponentUsername || "Opponent";
        document.getElementById('p1HealthDiv').textContent = healthLevels[this.playerHealth];
        document.getElementById('p2HealthDiv').textContent =healthLevels[this.opponentHealth];
        document.getElementById("language").textContent = this.language;
        document.getElementById("difficulty").textContent = this.diffculty;
    }

    updateChallengeDisplay() {
        if (!this.currentChallenge) return;
        this.currentChallenge.sample = this.currentChallenge.sample.replace(/Input : /g, 'input : <br><br>').replace(/Output : /g, 'output : ');
        const challengeDiv = document.getElementById('challengeDiv');
        challengeDiv.innerHTML = `
            <h3>${this.currentChallenge.title}</h3>
            <br>
            <p>${this.currentChallenge.description}</p>
        `;
        document.getElementById('sampleContent').innerHTML = `
            ${this.currentChallenge.sample}
        `;
    }
    quit(){
        this.stompClient.send(`/app/match/${this.matchId}/quit`);
    }

    // Animation Methods
  

    // Notification Methods
    showGameSuccess(message, duration = 4000) {
        // Remove existing success message if any
        const existingSuccess = document.querySelector('.game-message-success');
        if (existingSuccess) {
            existingSuccess.remove();
        }

        // Create success message element
        const successDiv = document.createElement('div');
        successDiv.className = 'game-message-success';
        successDiv.innerHTML = `
            <div class="game-message-icon">✓</div>
            <div class="game-message-text">${message}</div>
            <button class="game-message-close" onclick="this.parentElement.remove()">×</button>
        `;

        document.body.appendChild(successDiv);

        // Animate in
        setTimeout(() => {
            successDiv.classList.add('show');
        }, 10);

        // Auto remove after duration
        setTimeout(() => {
            successDiv.classList.remove('show');
            setTimeout(() => {
                if (successDiv.parentElement) {
                    successDiv.remove();
                }
            }, 300);
        }, duration);
    }

    showGameError(message, duration = 5000) {
        // Remove existing error message if any
        const existingError = document.querySelector('.game-message-error');
        if (existingError) {
            existingError.remove();
        }

        // Create error message element
        const errorDiv = document.createElement('div');
        errorDiv.className = 'game-message-error';
        errorDiv.innerHTML = `
            <div class="game-message-icon">⚠</div>
            <div class="game-message-text">${message}</div>
            <button class="game-message-close" onclick="this.parentElement.remove()">×</button>
        `;

        document.body.appendChild(errorDiv);

        // Animate in
        setTimeout(() => {
            errorDiv.classList.add('show');
        }, 10);

        // Auto remove after duration
        setTimeout(() => {
            errorDiv.classList.remove('show');
            setTimeout(() => {
                if (errorDiv.parentElement) {
                    errorDiv.remove();
                }
            }, 300);
        }, duration);
    }

    // Event Listeners
    setupUIEvents() {
        document.getElementById('submitBtn').addEventListener('click', () => this.handleSubmit());
        document.getElementById('quitBtn').addEventListener('click', () => this.quit());
        // Allow Ctrl+Enter to submit
        document.getElementById('codeEditor').addEventListener('keydown', (e) => {
            if (e.ctrlKey && e.key === 'Enter') {
                this.handleSubmit();
            }
        });
    }
}




let game = null;
//Initialize game when page loads
window.onload = async () => {
    game = new GameLogic();
    const user = await fetchCurrentUser()
    game.init(user);
    if (!user) {
        console.warn("No user found , Redirecting to login page ...")
        window.location.href = '/login';
        return;
    }
    
};




async function fetchCurrentUser() {
    console.log("fetching user")
    try {
      const response = await fetch('/api/auth/me', {
        credentials: 'include' 
      });
      
      if (!response.ok) {

        window.location.href = '/login'; // Redirect if unauthorized
        return;
      }
      const user = await response.json();
      console.log("from fucnction : " , user);
      return user;
    } catch (error) {
      console.error('Error fetching user:', error);
      return null;
    }
}

