package org.example.servletai.service;

import com.google.genai.Client;
import io.github.cdimascio.dotenv.Dotenv;

public class GeminiAIService implements AIService {
    private final Client client;

    public GeminiAIService() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String apiKey = dotenv.get("GEMINI_API_KEY");
        client = Client.builder().apiKey(apiKey).build();
    }

    @Override
    public String chat(String question, String model) {
        return client.models.generateContent(model, question, null).text();
    }
}
