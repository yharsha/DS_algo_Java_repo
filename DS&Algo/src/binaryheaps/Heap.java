package binaryheaps;

public class Heap {

	public int[] array;
	public int count;
	public int capacity;
	public int heap_type;

	public Heap(int capacity, int heap_type) {
		this.heap_type = heap_type;
		this.capacity = capacity;
		this.array = new int[capacity];
	}

	public int parent(int i) {
		if (i <= 0 || i >= this.count) {
			return -1;
		}
		return (i - 1) / 2;
	}

	public int leftChild(int i) {
		int left = 2 * i + 1;
		if (left >= this.count) {
			return -1;
		}
		return left;
	}

	public int rightChild(int i) {
		int right = 2 * i + 2;
		if (right >= this.count) {
			return -1;
		}
		return right;
	}

	public int getMax() {
		if (this.count == 0) {
			return Integer.MIN_VALUE;
		}
		return array[0];
	}
	public void perculate_down(int i)
	{
		if(2*i+1>=count||i<0){
			return;
		}
		int lc,rc,maxi,li,ri,temp;
		li = 2*i+1;
		ri=2*i+2;
		
		lc= array[2*i+1];
		if(2*i+2<count){
			rc= array[2*i+2];}
		else rc=Integer.MIN_VALUE;
		maxi = (lc>rc?li:ri);
		if(array[i]<array[maxi])
		{
			temp=array[maxi];
			array[maxi]=array[i];
			array[i]=temp;
			perculate_down(maxi);
		}

		//TC:O(logn)
		
	}
	public int delete_heap(int i)
	{
		if(i<0||i>=this.count||this.count==0){return Integer.MIN_VALUE;}
		int data=this.array[i];
		this.array[i] = array[count-1];
		this.count--;
		perculate_down(i);
		return data;
		//in standard heap only max element should be deleted
	}

	public void insert_in_heap(int data)
	{
		if(this.count==this.capacity)
		{
			reSizeHeap();
		}
		array[count]=data;
		this.count++;
		perculate_up(count-1);
	}
	public void perculate_up(int i)
	{
		if(i<=0||i>=this.count){return;}
		int pi= (i-1)/2,temp;
		if(array[pi]<array[i])
		{
			temp=array[pi];
			array[pi]=array[i];
			array[i]=temp;
			perculate_up(pi);
		}
		
	}
	public void reSizeHeap()
	{
		int []temp_array = new int[2*capacity];
		System.arraycopy(array, 0, temp_array, 0, this.count);
		this.array= new int[2*capacity];
		if(this.array==null)
		{
			System.out.println("Memmory failure");
		}
		for(int i=0;i<this.count;i++)
		{
			array[i]= temp_array[i];
		}
		this.capacity*=2;
		temp_array=null;
		
	}
	public void print_heap()
	{
		if(this.count==0){
			System.out.println("Heap is empty..!!");
			return;
		}
		for(int i =0;i<this.count;i++)
		{
			System.out.print(this.array[i]+":");
		}
		System.out.println();
	}
	public void destroyHeap()
	{
		this.count=0;
		this.array=null;
	}
	//best  method insert in heap
	//perculate down only for non-leaf childs
	public void BuildHeap(Heap h,int[] a,int n)
	{
		if(h==null){return;}
		while(n>h.capacity)
		{
			h.reSizeHeap();
		}
		for(int i=0;i<n;i++)
		{
			h.array[i]=a[i];
		}
		this.count=n;
		for(int i=(n-1)/2;i>=0;i--)
		{
		h.perculate_down(i);
		}
	}
	//heap sort find max..shift to last...build heap
	public void heapSort(Heap h,int[]a,int n)
	{
		if(h==null){return;}
		h.BuildHeap(h, a, n);
		int temp;
		for(int i=n;i>0;i--)
		{
			temp=h.array[0];
			h.array[0]=h.array[h.count-1];
			h.array[h.count-1]=temp;
			h.count--;
			h.perculate_down(0);
		}
		h.count=n;
		
	}
	
	
	public static void main(String[] args) {
		Heap h1 = new Heap(12, 1);
//		h1.array[0]= 31;
//		h1.array[1]=1;
//		h1.array[2]=21;
//		h1.array[3]=9;
//		h1.array[4]=10;
//		h1.array[5]=12;
//		h1.array[6]=18;
//		h1.array[7]=3;
//		h1.array[8]=2;
//		h1.array[9]=8;
//		h1.array[10]=7;
//		h1.count=11;
		h1.insert_in_heap(31);
		h1.insert_in_heap(1);
		h1.insert_in_heap(21);
		h1.insert_in_heap(9);
		h1.insert_in_heap(10);
		h1.insert_in_heap(12);
		h1.insert_in_heap(18);
		h1.insert_in_heap(3);
		h1.insert_in_heap(2);
		h1.insert_in_heap(8);
		h1.insert_in_heap(7);
		
		h1.print_heap();
		h1.perculate_down(1);
		System.out.println("After Perculating/Heapyfying");
		h1.print_heap();
		System.out.println("Deleted value in heap:"+h1.delete_heap(0));
		h1.print_heap();
		h1.insert_in_heap(31);
		h1.insert_in_heap(25);
		System.out.println("After inserting 25");
		h1.print_heap();
		h1.insert_in_heap(95);
		h1.print_heap();
		h1.destroyHeap();
		System.out.println("after destroying heap");
		h1.print_heap();
		
		int []check_arr = new int[10];
		for(int i=1;i<8;i++)
		{
			check_arr[i-1]=i;
		}
		Heap h2 = new Heap(7,1);
		System.out.println("Using Build Heap...");
		h2.BuildHeap(h2, check_arr, 7);
		h2.print_heap();
		System.out.println("Using Heap Sort...in Increasing order...last element max value");
		h2.heapSort(h2, check_arr, 7);
		h2.print_heap();
		
	}

}
