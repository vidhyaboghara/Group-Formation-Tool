package CSCI5308.GroupFormationTool.CoursesTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;

public class CourseUserRelationshipDBMock implements ICourseUserRelationshipPersistence 
{
	private Map<String, Map<Role, List<String>>> courseUserMapper;
	
	public CourseUserRelationshipDBMock()
	{
		courseUserMapper = new HashMap<String, Map<Role, List<String>> >();
		populateCourseUserRelationships();
	}
	
	private void populateCourseUserRelationships()
	{
		Map<Role, List<String>> roleUserList;
		Role role;
		List<String> userList;
		
		roleUserList = new HashMap<Role, List<String>>();
		
		role = Role.valueOf("STUDENT");
		userList = new ArrayList<String>();
		userList.add("10");
		userList.add("20");
		roleUserList.put(role, userList);
		
		role = Role.valueOf("INSTRUCTOR");
		userList = new ArrayList<String>();
		userList.add("1");
		roleUserList.put(role, userList);
		
		role = Role.valueOf("TA");
		userList = new ArrayList<String>();
		userList.add("10");
		userList.add("22");
		roleUserList.put(role, userList);
		
		courseUserMapper.put("1", roleUserList);
	}
	
	public List<IUser> findAllUsersWithoutCourseRole(Role role, long courseId) 
	{
		List<IUser> users = new ArrayList<IUser>();
		Map<Role, List<String>> roleUserList = courseUserMapper.get(String.valueOf(courseId));
		for (Map.Entry<Role, List<String>> entry : roleUserList.entrySet())  
		{
			Role r = entry.getKey();
			if(r.toString()!=role.toString())
			{
				List<String> userList = roleUserList.get(r);
				for(String userId: userList)
				{
					IUser u = new User();
					u.setId(Long.valueOf(userId));
					users.add(u);
				}
			}
		}
		return users;
	}

	public List<IUser> findAllUsersWithCourseRole(Role role, long courseId) 
	{
		List<IUser> users = new ArrayList<IUser>();
		Map<Role, List<String>> roleUserList = courseUserMapper.get(String.valueOf(courseId));
		List<String> userList = roleUserList.get(role);
		for(String userId: userList)
		{
			IUser u = new User();
			u.setId(Long.valueOf(userId));
			users.add(u);
		}
		return users;
	}

	public boolean enrollUser(ICourse course, IUser user, Role role) 
	{
		String courseId = String.valueOf(course.getId());
		Map<Role, List<String>> roleUserList = courseUserMapper.get(courseId);
		List<String> userList = roleUserList.get(role);
		String userId = String.valueOf(user.getId());
		if(userList.contains(userId))
		{
			return false;
		}
		else
		{
			userList.add(userId);
			roleUserList.put(role, userList);
			courseUserMapper.put(courseId, roleUserList);
			return true;
		}
	}

	public List<Role> loadUserRolesForCourse(ICourse course, IUser user) 
	{
		List<Role> roles = new ArrayList<Role>();
		List<String> userList;
		String userId = String.valueOf(user.getId());
		String courseId = String.valueOf(course.getId());
		Map<Role, List<String>> roleUserList = courseUserMapper.get(courseId);
		for (Map.Entry<Role, List<String>> entry : roleUserList.entrySet())  
		{
			userList = entry.getValue();
			if(userList.contains(userId))
			{
				roles.add(entry.getKey());
			}
		}
        return roles;
	}
}