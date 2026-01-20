package ai.llm;

import java.net.http.*;
import java.net.URI;

public class OllamaClient implements LlmClient {

    private static final String ENDPOINT =
            "http://localhost:11434/api/generate";

    @Override
    public String chat(String prompt) {
        try {
            String safePrompt = prompt
                    .replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n");

            String body = """
                    {
                      "model": "qwen2.5",
                      "prompt": "%s",
                      "stream": false
                    }
                    """.formatted(safePrompt);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ENDPOINT))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("Ollama request failed", e);
        }
    }
}

