import java.util.*;

public class SelectionSort {

	// to find min of an array
	static int[] min(int si, int ei, int arr[]) {
		int temp_val = arr[si];
		int index = 0;
		while (si <= ei) {
			if (arr[si] <= temp_val) {
				temp_val = arr[si];
				index = si;
			}
			si++;
		}
		return new int[] { temp_val, index };
	}

	// to swap values in array
	static void swap(int i1, int i2, int[] arr) {
		int stemp = arr[i2];
		arr[i2] = arr[i1];
		arr[i1] = stemp;
	}

	public static void main(String[] args) {
		// Selection sort - Find min and arrange it in increasing order --O(n2) complexity

		Scanner user_input = new Scanner(System.in);
		System.out.println("Enter total count of integers:");
		int len = user_input.nextInt();
		int a[] = new int[len];
		int min_val[];
		for(int i=0;i<len;i++)
		{
		System.out.println("Enter the integer:");
		a[i] =user_input.nextInt();
		
		}

		for (int i = 0; i <len - 1; i++) {
			// to find min
			min_val = min(i, a.length - 1, a);
			// to swap min and starting index
			swap(i, min_val[1], a);

		}
		// print array
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			System.out.print(" ");
		}
		user_input.close();
	}
}
