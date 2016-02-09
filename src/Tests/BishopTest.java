package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Board.Board;

public class BishopTest {
	
	Board board = new Board(8, 8);

	@Test
	public void cannotMoveOverOwnColor() {
		assertFalse("Unable to move over own color piece", board.movePiece(2, 0, 4, 2));
	}
	
	@Test
	public void cannotTakeOwnColor() {
		assertFalse("Unable to take own color piece", board.movePiece(2, 0, 3, 1));
	}
	
	@Test
	public void cannotMoveHorizantal(){
		board.setPiece(2, 3, 0, "bishop");
		assertFalse("Cannot move strictly right", board.movePiece(2,3,4,3));
		assertFalse("Cannot move strictly left", board.movePiece(2,3,0,3));
	}
	
	@Test
	public void cannotMoveVertical(){
		board.setPiece(2, 3, 0, "bishop");
		assertFalse("Cannot move strictly up", board.movePiece(2,3,2,5));
		assertFalse("Cannot move strictly down", board.movePiece(2,3,2,2));
	}
	
	@Test
	public void canMoveDiagonal(){
		board.setPiece(2, 3, 0, "bishop");
		assertTrue("Can move diagonal, right up", board.movePiece(2, 3, 3, 4));
		assertTrue("Can move diagonal, left up", board.movePiece(3, 4, 2, 5));
		assertTrue("Can move diagonal, left down", board.movePiece(2, 5, 1, 4));
		assertTrue("Can move diagonal, right down", board.movePiece(1, 4, 2, 3));
	}
	
	@Test
	public void canTakeEnemyPiece() {
		board.setPiece(2, 3, 0, "bishop");
		board.setPiece(3, 4, 1, "bishop");
		assertTrue("Able to take enemy piece", board.movePiece(2,3,3,4));
	}
	
	@Test
	public void cannotJumpOverEnemyPiece() {
		board.setPiece(2, 3, 0, "bishop");
		board.setPiece(3, 4, 1, "bishop");
		assertFalse("Unable to move over enemy piece", board.movePiece(2,3,4,5));
	}
	
	@Test
	public void cannotMakeLShape(){
		board.setPiece(2, 3, 0, "bishop");
		assertFalse("Unable to make L shape", board.movePiece(2, 3, 4, 4));
	}

}
