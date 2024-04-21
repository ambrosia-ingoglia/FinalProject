package byteLingo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.lang.Thread;


@Table(name = "se_project")
@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer loginStreak;
	private boolean loginToday;
	
	private String username;
	private String fname;
	private String lname;
	private String password;
	
	public UserEntity(String username, String fname, String lname, String password, Integer loginStreak, boolean loginToday) {
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.loginStreak = loginStreak;
		this.loginToday = loginToday;

		//Unsure of where to put this so that it is always running, commented out for now
		/*do {
			Thread.sleep(1000 * 60 * 60 * 24);
			if (this.loginToday.equals(true)) {
				this.loginToday = false;
			}else {
				this.setLoginStreak(0);
			}
		} while (true);*/
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

	public Integer getLoginStreak() {
		return loginStreak;
	}

	public boolean getloginToday() {
		return loginToday;
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

	public void setLoginStreak(Integer loginStreak) {
		this.loginStreak = loginStreak;
	}

	public void setLoginToday(boolean loginToday) {
		this.loginToday = loginToday;
	}
	
	public String printUserInfo() {
	    return "UserEntity{" +
	            "id=" + id +
	            ", username='" + username + '\'' +
	            ", firstname='" + fname + '\'' +
	            ", lastname='" + lname + '\'' +
	            ", password='" + password + '\'' +
				", login streak='" + loginStreak + '\'' +
				", login today='" + loginToday + '\'' +
	            '}';
	}
	
}