package byteLingo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import byteLingo.entity.UserEntity;

@Repository
public class UserRepository {

	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getTemplate() {
		return jdbcTemplate;	
	}
	
	@Autowired
	public void setTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	UserEntity UserEntity;
	
	RowMapper<UserEntity> mapper = new RowMapper<UserEntity>() {
		@Override
		public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException{
			UserEntity user = new UserEntity(null, null, null, null);
			user.setId(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setFirstname(rs.getString(3));
			user.setLastname(rs.getString(4));
			user.setPassword(rs.getString(5));
			
			return user;
		}
	};
	
	public List<UserEntity> readUsersFromDB(String Input) {
		List<UserEntity> user = jdbcTemplate.query("select * from user_info where username=?", mapper, new Object[]{Input});
		return user;
		
	}


	public String createUserDB(String username, String pwd, String fname, String lname, String email, RedirectAttributes model) {
		List<UserEntity> newUser = jdbcTemplate.query("select * from user_info order by ID desc limit 1", mapper);
		
		//checks if the user or email already existed
		List<UserEntity> checkuser = jdbcTemplate.query("select * from user_info where username=?", mapper, new Object[]{username});
		List<UserEntity> checkemail = jdbcTemplate.query("select * from user_info where email=?", mapper, new Object[]{email});
		
		if(checkuser.size() > 0|| checkemail.size() > 0) {
			return null;
		}
		
		Integer lastID = null;
		String sqlStmt = "INSERT INTO user_info (`id`, `username`, `first_name`, `last_name`, `password`, `email`) VALUES (?, ?, ?, ?, ?, ?)";
		
		
		//retrieve the last id number in SQL
		for(UserEntity getDB: newUser)	{
			lastID = getDB.getId();
		}
		lastID = lastID + 1;
		
		jdbcTemplate.update(sqlStmt, lastID, username, fname, lname, pwd, email);
		
		model.addFlashAttribute("fname", fname);
		model.addFlashAttribute("lname", lname);
		model.addFlashAttribute("username", username);
		model.addFlashAttribute("password", pwd);
		
		return "account: " + username + " created";
	}
	
	
	//inserts the table for personal flashcard
	public void InsertTable(String user, String table) {
		String sqlStmt = "UPDATE user_info SET personal_table= CONCAT(IF(personal_table IS NULL, '', CONCAT(personal_table, ',')),  ?) where username=?";
		jdbcTemplate.update(sqlStmt, table, user);
	}
	
	
	//returns the personal flashcards the user have
	public List<String> showFC(String user){
		String stmt = "SELECT IFNULL(GROUP_CONCAT(personal_table SEPARATOR ','), NULL) AS text FROM user_info where username=?";
		List<String> result = jdbcTemplate.queryForList(stmt, String.class, user);
		
		if (result.size() == 1 && result.get(0) == null) {
            return null;
		}
		
		//System.out.print(result);
		
		List<String> finalList = new ArrayList<>();
		for (String item : result) {
            finalList.addAll(Arrays.asList(item.split(",")));
        }

		//System.out.print(finalList);
       
		return finalList;
	}
	
	public String updateUserPassword(String loggedInUsername, String oldPassword, String newPassword, String confirmPassword) {
		List<UserEntity> loggedInUser = jdbcTemplate.query("SELECT * FROM user_info WHERE username=? AND password=?", new Object[]{Username, old_Password}, mapper); //get the users name and password
		String sql = "UPDATE user_info SET password=? WHERE username=?"; // Update the password for the logged-in user
		int rowsUpdated = jdbcTemplate.update(sql, newPassword, loggedInUsername);
		if (rowsUpdated > 0) {
			return "Password updated successfully.";
		} else {
			return "Failed to update password.";
		        }
		}	
		}
}
