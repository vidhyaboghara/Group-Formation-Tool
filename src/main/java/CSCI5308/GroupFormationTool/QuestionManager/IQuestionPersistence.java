package CSCI5308.GroupFormationTool.QuestionManager;

public interface IQuestionPersistence 
{
	public boolean deleteQuestionById(long questionId);
	public void loadQuestionById(long id, IQuestion question);
	public boolean createQuestion(IQuestion question);
}
