package CSCI5308.GroupFormationTool.QuestionManagerTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.util.ArrayList;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.QuestionManager.IMultipleChoice;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionPersistence;
import CSCI5308.GroupFormationTool.QuestionManager.MultipleChoice;
import CSCI5308.GroupFormationTool.QuestionManager.Question;

@SpringBootTest
public class QuestionTest 
{
	private static IQuestionPersistence questionDBMock;
	
	@BeforeAll
	static void setUpBeforeClass() 
	{
		questionDBMock = new QuestionDBMock();
	}
	
	@Test
	public void constructorTest() 
	{
		IQuestion question = new Question();
		assertEquals(question.getId(), -1);
		assertTrue(question.getText().isEmpty());
		assertTrue(question.getTitle().isEmpty());		
		assertTrue(question.getType().isEmpty());
	}
	
	@Test
	public void getQuestionIdTest() 
	{
		IQuestion question = new Question();
		question.setId(5);
		assertEquals(question.getId(), 5);		
	}
	
	@Test
	public void setQuestionIdTest() 
	{
		IQuestion question = new Question();
		question.setId(5);
		assertEquals(question.getId(), 5);
	}
	
	@Test
	public void getTitleTest() 
	{
		IQuestion question = new Question();
		question.setTitle("title");
		assertEquals(question.getTitle(), "title");
	}
	
	@Test
	public void setTitleTest() 
	{
		IQuestion question = new Question();
		question.setTitle("title");
		assertEquals(question.getTitle(), "title");
	}
	
	@Test
	public void getTypeTest()
	{
		IQuestion question = new Question();
		question.setType("type");
		assertEquals(question.getType(), "type");
	}
	
	@Test
	public void setTypeTest()
	{
		IQuestion question = new Question();
		question.setType("type");
		assertEquals(question.getType(), "type");
	}
	
	@Test
	public void getTextTest()
	{
		IQuestion question = new Question();
		question.setText("text");
		assertEquals(question.getText(), "text");
	}
	
	@Test
	public void setTextTest() 
	{
		IQuestion question = new Question();
		question.setText("text");
		assertEquals(question.getText(), "text");
	}
	
	@Test
	public void getCreatedOnTest()
	{
		IQuestion question = new Question();
		Date date = new Date(System.currentTimeMillis());
		question.setCreatedOn(date);
		assertEquals(question.getCreatedOn(), date);
	}
	
	@Test
	public void setCreatedOnTest() 
	{
		IQuestion question = new Question();
		Date date = new Date(System.currentTimeMillis());
		question.setCreatedOn(date);
		assertEquals(question.getCreatedOn(), date);	
	}
	
	@Test
	public void setInstructorTest() 
	{
		IQuestion question = new Question();
		IUser u = new User();
		u.setId(4);
		question.setInstructor(u);
		assertEquals(question.getInstructor().getId(), 4);
	}
	
	@Test
	public void getInstructorTest() 
	{
		IQuestion question = new Question();
		IUser u = new User();
		u.setId(4);
		question.setInstructor(u);
		assertEquals(question.getInstructor().getId(), 4);
	}
	
	@Test
	public void setMultipleChoiceTest() 
	{
		IQuestion question = new Question();
		ArrayList<IMultipleChoice> choices = new ArrayList<IMultipleChoice>();
		IMultipleChoice choice = new MultipleChoice();
		choice.setChoiceText("text");
		choice.setStoredAs(1);
		choices.add(choice);
		IMultipleChoice choice1 = new MultipleChoice();
		choice1.setChoiceText("text1");
		choice1.setStoredAs(2);
		choices.add(choice1);
		question.setMultipleChoice(choices);
		assertEquals(question.getMultipleChoice(), choices);
	}
	
	@Test
	public void getMultipleChoiceTest() 
	{
		IQuestion question = new Question();
		ArrayList<IMultipleChoice> choices = new ArrayList<IMultipleChoice>();
		MultipleChoice choice = new MultipleChoice();
		choice.setChoiceText("text");
		choice.setStoredAs(1);
		choices.add(choice);
		IMultipleChoice choice1 = new MultipleChoice();
		choice1.setChoiceText("text1");
		choice1.setStoredAs(2);
		choices.add(choice1);
		question.setMultipleChoice(choices);
		assertEquals(question.getMultipleChoice(), choices);
	}
	
	@Test 
	public void createQuestionTest() 
	{
		IQuestion question = new Question();
		question.setId(5);
		question.setTitle("abcdefghij");
		question.setText("abcdefghij");
		question.setType("MCQ_one");
		ArrayList<IMultipleChoice> choices = new ArrayList<IMultipleChoice>();
		IMultipleChoice choice = new MultipleChoice();
		choice.setChoiceText("abc");
		choice.setStoredAs(3);
		choices.add(choice);
		IMultipleChoice choice1 = new MultipleChoice();
		choice1.setChoiceText("def");
		choice1.setStoredAs(4);
		choices.add(choice1);
		question.setMultipleChoice(choices);
		IUser u = new User();
		u.setId(4);
		question.setInstructor(u);
		question.setCreatedOn(new Date(System.currentTimeMillis()));
		assertEquals(question.create(questionDBMock), true);
	}
	
	@Test
	public void deleteQuestionTest() 
	{
		IQuestion q = new Question(3 ,questionDBMock);
		assertTrue(q.delete(questionDBMock));
		assertFalse(q.delete(questionDBMock));
	}
	
	@Test
	public void loadQuestionByIdTest()
	{
		IQuestion q = new Question();
		questionDBMock.loadQuestionById(1, q);
		assertEquals(1, q.getId());
		assertEquals("Q1", q.getTitle());
		assertEquals("Test Question 1", q.getText());
		assertEquals("Numeric", q.getType());
		q.setType("Numeric");
	}
}