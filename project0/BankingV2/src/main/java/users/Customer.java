package users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
	protected String name;
	protected String username;
	protected String email;
	protected String password;
	protected String address;
	protected String city;
	protected String state;
	protected String zipcode;
	protected String phoneNumber;
	protected String dateJoined;
	
	private int accountId;
	private int bankId;
	private float checking;
	private float saving;
	 
	
	/*
	 * Customer constructor takes in a ResultSet that will initialize all Customer 
	 * variables
	 */
	public Customer(ResultSet rs) {
		try {
			rs.next();
			this.name = rs.getString("name");
			this.username = rs.getString("username");
			this.email = rs.getString("email");
			this.password = rs.getString("password");
			this.address = rs.getString("address");
			this.city = rs.getString("city");
			this.state = rs.getString("state");
			this.zipcode = rs.getString("zip_code");
			this.phoneNumber = rs.getString("phone_number");
			this.dateJoined = rs.getString("date_joined");
			this.accountId = rs.getInt("account_id");
			this.bankId = rs.getInt("bank_id");
			this.checking = rs.getFloat("checking");
			this.saving = rs.getFloat("saving");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Getters and Setters for all Customer variables
	 */
	
	public String getName() {
		return name;
	}
	
	public int getAccountId() {
		return accountId;
	}
	
	public String getJoinDate() {
		return dateJoined;
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getFullAddress() {
		String fullAddress = address + " " + city + ", " + state + " " + zipcode;
		return fullAddress;
	}
	
	public void setAddress(String address, String city, String state, String zipcode) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	
	public int getBankId() {
		return bankId;
	}
	
	public float getChecking() {
		return checking;
	}
	
	public void setChecking(float checking) {
		this.checking = checking;
	}
	
	public float getSaving() {
		return saving;
	}
	
	public void setSaving(float saving) {
		this.saving = saving;
	}
}
