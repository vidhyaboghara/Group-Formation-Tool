package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Date;
import java.util.ArrayList;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionPersistence;

public class Question implements IQuestion
{
	private long id;
	private String title;
	private String text;
	private String type;
	private Date createdOn;
	private IUser instructor;
	private ArrayList<IMultipleChoice> multipleChoice;
	
	public Question()
	{
		setDefaults();
	}
	
	public Question(long id, IQuestionPersistence questionDB) 
	{
		setDefaults();
		setId(id);
		questionDB.loadQuestionById(id, this);
	}
	
	public ArrayList<IMultipleChoice> getMultipleChoice()
	{
		return multipleChoice;
	}

	public void setMultipleChoice(ArrayList<IMultipleChoice> multipleChoice)
	{
		this.multipleChoice = multipleChoice;
	}

	public IUser getInstructor()
	{
		return instructor;
	}

	public void setInstructor(IUser instructor) 
	{
		this.instructor = instructor;
	}
	
	public void setDefaults() 
	{
		id=-1;
		title="";
		text="";
		type="";
		instructor=null;
	}
	
	public long getId() 
	{
		return id;
	}
	
	public void setId(long id) 
	{
		this.id = id;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
	}
	
	public String getText() 
	{
		return text;
	}
	
	public void setText(String text) 
	{
		this.text = text;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public Date getCreatedOn() 
	{
		return createdOn;
	}
	
	public boolean create(IQuestionPersistence questionDB)
	{
		return questionDB.createQuestion(this);
	}
	
	public boolean delete(IQuestionPersistence questionDB)
	{
		
		return questionDB.deleteQuestionById(this.id);
	}
	
	public void setCreatedOn(Date createdOn) 
	{
		this.createdOn = createdOn;
	}
}
