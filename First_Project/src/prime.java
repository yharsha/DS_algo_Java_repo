import java.util.Scanner;
public class prime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ctrl+F11 shortcut for running a program
	
		Scanner user_input = new Scanner (System.in);
		int num;
		System.out.println("enter the number:");
		num =  user_input.nextInt();
		
		int flag=0;
		
		for(int i=2;i<num;i++)
		{
			if(num==2)
			{
				System.out.println("Not a Prime");
				break;
			}
			if(num%i==0)
			{
				flag=1;
				break;
			}
		}
		if(flag==0)
			{
			System.out.println(" prime");
			}
		else System.out.println("Not a Prime");
		user_input.close();//to stop resource leak of scanner
		}
	
}