package algo1;
import java.util.*;

public class Linear_Search {
	private static long starttime = System.currentTimeMillis();
	

	public int linear_srch(int arr[], int n, int x) {
		int i = 0;
		for (i = 0; i < n; i++) {
			if (arr[i] == x) {
			return i;
			}
		}

		return  -1;
	}

	public static void main(String[] args) {
		System.out.println(starttime);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the val");
		int val = sc.nextInt();
		
		
		int A[] = { 32, 24, 56, 78, 1, 23, 45, 11, 50, 04, 53 };

		int index = new Linear_Search().linear_srch(A, A.length, val);
		if (index == -1) {
			System.out.println("Element Not found");
		} else {
			System.out.println("Value " + val + " is found at index A[" + index + "]");
		}
		long endtime = System.currentTimeMillis();
		System.out.println(endtime);
		sc.close();
		System.out.println("It took " + (endtime - starttime) + "ms");
	}
}
