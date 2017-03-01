//In java, this is a reference variable that refers to the current object.
public class This_Demo {

	int id;
	String name;
	int age;
	
	
	This_Demo()
	{
		System.out.println("Default constructor is invoked");
	}
	This_Demo(int id,String name)
	{
		//The this() constructor call can be used to invoke the current class constructor (constructor chaining). 
		//This approach is better if you have many constructors in the class and want to reuse that constructor.
		this();
		//**
		//parameter (formal arguments) and instance variables are same 
		//that is why we are using this keyword to distinguish between local variable and instance variable.
		this.id=id;
		this.name=name;
		
	}
	This_Demo(int id,String name,int age)
	{
		this(id,name);//now no need to initialize id and name-- constructor chaining
		this.age=age;
	}
	
	//this keyword can be passed as an argument in the method.
	void m(This_Demo obj)
	{
		System.out.println("this keyword can be passed as an argument in the method");
	}
	void p()
	{
		m(this);//passing this as arguement
	}
	void display()
	{
		System.out.println(id+" "+name+" "+age);
	}
	public static void main(String[] args) {
		This_Demo ob1 = new This_Demo(13, "JUDY");
		This_Demo ob2 = new This_Demo(31, "Cariolla");
		ob1.display();
		ob2.display();
		This_Demo ob3 = new This_Demo(99,"Gustavo",39);
		ob3.display();
		This_Demo ob4 = new This_Demo();
		ob4.p();
	}
}
