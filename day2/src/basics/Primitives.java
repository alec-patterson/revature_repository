package basics;

import java.util.logging.Formatter;

public class Primitives {
	public static void main(String[] args) {
		int i = 9;
		System.out.println(i);
		
		byte b = (byte)128; 
		System.out.println(b);
		
		long l = 10000000000L;
		System.out.println(l);
		
		double d = 1000.00;
		float f = 1000.00F;
		System.out.println(d + "\t" + f);
		System.out.println(1_000_000.00);
		
		/*
		 * int a = b & 0xff;
		 * something that i looked up there is no unsigned
		 * byte (0 to 255). You would have to declare an int and then
		 * and it with 0xff which in 8 bits is 1111 1111 so that way
		 * the bits are extended and wont be converted to negative.
		 */
		
		/*
		 * double a = 10.3333*3.0;
		 * System.out.printf("%.1f", a);
		 * also done myself. this will format the number to have one
		 * decimal place. 
		 * Number on the right of the decimal will make the number
		 * take up a space of that number of digits.
		 * Number on the left of the decimal will limit the number
		 * of decimal places and must have an f at the end
		 */

		
	}
}
