package algo1;

public class Quick_sort {
	
	
	public void quick_sort(int a[],int start,int end)
	{
		if(start>=end)return;
		int pivot=a[end],index=start;
		for(int i=start;i<=end;i++)
		{
			if(a[i]<pivot && index!=i)
			{
				int temp=a[index];
				a[index]=a[i];
				a[i]=temp;
				index++;
			}
		}
		int temp=a[index];
		a[index]=pivot;
		a[end]=temp;
		quick_sort(a, start,index-1 );
		quick_sort(a, index+1, end);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = {10,80,30,90,40,50,70};
		System.out.println("......Merge_Sort.........");
		new Quick_sort().quick_sort(a, 0, a.length-1);
		for(int k=0;k<a.length;k++)
		{
			System.out.print(a[k]);
			System.out.print(" ");
		}
		}
	

}
