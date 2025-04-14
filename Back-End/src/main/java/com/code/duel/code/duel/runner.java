package com.code.duel.code.duel;

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

        Match newMatch = matchService.createMatch(2L , "Easy" , "Java");
        matchService.joinMatch(1500L , 3L);


    }
}
