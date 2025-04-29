package com.code.duel.code.duel.Judge;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Component
public class Judge0Wrapper {

    private static final Logger logger = LoggerFactory.getLogger(Judge0Wrapper.class);

    public String submit(String sourceCode, int languageId, String input, String expected_output) throws IOException, InterruptedException {
        String sourceCodeEncoded = Base64.getEncoder().encodeToString(sourceCode.getBytes());
        String inputEncoded = Base64.getEncoder().encodeToString(input.getBytes());
        String expectedOutputEncoded = Base64.getEncoder().encodeToString(expected_output.getBytes());
        String jsonPayload = "{\n" +
                "  \"source_code\": \"" + sourceCodeEncoded + "\",\n" +
                "  \"language_id\": " + languageId + ",\n" +
                "  \"stdin\": \"" + inputEncoded + "\",\n" +
                "  \"expected_output\": \"" + expectedOutputEncoded + "\"\n" +
                "}";

        logger.info("Payload to submit: " + jsonPayload);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge0-extra-ce.p.rapidapi.com/submissions?base64_encoded=true&wait=true&fields=*"))
                .header("x-rapidapi-key", "e3f6fcba8emsh427fb13a3e71224p128063jsn30301f1d3849")
                .header("x-rapidapi-host", "judge0-extra-ce.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(jsonPayload)
                )
                .build();

        logger.info("Request to submit: " + request.headers());

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("Response from Judge0: " + response.body());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.body());
        String statusDescription = rootNode.path("status").path("description").asText();
        return statusDescription;
    }

    public String getLanguages() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge0-extra-ce.p.rapidapi.com/languages"))
                .header("x-rapidapi-key", "e3f6fcba8emsh427fb13a3e71224p128063jsn30301f1d3849")
                .header("x-rapidapi-host", "judge0-extra-ce.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}