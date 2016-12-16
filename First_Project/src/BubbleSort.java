import java.util.*;

public class BubbleSort {

	 static int swap(int i,int j, int arr[]) {
		int temp = 0;
		temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
		return 0;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// CTRL+SHIFT+F -->AutoFormat code
		// int a[]=new int[5];
		int a[] = {1,3,2,4,5,6,7,8,9};
		int k=0,flag=1;//for discontinuing sorting once all elements are sorted.

		// Bubble sort algorithm
		// iterating & -->scanning - so that larger elements bubble up
		for (int j = 0; j < a.length; j++) {
			
			for (int i = 0; i < (a.length - 1-k); i++) {
				if (a[i] > a[i + 1]) {
					flag = swap(i,i+1,a);
				}
				
				
			}
			k++;//for optimizing Scanning
			if(flag==1)//for discontinuing sorting once all elements are sorted.
				break;
		}

		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			System.out.print(" ");
		}
	}
}
