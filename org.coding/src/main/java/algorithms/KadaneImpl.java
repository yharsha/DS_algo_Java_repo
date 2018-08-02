package algorithms;

import java.io.*;

public class KadaneImpl {
	
	static int maxSumSubArray(int[]arr, int n)
	{
		int global_sum=arr[0],curr_sum_so_far=arr[0];
		for(int i=1;i<n;i++)
		{
			curr_sum_so_far=Math.max(arr[i],curr_sum_so_far+arr[i]);
			global_sum=Math.max(global_sum, curr_sum_so_far);
		}
		return global_sum;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tests  = Integer.parseInt(br.readLine());
        for(int i=0;i<tests;i++)
        {
        	int n= Integer.parseInt(br.readLine());   
        	int [] arr=new int[n];
        	String[]tokens=br.readLine().split("\\s");
        	for(int j=0;j<n;j++)
        	{
        		arr[j]=Integer.parseInt(tokens[j]);
        	}
        	System.out.println(maxSumSubArray(arr,n));
        }
	}
}

