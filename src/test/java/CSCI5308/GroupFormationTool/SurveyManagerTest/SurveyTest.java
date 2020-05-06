package CSCI5308.GroupFormationTool.SurveyManagerTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Survey.ISurvey;
import CSCI5308.GroupFormationTool.Survey.Survey;

public class SurveyTest 
{
	private ISurvey s;
	
	@BeforeEach
	void setUpBeforeClass()
	{
		s = new Survey();
	}
	
	@Test
	public void constructorTest() 
	{
		assertTrue(s.getId().isEmpty());
		assertNull(s.getCourse());
		ISurvey survey = new Survey("Survey_1");
		assertEquals(survey.getId(),"Survey_1");
		assertNull(survey.getCourse());
	}
	
	@Test
	public void setIdTest() 
	{
		s.setId("survey_1");
		assertEquals(s.getId(), "survey_1");
	}
	
	@Test
	public void getIdTest() 
	{
		s.setId("survey_1");
		assertEquals(s.getId(), "survey_1");
	}
	
	@Test
	public void setCourseTest() 
	{
		ICourse c = new Course();
		c.setId(1);
		c.setTitle("TestCourse");
		s.setCourse(c);
		assertEquals(c, s.getCourse());
	}
	
	@Test
	public void getCourseTest() 
	{
		ICourse c = new Course();
		c.setId(10);
		c.setTitle("TestCourse-1");
		s.setCourse(c);
		assertEquals(c, s.getCourse());
	}
	
}
