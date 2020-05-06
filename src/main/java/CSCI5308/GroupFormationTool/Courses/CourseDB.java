package CSCI5308.GroupFormationTool.Courses;

import java.util.List;
import org.apache.log4j.Logger;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDB implements ICoursePersistence
{
	private static final Logger logger = Logger.getLogger(CourseDB.class);
	public List<ICourse> loadAllCourses()
	{
		logger.info("Inside the class" + getClass());
		List<ICourse> courses = new ArrayList<ICourse>();
		CallStoredProcedure proc = null;		
		try
		{
			proc = new CallStoredProcedure("spLoadAllCourses()");
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long id = results.getLong(1);
					String title = results.getString(2);
					ICourse c = new Course();
					c.setId(id);
					c.setTitle(title);
					courses.add(c);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in loading all courses with Error Code: " + e.getErrorCode() +
					", Message: " + e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return courses;
	}

	public void loadCourseById(long id, ICourse course)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindCourseByID(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					String title = results.getString(2);
					course.setId(id);
					course.setTitle(title);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in loading course for the course ID: " + id + " with Error Code: " + e.getErrorCode() +
					", Message: " + e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}
	
	public boolean createCourse(ICourse course)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateCourse(?, ?)");
			proc.setParameter(1, course.getTitle());
			proc.registerOutputParameterLong(2);
			proc.execute();
			logger.info("Course created successfully " + course.getId() + " and " + course.getTitle());
		}
		catch (SQLException e)
		{
			logger.error("Error in creating a course with course ID: " + course.getId() + 
					" and title: " + course.getTitle() + " with Error Code: " + e.getErrorCode() +
					", Message: " + e.getMessage());
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
	
	public boolean deleteCourse(long id)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spDeleteCourse(?)");
			proc.setParameter(1, id);
			proc.execute();
			logger.info(" Course with Course ID "+ id + " is deleted successfully");
		}
		catch (SQLException e)
		{
			logger.error("Error in deleting a course with course ID: " + id + 
					 " with Error Code: " + e.getErrorCode() +
					", Message: " + e.getMessage());
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
