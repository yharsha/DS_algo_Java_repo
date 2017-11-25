package dynamic_programming;

public class DP_Demo {

	public int Fib(int n)
	{
		//APPROACH1:disadvaTAGe doest store does repeated calculations f(o,1,2,3 etc...)...LARGE NUMBER LEADS TO EXPONENTIAL
//		if(n<=0){return 0;}
//		else if(n==1){return 1;}
//		return Fib(n-1)+Fib(n-2);
		
		//APPROACH2:ARRAY...EXPONENTIAL TO POLYNOMIAL
		
		if(n==0 || n==1){return 1;}
		int f[]= new int[n+1];
		f[0]=0;
		f[1]=1;
		for(int i=2;i<=n;i++)
		{
			f[i]=f[i-1]+f[i-2];
		}
		return f[n];

		//Approach 3: We need only two values f[n-1]+f[n-2]
//		int a=0,b=1,sum,i;
//		for(int j=0;j<n;j++)
//		{
//			sum=a+b;
//			a=b;
//			b=sum;
//		}
//		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			 //print fib(n)
		int n=5;
		DP_Demo d1 = new DP_Demo();
		
		System.out.println(d1.Fib(8));
	}

}
