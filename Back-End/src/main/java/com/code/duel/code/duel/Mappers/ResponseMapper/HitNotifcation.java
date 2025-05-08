package com.code.duel.code.duel.Mappers.ResponseMapper;

public class HitNotifcation {

    private Long hittingPlayerId;
    private Integer player1Health;
    private Integer player2Health;

    public HitNotifcation(Long hittingPlayerId, Integer player1Health, Integer player2Health) {
        this.hittingPlayerId = hittingPlayerId;
        this.player1Health = player1Health;
        this.player2Health = player2Health;
    }

    public Long getHittingPlayerId() {
        return hittingPlayerId;
    }

    public void setHittingPlayerId(Long hittingPlayerId) {
        this.hittingPlayerId = hittingPlayerId;
    }

    public Integer getPlayer1Health() {
        return player1Health;
    }

    public void setPlayer1Health(Integer player1Health) {
        this.player1Health = player1Health;
    }

    public Integer getPlayer2Health() {
        return player2Health;
    }

    public void setPlayer2Health(Integer player2Health) {
        this.player2Health = player2Health;
    }
}
