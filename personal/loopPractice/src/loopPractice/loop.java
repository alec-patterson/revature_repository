package loopPractice;

public class loop {
	public static void main(String[] args) {
		 int[][] a = {{1, 2, 3}, {10, 20, 30}};
		 for(int[] b: a) {
			 for(int c: b) {
				 System.out.println(c);
			 }
		 }
	}
}
