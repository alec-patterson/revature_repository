package day6;

public class ByValue {

	public static void main(String[] args) {
		int num = 5;
		System.out.println(modify(num));
		System.out.println(num);
		
		Data d = new Data(10);
		System.out.println(d.id);
		System.out.println(modify(d).id);
		System.out.println(d.id);
	}
	
	public static int modify(int num) {
		num = num + 15;
		return num;
	}
	
	public static Data modify(Data d) {
		d.id = d.id + 15;
		return d;
	}
}

/*
 * pass by reference is fundamentally different from c++ than in java
 * Technically speaking java only does pass by value. 
 * what looks like pass by reference is when you pass an object in itself by value
 * a new object is created that points to the same location as the original object.
 */
