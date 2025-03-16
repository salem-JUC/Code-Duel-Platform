-- Create users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    rating INT DEFAULT 1200,  -- ELO rating system for ranking
    total_matches INT DEFAULT 0,
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create challenges table
CREATE TABLE challenges (
    challenge_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    difficulty VARCHAR(50) NOT NULL,  -- Using VARCHAR instead of ENUM
    sample_input TEXT,
    sample_output TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create matches table
CREATE TABLE matches (
    match_id INT AUTO_INCREMENT PRIMARY KEY,
    player1_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    player2_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    challenge_id INT REFERENCES challenges(challenge_id) ON DELETE CASCADE,
    winner_id INT REFERENCES users(user_id),
    status VARCHAR(50) DEFAULT 'Pending',  -- Using VARCHAR instead of ENUM
    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ended_at TIMESTAMP
);

-- Create match_submissions table
CREATE TABLE match_submissions (
    submission_id INT AUTO_INCREMENT PRIMARY KEY,
    match_id INT REFERENCES matches(match_id) ON DELETE CASCADE,
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    code TEXT NOT NULL,
    "language" VARCHAR(50) NOT NULL,  -- Python, Java, C++, etc.
    status VARCHAR(50) DEFAULT 'Pending',  -- Using VARCHAR instead of ENUM
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create test_cases table
CREATE TABLE test_cases (
    test_case_id INT AUTO_INCREMENT PRIMARY KEY,
    challenge_id INT REFERENCES challenges(challenge_id) ON DELETE CASCADE,
    "input" TEXT NOT NULL,
    expected_output TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);