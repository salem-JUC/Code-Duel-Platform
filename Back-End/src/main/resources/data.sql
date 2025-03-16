INSERT INTO users (username, email, password_hash, rating, total_matches, wins, losses)
VALUES
    ('alice', 'alice@example.com', 'hashed_password_1', 1200, 5, 3, 2),
    ('bob', 'bob@example.com', 'hashed_password_2', 1300, 10, 7, 3),
    ('charlie', 'charlie@example.com', 'hashed_password_3', 1100, 8, 4, 4);

INSERT INTO challenges (title, description, difficulty, sample_input, sample_output)
VALUES
    ('Two Sum', 'Find two numbers that add up to a target.', 'Easy', '[2, 7, 11, 15]\n9', '[0, 1]'),
    ('Palindrome Check', 'Check if a string is a palindrome.', 'Medium', '"racecar"', 'true'),
    ('Binary Search', 'Implement binary search on a sorted array.', 'Hard', '[1, 2, 3, 4, 5]\n3', '2');

INSERT INTO matches (player1_id, player2_id, challenge_id, winner_id, status, started_at, ended_at)
VALUES
    (1, 2, 1, 1, 'Completed', '2023-10-01 10:00:00', '2023-10-01 10:30:00'),
    (2, 3, 2, NULL, 'Pending', '2023-10-02 11:00:00', NULL),
    (1, 3, 3, 3, 'Completed', '2023-10-03 12:00:00', '2023-10-03 12:45:00');

INSERT INTO match_submissions (match_id, user_id, code, "language", status, submitted_at)
VALUES
    (1, 1, 'def two_sum(nums, target): ...', 'Python', 'Accepted', '2023-10-01 10:15:00'),
    (1, 2, 'public class Solution { ... }', 'Java', 'Wrong Answer', '2023-10-01 10:20:00'),
    (3, 1, 'function binarySearch(arr, target) { ... }', 'JavaScript', 'Runtime Error', '2023-10-03 12:10:00'),
    (3, 3, 'int binarySearch(int arr[], int target) { ... }', 'C++', 'Accepted', '2023-10-03 12:30:00');

INSERT INTO test_cases (challenge_id, "input", expected_output)
VALUES
    (1, '[2, 7, 11, 15]\n9', '[0, 1]'),
    (1, '[3, 2, 4]\n6', '[1, 2]'),
    (2, '"racecar"', 'true'),
    (2, '"hello"', 'false'),
    (3, '[1, 2, 3, 4, 5]\n3', '2'),
    (3, '[1, 2, 3, 4, 5]\n6', '-1');
