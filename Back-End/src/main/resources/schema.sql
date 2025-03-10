-- Create Tables

CREATE TABLE "User" (
    userID BIGINT PRIMARY KEY,
    Username VARCHAR(255),
    Email VARCHAR(255),
    Password BIGINT,
    Role VARCHAR(50),
    Score INT,
    matchesPlayed BIGINT
);

CREATE TABLE Challenge (
    ChallengeID BIGINT PRIMARY KEY,
    Title VARCHAR(255),
    Description VARCHAR(255),
    Difficulty VARCHAR(50)
);

CREATE TABLE TestCase (
    testCaseID BIGINT PRIMARY KEY,
    ChallengeID BIGINT,
    Input VARCHAR(255),
    ExpectedOutput VARCHAR(255),
    FOREIGN KEY (ChallengeID) REFERENCES Challenge(ChallengeID)
);

CREATE TABLE "Match" (
    matchID BIGINT PRIMARY KEY,
    Player1ID BIGINT,
    Player2ID BIGINT,
    status VARCHAR(50),
    winner BIGINT,
    Player1Score INT,
    Player2Score INT,
    FOREIGN KEY (Player1ID) REFERENCES "User"(userID),
    FOREIGN KEY (Player2ID) REFERENCES "User"(userID),
    FOREIGN KEY (winner) REFERENCES "User"(userID)
);

CREATE TABLE Submission (
    submissionID BIGINT PRIMARY KEY,
    ChallengeID BIGINT,
    submitterID BIGINT,
    Result VARCHAR(255),
    FOREIGN KEY (ChallengeID) REFERENCES Challenge(ChallengeID),
    FOREIGN KEY (submitterID) REFERENCES "User"(userID)
);
