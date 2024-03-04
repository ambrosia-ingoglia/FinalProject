package byteLingo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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


	public String createUserDB(String username, String pwd, String fname, String lname, String email) {
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
		
		return "account: " + username + " created";
	}
}
