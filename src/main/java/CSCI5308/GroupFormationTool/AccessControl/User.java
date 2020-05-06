package CSCI5308.GroupFormationTool.AccessControl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.apache.log4j.Logger;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class User implements IUser
{
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";	
	private long id;
	private String password;
	private String bannerId;
	private String firstName;
	private String lastName;
	private String email;
	private static final Logger logger = Logger.getLogger(User.class);
	
	public User()
	{
		setDefaults();
	}
	
	public User(long id, IUserPersistence persistence)
	{
		setDefaults();
		persistence.loadUserById(id, this);
	}
	
	public User(String bannerId, IUserPersistence persistence)
	{
		setDefaults();
		persistence.loadUserByBannerId(bannerId, this);
	}
	
	public void setDefaults()
	{
		id = -1;
		password = "";
		bannerId = "";
		firstName = "";
		lastName = "";
		email = "";
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getId()
	{
		return id;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setBannerId(String bannerId)
	{
		this.bannerId = bannerId;
	}
	
	public String getBannerId()
	{
		return bannerId;
	}
	
	public void setFirstName(String name)
	{
		firstName = name;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setLastName(String name)
	{
		lastName = name;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public boolean isValidUser()
	{
		return id != -1; 
	}
	
	public boolean createUser(
		IUserPersistence userDB,
		IPasswordEncryption passwordEncryption,
		IUserNotifications notification)
		{
		String rawPassword = password;
		this.password = passwordEncryption.encryptPassword(this.password);
		boolean success = userDB.createUser(this);
		if (success && (null != notification))
		{
			try 
			{
				notification.sendUserLoginCredentials(this, rawPassword);
			} 
			catch(AddressException exp) 
			{
				logger.error("An invalid email adddress"+ exp);				
			}
			catch(MessagingException exp) 
			{
				logger.error("Message was not sent to the user"+exp.getMessage());
			}
		}
		return success;
	}
		
	private static boolean isStringNullOrEmpty(String s)
	{
		if (null == s)
		{
			return true;
		}
		return s.isEmpty();
	}
	
	public static boolean isBannerIdValid(String bannerId)
	{
		return !isStringNullOrEmpty(bannerId);
	}
		
	public static boolean isFirstNameValid(String name)
	{
		return !isStringNullOrEmpty(name);
	}
	
	public static boolean isLastNameValid(String name)
	{
		return !isStringNullOrEmpty(name);
	}
	
	public static boolean isEmailValid(String email)
	{
		if (isStringNullOrEmpty(email))
		{
			return false;
		}		 
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}