package CSCI5308.GroupFormationTool.QuestionManagerTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import CSCI5308.GroupFormationTool.QuestionManager.IMultipleChoice;
import CSCI5308.GroupFormationTool.QuestionManager.MultipleChoice;

@SpringBootTest
class MultipleChoiceTest 
{	
	@Test
	void testConstructor() 
	{
		IMultipleChoice multipleChoice= new MultipleChoice();
		assertEquals(multipleChoice.getChoiceText(), "");
		assertEquals(multipleChoice.getStoredAs(), -1);
	}
	
	@Test
	public void getChoiceTextTest() 
	{
		IMultipleChoice multipleChoice= new MultipleChoice();
		assertEquals(multipleChoice.getChoiceText(), "");
		multipleChoice.setChoiceText("choicetext");
		assertEquals(multipleChoice.getChoiceText(), "choicetext");
	}
	
	@Test
	public void setChoiceTextTest() 
	{
		IMultipleChoice multipleChoice= new MultipleChoice();
		multipleChoice.setChoiceText("choicetext");
		assertEquals(multipleChoice.getChoiceText(), "choicetext");
	}
	
	@Test
	public void getStoredAsTest() 
	{
		IMultipleChoice multipleChoice= new MultipleChoice();
		assertEquals(multipleChoice.getStoredAs(), -1);
		multipleChoice.setStoredAs(5);
		assertEquals(multipleChoice.getStoredAs(), 5);
	}
	
	@Test
	public void setStoredAsTest() 
	{
		IMultipleChoice multipleChoice= new MultipleChoice();
		multipleChoice.setStoredAs(5);
		assertEquals(multipleChoice.getStoredAs(), 5);
	}
}