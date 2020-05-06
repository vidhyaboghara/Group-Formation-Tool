package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class UserDB implements IUserPersistence
{	
	private static final Logger logger = Logger.getLogger(UserDB.class);
	
	public void loadUserById(long id, IUser user)
	{
		logger.info(getClass());
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadUser(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userId = results.getLong(1);
					String bannerId = results.getString(2);
					String password = results.getString(3);
					String firstName = results.getString(4);
					String lastName = results.getString(5);
					String email = results.getString(6);
					user.setId(userId);
					user.setBannerId(bannerId);
					user.setPassword(password);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in UserDB while fetching user by post id " + id);
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
			logger.error("User credentials not found in database" + user.getBannerId());
		}			
	}

	public void loadUserByBannerId(String bannerId, IUser user)
	{
		logger.info("Inside the class" +getClass());
		CallStoredProcedure proc = null;
		long userId = -1;
		try
		{
			proc = new CallStoredProcedure("spFindUserByBannerID(?)");
			proc.setParameter(1, bannerId);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					userId = results.getLong(1);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in " + getClass() + " in loading user profile with Error Code: " +e.getErrorCode() +
				", Message: " + e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		if (userId > -1)
		{
			loadUserById(userId, user);
		}
	}
	
	public boolean createUser(IUser user)
	{
		logger.info(getClass());
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
			proc.setParameter(1, user.getBannerId());
			proc.setParameter(2, user.getPassword());
			proc.setParameter(3, user.getFirstName());
			proc.setParameter(4, user.getLastName());
			proc.setParameter(5, user.getEmail());
			proc.registerOutputParameterLong(6);
			proc.execute();
			logger.info(" User created sucessfully"+user.getBannerId());
		}
		catch (SQLException e)
		{
			logger.error("Error in creating the user " + user.getBannerId() + "with Error Code: " +e.getErrorCode() +
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
