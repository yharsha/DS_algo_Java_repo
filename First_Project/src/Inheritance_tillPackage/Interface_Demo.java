//Interface - to achieve fully abstraction.
//In other words, Interface fields are public, static and final bydefault, and methods are public and abstract.
//

package Inheritance_tillPackage;

interface printable {
	int copies = 10;

	void print();
}
interface showable{
	void show();
}

//An interface that have no member is known as marker or tagged interface
interface Serializable
{}


// Multiple inheritance in Java by interface
/** Multiple inheritance is not supported through class in java but it is possible by interface, why?**/
public class Interface_Demo implements printable {

	public void print() {
		System.out.println("Implemented interface abstract method :printable...");
	}

	public void show()
	{
	System.out.println("Implemented interface abstract method:sHOW...");	
	}
	
	public static void main(String[] args) {
		Interface_Demo i1 = new Interface_Demo();
		i1.print();
		i1.show();

	}

}
