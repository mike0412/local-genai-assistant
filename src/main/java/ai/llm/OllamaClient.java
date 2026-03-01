package ai.llm;

import ai.util.PromptSanitizer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.*;


public class OllamaClient implements LlmClient {

    private static final String ENDPOINT =
            "http://localhost:11434/api/generate";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    private final String model;
    
    public OllamaClient() {
    	this("llama3");
    }
    
    public OllamaClient(String model) {
    	this.model = model;
    }

    @Override
    public String chat(String prompt) {
        try {
            String safePrompt = PromptSanitizer.clean(prompt);

//            String body = """
//                    {
//                      "model": "qwen2.5",
//                      "prompt": "%s",
//                      "stream": false
//                    }
//                    """.formatted(safePrompt);
            String body = MAPPER.writeValueAsString(
            		MAPPER.createObjectNode()
            				.put("model",model)
            				.put("prompt", safePrompt)
            				.put("stream", false)
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ENDPOINT))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            
            //key: only return pure text
            JsonNode root = MAPPER.readTree(response.body());
            return root.path("response").asText();
            //return response.body();

        } catch (Exception e) {
            throw new RuntimeException("Ollama request failed", e);
        }
    }
}


