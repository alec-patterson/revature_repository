
public class ConditionalBlocks {
	public static void main(String[] args) {
		//boolean b = false;
		boolean firstCondition = false;
		boolean secondCondition = true;
		
		if (firstCondition) {
			System.out.println("inside the if-statement");
			
			if (secondCondition) {
				System.out.println("inside the nested if-statement");
			}
		} else if(secondCondition) {
			System.out.println(5);
		} else {
			System.out.println("inside the else-statement");
		}
		
		/* Testing if b is true for ConditionalBlocks lab
		 * b = true;
		 * if (b) {
		 * 		System.out.println("inside the second if-statement");
		 * }
		 */
		
		System.out.println("outside the if-statement");
		
		
	}

}
