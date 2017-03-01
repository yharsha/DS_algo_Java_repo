package stacks;

public class Array_stacks {
	int stack_arr[];
	int capacity;
	public static final int MAX=10;
	int top=-1;

	public Array_stacks()
	{
		this(MAX);
	}
	public Array_stacks(int cap) {
		capacity = cap;
		stack_arr = new int[capacity];
	}
	public int size()
	{
		return(top+1);
	}
	public boolean isEmpty()
	{
		return(top<0);
	}
	public void push(int data) throws Exception{
		if(size()==capacity) throw new Exception("Stack is full");
		stack_arr[++top]=data;
	}
	public int top() throws Exception{
		if(isEmpty())throw new Exception("Stack is empty");
		return stack_arr[top];	
	}
	public int pop() throws Exception{
		int data;
		if(isEmpty())throw new Exception("Stack is empty:");
		data=stack_arr[top];
		stack_arr[top--]=Integer.MIN_VALUE;
		return data;
	}
	public String tostring()
	{
		String s;
		s="[";
		if(size()>0)
		{s=s+stack_arr[0];}
		if(size()>1)
		{for(int i=1;i<=size()-1;i++)
			{
			s+=","+stack_arr[i];
			}}
		return s+"]";
	}
	public void print_string()
	{
		for(int i=0;i<=top;i++)
		{
			System.out.print(stack_arr[i]);
			System.out.print("");
		}
		System.out.println("");
	}
	
	public static void main(String[] args) throws Exception {
		
		Array_stacks a1 = new Array_stacks();
		System.out.println("...........stacks implementation.....");
		System.out.println("is empty.."+a1.isEmpty());
		//System.out.println(a1.pop());
		a1.push(0);
		a1.push(1);
		a1.push(2);
		a1.push(3);
		a1.push(4);
		a1.print_string();
		System.out.println(a1.pop());
		System.out.println(a1.pop());
		System.out.println(a1.pop());
		System.out.println(a1.pop());
		System.out.println(a1.pop());
		//System.out.println(a1.pop());
		
		
		
		
		
	}

}
