package ai.vector;

import ai.document.DocumentChunk;
import java.util.*;
/*
 * 真实企业 = FAISS / Milvus / pgvector 
 */

public class InMemoryVectorStore implements VectorStore {
	
	private static class Entry {
		DocumentChunk chunk;
		List<Double> vector;
		
		Entry(DocumentChunk chunk, List<Double> vector) {
			this.chunk = chunk;
			this.vector = vector;
		}
	}
	
	private final List<Entry> store = new ArrayList<>();
	
	@Override
	public void add(DocumentChunk chunk, List<Double> vector) {
		store.add(new Entry(chunk, vector));
	}
	
	@Override
	public int size() {
		return store.size();
	}
	
	@Override
	public List<DocumentChunk> search(List<Double> queryVector, int topK) {
		return store.stream()
				.sorted((a, b) ->
					Double.compare(
						cosineSimilarity(queryVector, b.vector),
						cosineSimilarity(queryVector, a.vector)
					)
				)
				.limit(topK)
				.map(entry -> entry.chunk)
				.toList();
	}
	
	private double cosineSimilarity(List<Double> a, List<Double> b) {
		double dot = 0, normA = 0, normB = 0;
		for (int i = 0; i< a.size(); i++) {
			dot += a.get(i) * b.get(i);
			normA += a.get(i) * a.get(i);
			normB += b.get(i) * b.get(i);
		}
		return dot / (Math.sqrt(normA) * Math.sqrt(normB));	
	}	
}
