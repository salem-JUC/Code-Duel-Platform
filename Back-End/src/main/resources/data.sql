INSERT INTO "user" (userID, Username, Email, Password, Role, Score)
VALUES
(1, 'Alice', 'alice@example.com', '{noop}123456', 'E', 100),
(2, 'Bob', 'bob@example.com', '{noop}654321', 'E', 85),
(3, 'Charlie', 'charlie@example.com', '{noop}987654', 'E', 120);

INSERT INTO Challenge (ChallengeID, Title, Description, Difficulty, Sample)
VALUES
<<<<<<< HEAD
(1, 'Two Sum', 'Find two numbers that add up to a target.', 'Easy', 'Input : 4 4 Output : 8'),
(2, 'Palindrome Check', 'Check if a string is a palindrome.', 'Hard', 'Sample Input: "racecar"'),
(3, 'Fibonacci Sequence', 'Generate the nth Fibonacci number.', 'Normal', 'Sample Input: 5'),
(4, 'Two Minus', 'Find two numbers that mins up to a target.', 'Easy', 'Input : 5 3 Output : 2');
=======
(1, 'Sum of Two Numbers', 'Read two integers and print their sum.', 'Easy', 'Sample Input: 3 5'),
(2, 'Even or Odd', 'Determine if an integer is even or odd.', 'Easy', 'Sample Input: 4'),
(3, 'Maximum of Two Numbers', 'Print the larger of two numbers.', 'Easy', 'Sample Input: 7 9'),
(4, 'Area of a Rectangle', 'Read the length and width of a rectangle and calculate its area.', 'Easy', 'Sample Input: 5 3')
(5, 'Convert Minutes to Seconds', 'Convert a number of minutes to seconds.', 'Easy', 'Sample Input: 5'),
(6, 'Fibonacci Sequence', 'Generate the nth Fibonacci number.', 'Normal', 'Sample Input: 5'),
(7, 'Reverse a String', 'Read a string and print its reverse.', 'Normal', 'Sample Input: hello'),
(8, 'Count Vowels in a Word', 'Count how many vowels are in a given lowercase word.', 'Normal', 'Sample Input: banana'),
(9, 'Square a Number', 'Read a number and output its square.', 'Normal', 'Sample Input: 4'),
(10, 'Find Smallest of Three Numbers', 'Given three integers, print the smallest.', 'Normal', 'Sample Input: 3 1 2'),
(11, 'Sum of Digits', 'Read a number and print the sum of its digits.', 'Hard', 'Sample Input: 1234'),
(12, 'Check for Palindrome Number', 'Check if a number reads the same backward.', 'Hard', 'Sample Input: 121'),
(13, 'Print Multiples of 3 up to N', 'Print all numbers divisible by 3 up to a number n.', 'Hard', 'Sample Input: 10'),
(14, 'Count Letters in a String', 'Count the number of letters in a string (excluding spaces).', 'Hard', 'Sample Input: hello world'),
(15, 'Capitalize First Letter', 'Convert the first character of a word to uppercase.', 'Hard', 'Sample Input: hello');
>>>>>>> 2706f69629f309496d19809a3b7b0aa5e8a457c1

INSERT INTO "match" (matchID,current_challenge_id, difficulty , programmingLanguage, status, winnerId)
VALUES
<<<<<<< HEAD
(1,4, 'Easy' , 'Java' ,'FINISHED' , 1),
(2,1 , 'Easy' , 'Java','FINISHED' , 1),
(3,1 , 'Easy' , 'Java' ,'FINISHED' , NULL),
(4,1 , 'Easy' , 'Java' ,'FINISHED' , NULL);
=======
(1,1, 'Easy' , 'Java' ,'PENDING'),
(2,1 , 'Easy' , 'Java','PENDING'),
(3,1 , 'Easy' , 'Java' ,'PENDING'),
(4,1 , 'Easy' , 'Java' ,'RUNNING');
>>>>>>> 2706f69629f309496d19809a3b7b0aa5e8a457c1


INSERT INTO TestCase (testCaseID, ChallengeID, "input", ExpectedOutput)
VALUES
<<<<<<< HEAD
(1, 1, '5 5', '10'),
(2, 1, '2 2', '4'),
(3, 2, '"racecar"', 'true'),
(4, 2, '"hello"', 'false'),
(9, 4, '10 5' , '5'),
(10, 4, '3 2' , '1'),
(11, 4, '9 4' , '5');

=======
(1, 1, '3 5', '8'),
(2, 1, '3 5', '7'),
(3, 2, '4', 'Even'),
(4, 2, '4', 'Odd'),
(5, 3, '7 9', '9'),
(6, 3, '7 9', '7'),
(7, 4, '5 3', '15'),
(8, 4, '5 3', '8'),
(9, 5, '5', '300'),
(10, 5, '5', '60'),
(11, 6, '5', '5'),
(12, 6, '5', '8'),
(13, 7, 'hello', 'olleh'),
(14, 7, 'hello', 'hello'),
(15, 8, 'banana', '3'),
(16, 8, 'banana', '2'),
(17, 9, '4', '16'),
(18, 9, '4', '8'),
(19, 10, '3 1 2', '1'),
(20, 10, '3 1 2', '3'),
(21, 11, '1234', '10'),
(22, 11, '1234', '6'),
(23, 12, '121', 'true'),
(24, 12, '121', 'false'),
(25, 13, '10', '3 6 9'),
(26, 13, '10', '2 4 8'),
(27, 14, 'hello world', '10'),
(28, 14, 'hello world', '11'),
(29, 15, 'hello', 'Hello'),
(30, 15, 'hello', 'hello');
>>>>>>> 2706f69629f309496d19809a3b7b0aa5e8a457c1

INSERT INTO Submission (submissionID, ChallengeID, submitterID, Result, Code, ProgrammingLanguage)
VALUES
(1, 1, 1, 'Success', 'public class Solution {...}', 'E'),
(2, 1, 2, 'Failure', 'public class Solution {...}', 'E'),
(3, 2, 3, 'Success', 'public class Solution {...}', 'E'),
(4, 3, 1, 'Success', 'public class Solution {...}', 'E');
<<<<<<< HEAD

INSERT INTO user_play_match (userID, matchID, username, userScore) VALUES
-- John's matches
(1, 1, 'Alice', 95),  -- John won match 101
(1, 2, 'Alice', 80),   -- John lost to Jane in match 102
(1, 3, 'Alice', NULL), -- Ongoing match

-- Jane's matches
(2, 2, 'Bob', 90), -- Jane won match 102
(2, 4, 'Bob', NULL);
=======
>>>>>>> 2706f69629f309496d19809a3b7b0aa5e8a457c1
