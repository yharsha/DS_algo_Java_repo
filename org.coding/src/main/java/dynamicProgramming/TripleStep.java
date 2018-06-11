package dynamicProgramming;

import java.util.Arrays;

public class TripleStep {

	/*
	 * Triple Step: A child is running up a staircase with n steps and can hop
	 * either 1 step, 2 steps, or 3 steps at a time. Implement a method to count how
	 * many possible ways the child can run up the stairs.
	 */
	public static int countWays(int steps) {
		int memo[]=new int[steps+1];
		Arrays.fill(memo,-1);
		return countWays(0,steps,memo);
		
	}

	private static int countWays(int stepCount, int steps,int[] memo) {
		if(stepCount>steps)return 0;
		else if(stepCount==steps)return 1;
		else if(memo[stepCount] == -1)
		{
			memo[stepCount]=countWays(stepCount+1,steps,memo)+countWays(stepCount+2,steps,memo)+countWays(stepCount+3,steps,memo);
		}
		return memo[stepCount];
	}

	public static void main(String[] args) {
		int n=5;
		System.out.println("ways for climbing "+n+" Steps: "+countWays(n));

	}

}
