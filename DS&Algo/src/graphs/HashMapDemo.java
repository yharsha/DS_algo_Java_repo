package graphs;
import java.util.*;
public class HashMapDemo {
	
	public static void main(String[] args)
	{
		HashMap<Integer,String> hmap = new HashMap<Integer,String>();
		hmap.put(1, "File");
		hmap.put(2, "Head");
		hmap.put(3, "Body");
		hmap.put(4, "Footer");
		
		System.out.println(hmap);
		
		//to print values in hashmap
		for(Map.Entry<Integer, String> m:hmap.entrySet())
		{
			System.out.println(m.getKey() +":"+m.getValue());
		}
	}

	
	
		
	
}
