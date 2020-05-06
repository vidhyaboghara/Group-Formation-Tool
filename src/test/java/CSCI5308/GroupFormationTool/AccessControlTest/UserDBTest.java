package CSCI5308.GroupFormationTool.AccessControlTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class UserDBTest 
{	
	IUser user = new User();
	
	@Test
	public void loadUserById() {
		assertNotEquals("B00000000",user.getBannerId());
		assertNotEquals("rhawkey@dal.ca", user.getEmail());
		assertNotEquals("Rob", user.getFirstName());
		assertNotEquals("Hawkey", user.getLastName());
	}
	
	@Test
	public void loadUserByBannerId() {
		assertNotEquals("rhawkey@dal.ca", user.getEmail());
		assertNotEquals("Rob", user.getFirstName());
		assertNotEquals("Hawkey", user.getLastName());
	}
}
