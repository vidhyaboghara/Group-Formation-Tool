package CSCI5308.GroupFormationTool.Database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class DefaultDatabaseConfiguration implements IDatabaseConfiguration
{
	private static final String URL = "spring.datasource.url";
	private static final String USER = "spring.datasource.username";
	private static final String PASSWORD = "spring.datasource.password";
	
	private static String url;
    private static String user;
    private static String password;
	private static final Logger logger = Logger.getLogger(DefaultDatabaseConfiguration.class);

    public DefaultDatabaseConfiguration()
    {
    	try(InputStream inputStream = new FileInputStream("src/main/resources/application.properties"))
    	{
    		Properties properties = new Properties();
            properties.load(inputStream);
            url = properties.getProperty(URL);
            user = properties.getProperty(USER);
            password = properties.getProperty(PASSWORD);
    	}
    	catch(Exception e) 
    	{
    		logger.error("Error in connecting to database with Error Message: " + e.getMessage());
    	}
    }
    
	public String getDatabaseUserName()
	{
		return user;
	}

	public String getDatabasePassword()
	{
		return password;
	}

	public String getDatabaseURL()
	{
		return url;
	}
}
