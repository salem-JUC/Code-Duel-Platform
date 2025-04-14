package com.code.duel.code.duel.Service;

import com.code.duel.code.duel.Exception.MatchNotFoundException;
import com.code.duel.code.duel.Model.Difficulty;
import com.code.duel.code.duel.Model.Match;
import com.code.duel.code.duel.Model.UserPlayMatch;
import com.code.duel.code.duel.Repository.MatchRepo;
import com.code.duel.code.duel.Repository.UserPlayMatchRepo;
import com.code.duel.code.duel.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    MatchRepo matchRepo;
    @Autowired
    UserPlayMatchRepo userPlayMatchRepo;
    @Autowired
    UserRepo userRepo;

    long idIncrement = 1000;
    public Match createMatch(Long playerId , String difficulty , String programmingLanguage){
        Match newMatch = new Match();
        newMatch.setStatus("PENDING");
        newMatch.setMatchID(idIncrement);
        newMatch.setDifficulty(difficulty);
        newMatch.setProgrammingLanguage(programmingLanguage);
        idIncrement++;
        matchRepo.save(newMatch);

        UserPlayMatch userPlayMatch1 = new UserPlayMatch();
        userPlayMatch1.setUserID(playerId);
        userPlayMatch1.setMatchID(newMatch.getMatchID());
        String username = userRepo.findById(playerId).getUsername();
        System.out.println(username + " created match");
        userPlayMatch1.setUsername(username);
        userPlayMatch1.setUserScore(3); // init score
        userPlayMatchRepo.save(userPlayMatch1);

        return newMatch;
    }

    public Match joinMatch(Long matchId , Long playerId){
        Match wantedMatch = matchRepo.findById(matchId);
        if (wantedMatch == null)
            throw new MatchNotFoundException(matchId);
        UserPlayMatch userPlayMatch = new UserPlayMatch(playerId, wantedMatch.getMatchID(),userRepo.findById(playerId).getUsername() , 3);
        userPlayMatchRepo.save(userPlayMatch);
        wantedMatch.setStatus("RUNNING");
        wantedMatch.setCurrentChallengeId(1L);
        matchRepo.update(wantedMatch);
        return wantedMatch;
    }
}
