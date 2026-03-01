package ai.agent;

import ai.document.DocumentChunk;
import ai.embedding.EmbeddingClient;
import ai.llm.LlmClient;
import ai.prompt.RagPrompts;
import ai.retriever.Retriever;

import java.util.List;
import java.util.stream.Collectors;

public class RagAgent {
	
	private final Retriever retriever;
	private final EmbeddingClient embedding;
	private final LlmClient llm;
	
	public RagAgent(Retriever retriever,
					EmbeddingClient embedding,
					LlmClient llm) {
		this.retriever = retriever;
		this.embedding = embedding;
		this.llm = llm;		
	}
	
//	public String answer(String question) {
//		var chunks = retriever.retrieve(question, 3);
//		
//		String context = chunks.stream()
//				.map(c -> c.content())
//				.reduce("", (a, b) -> a + "\n---\n" + b);
//		
//		String prompt = RagPrompts.ANSWER_WITH_CONTEXT.formatted(
//				context, question
//		);
//		return llm.chat(prompt);
//	}
	
	public String answer(String question) {
		
		System.out.println("[RAG] Question = " + question);
		
		List<DocumentChunk> contexts = retriever.retrieve(question, 3);
		System.out.println("[RAG] Retrieved chunks = " + contexts.size());
		
		contexts.forEach(c -> {
	        System.out.println("---- Chunk ----");
	        System.out.println(c.content());
	    });
		
		String contextText = contexts.stream()
				.map(DocumentChunk::content)
				.collect(Collectors.joining("\n---\n"));
		
		System.out.println("[RAG] Context length = " + contextText.length());
		
		String prompt = RagPrompts.ANSWER_WITH_CONTEXT.formatted(
				contextText, question
		);
		System.out.println("===== FINAL PROMPT =====");
	    System.out.println(prompt);
	    System.out.println("========================");

	    String answer = llm.chat(prompt);
	    System.out.println("[RAG] Answer length = " + answer.length());

	    return answer;	
	}	
}
