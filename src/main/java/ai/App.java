package ai;

import ai.agent.SimpleAgent;
import ai.config.AiConfig;
import ai.llm.LlmClient;
import ai.llm.OllamaClient;
import ai.llm.OpenAiClient;


/* App：启动与装配层
 * 它负责：
决定用哪个 LLM
创建 Agent
启动流程
 */

public class App {
	
	public static void main(String[] args) {
		
		SimpleAgent agent = new SimpleAgent(AiConfig.llmClient());
		
		String answer = agent.ask(
				//"ログインページのテストケースを生成します。"
				//"generate test cases for a login page."
				"生成登录页面的测试用例。"
		);
		
		System.out.println(answer);
	}

}
