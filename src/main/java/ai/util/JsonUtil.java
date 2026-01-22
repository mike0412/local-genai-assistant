package ai.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 *Util：协议 / 数据清洗层
 * 
 * 
 */

public class JsonUtil {
	
	//simple and stable: only abstract response fileds
	private static final Pattern RESPONSE_PATTERN =
			Pattern.compile("\"response\"\\s*:\\s*\"(.*?)\"", Pattern.DOTALL);
	
	public static String extractResponse(String json) {
		if(json == null || json.isBlank()) {
			return "";
		}
		
		Matcher matcher = RESPONSE_PATTERN.matcher(json);
		if(matcher.find()) {
			return unescape(matcher.group(1));
		}
		
		throw new IllegalArgumentException(
				"Cannot extract response from JSON:\n" + json
		);	
	}
	
	private static String unescape(String text) {
		return text
				.replace("\\n", "\n")
				.replace("\\\"", "\"")
				.replace("\\\\", "\\");
	}

}
