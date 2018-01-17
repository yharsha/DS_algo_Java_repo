package searching;

public class Search {

	int arr[];

	public int unorderedSearch(int arr[], int sValue) {
		// for list not in sorted array
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == sValue) {
				return i;
			}
		}
		return -1;
	}

	public int orderedSearch(int arr[], int sValue) {
		// list in sorted array
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == sValue) {
				return i;
			} else if (arr[i] > sValue) {
				return -1;
			}
		}
		return -1;
	}
	
	public int binarySearchIterative(int arr[],int sValue,int left,int right)
	{
		int mid ;
		while(left<=right)
		{
			mid = (left + right)/2;
		if(arr[mid]==sValue)
		{
			return mid;
		}
		else if(sValue> arr[mid])
		{
			left = mid+1;
		}
		else {right=mid-1;}
		}
		return -1;
	}
	
	public int binarySearchrecursive(int arr[],int sValue,int left,int right)
	{
		if(left>right){return -1;}
		int mid = (left+right)/2;
		if(arr[mid]==sValue){return mid;}
		else if(sValue>arr[mid]){return binarySearchrecursive(arr, sValue, mid+1, right);}
		else {return binarySearchrecursive(arr, sValue, left,mid-1);}
		
		}

	public static void main(String[] args) {
		Search s1 = new Search();
		s1.arr = new int[] { 34, 12, -199, 87, -789 };
		System.out.println("unorderedSearch");
		System.out.println("index:" + s1.unorderedSearch(s1.arr, -199));
		Search s2 = new Search();
		s2.arr = new int[] { 34, 42, 99, 178, 189,333,486,567,689,734,876,968,999 };
		System.out.println("orderedSearch");
		System.out.println("index:" + s1.orderedSearch(s1.arr, -199));
		System.out.println("BinarySearch......iterative");
		System.out.println("index:"+s2.binarySearchIterative(s2.arr, 689, 0, s2.arr.length-1));
		System.out.println("BinarySearch......iterative");
		System.out.println("index:"+s2.binarySearchrecursive(s2.arr, 689, 0, s2.arr.length-1));
	
	}

}
