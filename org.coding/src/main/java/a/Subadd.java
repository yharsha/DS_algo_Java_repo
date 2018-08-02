package a;

public class Subadd extends Addition{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Addition obj = new Addition();
		System.out.println(obj.addTwoNumbers(2, 3));
		
		Subadd s1 = new Subadd();
		System.out.println(s1.addTwoNumbers(2, 3));
	}

}
