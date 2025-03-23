package com.code.duel.code.duel.Model;

public class Match {
    private Long matchID;
    private String status;

    // Constructors
    public Match() {}

    public Match(Long matchID, String status) {
        this.matchID = matchID;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchID=" + matchID +
                ", status='" + status + '\'' +
                '}';
    }

    // Getters and Setters
    public Long getMatchID() {
        return matchID;
    }

    public void setMatchID(Long matchID) {
        this.matchID = matchID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
