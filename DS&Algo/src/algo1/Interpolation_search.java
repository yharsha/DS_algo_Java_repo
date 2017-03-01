package algo1;

import java.util.Scanner;

//The idea of formula is to return higher value of pos when element to be searched is closer to arr[hi]. And
//smaller value when closer to arr[lo]
//.....pos = lo + [ (x-arr[lo])*(hi-lo) / (arr[hi]-arr[Lo]) ]
//

public class Interpolation_search {
	
	public int interpol_srch(int arr[],int li,int hi,int x)
	{
		int pos;
		while(li<=hi && x>=arr[li] && x<=arr[hi] )
		{
			 pos = li+((x-arr[li])*(hi-li)/(arr[hi]-arr[li]));
			 if(arr[pos]==x)
			 {
				 System.out.print("element found at index a["+pos+"]="+arr[pos]);
				 System.out.println("");
				 return pos;
			 }
			 else if(x<arr[pos])
			 {
				 // If x is smaller, x is in lower part
				 hi=pos-1;
			 }
			 else 
				 li=pos+1;
			
		}
		return -1;
		
	}
	
	public static void main(String[] args) {
		
		 int arr[] =  {10, 12, 13, 16, 18, 19, 20, 21, 22, 23,
                 24, 33, 35, 42, 47};
	
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the value");
			int val = sc.nextInt();
			int flag=new Interpolation_search().interpol_srch(arr,0,arr.length-1,val);
			if(flag==-1)
				System.out.println("element not found");
			
			sc.close();
	
	
	}

}
