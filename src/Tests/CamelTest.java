package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Board.Board;

public class CamelTest {

	Board board = new Board(8, 8);
	
	@Test
	public void cannotTakeOwnColor() {
		board.setPiece(4, 3, 0, "pawn");
		assertFalse(board.movePiece(1, 2, 4, 3));
	}
	
	@Test
	public void canTakeEnemyPiece() {
		board.setPiece(2, 5, 1, "pawn");
		assertTrue("Can take enemy piece", board.movePiece(1, 2, 2, 5));
	}

	@Test
	public void canJumpOwnPieces(){
		board.setPiece(2, 2, 0, "pawn");
		assertTrue("Can jump over own pieces", board.movePiece(1, 2, 4, 3));
	}
	
	@Test
	public void canMove(){
		assertTrue("+3/+1 movement", board.movePiece(1, 2, 4, 3));
		assertTrue("-3/+1 movement", board.movePiece(4, 3, 1, 4));

		assertTrue("+3/-1 movement", board.movePiece(1 , 4 , 4, 3));
		
		
		assertTrue("-3/-1 movement", board.movePiece(1, 5, 0, 2));
		
		assertTrue("+1/-3 movement", board.movePiece(0, 2, 1, 5));
		assertTrue("-1/-3 movement", board.movePiece(1, 5, 2, 2));
		
		assertTrue("+1/+3 movement", board.movePiece(2, 2, 3, 5));
		
		board.setPiece(2, 2, 1, "camel");
		
		assertTrue("-1/+3 movement", board.movePiece(2, 2, 1, 5));
	}
	
	@Test
	public void canJumpEnemyPieces(){
		board.setPiece(2, 2, 1, "pawn");
		assertTrue("Can jump over own pieces", board.movePiece(1, 2, 4, 3));
	}
	
	@Test
	public void diagonalMove(){
		assertFalse("Cannot move diagonally", board.movePiece(1,2,3,3));
	}

	@Test
	public void horizontallMove(){
		assertFalse("Cannot move horizontally", board.movePiece(1,2,4,2));
	}
	
	@Test
	public void verticalMove(){
		assertFalse("Cannot move vertically", board.movePiece(2,2,2,4));
	}

}
