package Inheritance_tillPackage;

class Bank
{
	int getinterest(){return 0;}
}

class Axis_Bank extends Bank
{
	int getinterest(){return 10;}
}

class Sbi_Bank extends Bank
{
	int getinterest(){return 9;}	
}

class Icici_Bank extends Bank
{
	int getinterest(){return 8;}
}
public class Bank_Test {
	public static void main(String[] args) {
//		Axis_Bank a = new Axis_Bank();
//		Sbi_Bank s = new Sbi_Bank();
//		Icici_Bank i = new Icici_Bank();
		
		/** reference variable of Parent class refers to the object of Child class, it is known as upcasting**/
		Bank a = new Axis_Bank();//Upcasting- run time polymorphism
		Bank s = new Sbi_Bank();
		Bank i = new Icici_Bank();
		//In upcasting, Runtime polymorphism can't be achieved by data members.
		System.out.println("SBI Rate of Interest: "+s.getinterest());  
		System.out.println("ICICI Rate of Interest: "+i.getinterest());  
		System.out.println("AXIS Rate of Interest: "+a.getinterest());  
		
	}

}
