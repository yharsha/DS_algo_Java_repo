package arrays_strings;

import java.util.Arrays;

public class StringImpl {

	public static String checkStringBuilder(String[] words) {
		// unlike creating a new string when two strings are concatenated o(n2)....it
		// resizes array dynamically and adds string to array

		StringBuilder sentence = new StringBuilder();
		for (String w : words) {
			sentence.append(w);
		}
		return sentence.toString();
	}

	public static boolean checkStringUnique(String word) {
		/*
		 * Is Unique: Implement an algorithm to determine if a string has all unique
		 * characters. What if you cannot use additional data structures?
		 */
		// 256 characters in ascii...sort and check adj chrcts...O(nlogn)
		int len = word.length();
		if (len > 256)
			return false;
		char[] arr = new char[len];
		arr = word.toCharArray();
		// sort
		// Arrays.sort(arr);
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == arr[i + 1])
				return false;
		}
		return true;
	}

	public static boolean checkPermutationOfStrings(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;
		int arr[] = new int[256];// ascii table
		for (int i = 0; i < s1.length(); i++) {
			arr[s1.charAt(i)]++;
			arr[s2.charAt(i)]--;
		}

		for (int j = 0; j < 256; j++) {
			if (arr[j] != 0)
				return false;

		}
		return true;
	}

	public static String replaceSpace(char[] str, int length) {
		int space_count = 0;
		for (int i = 0; i < length; i++) {
			if (str[i] == ' ') {
				space_count++;
			}
		}

		int new_len = length + space_count * 2;
		str[new_len] = '\0';

		for (int j = length - 1; j >= 0; j--) {
			if (str[j] == ' ') {
				str[new_len - 1] = '0';
				str[new_len - 2] = '2';
				str[new_len - 3] = '%';
				new_len = new_len - 3;
			} else {
				str[new_len - 1] = str[j];
				new_len--;
			}
		}

		return String.valueOf(str);
	}

	/*
	 * Palindrome Permutation: Given a string, write a function to check if it is a
	 * permutation of a palindrome. A palindrome is a word or phrase that is the
	 * same forwards and backwards. A permutation is a rearrangement of letters. The
	 * palindrome does not need to be limited to just dictionary words.O(n)
	 */
	public static boolean checkPermutePalindrome(String s1) {
		int count[] = new int[256];
		for (int i = 0; i < s1.length(); i++) {
			count[s1.charAt(i)]++;
		}

		// if the count array has more than one odd values, return false.
		int odd_count = 0;
		for (int j = 0; j < 256; j++) {
			if ((count[j] & 1) == 1) {
				odd_count++;
			}
			if (odd_count > 1) {
				return false;
			}
		}
		return true;
	}

	/*
	 * There are three types of edits that can be performed on strings: insert a
	 * character, remove a character, or replace a character. Given two strings,
	 * write a function to check if they are one edit (or zero edits) away..o(n)
	 */
	public static boolean checkOneEdit(String s1, String s2) {
		int l1 = s1.length(), l2 = s2.length();
		if (l1 == l2) {
			return oneEditReplace(s1, s2);
		} else if (l1 > l2) {
			return oneEditInsert(s2, s1);
		} else if (l1 > l2) {
			return oneEditInsert(s2, s1);
		}
		return false;
	}

	public static boolean oneEditReplace(String sl, String s2) {
		boolean foundDifference = false;
		for (int i = 0; i < sl.length(); i++) {
			if (sl.charAt(i) != s2.charAt(i)) {
				if (foundDifference) {
					return false;
				}
				foundDifference = true;
			}
		}
		return true;
	}

	public static boolean oneEditInsert(String s1, String s2) {
		// check if we can insert only one ch in s1
		int index1 = 0, index2 = 0;
		while (index1 < s1.length() && index2 < s2.length()) {
			if (s1.charAt(index1) != s2.charAt(index2)) {
				if (index1 != index2) {
					return false;
				}
				index2++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}

	/**
	 * Implement a method to perform basic string compression using the counts of
	 * repeated characters. For example, the string a a b c c c c c a a a would
	 * become a2blc5a3. If the "compressed" string would not become smaller than the
	 * original string, your method should return the original string.
	 **/
	public static String stringCompression(String s1) {
		StringBuilder str = new StringBuilder();
		int count = 1, len = s1.length();
		for (int i = 0; i < s1.length(); i++) {
			if ((i + 1) < len && s1.charAt(i) == s1.charAt(i + 1)) {
				count++;
			} else {
				str.append(s1.charAt(i)).append(count);
				count = 1;
			}
		}
		return str.length() < len ? str.toString() : s1;
	}

	/*
	 * Given an image represented by an NxN matrix, where each pixel in the image is
	 * 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in
	 * place?
	 */
	public static void rotateMatrix(int[][]arr,int N)
	{
		//no of circles
		
		for(int x=0;x<(N/2);x++)
		{
			//shift ele by ele eg: group of 4
			for(int y=x;y<N-x-1;y++)
			{
				int temp=arr[x][y];
				//which is moving(row/c) keep y ,cont keep x
				//left to top
				arr[x][y]=arr[N-y-1][x];
				
				//bottom to left
				arr[N-y-1][x]=arr[N-1-x][N-1-y];
				
				//right to bottom
				arr[N-x-1][N-y-1]=arr[y][N-1-x];
				
				//top to right
				arr[y][N-1-x]=temp;
			}
		
		}
		
		displayMatrix(arr,N);
	}
	
	 public static void displayMatrix(int mat[][],int N)
	    {
	        for (int i = 0; i < N; i++)
	        {
	            for (int j = 0; j < N; j++)
	                System.out.print(" " + mat[i][j]);
	      
	            System.out.print("\n");
	        }
	        System.out.print("\n");
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] words = { "StringBuilder", "solves", "runtime", "of", "adding ", "strings" };

		System.out.println(checkStringBuilder(words));
		System.out.println(checkStringUnique("StringofNum"));
		System.out.println(checkPermutationOfStrings("IS HARSHA dev", "dev HARSHA IS"));
		char[] ch = { 'm', 'y', ' ', '3', '\0', '\0', '\0', '\0', '\0', '\0', '\0' };
		System.out.println(replaceSpace(ch, 4));
		System.out.println(checkPermutePalindrome("geeksogeeks"));
		System.out.println(checkOneEdit("pale", "bae"));
		System.out.println(stringCompression("aabcccccaaa"));
		
		   int mat[][] =
		        {
		            {1, 2, 3, 4},
		            {5, 6, 7, 8},
		            {9, 10, 11, 12},
		            {13, 14, 15, 16}
		        };
		  rotateMatrix(mat, mat.length);
	}

}
