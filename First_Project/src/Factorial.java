import java.util.Scanner;
public class Factorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner user_input = new Scanner(System.in);
		int num;
		System.out.println("Enetr the num:");
		num = user_input.nextInt();
		
		//factorial prog
		int fact=1;
		while(num>1)
		{
			fact=fact *num;
			num--;
		}
		System.out.println("Fact:");
		System.out.println(fact);
		user_input.close();//to stop resource leak of scanner
	}

}
