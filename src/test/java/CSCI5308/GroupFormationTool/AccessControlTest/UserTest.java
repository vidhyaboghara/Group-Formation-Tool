package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest
{
	@Test
	public void constructorTests()
	{
		IUser u = new User();
		assertTrue(u.getBannerId().isEmpty());
		assertTrue(u.getFirstName().isEmpty());
		assertTrue(u.getLastName().isEmpty());
		assertTrue(u.getEmail().isEmpty());		
		
		IUserPersistence userDBMock = new UserDBMock();
		u = new User(1, userDBMock);
		assertTrue(u.getBannerId().equals("B00000000"));
		
		u = new User("B00000000", userDBMock);
		assertTrue(u.getBannerId().equals("B00000000"));
	}
	
	@Test
	public void setIdTest()
	{
		IUser u = new User();
		u.setId(10);
		assertTrue(10 == u.getId());
	}
	
	@Test
	public void getIdTest()
	{
		IUser u = new User();
		u.setId(10);
		assertTrue(10 == u.getId());
	}
	
	@Test
	public void setBannerIdTest()
	{
		IUser u = new User();
		u.setBannerId("B00000000");
		assertTrue(u.getBannerId().equals("B00000000"));
	}
	
	@Test
	public void getBannerIdTest()
	{
		IUser u = new User();
		u.setBannerId("B00000000");
		assertTrue(u.getBannerId().equals("B00000000"));
	}
	
	@Test
	public void setFirstNameTest()
	{
		IUser u = new User();
		u.setFirstName("Rob");
		assertTrue(u.getFirstName().equals("Rob"));
	}
	
	@Test
	public void getFirstNameTest()
	{
		IUser u = new User();
		u.setFirstName("Rob");
		assertTrue(u.getFirstName().equals("Rob"));
	}

	@Test
	public void setLastNameTest()
	{
		IUser u = new User();
		u.setLastName("Hawkey");
		assertTrue(u.getLastName().equals("Hawkey"));
	}

	@Test
	public void getLastNameTest()
	{
		IUser u = new User();
		u.setLastName("Hawkey");
		assertTrue(u.getLastName().equals("Hawkey"));
	}
	
	@Test
	public void setEmailTest()
	{
		IUser u = new User();
		u.setEmail("rhawkey@dal.ca");
		assertTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void getEmailTest()
	{
		IUser u = new User();
		u.setEmail("rhawkey@dal.ca");
		assertTrue(u.getEmail().equals("rhawkey@dal.ca"));
	}
	
	@Test
	public void isBannerIdValidTest()
	{
		assertTrue(User.isBannerIdValid("B000000000"));
		assertTrue(!User.isBannerIdValid(null));
		assertTrue(!User.isBannerIdValid(""));
	}
		
	@Test
	public void isFirstNameValidTest()
	{
		assertTrue(User.isFirstNameValid("rob"));
		assertTrue(!User.isFirstNameValid(null));
		assertTrue(!User.isFirstNameValid(""));
	}
	
	@Test
	public void isLastNameValidTest()
	{
		assertTrue(User.isLastNameValid("hawkey"));
		assertTrue(!User.isLastNameValid(null));
		assertTrue(!User.isLastNameValid(""));
	}
	
	@Test
	public void isEmailValidTest()
	{
		assertTrue(User.isEmailValid("rhawkey@dal.ca"));
		assertTrue(!User.isEmailValid(null));
		assertTrue(!User.isEmailValid(""));
		assertTrue(!User.isEmailValid("@dal.ca"));
		assertTrue(!User.isEmailValid("rhawkey@"));
	}	
}