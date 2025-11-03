package org.example.servletai.service;

public class OpenRouterAIService extends HttpClientService implements AIService {
    protected OpenRouterAIService() {
        super("https://openrouter.ai/api/v1/chat/completions", "OPEN_ROUTER_API_KEY");
    }

    @Override
    public String chat(String question, String model) {
        return sendRequest(question, model);
    }
}
