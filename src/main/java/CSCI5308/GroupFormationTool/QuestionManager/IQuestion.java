package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Date;
import java.util.ArrayList;
import CSCI5308.GroupFormationTool.AccessControl.IUser;

public interface IQuestion 
{
	public boolean create(IQuestionPersistence questionDB);
	public boolean delete(IQuestionPersistence questionDB);
	public ArrayList<IMultipleChoice> getMultipleChoice();
	public void setMultipleChoice(ArrayList<IMultipleChoice> multipleChoice);
	public IUser getInstructor();
	public void setInstructor(IUser instructor);
	public long getId();
	public void setId(long id);
	public String getTitle();
	public void setTitle(String title);
	public String getText();
	public void setText(String text);
	public String getType();
	public void setType(String type);
	public Date getCreatedOn();
	public void setCreatedOn(Date createdOn);
}
