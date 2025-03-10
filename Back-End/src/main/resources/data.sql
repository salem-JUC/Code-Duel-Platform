INSERT INTO "User" (userID, Username, Email, Password, Role, Score, matchesPlayed) VALUES
(1, 'Alice', 'alice@example.com', 123456, 'PLAYER', 100, 5),
(2, 'Bob', 'bob@example.com', 654321, 'PLAYER', 150, 7),
(3, 'Charlie', 'charlie@example.com', 987654, 'ADMIN', 200, 10);

-- Insert Challenges
INSERT INTO Challenge (ChallengeID, Title, Description, Difficulty) VALUES
(1, 'Challenge 1', 'add this number and return ', 'EASY'),
(2, 'Challenge 2', 'Another problem to solve', 'MEDIUM'),
(3, 'Challenge 3', 'Difficult problem', 'HARD');

-- Insert TestCases
INSERT INTO TestCase (testCaseID, ChallengeID, Input, ExpectedOutput) VALUES
(1, 1, '5 5', '10'),
(2, 1, '9 4', '13'),
(3 , 1,'3 7', '10');

-- Insert Matches
INSERT INTO "Match" (matchID, Player1ID, Player2ID, status, winner, Player1Score, Player2Score) VALUES
(1, 1, 2, 'COMPLETED', 1, 10, 8);

-- Insert Submissions
INSERT INTO Submission (submissionID, ChallengeID, submitterID, Result) VALUES
(1, 1, 1, 'SUCCESS'),
(2, 2, 2, 'FAILURE'),
(3, 3, 3, 'SUCCESS');