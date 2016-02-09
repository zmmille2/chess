package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Board.Board;

public class SquirrelTest {

	Board board = new Board(8, 8);
	
	@Test
	public void cannotTakeOwnColor() {
		board.setPiece(5, 3, 0, "pawn");
		assertFalse(board.movePiece(6, 2, 5, 3));
	}
	
	@Test
	public void canTakeEnemyPiece() {
		board.setPiece(5, 3, 1, "pawn");
		assertTrue("Can take enemy piece", board.movePiece(6, 2, 5, 3));
	}

	@Test
	public void canJumpOwnPieces(){
		board.setPiece(5, 3, 0, "pawn");
		assertTrue("Can jump over own pieces", board.movePiece(6, 2, 5, 4));
	}
	
	@Test
	public void canJumpEnemyPieces(){
		board.setPiece(5, 3, 1, "pawn");
		assertTrue("Can jump over enemy pieces", board.movePiece(6, 2, 5, 4));
	}
	
	@Test
	public void cannotMoveMoreThanTwo(){
		assertFalse("Cannot move more than two squares", board.movePiece(6,2,3,2));
		assertFalse("Cannot move more than two squares", board.movePiece(6,2,5,5));
	}
	
	@Test
	public void diagonalMove(){
		assertTrue("Can move diagonally", board.movePiece(6,2,4,4));
	}

	@Test
	public void horizontallMove(){
		assertTrue("Can move horizontally", board.movePiece(6,2,4,2));
	}
	
	@Test
	public void verticalMove(){
		assertTrue("Can move vertically", board.movePiece(6,2,6,4));
	}

}
