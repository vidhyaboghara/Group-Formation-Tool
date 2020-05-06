package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Courses.ICourse;

public class Survey implements ISurvey
{
	private String id;
	private ICourse course;
	
	public Survey()
	{
		setDefaults();
	}
	
	public Survey(String id)
	{
		this.id = id;
	}
	
	private void setDefaults()
	{
		this.id = "";
		this.course = null;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getId() 
	{
		return id;
	}
	
	public void setCourse(ICourse course) 
	{
		this.course = course;
	}
	
	public ICourse getCourse() 
	{
		return course;
	}
	
	public boolean create(ICourseSurveyRelationshipPersistence courseSurveyRelationshipDB, ICourse course) 
	{	
		this.course = course;
		return courseSurveyRelationshipDB.createSurveyForCourse(this.id, course);
	}
}
