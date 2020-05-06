package CSCI5308.GroupFormationTool.Survey;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICourse;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionUserRelationship;

@Controller
public class SurveyController 
{
	private static final String ID = "id";
	private static final String COURSE_ID = "courseId";
	private static final String SURVEY_ID = "surveyId";
	private static final String INSTRUCTOR_ID = "instructorId";
	private static final String QUESTION_ID = "questionId";	
	private static final Logger logger = Logger.getLogger(SurveyController.class);
	
	@GetMapping("/survey/editSurveyHome")
	public ModelAndView editSurveyHome(RedirectAttributes redirectAttributes, Model model, @RequestParam(name = COURSE_ID) long id)
	{
		ICourseSurveyRelationshipPersistence courseSurveyRelationshipDB = SystemConfig.instance().getCourseSurveyRelationshipDB();
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		ICourse course = new Course(id, courseDB);		
		if(courseSurveyRelationshipDB.surveyNotCreatedForCourse(course)) 
		{
			final String courseId = String.valueOf(id);
			final String surveyId = "survey_" + courseId;
			ISurvey survey = new Survey(surveyId);
			survey.create(courseSurveyRelationshipDB, course);
			redirectAttributes.addAttribute("message", "Survey has been successfully created for this course");
		}			
		redirectAttributes.addAttribute("courseId", id);
		ModelAndView mav = new ModelAndView("redirect:/survey/editSurvey");
		return mav;
	}

	@GetMapping("/survey/editSurvey")
	public String editSurvey(Model model, @ModelAttribute("courseId") long courseId, @ModelAttribute("message") String message)
	{
		ICourseSurveyRelationshipPersistence courseSurveyRelationshipDB = SystemConfig.instance().getCourseSurveyRelationshipDB();
		ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
		ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
		ICourseUserRelationshipPersistence courseUserRelationshipDB = SystemConfig.instance().getCourseUserRelationshipDB();
		ICourse course = new Course(courseId, courseDB);
		ISurvey survey = courseSurveyRelationshipDB.loadSurveyForCourse(course);
		List<IUser> instructors = courseUserRelationshipDB.findAllUsersWithCourseRole(Role.INSTRUCTOR, courseId);
		model.addAttribute("instructor", instructors.get(0));
		model.addAttribute("survey", survey);
		model.addAttribute("courseId", courseId);
		model.addAttribute("message", message);	
		List<IQuestion> surveyQuestions = surveyDB.loadAllQuestionsFromSurvey(survey.getId());
		model.addAttribute("surveyQuestions", surveyQuestions);
		return "survey/editSurvey";
	}
	
	@GetMapping("/survey/addQuestion")
	public String addQuestion(Model model, @RequestParam(name = INSTRUCTOR_ID) long instructorId, 
			@RequestParam(name = SURVEY_ID) String surveyId, @RequestParam(name = COURSE_ID) long courseId)
	{
		IQuestionUserRelationship questionUserRelationship = SystemConfig.instance().getQuestionUserRelationshipDB();
		IUserPersistence userDB = SystemConfig.instance().getUserDB();
		IUser instructor = new User(instructorId,userDB); 
		List<IQuestion> questions = questionUserRelationship.loadAllQuestionsByInstructor(instructor, ID);
		model.addAttribute("questions", questions);
		model.addAttribute("surveyId", surveyId);
		model.addAttribute("courseId", courseId);
		return "survey/addQuestion";
	}
	
	@PostMapping("/survey/addQuestionSubmit")
	public ModelAndView addQuestionSubmit(RedirectAttributes redirectAttributes, Model model, @RequestParam(name = QUESTION_ID) long questionId,
			@RequestParam(name = SURVEY_ID) String surveyId, @RequestParam(name = COURSE_ID) long courseId)
	{	
		ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
		ModelAndView mav = new ModelAndView("redirect:/survey/editSurvey");
		if(surveyDB.surveyHasQuestion(surveyId, questionId))
		{
			logger.info("Question with ID: " + questionId + " already exists in the survey with ID: " + surveyId
					+ " for the course with ID: " + courseId);
			mav.addObject("message", "This Question already exist in the course survey");
			redirectAttributes.addAttribute("message", "This Question already exist in the course survey");
		}
		else
		{
			surveyDB.addQuestionToSurvey(surveyId, questionId);
			logger.info("Successfully added Question with ID: " + questionId + " in the survey with ID: " + surveyId
					+ " for the course with ID: " + courseId);
			mav.addObject("message", "Question was successfully added to the course survey");
			redirectAttributes.addAttribute("message", "Question was successfully added to the course survey");
		}
		redirectAttributes.addAttribute("courseId", courseId);
		return mav;
	}
	
	@PostMapping("/survey/deleteQuestion")
	public ModelAndView deleteQuestion(RedirectAttributes redirectAttributes, Model model, @RequestParam(name = QUESTION_ID) long questionId, 
			@RequestParam(name = SURVEY_ID) String surveyId, @RequestParam(name = COURSE_ID) long courseId)
	{
		ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
		surveyDB.deleteQuestionFromSurvey(surveyId, questionId);
		redirectAttributes.addAttribute("courseId", courseId);		
		ModelAndView mav = new ModelAndView("redirect:/survey/editSurvey");
		logger.info("Question with ID: " + questionId + " was successfully deleted from the survey with ID: " + surveyId
				+ " for the course with ID: " + courseId);
		mav.addObject("message", "Question was successfully deleted from the course survey");
		redirectAttributes.addAttribute("message", "Question was successfully deleted from the course survey");
		return mav;
	}
}
