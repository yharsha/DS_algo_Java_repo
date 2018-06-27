package arrays_strings;

import java.util.ArrayList;

public class AllSubsetsImpl {

	static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set) {
		ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
		int max = (1 << set.size());// 2N
		for (int i = 0; i < max; i++) {
			ArrayList<Integer> subset = convertintToSet(i, set);
			allsubsets.add(subset);
		}
		return allsubsets;
	}

	static ArrayList<Integer> convertintToSet(int i, ArrayList<Integer> set) {

		ArrayList<Integer> subset = new ArrayList<Integer>();
		int index = 0;

		for (int j = 0; j < set.size(); j++) {
			if ((i & (1 << j)) > 0)
				subset.add(set.get(j));
		}

		return subset;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		allsubsets = getSubsets(set);

		for (ArrayList<Integer> subset : allsubsets) {
			System.out.println(subset.toString());
		}
	}

}
