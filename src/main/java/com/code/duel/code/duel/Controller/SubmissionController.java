package com.code.duel.code.duel.Controller;

import com.code.duel.code.duel.DTO.SubmissionDTO.SubmissionDTO;
import com.code.duel.code.duel.DTO.SubmissionDTO.SubmissionDetailsDTO;
import com.code.duel.code.duel.DTO.SubmissionDTO.SubmissionWithUserDTO;
import com.code.duel.code.duel.Model.Submission;
import com.code.duel.code.duel.Model.User;
import com.code.duel.code.duel.Service.ChallengeService;
import com.code.duel.code.duel.Service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<SubmissionDTO>> getSubmissionsByUserId(@AuthenticationPrincipal User user) {
        List<SubmissionDTO> submissions = submissionService.getAllSubmissionsOfSubmitter(user.getUserID());
        return ResponseEntity.ok(submissions);
    }
    @GetMapping("/challenge/{challengeId}")
    public ResponseEntity<List<SubmissionWithUserDTO>> getSubmissionsByChallengeId(@PathVariable Long challengeId) {
        List<SubmissionWithUserDTO> submissionWithUserDTO = submissionService.getAllSubmissionsWithUsernames(challengeId);
        return ResponseEntity.ok(submissionWithUserDTO);
    }
    @GetMapping("/{submissionId}")
    public ResponseEntity<SubmissionDetailsDTO> getSubmissionById(@PathVariable Long submissionId) {
        SubmissionDetailsDTO submissionDetailsDTO = submissionService.getSubmissionDetailsDTO(submissionId);
        return ResponseEntity.ok(submissionDetailsDTO);
    }

}
