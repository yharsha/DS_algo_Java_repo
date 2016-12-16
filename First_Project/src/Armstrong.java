import java.util.*;
public class Armstrong {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner user_input = new Scanner(System.in);
		System.out.println("Enter num");
		int num = user_input.nextInt();
		//****************************
		int temp=0,sum=0,val=0;
		temp=num;
		while(temp>0)
		{
			val=temp%10;
			 sum=(int) (sum+Math.pow(val, 3));
			 temp=temp/10;
		 }
		if(sum==num)
		{
			System.out.println("AS");
		}
		else
		{
			System.out.println("Not an as");
		}
	}

}
