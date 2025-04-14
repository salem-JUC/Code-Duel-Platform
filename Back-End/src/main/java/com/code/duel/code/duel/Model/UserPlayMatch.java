package com.code.duel.code.duel.Model;

public class UserPlayMatch {
    private Long userID;
    private Long matchID;
    private String username;
    private Integer userScore;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Constructors
    public UserPlayMatch() {}

    public UserPlayMatch(Long userID, Long matchID,String username, Integer userScore) {
        this.userID = userID;
        this.matchID = matchID;
        this.username = username;
        this.userScore = userScore;
    }

    // Getters and Setters
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getMatchID() {
        return matchID;
    }

    public void setMatchID(Long matchID) {
        this.matchID = matchID;
    }

    @Override
    public String toString() {
        return "UserPlayMatch{" +
                "userID=" + userID +
                ", matchID=" + matchID +
                ", username=" + username +
                ", userScore=" + userScore + '\'' +
                '}';
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }


}