package hashing;


public class Hashing_sperate_chaining {
	
	public class ListNode{
		private int key;
		private int data;
		private ListNode next;
		public int getKey(){return key;}
		public void setKey(int key){this.key = key;}
		public int getData(){return data;}
		public void setData(int data){this.data=data;}
		public ListNode getNext(){return next;}
		public void setNext(ListNode next){this.next = next;}	
	}
	
	public class HashTableNode{
		private int blockCount;
		private ListNode startNode;
		public int getBlockCount(){return blockCount;}
		public void setBlockCount(int blockCount){this.blockCount=blockCount;}
		public ListNode getStartNode(){return startNode;}
		public void setStartNode(ListNode startNode){this.startNode=startNode;}
	}
	
	public class HashTable{
		private int size;
		private int count;
		//hash table is array which stores head pointer for linked list
		private HashTableNode[] table;
		public int getTSize(){return size;}
		public void setTsize(int size){
			this.size=size;
			table= new HashTableNode[size];}
		public void setCount(int count){this.count=count;}
		public HashTableNode[] getTable(){return table;}
		public void setTable(HashTableNode[] table){this.table=table;}
	}
	
	public class HashTableOperations{
		public final static int LOADFACTOR = 20;
		public  HashTable createHashTable(int size)
		{
			HashTable h = new HashTable();
			h.setTsize(size/LOADFACTOR);
			for(int i=0;i<h.getTSize();i++)
			{
				h.getTable()[i].setStartNode(null);
			}
			return h;
		}
		public int Hash(int data,int size)
		{//assume it is a inbuilt function
			return size;
		}
		public  int hashSearch(HashTable h,int data)
		{
			ListNode temp;
			temp=h.getTable()[Hash(data,h.getTSize())].getStartNode();
			while(temp!=null)
			{
				if(temp.getData()==data)
				{
					return 1;}
					temp=temp.getNext();
				}
			return 0;
			
		}
		
		public void hashInsert(HashTable h,int data){
			int index;
			ListNode temp,newNode;
			if(hashSearch(h,data)!=0){return;}
			index=Hash(data,h.getTSize());
			newNode = new ListNode();
			if(newNode==null)
			{
				System.out.println("Memory Error");return ;
			}
			
		}
	}
}
