
//If subclass (child class) has the same method as declared in the parent class, it is known as method overriding in java.
package Inheritance_tillPackage;

class vehicle
{
 void run()
 {
	 System.out.println("Vehicle run method is invoked");
 }

}

//if both parent and child has same method,child obj will select child method
public class Bike extends vehicle//bike is a vehicle(inheritance)
{

	void run()
	{
		 System.out.println("Bike run method is invoked");
	}
	 protected void msg()
	 {
		 System.out.println("Hello from Bike class:package Inheritance_tillPackage");
	 }
	
	public static void main(String[] args) {
		Bike obj = new Bike();
		obj.run();
	}
}
