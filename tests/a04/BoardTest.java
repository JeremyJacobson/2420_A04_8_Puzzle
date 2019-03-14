package a04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
	int[][] tilesSolved = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
	Board testSolved = new Board(tilesSolved);
	int[][] tilesNotSolved = { { 1, 5, 2 }, { 4, 0, 3 }, { 7, 8, 6 } };
	Board testNotSolved = new Board(tilesNotSolved);
	int[][] oddSolvableTiles = { { 1, 8, 2 }, { 0, 4, 3 }, { 7, 6, 5 } };
	Board oddSolvable = new Board(oddSolvableTiles);
	int[][] oddUnsolvableTiles = { { 8, 1, 2 }, { 0, 4, 3 }, { 7, 6, 5 } };
	Board oddUnsolvable = new Board(oddUnsolvableTiles);
	int[][] evenSolvableTiles = { { 6, 13, 7, 10 }, { 8, 9, 11, 0 }, { 15, 2, 12, 5 }, { 14, 3, 1, 4 } };
	Board evenSolvable = new Board(evenSolvableTiles);
	int[][] evenUnsolvableTiles = { { 3, 9, 1, 15 }, { 14, 11, 4, 6 }, { 13, 0, 10, 12 }, { 2, 7, 8, 5 } };
	Board evenUnsolvable = new Board(evenUnsolvableTiles);
	
	@BeforeEach
	void setUp() throws Exception {
	}

//	@Test
//	void testBoard() {
//		fail("Not yet implemented");
//	}

	@Test
	void testSize3() {
		int expected = 3;
		int actual = oddSolvable.size();
		assertEquals(expected, actual);
	}
	
	@Test
	void testSize4() {
		int expected = 4;
		int actual = evenSolvable.size();
		assertEquals(expected, actual);
	}

	@Test
	void testHamming() {
		fail("Not yet implemented");
	}

	@Test
	void testManhattan() {
		fail("Not yet implemented");
	}

	@Test
	void testIsGoal() {
		fail("Not yet implemented");
	}

	@Test
	void testIsSolvable_oddSolvable() {
		boolean expected = true;
		boolean actual = oddSolvable.isSolvable();
		assertEquals(expected, actual);
	}
	
	@Test
	void testIsSolvable_oddUnsolvable() {
		boolean expected = false;
		boolean actual = oddUnsolvable.isSolvable();
		assertEquals(expected, actual);
	}
	
	@Test
	void testIsSolvable_evenSolvable() {
		boolean expected = true;
		boolean actual = evenSolvable.isSolvable();
		assertEquals(expected, actual);
	}
	
	@Test
	void testIsSolvable_evenUnsolvable() {
		boolean expected = false;
		boolean actual = evenUnsolvable.isSolvable();
		assertEquals(expected, actual);
	}

	@Test
	void testEqualsObject_Null() {
		boolean expected = false;
		boolean actual = testSolved.equals(null);
		assertEquals(expected, actual);
	}
	
	@Test
	void testEqualsObject_CopywithDifferentName() {
		Board copy = testSolved;
		boolean expected = true;
		boolean actual = testSolved.equals(copy);
		assertEquals(expected, actual);
	}
	
	@Test
	void testEqualsObject_DifferentBlocks_SameN() {
		boolean expected = false;
		boolean actual = testSolved.equals(testNotSolved);
		assertEquals(expected, actual);
	}
	
	@Test
	void testEqualsObject_SameObject() {
		boolean expected = true;
		boolean actual = testSolved.equals(testSolved);
		assertEquals(expected, actual);
	}

	@Test
	void testNeighbors() {
		fail("Not yet implemented");
	}

//	@Test
//	void testToString() {
//		fail("Not yet implemented");
//	}

}
