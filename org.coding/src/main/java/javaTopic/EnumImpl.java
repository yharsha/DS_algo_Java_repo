package javaTopic;

/** https://www.geeksforgeeks.org/enum-in-java/ **/
enum color {
	RED, GREEN, BLUE;
}

public class EnumImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		color[] arr = color.values();

		for (color col : arr) {
			System.out.println(col + " at index " + col.ordinal());
		}
		System.out.println(color.valueOf("RED"));

		color c1 = color.RED;
		System.out.println(c1);
	}

}
