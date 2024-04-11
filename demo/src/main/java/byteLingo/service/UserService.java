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


	// Method to get the logged-in username
	private String getLoggedInUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
	        	return authentication.getName();
	}
	return null;
	    }
	
	
	}

	// method for updating the password
	public String updatePassword(String oldPassword, String newPassword, String confirmPassword) {
	        String loggedInUsername = getLoggedInUsername();
		String pwd2;
		String user_name;
	        if (loggedInUsername != null) {// if the user is logged in
	            List<UserEntity> user = checkInput(loggedInUsername);//gets all the users with that username
	            if (user != null) {// if the user exists in the database
				for(UserEntity user_info: user)	{
					user_name = user_info.getUsername();//get the username in the database
					pwd2 = user_info.getPassword(); //get the password in the database
						
					if(user_name.equals(loggedInUsername)) {
						if(pwd2.equals(oldPassword)) {//make sure you have the correct user
							if(oldPassword.equals(newPassword)){//validates that the new password is differnt from the old password
								return "you need to choose a new password";
							}
							else{
							UserRepository.updateUserPassword(loggedInUsername, oldPassword, newPassword, confirmPassword) //update the password
							}
						} 
					}
			}
	                return "Password updated successfully for user: " + loggedInUsername;
	            } else {
	                return "Failed to update password. User not found.";
	            }
	        } else {
	            return "No user logged in.";
	        }
	    }

	
	//creates a account
	public String createUser(String username, String pwd, String fname,
			String lname, String email) {
		String acc = UserRepository.createUserDB(username, pwd, fname, lname, email);
		
		return acc;
	}
	
}
