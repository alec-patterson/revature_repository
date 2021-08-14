package user;

import org.hibernate.Session;

import common.util.DBUtil;
import hibernate.EmployeeInfo;
import hibernate.LoginInfo;
import hibernate.PersonalInfo;

public class Account {

	private Session session;
	
	private LoginInfo login;
	private EmployeeInfo employee;
	private PersonalInfo person;
	
	public Account(LoginInfo l, EmployeeInfo e, PersonalInfo p) {
		this.session = DBUtil.getInstance().getSession();
		this.login = l;
		this.employee = e;
		this.person = p;
		l.setEmployeeInfo(e);
		e.setLoginInfo(l);
		e.setPersonalInfo(p);
		p.setEmployeeInfo(e);
	}
	
	public Account(String email, String password, String role, String firstName, String lastName, String address, String city, String state, String zipcode, String phonenumber) {
		this.session = DBUtil.getInstance().getSession();
		login = new LoginInfo(email, password);
		employee = new EmployeeInfo(role);
		person = new PersonalInfo(firstName, lastName, address, city, state, zipcode, phonenumber);
		login.setEmployeeInfo(employee);
		employee.setLoginInfo(login);
		employee.setPersonalInfo(person);
		person.setEmployeeInfo(employee);
	}
	
	public LoginInfo getLoginInfo() {
		return login;
	}
	
	public void setLoginInfo(LoginInfo login) {
		this.login = login;
	}
	
	public EmployeeInfo getEmployeeInfo() {
		return employee;
	}
	
	public void setEmployeeInfo(EmployeeInfo employee) {
		this.employee = employee;
	}
	
	public PersonalInfo getPersonalInfo() {
		return person;
	}
	
	public void setPersonalInfo(PersonalInfo person) {
		this.person = person;
	}
	
	public Session getSession() {
		return session;
	}

	@Override
	public String toString() {
		return login.toString() + employee.toString() + person.toString();
	}
}
