package ai.tool;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/*
 * 先用 public field，降低复杂度，后面再上 Lombok / record
 */
public class TestCase {

    private String id;
    private String title;
    private List<String> steps;
    private String expectedResult;

    public TestCase() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<String> getSteps() { return steps; }
    public void setSteps(List<String> steps) {
    	this.steps = steps;
    }
    public String getExpectedResult() { return expectedResult; }
    public void setExpectedResult(String expectedResult) {
    	this.expectedResult = expectedResult;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", steps=" + steps +
                ", expectedResult='" + expectedResult + '\'' +
                '}';
    }
}
