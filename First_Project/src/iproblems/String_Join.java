package iproblems;

public class String_Join {
	//to check whether string is empty or not
	public int check_string(String s)
	{
		if(s!=null && s.length()!=0)
		{
			return 1;
		}
		else return 0;
	}

	public String JoinWithAnd(String a, String b, String c, String d, String e)
	{
		
		int counter= 0;String result = "";
		counter= counter+check_string(a);
		counter= counter+check_string(b);
		counter= counter+check_string(c);
		counter= counter+check_string(d);
		counter= counter+check_string(e);
		switch(counter){
		case 0:
			result="";
			break;
		case 1:
			if(check_string(a)==1){result=a;break;}
			else if(check_string(b)==1){result=b;break;}
			else if(check_string(c)==1){result=c;break;}
			else if(check_string(d)==1){result=d;break;}
			else if(check_string(e)==1){result=e;break;}
		default:
			if(check_string(a)==1){result=a;};
			if(check_string(b)==1){result=result+" AND "+b;}
			if(check_string(c)==1){result=result+" AND "+c;}
			if(check_string(d)==1){result=result+" AND "+d;}
			if(check_string(e)==1){result=result+" AND "+e;}
			break;
		}
		
		return result;
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String_Join s1 = new String_Join();
String sout1=s1.JoinWithAnd(null,null,null,null,null);
System.out.println(sout1);

String_Join s2 = new String_Join();
String sout2=s2.JoinWithAnd(null,null,"C",null,null);
System.out.println(sout2);

String_Join s3= new String_Join();
String sout3=s3.JoinWithAnd("A",null,"C","D",null);
System.out.println(sout3);

	}

}
