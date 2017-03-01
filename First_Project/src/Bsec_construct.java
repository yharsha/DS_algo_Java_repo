

//Java constructor is invoked at the time of object creation.
//It constructs the values i.e. provides data for the object that is why it is known as constructor.
public class Bsec_construct {
	int id;
	String name;
	int age;

	void display() {
		System.out.print("id:" + id);
		System.out.print(" name:" + name);
		System.out.print(" age:" + age);
		System.out.println(" ");
	}

	// Example of default constructor
	Bsec_construct() {
		System.out.println("Bsec obj is created");
	}
	// If there is no constructor in a class, compiler automatically creates a
	// default constructor which provide default valeus.

	// parameterized constructor
	Bsec_construct(int a, String b) {
		System.out.println("*********Parameterized constructor#");
		id = a;
		name = b;

	}

	// Constructor Overloading:class can have any number of constructors that
	// differ in parameter lists.
	Bsec_construct(int a, String b, int c) {
		System.out.println("*********constructor overloading by changing Parameters#");
		id = a;
		name = b;
		age = c;
	}

/*There is no copy constructor in java. But, we can copy the values of one object to another like copy constructor in C++.
	By assigning the values of one object into another
	By clone() method of Object class*/
 Bsec_construct(Bsec_construct s) {
	 System.out.println("*********copy constructor#");
	 id=s.id;
	 name=s.name;
	 age=s.age;
	}
	
	
	public static void main(String[] args) {

		Bsec_construct b1 = new Bsec_construct();
		b1.display();
		Bsec_construct b2 = new Bsec_construct(11, "Gustavao");
		b2.display();
		Bsec_construct b3 = new Bsec_construct(32, "Pablo", 44);
		b3.display();
		
		//copy constructor -By constructor
		Bsec_construct b4 = new Bsec_construct(b3);
		b4.display();
		
		//Copying values without constructor
		Bsec_construct b5 = new Bsec_construct();
		b5.id=b3.id;
		b5.name=b3.name;
		b5.age=b3.age;
		b5.display();
		
		
	}

}
