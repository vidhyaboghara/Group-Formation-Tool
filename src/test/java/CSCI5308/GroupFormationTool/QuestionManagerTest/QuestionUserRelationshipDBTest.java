package CSCI5308.GroupFormationTool.QuestionManagerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionUserRelationship;

public class QuestionUserRelationshipDBTest {

	private IQuestionUserRelationship questionUserRelationshipDBMock;
	
	@BeforeEach
	public void setUpBeforeClass()
	{
		questionUserRelationshipDBMock = new QuestionUserRelationshipDBMock();
	}
	
	@Test
	public void loadAllQuestionsByInstructorTest() 
	{
		List<IQuestion> questions;
		IUser user = new User();
		user.setId(1);
		questions = questionUserRelationshipDBMock.loadAllQuestionsByInstructor(user, "id");
		assertEquals(3, questions.size());
	}

}
