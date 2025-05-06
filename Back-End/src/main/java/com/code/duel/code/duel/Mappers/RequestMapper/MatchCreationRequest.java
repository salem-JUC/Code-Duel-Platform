package com.code.duel.code.duel.Mappers.RequestMapper;

public class MatchCreationRequest {

    Long playerId;
    String diffculty;
    String language;

    public MatchCreationRequest(Long playerId, String diffculty, String language) {
        this.playerId = playerId;
        this.diffculty = diffculty;
        this.language = language;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getDiffculty() {
        return diffculty;
    }

    public void setDiffculty(String diffculty) {
        this.diffculty = diffculty;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
