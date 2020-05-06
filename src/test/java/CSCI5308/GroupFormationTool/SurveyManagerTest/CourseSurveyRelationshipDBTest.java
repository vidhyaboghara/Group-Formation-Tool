package CSCI5308.GroupFormationTool.SurveyManagerTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Survey.ICourseSurveyRelationshipPersistence;
import CSCI5308.GroupFormationTool.Survey.ISurvey;
import CSCI5308.GroupFormationTool.Survey.Survey;

public class CourseSurveyRelationshipDBTest 
{
	private ICourseSurveyRelationshipPersistence courseSurveyRelationshipDBMock;
	private ICourse course;
	
	@BeforeEach
	void setUpBeforeClass() 
	{
		courseSurveyRelationshipDBMock = new CourseSurveyRelationshipDBMock();
		course = new Course();
	}
		
	@Test
	public void createSurveyForCourseTest()
	{	
		course.setId(5);
		course.setTitle("course-5");
		assertTrue(courseSurveyRelationshipDBMock.surveyNotCreatedForCourse(course));
		
		ISurvey survey1 = new Survey("survey-5");
		survey1.create(courseSurveyRelationshipDBMock, course);
		assertFalse(courseSurveyRelationshipDBMock.surveyNotCreatedForCourse(course));
		
		ISurvey survey2 = courseSurveyRelationshipDBMock.loadSurveyForCourse(course);
		assertEquals(survey1.getId(), survey2.getId());
		assertEquals(survey1.getCourse(), survey2.getCourse());
	}

	@Test
	public void surveyNotCreatedForCourseTest()
	{	
		course.setId(1);
		course.setTitle("course-1");
		assertFalse(courseSurveyRelationshipDBMock.surveyNotCreatedForCourse(course));
		
		course = new Course();
		course.setId(5);
		course.setTitle("course-5");
		assertTrue(courseSurveyRelationshipDBMock.surveyNotCreatedForCourse(course));
	}

	@Test
	public void loadSurveyForCourseTest() 
	{
		course.setId(5);
		course.setTitle("course-5");
		assertNull(courseSurveyRelationshipDBMock.loadSurveyForCourse(course));
		
		course = new Course();
		course.setId(1);
		ISurvey survey = courseSurveyRelationshipDBMock.loadSurveyForCourse(course);
		assertEquals(survey.getId(),"survey_1");
	}
}
