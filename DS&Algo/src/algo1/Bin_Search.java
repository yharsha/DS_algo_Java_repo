package algo1;
import java.util.*;
public class Bin_Search {
	private static long starttime = System.currentTimeMillis();
	

	public int binary_srch(int arr[],int li, int hi, int x) {
		if(li<=hi)
		{
		int mid = (li+hi)/2;
		if(x==arr[mid])
		{
			System.out.println("Element found");
			return mid;
		}
		else if(x<arr[mid])
		{
			hi = mid-1;
			return binary_srch(arr, li, hi, x);
		}
		else if(x>arr[mid])
		{
			li = mid+1;
			return binary_srch(arr, li, hi, x);
		}}
	 return -1;
		
	}

	public static void main(String[] args) {
		System.out.println(starttime);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the value");
		int val = sc.nextInt();
		
		
		int A[] = { 1, 2, 5, 7, 19, 23, 45, 59, 60, 64, 93 };

		int index = new Bin_Search().binary_srch(A, 0, A.length-1,val);
		if (index == -1) {
			System.out.println("Element Not found");
		} else {
			System.out.println("Value " + val + " is found at index A[" + index + "]");
		}
		long endtime = System.currentTimeMillis();
		
		sc.close();
		System.out.println("It took " + (endtime - starttime) + "ms");
	}
}
