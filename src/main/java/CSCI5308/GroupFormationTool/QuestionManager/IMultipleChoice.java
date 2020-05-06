package CSCI5308.GroupFormationTool.QuestionManager;

public interface IMultipleChoice 
{
	public String getChoiceText();
	public void setChoiceText(String choiceText);
	public int getStoredAs();
	public void setStoredAs(int storedAs);
	public boolean isNullorEmpty();
}
