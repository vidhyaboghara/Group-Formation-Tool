package CSCI5308.GroupFormationTool.Courses;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class StudentCSVImport
{
	private List<String> successResults;
	private List<String> failureResults;
	private ICourse course;
	private MultipartFile uploadedFile;
	private IUserPersistence userDB;
	private IPasswordEncryption passwordEncryption;
	private IUserNotifications userNotification;
	private static final Logger logger = Logger.getLogger(StudentCSVImport.class);
	
	public StudentCSVImport(ICourse course, MultipartFile file)
	{
		this.course = course;
		this.uploadedFile = file;
		successResults = new ArrayList<String>();
		failureResults = new ArrayList<String>();
		userDB = SystemConfig.instance().getUserDB();
		userNotification = SystemConfig.instance().getUserNotification();
		passwordEncryption = SystemConfig.instance().getPasswordEncryption();
		parseCSVFile();
	}
	
	private void parseCSVFile()
	{
		try
		{
			Reader reader = new InputStreamReader(uploadedFile.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).build();
			List<String[]> records = csvReader.readAll();
			Iterator<String[]> iter = records.iterator();
			while (iter.hasNext())
			{
				String[] record = iter.next();
				enrollStudentFromRecord(record);
			}
		}
		catch (IOException e)
		{
			failureResults.add("Failure reading uploaded file: " + e.getMessage());
			logger.fatal("Failed to read the CSV file with Error Message: " + e.getMessage());
		}
		catch (Exception e)
		{
			failureResults.add("Failure parsing CSV file: " + e.getMessage());
			logger.error("Failed to parse the CSV file with Error Message: " + e.getMessage());
		}
	}
	
	private void enrollStudentFromRecord(String[] record)
	{
		if (record.length != 4)
		{
			failureResults.add("Faulty row: " + Arrays.toString(record));
			logger.debug("Errors while reading rows from CSV file. Faulty row: " + Arrays.toString(record));
			return;
		}
		String bannerId = record[0];
		String firstName = record[1];
		String lastName = record[2];
		String email = record[3];
		IUser u = new User();
		userDB.loadUserByBannerId(bannerId, u);		
		if (!u.isValidUser())
		{
			u.setBannerId(bannerId);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setEmail(email);
			u.setPassword(bannerId);
			if (u.createUser(userDB, passwordEncryption, userNotification))
			{
				successResults.add("Created: " + Arrays.toString(record));
				logger.info("Created User and loaded to the database: " + Arrays.toString(record));
			}
			else
			{
				failureResults.add("Unable to save this user to DB: " + Arrays.toString(record));
				logger.error("Error saving user in the database: " + Arrays.toString(record));
				return;
			}
		}
		if (course.enrollUserInCourse(Role.STUDENT, u))
		{
			failureResults.add("Unable to enroll user in course: " + Arrays.toString(record));
			logger.error("Error enrolling user in the course: " + Arrays.toString(record));
		}
	}
	
	public List<String> getSuccessResults()
	{
		return successResults;
	}
	
	public List<String> getFailureResults()
	{
		return failureResults;
	}
}
