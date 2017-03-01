//Abstraction is a process of hiding the implementation details and showing only functionality to the user.
//A class that is declared as abstract is known as abstract class. It needs to be extended and its method implemented. It cannot be instantiated.
//A method that is declared as abstract and does not have implementation is known as abstract method.

package Inheritance_tillPackage;

abstract class tiger{
	tiger()
	{System.out.println("Tiger Class is invoked");}
	
	abstract void run();
	void flash()
	{
		System.out.println("Tiger Flashed");
	}
}


class Honda1 extends tiger{
	void run(){System.out.println("Honda1 is triggerd");}
	
}

public class Abstract_Demo {
public static void main(String[] args) {
	tiger t1 = new Honda1();
	t1.run();
	t1.flash();
	
}
}
