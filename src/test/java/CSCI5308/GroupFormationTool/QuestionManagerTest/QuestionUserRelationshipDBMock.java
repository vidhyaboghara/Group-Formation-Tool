package CSCI5308.GroupFormationTool.QuestionManagerTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionUserRelationship;
import CSCI5308.GroupFormationTool.QuestionManager.Question;

public class QuestionUserRelationshipDBMock implements IQuestionUserRelationship
{
	private Map<String, List<IQuestion>> instructorQuestionList;
	
	public QuestionUserRelationshipDBMock() 
	{
		instructorQuestionList = new HashMap<String, List<IQuestion>>();
		populateQuestions();
	}
	
	private void populateQuestions()
	{
		IQuestion q = new Question();
		List<IQuestion> questionList = new ArrayList<IQuestion>();	
		q.setId(1);
		questionList.add(q);
		q = new Question();
		q.setId(2);
		questionList.add(q);
		q = new Question();
		q.setId(3);
		questionList.add(q);
		
		instructorQuestionList.put("1", questionList);
	}
	
	@Override
	public List<IQuestion> loadAllQuestionsByInstructor(IUser instructor, String sortBY) {

		String instructorId = String.valueOf(instructor.getId());
		return instructorQuestionList.get(instructorId);
	}

}
