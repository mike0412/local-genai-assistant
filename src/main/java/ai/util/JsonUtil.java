package ai.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.tool.TestCase;

/*
 *Util：协议 / 数据清洗层
 * 
 * 
 */

public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Step 1: 解析 Ollama/OpenAI 外层协议
     */
    public static String extractBusinessJson(String rawJson) {
        try {
            JsonNode root = MAPPER.readTree(rawJson);

            JsonNode responseNode = root.get("response");
            if (responseNode == null || responseNode.isNull()) {
                throw new IllegalStateException(
                        "LLM response does not contain 'response' field:\n"
                                + root.toPrettyString());
            }

            return responseNode.asText();

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to parse LLM protocol JSON:\n" + rawJson, e);
        }
    }

    /**
     * Step 2: 解析业务 JSON（cases）
     */
    public static List<TestCase> parseTestCases(String rawJson) {

        String businessJson = extractBusinessJson(rawJson);

        try {
            JsonNode root = MAPPER.readTree(businessJson);

            JsonNode casesNode = root.get("cases");
            if (casesNode == null || !casesNode.isArray()) {
                throw new IllegalStateException(
                        "Business JSON does not contain 'cases' array:\n"
                                + root.toPrettyString());
            }

            List<TestCase> cases = new ArrayList<>();
            for (JsonNode node : casesNode) {
                cases.add(MAPPER.treeToValue(node, TestCase.class));
            }
            return cases;

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to parse business JSON:\n" + businessJson, e);
        }
    }
}