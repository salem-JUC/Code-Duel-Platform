package com.code.duel.code.duel;

import com.code.duel.code.duel.Model.TestCase;
import com.code.duel.code.duel.Repository.ChallengeRepo;
import com.code.duel.code.duel.Repository.TestCaseRepo;
import com.code.duel.code.duel.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class runner implements CommandLineRunner {

    @Autowired
    private ChallengeRepo challengeRepo;

    @Override
    public void run(String... args) throws Exception {
    }
}
