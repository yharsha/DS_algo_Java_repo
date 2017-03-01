package access_till_quiz;
//he Object class is the parent class of all the classes in java by default. 
public class Objectc_demo {
public static Objectc_demo getObject()
{
	Objectc_demo d1 = new Objectc_demo();
	return d1;
	}
	public static void main(String[] args) {
		Object obj=getObject();
		System.out.println(obj);
	}
}
