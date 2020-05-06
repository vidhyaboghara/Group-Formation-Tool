package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class QuestionUserRelationshipDB implements IQuestionUserRelationship
{
	private static final Logger logger = Logger.getLogger(QuestionUserRelationshipDB.class);
	
	@Override
	public List<IQuestion> loadAllQuestionsByInstructor(IUser instructor , String sortBy)
	{
		List<IQuestion> questions = new ArrayList<IQuestion>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadAllQuestionsByInstructor(?,?)");
			proc.setParameter(1, instructor.getId());
			proc.setParameter(2, sortBy);
			logger.info("Loaded all the questions for the instructor with ID: " + instructor.getId());
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long id = results.getLong(1);
					String title = results.getString(2);
					String text = results.getString(3);
					String type = results.getString(4);
					Date createdOn = results.getDate(5);
					IQuestion q = new Question();
					q.setId(id);
					q.setTitle(title);
					q.setText(text);
					q.setType(type);
					q.setCreatedOn(createdOn);
					questions.add(q);
				}
			}
		}
		catch (SQLException e)
		{
			logger.error("Error in loading All Questions By Instructor with ID: " + instructor.getId() 
					 + " with Error Code: " + e.getErrorCode() + ", Message: " + e.getMessage());
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return questions;
	}
}
