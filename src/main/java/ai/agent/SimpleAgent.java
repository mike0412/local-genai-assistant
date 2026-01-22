package ai.agent;

import ai.llm.LlmClient;
import ai.prompt.PromptTemplate;
import ai.prompt.SystemPrompts;
import ai.tool.TestCase;
import ai.util.JsonUtil;

import java.util.List;

/*Agent：对外协作与决策层
 * Agent 的真实职责是：
把「用户意图」转化为「可执行的 LLM 行为链」
它通常负责：
接收用户输入（外界）
组合 Prompt
决定是否调用 Tool
调用 LLM
返回最终结果

关键点：
Agent 不直接干活，它是「指挥官 / 调度者」。

现在的 SimpleAgent 正处于：
Single-step Agent（单步代理）
这是非常正确的第一阶段。

 */

public class SimpleAgent {
	
	private final LlmClient llm;
	
	public SimpleAgent(LlmClient llm) {
		this.llm = llm;
	}
	
	public List<TestCase> generateTestCases(String requirement) {
		String prompt = PromptTemplate.build(
				SystemPrompts.TEST_CASE_JSON,
				requirement
		);
		
		String raw = llm.chat(prompt);
		
		return JsonUtil.parseTestCases(raw);
	}
	
}
