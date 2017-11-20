package iproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
	    List<Integer> curr = new ArrayList<Integer>();
	    Arrays.sort(candidates);
	    helper(result, curr, 0, target, candidates);
	    return result;
	}
	
	public static void helper(List<List<Integer>> result, List<Integer> curr, int start, int target, int[] candidates){
	    if(target==0){
	        result.add(new ArrayList<Integer>(curr));
	        return;
	    }
	    if(target<0){
	        return;
	    }
	 
	    int prev=-1;
	    for(int i=start; i<candidates.length; i++){
	        if(prev!=candidates[i]){ // each time start from different element
	            curr.add(candidates[i]);
	            helper(result, curr, i+1, target-candidates[i], candidates); // and use next element only
	            curr.remove(curr.size()-1);
	            prev=candidates[i];
	        }
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr={10,1,2,7,6,1,5};
		List<List<Integer>> ll;
		ll=combinationSum2(arr,8);
		System.out.println(ll);
	}

}
