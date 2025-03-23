package com.code.duel.code.duel.Model;

public class Submission {
    private Long submissionID;
    private Long challengeID;
    private Long submitterID;
    private String result;
    private String code;
    private String programmingLanguage;

    // Constructors
    public Submission() {}

    public Submission(Long submissionID, Long challengeID, Long submitterID, String result, String code, String programmingLanguage) {
        this.submissionID = submissionID;
        this.challengeID = challengeID;
        this.submitterID = submitterID;
        this.result = result;
        this.code = code;
        this.programmingLanguage = programmingLanguage;
    }

    // Getters and Setters
    public Long getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(Long submissionID) {
        this.submissionID = submissionID;
    }

    public Long getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(Long challengeID) {
        this.challengeID = challengeID;
    }

    public Long getSubmitterID() {
        return submitterID;
    }

    public void setSubmitterID(Long submitterID) {
        this.submitterID = submitterID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}
