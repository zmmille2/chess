package Pieces;
import java.util.ArrayList;

import Board.Board;

public class King extends Piece{

	public King(int color, int currX, int currY) {
		super(color, currX, currY);
	}
	
	/*
	 * Method to check if the king can move to a position without being put in check
	 */
	public Boolean canKingMove(int destX, int destY, Board board){
		ArrayList<Piece> enemyPieces = board.getEnemyPieces(this.color);
		
		for(int i = 0; i < enemyPieces.size(); i++){
			Piece enemyPiece = enemyPieces.get(i);
			String type = enemyPiece.getClass().getName().substring(7).toLowerCase();
				if(type.equals("king") && board.canKingMove(destX, destY, enemyPiece)){
					return false;
				}
				else if( enemyPiece.checkValid(destX, destY, board) ){
					return false;
				}
		}
		
		return true;
	}
	
	
	/*
	 *Method to check if a king can move to a location on the board 
	 * 
	 */
	public Boolean checkValid(int destX, int destY, Board board){
		
		if( Math.abs(destX - this.currX) != 1 && (destX - this.currX) != 0){
			return false;
		}
		
		if( Math.abs(destY - this.currY) != 1 && (destY - this.currY) != 0){
			return false;
		}
		
		if( board.getPiece(destX, destY) != null && board.getPiece(destX, destY).color == this.color){
			return false;
		}
		
		if( !canKingMove(destX, destY, board) ){
			return false;
		}
		
		return true;
	}
}