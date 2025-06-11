CREATE TABLE "user" (
    userID BIGINT PRIMARY KEY,
    Username VARCHAR(255) UNIQUE,
    Email VARCHAR(255) UNIQUE,
    Password VARCHAR(255),
    Role ENUM('PLAYER' , 'ADMIN') NOT NULL,
    Score INT
);
CREATE TABLE Challenge (
    ChallengeID BIGINT PRIMARY KEY,
    Title VARCHAR(255),
    Description VARCHAR(255),
    Difficulty ENUM('Easy' , 'Normal' , 'Hard') NOT NULL,
    Sample VARCHAR(255)
);

-- Create Match table
CREATE TABLE "match" (
    matchID BIGINT PRIMARY KEY,
    current_challenge_id BIGINT,
    difficulty VARCHAR(255),
    programmingLanguage VARCHAR(255),
    status ENUM('PENDING' , 'RUNNING' , 'FINISHED') NOT NULL,
    winnerId BIGINT,
    FOREIGN KEY (current_challenge_id) REFERENCES Challenge(ChallengeID)
);

-- Create TestCase table
CREATE TABLE TestCase (
    testCaseID BIGINT PRIMARY KEY,
    ChallengeID BIGINT,
    "input" VARCHAR(255),
    ExpectedOutput VARCHAR(255),
    FOREIGN KEY (ChallengeID) REFERENCES Challenge(ChallengeID)
);

CREATE TABLE user_play_match (
    userID BIGINT,
    matchID BIGINT,
    username VARCHAR(255),
    userScore INT,
    PRIMARY KEY (userID, matchID),
    FOREIGN KEY (userID) REFERENCES "user"(userID),
    FOREIGN KEY (matchID) REFERENCES "match"(matchID)
);

-- Create Submission table
CREATE TABLE Submission (
    submissionID BIGINT PRIMARY KEY,
    ChallengeID BIGINT,
    submitterID BIGINT,
    Result VARCHAR(255),
    Code TEXT,
    ProgrammingLanguage VARCHAR(255),
    FOREIGN KEY (ChallengeID) REFERENCES Challenge(ChallengeID),
    FOREIGN KEY (submitterID) REFERENCES "user"(userID)
);
