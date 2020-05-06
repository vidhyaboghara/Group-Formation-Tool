package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserPersistence
{
	public void loadUserById(long id, IUser user);
	public void loadUserByBannerId(String bannerId, IUser user);
	public boolean createUser(IUser user);
}
