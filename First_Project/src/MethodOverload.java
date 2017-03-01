
public class MethodOverload {
	//Method overloading increases the readability of the program.
	//Method Overloading by changing the no. of arguments
	void sum(int a,int b){System.out.println(a+b);}
	void sum(int a,int b,int c){System.out.println(a+b+c);}
	
	//Method Overloading by changing data type of argument
	void mul(int a,int b){System.out.println(a*b);}
	void mul(double a,double b){System.out.println(a*b);}
	
	//Can we overload main() method?
	public static void main(int a)
	{
		System.out.println(a);
	}
	
	//Type Promoting...Byte-->short..int.float....double..long
	//http://www.javatpoint.com/method-overloading-in-java
	 void sum_tprom(int a,double b){System.out.println(a+b);}  
	  void sum_tprom(int a,int b,int c){System.out.println(a+b+c);}  
	
	
	
	public static void main(String args[])
	{
		MethodOverload obj1 = new MethodOverload();
		System.out.println("Method Overloading by changing the no. of arguments");
		obj1.sum(10,20);
		obj1.sum(10,20,30);
		System.out.println("Method Overloading by changing data type of argument");
		obj1.mul(5, 10);
		obj1.mul(5.5, 10.1);
		System.out.println("Can we overload main() method?Yes...");
		main(10);
		System.out.println("Method Overloading with TypePromotion");
		obj1.sum_tprom(20, 30.2);////now second int literal will be promoted to double  
		
	}

}
