package com.code.duel.code.duel.Mappers.ResponseMapper;

import com.code.duel.code.duel.Model.Challenge;
import com.code.duel.code.duel.Model.Submission;

public class SubmissionWithChallengeResponse {

    Submission submission;
    Challenge challenge;

    public SubmissionWithChallengeResponse(Submission submission, Challenge challenge) {
        this.submission = submission;
        this.challenge = challenge;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
