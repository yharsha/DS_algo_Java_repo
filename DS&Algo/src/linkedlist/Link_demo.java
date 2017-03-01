package linkedlist;

public class Link_demo {

	Node head;//a field to hold address of first node
	static public class Node
	{
		int data;
		Node next;
		Node()
		{
			
		}
		Node(int d)
		{
			data=d;
		}
	}
	
	public void print_data()
	{
		Node current = head;
		while(current!=null)
		{
			System.out.println(current.data);
			current=current.next;
		}
	}
	public static void main(String[] args) {
		Link_demo l1 = new Link_demo();

		Node first = new Node();
		l1.head=first;
		Node second = new Node();
		Node third = new Node();
		Node four = new Node(11);
		
		first.data=1;
		second.data = 2;
		third.data = 3;
		
		//linking
		first.next=second;
		second.next= third;
		third.next= four;
		four.next=null;
		l1.print_data();
		
		
	}
}
