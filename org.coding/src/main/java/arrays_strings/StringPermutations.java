package arrays_strings;

public class StringPermutations {
	/*
	 * Time Complexity: O(n*n!) Note that there are n! permutations and it requires
	 * O(n) time to print a a permutation.allows duplicates
	 */
	public static void permute(String str, int l, int r) {
		if (l == r) {
			System.out.println(str);
		} else {
			for (int i = l; i <= r; i++) {
				// making the left curr pos constant & gen constants
				str = swap(str, l, i);
				permute(str, l + 1, r);
				str = swap(str, l, i);
			}
		}
	}

	public static String swap(String str, int i, int j) {
		if (i == j)
			return str;
		char ch[] = str.toCharArray();
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
		return String.valueOf(ch);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "ABCD";
		permute(str, 0, str.length() - 1);
	}

}
