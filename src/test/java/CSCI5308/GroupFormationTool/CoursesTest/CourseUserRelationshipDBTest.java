package CSCI5308.GroupFormationTool.CoursesTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.Role;

@SpringBootTest
public class CourseUserRelationshipDBTest 
{	
	private CourseUserRelationshipDBMock courseUserRelationshipDBMock;
	
	@BeforeEach
	public void setUpBeforeClass()
	{
		courseUserRelationshipDBMock = new CourseUserRelationshipDBMock();
	}
	
	@Test
	public void enrollUserTest() 
	{
		ICourse c = new Course();
		c.setId(1);
		IUser u = new User();
		u.setId(10);
		Role r = Role.valueOf("STUDENT");	
		assertFalse(courseUserRelationshipDBMock.enrollUser(c, u, r));
		
		c.setId(1);
		u.setId(55);
		r = Role.valueOf("TA");
		assertTrue(courseUserRelationshipDBMock.enrollUser(c, u, r));		
	}
	
	@Test
	public void findAllUsersWithCourseRoleTest()
	{
		List<IUser> users;
		Role r = Role.valueOf("STUDENT");
		users = courseUserRelationshipDBMock.findAllUsersWithCourseRole(r, 1);
		assertEquals(2, users.size());
		
		r = Role.valueOf("TA");
		users = courseUserRelationshipDBMock.findAllUsersWithCourseRole(r, 1);
		assertEquals(2, users.size());
		
		r = Role.valueOf("INSTRUCTOR");
		users = courseUserRelationshipDBMock.findAllUsersWithCourseRole(r, 1);
		assertEquals(1, users.size());
	}
	
	@Test
	public void findAllUsersWithoutCourseRoleTest()
	{
		List<IUser> users;
		Role r = Role.valueOf("STUDENT");
		users = courseUserRelationshipDBMock.findAllUsersWithoutCourseRole(r, 1);
		assertEquals(3, users.size());
		
		r = Role.valueOf("TA");
		users = courseUserRelationshipDBMock.findAllUsersWithoutCourseRole(r, 1);
		assertEquals(3, users.size());
		
		r = Role.valueOf("INSTRUCTOR");
		users = courseUserRelationshipDBMock.findAllUsersWithoutCourseRole(r, 1);
		assertEquals(4, users.size());
	}
	
	@Test
	public void loadUserRolesForCourseTest()
	{
		List<Role> roles;
		ICourse c = new Course();
		c.setId(1);
		IUser u = new User();
		u.setId(10);
		roles = courseUserRelationshipDBMock.loadUserRolesForCourse(c, u);
	
		assertEquals(2, roles.size());
		assertTrue(roles.contains(Role.TA));
		assertTrue(roles.contains(Role.STUDENT));
	}
}
