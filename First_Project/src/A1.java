//demo of this constructor
class B1
{
	A1 obj;
	//We can pass the this keyword in the constructor also
	B1(A1 obj)
	{
		this.obj=obj;
	}
	void display()
	{
		System.out.println(obj.data);
	}
}
public class A1
{
	int data = 10;
	
	A1()
	{
		B1 obj2 = new B1(this);
		obj2.display();
	}
	public static void main(String[] args) {
		A1 obj1 = new A1();
		}
	
	
	
}
//this keyword that you return as a statement from the method
//this keyword refers to the current class instance variable

