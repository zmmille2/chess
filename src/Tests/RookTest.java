package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Board.Board;

public class RookTest {

	Board board = new Board(8, 8);
	
	@Test
	public void cannotMoveOverPiece() {
		assertFalse("Cannot move over own piece", board.movePiece(0,0,0,2));
	}
	
	@Test
	public void cannotTakeOwnPiece() {
		assertFalse("Cannot take own piece", board.movePiece(0,0,0,1));
	}
	
	@Test
	public void cannotMoveDiagonal() {
		board.setPiece(2, 3, 0, "rook");
		assertFalse("Cannot move diagonal", board.movePiece(2,3,3,4));
		assertFalse("Cannot move diagonal", board.movePiece(2,3,1,4));
		assertFalse("Cannot move diagonal", board.movePiece(2,3,3,2));
		assertFalse("Cannot move diagonal", board.movePiece(2,3,1,2));
	}
	
	@Test
	public void canMoveHorizantal() {
		board.setPiece(2, 3, 0, "rook");
		assertTrue("If no pieces in the way, can move right", board.movePiece(2,3,4,3));
		assertTrue("If no pieces in the way, can move left", board.movePiece(4,3,1,3));		
	}
	
	@Test
	public void canMoveVertical() {
		board.setPiece(2, 3, 0, "rook");
		assertTrue("If no pieces in the way, can move up", board.movePiece(2,3,2,5));
		assertTrue("If no pieces in the way, can move down", board.movePiece(2,5,2,2));
	}
	
	@Test
	public void canTakeEnemyPiece() {
		board.setPiece(2, 3, 0, "rook");
		board.setPiece(2, 4, 1, "rook");
		assertTrue("Able to take enemy piece", board.movePiece(2,3,2,4));
	}
	
	@Test
	public void cannotJumpOverEnemyPiece() {
		board.setPiece(2, 3, 0, "rook");
		board.setPiece(2, 4, 1, "rook");
		assertFalse("Unable to move over enemy piece", board.movePiece(2,3,2,5));
	}
	
	@Test
	public void cannotMakeLShape(){
		board.setPiece(2, 3, 0, "rook");
		assertFalse("Unable to make L shape", board.movePiece(2, 3, 4, 5));
	}

}
