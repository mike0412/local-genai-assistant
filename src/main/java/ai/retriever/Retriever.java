package ai.retriever;

import ai.document.DocumentChunk;
import ai.embedding.EmbeddingClient;
import ai.vector.VectorStore;

import java.util.List;

/*
 * RAG 中的 “R”
 */

public class Retriever {
	
	private final EmbeddingClient embeddingClient;
	private final VectorStore vectorStore;
	
	public Retriever(EmbeddingClient embeddingClient, VectorStore vectorStore) {
		this.embeddingClient = embeddingClient;
		this.vectorStore = vectorStore;
	}
	
	public List<DocumentChunk> retrieve(String query, int topK) {
		var queryVector = embeddingClient.embed(query);
		return vectorStore.search(queryVector, topK);
	}

}
