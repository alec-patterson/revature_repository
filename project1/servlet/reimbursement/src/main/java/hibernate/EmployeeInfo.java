package hibernate;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity (name = "employee_information")
public class EmployeeInfo implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name="id_generator", sequenceName = "employee_id_seq", allocationSize = 1)
	@Column (name = "employee_id")
	private int employeeId;

	@Column
	private String role;
	
	@OneToOne
	@JoinColumn(name = "login_id")
	private LoginInfo login;
	
	@OneToOne (mappedBy = "ePersonal")
	private PersonalInfo person;
	
	@OneToMany (mappedBy = "eRequest", cascade=CascadeType.ALL)
    private Set<ReimburseRequest> request;
	
	public EmployeeInfo() {
		super();
	}
	
	public EmployeeInfo(String role) {
		super();
		this.role = role;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setLoginInfo(LoginInfo login) {
		this.login = login;
	}
	
	public void setPersonalInfo(PersonalInfo person) {
		this.person = person;
	}
	
	public void addReimburseRequest(ReimburseRequest r) {
		request.add(r);
	}
	
	public Set<ReimburseRequest> getRequest() {
		return request;
	}
	
	@Override
	public String toString() {
		return "[" + employeeId + ", " + role + "]";
	}
}
