package recursion;


public class Hanoi_Imp {
	
	public void hanoi(int n,char src,char aux,char des)
	{
		//a,b,c pegs
		//n disks of decreasing size
		if(n==1)
		{
			System.out.println(src+"-->"+des);
			return;
		}
		//using one should be in middle
		//move n-1 disks from src to aux using des
		hanoi(n-1,src,des,aux);
		//move 1 disk from src to des		
		hanoi(1, src, aux, des);
		//move n-1 from aux to des using src
		hanoi(n-1,aux,src,des);
		
	}
	public static void main(String[] args) {
		
		new Hanoi_Imp().hanoi(3, 'A','B', 'C');
	}

}
