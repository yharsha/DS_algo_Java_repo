package Inheritance_tillPackage;
//Aggregation - when one class object is used as member of other class
//If a class have an entity reference, it is known as Aggregation. Aggregation represents HAS-A relationship.
//use: Code Reusability

class operation
{
	int square(int n)
	{
		return n*n;
	}
}

public class Aggn_Demo {
	
	operation obj;//Aggregation
	double pi = 3.14;
	//area of circle
	void area(int radius)
	{
		operation obj = new operation();
		float area = (float)obj.square(radius);
		System.out.println("Area: " +area);
	}
	public static void main(String[] args) {
		Aggn_Demo oc = new Aggn_Demo();
		oc.area(10);
	}

}
