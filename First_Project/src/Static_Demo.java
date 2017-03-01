//static keyword in java is used for memory management mainly.
public class Static_Demo {
	
	int id;
	String name;
	//static variable can be used to refer the common property of all objects
	// static variable gets memory only once in class area at the time of class loading
	static String college="AMC Engineering";
	
	//if any object changes the value of the static variable, it will retain its value.
	static int counter;
	
	Static_Demo()
	{
		
	}
	
	Static_Demo(int a,String b) {
		id=a;
		name=b;
	}
	
	void display()
	{
		System.out.println(id+" " +name +" " +college);
	}
	
	/*A static method belongs to the class rather than object of a class*/
	
	static void change()
	{
		//static method can access static data member and can change the value of it.
		college="STANFORD";
		
		//name = "Pirate";static method can not use non static data member or call non-static method directly.
		//this and super cannot be used in static context.
	}
	
	static{System.out.println("Block of static code excecuted before static main method");}
	
	void counter()
	{
		
		counter++;
		System.out.println("Count:"+ counter);
	}
	
	public static void main(String[] args) {
		Static_Demo ob1 = new Static_Demo(13, "Gaucha");
		ob1.display();
		ob1.counter();
		Static_Demo ob2 = new Static_Demo();
		ob2.counter();
		Static_Demo ob3 = new Static_Demo();
		ob3.counter();
		
//		A static method can be invoked without the need for creating an instance of a class.
		Static_Demo.change();
		ob1.display();
		
	}
	

}
