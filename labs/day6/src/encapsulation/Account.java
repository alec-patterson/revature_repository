package encapsulation;

public class Account {
	private String accountHolder;
	private double accountTotal;
	private long accountId;
	
	/*
	 * Getters and setters are a convention used to 
	 * read/access or change/mutate the fields(variables)
	 * on a class
	 * 
	 * getters and setters use the following naming convention
	 * get<VariableName>
	 * set<VariableName>
	 */
	public String getAccountHolder() {
		return accountHolder;
	}
	
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	public double getAccountTotal() {
		return accountTotal;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountTotal(double accountTotal) {
		this.accountTotal = accountTotal;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public static void main(String[] args) {
		Account a = new Account();
		a.setAccountHolder("Alec");
		System.out.println(a.getAccountHolder());
	}
}
