package Inheritance_tillPackage;


class deo
{
	void run()
	{
		System.out.println("Parent class method is invoked");
	}
}
public class Binding extends deo{
	
	void run()
	{
		System.out.println("Child class method is invoked");
	}
	
	void data()
	{
		System.out.println("Data method is triggered");
	}
public static void main(String[] args) {
	Binding obj1 = new Binding();
	//System.out.println("Parent class method is invoked");compiler knows which obj
	obj1.data();
	
	deo obj2 = new Binding();
	//When type of the object is determined at run-time, it is known as dynamic binding
	/**We are calling the run method by the reference variable of Parent class. Since it refers to the subclass object and subclass method overrides the Parent class method,
	subclass method is invoked at runtime.**/
	obj2.run();
	
	deo b1 = new deo();
	b1.run();
}

}
