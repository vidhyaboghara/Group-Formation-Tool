package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.IUser;

@Controller
public class QuestionManagerController 
{
	private static final String ID = "id";
	private static final String SORT_BY = "selectedSortOption";
	private static final String TITLE = "title";
	private static final String TEXT = "question";
	private static final String TYPE = "questionType";
	private static final String DISPLAYED_AS = "optionText";
	private static final String STORED_AS = "optionCode";
	private static final String QUESTION_SORT_BY ="sortBy";
	
	@GetMapping("/questionManager/questionManager")
	public String questionManager(Model model, @ModelAttribute(QUESTION_SORT_BY) String sortBy)
	{
		IUser instructor = CurrentUser.instance().getCurrentAuthenticatedUser();
		IQuestionUserRelationship questionUserRelationship = SystemConfig.instance().getQuestionUserRelationshipDB();
		
		if(sortBy.isEmpty())
		{
			sortBy = ID;
		}
		List<IQuestion> questions = questionUserRelationship.loadAllQuestionsByInstructor(instructor, sortBy);
		model.addAttribute("questions", questions);
		return "questionManager/questionManager";
	}
	
	@PostMapping("/questionManager/deleteQuestion")
	public ModelAndView deleteQuestion(Model model, @RequestParam(name = ID) long questionId)
	{
		IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
		IQuestion question = new Question(questionId, questionDB);
		question.delete(questionDB);
		ModelAndView mav = new ModelAndView("redirect:/questionManager/questionManager");
		return mav;
	}
	
	@RequestMapping("/questionManager/sortQuestions")
	public ModelAndView sortQuestions(RedirectAttributes redirectAttributes, @RequestParam(name = SORT_BY) String sortBy)
	{
		redirectAttributes.addAttribute("sortBy", sortBy);
		ModelAndView mav = new ModelAndView("redirect:/questionManager/questionManager");
		return mav;
	}
	
	@RequestMapping("/questionManager/questioncreation")
	public String questioncreation(Model model) 
	{
		return "questionManager/questioncreation";
	}
	
	@RequestMapping("/questionManager/createquestion")
	public ModelAndView question(Model model, @RequestParam(name = TITLE) String title, 
			@RequestParam(name = TEXT) String text, @RequestParam(name = TYPE) String type,
			@RequestParam(name= DISPLAYED_AS) ArrayList<String> displayedAs,
			@RequestParam(name=STORED_AS) ArrayList<Integer> storedAs) 
	{	
		IQuestionPersistence questionDB = SystemConfig.instance().getQuestionDB();
		IUser user = CurrentUser.instance().getCurrentAuthenticatedUser();
		IQuestion question = new Question();
		ArrayList<IMultipleChoice> multipleChoices = new ArrayList<IMultipleChoice>() ;
		
		for(int i =0; i<displayedAs.size(); i++) 
		{
			if(displayedAs.get(i)!=null && displayedAs.get(i)!="")
			{
				IMultipleChoice choice = new MultipleChoice();
				choice.setChoiceText(displayedAs.get(i));
				choice.setStoredAs(storedAs.get(i));
				multipleChoices.add(choice);
			}
		}
		question.setTitle(title);
		question.setText(text);
		question.setType(type);
		question.setInstructor(user);
		question.setMultipleChoice(multipleChoices);
		long millis=System.currentTimeMillis();
		Date date = new Date(millis);
		question.setCreatedOn(date);
		question.create(questionDB);
		ModelAndView mav = new ModelAndView("redirect:/questionManager/questionManager");
		return mav;
	}
}