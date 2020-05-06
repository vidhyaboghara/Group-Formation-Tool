package CSCI5308.GroupFormationTool.SurveyManagerTest;

import java.util.HashMap;
import java.util.Map;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Survey.ICourseSurveyRelationshipPersistence;
import CSCI5308.GroupFormationTool.Survey.Survey;

public class CourseSurveyRelationshipDBMock implements ICourseSurveyRelationshipPersistence
{
	private Map<String, String> courseSurveyMap;
	
	public CourseSurveyRelationshipDBMock() 
	{
		courseSurveyMap = new HashMap<String, String>();
		populateCourseServeyMap();
	}

	private void populateCourseServeyMap()
	{
		courseSurveyMap.put("1", "survey_1");
		courseSurveyMap.put("2", "survey_2");
		courseSurveyMap.put("3", "survey_3");
	}
	
	@Override
	public boolean createSurveyForCourse(String surveyId, ICourse course) {
		
		if(surveyNotCreatedForCourse(course))
		{
			String courseId = String.valueOf(course.getId());
			courseSurveyMap.put(courseId, surveyId);
			return true;
		}
		else
		{
			return false;			
		}
	}

	@Override
	public boolean surveyNotCreatedForCourse(ICourse course) {
		
		String courseId = String.valueOf(course.getId());
		if(courseSurveyMap.containsKey(courseId))
		{
			return false;
		}
		else
		{
			return true;			
		}
	}

	@Override
	public Survey loadSurveyForCourse(ICourse course) {
	
		if(surveyNotCreatedForCourse(course))
		{
			return null;
		}
		else
		{
			String courseId = String.valueOf(course.getId());	
			String surveyId = courseSurveyMap.get(courseId);
			Survey survey = new Survey(surveyId);
			survey.setCourse(course);
			return survey;
		}	
	}
}
