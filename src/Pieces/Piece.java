package Pieces;
import Board.Board;

public abstract class Piece {
	int color, currX, currY;

	public Piece(int color, int currX, int currY) {
		this.color = color;
		this.currX = currX;
		this.currY = currY;
	}
	
	public int getX(){
		return this.currX;
	}
	
	public int getY(){
		return this.currY;
	}
	
	public int getColor(){
		return this.color;
	}
	
	public void setX(int x){
		this.currX = x;
	}
	
	public void setY(int y){
		this.currY = y;
	}
	
	public abstract Boolean checkValid(int destX, int destY, Board board);
}