package byteLingo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import byteLingo.entity.FCEntity;
import byteLingo.entity.UserEntity;
import byteLingo.repository.FCRepository;
import byteLingo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository UserRepository;

	@Autowired
	private FCRepository FCRepository;
	
	public List<UserEntity> checkInput(String Input) {
		List<UserEntity> user = UserRepository.readUsersFromDB(Input);
		return user;
		
		
	}
	
	
	//checks if the user exists by sending it to UserRepository
	public String checkDatabase(String username, String pwd, Model model) {
    	
    	String user_name;
    	String fname;
    	String lname;
    	String pwd2;
    	
    	
		List<UserEntity> user = checkInput(username);
		if(user != null) {
			for(UserEntity user_info: user)	{
				fname = user_info.getFirstname();
				lname = user_info.getLastname();
				user_name = user_info.getUsername();
				pwd2 = user_info.getPassword();
			
				/*model.addAttribute("fname", fname);
				model.addAttribute("lname", lname);
				model.addAttribute("username", user_name);
				model.addAttribute("password", pwd2);*/
				
				if(user_name.equals(username)) {
					if(pwd2.equals(pwd)) {
						return "Welcome, " + fname + " " + lname;
					} else {
						return null;
					}
				} else {
					return null;
				}
			}
			
		} 
			
		return null;
		
    }
	
	
	//creates a account
	public String createUser(String username, String pwd, String fname,
			String lname, String email) {
		String acc = UserRepository.createUserDB(username, pwd, fname, lname, email);
		
		return acc;
	}
	
	
	public List<FCEntity> getDSFlaschcards(){
		return FCRepository.getDSdatabase();
	}
	
}
