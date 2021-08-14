package hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity (name = "personal_information")
public class PersonalInfo implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name="id_generator", sequenceName = "personal_id_seq", allocationSize = 1)
	@Column (name = "personal_id")
	private int id;
	
	@OneToOne
	@JoinColumn (name = "employee_id")
	private EmployeeInfo ePersonal;
	
//	private int employeeId;
	
	@Column (name = "first_name")
	private String firstName;
	
	@Column (name = "last_name")
	private String lastName;
	
	@Column
	private String address;
	
	@Column
	private String city;
	
	@Column
	private String state;
	
	@Column
	private String zipcode;
	
	@Column
	private String phonenumber;

	public PersonalInfo() {
		super();
	}
	
	public PersonalInfo(String firstName, String lastName, String address, String city, String state, String zipcode, String phonenumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phonenumber = phonenumber;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
//	public int getEmployeeId() {
//		return employeeId;
//	}
//	
//	public void setEmployeeId(int employeeId) {
//		this.employeeId = employeeId;
//	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZipCode() {
		return zipcode;
	}
	
	public void setZipCode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}
	
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public void setEmployeeInfo(EmployeeInfo ePersonal) {
		this.ePersonal = ePersonal;
	}
	
	@Override
	public String toString() {
		return "[" + id + ", " + firstName + ", " + lastName + ", " + address + ", " + city + ", " + state + ", " + zipcode + ", " + phonenumber + "]";
	}
}
