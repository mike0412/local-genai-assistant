package ai.prompt;

public class PromptTemplate {

    public static String build(String system, String user) {
        return """
            <system>
            %s
            </system>

            <user>
            %s
            </user>
            """.formatted(system, user);
    }
}
