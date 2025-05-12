package com.code.duel.code.duel.Controller;

import com.code.duel.code.duel.Mappers.ResponseMapper.SubmissionWithChallengeResponse;
import com.code.duel.code.duel.Model.Challenge;
import com.code.duel.code.duel.Model.Submission;
import com.code.duel.code.duel.Model.User;
import com.code.duel.code.duel.Service.ChallengeService;
import com.code.duel.code.duel.Service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    @Autowired
    SubmissionService submissionService;
    @Autowired
    ChallengeService challengeService;

    @GetMapping
    public ResponseEntity<List<Submission>> getSubmissionsByUserId(@AuthenticationPrincipal User user) {
        List<Submission> submissions = submissionService.getAllSubmissionsOfSubmitter(user.getUserID());
        return ResponseEntity.ok(submissions);
    }
    @GetMapping("/challenge/{challengeId}")
    public ResponseEntity<List<Submission>> getSubmissionsByChallengeId(@PathVariable Long challengeId) {
        List<Submission> submissions = submissionService.getAllSubmissionsOfChallenge(challengeId);
        return ResponseEntity.ok(submissions);
    }
    @GetMapping("/{submissionId}")
    public ResponseEntity<SubmissionWithChallengeResponse> getSubmissionById(@PathVariable Long submissionId) {
        Submission submission = submissionService.getSubmissionById(submissionId);
        Challenge challenge = challengeService.getChallengeById(submission.getChallengeID());
        SubmissionWithChallengeResponse submissionWithChallengeResponse = new SubmissionWithChallengeResponse(submission , challenge);
        return ResponseEntity.ok(submissionWithChallengeResponse);
    }
}
