package Pieces;
import Board.Board;

/*
 * 
 * Custom Chess Piece
 * Can move +/- 0, 1, 2 spots in any direction
 * 
 */

public class Squirrel extends Piece{

	public Squirrel(int color, int currX, int currY) {
		super(color, currX, currY);
	}
	
	public Boolean checkValid(int destX, int destY, Board board){
		if( Math.abs(destX - this.currX) < 3 && Math.abs(destY - this.currY) < 3){
			Piece piece = board.getPiece(destX, destY);
			if( piece != null && piece.color == this.color){
				return false;
			}
			
			return true;
		}
		
		return false;
	}
}