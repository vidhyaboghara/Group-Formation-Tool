package CSCI5308.GroupFormationTool.Courses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class CourseUserRelationshipDB implements ICourseUserRelationshipPersistence
{
	private static final Logger logger = Logger.getLogger(CourseUserRelationship.class);
	public List<IUser> findAllUsersWithoutCourseRole(Role role, long courseId)
	{
		List<IUser> users = new ArrayList<IUser>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindUsersWithoutCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2,  courseId);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userId = results.getLong(1);
					String bannerId = results.getString(2);
					String firstName = results.getString(3);
					String lastName = results.getString(4);
					IUser u = new User();
					u.setId(userId);
					u.setBannerId(bannerId);
					u.setFirstName(firstName);
					u.setLastName(lastName);
					users.add(u);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in finding users without course role " + role.name() + 
					 " for the course ID: " + courseId + " with Error Code: " + e.getErrorCode() +
					 ", Message: " + e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return users;
	}

	public List<IUser> findAllUsersWithCourseRole(Role role, long courseId)
	{
		List<IUser> users = new ArrayList<IUser>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindUsersWithCourseRole(?, ?)");
			proc.setParameter(1, role.toString());
			proc.setParameter(2,  courseId);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userId = results.getLong(1);
					IUser u = new User();
					u.setId(userId);
					users.add(u);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in finding users with course role " + role.name() + 
					 " for the course ID: " + courseId + " with Error Code: " + e.getErrorCode() +
					 ", Message: " + e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return users;
	}
	
	public boolean enrollUser(ICourse course, IUser user, Role role)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spEnrollUser(?, ?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getId());
			proc.setParameter(3, role.toString());
			proc.execute();
		}
		catch (SQLException e)
		{
			logger.error("Error in enrolling the user with banner ID " + user.getBannerId() + " to the course" 
					+ course.getId() + " with Error Code: " + e.getErrorCode() + 
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

	public List<Role> loadUserRolesForCourse(ICourse course, IUser user)
	{
		List<Role> roles = new ArrayList<Role>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadUserRolesForCourse(?, ?)");
			proc.setParameter(1, course.getId());
			proc.setParameter(2, user.getId());
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					Role role = Role.valueOf(results.getString(1).toUpperCase());
					roles.add(role);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in loading the user roles for the current course " + course.getId() 
					+ " for user with Banner ID: " + user.getBannerId() + " with Error code: "
					+ e.getErrorCode() + ", Message: " + e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return roles;
	}
}
