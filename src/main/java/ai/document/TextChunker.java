package ai.document;

import java.util.List;
import java.util.ArrayList;
/*
 * 纯字符切割
 * 不关心来源，
 * 不关心chunk的身份，来源，位置
 * RAG阶段：MVP
 */

public class TextChunker {
	
	public static List<DocumentChunk> chunk(String text, String source, int size) {
		List<DocumentChunk> chunks = new ArrayList<>();
		int index = 0;
		
		for(int i = 0; i < text.length(); i += size) {
			String content = text.substring(i, Math.min(i + size, text.length()));
			
			chunks.add(new DocumentChunk(
					source + "-" +index,
					content,
					source,
					index++));	
		}
		return chunks;
	}
}
