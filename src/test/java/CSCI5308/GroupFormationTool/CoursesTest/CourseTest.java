package CSCI5308.GroupFormationTool.CoursesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourse;

@SpringBootTest
public class CourseTest 
{
	ICourse c;
	
	@BeforeEach
	public void setUpBefore()
	{
		c = new Course();
	}
	
	@Test
	public void ConstructorTest() 
	{
		assertTrue(c.getTitle().isEmpty());
		assertEquals(-1,c.getId());
	}
	
	@Test
	public void setIdTest() 
	{
		c.setId(0);
		assertTrue(0 == c.getId());
	}
	
	@Test
	public void getIdTest() 
	{
		c.setId(0);
		assertTrue(0 == c.getId());
	}
	
	@Test
	public void setTitleTest() 
	{
		c.setTitle("SDC");
		assertEquals("SDC",c.getTitle());
	}
	
	@Test
	public void getTitleTest() 
	{
		c.setTitle("SDC");
		assertEquals("SDC",c.getTitle());
	}
}