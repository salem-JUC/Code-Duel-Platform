let player = {
    id: 101,
    username: "KhalidX7x",
    health: 3
};

let opponentPlayer = {
    id: 202,
    username: "M7",
    health: 3
};

const challenges = [
    {
        id: 1,
        description: 'Write a function that adds two numbers',
        sampleInput: '5,7',
        expectedOutput: '12'
    },
    {
        id: 2,
        description: 'Check if a string is palindrome',
        sampleInput: '"racecar"',
        expectedOutput: 'true'
    }
];

const challengeDiv = document.getElementById('challengeDiv');
const codeEditor = document.getElementById('codeEditor');

let currentChallenge = null;

function updateGame() {
    document.getElementById('player1Div').textContent = player.username;
    document.getElementById('player2Div').textContent = opponentPlayer.username;
    document.getElementById('p1HealthDiv').textContent = '❤️'.repeat(player.health);
    document.getElementById('p2HealthDiv').textContent = '❤️'.repeat(opponentPlayer.health);
}

function loadNewChallenge() {
    currentChallenge = challenges[Math.floor(Math.random() * challenges.length)];
    challengeDiv.innerHTML = `
        <h3>${currentChallenge.description}</h3>
        <p>Sample Input: ${currentChallenge.sampleInput}</p>
        <p>Expected Output: ${currentChallenge.expectedOutput}</p>
    `;
}

function validateSolution(code, challenge) {
    try {
        const func = new Function(`return ${code}`)();
        const args = challenge.sampleInput.includes(',') 
            ? challenge.sampleInput.split(',').map(x => JSON.parse(x.trim()))
            : [JSON.parse(challenge.sampleInput)];
        const testResult = func(...args);
        return testResult.toString() === challenge.expectedOutput;
    } catch (e) {
        console.error(e);
        return false;
    }
}

function duel() {
   document.querySelector('.submit').disabled = true; /*Block the Button */
    const isCorrect = validateSolution(codeEditor.value, currentChallenge);
    
    if (isCorrect) {
        opponentPlayer.health--;
        if (opponentPlayer.health <= 0) {
            endGame(true);
            return;
        }
    } else {
        player.health--;
        if (player.health <= 0) {
            endGame(false);
            return;
        }
    }
    
    updateGame();
    codeEditor.value = ''; // Clear editor
    loadNewChallenge(); // load a new challenge after duel
    document.querySelector('.submit').disabled = false;/* unlock button*/
}

function endGame(isWinner) {
    const gameOverModal = document.getElementById('gameOverModal');
    const resultMessage = document.getElementById('resultMessage');
    
    // Show game over modal
    gameOverModal.style.display = 'flex';
    
    // Set result message
    resultMessage.textContent = isWinner ? 
        `${player.username} Wins! ` : 
        `${opponentPlayer.username} Wins! `;
    
    // Disable game elements
    document.getElementById('codeEditor').disabled = true;
    document.querySelector('.submit').disabled = true;
}

// Initialize Game
updateGame();
loadNewChallenge();
