//The super keyword in java is a reference variable that is used to refer immediate parent class object.

package Inheritance_tillPackage;

class vehicle1 {
	int speed = 100;

	void msg() {
		System.out.println("Welcome to main class");
	}

	vehicle1()

	{
		System.out.println("Vehicle constructor is triggered");
	}

}

public class Bike5 extends vehicle1 {
	int speed = 50;

	void msg() {
		System.out.println("Welcome to child class");
	}

	Bike5() {
		// super();//super() is added in each class constructor automatically by
		// compiler.
		System.out.println("Bike5 constructor is triggered");
	}

	void display() {
		System.out.println("Speed:" + speed);// speed of child class
		System.out.println("Speed:" + super.speed);// super is used to refer
													// immediate parent class
													// instance variable.
		System.out.println("**************************************");
		msg();
		super.msg();// super keyword can also be used to invoke parent class method
		//In case there is no method in subclass as parent, there is no need to use super.

	}

	public static void main(String[] args) {
		Bike5 obj = new Bike5();
		obj.display();
	}
}
