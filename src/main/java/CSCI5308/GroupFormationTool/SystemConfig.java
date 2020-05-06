package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Security.*;
import CSCI5308.GroupFormationTool.Survey.CourseSurveyRelationshipDB;
import CSCI5308.GroupFormationTool.Survey.ICourseSurveyRelationshipPersistence;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyDB;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Courses.CourseDB;
import CSCI5308.GroupFormationTool.Courses.CourseUserRelationshipDB;
import CSCI5308.GroupFormationTool.Courses.EmailConfiguration;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.IEmailConfiguration;
import CSCI5308.GroupFormationTool.Database.*;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionPersistence;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionUserRelationship;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionDB;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionUserRelationshipDB;

public class SystemConfig
{
	private static SystemConfig uniqueInstance = null;
	private IPasswordEncryption passwordEncryption;
	private IUserPersistence userDB;
	private IDatabaseConfiguration databaseConfiguration;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IEmailConfiguration emailConfiguration;
	private IUserNotifications userNotification;
	private IQuestionPersistence questionDB;
	private ISurveyPersistence surveyDB;
	private ICourseSurveyRelationshipPersistence courseSurveyRelationshipDB;
	private IQuestionUserRelationship questionUserRelationshipDB;
	
	private SystemConfig()
	{
		passwordEncryption = new BCryptPasswordEncryption();
		userDB = new UserDB();
		databaseConfiguration = new DefaultDatabaseConfiguration();
		courseDB = new CourseDB();
		courseUserRelationshipDB = new CourseUserRelationshipDB();
		emailConfiguration = new EmailConfiguration();
		userNotification = new UserNotification();
		questionDB = new QuestionDB();
		surveyDB = new SurveyDB();
		courseSurveyRelationshipDB = new CourseSurveyRelationshipDB();
		questionUserRelationshipDB = new QuestionUserRelationshipDB();
	}

	public static SystemConfig instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new SystemConfig();
		}
		return uniqueInstance;
	}
	
	public IQuestionUserRelationship getQuestionUserRelationshipDB()
	{
		return questionUserRelationshipDB;
	}

	public void setQuestionUserRelationshipDB(IQuestionUserRelationship questionUserRelationshipDB)
	{
		this.questionUserRelationshipDB = questionUserRelationshipDB;
	}
	
	public ISurveyPersistence getSurveyDB()
	{
		return surveyDB;
	}
	
	public ICourseSurveyRelationshipPersistence getCourseSurveyRelationshipDB()
	{
		return courseSurveyRelationshipDB;
	}
	
	public IEmailConfiguration getEmailConfiguration()
	{
		return emailConfiguration;
	}
	
	public IUserNotifications getUserNotification()
	{
		return userNotification;
	}
	
	public IPasswordEncryption getPasswordEncryption()
	{
		return passwordEncryption;
	}
	
	public void setPasswordEncryption(IPasswordEncryption passwordEncryption)
	{
		this.passwordEncryption = passwordEncryption;
	}
	
	public IUserPersistence getUserDB()
	{
		return userDB;
	}
	
	public void setUserDB(IUserPersistence userDB)
	{
		this.userDB = userDB;
	}
	
	public IDatabaseConfiguration getDatabaseConfiguration()
	{
		return databaseConfiguration;
	}
	
	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration)
	{
		this.databaseConfiguration = databaseConfiguration;
	}
	
	public void setCourseDB(ICoursePersistence courseDB)
	{
		this.courseDB = courseDB;
	}
	
	public ICoursePersistence getCourseDB()
	{
		return courseDB;
	}
	
	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB)
	{
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}
	
	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB()
	{
		return courseUserRelationshipDB;
	}
	
	public IQuestionPersistence getQuestionDB() 
	{
		return questionDB;
	}
	
	public void setQuestionDB(IQuestionPersistence questionDB) 
	{
		this.questionDB = questionDB;
	}
}
