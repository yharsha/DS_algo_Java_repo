//final keyword in java is used to restrict the user
package Inheritance_tillPackage;

class Bikef {
	final int speedlimit = 90;
	final String bike_num;//blank or uninitialized final variable

	Bikef()
	{
		System.out.println("Bikef constructor triggered");
		bike_num = "AP021998";//blank or uninitialized final variable can be initialized only in constructor.
		System.out.println("Bike_NUM:"+bike_num);
	}
	final void run() {
		// speedlimit = 150;It can't be changed because final variable once assigned a value can never be changed.
		System.out.println("final method inherited");
	}
}

final class Bikewrong//If you make any class as final, you cannot extend it.
{}

public class Final_Demo extends Bikef {

	/*A static final variable that is not initialized at the time of declaration is known as static blank final variable. 
	It can be initialized only in static block.*/
	static final int data;
	// void run()//Cannot override the final method from Bikef
	// { System.out.println("Run method in child class");}
	
	static{data=50;}

	public static void main(String[] args) {

//		Bikef obj = new Bikef();
//		obj.run();
		Final_Demo obj1 = new Final_Demo();
		obj1.run();
		System.out.println("Data:"+Final_Demo.data);
	}
}
