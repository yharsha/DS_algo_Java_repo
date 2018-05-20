package trees;

public class MinHeap {

	int arr[];
	int count;
	int capacity = 0;

	MinHeap(int max_size) {
		this.capacity = max_size;
		this.count = 0;
		arr = new int[max_size];
	}

	public int getParent(int i) {
		if (i <= 0 || i > count)
			return -1;
		return (i - 1) / 2;
	}

	public int getLeftChild(int i) {
		int left = (2 * i + 1);
		if (left >= this.count)
			return -1;
		return left;
	}

	public int getRightChild(int i) {
		int right = (2 * i + 2);
		if (right >= this.count)
			return -1;
		return right;
	}

	/**
	 * heapyfying - to maintain heap property heap property: root element should be
	 * =< or >= its childs
	 **/
	public void percolateDown(int i) {
		int l, r, min, temp;// min to store index
		l = getLeftChild(i);
		r = getRightChild(i);
		if (l != -1 && this.arr[l] < arr[i])
			min = l;
		else
			min = i;

		if (r != -1 && arr[r] < arr[min])
			min = r;
		// if min is one of childs swap it and continue down
		if (min != i) {
			temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
			percolateDown(min);
		}
	}

	/*
	 * 1.We insert at the rightmost spot so as to maintain the complete tree
	 * property. 2.we "fix"the tree by swapping the new element with its parent,
	 * until we find an appropriate spot for the element
	 */
	public void insert(int data) {
		if (this.count == this.capacity) {
			System.out.println("Heap is Full");
			return;
		}

		int i = count;// last pos
		arr[i] = data;
		count++;
		// percolate up to maintain heap property
		while (i >= 0 && data < arr[(i - 1) / 2]) {
			arr[i] = arr[(i - 1) / 2];
			arr[(i - 1) / 2] = data;
			i = getParent(i);
		}

	}

	/* 1.copy the last element to root and percolatedown from root */
	public int ExtractMin() {
		if (count == 0)
			return -1;
		int data = arr[0];
		arr[0] = arr[count - 1];
		count--;
		percolateDown(0);
		return data;
	}

	public void printHeap() {
		for (int i = 0; i < count; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("------------");
	}

	public static void main(String[] args) {
		MinHeap h = new MinHeap(20);
		h.insert(3);
		h.insert(2);
		h.insert(1);
		h.insert(15);
		h.insert(5);
		h.insert(4);
		h.insert(45);
		h.insert(0);
		h.printHeap();
		System.out.println("Removing the min value :" + h.ExtractMin());
		h.printHeap();
	}

}
