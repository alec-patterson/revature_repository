package day6;

public class wrapper {

	/*
	 * wrapper classes allow you to view a datatype 
	 * as if it existed in a different inheritance heirarchy or provide more functionality to a datatype
	 * 
	 * in java primitive datatypes all have a wrapper
	 * class associated with them in the JRE, these
	 * allow you to treat the primitive as if it were an object
	 * 
	 * java also allows you to convert between the 
	 * primitive and wrapper automatically. this is
	 * referred to as autoboxing (primitive > wrapper)
	 * and unboxing (wrapper > primitive)
	 */
	public static void main(String[] args) {
		int num = 10;
		Integer number = null; // autoboxing
		
		Double dbl = new Double(10.0);
		double d = dbl;
		System.out.println(dbl);
		System.out.println(d);
	}

}
