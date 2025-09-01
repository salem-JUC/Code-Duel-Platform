package com.code.duel.code.duel;


import com.code.duel.code.duel.DTO.SubmissionDTO.SubmissionDTO;
import com.code.duel.code.duel.Repository.SubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class runner implements CommandLineRunner {

    @Autowired
    SubmissionRepo submissionRepo;
    public void run(String... args) throws Exception {
        System.out.println(submissionRepo.getSubmissionDetails(1L).toString());

    }
}
