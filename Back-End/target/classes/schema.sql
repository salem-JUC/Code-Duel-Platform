CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique identifier for the user
    username VARCHAR(50) NOT NULL UNIQUE,  -- Username, must be unique
    email VARCHAR(100) NOT NULL UNIQUE,  -- Email address, must be unique
    password VARCHAR(255) NOT NULL -- Password (hashed)
);