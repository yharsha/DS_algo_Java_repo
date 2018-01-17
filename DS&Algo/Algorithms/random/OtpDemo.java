package random;

import java.util.*;

public class OtpDemo {
	
	public int GenerateOtp(int n)
	{
//		String val ="1234567890";
		
		Random rgen = new Random();
		
		int num=0,temp;
		for(int i=0;i<n;i++)
		{
			temp=rgen.nextInt(10);
			num=(num* 10);
			num+=temp;
		}
		
		return num;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OtpDemo d1 = new OtpDemo();
		
		for(int i=0;i<500;i++)
		{
			System.out.println(d1.GenerateOtp(5));
		}
	}

}
