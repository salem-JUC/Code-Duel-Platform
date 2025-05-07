package com.code.duel.code.duel.Judge;

import com.code.duel.code.duel.Model.Submission;
import com.code.duel.code.duel.Model.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class EvaluationModule {

    @Autowired
    private Judge0Wrapper judge0Wrapper;

    public Submission evaluate(Submission submission, List<TestCase> testCases) {
        System.out.println("Evaluating submission: " + submission.getSubmissionID());
        int languageId =0;
        if (submission.getProgrammingLanguage().equals("Java")) {
            System.out.println("Java");
            languageId =4;
        }
        else if (submission.getProgrammingLanguage().equals("Python")) {
            System.out.println("Python");
            languageId=27;
        }

        for (int i = 0; i < testCases.size(); i++) {
            TestCase testCase = testCases.get(i);
            try {
                //the submit mathod want languageid to be int not string
                String status = judge0Wrapper.submit(
                        submission.getCode(),
                        languageId,
                        testCase.getInput(),
                        testCase.getExpectedOutput()
                );

                if (!"Accepted".equalsIgnoreCase(status)) {
                    submission.setResult(status);
                    return submission;
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                submission.setResult("Error");
                return submission;
            }
            System.out.println("Test case " + (i + 1) + " passed.");
        }

        submission.setResult("Accepted");
        return submission;
    }
}
