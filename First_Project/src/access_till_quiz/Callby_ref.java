package access_till_quiz;

public class Callby_ref {
int data = 99;
void call_by_val(int val)
{
	val = val+ 100;
}

void call_by_ref(Callby_ref  obj)
{
	obj.data +=100; 
}

public static void main(String[] args) {
	Callby_ref c1 = new Callby_ref();
	System.out.println("before call by value:"+c1.data);
	c1.call_by_val(c1.data);
	System.out.println("After call by value:"+c1.data);
	System.out.println("**........................**");
	System.out.println("before call by Ref:"+c1.data);
	c1.call_by_ref(c1);
	System.out.println("After call by Ref:"+c1.data);
	
	//command line arguement demo
	for(int i=0;i<args.length;i++)
	{
		System.out.println("arg["+i+"]..:"+args[i]);
	}
}
}
