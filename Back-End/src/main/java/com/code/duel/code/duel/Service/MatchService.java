package com.code.duel.code.duel.Service;

import com.code.duel.code.duel.Exception.MatchNotFoundException;
import com.code.duel.code.duel.Model.Match;
import com.code.duel.code.duel.Model.UserPlayMatch;
import com.code.duel.code.duel.Repository.ChallengeRepo;
import com.code.duel.code.duel.Repository.MatchRepo;
import com.code.duel.code.duel.Repository.UserPlayMatchRepo;
import com.code.duel.code.duel.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    MatchRepo matchRepo;
    @Autowired
    UserPlayMatchRepo userPlayMatchRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ChallengeRepo challengeRepo;

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
        Match wantedMatch;
        try {
            wantedMatch = matchRepo.findById(matchId);
        }catch (EmptyResultDataAccessException e){
            throw new MatchNotFoundException(matchId);
        }
        if (!wantedMatch.getStatus().equals("PENDING"))
            throw new MatchNotFoundException(matchId);
        UserPlayMatch userPlayMatch = new UserPlayMatch(playerId, wantedMatch.getMatchID(),userRepo.findById(playerId).getUsername() , 3);
        userPlayMatchRepo.save(userPlayMatch);
        wantedMatch.setStatus("RUNNING");
        wantedMatch.setCurrentChallengeId(1L);
        matchRepo.update(wantedMatch);
        assignChallenge(wantedMatch.getMatchID());
        return wantedMatch;
    }

    public void assignChallenge(Long matchId){
        Match match = matchRepo.findById(matchId);
        match.setCurrentChallengeId(challengeRepo.findRandomWithDifficulty(match.getDifficulty()).getChallengeID());
        matchRepo.update(match);
    }
}
