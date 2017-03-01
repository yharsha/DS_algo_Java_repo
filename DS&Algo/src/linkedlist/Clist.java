package linkedlist;

//circular linked list
public class Clist {
	Cnode head;

	static class Cnode {

		int data;
		Cnode next;

		Cnode() {
		}

		Cnode(int data) {
			this.data = data;
		}

		public void cnode_set(int data, Cnode next) {
			this.data = data;
			this.next = next;
		}

	}

	// insertion
	public void insert_At_Head_C(int data) {
		if (head == null) {
			return;
		}
		Cnode newnode = new Cnode();
		newnode.cnode_set(data, head);

		Cnode temp = head;
		while (temp.next != head) {
			temp = temp.next;
		}
		temp.next = newnode;
		head = newnode;
	}

	public void insert_AT_Tail_C(int data) {
		if (head == null) {
			return;
		}
		Cnode newnode = new Cnode();
		newnode.cnode_set(data, head);
		Cnode temp = head;
		while (temp.next != head) {
			temp = temp.next;
		}
		temp.next = newnode;
	}

	// ************************delete_operations**************************//
	public void delete_At_Head() {
		if (head == null) {
			return;
		}
		Cnode temp = head;
		while (temp.next != head) {
			temp = temp.next;
		}
		if (temp == head) {
			head = null;
			return;
		}
		temp.next = head.next;
		temp = head;
		head = head.next;
		temp.next = null;
	}

	public void delete_At_Tail() {
		if (head == null) {
			return;
		}
		Cnode temp = head;
		Cnode temp1 = null;
		while (temp.next != head) {
			temp1 = temp;
			temp = temp.next;
		}
		if (temp == head) {
			head = null;
			return;
		}
		temp1.next = head;
		temp.next = null;
	}

	// find element
	public boolean check_data_c(int val) {
		if (head == null) {
			System.out.println("List is empty");
			return false;
		}
		Cnode temp = head;
		while (temp.next != head) {
			if (temp.data == val) {
				return true;
			}
			temp = temp.next;
		}
		if (temp.data == val) {
			return true;
		}
		else return false;
	}

	public void remove_data_c(int data)
	{
		if (head == null) {System.out.println("List is empty");}
		
		Cnode temp = head;
		Cnode temp1=null;
		if(temp.next==head && temp.data==data)
		{
			head=null;return;
			
		}
		if(temp.next!=head && temp.data==data)
		{temp1=temp;
			while (temp1.next != head) {
				temp1 = temp1.next;
			}
			temp1.next=head.next;
			head=head.next;
			temp.next=null;
			return;
		}
		while (temp.next != head) {
			if (temp.data == data) {
				temp1.next=temp.next;
				temp.next=null;
				return;
			}
			temp1=temp;
			temp = temp.next;
		}
		if(temp.next==head)
		{
		temp1.next=head;
		temp.next=null;
		}
	}
	// print list
	public void printlist_c() {
		if (head == null) {
			return;
		}
		Cnode temp = head;
		System.out.print(temp.data + "->");
		while (temp.next != head) {
			temp = temp.next;
			System.out.print(temp.data + "->");
		}
		System.out.println();
	}

	// length of list
	public int length_c() {

		if (head == null) {
			System.out.println("List is empty");
			return 0;
		}
		int len = 1;
		Cnode temp = head;
		while (temp.next != head) {
			temp = temp.next;
			len++;
		}
		return len;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clist cobj = new Clist();
		Cnode c0 = new Cnode(0);
		Cnode c1 = new Cnode(1);
		Cnode c2 = new Cnode(2);
		Cnode c3 = new Cnode(3);
		Cnode c4 = new Cnode(4);

		cobj.head = c0;
		c0.cnode_set(0, c1);
		c1.cnode_set(1, c2);
		c2.cnode_set(2, c3);
		c3.cnode_set(3, c4);
		c4.cnode_set(4, c0);

		cobj.printlist_c();
		System.out.println("len:" + cobj.length_c());

		cobj.insert_At_Head_C(-1);
		cobj.printlist_c();
		System.out.println("len:" + cobj.length_c());

		cobj.insert_AT_Tail_C(5);
		cobj.printlist_c();
		System.out.println("len:" + cobj.length_c());

		cobj.delete_At_Head();
		cobj.printlist_c();
		System.out.println("len:" + cobj.length_c());

		for (int i = 0; i < 7; i++) {}
			cobj.delete_At_Tail();
			cobj.printlist_c();
			System.out.println("len:" + cobj.length_c());
			
			System.out.println("Check_Element:"+cobj.check_data_c(4));
			cobj.printlist_c();
			System.out.println("len:" + cobj.length_c());
		
			cobj.remove_data_c(0);
			cobj.printlist_c();
			System.out.println("len:" + cobj.length_c());

	}

}
