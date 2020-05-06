package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;

public class UserDBMock implements IUserPersistence
{
	public void loadUserById(long id, IUser user)
	{
		user.setBannerId("B00000000");
		user.setEmail("rhawkey@dal.ca");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setId(id);
	}

	public void loadUserByBannerId(String bannerId, IUser user)
	{
		user.setBannerId(bannerId);
		user.setEmail("rhawkey@dal.ca");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		user.setId(1);
	}
	
	public boolean createUser(IUser user)
	{
		user.setBannerId("B00000000");
		user.setEmail("rhawkey@dal.ca");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		return true;
	}
}
