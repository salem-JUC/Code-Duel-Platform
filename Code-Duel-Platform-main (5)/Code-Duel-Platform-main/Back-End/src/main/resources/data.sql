INSERT INTO "user" (userID, Username, Email, Password, Role, Score)
VALUES
(1, 'Alice', 'alice@example.com', 123456, 'E', 100),
(2, 'Bob', 'bob@example.com', 654321, 'E', 85),
(3, 'Charlie', 'charlie@example.com', 987654, 'E', 120);

INSERT INTO Challenge (ChallengeID, Title, Description, Difficulty, Sample)
VALUES
(1, 'Two Sum', 'Find two numbers that add up to a target.', 'Easy', 'Sample Input: [2, 7, 11, 15], 9'),
(2, 'Palindrome Check', 'Check if a string is a palindrome.', 'Hard', 'Sample Input: "racecar"'),
(3, 'Fibonacci Sequence', 'Generate the nth Fibonacci number.', 'Med', 'Sample Input: 5'),
(4, 'Two Minus', 'Find two numbers that mins up to a target.', 'Easy', 'Sample Input: [2, 7, 11, 15], 9');

INSERT INTO "match" (matchID,current_challenge_id, difficulty , programmingLanguage, status)
VALUES
(1,1, 'Easy' , 'Java' ,'PENDING'),
(2,1 , 'Easy' , 'Java','PENDING'),
(3,1 , 'Easy' , 'Java' ,'PENDING'),
(4,1 , 'Easy' , 'Java' ,'RUNNING');



INSERT INTO TestCase (testCaseID, ChallengeID, "input", ExpectedOutput)
VALUES
(1, 1, '[2, 7, 11, 15], 9', '[0, 1]'),
(2, 1, '[3, 2, 4], 6', '[1, 2]'),
(3, 2, '"racecar"', 'true'),
(4, 2, '"hello"', 'false'),
(5, 3, '5', '5'),
(6, 3, '7', '13');


INSERT INTO Submission (submissionID, ChallengeID, submitterID, Result, Code, ProgrammingLanguage)
VALUES
(1, 1, 1, 'Success', 'public class Solution {...}', 'E'),
(2, 1, 2, 'Failure', 'public class Solution {...}', 'E'),
(3, 2, 3, 'Success', 'public class Solution {...}', 'E'),
(4, 3, 1, 'Success', 'public class Solution {...}', 'E');