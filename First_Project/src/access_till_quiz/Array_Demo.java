package access_till_quiz;

public class Array_Demo {

	void min(int a[]) {
		int min = a[0];
		for (int i = 0; i < a.length; i++) {
			if (min > a[i]) {
				min = a[i];
			}
		}
		System.out.println("min value:" + min);
	}

	void multi(int arr[][]) {
		for (int i = 0; i < 3; i++) {
			System.out.println();
			for (int j = 0; j < 3; j++)
				System.out.print(arr[i][j] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Array_Demo a1 = new Array_Demo();
		int arr[] = { 23, 12, 456, 78, 90, 45, 2, 3, 31 };
		a1.min(arr);
		int marray[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		a1.multi(marray);
		
		//In java, array is an object. For array object, an proxy class is created whose name can be obtained by getClass().getName() method on the object.
		Class c1 = arr.getClass();
		String name1 = c1.getName();
		System.out.println("name: "+name1);
		Class c2 = arr.getClass();
		String name2 = c2.getName();
		System.out.println("name: "+name2);
		
		//Example of arraycopy method
        char[] copyFrom = { 'd', 'e', 'c', 'a', 'f', 'f', 'e',  
                'i', 'n', 'a', 't', 'e', 'd' };  
        char[] copyTo = new char[7];  
  
        System.arraycopy(copyFrom, 2, copyTo, 0, 7);  
        System.out.println(new String(copyTo));  
	}
}
