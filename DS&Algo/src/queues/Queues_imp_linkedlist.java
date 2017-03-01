package queues;

//insert at rear...dequeue from head(front)
public class Queues_imp_linkedlist {
	Node_q front, rear;
	int size_lq;

	class Node_q {
		int data;
		Node_q next;

		public void set_node_q(int data, Node_q next) {
			this.data = data;
			this.next = next;
		}
	}

	public void enq_listq(int data) {
		Node_q newnode = new Node_q();
		if (size_lq == 0) {
			front = newnode;
		}
		newnode.set_node_q(data, null);
		if (rear != null) {
			rear.next = newnode;
		}
		rear = newnode;
		size_lq++;
	}

	public int deq_listq() {
		if (size_lq == 0) {
			System.out.println("q is empty");
			return Integer.MIN_VALUE;
		}
		int val = front.data;
		Node_q temp = front;
		front = front.next;
		temp.next = null;
		size_lq--;
		return val;
	}

	public boolean isEmpty() {
		return (size_lq == 0);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Queues_imp_linkedlist l1 = new Queues_imp_linkedlist();
System.out.println(l1.isEmpty());
for(int i =0;i<5;i++)
{
	l1.enq_listq(i);
	}
for(int i =0;i<5;i++)
{
	System.out.print(l1.deq_listq());
	System.out.print(" ");
	}
	}

}
