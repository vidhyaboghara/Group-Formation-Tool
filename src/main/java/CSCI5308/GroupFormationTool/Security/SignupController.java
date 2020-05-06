package CSCI5308.GroupFormationTool.Security;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import CSCI5308.GroupFormationTool.SystemConfig;
import CSCI5308.GroupFormationTool.AccessControl.*;

@Controller
public class SignupController
{
	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";
	private static final Logger logger = Logger.getLogger(SignupController.class);
	
	@GetMapping("/signup")
	public String displaySignup(Model model)
	{
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST) 
    public ModelAndView processSignup(
   	@RequestParam(name = USERNAME) String bannerId,
   	@RequestParam(name = PASSWORD) String password,
   	@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
   	@RequestParam(name = FIRST_NAME) String firstName,
   	@RequestParam(name = LAST_NAME) String lastName,
   	@RequestParam(name = EMAIL) String email)
	{
		boolean success = false;
		if (User.isBannerIdValid(bannerId) &&
			 User.isEmailValid(email) &&
			 User.isFirstNameValid(firstName) &&
			 User.isLastNameValid(lastName) &&
			 password.equals(passwordConfirm))
		{
			User u = new User();
			u.setBannerId(bannerId);
			u.setPassword(password);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setEmail(email);
			IUserPersistence userDB = SystemConfig.instance().getUserDB();
			IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
			success = u.createUser(userDB, passwordEncryption, null);
		}
		ModelAndView mav;
		if (success)
		{
			logger.info("User created successfully with Banner ID: " + bannerId + " and Email: " + email);
			mav = new ModelAndView("login");
		}
		else
		{
			mav = new ModelAndView("signup");
			mav.addObject("errorMessage", "Invalid data, please check your values.");
			logger.error("Error in signing up because of Invalid user input for user with banner ID: " + bannerId);	
		}
		return mav;
	}
}