package CSCI5308.GroupFormationTool.QuestionManager;

public class MultipleChoice implements IMultipleChoice
{
	private String choiceText;
	private int storedAs;
	
	public MultipleChoice() 
	{
		this.choiceText = "";
		this.storedAs = -1;
	}
	
	public String getChoiceText() 
	{
		return choiceText;
	}
	
	public void setChoiceText(String choiceText)
	{
		this.choiceText = choiceText;
	}
	
	public int getStoredAs() 
	{
		return storedAs;
	}
	
	public void setStoredAs(int storedAs)
	{
		this.storedAs = storedAs;
	}
	
	public boolean isNullorEmpty() 
	{
		if(choiceText==null || choiceText =="") 
		{
			return true;
		}
		return false;
	}
}
