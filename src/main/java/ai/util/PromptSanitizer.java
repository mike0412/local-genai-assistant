package ai.util;

public class PromptSanitizer {
	
	private PromptSanitizer() {}
	
	public static String clean(String text) {
		if (text == null)  return "";
		return text
			.replace("\r", "")
	        .replace("\n", "\\n")
	        .replace("\"", "\\\"")
	        .replace("\\", "\\\\");
	}

}
