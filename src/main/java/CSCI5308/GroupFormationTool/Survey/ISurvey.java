package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Courses.ICourse;

public interface ISurvey 
{
	public void setId(String id);
	public String getId();
	public void setCourse(ICourse course);
	public ICourse getCourse();
	public boolean create(ICourseSurveyRelationshipPersistence courseSurveyRelationshipDB, ICourse course);
}
