package strings;

import java.util.*;

public class StringDemo {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter String:");
		String time = s.nextLine();
		String ftime = "";
		String str2 = "";
		char dec = time.charAt(8);
		for (int i = 0; i < 2; i++) {
			str2 += time.charAt(i);
		}
		int h1 = Integer.parseInt(str2);

		if (dec == 'P') {
			if (h1 != 12) {
				h1 += 12;
			}
			ftime = Integer.toString(h1);
			for (int i = 2; i < 8; i++) {
				ftime += time.charAt(i);
			}
			System.out.print(ftime);
		} else if (dec == 'A') {
			if (h1 == 12) {
				h1 = 00;
				ftime = "00";

				for (int i = 2; i < 8; i++) {
					ftime += time.charAt(i);
				}
				System.out.print(ftime);
			} else {
				for (int i = 0; i < 8; i++) {
					ftime += time.charAt(i);
				}
				System.out.print(ftime);
			}
		}

	}

}
