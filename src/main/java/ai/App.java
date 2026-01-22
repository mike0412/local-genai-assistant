package ai;

import ai.agent.SimpleAgent;
import ai.config.AiConfig;
import ai.tool.TestCase;
import java.util.List;


/* App：启动与装配层
 * 它负责：
决定用哪个LLM
创建Agent
启动流程
 */

public class App {
	
	public static void main(String[] args) {
		
		SimpleAgent agent = new SimpleAgent(AiConfig.llmClient());
		
		List<TestCase> cases = agent.generateTestCases("生成登录页面测试用例");
		
		cases.forEach(System.out::println);
	}

}
