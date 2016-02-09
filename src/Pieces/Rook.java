package Pieces;
import Board.Board;

public class Rook extends Piece{

	public Rook(int color, int currX, int currY) {
		super(color, currX, currY);
	}
	
	/*
	 * Helper function to check x-values along rook's horizontal path to ensure no pieces are in the way
	 */
	public Boolean checkPathX(int start, int end, Board board, int destY){
		if(start < end){
			for(int i = start + 1; i < end; i++){
				if( board.checkPosition(i, destY) == false ){
					return false;
				}
			}
		} else{
			for(int i = start - 1; i > end + 1; i--){
				if( board.checkPosition(i, destY) == false ){
					return false;
				}
			}
		}

		return true;
	}
	
	/*
	 * Helper function to check y-values along rook's vertical path to ensure no pieces are in the way
	 */
	public Boolean checkPathY(int start, int end, Board board, int destX){
		if(start < end){
			for(int i = start + 1; i < end; i++){
				if( board.checkPosition(destX, i) == false ){
					return false;
				}
			}
		} else{
			for(int i = start - 1; i > end + 1; i--){
				if( board.checkPosition(destX, i) == false ){
					return false;
				}
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see Piece#checkValid(int, int, Board)
	 */
	public Boolean checkValid(int destX, int destY, Board board){
		if( (this.currX - destX) != 0 && (destY - this.currY) == 0 ){
			//moving left or right

			if( checkPathX(this.currX, destX, board, destY) == false){
				return false;
			}

			if(board.checkPosition(destX, destY) == false){
				//there is something in the last spot, check if opposite color

				if( board.getPiece(destX, destY) != null && board.getPiece(destX, destY).color == this.color){
					return false;
				}
			}

			return true;
		}

		else if( (this.currX - destX) == 0 && (this.currY - destY) != 0 ){
			//moving up or down

			if( checkPathY(this.currY, destY, board, destX) == false){
				return false;
			}

			if(board.checkPosition(destX, destY) == false){
				//there is something in the last spot, check if opposite color

				if( board.getPiece(destX, destY) != null && board.getPiece(destX, destY).color == this.color){
					return false;
				}
			}

			return true;
		}

		return false;
	}
}