package linkedlist;

public class LinkedList {

	public Node head;

	public static class Node {
		public int data;
		public Node next;

		public Node(int d) {
			this.data = d;
		}
		public Node() {
		}
		
		public int getData(Node temp) {
			if (temp != null) {
				return temp.data;
			} else
				return Integer.MIN_VALUE;
		}

	}

	public Node getHead() {
		return head;
	}

	public int getData(Node temp) {
		if (temp != null) {
			return temp.data;
		} else
			return Integer.MIN_VALUE;
	}

	// insert node at beginning
	public void insertAtBegin(Node a) {

		a.next = head;
		head = a;
	}

	// insert node at last
	public void insertAtEnd(Node a) {
		if (head == null) {
			head = a;
		} else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = a;
			a.next = null;
		}
	}

	// Inserting a new value at given position
	public void insertAtPos(Node a, int x) {
		if (x < 1) {
			x = 1;
			insertAtBegin(a);
		} else if (x > lengthOfList()) {
			insertAtEnd(a);
		} else {
			Node temp1 = head;
			int length = 1;
			while (length < x - 1 && temp1.next != null) {
				temp1 = temp1.next;
				length++;
			}
			Node temp2 = temp1.next;
			temp1.next = a;
			a.next = temp2;
		}
	}
	// ******************************************************************************//
	// **************Deleting
	// Nodes**************************************************//
	// ******************************************************************************//

	// Delete a Node at beginning
	public void deleteAtBegin() {
		if (head != null) {
			Node temp = head;
			head = head.next;
			temp.next = null;
		}
	}

	// delete a node at end
	public void deleteAtend() {
		if (head == null)
			return;
		if (head.next == null) {
			head = null;
			return;
		}
		;
		Node temp = head;
		Node tempprev = null;
		while (temp.next != null) {
			tempprev = temp;
			temp = temp.next;
		}
		tempprev.next = null;
		temp = null;
	}

	// delete on obj/node match
	public void deleteOnMatched(Node a) {
		if (head == null)
			return;
		if (head == a) {
			if (a.next == null) {
				head = null;
				return;
			} else {
				head = head.next;
				a.next = null;
			}
		}
		Node temp = head;
		Node temp1 = null;
		while (temp.next != null) {
			if (a == temp) {
				temp = temp.next;
				temp1.next = temp;
				a.next = null;
				return;
			}
			temp1 = temp;
			temp = temp.next;
		}
		if (a == temp) {
			temp1.next = null;
			a.next = null;
			return;
		}
	}

	// delete at position
	public void deleteAtposition(int x) {
		if (head == null)
			return;
		if (lengthOfList() == 1) {
			head = null;
			return;
		}
		Node temp = null;
		if (x <= 1) {
			x = 1;
			temp = head;
			head = head.next;
			temp.next = null;
			return;
		} else if (x >= lengthOfList()) {
			x = lengthOfList();
		}

		temp = head;
		int len = 1;
		Node temp1 = null, temp2 = null;
		while (len < x && temp.next != null) {
			temp1 = temp;
			temp = temp.next;
			len++;
		}
		temp1.next = temp.next;
		temp2 = temp;
		temp = temp.next;
		temp2.next = null;

	}

	// string representation
	// print linked list...traversing
	public void print_list() {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.data + " -> ");
			temp = temp.next;
		}
		System.out.println("");

	}

	// to get length of list
	public int lengthOfList() {
		if (head == null) {
			return 0;
		} else {
			int len = 1;
			Node temp = head;
			while (temp.next != null) {
				temp = temp.next;
				len++;
			}
			return len;
		}
	}

	// **************************************
	// reverese a llist
	public void rev_list() {
		if (head == null) {
			return;
		}
		;
		Node prev = null;
		Node current = head;
		Node next = current.next;
		while (current.next != null) {
			current.next = prev;
			prev = current;
			current = next;
			next = next.next;
		}
		current.next = prev;
		head = current;

	}

	// reverse in pairs
	public void reverse_in_pairs() {
		if (head == null) {
			return;
		}
		Node temp1 = null;
		Node temp2 = head;
		Node temp3 = head.next;
		while (temp2.next != null) {
			// realign
			if (temp1 != null) {
				temp1.next = temp3;
			}
			temp2.next = temp3.next;
			temp3.next = temp2;
			if (temp1 == null) {
				head = temp3;
			}
			// shifting
			if (temp2.next != null) {
				temp3 = temp2.next.next;
				temp1 = temp2;
				temp2 = temp2.next;
			}
		}
	}

	// find position at value
	public int positionOfValue(int val) {
		if (head == null) {
			return -1;
		}

		int pos = 1;
		Node temp = head;
		while (temp != null) {
			if (temp.data == val) {
				return pos;
			}
			temp = temp.next;
			pos++;
		}
		return -1;
	}

	// string output of linked list
	public String outputAsString() {
		if (head == null) {
			return "|";
		}
		String result = "[";
		Node temp = head;
		while (temp != null) {
			result = result + "," + temp.data;
			temp = temp.next;
		}
		return result + "]";

	}

	public static void main(String[] args) {

		LinkedList l1 = new LinkedList();
		Node a1 = new Node(1);
		Node a2 = new Node(2);
		Node a3 = new Node(3);
		Node a4 = new Node(4);

		l1.head = a1;
		a1.next = a2;
		a2.next = a3;
		a3.next = a4;
		a4.next = null;

		Node a0 = new Node(0);
		l1.insertAtBegin(a0);
		l1.print_list();
		System.out.println("Len:" + l1.lengthOfList());

		Node a5 = new Node(5);
		l1.insertAtEnd(a5);
		l1.print_list();
		System.out.println("Len:" + l1.lengthOfList());

		Node a6 = new Node(6);
		l1.insertAtPos(a6, 5);
		l1.print_list();
		System.out.println("Len:" + l1.lengthOfList());

		Node a7 = new Node(7);
		l1.insertAtPos(a7, -2);
		l1.print_list();
		System.out.println("Len:" + l1.lengthOfList());

		Node a8 = new Node(8);
		l1.insertAtPos(a8, 11);
		l1.print_list();
		System.out.println("Len:" + l1.lengthOfList());

		l1.deleteAtBegin();
		System.out.println("Del at begin");
		l1.print_list();
		System.out.println("Len:" + l1.lengthOfList());

		l1.deleteAtend();
		System.out.println("Del at End");
		l1.print_list();
		System.out.println("Len:" + l1.lengthOfList());

		l1.deleteOnMatched(a3);
		System.out.println(".........Del on object matched.......");
		l1.print_list();
		System.out.println("Len:" + l1.lengthOfList());

		// l1.deleteOnMatched(a0);
		// l1.deleteOnMatched(a6);
		// System.out.println(".........Del on object matched.......");
		// l1.print_list();
		// System.out.println("Len:" + l1.lengthOfList());

		l1.deleteAtposition(3);
		System.out.println(".........Del at position.......");
		l1.print_list();
		System.out.println("Len:" + l1.lengthOfList());
		// l1.deleteAtposition(7);
		//
		// System.out.println(".........Del at position.......");
		// l1.print_list();
		// System.out.println("Len:" + l1.lengthOfList());
		//
		// l1.deleteAtposition(-2);
		// System.out.println(".........Del at position.......");
		// l1.print_list();
		// System.out.println("Len:" + l1.lengthOfList());

		System.out.println("String Format:" + l1.outputAsString());

		System.out.println("position of 0 is :" + l1.positionOfValue(0));
		System.out.println("position of 1 is :" + l1.positionOfValue(1));
		System.out.println("position of 4 is :" + l1.positionOfValue(4));
		System.out.println("position of 6 is :" + l1.positionOfValue(6));
		System.out.println("position of 5 is :" + l1.positionOfValue(5));
		System.out.println("position of 12 is :" + l1.positionOfValue(12));

		System.out.println(".........Normal list.......");
		l1.print_list();
		System.out.println(".........Reversed list.......");
		l1.rev_list();
		l1.print_list();
		System.out.println(".........Reversed list in pairs of 2.......");
		l1.reverse_in_pairs();
		l1.print_list();

	}

}
