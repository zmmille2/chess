package Pieces;
import Board.Board;

public class Queen extends Piece{

	public Queen(int color, int currX, int currY) {
		super(color, currX, currY);
	}
	
	/*
	 * Bishop movement helper function logic
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
		if( (destX - this.currX) == 0 || (destY - this.currY) == 0){
			//Rook logic
			
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
		else if( Math.abs(destX - this.currX) == Math.abs(destY - this.currY) ){
			//Bishop logic
			
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
		
		return false;
	}
}
