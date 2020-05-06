package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class QuestionDB implements IQuestionPersistence
{
	private static final String SINGLE_CHOICE = "MCQ_one";
	private static final String MULTIPLE_CHOICE = "MCQ_multiple";
	private static final Logger logger = Logger.getLogger(QuestionDB.class);
	
	@Override
	public boolean createQuestion(IQuestion question)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?, ?)");
			proc.setParameter(1, question.getTitle());
			proc.setParameter(2, question.getText());
			proc.setParameter(3, question.getType());
			proc.setParameter(4, question.getCreatedOn().toString());
			proc.setParameter(5, question.getInstructor().getId());
			if(question.getType().equals(SINGLE_CHOICE) || question.getType().equals(MULTIPLE_CHOICE))
			{
				ResultSet results = proc.executeWithResults();
				long questionId = 0;
				while(results.next())
				{
					questionId = results.getLong(1);
				}
				createMultipleChoice(questionId, question.getMultipleChoice());				
			}
			else 
			{
				proc.execute();
			}
			logger.info("The question created successfully with ID: " + question.getId() + 
						", Title: " + question.getTitle());
			return true;
		}
		catch (SQLException e)
		{
			logger.error("Error in Creating the Question with ID: " + question.getId() + 
					" with Error Code: " + e.getErrorCode() + ", Message: " + e.getMessage());
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}

	private boolean createMultipleChoice(long id, ArrayList<IMultipleChoice> choices) 
	{
		CallStoredProcedure proc = null;
		try 
		{
			for(int i = 0; i< choices.size();i++) 
			{
				proc = new CallStoredProcedure("spCreateMultipleChoice(?, ?, ?)");
				proc.setParameter(1, id);
				proc.setParameter(2, choices.get(i).getChoiceText());
				proc.setParameter(3, choices.get(i).getStoredAs());
				proc.execute();		
			}
			logger.info("Created the multiple choices Successfully for question with ID: " + id);
			return true;
		}
		catch (SQLException e)
		{
			logger.error("Error in creating multiple choices for question with ID: " + id 
					+ " with Error Code: " + e.getErrorCode() + ", Message: " + e.getMessage());
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}

	@Override
	public boolean deleteQuestionById(long id) 
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spDeleteQuestionById(?)");
			proc.setParameter(1, id);
			proc.execute();
			logger.info("Question deleted successfully with ID: " + id);
		}
		catch (SQLException e)
		{
			logger.error("Error in deleting question with ID: " + id + " with Error Code: "
					+ e.getErrorCode() + ", Message: " + e.getMessage());
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public void loadQuestionById(long id, IQuestion question) 
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindQuestionById(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					String title = results.getString(2);
					String text = results.getString(3);
					String type = results.getString(4);
					Date createdOn = results.getDate(5);
					question.setTitle(title);
					question.setText(text);
					question.setType(type);
					question.setCreatedOn(createdOn);
					logger.info("Loaded question with the question ID: " + id);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in loading Question with ID: " + id + " with Error Code: " + 
					e.getErrorCode() + ", Message: " + e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}
}