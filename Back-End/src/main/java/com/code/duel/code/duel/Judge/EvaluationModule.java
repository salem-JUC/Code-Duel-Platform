package com.code.duel.code.duel.Judge;

import com.code.duel.code.duel.Model.Submission;
import com.code.duel.code.duel.Model.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class EvaluationModule {

    @Autowired
    private Judge0Wrapper judge0Wrapper;

    private static final Logger logger = LoggerFactory.getLogger(EvaluationModule.class);

    public String evaluate(Submission submission, List<TestCase> testCases) {
        logger.info("Evaluating submission: {} by user id: {}", submission.getSubmissionID(), submission.getSubmitterID());
        int languageId =0;
        if (submission.getProgrammingLanguage().equals("Java")) {
            languageId =4;
        }
        else if (submission.getProgrammingLanguage().equals("Python")) {
            languageId=27;
        }

        for (int i = 0; i < testCases.size(); i++) {
            TestCase testCase = testCases.get(i);
            logger.info("Evaluating test case {}: input = {}, expected output = {}", i + 1, testCase.getInput(), testCase.getExpectedOutput());
            try {
                //the submit mathod want languageid to be int not string
                String status = judge0Wrapper.submit(
                        submission.getCode(),
                        languageId,
                        testCase.getInput(),
                        testCase.getExpectedOutput()
                );
                logger.info("Test case {} status: {}", i + 1, status);

                if (!status.equals("Accepted")) {
                    logger.info("Test case {} failed: {}", i + 1, status);
                    return status;
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return "Execuation Error";
            }
            logger.info("Test case {} passed", i + 1);
        }
        logger.info("All test cases passed for submission: {} by user id: {}", submission.getSubmissionID(), submission.getSubmitterID());

        return "Accepted";
    }
}
