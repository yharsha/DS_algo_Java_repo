package sorting;

public class Sort {
	private int arr[];

	public Sort(int d) {
		arr = new int[d];
	}

	public int[] BubbleSort(int arr[]) {
		// tc:o(n2)...improved best o(n)
		int temp, swapped = 1;
		// 1.no of passes 2.for each pass scan through all elemnts-pass {do
		// swapping if bigger}
		for (int pass = arr.length - 1; pass >= 0 && swapped == 1; pass--) {
			swapped = 0;
			for (int i = 0; i <= pass - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
					swapped = 1;
				}
			}
		}
		return arr;
	}

	public int[] SelectionSort(int arr[]) {
		int min, temp;
		// index
		for (int i = 0; i < arr.length; i++) {
			// to find min of rem elements
			min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
		return arr;
	}

	public int[] InsertionSort(int arr[]) {
		int j, value;
		// every iteration insert the element in correction position
		for (int i = 1; i < arr.length; i++) {
			value = arr[i];
			// insert the value in sorted array i.e...arr[o to i-1]
			j = i;
			while (j >= 1 && arr[j - 1] > value) {
				arr[j] = arr[j - 1];
				j--;
			}
			// since j is already reduced by one
			arr[j] = value;
		}
		return arr;
	}

	public int[] ShellSort(int arr[]) {
		// extension of Insertion sort
		int value, j;
		for (int gap = arr.length / 2; gap >= 1; gap = gap / 2) {
			for (int i = gap; i < arr.length; i++) {
				value = arr[i];
				j = i;
				while ((j - gap) >= 0 && arr[j - gap] > value) {
					arr[j] = arr[j - gap];
					j = j - gap;
				}
				arr[j] = value;
			}
		}
		return arr;
	}

	public void mergeSort(int arr[], int left, int right) {
		if (left >= right) {
			return;
		}
		int mid = (left + right) / 2;
		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);
		merge(arr, left, mid, right);
	}

	public void merge(int[] arr, int left, int mid, int right) {
		if (left == right) {
			return;
		}
		int l1 = left;
		int l2 = mid + 1;
		int temp[] = new int[arr.length];
		int temp_index = left;
		while (l1 <= mid && l2 <= right) {
			if (arr[l1] < arr[l2]) {
				temp[temp_index] = arr[l1];
				temp_index++;
				l1++;
			} else {
				temp[temp_index] = arr[l2];
				temp_index++;
				l2++;
			}
		}
		// remaining elements
		while (l1 <= mid) {
			temp[temp_index] = arr[l1];
			temp_index++;
			l1++;
		}
		while (l2 <= right) {
			temp[temp_index] = arr[l2];
			temp_index++;
			l2++;
		}
		// copying sorted array
		for (int i = left; i <= right; i++) {
			arr[i] = temp[i];
		}
		temp = null;
	}

	public void quickSort(int arr[], int left, int right) {

		if (left >= right) {
			return;
		}
		int pivot_index = partition(arr, left, right);
		quickSort(arr, left, pivot_index - 1);
		quickSort(arr, pivot_index + 1, right);
	}

	public int partition(int arr[], int left, int right) {
		// selecting the pivot and elements than
		// pivot are to the left and similarly to right
		int pindex;
		pindex = left + 1;
		int pivot_value = arr[left];
		for (int i = left + 1; i <= right; i++) {
			if (arr[i] <= pivot_value) {
				swap(arr, i, pindex);
				pindex++;
			}
		}
		swap(arr, left, pindex - 1);

		return pindex - 1;
	}

	public void swap(int arr[], int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	public void print_array(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ":");
		}
		System.out.println(" ");
		System.out.println("##################");
	}

	public static void main(String[] args) {
		Sort d1 = new Sort(5);
		d1.arr = new int[] { 12, 3, 45, 77, 2 };
		System.out.println("Bubble Sort...:");
		d1.BubbleSort(d1.arr);
		d1.print_array(d1.arr);

		Sort d2 = new Sort(7);
		d2.arr = new int[] { 987, 23, 66, -3, 33, 11, 222 };
		System.out.println("Selection sort");
		d2.SelectionSort(d2.arr);
		d2.print_array(d2.arr);

		Sort d3 = new Sort(8);
		d3.arr = new int[] { 6, 8, 1, 4, 5, 3, 7, 2 };
		System.out.println("Insertion Sort");
		d3.InsertionSort(d3.arr);
		d3.print_array(d3.arr);

		Sort d4 = new Sort(20);
		d4.arr = new int[] { 9, 4, 1, 2, 5, 6, 8, 16, 7, 3, 12, 14, 15, 18, 19, 10, 11, 13, 17, 20 };
		d4.ShellSort(d4.arr);
		System.out.println("Shell Sort");
		d4.print_array(d4.arr);

		Sort d5 = new Sort(8);
		d5.arr = new int[] { 2, 4, 1, 6, 8, 5, 3, 7 };
		d5.mergeSort(d5.arr, 0, d5.arr.length - 1);
		System.out.println("Merge Sort:");
		d5.print_array(d5.arr);

		Sort d6 = new Sort(7);
		d6.arr = new int[] { 10, 80, 30, 90, 40, 50, 70 };
		d6.quickSort(d6.arr, 0, d6.arr.length - 1);
		System.out.println("Quick Sort:");
		d6.print_array(d6.arr);

	}

}
