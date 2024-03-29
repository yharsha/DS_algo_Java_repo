package access_till_quiz;

import Inheritance_tillPackage.Bike;

//The private access modifier is accessible only within class.
//Note: A class cannot be private or protected except nested class
class A
{
	private int data = 40;
	private A(){}//class constructor private, you cannot create the instance of that class from outside the class.
	private	void msg()
	{
		System.out.println("msg from class A");
	}
	void go()
	{
		msg();
	}
}
//The public access modifier is accessible everywhere. It has the widest scope among all other modifiers.

public class Access_Demo extends Bike {

	public static void main(String[] args) {
		Access_Demo ob1 = new Access_Demo();
		//ob1.msg();//Compile Time Error  
		//A ob2 = new A();//Compile Time Error 
		
		
		//Default specifier-- The default modifier is accessible only within package.
		//	Vehicle b1 = new Vehicle();//from import Inheritance_tillPackage.Bike;
		//		v1.run();
		//The default modifier is more restrictive than protected
		
		/**The protected access modifier is accessible within package and outside the package but through inheritance only.**/
		ob1.msg();
		
	}
}
