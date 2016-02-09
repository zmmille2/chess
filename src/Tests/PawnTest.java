package Tests;
import static org.junit.Assert.*;
import org.junit.Test;

import Board.Board;

public class PawnTest {

Board board = new Board(8, 8);

	@Test
	public void moveOneVertical(){
		assertTrue("Pawn can move one space vertically.", board.movePiece(2, 1, 2, 2));
	}

	@Test
	public void firstMoveTwoVertical() {
		assertTrue("If first move, pawn can move two spaces vertically.", board.movePiece(2, 1, 2, 3));
	}
	
	@Test
	public void secondMoveTwoVertical() {
		board.movePiece(2, 1, 2, 3);
		assertFalse("After the second move, the pawn cannot move 2 spaces vertically", board.movePiece(2, 3, 2, 5));
	}
	
	@Test
	public void cannotMoveBackward() {
		assertFalse("Pawn cannot move backward.", board.movePiece(1, 1, 1, 0));
	}
	
	@Test
	public void cannotMoveHorizontal() {
		assertFalse("Pawns cannot move left.", board.movePiece(1, 1, 0, 1));
		assertFalse("Pawns cannot move right.", board.movePiece(1, 1, 2, 1));
	}
	
	@Test
	public void cannotMoveThroughPiece(){
		board.setPiece(1, 2, 1, "pawn");
		assertFalse("Pawns cannot move forward through pieces", board.movePiece(1, 1, 1, 2));
	}
	
	@Test
	public void cannotMoveDiagonal() {
		assertFalse("Pawn cannot move diagonal forwards if no enemy piece", board.movePiece(1, 1, 0, 0));
		assertFalse("Pawn cannot move diagonal forwards if no enemy piece", board.movePiece(1, 1, 2, 2));
		assertFalse("Pawn cannot move diagonal backwards", board.movePiece(1, 1, 2, 0));
		assertFalse("Pawn cannot move diagonal backwards", board.movePiece(1, 1, 0, 2));
	}

	@Test
	public void captureDiagonalForward() {
		board.setPiece(2, 2, 1, "pawn");
		assertTrue("Pawn captures forward",board.movePiece(1, 1, 2, 2));
	}
}