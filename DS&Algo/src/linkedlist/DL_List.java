package linkedlist;


//Doubly linked lists
public class DL_List {

	DL_Node head;
	DL_Node tail;
//add tail also,missed it ..
	static class DL_Node {

		private DL_Node prev;
		private int data;
		private DL_Node next;

		public DL_Node() {
		}

		DL_Node(int d) {
			this.data = d;
			prev = null;
			next = null;
		}

		public void dl_node_set(DL_Node a, int data, DL_Node b) {
			this.prev = a;
			this.data = data;
			this.next = b;
		}
		// end of dl node class
	}
	//**********************Insert operations*******************//
	public void dl_insertAtBegin(int data)
	{	
		DL_Node newnode = new DL_Node();
		newnode.dl_node_set(null, data, head);
		if(head==null){head=newnode;return;}
		head.prev=newnode;
		head=newnode;
	}
	
	public void dl_insertAtPos(int data,int x)
	{
		if(head==null){return;}
		if(x<=1){x=1;dl_insertAtBegin(data);}
		else if(x>length_dlist()){x=length_dlist();}
		int pos=1;DL_Node temp=head,temp1=null;
		while(pos!=x&&temp!=null)
		{
			temp1=temp;
			temp=temp.next;
			pos++;
		}
		DL_Node newnode = new DL_Node();
		newnode.dl_node_set(temp1, data, temp);
		temp1.next=newnode;
		temp.prev=newnode;	
	}
	public void dl_insertAtTail(int data)
	{
		if(head==null){return;}
		DL_Node temp = head;
		while(temp.next!=null)
		{
			temp=temp.next;
		}
		DL_Node newnode = new DL_Node();
		newnode.dl_node_set(temp, data, null);
		temp.next=newnode;
		
	}
	//**********************Delete operations*******************//
	//***********************************************************
	
	
	public void dl_deleteAtPosition(int x)
	{
		if(head==null){return;}
		if(x<1){x=1;}
		else if(x>length_dlist()){x=length_dlist();}
		DL_Node temp=head;
		int pos=1;
		while(temp!=null&&pos!=x)
		{
			temp=temp.next;
			pos++;
		}
		if(temp.prev!=null){temp.prev.next=temp.next;}
		if(temp.next!=null){temp.next.prev=temp.prev;}
		if(x==1){head=temp.next;}
		temp.prev=null;
		temp.next=null;
		
	}
	
	public void dl_delete_at_head()
	{
		if(head==null){return;}
		DL_Node temp = head;
		if(temp.next==null)
		{
			head=null;
			return;
		}
		temp.next.prev=null;
		head=temp.next;
		temp.next=null;
	}
	
	public void dl_delete_at_tail()
	{
		if(head==null){System.out.println("List is empty");return;}
		DL_Node temp = head;
		if(temp.next==null)
		{
			head=null;
			return;
		}
		while(temp.next!=null)
		{
			temp=temp.next;
		}
		temp.prev.next=null;
		temp.prev=null;
	}
	public void dl_delete_On_Matched(DL_Node a)
	{
		if(head==null){System.out.println("List is empty");return;}
		DL_Node temp = head;
		if(head==a)
		{
			head.next.prev=null;
			head=head.next;
			a.next=null;
			return;
			
		}
		while(temp!=null)
		{
			if(temp==a)
			{
				temp.prev.next=temp.next;
				if(temp.next!=null){
				temp.next.prev=temp.prev;}
				temp.next=null;
				temp.prev=null;
				return;
			}
			temp=temp.next;
		}
	}
	//get position of data
	public int getPosition(int data)
	{
		if(head==null){return -1;}
		int pos=1;
		DL_Node temp=head;
		while(temp!=null)
		{
			if(temp.data == data)
			{
				return pos;
			}
			temp=temp.next;
			pos++;
		}
		return pos;
	}
	//remove all elements
	public void dl_Remove_All()
	{
		if(head==null){return;}
		DL_Node temp = head;
		while(temp.next!=null)
		{
			temp=temp.next;
		}
		tail=temp;
		head=null;
		tail=null;
		
		
		
	}
	//print length of list
	public int length_dlist()
	{
		int len=0;
		if(head==null){return len;};
		DL_Node temp = head;len=1;
		while(temp.next!=null)
		{
			temp=temp.next;
			len++;
		}
		return len;
	}
	// for printing list
	public void print_dl_list() {
		if (head == null) {
			return;
		}
		DL_Node temp = head;
		System.out.print("DL_LIST:");
		while (temp != null) {
			System.out.print(temp.data + "->");
			temp = temp.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {

		DL_Node a1 = new DL_Node();
		DL_Node a2 = new DL_Node();
		DL_Node a3 = new DL_Node();
		DL_Node a4 = new DL_Node();

		a1.dl_node_set(null, 1, a2);
		a2.dl_node_set(a1, 2, a3);
		a3.dl_node_set(a2, 3, a4);
		a4.dl_node_set(a3, 4, null);

		DL_List d1 = new DL_List();
		d1.head = a1;// reference for a1 to head

		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		System.out.println("Pos:"+d1.getPosition(3));
		
		d1.dl_insertAtBegin(-12);
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		
		d1.dl_insertAtPos(0, 2);
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		
		d1.dl_insertAtPos(5, 6);
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		
		d1.dl_insertAtTail(7);
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		
		d1.dl_deleteAtPosition(1);
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		

		d1.dl_deleteAtPosition(7);
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		

		d1.dl_deleteAtPosition(5);
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		
		d1.dl_insertAtTail(5);
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
	
		d1.dl_delete_at_head();
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		
		d1.dl_delete_at_tail();
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		
		d1.dl_delete_On_Matched(a1);
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		
		d1.dl_Remove_All();
		d1.print_dl_list();
		System.out.println("len:"+d1.length_dlist());
		
		
		
		
		
	}

}
