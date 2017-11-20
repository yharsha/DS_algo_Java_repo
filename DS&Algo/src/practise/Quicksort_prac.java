package practise;

import java.util.Scanner;

public class Quicksort_prac {

	   public static int[] quickSort(int arr[],int start,int end)
	    {
	        /*1.select the end element as pivot element.
	        2.all elements ls than pivot shouldbe at left and viceversa
	        3.recursively go for both the parts
	        adv:space is o(n)..tc:o(nlog n)..worst o(n2)
	        */
	        if(start>=end)
	        {
	            return arr;
	        }
	        
	        int pivot = arr[end];
	        int index=start;
	        
	        for(int i=start;i<end;i++)
	        {
	            if(arr[i]<pivot)
	            {
	                if(i!=index)
	                {
	                int temp=arr[i];
	                arr[i]=arr[index];
	                arr[index]=temp;
	                }
	                index++;
	            }
	        }
	        int temp=pivot;
	        arr[end]=arr[index];
	        arr[index]=temp;
	        //recursively apply qp for other two halfs
	        quickSort(arr,start,index-1);
	        quickSort(arr,index+1,end);
	        return arr;
	    }
	    public static void main(String args[] ) throws Exception {
	        /*
	         * Read input from stdin and provide input before running
	         * Use either of these methods for input

	        //BufferedReader
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String line = br.readLine();
	        int N = Integer.parseInt(line);

	        //Scanner
	        */
	        Scanner s = new Scanner(System.in);
	        int N = s.nextInt();
	        int arr[] = new int[N];
	        for (int i = 0; i < N; i++) {
	           arr[i]=s.nextInt();
	        }
	        
	        quickSort(arr,0,N-1);
	               for (int j = 0; j < N; j++) {
	           System.out.print(arr[j]+" ");
	        }
	        
	        
	    }

}
