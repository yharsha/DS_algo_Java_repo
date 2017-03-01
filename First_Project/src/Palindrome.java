import java.util.Scanner;
public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner user_input = new Scanner(System.in);
		int num;
		System.out.println("Enter the num:");
		num = user_input.nextInt();
		//Checking for palindrome
		int temp_num=num;
		int sum=0,rem=0;
		while(temp_num>0)
		{
			rem=temp_num%10;
			sum=(sum*10)+rem;
			temp_num=temp_num/10;
		}
		
		if(num==sum)
		{
			System.out.println("Palin");
		}
		else System.out.println("Not a Palin");
		user_input.close();//to stop resource leak of scanner
	}

}
