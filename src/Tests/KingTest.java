package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Board.Board;

public class KingTest {

	Board board = new Board(8, 8);
	
	@Test
	public void cannotMoveMoreThanOne(){
		board.setPiece(3, 3, 0, "king");
		assertFalse("King cannot move more than one space in either direction", board.movePiece(3, 3, 5, 3));
		assertFalse("King cannot move more than one space in either direction", board.movePiece(3, 3, 5, 5));
		assertFalse("King cannot move more than one space in either direction", board.movePiece(3, 3, 3, 5));
	}
	
	@Test
	public void cannotTakeOwnPiece(){
		assertFalse("King cannot take own piece", board.movePiece(4, 0, 4, 1));
	}
	
	@Test
	public void moveHorizontally() {
		board.setPiece(4, 2, 0, "king");
		assertTrue("King can move horizontally", board.movePiece(4, 2, 5, 2));
		assertTrue("King can move horizontally", board.movePiece(5, 2, 4, 2));
	}
	
	@Test
	public void moveVertically(){
		board.setPiece(0, 2, 0, "king");
		assertTrue("King can move vertically", board.movePiece(0, 2, 0, 3));
		assertTrue("King can move vertically", board.movePiece(0, 3, 0, 4));
	}
	
	@Test
	public void moveDiagonally(){
		board.setPiece(2, 2, 0, "king");
		assertTrue("King can move diagonally", board.movePiece(2, 2, 1, 3));
		assertTrue("King can move diagonally", board.movePiece(1, 3, 0, 4));
	}
	
	@Test
	public void cannotJumpPieces(){
		assertFalse("King cannot jump pieces", board.movePiece(4, 0, 4, 2));
	}
	
	@Test
	public void canTakeEnemyPiece(){
		board.setPiece(3, 3, 0, "king");
		board.setPiece(3, 4, 1, "knight");
		assertTrue("King can take enemy piece when not attacked", board.movePiece(3, 3, 3, 4));
	}
	
	@Test
	public void cannotMoveOntoAttackedSpace(){
		board.setPiece(3, 3, 0, "king");
		board.setPiece(4, 4, 1, "rook");
		assertFalse("King cannot move onto attacked space", board.movePiece(3, 3, 3, 4));
	}
}
