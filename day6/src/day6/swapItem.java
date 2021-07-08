package day6;

public class swapItem {
	public static void swap(int[] a) {
		int first = 0;
		int last = 0;
		if(a.length != 0) {
			first = a[0];
			last = a[a.length-1];
			a[0] = last;
			a[a.length-1] = first;
			for(int i = 0; i < a.length; i++)
				System.out.print(a[i] + " ");
			System.out.println();
		}
		else {
			System.out.println("Error, no array elements exist");
		}
	}
	public static void main(String[] args) {
		int[] a = {23,54,1,56,78,65};
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
		swap(a);
	}
}
