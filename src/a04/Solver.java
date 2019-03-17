/*************************************************
 * Authors: Jeremy Jacobson and Christopher Munoz
 * Assignment: 8 Puzzle
 * Date: TODO
 *************************************************/
package a04;

import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
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
		PriorityOrderManhattan orderMan = new PriorityOrderManhattan();
		MinPQ<SearchNode> queue = new MinPQ<SearchNode>(orderMan);
		SearchNode node = new SearchNode();
		node.board = initial;
		queue.insert(node);
		
		SearchNode minP = queue.delMin(); // initial node with smallest priority board
		//Prints out neighbors
		System.out.println(minP.board.toString());
		for (Board el : minP.board.neighbors()) {
			System.out.println(el.toString());
		}
		
		// while the minimum priority nodes board is not equal to the goal board
//		while(!minP.board.isGoal()) {
//			
//			for (Board el : minP.board.neighbors()) {
//				// Checks if previous is null or if this neighbor does not equal previous board
//				if (minP.previous == null || !el.equals(minP.previous.board)) {
//					SearchNode newNode = new SearchNode();
//					newNode.board = el;
//					newNode.moves = minP.moves + 1;
//					newNode.previous = minP;
//					queue.insert(newNode);
//				}
//				
//			}
//			
//			
//			minP = queue.delMin();
//		}
	}
	
	private class SearchNode {
		private Board board;
		private int moves = 0;
		private SearchNode previous;
	}
	
	private class PriorityOrderManhattan implements Comparator<SearchNode> {

		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			if (o1.board.manhattan() + o1.moves > o2.board.manhattan() + o2.moves)
				return 1;
			if (o1.board.manhattan() + o1.moves < o2.board.manhattan() + o2.moves)
				return -1;
			return 0;
		}
		
	}
	
	private class PriorityOrderHamming implements Comparator<SearchNode> {

		@Override
		public int compare(SearchNode o1, SearchNode o2) {
			if (o1.board.hamming() + o1.moves > o2.board.hamming() + o2.moves)
				return 1;
			if (o1.board.hamming() + o1.moves < o2.board.hamming() + o2.moves)
				return -1;
			return 0;
		}
		
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
	    In in = new In("/a04/resources/puzzle30.txt");
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
