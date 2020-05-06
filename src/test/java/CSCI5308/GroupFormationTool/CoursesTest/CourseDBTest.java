package CSCI5308.GroupFormationTool.CoursesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;

@SpringBootTest
public class CourseDBTest 
{
	private ICoursePersistence courseDBMock;
	
	@BeforeEach
	public void setUpBefore()
	{
		courseDBMock = new CourseDBMock();
	}
	
	@Test
	public void loadAllCoursesTest()
	{
		List<ICourse> courses = courseDBMock.loadAllCourses();
		assertEquals(courses.size(),3);
	}
	
	@Test
	public void loadCourseByIdTest()
	{
		ICourse course = new Course(1, courseDBMock);
		assertTrue(course.create(courseDBMock));
	}
	
	@Test
	public void deleteCourseTest()
	{
		ICourse course = new Course(2, courseDBMock);
		assertFalse(course.delete(courseDBMock));
	}
}
