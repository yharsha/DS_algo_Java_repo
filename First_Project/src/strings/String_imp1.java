package strings;
//string is an object that represents a sequence of characters

public class String_imp1 {
	public static void main(String[] args) {
		//There are two ways to create String object: 1.By string literal 2.By new keyword
		
		String s1= "Harsha";
		char ch1[] ={'v','a','r'};
		String s2 = new String(ch1);
		String s3 = new String("Reddy");
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		//The java String is immutable i.e. it cannot be changed. Whenever we change any string, a new instance is created.
		System.out.println(Integer.toHexString(s1.hashCode()));
		s1="awesome";
		System.out.println(Integer.toHexString(s1.hashCode()));
		
		//charAt() method returns a char value at the given index number.
		System.out.println("...........charAt() method .............");
		char ch=s3.charAt(4);
		System.out.println(ch);
		
		//string compareTo() method compares the given string with current string lexicographically.returns g+ve,l-ve or eqzero.
		System.out.println("...........compareTo() method....................");
		String s4 = new String("awesome");
		System.out.println(s1.compareTo(s4));
		s4="Awesome";
		System.out.println(s1.compareTo(s4));
		//ascii value of A = 65 ,a=97
		System.out.println(s4.compareTo(s1));
		
		//string concat() method combines specified string at the end of this string
		System.out.println("........concat() method.............");
		s3=s3.concat(" Superr...!!");
		System.out.println(s3);
		
		// contains() method searches the sequence of characters in this string.Returns T or F
		System.out.println("......contains() method.........");
		String text="Hello..!! Boss how are you?";
		System.out.println(text.contains("Hello"));
		System.out.println(text.contains("Wow"));
		System.out.println(text.contains("how are you?"));
		System.out.println(text.contains("! ?"));
		System.out.println(text.contains("Boss"));
		
		//string endsWith() method checks if this string ends with given suffix.Returns T or F
		System.out.println("........endsWith() method.........");
		System.out.println(text.endsWith("?"));
		System.out.println(text.endsWith("are you?"));
		
		// equals() method compares the two given strings based on the content of the string.
		System.out.println(".....equals method..........");
		System.out.println(s3.equals(s4));
		System.out.println(s3.equals(s3));
		
		//equalsIgnoreCase() method compares the two given strings on the basis of content of the string irrespective of case of the string
		System.out.println("...........equalsIgnoreCase() method ...........");
		String t1 = "javatpoint has good learning stuff";
		String t2 = "JAVATPOINT has good learning stuff";
		String t3 ="javaTpoint has good learning stuff";
		System.out.println(t1.equalsIgnoreCase(t2));
		System.out.println(t2.equalsIgnoreCase(t3));
		
		//string format() method returns the formatted string by given locale, format and arguments
		System.out.println("..........format() method..........");
		String sf1 = String.format("name: %s",t1);
		System.out.println(sf1);
		String sf2 = String.format("%f", 31.1);
		System.out.println(sf2);
		String sf3 = String.format("%12.12f",13.435);//returns 12 char fractional part filling with 0 
		System.out.println(sf3);
		
		//getBytes() method returns the byte array of the string. In other words, it returns sequence of bytes
		System.out.println("..............getBytes() method....gives ascii value......");
		byte b[] = t1.getBytes();//java uses lower camelcase for methods
		for(int i=0;i<b.length;i++)
		{
		System.out.println(b[i]);
		}
		
		//getChars() method copies the content of this string into specified char array.
		//public void getChars(int srcBeginIndex, int srcEndIndex, char[] destination, int dstBeginIndex)  
		System.out.println(".........getChars.............");
		char []ch3 = new char[10];
		t1.getChars(0,9,ch3,0);
        System.out.println(ch3);  
		
        //indexOf() method returns index of given character value or substring. If it is not found, it returns -1
        //	String t1 = "javatpoint has good learning stuff";
        System.out.println("............indexOf() method........");
        System.out.println(t1.indexOf("a"));
        System.out.println(t1.indexOf('t',0));
        System.out.println(t1.indexOf("has"));
        System.out.println(t1.indexOf("stuff",12));
        
        //String interning is a method of storing only one copy of each distinct string value, which must be immutable.
        System.out.println("...... intern() method.........");
        String ts1=new String("hello");  
        String ts2="hello";  
        String ts3=ts1.intern();//returns string from pool, now it will be same as s2  
        System.out.println(ts1==ts2);//false because reference is different  
        System.out.println(ts2==ts3);//true because reference is same  

        System.out.println(ts1.isEmpty());  
        
        // string join() method returns a string joined with given delimiter. In string join method, delimiter is copied for each elements
        System.out.println(".........join() method..........");
        String js1 = String.join("..*..", "hows"," ur"," code?");
        System.out.println(js1);
        
        //lastIndexOf() method returns last index of the given character value or substring
        System.out.println(".............lastIndexOf()& length of string...........");
        String l1 = "Hello Boss How are you";
        System.out.println(l1.lastIndexOf("H"));
		System.out.println("string len is:"+l1.length());
        
		//string replace() method returns a string replacing all the old char or CharSequence to new char or CharSequence
		System.out.println("....replace() method....");
		String rps1 = l1.replace("o", "A");
		System.out.println(rps1);
		rps1 =rps1.replace("are","were");
		System.out.println(rps1);
		
		//split() method splits this string against given regular expression and returns a char array.
		System.out.println("......split() method........");
		String[] a1 = l1.split(" ");
		for(String i:a1)
		{
			System.out.println(i);
		}
		
		//string startsWith() method checks if this string starts with given prefix. It returns true if this string starts with given prefix 
		System.out.println("...........startsWith()...........");
		System.out.println(l1.startsWith("Hell"));
		
		//string substring() method returns a part of the string.where start index is inclusive and end index is exclusive. 
		System.out.println("...substring() method....");
		System.out.println(l1.substring(0, 11));
		
		
		//toCharArray() method converts this string into character array.
		System.out.println("....//toCharArray() ....");
		char[] ch9 = l1.toCharArray();
		for(int i=0;i<ch9.length;i++)
		{
			System.out.println(ch9[i]);
		}
		
		//toUpperCase() method
		System.out.println(".......toUpperCase() method.......");
		l1=l1.toLowerCase();
		System.out.println(l1);
		l1=l1.toUpperCase();
		System.out.println(l1);
		
		//Trim method
		String tr1= "  yo space Bro ";
		System.out.println(tr1.trim());
		
		//string valueOf() method converts different types of values into string. By the help of string valueOf() method, you can convert int to string, long to string, boolean to string, 
		//character to string, float to string, double to string, object to string and char array to string.
		System.out.println("...........valueOf() method/...........");
		int val=89;
		String v1 = String.valueOf(val);
		System.out.println(v1+77);
		
	}

}
