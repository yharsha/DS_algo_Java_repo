package b;



import  a.*;
public class Test extends Addition{
	
	  public static void main(String args[]){
		  Test obj = new Test();
		        /* It will throw error because we are trying to access
		         * the default method in another package
		         */
			obj.addTwoNumbers(10, 21);
		   }

}
