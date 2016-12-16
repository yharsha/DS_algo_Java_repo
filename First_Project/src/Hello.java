
import java.util.Scanner;
public class Hello {

	//global
	public static int count;
	public static int counter=0;
	public static int c=0;
	private static Scanner input;

	//recursive for febinocci
	public static void fact( int a, int b)
	{
		if( counter < count)
		{
			c=a+b;
			System.out.println(c);
			a=b;
			b=c;
			counter++;
			fact(a,b);
		}
		else return;
	}

	//Main method
	public static void main(String[] args) {
		input = new Scanner(System.in);
		System.out.println("Enter the Count:");
		count =  input.nextInt();



		//**************************************************
		/*Fibonacci Series in Java without using recursion
		 * Scanner input = new Scanner(System.in);
		System.out.println("Enter the Count:");
		int count = input.nextInt();
		int a=0,b=1;int c=0;
		System.out.println(a);
		System.out.println(b);
		for(int i=0;i<count;i++)
		{
			c=a+b;
			System.out.println(c);
			a=b;
			b=c;
		}//*************************************************
		 */

		fact(0,1);


	}


}
