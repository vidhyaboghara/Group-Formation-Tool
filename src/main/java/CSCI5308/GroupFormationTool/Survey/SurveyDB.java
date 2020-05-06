package CSCI5308.GroupFormationTool.Survey;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;

public class SurveyDB implements ISurveyPersistence 
{
	private static final Logger logger = Logger.getLogger(SurveyDB.class);
	
	@Override
	public boolean addQuestionToSurvey(String surveyId, long questionId) 
	{		
		if(surveyHasQuestion(surveyId, questionId)) {
			return true;
		} 
		else {
			CallStoredProcedure proc = null;
			try
			{
				proc = new CallStoredProcedure("spAddQuestionToSurvey(?, ?)");
				proc.setParameter(1, surveyId);
				proc.setParameter(2, questionId);
				proc.execute();
			}
			catch (SQLException e)
			{
				logger.error("Error in adding question with ID: " + questionId + " to the survey "
							+ "with ID: " + surveyId + " with error code: " + e.getErrorCode()
							+ ", Messgage " + e.getMessage());
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
	}

	@Override
	public boolean surveyHasQuestion(String surveyId, long questionId) 
	{		
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindQuestionInSurvey(?, ?)");
			proc.setParameter(1, surveyId);
			proc.setParameter(2, questionId);
			ResultSet results = proc.executeWithResults();
			if(results.next()) 
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in finding question with ID: " + questionId + " from the survey "
					+ "with ID: " + surveyId + " with error code: " + e.getErrorCode()
					+ ", Messgage " + e.getMessage());
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
	public List<IQuestion> loadAllQuestionsFromSurvey(String surveyId) 
	{	
		List<IQuestion> questions = new ArrayList<IQuestion>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadAllQuestionsFromSurvey(?)");
			proc.setParameter(1, surveyId);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long id = results.getLong(1);
					String title = results.getString(2);
					String text = results.getString(3);
					String type = results.getString(4);
					Date createdOn = results.getDate(5);
					IQuestion q = new Question();
					q.setId(id);
					q.setTitle(title);
					q.setText(text);
					q.setType(type);
					q.setCreatedOn(createdOn);
					questions.add(q);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in loading all questions from the survey "
					+ "with ID: " + surveyId + " with error code: " + e.getErrorCode()
					+ ", Messgage " + e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return questions;
	}

	@Override
	public boolean deleteQuestionFromSurvey(String surveyId, long questionId) 
	{		
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spDeleteQuestionFromSurvey(?,?)");
			proc.setParameter(1, surveyId);
			proc.setParameter(2, questionId);
			proc.execute();
		}
		catch (SQLException e)
		{
			logger.error("Error in deleting question with ID: " + questionId + " from the survey "
					+ "with ID: " + surveyId + " with error code: " + e.getErrorCode()
					+ ", Messgage " + e.getMessage());
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
}
