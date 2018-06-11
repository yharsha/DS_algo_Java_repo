package math_logic_puzzles;

public class PrimeGeneration {

	boolean[] sieveOfErathosthenes(int max) {
		// cutoff numbers by primes
		int prime = 2;
		boolean[] nums = new boolean[max + 1];
		init(nums);
		while (prime <= Math.sqrt(max)) {
			// cutoff all multiples of prime
			crossoff(nums, prime);
			/* find next value which is true */
			prime = getNextPrime(nums, prime);
		}

		return nums;
	}

	private int getNextPrime(boolean[] nums, int prime) {
		// TODO Auto-generated method stub
		int next = prime + 1;
		while (next < nums.length && !nums[next]) {
			next++;
		}
		return next;
	}

	private void crossoff(boolean[] nums, int prime) {

		for (int i = prime * prime; i < nums.length; i += prime) {
			nums[i] = false;
		}

	}

	private void init(boolean[] nums) {
		// TODO Auto-generated method stub
		for (int i = 0; i < nums.length; i++) {
			nums[i] = true;
		}
	}

	public static void main(String[] args) {
		PrimeGeneration primeGen= new PrimeGeneration(); 
		int max=100;
		boolean arr[]=new boolean[max+1];
		arr=primeGen.sieveOfErathosthenes(max);
		for(int i=2;i<arr.length;i++)
		{
			if(arr[i])
			System.out.print(i+" ");
		}
	}

}
