package algo1;

public class Bubble_Sort {
public int[] bubble_sort(int arr[])
{
	int k=0,flag;
	for(int i=0;i<(arr.length);i++)
	{
		flag=0;
		for(int j=0;j<(arr.length-1-k);j++)
		{
			if(arr[j]>arr[j+1])
			{
				int temp=arr[j];
				arr[j]=arr[j+1];
				arr[j+1]=temp;
				flag=1;
			}
			
		}
		if(flag==0)
		{
			break;
		}
		k++;
	}
	return arr; 
}
public static void main(String[] args) {
	int a[] = {5,4,3,2,1};
	System.out.println("......Bubble.........");
	new Bubble_Sort().bubble_sort(a);
	for(int k=0;k<a.length;k++)
	{
		System.out.print(a[k]);
		System.out.print(" ");
	}
}
}
