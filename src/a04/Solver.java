/*************************************************
 * Authors: Jeremy Jacobson and Christopher Munoz
 * Assignment: 8 Puzzle
 * Date: TODO
 *************************************************/
package a04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implements an immutable data type Solver for an 8 puzzle
 * that solves a given Board and provides functionality to 
 * show the minimum number of moves needed and the steps 
 * taken in the solution.
 *
 */
public class Solver {
	
	/**
	 * Finds a solution to the initial board by 
	 * using the A* algorithm.
	 * @param initial
	 */
	public Solver(Board initial) {
		//TODO
	}
	
	/**
	 * Returns the minimum number of moves needed
	 * to solve the initial board.
	 * @return
	 */
    public int moves() {
    	return 0;//TODO
    }
    
    /**
     * Returns an Iterable of boards that contains the
     * sequence of boards in the shortest solution.
     * @return
     */
    public Iterable<Board> solution() {
    	return null;//TODO
    }
    
    /* * * * * * * * * * Test Client * * * * * * * * * */
	public static void main(String[] args) {
		// create initial board from file
	    In in = new In(args[0]);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // check if puzzle is solvable; if so, solve it and output solution
	    if (initial.isSolvable()) {
	        Solver solver = new Solver(initial);
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }

	    // if not, report unsolvable
	    else {
	        StdOut.println("Unsolvable puzzle");
	    }
	} 
}
