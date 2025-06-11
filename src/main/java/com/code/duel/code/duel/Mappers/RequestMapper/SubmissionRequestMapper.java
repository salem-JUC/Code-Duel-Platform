package com.code.duel.code.duel.Mappers.RequestMapper;

public class SubmissionRequestMapper {



    Long challengeId;
    String code;

    public SubmissionRequestMapper(Long challengeId, String code) {
        this.challengeId = challengeId;
        this.code = code;
    }



    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
