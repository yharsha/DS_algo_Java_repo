package dynamic_programming;

//http://algorithms.tutorialhorizon.com/dynamic-programming-longest-common-subsequence/
public class Lcs_Demo {

	// Case 1:
	// String A: "ABCD", String B: "AEBD"
	// LCS("ABCD", "AEBD") = 1 + LCS("ABC", "AEB")
	// Case 2:
	// String A: "ABCDE", String B: "AEBDF"
	// LCS("ABCDE", "AEBDF") = Max(LCS("ABCDE", "AEBD"), LCS("ABCD", "AEBDF"))

	public int LCS_recursive(String A, String B) {
		if (A.length() == 0 || B.length() == 0) {
			return 0;
		}
		int la = A.length();
		int lb = B.length();
		// check if last characters are same
		if (A.charAt(la - 1) == B.charAt(lb - 1)) {
			// Add 1 to the result and remove the last character from both
			return 1 + LCS_recursive(A.substring(0, la - 1), B.substring(0, lb - 1));
		} else {
			// Remove the last character of String 1 and make a recursive call
			// and remove the last character from String 2 and make a
			// recursive and then return the max from returns of both recursive
			// calls
			return Math.max(LCS_recursive(A.substring(0, la), B.substring(0, lb - 1)),
					LCS_recursive(A.substring(0, la - 1), B.substring(0, lb)));
		}
	}

	public int LCS_Find(String A, String B) {
		int m = A.length();
		int n = B.length();
		//initial row,col fill with zero
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <=n; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
				} else if (A.charAt(i - 1) == B.charAt(j - 1)) {
					//if matched 1+prev diagonal
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else if (A.charAt(i - 1) != B.charAt(j - 1)) {
					//if not matched max(left or top)
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}
			}
		}
		
//print the Sequence
		if(dp[m][n]!=0)
		{
			char seq[]=new char[dp[m][n]];
			
			int i=m,j=n,k=0;
			while(i!=0 && j!=0)
			{
				if(dp[i][j]==dp[i][j-1])
				{
					j=j-1;
				}
				else if(dp[i][j]==dp[i-1][j])
				{
					i=i-1;
				}
				else if(dp[i][j]==(1+dp[i-1][j-1]))
				{
					seq[k]=B.charAt(j-1);
					k++;
					i--;
					j--;
				}
			}
			for(int a= seq.length-1;a>=0;a--)
			{
				System.out.println(seq[a]);
			}
		}
		
		
		return dp[m][n];
//TC:o(mn)
	}

	public static void main(String[] args) {

		Lcs_Demo d1 = new Lcs_Demo();
		System.out.println(d1.LCS_recursive("ACBAED", "ABCADF"));
		System.out.println("*********************************");
		System.out.println(d1.LCS_Find("ABCADF","ACBAED"));
		

		// String s1="abcgh";
		// char[]arr=new char[s1.length()];
		// for(int i=0;i<s1.length();i++)
		// {
		// arr[i]=s1.charAt(i);
		// }
		// String s2= String.valueOf(arr);
		// String s3=s2.substring(0, 4);

	}

}
