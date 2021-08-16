package day6;

public class selectionSort {
	
	public static void sort(int[] a) {
		int min = 0;
		for(int i = 0; i < a.length-1; i++) {
			min = i;
			for(int j = i+1; j < a.length; j++) {
				if(a[j] < a[min])
					min = j;
			}
		swap(a, i, min);
		}
	}
	
	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void main(String[] args) {
		int [] a = {12, 4, 65, 7, 324, 35, 1, 23};
		
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
		sort(a);
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
