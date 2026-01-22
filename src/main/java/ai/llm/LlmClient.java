package ai.llm;

/*LLM：模型适配与推理执行层
 * llm 层的本质是：
模型适配器（Model Adapter）
它解决的是：
OpenAI / Ollama / Claude / Gemini 差异
API 协议差异
Streaming / Non-streaming
Token / 参数控制
 * 
 */

public interface LlmClient {

	String chat(String prompt);
	
}
