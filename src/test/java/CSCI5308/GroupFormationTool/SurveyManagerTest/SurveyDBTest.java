package CSCI5308.GroupFormationTool.SurveyManagerTest;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;

public class SurveyDBTest 
{
	private ISurveyPersistence surveyDBMock;
	
	@BeforeEach
	void setUpBeforeClass() 
	{
		surveyDBMock = new SurveyDBMock();
	}
	
	@Test
	public void surveyHasQuestionTest()
	{
		assertTrue(surveyDBMock.surveyHasQuestion("1", 10));
		assertFalse(surveyDBMock.surveyHasQuestion("1", 25));
		assertTrue(surveyDBMock.surveyHasQuestion("2", 1));
		assertFalse(surveyDBMock.surveyHasQuestion("2", 5));
	}
	
	@Test
	public void loadAllQuestionsFromSurveyTest()
	{
		List<IQuestion> questions = surveyDBMock.loadAllQuestionsFromSurvey("2");
		assertEquals(questions.size(), 2);
		questions = surveyDBMock.loadAllQuestionsFromSurvey("1");
		assertEquals(questions.size(), 3);
	}
	
	@Test
	public void addQuestionToSurveyTest()
	{
		assertTrue(surveyDBMock.surveyHasQuestion("2", 1));
		assertFalse(surveyDBMock.addQuestionToSurvey("2", 1));
		assertTrue(surveyDBMock.addQuestionToSurvey("2", 3));
		assertTrue(surveyDBMock.surveyHasQuestion("2", 3));		
	}
	
	@Test
	public void deleteQuestionFromSurveyTest()
	{
		assertFalse(surveyDBMock.surveyHasQuestion("2", 10));
		assertFalse(surveyDBMock.deleteQuestionFromSurvey("2", 10));
		assertTrue(surveyDBMock.surveyHasQuestion("1", 20));
		assertTrue(surveyDBMock.deleteQuestionFromSurvey("1", 20));				
		assertFalse(surveyDBMock.surveyHasQuestion("1", 20));
	}
}
