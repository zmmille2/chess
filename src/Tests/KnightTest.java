package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Board.Board;

public class KnightTest {

	Board board = new Board(8, 8);
	
	@Test
	public void cannotTakeOwnColor() {
		board.movePiece(2, 1, 2, 2);
		assertFalse("Cannot take own colored piece", board.movePiece(1, 0, 2, 2));
	}
	
	@Test
	public void canTakeEnemyPiece(){
		board.setPiece(2, 2, 1, "pawn");
		assertTrue("Can take enemy piece", board.movePiece(1, 0, 2, 2));		
	}
	
	@Test
	public void canJumpOwnPieces(){
		assertTrue("Can jump over own pieces", board.movePiece(1, 0, 2, 2));
	}
	
	@Test
	public void canMoveLShape(){
		assertTrue("+1/+2 movement", board.movePiece(1, 0, 2, 2));
		assertTrue("-1/+2 movement", board.movePiece(6, 0, 5, 2));
		board.setPiece(3, 4, 0, "knight");
		assertTrue("+1/-2 movement", board.movePiece(3,4,4,2));
		assertTrue("-1/+2 movement", board.movePiece(4, 2, 3, 4));
		
		assertTrue("+2/+1 movemnet", board.movePiece(3, 4, 5, 5));
		assertTrue("-2/-1 movement", board.movePiece(5, 5, 3, 4));
		
		assertTrue("+2/-1 movement", board.movePiece(3, 4, 5, 3));
		assertTrue("-2/+1 movement", board.movePiece(5, 3, 3, 4));
	}
	
	@Test
	public void canJumpEnemyPieces(){
		board.setPiece(2, 2, 0, "knight");
		board.setPiece(2, 3, 1, "pawn");
		assertTrue("Can jump over enemy pieces", board.movePiece(2, 2, 4, 3));
	}
	
	@Test
	public void diagonalMove(){
		board.setPiece(2, 2, 0, "knight");
		assertFalse("Cannot move diagonally", board.movePiece(2,2,3,3));
	}

	@Test
	public void horizontallMove(){
		board.setPiece(2, 2, 0, "knight");
		assertFalse("Cannot move horizontally", board.movePiece(2,2,4,2));
	}
	
	@Test
	public void verticalMove(){
		board.setPiece(2, 2, 0, "knight");
		assertFalse("Cannot move vertically", board.movePiece(2,2,2,4));
	}

}
