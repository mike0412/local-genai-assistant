package ai;

import ai.agent.SimpleAgent;
import ai.llm.OllamaClient;

public class App {
	
	public static void main(String[] args) {
		SimpleAgent agent = 
				new SimpleAgent(new OllamaClient());
		
		String answer = agent.ask(
				"Generate test cases for a login page."
		);
		
		System.out.println(answer);
	}

}
