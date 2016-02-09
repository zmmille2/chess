package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Board.Board;

public class QueenTest {
	
	Board board = new Board(8, 8);

	@Test
	public void diagonalMove() {
		board.setPiece(3, 3, 0, "queen");
		assertTrue("Queen can move diagonally", board.movePiece(3, 3, 4, 4));
		assertTrue("Queen can move diagonally", board.movePiece(4, 4, 2, 2));
	}
	
	@Test
	public void horizontalMove(){
		board.setPiece(3, 3, 0, "queen");
		assertTrue("Queen can move horizontally", board.movePiece(3, 3, 5, 3));
		assertTrue("Queen can move horizontally", board.movePiece(5, 3, 2, 3));
	}
	
	@Test
	public void verticalMove(){
		board.setPiece(3, 3, 0, "queen");
		assertTrue("Queen can move vertically", board.movePiece(3, 3, 3, 5));
		assertTrue("Queen can move vertically", board.movePiece(3, 5, 3, 2));
	}
	
	@Test
	public void takeEnemyPiece(){
		board.setPiece(3, 3, 0, "queen");
		board.setPiece(3, 4, 1, "pawn");
		assertTrue("Queen can take enemy piece", board.movePiece(3, 3, 3, 4));
	}
	
	@Test
	public void cannotTakeOwnPiece(){
		board.setPiece(3, 3, 0, "queen");
		board.setPiece(3, 4, 0, "pawn");
		assertFalse("Queen cannot take own colored piece", board.movePiece(3, 3, 3, 4));
	}
	
	@Test
	public void cannotJumpOverPiece(){
		assertFalse("Queen cannot jump pieces", board.movePiece(4, 0, 4, 3));
	}
	
	@Test
	public void cannotMoveLShape(){
		board.setPiece(2, 2, 0, "queen");
		assertFalse("Queen cannot move in L Shape", board.movePiece(2, 2, 4, 3));
	}

}
