package iter;

import java.util.Iterator;
import java.util.ArrayList;

public class Interate {
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		a.add(23);
		a.add(45);
		
//		for(int i = 0; i < a.size(); i++) {
//			System.out.println(a.get(i));
//		}
		
//		Iterator<Integer> j = a.iterator();
//		while(j.hasNext()) {
//			System.out.println(j.next());
//		}
		
		a.forEach((element) -> {System.out.println(element);});
	}
}
