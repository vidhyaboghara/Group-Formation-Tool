package CSCI5308.GroupFormationTool.AccessControl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface IUserNotifications
{
	public void sendUserLoginCredentials(IUser user, String rawPassword) throws AddressException, MessagingException;
}
