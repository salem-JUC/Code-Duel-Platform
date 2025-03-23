package com.code.duel.code.duel.Model;

public class TestCase {
    private Long testCaseID;
    private Long challengeID;
    private String input;
    private String expectedOutput;

    // Constructors
    public TestCase() {}

    public TestCase(Long testCaseID, Long challengeID, String input, String expectedOutput) {
        this.testCaseID = testCaseID;
        this.challengeID = challengeID;
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    // Getters and Setters
    public Long getTestCaseID() {
        return testCaseID;
    }

    public void setTestCaseID(Long testCaseID) {
        this.testCaseID = testCaseID;
    }

    public Long getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(Long challengeID) {
        this.challengeID = challengeID;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}
