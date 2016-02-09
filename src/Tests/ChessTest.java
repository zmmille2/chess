package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Board.Board;

public class ChessTest {
	
	Board board = new Board(8, 8);

	@Test
	public void pawnPlacementTest() {
		for(int i = 0; i < board.getSize(); i++){
			assertTrue("black pawns in position", board.getPiece(i,  1).getClass().toString().equals("class Pieces.Pawn"));
			assertTrue("white pawns in position", board.getPiece(i,  6).getClass().toString().equals("class Pieces.Pawn"));
		}
	}
	
	@Test
	public void rookPlacementTest(){
		System.out.println(board.getPiece(0, 0).getClass().toString());
		assertTrue("black rook in position", board.getPiece(0, 0).getClass().toString().equals("class Pieces.Rook"));
		assertTrue("black rook in position", board.getPiece(7, 0).getClass().toString().equals("class Pieces.Rook"));
		assertTrue("white rook in position", board.getPiece(0, 7).getClass().toString().equals("class Pieces.Rook"));
		assertTrue("white rook in position", board.getPiece(7, 7).getClass().toString().equals("class Pieces.Rook"));
	}
	
	@Test
	public void knightPlacementTest(){
		assertTrue("black knight in position", board.getPiece(1, 0).getClass().toString().equals("class Pieces.Knight"));
		assertTrue("black knight in position", board.getPiece(6, 0).getClass().toString().equals("class Pieces.Knight"));
		assertTrue("white knight in position", board.getPiece(1, 7).getClass().toString().equals("class Pieces.Knight"));
		assertTrue("white knight in position", board.getPiece(6, 7).getClass().toString().equals("class Pieces.Knight"));
	}
	
	@Test
	public void bishopPlacementTest(){
		assertTrue("black bishop in position", board.getPiece(2, 0).getClass().toString().equals("class Pieces.Bishop"));
		assertTrue("black bishop in position", board.getPiece(5, 0).getClass().toString().equals("class Pieces.Bishop"));
		assertTrue("white bishop in position", board.getPiece(2, 7).getClass().toString().equals("class Pieces.Bishop"));
		assertTrue("white bishop in position", board.getPiece(5, 7).getClass().toString().equals("class Pieces.Bishop"));
	}
	
	@Test
	public void kingPlacementTest(){
		assertTrue("black king in position", board.getPiece(3, 0).getClass().toString().equals("class Pieces.King"));
		assertTrue("white king in position", board.getPiece(3, 7).getClass().toString().equals("class Pieces.King"));
	}
	
	@Test
	public void queenPlacementTest(){
		assertTrue("black queen in position", board.getPiece(4, 0).getClass().toString().equals("class Pieces.Queen"));
		assertTrue("white queen in position", board.getPiece(4, 7).getClass().toString().equals("class Pieces.Queen"));
	}
	
	@Test
	public void camelPlacementTest(){
		assertTrue("black camel in position", board.getPiece(1, 2).getClass().toString().equals("class Pieces.Camel"));
		assertTrue("white camel in position", board.getPiece(1, 5).getClass().toString().equals("class Pieces.Camel"));
	}
	
	@Test
	public void squirrelPlacementTest(){
		assertTrue("black squirrel in position", board.getPiece(6, 2).getClass().toString().equals("class Pieces.Squirrel"));
		assertTrue("white squirrel in position", board.getPiece(6, 5).getClass().toString().equals("class Pieces.Squirrel"));
	}

}
