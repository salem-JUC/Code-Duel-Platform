package com.code.duel.code.duel.Service;

import com.code.duel.code.duel.Model.Submission;
import com.code.duel.code.duel.Repository.SubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {
    @Autowired
    SubmissionRepo submissionRepo;

    public List<Submission> getAllSubmissionsOfChallenge(Long challengeId) {
        return submissionRepo.findByChallengeId(challengeId);
    }

    public List<Submission> getAllSubmissionsOfSubmitter(Long submitterId) {
        return submissionRepo.findBysubmitterId(submitterId);
    }


}