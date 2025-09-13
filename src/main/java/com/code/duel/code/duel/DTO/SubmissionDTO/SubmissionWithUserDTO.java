package com.code.duel.code.duel.DTO.SubmissionDTO;

public class SubmissionWithUserDTO {


    private String username;
    private Long submissionId;
    private String challengeTitle;
    private String difficulty;
    private String programmingLanguage;
    private String result;

    public SubmissionWithUserDTO(String username, Long submissionId, String challengeTitle, String difficulty, String programmingLanguage, String result) {
        this.username = username;
        this.submissionId = submissionId;
        this.challengeTitle = challengeTitle;
        this.difficulty = difficulty;
        this.programmingLanguage = programmingLanguage;
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public String getChallengeTitle() {
        return challengeTitle;
    }

    public void setChallengeTitle(String challengeTitle) {
        this.challengeTitle = challengeTitle;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SubmissionWithUserDTO{" +
                "username='" + username + '\'' +
                ", submissionId=" + submissionId +
                ", challengeTitle='" + challengeTitle + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

