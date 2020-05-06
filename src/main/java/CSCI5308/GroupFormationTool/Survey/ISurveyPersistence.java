package CSCI5308.GroupFormationTool.Survey;

import java.util.List;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

public interface ISurveyPersistence 
{
	public boolean addQuestionToSurvey(String surveyId, long questionId);
	public boolean surveyHasQuestion(String surveyId, long questionId);
	public List<IQuestion> loadAllQuestionsFromSurvey(String surveyId);
	public boolean deleteQuestionFromSurvey(String surveyId, long questionId);
}
