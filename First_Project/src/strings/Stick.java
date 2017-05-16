package strings;
import java.util.*;
public class Stick {

	public static void main(String[] args) {
	

//		Scanner s = new Scanner(System.in);
//		int len = s.nextInt();
//		int arr[] = new int[len];
//		int sum = 0, min = Integer.MAX_VALUE, stickCount = 0;
//		for (int i = 0; i < len; i++) {
//			arr[i] = s.nextInt();
//			sum += arr[i];
//		}
//		s.close();
//		while (sum > 0) {
//			stickCount = 0;
//			// find min value
//			min = Integer.MAX_VALUE;
//			for (int i = 0; i < len; i++) {
//				if (arr[i] > 0 && min > arr[i]) {
//					min = arr[i];
//				}
//
//			}
//			
//			// reduce the stick value
//			for (int i = 0; i < len; i++) {
//				if (arr[i] >= min) {
//					arr[i] = arr[i] - min;
//					stickCount++;
//				} else if (arr[i] > 0) {
//					stickCount++;
//					arr[i] = 0;
//				} else if (arr[i] == 0) {
//
//				}
//			}
//			sum = 0;
//
//			// find number of sticks remaining
//			for (int i = 0; i < len; i++) {
//				sum += arr[i];
//
//			}
//			
//
//			System.out.println(stickCount);
//		}
		
		  Scanner s = new Scanner(System.in);
	        int n=s.nextInt();
	        int[] arr=new int[n];
	        for(int i=0;i<n;i++)
	            {
	            arr[i]=s.nextInt();
	        }
	        Arrays.sort(arr);
	        int max=0;
	        for(int i=0;i<n;i++)
	            {
	            if(arr[i]>max)
	                {
	                max=arr[i];
	                System.out.println(n-i);
	            }
	        }
	        s.close();
	}

}
