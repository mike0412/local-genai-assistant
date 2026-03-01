package ai.prompt;

public class RagPrompts {
	
	public static final String ANSWER_WITH_CONTEXT = """
	You are a professinal assistant.
	
	Use only the following context to answer the question.
	If the answer is not in the context, say:
	'該当情報は文書内に見つかりませんでした。'
	
	Ｃontext:
	%s
	
	Ｑuestion:
	%s
	
	Answer in Japanese.
	""";
}

