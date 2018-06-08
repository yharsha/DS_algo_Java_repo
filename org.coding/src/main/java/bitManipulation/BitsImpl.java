package bitManipulation;

public class BitsImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 23;

		// get 5thbit - 23 : 0001 0111
		System.out.println(num & (1 << 4));

		//set 6th bit
		System.out.println(num | (1 << 5));
		
		//clear 6th bit
		System.out.println(num & ~(1 << 5));
		
		
		//clear bits from MSB to before 4 or i
		System.out.println(Integer.toBinaryString(num & ((1 << 4)-1)));
		
		//2's complement form it is stored
		System.out.println(Integer.toBinaryString(-1<<4));
	}

}
