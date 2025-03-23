INSERT INTO "user" (userID, Username, Email, Password, Role, Score)
VALUES
(1, 'Alice', 'alice@example.com', 123456, 'E', 100),
(2, 'Bob', 'bob@example.com', 654321, 'E', 85),
(3, 'Charlie', 'charlie@example.com', 987654, 'E', 120);

INSERT INTO "match" (matchID, status)
VALUES
(1, 'E'),
(2, 'E'),
(3, 'E');

INSERT INTO Challenge (ChallengeID, Title, Description, Difficulty, Sample)
VALUES
(1, 'Two Sum', 'Find two numbers that add up to a target.', 'E', 'Sample Input: [2, 7, 11, 15], 9'),
(2, 'Palindrome Check', 'Check if a string is a palindrome.', 'E', 'Sample Input: "racecar"'),
(3, 'Fibonacci Sequence', 'Generate the nth Fibonacci number.', 'E', 'Sample Input: 5');

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