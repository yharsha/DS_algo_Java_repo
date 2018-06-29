package algorithms;

public class TowersOfHanoi {
	/**
	 * Step 1 − Move n-1 disks from source to aux Step 2 − Move nth disk from source
	 * to dest Step 3 − Move n-1 disks from aux to dest
	 **/

	static void towerOfHanoi(int n, char src, char des, char aux) {
		if (n == 1) {
			System.out.println("Move disk 1 from rod " + src + " to rod " + des);
			return;
		}
		towerOfHanoi(n - 1, src, aux, des);

		System.out.println("Move disk " + n + " from rod " + src + " to rod " + des);
		towerOfHanoi(n - 1, aux, des, src);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		towerOfHanoi(100, 'A', 'C', 'B');
	}

}
