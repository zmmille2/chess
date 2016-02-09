package Pieces;
import Board.Board;

/*
 * 
 * Custom Chess Piece
 * Moves similar to the horse, but can move X: +/- 3 Y: +/- 1 or X: +/- 1 Y: +/- 3
 * 
 */
public class Camel extends Piece{

	public Camel(int color, int currX, int currY) {
		super(color, currX, currY);
	}

	public Boolean checkValid(int destX, int destY, Board board){
		if( Math.abs(destX - this.currX) == 3 && Math.abs(destY - this.currY) == 1 ){
			Piece piece = board.getPiece(destX, destY);
			if(piece != null && piece.color == this.color){
				return false;
			}
			return true;
		}
		
		else if( Math.abs(destY - this.currY) == 3 && Math.abs(destX - this.currX) == 1){
			Piece piece = board.getPiece(destX, destY);
			if(piece != null && piece.color == this.color){
				return false;
			}
			return true;
		}
		
		return false;
	}
}