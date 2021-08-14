package bankingAccount;

public class AccountManager {
	private User user = null;
	private Database db;
	
	public AccountManager(User user) {
		this.user = user;
		db = new Database();
	}
	
	
}
