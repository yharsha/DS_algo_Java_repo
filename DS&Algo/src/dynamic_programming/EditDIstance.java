package dynamic_programming;

import java.util.Scanner;

public class EditDIstance {

	  static int min(int x,int y,int z)
	    {
	        if(x<=y && x<=z)return x;
	        if(y<=x && y<=z)return y;
	        return z;
	    }
	    static int minEditDis(String s1,String s2,int m,int n)
	    {
	        if(m==0){return n;}
	        if(n==0){return m;}
	        //last equal
	        if(s1.charAt(m-1)==s2.charAt(n-1))
	        {
	            return minEditDis(s1,s2,m-1,n-1);
	        }
	        //last not equal
	        //min of {insert : m,n-1 & delete: m-1,n & modify: m-1,n-1}
	       
	            return 1+min(minEditDis(s1,s2,m,n-1),
	                                minEditDis(s1,s2,m-1,n),
	                                minEditDis(s1,s2,m-1,n-1));
	    }
	    
	    static int minEditDisDp(String s1,String s2,int m,int n)
	    {
	        int dp[][]= new int[m+1][n+1];
	        for(int i=0;i<=m;i++)
	        {
	            for(int j=0;j<=n;j++)
	            {
	                if(i==0)
	                {
	                    dp[i][j]=j;
	                }
	                else if(j==0)
	                {dp[i][j]=i;}
	                
	                else if(s1.charAt(i-1)==s2.charAt(j-1))
	                {
	                    dp[i][j]=dp[i-1][j-1];
	                }
	                else{
	                dp[i][j]=1+min(dp[i][j-1],dp[i-1][j],dp[i-1][j-1]);
	                }
	            }
	        }
	        return dp[m][n];
	    }
		public static void main (String[] args) {
			//code
				Scanner s = new Scanner(System.in);
			int testCases = s.nextInt();
			for(int i=0;i<testCases;i++)
			{
			    int m=s.nextInt();
			    int n=s.nextInt();
			    String s1=s.next();
			    String s2=s.next();
			    System.out.println(minEditDisDp(s1,s2,m,n));
			}

		}
}
