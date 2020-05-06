package CSCI5308.GroupFormationTool.AccessControl;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import CSCI5308.GroupFormationTool.SystemConfig;

public class UserNotification implements IUserNotifications 
{	
	@Override
	public void sendUserLoginCredentials(IUser user, String rawPassword) throws AddressException, MessagingException 
	{
		final String subject = "Login Credentails for GroupFormation Tool";
		final String body = "Welcome to the GroupFormation Tool\n"
							 + "Your Login credentials are as follows: \n"
							 + "Username: "+ user.getBannerId()
							 + "\nPassword: "+ rawPassword;
		MimeMessage message = SystemConfig.instance().getEmailConfiguration().getMessage();		
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
		message.setSubject(subject);
		message.setText(body);
		Transport.send(message);
	}
}
