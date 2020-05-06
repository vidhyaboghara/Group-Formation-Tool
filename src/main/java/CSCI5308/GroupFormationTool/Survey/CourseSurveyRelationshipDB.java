package CSCI5308.GroupFormationTool.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class CourseSurveyRelationshipDB implements ICourseSurveyRelationshipPersistence 
{
	private static final Logger logger = Logger.getLogger(CourseSurveyRelationshipDB.class);
	
	@Override
	public boolean createSurveyForCourse(String surveyId, ICourse course) 
	{	
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateCourseSurvey(?, ?)");
			proc.setParameter(1, surveyId);
			proc.setParameter(2, course.getId());
			proc.execute();
			logger.info("Successfully create survey for the course ID: " + course.getId());
		}
		catch (SQLException e)
		{
			logger.error("Error in creating survey for course ID: " + course.getId() + 
					"with error code" + e.getErrorCode() + ", Messgage " + e.getMessage());
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
	public boolean surveyNotCreatedForCourse(ICourse course) 
	{
		if(loadSurveyForCourse(course) == null) 
		{
			logger.info("Survey does not exist for course with course ID: " + course.getId());
			return true;
		}
		else 
		{
			return false;
		}
	}

	@Override
	public Survey loadSurveyForCourse(ICourse course) 
	{	
		Survey survey = null;
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindSurveyForCourse(?)");
			proc.setParameter(1, course.getId());
			ResultSet results = proc.executeWithResults();
			while (results.next())
			{
				survey = new Survey(results.getString(2));
				survey.setCourse(course);
			}
			logger.info("Successfully loaded survey for the course with course ID: " + course.getId());
		}
		catch (SQLException e)
		{
			logger.error("Error in loading Survey for the course with course ID: " + course.getId() + 
						" with error code: " + e.getErrorCode() + ", Message: " + e.getMessage());
			return null;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return survey;
	}
}
