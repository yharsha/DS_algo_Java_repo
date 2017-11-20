import java.util.*;
	import java.lang.*;
	import java.io.*;

public class pract {
	
		public static void main (String[] args) {
			//code
			Scanner s = new Scanner(System.in);
			int testCases = s.nextInt();
			for(int i=0;i<testCases;i++)
			{
			    int limit=s.nextInt();
			    int arr[]= new int[limit];
			    //storing
			    for(int j=0;j<limit;j++)
			    {
			        arr[j]=s.nextInt();
			    }
			    
			    //logic..o(n2)
			  int max=Integer.MIN_VALUE;
			  for(int a=0;a<limit;a++)
			  {
				  int sum=0;
				  for(int b=a;b<limit;b++)
				  {
					 sum=sum+arr[b];
					 if(max<sum)
					 {
						 max=sum;
					 }
				  }
			  }
			  System.out.println(max);

			}	
}
}