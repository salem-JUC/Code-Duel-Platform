INSERT INTO "user" (userID, Username, Email, Password, Role, Score)
VALUES
(1, 'Alice', 'alice@example.com', '{noop}123456', 'E', 100),
(2, 'Bob', 'bob@example.com', '{noop}654321', 'E', 85),
(3, 'Charlie', 'charlie@example.com', '{noop}987654', 'E', 120);

INSERT INTO Challenge (ChallengeID, Title, Description, Difficulty, Sample)
VALUES
(1, 'Two Sum', 'Find two numbers that add up to a target.', 'Easy', 'Input : 4 4 Output : 8'),
(2, 'Palindrome Check', 'Check if a string is a palindrome.', 'Hard', 'Sample Input: "racecar"'),
(3, 'Fibonacci Sequence', 'Generate the nth Fibonacci number.', 'Normal', 'Sample Input: 5'),
(4, 'Two Minus', 'Find two numbers that mins up to a target.', 'Easy', 'Input : 5 3 Output : 2');

INSERT INTO "match" (matchID,current_challenge_id, difficulty , programmingLanguage, status, winnerId)
VALUES
(1,4, 'Easy' , 'Java' ,'FINISHED' , 1),
(2,1 , 'Easy' , 'Java','FINISHED' , 1),
(3,1 , 'Easy' , 'Java' ,'FINISHED' , NULL),
(4,1 , 'Easy' , 'Java' ,'FINISHED' , NULL);



INSERT INTO TestCase (testCaseID, ChallengeID, "input", ExpectedOutput)
VALUES
(1, 1, '5 5', '10'),
(2, 1, '2 2', '4'),
(3, 2, '"racecar"', 'true'),
(4, 2, '"hello"', 'false'),
(9, 4, '10 5' , '5'),
(10, 4, '3 2' , '1'),
(11, 4, '9 4' , '5');


INSERT INTO Submission (submissionID, ChallengeID, submitterID, Result, Code, ProgrammingLanguage)
VALUES
(1, 1, 1, 'Success', 'public class Solution {...}', 'E'),
(2, 1, 2, 'Failure', 'public class Solution {...}', 'E'),
(3, 2, 3, 'Success', 'public class Solution {...}', 'E'),
(4, 3, 1, 'Success', 'public class Solution {...}', 'E');

INSERT INTO user_play_match (userID, matchID, username, userScore) VALUES
-- John's matches
(1, 1, 'Alice', 95),  -- John won match 101
(1, 2, 'Alice', 80),   -- John lost to Jane in match 102
(1, 3, 'Alice', NULL), -- Ongoing match

-- Jane's matches
(2, 2, 'Bob', 90), -- Jane won match 102
(2, 4, 'Bob', NULL);