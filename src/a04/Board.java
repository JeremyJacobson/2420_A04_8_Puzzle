/*************************************************
 * Authors: Jeremy Jacobson and Christopher Munoz
 * Assignment: 8 Puzzle
 * Date: TODO
 *************************************************/
package a04;

/**
 * Implements an immutable data type Board for an 8 puzzle 
 * that contains a multidimensional array of blocks with the 
 * functionality needed to solve the puzzle.
 *
 */
public class Board {
	private int[][] blocks;
	private int N;
	
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
		return 0;//TODO
	}
	
	/**
	 * Returns the sum of the Manhattan distances of the 
	 * blocks + number of moves made so far to get to the 
	 * search node.
	 * The Manhattan distance is the sum of the vertical and 
	 * horizontal distance from a block to its goal.
	 * @return
	 */
	public int manhattan() {
		return 0;//TODO Chris
	}
	
	/**
	 * Returns true if this board is equal to the 
	 * goal (completed) board.
	 * @return
	 */
	public boolean isGoal() {
		return false;//TODO Chris
	}
	
	/**
	 * Returns true if the board can be turned into the
	 * goal board.
	 * @return
	 */
	public boolean isSolvable() {
		return false;//TODO
	}
	
	/**
	 * Returns true if this board equals the given object y.
	 * @return
	 */
	public boolean equals(Object y) {
		return false;//TODO
	}
	
	/**
	 * Returns an Iterable of boards that contains this boards neighbors.
	 * @return
	 */
	public Iterable<Board> neighbors() {
		return null;//TODO Chris
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
		return sb.toString();//TODO
	}
	
	/**
	 * Used for SolverVisualizer. TODO remove before submitting.
	 * Returns the tile at the specified index on the board.
	 * @param row
	 * @param col
	 * @return
	 */
	public int tileAt(int row, int col) {
		return 0;//TODO
	}

	/* * * * * * * * * * Test Client * * * * * * * * * */
	public static void main(String[] args) {
		int[][] tiles = {
				{0,1,2},
				{3,4,5},
				{6,7,8}
		};
		Board test = new Board(tiles);
		System.out.println("Test size: " + test.size());
		System.out.println(test.toString());
	}
}
