// GameLogic.js - Real-time competitive coding duel


class GameLogic {
    constructor() {
        this.stompClient = null;
        this.matchId = null;
        this.playerId = null;
        this.playerUsername = null;
        this.opponentId = null;
        this.opponentUsername = null;
        this.currentChallenge = null;
        this.playerHealth = 3;
        this.opponentHealth = 3;
        this.isGameActive = false;
    }

    // Initialize game
    init(user) {
        this.getMatchInfo(user);
        this.setupWebSocket();
        this.setupUIEvents();
        this.updateUI();
    }

    // Get match info from URL and storage
    getMatchInfo(user) {
        const urlParams = new URLSearchParams(window.location.search);
        this.matchId = urlParams.get('matchId');
        this.playerId = user.userID;
        this.playerUsername = user.username;
        console.log("Player : " + user)
        if (!this.matchId || !this.playerId) {
            window.location.href = '/Login.html';
        }
    }
    

    // Setup WebSocket connection
    setupWebSocket() {
        const socket = new SockJS('/ws');
        this.stompClient = Stomp.over(socket);
        
        this.stompClient.connect({}, (frame) => {
            console.log('Connected to WebSocket');
            this.subscribeToChannels();
            this.sendReadyNotification();
        }, (error) => {
            console.error('WebSocket error:', error);
            this.showError('Connection lost. Reconnecting...');
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
            this.showError(error);
        });
    }

    // Notify server we're ready
    sendReadyNotification() {
        this.stompClient.send(`/app/match/${this.matchId}/ready`);
    }

    // Handle initial game state
    handleGameStart(status) {
        this.currentChallenge = status.currentChallenge;
        this.isGameActive = true;
        console.log(status)
        // Identify opponent
        status.userPlayMatch1.userID === this.playerId ?
            (this.opponentId = status.userPlayMatch2.userID,
             this.opponentUsername = status.userPlayMatch2.username,
             this.playerHealth = status.userPlayMatch1.userScore,
             this.opponentHealth = status.userPlayMatch2.userScore) :
            (this.opponentId = status.userPlayMatch1.userID,
             this.opponentUsername = status.userPlayMatch1.username,
             this.playerHealth = status.userPlayMatch2.userScore,
             this.opponentHealth = status.userPlayMatch1.userScore);
        
        this.updateUI();
        this.updateChallengeDisplay();
    }

    // Handle code submission
    handleSubmit() {
        
        if (!this.isGameActive) return;
        
        const code = document.getElementById('codeEditor').value.trim();
        if (!code) {
            this.showError("Please write some code first!");
            return;
        }

        this.stompClient.send(`/app/match/${this.matchId}/submit`, {}, JSON.stringify({
            challengeId: this.currentChallenge.id,
            code: code
        }));
        
        // Clear editor but keep it enabled for next attempt
        document.getElementById('codeEditor').value = '';
    }

    // Process hit event
    handleHitEvent(hit) {
        console.log(hit)
        const isAttacker = hit.hittingPlayerId === this.playerId;
        
        // Update health values
        this.playerHealth = hit.player1Health;
        this.opponentHealth = hit.player2Health;
        
        // Play animations
        if (isAttacker) {
            this.playAttackAnimation();
            this.showMessage("You hit your opponent!");
        } else {
            this.playHitAnimation();
            this.showMessage(`${this.opponentUsername} hit you!`);
        }
        
        this.updateUI();
        
        // Check for game over
        if (this.playerHealth <= 0 || this.opponentHealth <= 0) {
            this.isGameActive = false;
        }
    }

    // Handle submission response
    handleSubmissionResponse(response) {
        if (response.accepted) {
            this.showMessage("Correct solution!");
        } else {
            this.showError(response.message);
        }
    }

    // Handle match end
    handleMatchEnd(result) {
        const message = result.winnerId === this.playerId ?
            "You won the match!" : 
            `${result.winnerName} won the match!`;
        
        this.showMessage(message);
        this.isGameActive = false;
        
        setTimeout(() => {
            window.location.href = '/MatchMakingMenu.html';
        }, 3000);
    }

    // UI Updates
    updateUI() {
        let healthLevels = ['🖤🖤🖤', '❤️🖤🖤', '❤️❤️🖤', '❤️❤️❤️'];
        document.getElementById('p1UsernameDiv').textContent = this.playerUsername;
        document.getElementById('p2UsernameDiv').textContent = this.opponentUsername || "Opponent";
        document.getElementById('p1HealthDiv').textContent = healthLevels[this.playerHealth];
        document.getElementById('p2HealthDiv').textContent =healthLevels[this.opponentHealth];
    }

    updateChallengeDisplay() {
        if (!this.currentChallenge) return;
        
        const challengeDiv = document.getElementById('challengeDiv');
        challengeDiv.innerHTML = `
            <h3>${this.currentChallenge.title}</h3>
            <p>${this.currentChallenge.description}</p>
            <div class="sample">${this.currentChallenge.sample}</div>
        `;
    }

    // Animation Methods
    playAttackAnimation() {
        // Implement your attack animation
        const attacker = this.playerId === this.playerId ? 'P1' : 'P2';
        console.log(`${attacker} attack animation`);
    }

    playHitAnimation() {
        // Implement your hit animation
        console.log("Hit animation");
    }

    // Notification Methods
    showMessage(message) {
        alert(message)
    }

    showError(message) {
        alert(message)
    }

    // Event Listeners
    setupUIEvents() {
        document.getElementById('submitBtn').addEventListener('click', () => this.handleSubmit());
        
        // Allow Ctrl+Enter to submit
        document.getElementById('codeEditor').addEventListener('keydown', (e) => {
            if (e.ctrlKey && e.key === 'Enter') {
                this.handleSubmit();
            }
        });
    }
}

// Initialize game when page loads
window.onload = async () => {
    const user = await fetchCurrentUser()
    const game = new GameLogic();
    game.init(user);
    
};

async function fetchCurrentUser() {
    console.log("fetching user")
    try {
      const response = await fetch('/api/auth/me', {
        credentials: 'include' // Important for sending cookies
      });
      
      if (!response.ok) {
        if (response.status === 401) {
          window.location.href = '/login'; // Redirect if unauthorized
          return;
        }
        throw new Error('Failed to fetch user data');
      }
      const user = await response.json();
      console.log("from fucnction : " + user);
      return user;
    } catch (error) {
      console.error('Error fetching user:', error);
      return null;
    }
}