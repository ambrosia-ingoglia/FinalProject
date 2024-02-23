package byteLingo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import byteLingo.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
    private UserService UserService;

	@GetMapping("/login")
	String login() {
		return "login";
	}
	
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
	
	@GetMapping("/user-home")
	String getUserHome() {
		return "user-home";
	}
	
	@GetMapping("/setup")
	String getSetup() {
		return "setup";
	}
	
    @PostMapping("/authorization")
    public String getAuthorization(@RequestParam("username") String username, @RequestParam("password") String pwd, Model model){
    	
    	String existsInDatabase = UserService.checkDatabase(username, pwd, model); // Implement this method
    	model.addAttribute("reply", existsInDatabase);
    	if(existsInDatabase != null) {
    		return "user_home";
    	}
    	
    	return "sign-in";
    }
	
    

}
