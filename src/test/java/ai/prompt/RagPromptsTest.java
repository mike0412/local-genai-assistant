package ai.prompt;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RagPromptsTest {
	
	@Test
	void shouldContainContextAndQuestion() {
		String context = "これはテスト用の文脈です。";
		String question = "生成AIは何ですか？";
		
		String prompt = RagPrompts.ANSWER_WITH_CONTEXT
				.formatted(context, question);
		
		assertTrue(prompt.contains(context));
		assertTrue(prompt.contains(question));
	}
	
	@Test
    void shouldContainFallbackInstruction() {
        String prompt = RagPrompts.ANSWER_WITH_CONTEXT
                .formatted("dummy", "dummy");

        assertTrue(prompt.contains("該当情報は文書内に見つかりませんでした"));
    }

    @Test
    void shouldForceJapaneseAnswer() {
        String prompt = RagPrompts.ANSWER_WITH_CONTEXT
                .formatted("dummy", "dummy");

        assertTrue(prompt.contains("Answer in Japanese"));
    }

}
