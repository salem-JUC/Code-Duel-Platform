INSERT INTO "user" (userID, Username, Email, Password, Role, Score)
VALUES
(1, 'Alice', 'alice@example.com', '{noop}123456', 'E', 100),
(2, 'Bob', 'bob@example.com', '{noop}654321', 'E', 85),
(3, 'Charlie', 'charlie@example.com', '{noop}987654', 'E', 120);

INSERT INTO Challenge (ChallengeID, Title, Description, Difficulty, Sample)
VALUES
(1, 'Sum of Two Numbers', 'Read two integers and print their sum.', 'Easy', 'Input: 3 5\nOutput: 8'),
(2, 'Even or Odd', 'Determine if an integer is even or odd.', 'Easy', 'Input: 4\nOutput: Even'),
(3, 'Maximum of Two Numbers', 'Print the larger of two numbers.', 'Easy', 'Input: 7 9\nOutput: 9'),
(4, 'Area of a Rectangle', 'Read the length and width of a rectangle and calculate its area.', 'Easy', 'Input: 5 3\nOutput: 15'),
(5, 'Convert Minutes to Seconds', 'Convert a number of minutes to seconds.', 'Easy', 'Input: 5\nOutput: 300'),
(6, 'Fibonacci Sequence', 'Generate the nth Fibonacci number.', 'Normal', 'Input: 5\nOutput: 5'),
(7, 'Reverse a String', 'Read a string and print its reverse.', 'Normal', 'Input: hello\nOutput: olleh'),
(8, 'Count Vowels in a Word', 'Count how many vowels are in a given lowercase word.', 'Normal', 'Input: banana\nOutput: 3'),
(9, 'Square a Number', 'Read a number and output its square.', 'Normal', 'Input: 4\nOutput: 16'),
(10, 'Find Smallest of Three Numbers', 'Given three integers, print the smallest.', 'Normal', 'Input: 3 1 2\nOutput: 1'),
(11, 'Sum of Digits', 'Read a number and print the sum of its digits.', 'Hard', 'Input: 1234\nOutput: 10'),
(12, 'Check for Palindrome Number', 'Check if a number reads the same backward.', 'Hard', 'Input: 121\nOutput: Yes'),
(13, 'Print Multiples of 3 up to N', 'Print all numbers divisible by 3 up to a number n.', 'Hard', 'Input: 10\nOutput: 3 6 9'),
(14, 'Count Letters in a String', 'Count the number of letters in a string (excluding spaces).', 'Hard', 'Input: hello world\nOutput: 10'),
(15, 'Capitalize First Letter', 'Convert the first character of a word to uppercase.', 'Hard', 'Input: hello\nOutput: Hello');


INSERT INTO "match" (matchID,current_challenge_id, difficulty , programmingLanguage, status, winnerId)
VALUES
(1,4, 'Easy' , 'Java' ,'FINISHED' , 1),
(2,1 , 'Easy' , 'Java','FINISHED' , 1),
(3,1 , 'Easy' , 'Java' ,'FINISHED' , NULL),
(4,1 , 'Easy' , 'Java' ,'FINISHED' , NULL);



INSERT INTO TestCase (testCaseID, ChallengeID, "input", ExpectedOutput)
VALUES
(1, 1, '3 5', '8'),
(2, 1, '5 5', '10'),
(3, 2, '4', 'Even'),
(4, 2, '5', 'Odd'),
(5, 3, '7 9', '9'),
(6, 3, '10 9', '10'),
(7, 4, '5 3', '15'),
(8, 4, '2 3', '6'),
(9, 5, '5', '300'),
(10, 5, '10', '600'),
(11, 6, '5', '5'),
(12, 6, '8', '21'),
(13, 7, 'hello', 'olleh'),
(14, 7, 'saleh', 'helas'),
(15, 8, 'banana', '3'),
(16, 8, 'hello', '2'),
(17, 9, '4', '16'),
(18, 9, '5', '25'),
(19, 10, '3 1 2', '1'),
(20, 10, '5 9 4', '4'),
(21, 11, '1234', '10'),
(22, 11, '405', '9'),
(23, 12, '121', 'true'),
(24, 12, '5665', 'true'),
(25, 13, '10', '3 6 9'),
(26, 13, '15', '3 6 9 12 15'),
(27, 14, 'hello world', '10'),
(28, 14, 'code duel platform', '16'),
(29, 15, 'hello', 'Hello'),
(30, 15, 'code', 'Code');

INSERT INTO Submission (submissionID, ChallengeID, submitterID, Result, Code, ProgrammingLanguage)
VALUES
(1, 1, 1, 'Success', 'public class Solution {...}', 'E'),
(2, 1, 2, 'Failure', 'public class Solution {...}', 'E'),
(3, 2, 3, 'Success', 'public class Solution {...}', 'E'),
(4, 3, 1, 'Success', 'public class Solution {...}', 'E');

