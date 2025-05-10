package com.code.duel.code.duel.Model;

public class User {
    private Long userID;
    private String username;
    private String email;
    private Long password;
    private String role;

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", role='" + role + '\'' +
                ", score=" + score +
                '}';
    }

    private Integer score;

    // Constructors
    public User() {}

    public User(Long userID, String username, String email, Long password, String role, Integer score) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.score = score;
    }

    // Getters and Setters
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
