package ai.document;

/*
 * Domain Object
 */

public class Document {
	
	private final String id;
	private final String content;
	private final String source;
	
	public Document(String id, String content, String source) {
		this.id = id;
		this.content = content;
		this.source = source;
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
	
}
