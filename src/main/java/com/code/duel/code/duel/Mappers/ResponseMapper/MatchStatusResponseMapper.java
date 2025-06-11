package com.code.duel.code.duel.Mappers.ResponseMapper;

import com.code.duel.code.duel.Model.Challenge;
import com.code.duel.code.duel.Model.Match;
import com.code.duel.code.duel.Model.UserPlayMatch;

public class MatchStatusResponseMapper {
    // this class is data structe for the match status , should contain userplayematch
    // for each user in match and the match status

    private Match match;
    private Challenge currentChallenge;
    private UserPlayMatch userPlayMatch1;
    private UserPlayMatch userPlayMatch2;

    public MatchStatusResponseMapper(Match match, Challenge currentChallenge, UserPlayMatch userPlayMatch1, UserPlayMatch userPlayMatch2) {
        this.match = match;
        this.currentChallenge = currentChallenge;
        this.userPlayMatch1 = userPlayMatch1;
        this.userPlayMatch2 = userPlayMatch2;
    }

    public MatchStatusResponseMapper() {
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Challenge getCurrentChallenge() {
        return currentChallenge;
    }

    public void setCurrentChallenge(Challenge currentChallenge) {
        this.currentChallenge = currentChallenge;
    }

    public UserPlayMatch getUserPlayMatch1() {
        return userPlayMatch1;
    }

    public void setUserPlayMatch1(UserPlayMatch userPlayMatch1) {
        this.userPlayMatch1 = userPlayMatch1;
    }

    public UserPlayMatch getUserPlayMatch2() {
        return userPlayMatch2;
    }

    public void setUserPlayMatch2(UserPlayMatch userPlayMatch2) {
        this.userPlayMatch2 = userPlayMatch2;
    }

    @Override
    public String toString() {
        return "MatchStatusResponseMapper{" +
                "match=" + match.toString() +
                ", currentChallenge=" + currentChallenge.toString() +
                ", userPlayMatch1=" + userPlayMatch1.toString() +
                ", userPlayMatch2=" + userPlayMatch2.toString() +
                '}';
    }
}
