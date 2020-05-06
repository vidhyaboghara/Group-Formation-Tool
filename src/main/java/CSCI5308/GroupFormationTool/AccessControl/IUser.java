package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public interface IUser 
{
	public void setId(long id);
	public long getId();
	public void setPassword(String password);
	public String getPassword();
	public void setBannerId(String bannerId);
	public String getBannerId();
	public String getFirstName();
	public void setFirstName(String name);
	public void setLastName(String name);
	public String getLastName();
	public void setEmail(String email);
	public String getEmail();
	public boolean isValidUser();
	public boolean createUser(
			IUserPersistence userDB,
			IPasswordEncryption passwordEncryption,
			IUserNotifications notification);
}
