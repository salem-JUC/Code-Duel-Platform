package com.code.duel.code.duel.Service;

import com.code.duel.code.duel.Model.Challenge;
import com.code.duel.code.duel.Repository.ChallengeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    @Autowired
    ChallengeRepo challengeRepo;

    public Challenge findById(Long id){
        return challengeRepo.findById(id);
    }
}
