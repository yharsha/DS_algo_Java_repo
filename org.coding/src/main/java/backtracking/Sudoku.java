package backtracking;

/*TC:O(n^m)..n is size of sudoku and m is vacant places*/
public class Sudoku {

	static final int n = 9;
	static final int UNASSINGED = 0;

	static boolean solveSudoku(int grid[][]) {
		// find unfilled
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				if (grid[row][col] == UNASSINGED) {
					// try from 1 to 9 whether each num is allowed
					for (int num = 1; num <= 9; num++) {
						if (isAllowed(row, col, num, grid)) {
							grid[row][col] = num;
							if (solveSudoku(grid)) {
								return true;
							} else {
								grid[row][col] = UNASSINGED;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isAllowed(int row, int col, int number, int[][] grid) {
		// TODO Auto-generated method stub

		return !(containsInRow(row, number, grid) || containsInCol(col, number, grid)
				|| containsInBox(row, col, number, grid));
	}

	private static boolean containsInBox(int row, int col, int number, int[][] grid) {
		// TODO Auto-generated method stub
		int r = row - row % 3;
		int c = col - col % 3;
		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				if (grid[i][j] == number) {
					return true;
				}
			}

		}
		return false;
	}

	private static boolean containsInCol(int col, int number, int[][] grid) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++) {
			if (grid[i][col] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean containsInRow(int row, int number, int[][] grid) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 9; i++) {
			if (grid[row][i] == number) {
				return true;
			}
		}
		return false;
	}

	public static void displaySudoku(int[][] grid) {
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0 && i != 0) {
				System.out.println("----------------------------------\n");
			}
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0 && j != 0) {
					System.out.print(" | ");
				}
				System.out.print(" " + grid[i][j] + " ");

			}

			System.out.println();
		}
		System.out.println("\n\n__________________________________________\n\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		if (solveSudoku(grid)) {
			displaySudoku(grid);
		} else {
			System.out.println("NO SOlution Exists");
		}
	}

}
