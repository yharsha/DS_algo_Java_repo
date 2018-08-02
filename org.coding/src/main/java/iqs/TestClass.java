package iqs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class TestClass {

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tests = Integer.parseInt(br.readLine());

		for (int t = 0; t < tests; t++) {
			int cities = Integer.parseInt(br.readLine());
			LinkedList<Integer>[] lookup = new LinkedList[cities + 1];
			String values[] = br.readLine().split("\\s");
			int happyValue[] = new int[cities + 1];
			for (int i = 1; i <= cities; i++) {
				lookup[i] = new LinkedList<Integer>();
				happyValue[i] = Integer.parseInt(values[i - 1]);
			}
			int paths = Integer.parseInt(br.readLine());
			String[] source = br.readLine().split("\\s");
			String[] des = br.readLine().split("\\s");
			for (int p = 0; p < paths; p++) {
				lookup[Integer.parseInt(source[p])].addLast(Integer.parseInt(des[p]));
				lookup[Integer.parseInt(des[p])].addLast(Integer.parseInt(source[p]));
			}

			findMax(lookup, happyValue, 1);
		}
	}

	private static void findMax(LinkedList<Integer>[] lookup, int[] happyValue, int startNode) {
		LinkedList<Integer> tempList;
		int currNode = startNode, currHappy = 0;
		int[] arr = new int[2];
		arr[0] = startNode;
		arr[1] = currHappy;
		boolean visited[] = new boolean[happyValue.length];
		visited[0] = true;

		while (checkNext(lookup, happyValue, arr, visited)) {

		}
		System.out.println(arr[1]);
	}

	public static boolean checkNext(LinkedList<Integer>[] lookup, int[] happyValue, int[] arr, boolean[] visited) {
		// arr[0]=currNode;arr[1]=currHappy;
		LinkedList<Integer> tempList;
		// checkchild of curr node

		int currNode = arr[0];
		if (!visited[currNode]) {
			if (happyValue[currNode] > 0) {
				arr[1] += happyValue[currNode];
			}
			visited[currNode] = true;

			// find child with maxhap
			tempList = lookup[currNode];
			int maxChild = -1, maxHappy = -1;
			for (int x : tempList) {
				if (happyValue[x] > 0 && happyValue[x] > maxHappy) {
					maxHappy = happyValue[x];
					maxChild = x;
				}
			}
			if (maxChild != -1 && maxHappy != -1) {
				arr[0] = maxChild;
				return true;
			}

		}
		return false;
	}

}
