package com.code.duel.code.duel.Model;

public class Match {
    private Long matchID;

    private Long currentChallengeId;
    private String difficulty;
    private String programmingLanguage;



    private String status;



    // Constructors
    public Match() {}

    public Match(Long matchID,  Long currentChallengeId, String difficulty ,String programmingLanguage,String status ) {
        this.matchID = matchID;
        this.currentChallengeId = currentChallengeId;
        this.difficulty = difficulty;
        this.programmingLanguage = programmingLanguage;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchID=" + matchID +
                ", currentChallengeId=" + currentChallengeId +
                ", difficulty='" + difficulty + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
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
    public Long getCurrentChallengeId() {
        return currentChallengeId;
    }

    public void setCurrentChallengeId(Long currentChallengeId) {
        this.currentChallengeId = currentChallengeId;
    }
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}
