import java.util.*;

public class InsertionSort {
//Insertion sort --Split array into Sorted and Unsorted
//increase the sorted array by inserting in increasing order
	public static void main(String[] args) {
		
		Scanner user_input = new Scanner(System.in);
		System.out.println("Enter total count of integers:");
		int len = user_input.nextInt();
		int a[]=new int[len];
		for(int i=0;i<len;i++)
		{
			System.out.println("Enter integer value:");
			a[i]=user_input.nextInt();
		}
		//************************************************//
		int value=0,sbox=0;//sbox is vaccum  created after copying to temp variable 
	
	
	for(int i=1;i<len;i++)
	{
	value=a[i];
	sbox=i;
	while(sbox>0 && a[sbox-1]>value)
	{
		a[sbox]=a[sbox-1];
		sbox--;
			
		}
	a[sbox]=value;
	}
	// print array
	for (int i = 0; i < a.length; i++) {
		System.out.print(a[i]);
		System.out.print(" ");
	}
		
		
	user_input.close();	
	}

}
