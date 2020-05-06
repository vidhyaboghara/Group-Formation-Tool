package CSCI5308.GroupFormationTool.AccessControl;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import CSCI5308.GroupFormationTool.SystemConfig;

public class CurrentUser
{
	private static CurrentUser uniqueInstance = null;
	private static final Logger logger = Logger.getLogger(CurrentUser.class);
	
	public static CurrentUser instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new CurrentUser();
		}
		return uniqueInstance;
	}
	
	public IUser getCurrentAuthenticatedUser()
	{
		logger.info(getClass());
		IUserPersistence userDB = SystemConfig.instance().getUserDB();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated())
		{
			String bannerId = authentication.getPrincipal().toString();
			IUser u = new User();
			userDB.loadUserByBannerId(bannerId, u);
			if (u.isValidUser())
			{
				return u;
			}
		}
		logger.info("User Not Found");
		return null;
	}
}
