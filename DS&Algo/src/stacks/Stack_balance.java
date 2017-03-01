package stacks;

import java.util.Stack;

public class Stack_balance {

	public boolean validSyntaxCheck(String s)
	{
		Stack<Character> stk = new Stack<Character>();
		if(s==null||s.length()==0)
		{
			return true;
		}
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)==')' ){
				if(!stk.isEmpty() && stk.peek()=='(')
				{
					stk.pop();
				}
				else return false;
			}
			else if(s.charAt(i)=='}'){
				if(!stk.isEmpty() && stk.peek()=='{')
				{
					stk.pop();
				}
				else return false;
			}
			else if(s.charAt(i)==']'){
				if(!stk.isEmpty() && stk.peek()=='[')
				{
					stk.pop();
				}
				else return false;
			}
			else {stk.push(s.charAt(i));}
					
		}
		if(stk.isEmpty())
		{
			return true;
		}
		else return false;
	}
	public int evaluatepostfix(String tokens[])
	{
		Stack<Integer> stk = new Stack<Integer>(); 
		int op1,op2,res;
		for(String t:tokens){
			if(t.equals("+"))
			{
				op1=stk.pop();
				op2=stk.pop();
				res=op1+op2;
				stk.push(res);
			}
			else if(t.equals("-"))
			{
				op1=stk.pop();
				op2=stk.pop();
				res=op1-op2;
				stk.push(res);
			}
			else if(t.equals("*"))
			{
				op1=stk.pop();
				op2=stk.pop();
				res=op1*op2;
				stk.push(res);
			}
			else if(t.equals("/"))
			{
				op1=stk.pop();
				op2=stk.pop();
				res=op1/op2;
				stk.push(res);
			}
			else 
				stk.push(Integer.parseInt(t));
		}
		
		return stk.pop();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack_balance st1 = new Stack_balance();
		String S1 ="{({})[]()[]}";
		System.out.println("Syntax is:"+st1.validSyntaxCheck(S1));
		String t="12+34+*";
		String t1[]=t.split("");
	
		System.out.println("Final value of postfix eval is:"+st1.evaluatepostfix(t1));
		

	}

}
