package access_till_quiz;
//encapsulation demo that has only one field with its setter and getter methods.
//By providing only setter or getter method, you can make the class read-only or write-only.
public class E2 {
	public static void main(String[] args) {
		E1 obj = new E1();
		obj.set("Jain");
		System.out.println("Name:"+obj.get());
	}

}
