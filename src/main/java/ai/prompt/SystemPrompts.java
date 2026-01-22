package ai.prompt;

/*Prompt：认知边界与输出约束层
 * Prompt 在工程中的角色是：
限定 LLM 的“角色、边界、输出形态”
你现在的：
SystemPrompts.ASSISTANT
实际上是在做：
角色设定（senior test engineer）
风格约束（clear / structural）
不确定性处理策略 
 */

public class SystemPrompts {

    public static final String TEST_CASE_JSON = """
        You are a senior software test engineer.
        
        Output ONLY valid JSON.
        Do NOT include explanations or markdown.
        
        The output format must be:
        
        {
        "testCases": [
    	  {
    		"id": "TC01",
    		"title": "...",
    		"steps": "...",
    		"expectedResult": "..."
          }
         ]
        }
        """;
}
