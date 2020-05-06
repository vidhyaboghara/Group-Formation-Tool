package CSCI5308.GroupFormationTool;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroupFormationToolApplication 
{
	final static Logger logger = Logger.getLogger(GroupFormationToolApplication.class);
	public static void main(String[] args) 
	{
		logger.info("Starting Group Formation Tool Project");
		SpringApplication.run(GroupFormationToolApplication.class, args);
		PropertyConfigurator.configure("src/main/resources/log4j.properties");		 
	}
}
