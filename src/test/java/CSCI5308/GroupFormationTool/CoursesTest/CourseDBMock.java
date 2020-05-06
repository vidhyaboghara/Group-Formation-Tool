package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.*;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

public class CourseDBMock implements ICoursePersistence
{
	private Map<String, ICourse> courseList;
	
	public CourseDBMock() 
	{
		courseList = new HashMap<String, ICourse>();
		populateCourses();
	}
	
	private void populateCourses()
	{
		ICourse c = new Course();
		c.setId(1);
		c.setTitle("SDC");
		courseList.put("1",c);
		
		c = new Course();
		c.setId(2);
		c.setTitle("Cloud");
		courseList.put("2", c);
		
		c = new Course();
		c.setId(3);
		c.setTitle("Adv SDC");
		courseList.put("3", c);
	}
	
	@Override
	public List<ICourse> loadAllCourses() 
	{
		List<ICourse> courses = new ArrayList<ICourse>();
		ICourse c;
		for (Map.Entry<String, ICourse> entry : courseList.entrySet())
		{
			c = entry.getValue();
			courses.add(c);
		}
		return courses;						
	}
	
	@Override
	public void loadCourseById(long id, ICourse course) 
	{
		course = courseList.get(String.valueOf(id));
	}

	public boolean createCourse(ICourse course) 
	{
		courseList.put(String.valueOf(course.getId()), course);
		return true;
	}
	
	@Override
	public boolean deleteCourse(long id) 
	{		
		String courseId = String.valueOf(id);
		if(courseList.containsKey(courseId))
		{
			courseList.remove(courseId);
			return true;
		}
		else
		{
			return false;
		}
	}
}
