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

已经完成了「最小可用 AI Agent 架构（MVP），工程视角看，已经具备：
| 能力                           | 状态 |
| ---------------------------- | -- |
| LLM 抽象（LlmClient）            | ✅  |
| Ollama 本地模型接入            | ✅  |
| Prompt 工程（System / Template） | ✅  |
| Agent 角色封装                 | ✅  |
| CLI / main 入口                | ✅  |
| Maven / JDK17                 | ✅  |

