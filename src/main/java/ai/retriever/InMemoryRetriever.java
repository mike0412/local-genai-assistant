package ai.retriever;

import java.util.*;
import java.util.stream.IntStream;

public class InMemoryRetriever {
	
	private List<float[]> vectors = new ArrayList<>();
	private List<String> texts = new ArrayList<>();
	
	public void add(String text, float[] embedding) {
		texts.add(text);
		vectors.add(embedding);
	}
	
//	public List<String> search(float[] query, int topK) {
//		return IntStream.range(0, vectors.size())
//			.boxed()
//			.sorted((i, j) -> 
//				Float.compare(
//					cosine(vectors.get(j), query),
//					cosine(vectors.get(i), query)
//				))
//			.limit(topK)
//			.map(texts::get)
//			.toList();
//	}
}
