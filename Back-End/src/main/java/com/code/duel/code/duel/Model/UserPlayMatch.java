package com.code.duel.code.duel.Model;

public class UserPlayMatch {
    private Long userID;
    private Long matchID;
    private Integer userScore;
    private String result;

    // Constructors
    public UserPlayMatch() {}

    public UserPlayMatch(Long userID, Long matchID, Integer userScore, String result) {
        this.userID = userID;
        this.matchID = matchID;
        this.userScore = userScore;
        this.result = result;
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
                ", userScore=" + userScore +
                ", result='" + result + '\'' +
                '}';
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}