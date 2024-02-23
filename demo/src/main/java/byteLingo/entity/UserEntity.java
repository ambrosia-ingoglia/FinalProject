package byteLingo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name = "se_project")
@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String username;
	private String fname;
	private String lname;
	private String password;
	
	public UserEntity(String username, String fname, String lname, String password) {
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getFirstname() {
		return fname;
	}
	
	public String getLastname() {
		return lname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setFirstname(String fname) {
		this.fname = fname;
	}
	
	public void setLastname(String lname) {
		this.lname = lname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String printUserInfo() {
	    return "UserEntity{" +
	            "id=" + id +
	            ", username='" + username + '\'' +
	            ", firstname='" + fname + '\'' +
	            ", lastname='" + lname + '\'' +
	            ", password='" + password + '\'' +
	            '}';
	}
	
}