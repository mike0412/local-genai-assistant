package ai.agent;

import ai.llm.LlmClient;
import ai.prompt.PromptTemplate;
import ai.prompt.SystemPrompts;

public class SimpleAgent {
	
	private final LlmClient llm;
	
	public SimpleAgent(LlmClient llm) {
		this.llm = llm;
	}
	
	public String ask(String question) {
		String prompt = PromptTemplate.build(
				SystemPrompts.ASSISTANT,
				question
		);
		return llm.chat(prompt);
	}
	
}
