package ai.llm;

import ai.util.PromptSanitizer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenAiClient implements LlmClient {
	
	private static final String ENDPOINT = 
			"https://api.openai.com/v1/chat/completions";
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private final String apiKey;
	private final String model;
	
	public OpenAiClient(String apiKey) {
		this(apiKey, "gpt-4o-mini");
	}
	
	public OpenAiClient(String apiKey, String model) {
		this.apiKey = apiKey;
		this.model = model;
	}
	
	@Override
	public String chat(String prompt) {
		try {
			String safePrompt = PromptSanitizer.clean(prompt);
			
			ObjectNode rootObject = MAPPER.createObjectNode();
			rootObject.put("model", model);
			rootObject.put("temperature", 0.2);

			ArrayNode messages = MAPPER.createArrayNode();
			messages.add(
			    MAPPER.createObjectNode()
			        .put("role", "user")
			        .put("content", safePrompt)
			);

			rootObject.set("messages", messages);

			String body = MAPPER.writeValueAsString(rootObject);
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(ENDPOINT))
					.header("Authorization", "Bearer " + apiKey)
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(body))
					.build();
			
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response =
					client.send(request, HttpResponse.BodyHandlers.ofString());
			
			JsonNode root = MAPPER.readTree(response.body());
			return root
					.path("choices")
					.get(0)
					.path("message")
					.path("content")
					.asText();
		} catch(Exception e) {
			throw new RuntimeException("OpenAI request failed", e);
		}
	}
}
