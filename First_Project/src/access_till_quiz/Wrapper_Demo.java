package access_till_quiz;
//Wrapper class in java provides the mechanism to convert primitive into object and object into primitive.
/**Primitive Type	Wrapper class -- 8 classes
boolean	Boolean
char	Character
byte	Byte
short	Short
int	Integer
long	Long
float	Float
double	Double**/
public class Wrapper_Demo {
	public static void main(String[] args) {
		int a = 20;
		System.out.println("***....//Converting int into Integer...........***");
		//Converting int into Integer  
		Integer i = Integer.valueOf(a);
		////autoboxing, now compiler will write Integer.valueOf(a) internally  
		Integer j = a;
System.out.println(a+" "+i+" "+j);  

System.out.println("**--------------//Converting Integer to int----------**");
Integer b = new Integer(45);
int val = b.intValue();
int val1 = b;
System.out.println(b+" "+val+" "+val1);    

	}

}
