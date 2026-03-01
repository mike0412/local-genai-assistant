1-19
本项目以ollama为基础实现生成AI功能，输入基础代码后
期间需要安装ollama，安装步骤如下：
打开https://ollama.com/
下载download for windows
安装完成后ollama --version
ollama pull qwen2.5
ollama run qwen2.5
输入文字，模型正常回复，说明模型正常运行。
local-genai-assistant
├─ src/main/java        👉 生产代码（AI Agent / LLM Client）
│  └─ ai
│     ├─ agent          👉 Agent 行为（SimpleAgent）
│     ├─ config         👉 配置（AiConfig）
│     ├─ llm            👉 LLM 客户端（Ollama / OpenAI）
│     ├─ prompt         👉 Prompt 模板
│     ├─ tool           👉 Agent Tool（TestCaseTool）
│     ├─ util           👉 JSON 工具
│     └─ App.java       👉 🚀 程序入口
│
├─ src/test/java        👉 测试
│  └─ ai
│     └─ AgentTest.java
│
└─ pom.xml              👉 Maven 配置
运行成功

已经完成了「最小可用 AI Agent 架构（MVP），工程视角看，具备：
| 能力                           | 状态 |
| ---------------------------- | -- |
| LLM 抽象（LlmClient）            | ✅  |
| Ollama 本地模型接入            | ✅  |
| Prompt 工程（System / Template） | ✅  |
| Agent 角色封装                 | ✅  |
| CLI / main 入口                | ✅  |
| Maven / JDK17                 | ✅  |


方向 1（最推荐）：提纯 LLM 输出（工程必做）
目标
让 LlmClient.chat() 返回 纯文本，而不是 JSON
为什么重要？
以后所有 Agent / Tool 都会依赖这一步
下一步动作
新增 JsonUtil.extractResponse(String json)
OllamaClient 内部解析 JSON
Agent 完全不关心协议
这是 工程化第一步

方向 2：把 Agent 升级为「任务型 Agent」
现在：
agent.ask("Generate test cases for a login page");
下一步可以是：
agent.generateTestCases("login page");
agent.reviewTestCases(text);
agent.refineTestCases(text);
从「聊天」变成「能力」

方向 3：实现你的第一个 Tool（强烈推荐）
你已经留了：
ai.tool.TestCaseTool
可以让 Agent：
调用 Tool
Tool 负责结构化输出
比如：
TestCaseTool.parse(rawText);
这是 Agent 工程 vs 聊天工具 的分水岭

方向 4：Prompt 专业化（测试工程师专属）
现在 System Prompt 是通用的：
You are a senior software test engineer
你可以拆成：
TEST_CASE_GENERATOR
TEST_CASE_REVIEWER
NEGATIVE_CASE_EXPERT
一个 Agent，多种人格（非常实用）

方向 5：加第一个「真实测试」
现在 mvn test 是空的
下一步可以加：
@Test
void should_generate_test_cases() {
    String result = agent.ask(...);
    assert result.contains("Test Case");
}
这是 AI 工程的自动回归测试

🚀 方向 6（进阶）：生成代码而不是文本
终极形态之一：
输入需求 → 输出 .java 文件
比如：
Selenium Page
JUnit Test
Enum
你前面做的 Selenium 架构，正好可以喂给 AI

推荐顺序（3 步）
1️ JsonUtil：提纯 Ollama 输出
2️ TestCaseTool：结构化测试用例
3️ AgentTest：给 AI 加回归测试

1-22建立了agent-llm-prompt的架构，
testcase定义测试用例模板，App作为程序入口，
agent做任务调度和决策，config管理llm，
llm接口实现各种大模型的即插即用。
tool用以扩展，agent可以扩展。
