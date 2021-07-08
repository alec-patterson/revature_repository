package formatting;

public class formatLines {
	public static void main(String[] args) {
		String[] name = {"Alec", "Rj", "David", "Alex"};
		float[] wallet = {200.3457345F, 300.84764F, 50.6756745F, 78.34543F};
		
		for(int i = 0; i < name.length; i++) { 
			System.out.printf("%d. %-10s %.2f\n", i + 1, name[i], wallet[i]);
		}
	}
}

/*
 * printf is a lot like it is in c++. In order to do alignment you add a
 * number before the typing of the item. a positive number will set a 
 * width for the variable to be printed (right aligned). To left align it 
 * use a negative number. If you want to limit the decimal places then use
 * a decimal before the type. You can use both to do alignment and decimal
 * limitation. 
 */