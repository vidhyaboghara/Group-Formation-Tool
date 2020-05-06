package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Courses.ICourse;

public interface ICourseSurveyRelationshipPersistence 
{
	public boolean createSurveyForCourse(String surveyId, ICourse course);
	public boolean surveyNotCreatedForCourse(ICourse course);
	public ISurvey loadSurveyForCourse(ICourse course);
}
