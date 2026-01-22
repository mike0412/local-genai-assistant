package ai.config;

import ai.llm.LlmClient;
import ai.llm.OllamaClient;
import ai.llm.OpenAiClient;

/* config 的标准职责
 * 1. 选择用哪个 LLM
 * 2. 模型 & 参数配置
 * 3. 环境差异(本地：Ollama,CI：Mock LLM,生产：OpenAI / Azure)
 * */

public class AiConfig {
	
	public static LlmClient llmClient() {
		String provider = System.getProperty("llm.provider", "ollama");
		
		return switch(provider) {
			case "openai" -> new OpenAiClient(openAiKey(), openAiModel());
			case "ollama" -> new OllamaClient();
			default -> throw new IllegalArgumentException(
					"Unknown llm.provider: " + provider);
		};
	}
	
	private static String openAiKey() {
		String key = System.getenv("OPENAI_API_KEY");
		if(key == null || key.isBlank()) {
			throw new IllegalStateException("OPENAI_API_KEY is not set");
		}
		return key;
	}

	private static String openAiModel() {
		return System.getProperty("openai.model", "gpt-4o-mini");
	}
}
