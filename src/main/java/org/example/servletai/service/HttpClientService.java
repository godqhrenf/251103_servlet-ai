package org.example.servletai.service;

import io.github.cdimascio.dotenv.Dotenv;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public abstract class HttpClientService {
    protected final HttpClient httpClient = HttpClient.newHttpClient();
    protected final String BASE_URL;
    protected final String API_KEY;
    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected HttpClientService(String baseUrl, String name) {
        BASE_URL = baseUrl;
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        API_KEY = dotenv.get(name);
    }

    protected String sendRequest(String question, String model) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Authorization", "Bearer %s".formatted(API_KEY))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        objectMapper.writeValueAsString(new GroqRequest(
                                List.of(new GroqRequest.Message("user", question)), model)
                        ))
                )
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), GroqResponse.class).choices.get(0).message.content;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    record GroqRequest(List<Message> messages, String model) {
        record Message(String role, String content) {}
    }

    record GroqResponse(List<Choice> choices) {
        record Choice(Message message) {
            record Message(String role, String content) {}
        }
    }
}
