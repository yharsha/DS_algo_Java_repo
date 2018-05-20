package linked_lists;

import java.util.HashSet;

public class Linkedlist {
	Node head;

	static class Node {
		int data;
		Node next;

		Node(int d) {
			this.data = d;
			this.next = null;
		}
	}

	/* Write code to remove duplicates from an unsorted linked list..o(l) */

	public static void deleteDuplicates(Node head) {
		Node prev = null, node = head;
		HashSet<Integer> hs = new HashSet<Integer>();
		while (node != null) {
			if (hs.contains(node.data)) {
				prev.next = node.next;
			} else {
				hs.add(node.data);
				prev = node;
			}
			node = node.next;
		}
		printList(head);
	}

	/* Function to get the nth node from end of list */
	public static void printNthFromLast(Node head, int n) {
		if (head == null)
			return;
		Node ref_ptr = head, main_ptr = head;
		int count = 1;
		while (count < n && ref_ptr != null) {
			ref_ptr = ref_ptr.next;
			count++;
		}

		while (ref_ptr.next != null) {
			ref_ptr = ref_ptr.next;
			main_ptr = main_ptr.next;
		}
		System.out.println("Node from end" + main_ptr.data);
	}

	/*
	 * Partition: Write code to partition a linked list around a value x, such that
	 * all nodes less than x come before all nodes greater than or equal to x. If x
	 * is contained within the list, the values of x only need to be after the
	 * elements less than x (see below). The partition element x can appear anywhere
	 * in the "right partition"; it does not need to appear between the left and
	 * right partitions. EXAMPLE Input: Output: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1
	 * [partition= 5] 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
	 */
	public static void partition(Node head, int x) {
		Node curr = head, next = null;
		Node small_head = null, small_tail = null, big_tail = null, big_head = null;
		while (curr != null) {
			next = curr.next;
			if (curr.data < x) {
				if (small_head == null) {
					small_head = curr;
					small_tail = curr;
					small_tail.next = null;
				} else {
					small_tail.next = curr;
					small_tail = curr;
					small_tail.next = null;
				}
			} else {
				if (big_head == null) {
					big_head = curr;
					big_tail = curr;
					big_tail.next = null;
				} else {
					big_tail.next = curr;
					big_tail = curr;
					big_tail.next = null;
				}
			}

			curr = next;
		}
		// merge two lists
		small_tail.next = big_head;
		System.out.println("Partioning of List..!!");
		printList(small_head);
	}

	/*
	 * Sum Lists: You have two numbers represented by a linked list, where each node
	 * contains a single digit. The digits are stored in reverse order, such that
	 * the 1 's digit is at the head of the list. Write a function that adds the two
	 * numbers and returns the sum as a linked list. EXAMPLE Input: (7-> 1 -> 6) +
	 * (5 -> 9 -> 2).That is,617 + 295. Output: 2 -> 1 -> 9. That is, 912.
	 */
	static Node addSumLists(Node l1, Node l2, int carry) {
		if (l1 == null && l2 == null && carry == 0)
			return null;

		Node result = new Node(0);
		int value = carry;
		if (l1 != null)
			value += l1.data;
		if (l2 != null)
			value += l2.data;

		result.data = value % 10;
		// go recursive
		if (l1 != null || l2 != null) {
			Node next = addSumLists(l1 == null ? null : l1.next, l2 == null ? null : l2.next, value >= 10 ? 1 : 0);
			result.next = next;
		}

		return result;
	}

	static void printList(Node node) {
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println("-----------");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Linkedlist list = new Linkedlist();
		list.head = new Node(10);
		list.head.next = new Node(12);
		list.head.next.next = new Node(24);
		list.head.next.next.next = new Node(1);
		list.head.next.next.next.next = new Node(12);
		list.head.next.next.next.next.next = new Node(31);
		list.head.next.next.next.next.next.next = new Node(8);
		deleteDuplicates(list.head);
		printNthFromLast(list.head, 2);
		partition(list.head, 9);
		
		Linkedlist list1 = new Linkedlist();
		list1.head = new Node(7);
		list1.head.next = new Node(1);
		list1.head.next.next = new Node(6);
		
		
		Linkedlist list2 = new Linkedlist();
		list2.head = new Node(5);
		list2.head.next = new Node(9);
		list2.head.next.next = new Node(2);
		
		printList(addSumLists(list1.head, list2.head,0));
	}

}
