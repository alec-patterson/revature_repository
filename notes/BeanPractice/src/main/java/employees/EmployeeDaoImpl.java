package employees;

public class EmployeeDaoImpl implements EmployeeDao{

	private String name;
	private String username;
	private String password;
	
	
	public EmployeeDaoImpl(String message) {
		System.out.println(message);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean login(String username, String password) {
		printAllInfo();
		return true;
	}
	
	public void printAllInfo() {
		System.out.println(name);
		System.out.println(username);
		System.out.println(password);
	}
	
	
}
