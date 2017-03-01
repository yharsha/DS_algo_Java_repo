//java instanceof operator is used to test whether the object is an instance of the specified type (class or subclass or interface).
package Inheritance_tillPackage;

class An
{}

public class Instance_Demo extends An {

	static void convert(An obj)
	{
		if(obj instanceof An)
		{
			Instance_Demo d1 = (Instance_Demo)obj;
			
			System.out.println("Downcasting Performed..."+d1);
			}
	}
	public static void main(String[] args) {
		An ob1 = new Instance_Demo();
		System.out.println(ob1 instanceof An);
		System.out.println(ob1 instanceof Instance_Demo);
	
		An ob2 = new An();
		System.out.println(ob2 instanceof An);
		System.out.println(ob2 instanceof Instance_Demo);
		
		
		//Instance_Demo  ob3 = new An(); Type mismatch: cannot convert from An to Instance_Demo{subclass type....parent object error}
		
		Instance_Demo ob3 = new Instance_Demo();
		System.out.println(ob3 instanceof An);
		System.out.println(ob3 instanceof Instance_Demo);
		
		Instance_Demo ob4 = null;
		System.out.println(ob4 instanceof Instance_Demo);
		
		
		//When Subclass type refers to the object of Parent class, it is known as downcasting.
		System.out.println("Downcasting.............");
		An ob5 = new Instance_Demo();
		Instance_Demo.convert(ob5);
		
		
		
	}
}
