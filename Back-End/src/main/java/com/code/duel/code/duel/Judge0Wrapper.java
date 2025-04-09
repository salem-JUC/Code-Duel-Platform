package com.code.duel.code.duel;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Judge0Wrapper {
    public void submit() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge0-extra-ce.p.rapidapi.com/submissions?base64_encoded=true&wait=true&fields=*"))
                .header("x-rapidapi-key", "e3f6fcba8emsh427fb13a3e71224p128063jsn30301f1d3849")
                .header("x-rapidapi-host", "judge0-extra-ce.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .method("POST",
                        HttpRequest.BodyPublishers.ofString("{\"language_id\":1,\"source_code\":\"I2luY2x1ZGUgPHN0ZGlvLmg+CgppbnQgbWFpbih2b2lkKSB7CiAgY2hhciBuYW1lWzEwXTsKICBzY2FuZigiJXMiLCBuYW1lKTsKICBwcmludGYoImhlbGxvLCAlc1xuIiwgbmFtZSk7CiAgcmV0dXJuIDA7Cn0=\",\"stdin\":\"SnVkZ2Uw\"}")
                )
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
