package arrays_strings;

//Largest Sum Contiguous Subarray
//https://medium.com/@marcellamaki/finding-the-maximum-contiguous-sum-in-an-array-and-kadanes-algorithm-e303cd4eb98c
public class KadaneImpl {
	
	// the maximum is either the maximum of the previous subarray plus the current element, or just the current element of the array.
	static int maxSubArraySum(int [] arr)
	{
		int currSum=arr[0],maxSum=arr[0];
		for(int i=1;i<arr.length;i++)
		{
			currSum=Math.max(arr[i], arr[i]+currSum);
			maxSum=Math.max(currSum, maxSum);
		}
		return maxSum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  int a[] = {-2, -3, 4, -1, -2, 1, 5, -3};
		    int n = a.length;   
		    int max_sum = maxSubArraySum(a);
		    System.out.println("Maximum contiguous sum is "
		                       + max_sum);
	}

}
