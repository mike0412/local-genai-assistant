package ai.embedding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
 * 模型推荐：
 * nomic-embed-text
 * mxbai-embed-large 
 */

public class OllamaEmbeddingClient implements EmbeddingClient {
	
	@Override
	public List<Double> embed(String text) {
		//mock embedding
		Random random = new Random(text.hashCode());
		List<Double> vector = new ArrayList<>();
		for (int i = 0; i < 384; i++) {
			vector.add(random.nextDouble());
		}
		return vector;
	}
}
