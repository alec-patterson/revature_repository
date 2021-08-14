package hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity (name = "login_information")
public class LoginInfo implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name="id_generator", sequenceName = "login_id_seq", allocationSize = 1)
	private int id;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@OneToOne(mappedBy = "login")
	private EmployeeInfo employee;
	
	public LoginInfo() {
		super();
	}
	
	public LoginInfo(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "[" + this.id + ", " + this.email + ", " + this.password + "]";
	}
	
	public void setEmployeeInfo(EmployeeInfo employee) {
		this.employee = employee;
	}
}
