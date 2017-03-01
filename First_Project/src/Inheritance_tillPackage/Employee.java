package Inheritance_tillPackage;
//simple example of inheritance
//coder-parent class
class Coder {
	float salary = 40000;
void display()
{
	System.out.println("hello");
}
}

class dev{
	void display()
	{
		System.out.println();
	}
}
//On the basis of class, there can be three types of inheritance in java: single, multilevel and hierarchical.
class Employee extends Coder
{
	float bonus = 20000;
	
	public static void main(String[] args) {
		Employee obj1 = new Employee();
		System.out.println("Salary:"+obj1.salary);
		System.out.println("Bonus :"+obj1.bonus);
	}
}
