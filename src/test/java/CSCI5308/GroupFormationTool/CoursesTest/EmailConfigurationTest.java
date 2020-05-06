package CSCI5308.GroupFormationTool.CoursesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EmailConfigurationTest 
{	
	@Test
	public void sendEmail() 
	{
		String to = "harshi123@gmail.com";
		String body = "Test email body";
		String subject = "Test subject";
		
		assertEquals(to,"harshi123@gmail.com");
		assertEquals(body, "Test email body");
		assertEquals(subject, "Test subject");		
	}
}
