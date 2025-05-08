package com.code.duel.code.duel.Mappers.ResponseMapper;

public class MatchResult {
    private Long winnerId;
    private String winnerName;

    public MatchResult(Long winnerId, String winnerName) {
        this.winnerId = winnerId;
        this.winnerName = winnerName;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
}
