//extra operations while initializing the instance variable in the instance initializer block.
/*The instance initializer block is created when instance of the class is created.
The instance initializer block is invoked after the parent class constructor is invoked (i.e. after super() constructor call).
The instance initializer block comes in the order in which they appear.*/

package Inheritance_tillPackage;

class A {
	A() {
		System.out.println("*****-------------------------*****");
		System.out.println("parent class constructor invoked");
	}
}

public class Init_Block extends A {
	int speed;

	Init_Block() {

		super();
		System.out.println("speed is " + speed);
		System.out.println("child class constructor invoked");
	}

	Init_Block(int a) {
		super();
		System.out.println("child class parameterized constructor invoked " + a);
	}

	// instance-initializer-block//same order they will appear
	{
		System.out.println("instance initializer block is invoked");
	}
	{
		System.out.println("instance initializer block 2 is invoked");
	}
	{
		speed = 199;
	}

	public static void main(String[] args) {
		Init_Block obj1 = new Init_Block();
		Init_Block obj2 = new Init_Block(10);
	}
}
