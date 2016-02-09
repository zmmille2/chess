package Tests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Board.Board;
import Pieces.Piece;

public class BoardTest {

	Board board = new Board(8, 8, "empty");
	
	@Test
	public void notInStaleMate(){
		board.setPiece(7, 7, 0, "king");
		board.setPiece(5, 6, 1, "king");
		board.setPiece(6, 5, 1, "queen");
		board.setPiece(2, 2, 0, "bishop");
		assertFalse("Board is not in StaleMate", board.inStaleMate(0) );
	}
	
	@Test
	public void InStaleMate(){
		board.setPiece(7, 7, 0, "king");
		board.setPiece(5, 6, 1, "king");
		board.setPiece(6, 5, 1, "queen");
		assertTrue("Board is in StaleMate", board.inStaleMate(0) );
	}
	
	@Test
	public void notInCheckMate(){
		board.setPiece(5, 4, 1, "king");
		board.setPiece(7, 0, 1, "rook");
		board.setPiece(7, 5, 0, "king");
		assertTrue("Board is not CheckMate", board.inCheckMate(0) );
	}
	
	@Test
	public void inCheckMate(){
		board.setPiece(5, 4, 1, "king");
		board.setPiece(7, 0, 1, "rook");
		board.setPiece(7, 4, 0, "king");
		assertTrue("Board is in CheckMate", board.inCheckMate(0) );
	}
}
