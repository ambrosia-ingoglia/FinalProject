package byteLingo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import byteLingo.entity.FCEntity;
import byteLingo.entity.UserEntity;

@Repository
public class FCRepository {

	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getTemplate() {
		return jdbcTemplate;	
	}
	
	@Autowired
	public void setTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	FCEntity FCEntity;
	
	public List<FCEntity> getDSdatabase() {
        String sql = "SELECT * FROM ds_questions";
        
        List<FCEntity> rows = jdbcTemplate.query(sql, (rs, rowNum) -> {
            FCEntity entity = new FCEntity(null, null);
            entity.setQuestion(rs.getString("question"));
            entity.setAnswer(rs.getString("answer"));

            return entity;
        });
        
        return rows;
    }
}
