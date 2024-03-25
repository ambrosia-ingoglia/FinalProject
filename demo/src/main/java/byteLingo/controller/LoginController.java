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
	
	@GetMapping("/user_home")
	String getUserHome() {
		return "user_home";
	}
	
	@GetMapping("/setup")
	String getSetup() {
		return "setup";
	}
	
	@GetMapping("/fc")
	String getFC() {
		return "fc";
	}
	
	@GetMapping("/ds")
	String getDS() {
		return "ds";
	}
	
	
    @PostMapping("/authorization")
    public String getAuthorization(@RequestParam("username") String username, @RequestParam("password") String pwd, RedirectAttributes model){
    	
    	String existsInDatabase = UserService.checkDatabase(username, pwd, model); // Implement this method
    	
    	if(existsInDatabase == null) {
    		return "result";
    	}
    	
    	model.addFlashAttribute("reply", existsInDatabase);
    	return "redirect:/user_home";
    }
	
    
    
    
    @PostMapping("/SignUp")
    public String getSignUp(@RequestParam("username") String username, 
    		@RequestParam("password") String pwd, @RequestParam("firstname") String fname,
    		@RequestParam("lastname") String lname, @RequestParam("email") String email,
    		Model model){
    	
    	String createDB = UserService.createUser(username, pwd, fname, lname, email); 
    	
    	model.addAttribute("na", createDB);
    	
    	if(createDB == null) {
    		return "result2";
    	}
    	
    	
    	return "redirect:/setup";
    }
}
