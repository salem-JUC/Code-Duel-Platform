package com.code.duel.code.duel.Exception;

public class MatchNotFoundException extends RuntimeException{
    public MatchNotFoundException(Long matchId) {
        super("Match not found with id: " + matchId);
    }
}
