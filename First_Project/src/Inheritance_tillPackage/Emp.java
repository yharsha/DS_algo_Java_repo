package Inheritance_tillPackage;

public class Emp {
	int id;
	String name;
	Address obj;//refernce eg:structure using objects
	
	public Emp(int id, String name,Address obj)
	{
		  this.id = id;  
		    this.name = name;  
		    this.obj=obj;  
	}
	void display(){  
		System.out.println(id+" "+name);  
		System.out.println(obj.city+" "+obj.state+" "+obj.country);  
		}  
	public static void main(String[] args) {
		
//		Address obj1 = new Address("gzb","UP","india");  
//		Address obj2 = new Address("gno","UP","india");  
		
		Address obj1 = new Address();
		Address obj2 = new Address();
		obj1.details("gzb","UP","india");
		obj2.details("gno","UP","india");
	
		Emp e1=new Emp(111,"varun",obj1);  
		Emp e2=new Emp(112,"arun",obj2);  
		
		e1.display();
		e2.display();
	}

}
