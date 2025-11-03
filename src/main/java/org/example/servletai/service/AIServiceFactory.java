package org.example.servletai.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AIServiceFactory {
    private static final Map<Class<? extends AIService>, AIService> serviceCache = new ConcurrentHashMap<>();

    static {
        serviceCache.put(GeminiAIService.class, new GeminiAIService());
        serviceCache.put(GroqAIService.class, new GroqAIService());
        serviceCache.put(OpenRouterAIService.class, new OpenRouterAIService());
    }

    public static AIService getService(String model) {
        if (model != null && model.startsWith("gemini")) {
            return serviceCache.get(GeminiAIService.class);
        }
        if (model != null && (model.startsWith("llama") || model.startsWith("openai"))) {
            return serviceCache.get(GroqAIService.class);
        }
        if (model != null && (model.startsWith("minimax") || model.startsWith("alibaba"))) {
            return serviceCache.get(OpenRouterAIService.class);
        }

        return serviceCache.get(GeminiAIService.class);
    }
}