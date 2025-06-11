package com.code.duel.code.duel.Service;

import com.code.duel.code.duel.Model.Challenge;
import com.code.duel.code.duel.Repository.ChallengeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChallengeService {

    @Autowired
    ChallengeRepo challengeRepo;

    public Challenge getChallengeById(Long challengeId) {
        return challengeRepo.findById(challengeId);
    }

    // Create a new challenge
    public void createChallenge(Challenge challenge) {
        challengeRepo.save(challenge);
    }

    //Delete a challenge
    public void deleteChallenge(Long challengeId) {
        challengeRepo.deleteById(challengeId);
    }
}
