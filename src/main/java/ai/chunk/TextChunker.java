package ai.chunk;

import ai.document.Document;
import ai.document.DocumentChunk;

import java.util.ArrayList;
import java.util.List;

/* 切割策略层
 * 策略可替换（token / sentence / overlap）
 */

public class TextChunker {
	
	public List<DocumentChunk> chunk(Document document, int size){
		List<DocumentChunk> chunks = new ArrayList<>();
		
		String text = document.content();
		int index = 0;
		
		for (int i = 0; i < text.length(); i += size) {
			String part = text.substring(i, Math.min(i + size, text.length()));
			chunks.add(new DocumentChunk(
					document.id() + "-" + index,
					part,
					document.source(),
					index
			));
			index++;
		}
		return chunks;
	}
	
	
	
}
