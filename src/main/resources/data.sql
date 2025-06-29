INSERT INTO "user" (userID, Username, Email, Password, Role, Score)
VALUES
(1, 'Alice', 'alice@example.com', '{noop}123456', 'PLAYER', 0),
(2, 'Bob', 'bob@example.com', '{noop}654321', 'PLAYER', 0),
(3, 'SalemX', 'salem@example.com', '{noop}123456', 'PLAYER', 0),
(4, 'MohX', 'moh@example.com', '{noop}123456', 'PLAYER', 0);

INSERT INTO Challenge (ChallengeID, Title, Description, Difficulty, Sample)
VALUES
(1, 'Sum of Two Numbers', 'Read two integers and print their sum.', 'Easy', 'Input: 3 5 Output: 8'),
(2, 'Even or Odd', 'Reada number then print if an integer is even or odd.', 'Easy', 'Input: 4 Output: Even'),
(3, 'Maximum of Two Numbers', 'Read two numbers then Print the larger of two numbers.', 'Easy', 'Input: 7 9 Output: 9'),
(4, 'Area of a Rectangle', 'Read the length and width of a rectangle and calculate its area.', 'Easy', 'Input: 5 3 Output: 15'),
(5, 'Convert Minutes to Seconds', 'Convert a number of minutes to seconds.', 'Easy', 'Input: 5 Output: 300'),
(6, 'Fibonacci Sequence', 'Generate the nth Fibonacci number.', 'Normal', 'Input: 5 Output: 5'),
(7, 'Reverse a String', 'Read a string and print its reverse.', 'Normal', 'Input: hello Output: olleh'),
(8, 'Count Vowels in a Word', 'Count how many vowels are in a given lowercase word.', 'Normal', 'Input: banana Output: 3'),
(9, 'Square a Number', 'Read a number and output its square.', 'Normal', 'Input: 4 Output: 16'),
(10, 'Find Smallest of Three Numbers', 'Given three integers, print the smallest.', 'Normal', 'Input: 3 1 2 Output: 1'),
(11, 'Sum of Digits', 'Read a number and print the sum of its digits.', 'Hard', 'Input: 1234 Output: 10'),
(12, 'Check for Palindrome Number', 'Check if a number reads the same backward.', 'Hard', 'Input: 121 Output: Yes'),
(13, 'Print Multiples of 3 up to N', 'Print all numbers divisible by 3 up to a number n.', 'Hard', 'Input: 10 Output: 3 6 9'),
(14, 'Count Letters in a String', 'Count the number of letters in a string (excluding spaces).', 'Hard', 'Input: hello world Output: 10'),
(15, 'Capitalize First Letter', 'Convert the first character of a word to uppercase.', 'Hard', 'Input: hello Output: Hello');






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



