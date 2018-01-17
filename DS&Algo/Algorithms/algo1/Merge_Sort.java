package algo1;

public class Merge_Sort {
	
	public void merge_sorting(int a[],int li,int hi)
	{
		if(li==hi)
		{
			return;
		}
		int mid=(li+hi)/2;
		
		merge_sorting(a, li, mid);
		merge_sorting(a,mid+1,hi);
		merge(a,li,mid,hi);
	}
	
	public void merge(int a[],int li,int mid,int hi)
	{
		int l_index=li,r_index=mid+1,i=li;
		int b[]=new int[hi+1];
while(l_index<=mid&&r_index<=hi)
{
			if(a[l_index]>a[r_index])
			{
				b[i]=a[r_index];
				r_index++;
				i++;
			}
			else if(a[l_index]<a[r_index])
			{
				b[i]=a[l_index];
				l_index++;
				i++;
			}
}
/* Copy remaining elements of L[] if any */
while(l_index<=mid)
{
	b[i]=a[l_index];
	l_index++;
	i++;
}
/* Copy remaining elements of L[] if any */
while(r_index<=hi)
{
	b[i]=a[r_index];
	r_index++;
	i++;
}
		for(int k=li;k<=hi;k++)
		{
			a[k]=b[k];
		}
	}
	

	public static void main(String[] args) {
		int a[] = {38,27,43,3,9,82,10};
		System.out.println("......Merge_Sort.........");
		new Merge_Sort().merge_sorting(a, 0, a.length-1);
		for(int k=0;k<a.length;k++)
		{
			System.out.print(a[k]);
			System.out.print(" ");
		}
		}
}
