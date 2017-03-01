
package access_till_quiz;
import java.util.*;
//bject cloning is a way to create exact copy of an object.
//Cloneable interface must be implemented by the class whose object clone we want to create
//this saves lot of processing
public class Obj_Clone implements Cloneable{
	
	String name;
	int id;
	 Obj_Clone() {
		System.out.println("Constructor is triggered");
	}
	
	Obj_Clone(String name,int id)
	{
		this.name = name;
		this.id = id;
	}
	
	public Object Clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	void display()
	{
		System.out.print(name+"....");
		System.out.print(id+"....");
		System.out.println();
	}
	
	public static void main(String[] args) {
		try{
		Obj_Clone s1=new Obj_Clone("Jessie",123);
		s1.display();
		
		//cloning
		Obj_Clone s2 = (Obj_Clone)s1.clone();
		s2.display();
	}
	catch(CloneNotSupportedException c){}
	}
}
