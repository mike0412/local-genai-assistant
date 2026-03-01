package ai;


import ai.chunk.TextChunker;
import ai.document.Document;
import ai.embedding.OllamaEmbeddingClient;
import ai.loader.PdfLoader;
import ai.retriever.Retriever;
import ai.vector.InMemoryVectorStore;
import ai.agent.RagAgent;
import ai.agent.SimpleAgent;
import ai.config.AiConfig;
import ai.tool.TestCase;

import java.nio.file.Path;
import java.util.List;


/* App：启动与装配层
 * 它负责：
决定用哪个LLM
创建Agent
启动流程
 */

public class App {
	
	public static void main(String[] args) {
		
//		SimpleAgent agent = new SimpleAgent(AiConfig.llmClient());
//		
//		List<TestCase> cases = agent.generateTestCases("生成登录页面测试用例");
//		
//		cases.forEach(System.out::println);
		var loader = new PdfLoader();
		var chunker = new TextChunker();
		var embedding = new OllamaEmbeddingClient();
		var store = new InMemoryVectorStore();
		
		Document doc = loader.load(Path.of("docs/Japanese.pdf"));
		
		var chunks = chunker.chunk(doc, 300);
		System.out.println("Chunks size = " + chunks.size());

		chunker.chunk(doc, 300).forEach(c -> 
				store.add(c, embedding.embed(c.content())));
		
		System.out.println("Vector store size = " + store.size());
		
		var retriever = new Retriever(embedding, store);
		var agent = new RagAgent(retriever, embedding, AiConfig.llmClient());
		
		String answer = agent.answer("日本企業で生成AIはどのように使われていますか？");
		System.out.println(answer);
//		Document doc = new Document(
//				"doc-1",
//				"这是一个用于演示 RAG 的日文文档。生成AI在日本企业中越来越重要。",
//				"sample.pdf"
//		);
		
//		var chunks = chunker.chunk(doc, 20);
//		//debug code
//		System.out.println("Chunks count: " + chunks.size());
//		chunks.forEach(c -> {
//			System.out.println("Chunk ID: " +c.id());
//			System.out.println(c.content());
//		});
		
//		chunks.forEach(c ->
//				store.add(c, embedding.embed(c.content()))
//		);
//		
//		var retriever = new Retriever(embedding, store);
//		retriever.retrieve("日本 生成AI", 2)
//				.forEach(c -> System.out.println(c.content()));	
	}
}
