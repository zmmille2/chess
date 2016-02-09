package Pieces;
import Board.Board;

public class Bishop extends Piece{

	public Bishop(int color, int currX, int currY) {
		super(color, currX, currY);
	}

	/*
	 * Helper function with Bishop movement logic
	 */
	public Boolean checkPath(int tempX, int tempY, int destX, Board board, int caseNumber){
		boolean stop = false;

		while(!stop){
			if(caseNumber == 0){
				tempX++;
				tempY++;
			}

			if(caseNumber == 1){
				tempX++;
				tempY--;
			}

			if(caseNumber == 2){
				tempX--;
				tempY++;
			}

			if(caseNumber == 3){
				tempX--;
				tempY--;
			}
			
			if(tempX == destX){
				break;
			}

			if( board.checkPosition(tempX, tempY) == false ){
				return false;
			}
		}

		if( board.checkPosition(tempX, tempY) == false ){
			if( board.getPiece(tempX, tempY) != null && board.getPiece(tempX, tempY).color == this.color){
				return false;
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see Piece#checkValid(int, int, Board)
	 */
	public Boolean checkValid(int destX, int destY, Board board){
		if( Math.abs(destX - this.currX) != Math.abs(destY - this.currY) ){
			return false;
		} 

		int tempX = this.currX;
		int tempY = this.currY;

		if( (destX - this.currX) > 0 ){
			if( (destY - this.currY) > 0 ){
				//X: +1, Y: +1

				return checkPath(tempX, tempY, destX, board, 0);
			} 
			else{
				//X: +1, Y: -1

				return checkPath(tempX, tempY, destX, board, 1);
			}
		}

		else if( (destX - this.currX) < 0 ){
			if( (destY - this.currY) > 0 ){
				//X: -1, Y: +1
				
				return checkPath(tempX, tempY, destX, board, 2);
			} 
			else{
				//X: -1, Y: -1

				return checkPath(tempX, tempY, destX, board, 3);
			}
		}

		return false;
	}
}