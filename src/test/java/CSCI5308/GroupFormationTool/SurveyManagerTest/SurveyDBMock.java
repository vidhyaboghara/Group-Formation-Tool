package CSCI5308.GroupFormationTool.SurveyManagerTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;

public class SurveyDBMock implements ISurveyPersistence {

	private Map<String, List<String>> surveyList;
 
	public SurveyDBMock() 
	{
		surveyList = new HashMap<String, List<String>>();
		populateSurveys();
	}
	
	private void populateSurveys() 
	{
		List<String> questionIdList = new ArrayList<>();
		questionIdList.add("10");
		questionIdList.add("20");
		questionIdList.add("30");
		surveyList.put("1", questionIdList);
		
		questionIdList = new ArrayList<>();
		questionIdList.add("1");
		questionIdList.add("2");
		surveyList.put("2", questionIdList);
	}
	
	@Override
	public boolean addQuestionToSurvey(String surveyId, long questionId) 
	{	
		if(surveyHasQuestion(surveyId, questionId))
		{
			return false;
		}
		else
		{
			String id = String.valueOf(questionId);
			List<String> questionIdList = surveyList.get(surveyId);
			questionIdList.add(id);
			surveyList.put(surveyId, questionIdList);
			return true;
		}
	}

	@Override
	public boolean surveyHasQuestion(String surveyId, long questionId) {

		List<String> questionIdList = surveyList.get(surveyId);
		return questionIdList.contains(String.valueOf(questionId));
	}

	@Override
	public List<IQuestion> loadAllQuestionsFromSurvey(String surveyId) {
		
		List<IQuestion> questions = new ArrayList<>();
		List<String> questionIdList = surveyList.get(surveyId);
		for(String questionId: questionIdList)
		{
			IQuestion q = new Question();
			q.setId(Long.valueOf(questionId));
			questions.add(q);
		}
		return questions;
	}

	@Override
	public boolean deleteQuestionFromSurvey(String surveyId, long questionId) {

		if(surveyHasQuestion(surveyId, questionId))
		{
			String id = String.valueOf(questionId);
			List<String> questionIdList = surveyList.get(surveyId);
			questionIdList.remove(id);
			surveyList.put(surveyId, questionIdList);
			return true;
		}
		else
		{
			return false;
		}
	}
}
