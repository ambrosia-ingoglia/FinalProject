package byteLingo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import byteLingo.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
    private UserService UserService;
	
	@RequestMapping("/redirectRegisterNewUser")
    public String redirectRegisterNewUser() {
        return "redirect:registerNewUser";
    }
	
	@GetMapping("/new_account")
	String getNewAccount() {
		return "new_account";
	}
	@GetMapping("/sign-in")
	String getSignIn() {
		return "sign-in";
	}
	
	@GetMapping("/setup")
	String getSetup() {
		return "setup";
	}
	
	@GetMapping("/user_home")
	String getUserHome(@RequestParam(name="username", required=false) String user, Model model) {
		if(user != null) {
			model.addAttribute("username", user);
		}
		return "user_home";
	}
	
	@GetMapping("/about")
	String getAbout(@RequestParam("username") String user, Model model) {
		model.addAttribute("username", user);
		
		return "about";
	}
	
	@GetMapping("/learn")
	String getLearn(@RequestParam("username") String user, Model model) {
		model.addAttribute("username", user);
		
		return "learn";
	}
	
	@GetMapping("/fc")
	String getFC(@RequestParam("username") String user, Model model) {
		model.addAttribute("username", user);
		
		UserService.printTables(user, model);
		
		return "fc";
	}
	
	@GetMapping("/ds")
	String getDS() {
		return "ds";
	}
	
    	@GetMapping("/changePassword")
    public String getChangePassword() {
        return "change_password";
    }


    @PostMapping("/changePassword")
    public String postChangePassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword,@RequestParam("confirmPassword") String confirmPassword,RedirectAttributes redirectAttributes) {
    	//has the user confirmed there new password
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "passwords do not match");
            return "redirect:/changePassword";
        }


        String response = UserService.updatePassword(oldPassword, newPassword, confirmPassword);
        redirectAttributes.addFlashAttribute("changePasswordResult", response);
        return "redirect:/user_home"; // send the user back to their homepage
    }

	
    @PostMapping("/authorization")
    public String getAuthorization(@RequestParam("username") String username, @RequestParam("password") String pwd, RedirectAttributes model){
    	
    	String existsInDatabase = UserService.checkDatabase(username, pwd, model); // Implement this method
    	
    	if(existsInDatabase == null) {
    		return "result";
    	}
    	
    	return "redirect:/user_home";
    }
	
    
    
    
    @PostMapping("/SignUp")
    public String getSignUp(@RequestParam("username") String username, 
    		@RequestParam("password") String pwd, @RequestParam("firstname") String fname,
    		@RequestParam("lastname") String lname, @RequestParam("email") String email,
    		RedirectAttributes model){
    	
    	String createDB = UserService.createUser(username, pwd, fname, lname, email, model); 
    	
    	if(createDB == null) {
    		return "result2";
    	}
    	
    	
    	return "redirect:/user_home";
    }
}
