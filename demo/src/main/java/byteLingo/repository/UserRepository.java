package byteLingo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
}

