package Pieces;
import Board.Board;

public class Pawn extends Piece{

	public Pawn(int color, int currX, int currY) {
		super(color, currX, currY);
	}

	/*
	 * (non-Javadoc)
	 * @see Piece#checkValid(int, int, Board)
	 */
	public Boolean checkValid(int destX, int destY, Board board){
		boolean returnValue = false;
		if(this.color == 0){
			if((destY - this.currY) == 2 && this.currX == destX && board.checkPosition(destX, destY) == true && board.getPawnMove(this) == false){
				returnValue = true;
			}

			else if((destY - this.currY) == 1 && this.currX == destX && board.checkPosition(destX, destY) == true){
				returnValue = true;
			}

			else if((destY - this.currY) == 1 && Math.abs(this.currX - destX) == 1 && board.checkPosition(destX, destY) == false){
				Piece tempPiece = board.getPiece(destX, destY);
				if( tempPiece != null && tempPiece.color != this.color){
					returnValue = true;
				}
			}
		}

		else if(this.color == 1){
			if((destY - this.currY) == -2 && this.currX == destX && board.checkPosition(destX, destY) == true && board.getPawnMove(this) == false){
				returnValue = true;
			}

			else if((destY - this.currY) == -1 && this.currX == destX && board.checkPosition(destX, destY) == true){
				returnValue = true;
			}
			
			else if((destY - this.currY) == -1 && Math.abs(this.currX - destX) == 1 && board.checkPosition(destX, destY) == false){
				Piece tempPiece = board.getPiece(destX, destY);
				if( tempPiece != null && tempPiece.color != this.color){
					returnValue = true;
				}
			}
		}

		/*
		 * if pawn has moved, make sure the Boolean is changed
		 */
		if(returnValue){
			board.setPawnMove(this);
		}

		return returnValue;
	}
}