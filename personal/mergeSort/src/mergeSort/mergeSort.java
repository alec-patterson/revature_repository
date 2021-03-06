package mergeSort;

public class mergeSort {
	public static void merge(int left, int middle, int right, int[] a) {
		int n1 = middle - left + 1;
		int n2 = right - middle;
		
		int L[] = new int[n1];
		int R[] = new int[n2];
		
		for(int i = 0; i < n1; ++i)
			L[i] = a[left + i];
		for(int j = 0; j < n2; ++j)
			R[j] = a[middle + 1 + j];
		
		int i = 0;
		int j = 0;
		
		int k = left;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			}
			else {
				a[k] = R[j];
				j++;
			}
			k++;
		}
		
		while(i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}
		while(j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
		
	}
	
	public static void sort(int[] a, int left, int right) {
		if( left < right ) {
			int m = left + (right-left)/2;
			
			sort(a, left, m);
			sort(a, m + 1, right);
			
			merge(left, m, right, a);
		}
	}
	
	public static void main(String[] args) {
		// a = {1, 67, 756, 5, 45, 2};
		int[] a = new int[args.length];
		for(int i = 0; i < args.length; i++)
			a[i] = Integer.parseInt(args[i]);
		sort(a, 0, a.length-1);
		for(int i = 0; i < a.length; i++ ) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
