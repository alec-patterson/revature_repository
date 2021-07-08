package encapsulation;

public class Data {
	public static String pub = "public";
	protected static String pro = "protected";
	static String def = "Package-private / Default";
	private static String pri = "private";
			
	public static void main(String[] args) {
		System.out.println(pub);
		System.out.println(pro);
		System.out.println(def);
		System.out.println(pri);
	}
}
