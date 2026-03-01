package ai.embedding;
import java.util.List;

/*
 * 接口抽象
 * 
 */

public interface EmbeddingClient {
	
	List<Double> embed(String text);
}
