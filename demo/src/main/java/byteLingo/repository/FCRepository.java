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
	
	public List<FCEntity> getOOPdatabase() {
        String sql = "SELECT * FROM oop_questions";
        
        List<FCEntity> rows = jdbcTemplate.query(sql, (rs, rowNum) -> {
            FCEntity entity = new FCEntity(null, null);
            entity.setQuestion(rs.getString("question"));
            entity.setAnswer(rs.getString("answer"));

            return entity;
        });
        
        return rows;
    }
	
	public List<FCEntity> getMyTable(String table) {
        String sql = "SELECT * FROM " + table + "";
        
        List<FCEntity> rows = jdbcTemplate.query(sql, (rs, rowNum) -> {
            FCEntity entity = new FCEntity(null, null);
            entity.setQuestion(rs.getString("question"));
            entity.setAnswer(rs.getString("answer"));

            return entity;
        });
        
        return rows;
    }
	
	
	//Check if the table already existed
	public Integer checkDB(String tablename) {
		String stmt = "SELECT count(*) FROM information_schema.tables WHERE table_name = ?";
		Integer count = jdbcTemplate.queryForObject(stmt, Integer.class, tablename);
		if(count == 0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	
	//to create the flashcards 
	public void createDB(String tablename) {
		String stmt = "CREATE TABLE " + tablename + " ("
				  + "`id` INT NOT NULL, "
				  + "`question` LONGTEXT NOT NULL, "
				  + "`answer` LONGTEXT NOT NULL, "
				  + "PRIMARY KEY (`id`));";
		//System.out.print(stmt);
		jdbcTemplate.execute(stmt);
	}
	
	public void newFCdata(String tablename, Integer id, String question, String answer) {
		id = id + 1;
		
		String stmt = "INSERT INTO " + tablename + " (id, question, answer) VALUES (?, ?, ?)";
		//System.out.print(stmt);
		jdbcTemplate.update(stmt, id, question, answer);
		
	}
}
