package src.fixtures;

public class test {
	public static void main(String[] args) {
		String n = "NoRtH is the direction i want to go in";
		n = n.toLowerCase();
		System.out.println(n);
		String[] parsed = n.split(" ");
		for(int i = 0; i < parsed.length; i++)
			System.out.println(parsed[i]);
	}
}
