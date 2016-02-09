package Pieces;
import Board.Board;

public class Knight extends Piece{

	public Knight(int color, int currX, int currY) {
		super(color, currX, currY);
	}
	
	/*
	 * (non-Javadoc)
	 * @see Piece#checkValid(int, int, Board)
	 */
	public Boolean checkValid(int destX, int destY, Board board){
		if( ( Math.abs(destX - this.currX) == 2 && Math.abs(destY - this.currY) == 1 ) || 
			( Math.abs(destX - this.currX) == 1 && Math.abs(destY - this.currY) == 2 ) ){

			if( board.checkPosition(destX, destY) == false){
				if( board.getPiece(destX, destY) != null && board.getPiece(destX, destY).color == this.color){
					return false;
				}
			}

			return true;
		}

		else{
			return false;
		}
	}
}