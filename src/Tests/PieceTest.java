package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Board.Board;

public class PieceTest {
	
	Board board = new Board(8, 8);

	@Test
	public void cannotMoveOutofBounds() {
		board.setPiece(3, 3, 0, "rook");
		assertFalse("Cannot move piece off board", board.movePiece(3, 3, 8, 3));
		assertFalse("Cannot move piece off board", board.movePiece(3, 3, -1, 3));
		assertFalse("Cannot move piece off board", board.movePiece(3, 3, 3, -1));
		assertFalse("Cannot move piece off board", board.movePiece(3, 3, 3, 8));
	}
	
	@Test
	public void invalidSetPiece(){
		assertFalse("Invalid Set Piece Name", board.setPiece(3, 3, 0, "lkasjdlaksjd"));
	}
	
	@Test
	public void invalidPieceMove(){
		assertFalse("No piece exists to move", board.movePiece(3, 3, 4, 4));
	}
	
	@Test
	public void invalidCurrentCoordinates(){
		assertFalse("Current coordinates out of bounds", board.movePiece(-1, -1, 3, 3));
	}

}
