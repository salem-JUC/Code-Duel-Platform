package com.code.duel.code.duel;

import com.code.duel.code.duel.Exception.MatchNotFoundException;
import com.code.duel.code.duel.Model.Difficulty;
import com.code.duel.code.duel.Model.Match;
import com.code.duel.code.duel.Repository.MatchRepo;
import com.code.duel.code.duel.Repository.UserPlayMatchRepo;
import com.code.duel.code.duel.Repository.UserRepo;
import com.code.duel.code.duel.Service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class runner implements CommandLineRunner {

    @Autowired
    MatchService matchService;

    public void run(String... args) throws Exception {


        Match match = matchService.createMatch(2L , "Med", "Java");
        System.out.println(match.getCurrentChallengeId() + " - CurrentChallengeId of created challenge");
        Match joinedMatch;
        joinedMatch = matchService.joinMatch(match.getMatchID() , 3L);

        matchService.handleCorrectSubmmission(joinedMatch.getMatchID(), 3L, 3L);
        matchService.handleCorrectSubmmission(joinedMatch.getMatchID(), 3L, 3L);
        matchService.handleCorrectSubmmission(joinedMatch.getMatchID(), 3L, 3L);

        matchService.joinMatch(match.getMatchID() , 1L);
    }
}
