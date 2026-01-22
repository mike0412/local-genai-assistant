package ai.llm;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenAiClient implements LlmClient {
	
	private static final String ENDPOINT = 
			"https://api.openai.com/v1/chat/completions";
	
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
			String body = """
				{
				"model": "%s",
				"messages": [
				{ "role": "user", "content": "%s"}
				],
				"temperature": 0.2
				}
				""".formatted(model, escape(prompt));
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(ENDPOINT))
					.header("Authorization", "Bearer " + apiKey)
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(body))
					.build();
			
			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response =
					client.send(request, HttpResponse.BodyHandlers.ofString());
			
			return response.body();
		} catch(Exception e) {
			throw new RuntimeException("OpenAI request failed", e);
		}
	}
	
	private String escape(String text) {
		return text
				.replace("\\", "\\\\")
				.replace("\"", "\\\"")
				.replace("\n", "\\n");
	}
}
