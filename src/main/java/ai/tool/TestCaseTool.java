package ai.tool;

/*
 * 先用 public field，降低复杂度，后面再上 Lombok / record
 */

public class TestCaseTool {
	
	public String id;
	public String title;
	public String steps;
	public String expectedResult;
	
}
