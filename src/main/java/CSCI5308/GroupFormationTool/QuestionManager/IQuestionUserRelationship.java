package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

public interface IQuestionUserRelationship 
{
	public List<IQuestion> loadAllQuestionsByInstructor(IUser Instructor, String sortBY);
}
