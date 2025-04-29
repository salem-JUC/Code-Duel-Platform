package com.code.duel.code.duel;

import com.code.duel.code.duel.Exception.MatchNotFoundException;
import com.code.duel.code.duel.Judge.Judge0Wrapper;
import com.code.duel.code.duel.Model.Difficulty;
import com.code.duel.code.duel.Model.Match;
import com.code.duel.code.duel.Repository.MatchRepo;
import com.code.duel.code.duel.Repository.UserPlayMatchRepo;
import com.code.duel.code.duel.Repository.UserRepo;
import com.code.duel.code.duel.Service.MatchService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class runner implements CommandLineRunner {

    @Autowired
    Judge0Wrapper judge0Wrapper;

    public void run(String... args) throws Exception {
        String sourceCode = "import java.util.Scanner;\n\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner scanner = new Scanner(System.in);\n" +
                "        double num1 = scanner.nextDouble();\n" +
                "        double num2 = scanner.nextDouble();\n" +
                "        System.out.println(num1 + num2);\n" +
                "    }\n" +
                "}";
        int languageId = 4;
        String input = "5 3";
        String expectedOutput = "8.0";

        String response = judge0Wrapper.submit(sourceCode, languageId, input, expectedOutput);


    }
}
