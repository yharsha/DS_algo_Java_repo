package algo1;
//import java.util.*;

public class Selection_sort {
	
	public int min(int arr[],int li,int hi)
	{
		int min=arr[li],index=li;
		for(int i=li;i<=hi;i++)
		{
			if(arr[i]<min)
			{
				min=arr[i];
				index=i;
			}
		}
		return index;
	}
	public void swap(int a[],int i,int j)
	{
		int temp=a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	public int [] selectn_sort(int arr[],int li,int hi)
	{
		if(li<=hi)
		{
		swap(arr,li,min(arr,li,hi));
		selectn_sort(arr, li+1, hi);
		}
		return arr;
	}
	public static void main(String[] args) {
		int A[]={11,50,40,53,95,02,5,19,14,6,-99};
		int b[]=new Selection_sort().selectn_sort(A, 0, A.length-1);
		for(int k=0;k<b.length;k++)
		{
			System.out.print(b[k]+" ");
			
		}
	}

}
