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
        this.diffculty = null;
        this.language = null;
    }

    // Initialize game
    init(user) {
        document.getElementById('codeEditor').value = `
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Start Coding
    }
}
`;
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
        this.language = status.match.programmingLanguage;
        this.diffculty = status.match.difficulty;
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
        this.showMessage("⌛ Your submission is being evaluated");
        const code = document.getElementById('codeEditor').value.trim();
        if (!code) {
            this.showError("Please write some code first!");
            document.getElementById("submitBtn").disabled = false;
            return;
        }

        this.stompClient.send(`/app/match/${this.matchId}/submit`, {}, JSON.stringify({
            challengeId: this.currentChallenge.id,
            code: code
        }));
        
        // Clear editor but keep it enabled for next attempt
        document.getElementById('codeEditor').value = `
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Start Coding
    }
}
`;

    }

    // Process hit event
    handleHitEvent(hit) {
        console.log(hit)
        const isAttacker = hit.hittingPlayerId === this.playerId;
        
        // Play animations
        if (isAttacker) {
            this.showMessage("👊 You attacked " + this.opponentUsername)
            overworld.Player.performAttackP(overworld.Oponent);
            gameSounds.playSFX('hit');
            this.playerHealth = hit.player1Health;
            this.opponentHealth = hit.player2Health;
        } else {
            this.showMessage("⚠️ "+this.opponentUsername + " attacked you")
            overworld.Player.performAttackO(overworld.Oponent);
            gameSounds.playSFX('hit');
            this.playerHealth = hit.player2Health;
            this.opponentHealth = hit.player1Health;
        }
        document.getElementById('codeEditor').value = `
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Start Coding
    }
}
`;
        
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
            this.showMessage("✅️ Correct solution");
        } else {
            this.showMessage( "❌ Wrong solution" + response.message);
        }
    }

    attack(){
        console.log("attack animation function")
        
    }


    // Handle match end
    handleMatchEnd(result) {
        let message;

        if (result.winnerId === this.playerId) {
            overworld.Player.WinnerP(overworld.Oponent);
            message = "You won the match!";
            gameSounds.playSFX('win');

        } else if (result.winnerName) {
            overworld.Player.opponentWin(overworld.Oponent);
            message = `${result.winnerName} won the match!`;
        }
        
        this.showMessage(message);
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
        
        const challengeDiv = document.getElementById('challengeDiv');
        challengeDiv.innerHTML = `
            <h3>${this.currentChallenge.title}</h3>
            <br>
            <p>${this.currentChallenge.description}</p>
            <br>
            <div class="sample">${this.currentChallenge.sample}</div>
        `;
    }
    quit(){
        this.stompClient.send(`/app/match/${this.matchId}/quit`);
    }

    // Animation Methods
  

    // Notification Methods
    showMessage(message) {
        document.getElementById("message").textContent = message
    }

    showError(message) {
        alert(message)
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

const game = new GameLogic();
// Initialize game when page loads
window.onload = async () => {
    const user = await fetchCurrentUser()
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
      console.log("from fucnction : " , user);
      return user;
    } catch (error) {
      console.error('Error fetching user:', error);
      return null;
    }
}

