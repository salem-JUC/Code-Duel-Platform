CREATE TABLE "user" (
    userID BIGINT PRIMARY KEY,
    Username VARCHAR(255),
    Email VARCHAR(255),
    Password VARCHAR(255),
    Role ENUM('E') NOT NULL,
    Score INT
);

-- Create Match table
CREATE TABLE "match" (
    matchID BIGINT PRIMARY KEY,
    status ENUM('E') NOT NULL
);

-- Create Challenge table
CREATE TABLE Challenge (
    ChallengeID BIGINT PRIMARY KEY,
    Title VARCHAR(255),
    Description VARCHAR(255),
    Difficulty ENUM('E') NOT NULL,
    Sample VARCHAR(255)
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
    userScore INT,
    result ENUM('E') NOT NULL,
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
    ProgrammingLanguage ENUM('E'),
    FOREIGN KEY (ChallengeID) REFERENCES Challenge(ChallengeID),
    FOREIGN KEY (submitterID) REFERENCES "user"(userID)
);
