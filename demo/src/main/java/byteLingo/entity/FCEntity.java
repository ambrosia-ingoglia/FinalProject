package byteLingo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "ds_questions")
@Entity
public class FCEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String question;
	private String answer;
	
	public FCEntity(String question,String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
