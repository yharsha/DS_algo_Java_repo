package stacks;

public class Linked_stacks {
	int length_s;
	Node top = null;

	class Node {
		int data;
		Node next;

		public void set_node_s(int data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	public void push_s(int data) {

		Node newnode = new Node();
		newnode.set_node_s(data, top);
		top = newnode;
		length_s++;
	}

	public void pop_s() {
		if (isEmpty_s()) {
			System.out.println("Stack is empty");
			return;
		}
		Node temp = top;
		top = top.next;
		temp.next = null;
		length_s--;
	}

	public boolean isEmpty_s() {
		if (top == null) {
			return true;
		} else
			return false;
	}

	public int top_value_s() {
		if (top != null) {
			return top.data;
		} else
			return Integer.MIN_VALUE;
	}

	public void print_stack_l() {
		if (top == null) {
			System.out.println("stack empty...");
			return;
		}
		Node temp = top;
		while (temp != null) {
			System.out.print(temp.data);
			System.out.print("");
			temp = temp.next;
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		Linked_stacks l1 = new Linked_stacks();
		l1.pop_s();
		System.out.println(l1.isEmpty_s());
		l1.push_s(0);
		l1.push_s(1);
		l1.push_s(2);
		l1.push_s(4);
		l1.push_s(6);
		l1.print_stack_l();
		l1.pop_s();
		l1.pop_s();
		l1.pop_s();
		l1.print_stack_l();
	}

}
