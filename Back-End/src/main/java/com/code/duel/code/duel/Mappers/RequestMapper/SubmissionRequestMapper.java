package com.code.duel.code.duel.Mappers.RequestMapper;

public class SubmissionRequestMapper {


    Long matchId;
    Long challengeId;
    String code;

    public SubmissionRequestMapper(Long matchId, Long challengeId, String code) {
        this.matchId = matchId;
        this.challengeId = challengeId;
        this.code = code;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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
