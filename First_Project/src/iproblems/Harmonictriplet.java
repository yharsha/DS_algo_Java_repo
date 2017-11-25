package iproblems;
import java.util.*;

public class Harmonictriplet {

    public static int leftMax(int arr[],int index)
    {
        int value=-1;
        for(int i=0;i<index;i++)
        {
            if(arr[i]<= arr[index] && value<arr[i])
            {
                value=arr[i];
            }
        }
        return value;
    }
        public static int rightMax(int arr[],int index)
    {
        int value=-1;
        for(int i=index+1;i<arr.length;i++)
        {
            if(arr[i]<= arr[index] && value<arr[i])
            {
                value=arr[i];
            }
        }
        return value;
    }
    public static void harmonicTriplet(int [] arr,int index)
    {
        if(arr.length<3){System.out.println(0);return;}
        // System.out.println("Entered");
            int leftValue = leftMax(arr,index);
            int rightValue = rightMax(arr,index);
            System.out.println("leftmax"+leftValue);
            System.out.println("IndexValue"+arr[index]);
            System.out.println("rightMax"+rightValue);
            if(leftValue== -1 || rightValue == -1)
            {
                 System.out.println(0);
            }
            else
            {
            	long total=(leftValue*arr[index]);
            			total*=rightValue;
                 System.out.println(total);
            }
       
    }
     public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        // Write your code here
        int testCases = s.nextInt();
    for(int t=0;t<testCases;t++)
    {
        int n  = s.nextInt();
        int queries = s.nextInt();
        int arr[] = new int[n];
        int que[]= new int[queries];
        for(int i=0;i<n;i++)
        {
            arr[i]  = s.nextInt();
        }
        System.out.println("t");
        for(int j=0;j<queries;j++)
        {
            int index  = s.nextInt();
        harmonicTriplet(arr,index-1);
        }
    
     }

    }

}
