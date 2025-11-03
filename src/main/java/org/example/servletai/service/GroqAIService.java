package org.example.servletai.service;

public class GroqAIService extends HttpClientService implements AIService {
    public GroqAIService() {
        super("https://api.groq.com/openai/v1/chat/completions", "GROQ_API_KEY");
    }

    @Override
    public String chat(String question, String model) {
        return sendRequest(question, model);
    }
}
