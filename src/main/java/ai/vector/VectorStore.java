package ai.vector;

import ai.document.DocumentChunk;
import java.util.List;

public interface VectorStore {
	
	void add(DocumentChunk chunk, List<Double> vector);
	
	List<DocumentChunk> search(List<Double> queryVector, int topK);
	
	int size();
}
