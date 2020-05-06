package CSCI5308.GroupFormationTool.QuestionManagerTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.QuestionManager.IMultipleChoice;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionPersistence;
import CSCI5308.GroupFormationTool.QuestionManager.Question;

public class QuestionDBMock implements IQuestionPersistence 
{
	private Map<String, IQuestion> questionList;	
	
	public QuestionDBMock() 
	{
		questionList = new HashMap<String, IQuestion>();
		populateQuestions();
	}

	private void populateQuestions() 
	{
		IQuestion q = new Question();
		IUser instructor = new User();
		instructor.setId(1);	
		q.setId(1);
		q.setTitle("Q1");
		q.setText("Test Question 1");
		q.setType("Numeric");
		q.setCreatedOn(new Date(System.currentTimeMillis()));
		q.setInstructor(instructor);
		questionList.put("1", q);
		
		q = new Question();
		q.setId(2);
		q.setTitle("Q2");
		q.setText("Test Question 2");
		q.setType("Numeric");
		q.setCreatedOn(new Date(System.currentTimeMillis()));
		q.setInstructor(instructor);
		questionList.put("2", q);	
		
		instructor = new User();
		instructor.setId(2);
		
		q = new Question();
		q.setId(3);
		q.setTitle("Q3");
		q.setText("Test Question 3");
		q.setType("Numeric");
		q.setCreatedOn(new Date(System.currentTimeMillis()));
		q.setInstructor(instructor);
		questionList.put("3", q);	
	}
	
	@Override
	public boolean createQuestion(IQuestion question)
	{
		for (Map.Entry<String,IQuestion> entry : questionList.entrySet()) 
		{
			if(entry.getValue() == question)
			{
				return false;
			}
		}
		
		questionList.put(String.valueOf(question.getId()),question);
		if(question.getType().equals("MCQ_one") || question.getType().equals("MCQ_multiple"))
		{
			createMultipleChoice(question.getId(), question.getMultipleChoice());
		}
		return true;
	}

	public boolean createMultipleChoice(long id, ArrayList<IMultipleChoice> choices) 
	{
		IQuestion q = null;
		for (Map.Entry<String,IQuestion> entry : questionList.entrySet())
		{
			q = entry.getValue();
			if(id == q.getId())
			{
				q.setMultipleChoice(choices);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteQuestionById(long questionId)
	{
		if(questionList.containsKey(String.valueOf(questionId)))
		{
			questionList.remove(String.valueOf(questionId));
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void loadQuestionById(long id, IQuestion question)
	{
		IQuestion q = questionList.get(String.valueOf(id));
		question.setId(q.getId());
		question.setTitle(q.getTitle());
		question.setText(q.getText());
		question.setType(q.getType());
		question.setCreatedOn(q.getCreatedOn());
	}
}