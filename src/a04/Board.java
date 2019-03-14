/*************************************************
 * Authors: Jeremy Jacobson and Christopher Munoz
 * Assignment: 8 Puzzle
 * Date: TODO
 *************************************************/
package a04;

import edu.princeton.cs.algs4.Stack;

/**
 * Implements an immutable data type Board for an 8 puzzle 
 * that contains a multidimensional array of blocks with the 
 * functionality needed to solve the puzzle.
 *
 */
public class Board {
	private int[][] blocks;
	private int N;
	private int zeroIndex;
	private int[] twoToOne;
	
	/**
	 * Initializes the board from an N by N array of blocks
	 * where blocks[i][j] = block in row i, column j.
	 * @param blocks
	 */
	public Board(int[][] blocks) {
		this.blocks = blocks;
		N = blocks.length;
	}
	
	/**
	 * Returns the size N of the board.
	 * @return
	 */
	public int size() {
		return N;
	}
	
	/**
	 * Returns the number of blocks out of place + number 
	 * of moves made so far to get to the search node.
	 * @return
	 */
	public int hamming() {
		int hamming = 0;
		int num = 1;
		
		if (isGoal()) {													// If the puzzle is already solved, then
			return hamming;												// the hamming is 0.
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == N - 1 && j == N - 1) {
					num = 0;											// If checking bottom right of puzzle, setting
																		// {num} to 0 so the next if statement below
																		// can check which numbers are out of place.
				}
				
				if (blocks[i][j] != num++) {							// If 2D array is not in ascending order, it
					hamming++;											// tallies up the {hamming} variable so it can
																		// keep track of how many numbers are not in order.
				}
			}
		}
		return hamming - 1;												// hamming minus 1 since we don't need to account
																		// for the 0 being out of place.
	}
	
	/**
	 * Returns the sum of the Manhattan between blocks and goal.
	 * The Manhattan distance is the sum of the vertical and 
	 * horizontal distance from a block to its goal.
	 * @return
	 */
	public int manhattan() {
		int manhattan = 0;
		
		if (isGoal()) {													// If the puzzle is already solved, then
			return manhattan;											// the manhattan is 0.
		}
		
		twoToOne = new int[N * N];										// Used to convert blocks array to 1d array.
		int transfer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				twoToOne[transfer++] = blocks[i][j];					// Transferring to 1D Array.
			}
		}
		
		for (int i = 0; i < twoToOne.length; i++) {
			if (twoToOne[i] == i + 1 || twoToOne[i] == 0) {				// If number is in correct place or equals 0
																		// then skips the number.
				if (twoToOne[i] == 0) {
					zeroIndex = i;										// Records the index of 0 on the board.
				}
				continue;
			}
			manhattan += Math.abs((i / N) - ((twoToOne[i] - 1) / N));	// Accounts for how many rows the current
																		// number is off.
			
			manhattan += Math.abs((i % N) - ((twoToOne[i] - 1) % N));	// Accounts for how many columns the current
																		// number is off.
		}
		
		return manhattan;
	}
	
	/**
	 * Returns true if this board is equal to the 
	 * goal (completed) board.
	 * @return
	 */
	public boolean isGoal() {
		if (blocks[N - 1][N - 1] != 0) {								// If 0 is not at the bottom right corner
			return false;												// then the puzzle is not solved.
		}
		
		int num = 1;
		for (int i = 0; i < N; i++) {									// If Array is at the last spot, it sets {num}
			for (int j = 0; j < N; j++) {								// to 0 so that when the 2D Array is in ascending
				if (i == N - 1 && j == N - 1) {							// order, and the last spot is 0, it is considered
					num = 0;											// solved.
				}

				if (blocks[i][j] != num++) {							// Iterates through 2D Array to check if
					return false; 										// the numbers 1 through N - 1 are in ascending
																		// order.						
				}
			}
		}
		return true;													// If conditions above pass, the puzzle is solved.
	}
	
	/**
	 * Returns true if the board can be turned into the goal board.
	 * 
	 * @return
	 */
	public boolean isSolvable() {
		// turn 2D array into 1D array O(n)
		int[] oneD = new int[N * N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				oneD[(i * N) + j] = blocks[i][j];
			}
		}

		// count the number of inversions in 1D array O(n log n)?
		int inversions = 0;
		for (int i = 0; i < (N * N) - 1; i++) {
			for (int j = i + 1; j < N * N; j++) {
				if (oneD[i] > oneD[j] && oneD[i] != 0 && oneD[j] != 0)
					inversions++;
			}
		}

		if (N % 2 == 0) { // even
			// find row containing blank tile counting from bottom row up O(n)
			int blankRow;
			for (int i = N - 1; i >= 0; i--) {
				for (int j = N - 1; j >= 0; j--) {
					if (blocks[i][j] == 0) {
						blankRow = N - i;
						// solvable if blankRow is even and inversions odd or vice versa
						if ((blankRow % 2 == 0 && inversions % 2 != 0) || 
								(blankRow % 2 != 0 && inversions % 2 == 0))
							return true;
						else
							return false;
					}
				}
			}
		} else { // odd
			if (inversions % 2 == 0)// solvable if N is odd and inversions is even
				return true;
		}
		return false;
	}
	
	/**
	 * Returns true if this board equals the given object y.
	 * @return
	 */
	public boolean equals(Object y) {
		if (y == null)
			return false;
		if (y == this)
			return true;
		if (y.getClass() != this.getClass())
			return false;
		Board other = (Board) y;
		return (this.blocks == other.blocks) && (this.N == other.N);
	}
	
	/**
	 * Returns an Iterable of boards that contains this boards neighbors.
	 * @return
	 */
	public Iterable<Board> neighbors() {
		Stack<Board> neighbors = new Stack<Board>();
		
		if (zeroIndex / 6 )
		return neighbors; // TODO
	}
	
	
	
	/**
	 * Returns a string in the format:
	 * N
	 *  {block} {block} {block}
	 *  {block} {block} {block}
	 *  {block} {block} {block}
	 *  
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(String.format("%2d ", blocks[i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Used for SolverVisualizer. TODO remove before submitting.
	 * Returns the tile at the specified index on the board.
	 * @param row
	 * @param col
	 * @return
	 */
	public int tileAt(int row, int col) {
		return 0;//TODO Jeremy
	}

	/* * * * * * * * * * Test Client * * * * * * * * * */
	public static void main(String[] args) {
		int[][] tilesSolved = {
				{1,2,3},
				{4,5,6},
				{7,8,0}
		};
		Board testSolved = new Board(tilesSolved);
		System.out.println("Test size: " + testSolved.size());
		System.out.println(testSolved.toString());
		System.out.println("Is goal? " + testSolved.isGoal());
		System.out.println(testSolved.manhattan() + " is the manhattan");
		System.out.println(testSolved.hamming() + " is the hamming");
		System.out.println();
		
		int[][] tilesNotSolved = {
				{1,2,0},
				{4,5,3},
				{7,8,6}
		};
		Board testNotSolved = new Board(tilesNotSolved);
		System.out.println("Test size: " + testNotSolved.size());
		System.out.println(testNotSolved.toString());
		System.out.println("Is goal? " + testNotSolved.isGoal());
		System.out.println(testNotSolved.manhattan() + " is the manhattan");
		System.out.println(testNotSolved.hamming() + " is the hamming");
		System.out.println();
		System.out.println(5 / 6);
	}
}
