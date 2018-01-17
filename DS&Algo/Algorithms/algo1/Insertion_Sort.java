package algo1;

public class Insertion_Sort {

	public int[] insertn_sort(int arr[]) {

		for (int i = 1; i < arr.length; i++) {
			int j = i;
			while (arr[j] < arr[j - 1]) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
				j--;
				if (j < 1) {
					break;
				}
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		int A[] = { 5, 1, 6, 2,4, 3 };
		int b[] = new Insertion_Sort().insertn_sort(A);
		int i = 0;
		System.out.println(".................Insertion Sort...........");
		while (i < b.length) {
			System.out.print(b[i]);
			System.out.print(" ");
			i++;

		}
	}

}
