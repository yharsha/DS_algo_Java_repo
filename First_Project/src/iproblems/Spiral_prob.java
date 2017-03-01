package iproblems;


public class Spiral_prob {

	public void spiral_numbers(int a[][],int rlen,int clen)
	{
		
		//l1,l2----row index..initially l1=0,l2=rlen-1
		//r1,r2---Column index
		int r2=clen-1,l2=rlen-1,l1=0,r1=0;
		while(l1*r1<(rlen-1)*(clen-1))
		{
			int i=r1;
			//traverse right
			while(i<=r2)
			{
				System.out.println(a[l1][i]);
				i++;
			}
			l1++;
			i=l1;
			//down
			while(i<=l2)
			{
				System.out.println(a[i][r2]);
				i++;
			}
			r2--;
			i=r2;
			//left
			while(i>=r1)
			{
				System.out.println(a[l2][i]);
				i--;

			}
			l2--;
			i=l2;
			//up
			while(i>=l1)
			{
				System.out.println(a[i][r1]);
				i--;
			}
			r1++;
		
	
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
Spiral_prob s1 = new Spiral_prob();
int arr[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
s1.spiral_numbers(arr, arr.length, arr[0].length);
	}

}
