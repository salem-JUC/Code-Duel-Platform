package com.code.duel.code.duel.Model;

public class Challenge {
    private Long challengeID;
    private String title;
    private String description;
    private String difficulty;
    private String sample;

    // Constructors
    public Challenge() {}

    public Challenge(Long challengeID, String title, String description, String difficulty, String sample) {
        this.challengeID = challengeID;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.sample = sample;
    }

    // Getters and Setters
    public Long getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(Long challengeID) {
        this.challengeID = challengeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }
}
