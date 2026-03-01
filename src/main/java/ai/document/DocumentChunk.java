package ai.document;


/* RAG 的最小知识单元
 * 领域模型
 * RAG阶段：正式架构
 * 以后：embedding / search / citation 都靠它
 * */
public class DocumentChunk {
	
	private final String id;
	private final String content;
	private final String source;  //pdf file name / url
	private final int chunkIndex;
	
	public DocumentChunk(String id, String content, String source, int chunkIndex) {
		this.id = id;
		this.content = content;
		this.source = source;
		this.chunkIndex = chunkIndex;
	}
	
	public String id() {
		return id;
	}
	
	public String content() {
		return content;
	}
	
	public String source() {
		return source;
	}
	
	public int chunkIndex() {
		return chunkIndex;
	}

}
