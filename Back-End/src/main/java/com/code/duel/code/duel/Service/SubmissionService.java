package com.code.duel.code.duel.Service;

import com.code.duel.code.duel.Judge.EvaluationModule;
import com.code.duel.code.duel.Model.Submission;
import com.code.duel.code.duel.RequestMapper.SubmissionRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {

    @Autowired
    EvaluationModule evaluationModule;

    public void submit(SubmissionRequestMapper submissionRequestMapper) {

        evaluationModule.evaluate(submissionRequestMapper , )
    }
}
