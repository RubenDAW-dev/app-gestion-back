package com.appgestion.app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;

@Service
public class GroqService {
    
    @Value("${groq.api.key}")
    private String apiKey;
    
    @Value("${groq.api.url}")
    private String apiUrl;
    
    @Value("${groq.model}")
    private String model;
    
    private final WebClient webClient;
    
    public GroqService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
    
    public String generateResponse(String userMessage, String context) {
        String systemPrompt = """
            Eres un asistente inteligente para una aplicación de gestión de proyectos y tareas.
            
            CONTEXTO DEL USUARIO:
            %s
            
            INSTRUCCIONES:
            - Responde de forma concisa y directa
            - Usa formato markdown para listas
            - Si no tienes info suficiente, pregunta
            - Sugiere acciones relevantes
            """.formatted(context);
        
        Map<String, Object> request = Map.of(
            "model", model,
            "messages", List.of(
                Map.of("role", "system", "content", systemPrompt),
                Map.of("role", "user", "content", userMessage)
            ),
            "temperature", 0.7,
            "max_tokens", 500
        );
        
        try {
            Map<String, Object> response = webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
            
            if (response != null && response.containsKey("choices")) {
                List<Map> choices = (List<Map>) response.get("choices");
                if (!choices.isEmpty()) {
                    Map message = (Map) choices.get(0).get("message");
                    return (String) message.get("content");
                }
            }
            
            return "Error al generar respuesta";
            
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error: " + e.getMessage();
        }
    }
}
