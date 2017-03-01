package queues;

public class Queue_imp_array {
	int arr[] = new int[10];
	int capacity = arr.length;
	int front = 0, rear = 0, size_q = 0;

	public void que_enqueue(int data) {
		if (size_q == arr.length) {
			System.out.println("Queue is full");
			return;
		}
		size_q++;
		arr[rear] = data;
		rear = (rear + 1) % capacity;
	}

	public int que_deenqueue() {
		if (size_q == 0) {
			System.out.println("Queue is Empty");
			return Integer.MIN_VALUE;
		}
		size_q--;
		int temp=arr[front];
		arr[front] = Integer.MIN_VALUE;
		front = (front + 1) % capacity;
		return temp;
	}

	public boolean isEmpty_q() {
		return (size_q == 0);
	}

	public boolean isFull() {
		return (size_q == capacity);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue_imp_array q1 = new Queue_imp_array();
		System.out.println("empty?:" + q1.isEmpty_q());
		System.out.println("Full?:" + q1.isFull());
		for(int i=0;i<11;i++)
		{
			q1.que_enqueue(i);
		}
		for(int i=0;i<11;i++)
		{
			System.out.print(q1.que_deenqueue());
			System.out.print("");
		}
	}

}
