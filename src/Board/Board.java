package Board;
import java.util.*;
import java.util.Map;

import Pieces.Piece;

import Pieces.Bishop;
import Pieces.Camel;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import Pieces.Squirrel;

import java.util.HashMap;
import java.util.ArrayList;

public class Board {
	int width, height;
	Boolean [][] boardPositions;
	Piece [][] pieceArray;
	Map<String, Boolean> pawnMap;
	ArrayList<Piece> whitePieces;
	ArrayList<Piece> blackPieces;
	
	public static final int [] validKingMoves = {-1, 0, 1};
	public static final int numPieces = 18;
	public static final int kingLocation = 3;
	public int nextIndex;

	
	public void clear(){
		pawnMap = new HashMap<String, Boolean>();
		blackPieces = new ArrayList<Piece>();
		whitePieces = new ArrayList<Piece>();
		boardPositions = new Boolean[width][height];
		pieceArray = new Piece[width][height];
		nextIndex = numPieces;
		
		setPieces();
	}
	
	/*
	 * Returns dimension of board.
	 */
	public int getSize(){
		return width;
	}
	
	/*
	 * public function to find a piece on the board
	 * returns the piece if it exists, null otherwise
	 * 
	 */
	public Piece findPiece(int x, int y){
		return pieceArray[x][y];
	}
	
	/*
	 * public function to check if a space on the board is occupied or not
	 */
	public Boolean checkPosition(int x, int y){
		return boardPositions[x][y];
	}
	
	/*
	 * Function to see if pawn has made first move or not
	 */
	public Boolean getPawnMove(Pawn pawn){
		return pawnMap.get( pawn.toString() );
	}
	
	/*
	 * Function to set when pawn has made first move
	 */
	public void setPawnMove(Pawn pawn){
		if( pawnMap.get( pawn.toString() ) == false){
			pawnMap.put( pawn.toString() , true);
		}
	}

	/*
	 * Function to check whether destination coordinates are within game bounds
	 * Takes in an (x, y) position
	 */
	public Boolean checkBounds(int destX, int destY){
		if(destX >= boardPositions.length || destX < 0){
			return false;
		} 

		else if(destY >= boardPositions.length || destY < 0){
			return false;
		}

		else {
			return true;
		}
	}

	/*
	 * Function to move piece on the board after checking to make sure it is within bounds and a valid move
	 * Takes in two (x, y) coordinates as parameters
	 */
	public Boolean movePiece(int currX, int currY, int destX, int destY){
		if(checkBounds(currX, currY) == false){
			return false;
		}
		Piece piece = pieceArray[currX][currY];
		if(piece == null){
			return false;
		}
		if(checkBounds(destX, destY) && piece.checkValid(destX, destY, this) ){
			boardPositions[piece.getX()][piece.getY()] = true;
			boardPositions[destX][destY] = false;

			pieceArray[destX][destY] = pieceArray[piece.getX()][piece.getY()];
			pieceArray[piece.getX()][piece.getY()] = null;

			piece.setX(destX);
			piece.setY(destY);

			return true;
		} else{
			return false;
		}
	}
	
	/*
	 * Function used for testing functionality of chess game
	 * 
	 * Gets the piece at (x,y) location
	 */
	public Piece getPiece(int x, int y){
		return pieceArray[x][y];
	}
	
	/*
	 * Removes a piece given an (x,y) position from the chessboard
	 */
	
	public void removePiece(int posX, int posY){
		Piece piece = pieceArray[posX][posY];
		if(piece.getColor() == 0){
			blackPieces.remove(piece);
		} else{
			whitePieces.remove(piece);
		}
		pieceArray[posX][posY] = null;
		boardPositions[posX][posY] = true;
		if(piece.getClass().getName().substring(7).toLowerCase().equals("pawn")){
			pawnMap.remove(piece);
		}
	}
	
	/*
	 * Function used for testing functionality of chess game
	 * 
	 * Takes the starting (x,y) coordinate, piece color, and piece name
	 */

	public Boolean setPiece(int x, int y, int color, String pieceName){
		Piece testingPiece = null;
		
		switch(pieceName){
			case "pawn": 		testingPiece = new Pawn(color, x, y);
								break;
			case "rook": 		testingPiece = new Rook(color, x, y);
								break;
			case "knight": 		testingPiece = new Knight(color, x, y);
								break;
			case "bishop": 		testingPiece = new Bishop(color, x, y);
								break;
			case "queen": 		testingPiece = new Queen(color, x, y);
								break;
			case "king": 		testingPiece = new King(color, x, y);
								break;
			case "camel": 		testingPiece = new Camel(color, x, y);
								break;
			case "squirrel": 	testingPiece = new Squirrel(color, x, y);
								break;
			default:			testingPiece = null;
								break;
		}
		
		if(testingPiece == null){
			return false;
		}
		
		if(color == 0){
			blackPieces.add(testingPiece);
		} else{
			whitePieces.add(testingPiece);
		}
		
		if(testingPiece.equals("pawn")){
			pawnMap.remove(testingPiece);
		}
		
		boardPositions[x][y] = false;
		pieceArray[x][y] = testingPiece;
		
		return true;
	}
	
	/*
	 * 
	 * Method to get an array of the enemy pieces
	 * Takes in a color as a parameter
	 * 
	 */
	
	public ArrayList<Piece> getEnemyPieces(int color){
		if(color == 1){
			return blackPieces;
		} else{
			return whitePieces;
		}
	}
	
	/*
	 * Checks if player is in check
	 * Takes in a color as a parameter
	 */
	public Boolean inCheck(int color){
		Piece king;
		ArrayList<Piece> enemyPieces, friendlyPieces;
		if(color == 0){
			enemyPieces = whitePieces;
			friendlyPieces = blackPieces;
		} else {
			enemyPieces = blackPieces;
			friendlyPieces = whitePieces;
		}
		
		king = getKing(friendlyPieces, color);
		
		int kingX = king.getX();
		int kingY = king.getY();
		
		for(int i = 0; i < enemyPieces.size(); i++){
			Piece enemyPiece = enemyPieces.get(i);
			if(enemyPiece.checkValid(kingX, kingY, this)){
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 
	 * Returns the King given a color and an array of friendly pieces
	 * 
	 */
	public Piece getKing(ArrayList <Piece> friendlyPieces, int color){
		Piece king = null;
		for( int i = 0; i < friendlyPieces.size(); i++){
			Piece piece = friendlyPieces.get(i);
			if( piece.getClass().getName().substring(7).toLowerCase().equals("king")){
				king = piece;
			}
		}
		
		return king;
	}
	
	/*
	 * Method to check if player is in stalemate
	 * Will first check to make sure player is not in check, then making sure if any pieces can make a move
	 * Takes in a color as a parameter
	 * 
	 */
	public Boolean inStaleMate(int color){
		Piece king = null;
		ArrayList<Piece> friendlyPieces;
		
		if(color == 0){
			friendlyPieces = blackPieces;
		} else {
			friendlyPieces = whitePieces;
		}
		
		king = getKing(friendlyPieces, color);
		
		if( inCheck(king.getColor() ) ){
			return false;
		}
		
		for(int k = 0; k < friendlyPieces.size(); k++){
			Piece piece = friendlyPieces.get(k);			
			for(int i = 0; i < width; i++){
				for(int j = 0; j < height; j++){
					if( piece.checkValid(i, j, this) ){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/*
	 * Method to check if a king can move in StaleMate and CheckMate conditions
	 * 
	 */
	public Boolean canKingMove(int destX, int destY, Piece piece){
		if( Math.abs(destX - piece.getX()) != 1 && (destX - piece.getX()) != 0){
			return false;
		}
		
		if( Math.abs(destY - piece.getY()) != 1 && (destY - piece.getY()) != 0){
			return false;
		}
		
		if( getPiece(destX, destY) != null && getPiece(destX, destY).getColor() == piece.getColor()){
			return false;
		}
		
		return true;
	}
	
	
	/*
	 * Checks if player is in checkmate
	 * First makes sure if a player is in check, then checks if a king can move somewhere to get out of check, or
	 * if a piece can move in front of the king to block checkmate
	 * Takes in a color as a parameter
	 */
	public Boolean inCheckMate(int color){
		if( !inCheck(color)){
			return false;
		}
		
		Piece king;
		ArrayList<Piece> friendlyPieces;
		
		if(color == 0){
			friendlyPieces = blackPieces;
		} else {
			friendlyPieces = whitePieces;
		}
		
		king = getKing(friendlyPieces, color);
		
		//can a king move somewhere else to get out of check? or have piece move to block checkmate?
		
		for(int i = 0; i < validKingMoves.length; i++){
			for(int j = 0; j < validKingMoves.length; j++){
				int posX = king.getX() + validKingMoves[i];
				int posY = king.getY() + validKingMoves[j];
				boolean canMove = king.checkValid( posX, posY , this);
				if( canMove ){
					return true;
				} else{
					for(int k = 0; k < friendlyPieces.size(); k++){
						if( friendlyPieces.get(k).checkValid(posX , posY, this) ){
							Piece piece = friendlyPieces.get(k);
							this.setPiece( posX, posY, king.getColor(), piece.getClass().getName().substring(7).toLowerCase() );
							boolean inCheck = inCheck(king.getColor());
							this.removePiece(posX, posY);
							
							if( !inCheck ){
								this.movePiece(piece.getX(), piece.getY(), posX, posY);
								return true;
							}
						}
					}
				}
				
			}
		}
		
		return false;
	}
	
	/*
	 * Creates an initial empty chessboard with no pieces
	 */
	public Board(int width, int height, String type){
		this.width = width;
		this.height = height;

		pawnMap = new HashMap<String, Boolean>();
		blackPieces = new ArrayList<Piece>();
		whitePieces = new ArrayList<Piece>();
		boardPositions = new Boolean[width][height];
		pieceArray = new Piece[width][height];
		nextIndex = numPieces;
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				boardPositions[i][j] = true;
			}
		}
	}
	
	/*
	 * Initialization of an initial configuration on chessboard
	 */
	public Board(int width, int height){
		this.width = width;
		this.height = height;

		pawnMap = new HashMap<String, Boolean>();
		blackPieces = new ArrayList<Piece>();
		whitePieces = new ArrayList<Piece>();
		boardPositions = new Boolean[width][height];
		pieceArray = new Piece[width][height];
		nextIndex = numPieces;
		
		setPieces();
	}
	
	/*
	 * 
	 * Arranges initial configuration of pieces on chessboard
	 */
	public void setPieces(){
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				boardPositions[i][j] = true;
			}
		}

		for(int i = 0; i < width; i++){
			pieceArray[i][1] = new Pawn(0, i, 1);
			pieceArray[i][6] = new Pawn(1, i, 6);
			boardPositions[i][1] = false;
			boardPositions[0][i] = false;
			boardPositions[i][6] = false;
			boardPositions[7][i] = false;
			boardPositions[1][2] = false;
			boardPositions[1][5] = false;
			boardPositions[6][2] = false;
			boardPositions[6][5] = false;
			

			pawnMap.put(pieceArray[i][1].toString(), false);
			pawnMap.put(pieceArray[i][6].toString(), false);
		}
		
		pieceArray[0][0] = new Rook(0, 0, 0);
		pieceArray[7][0] = new Rook(0, 7, 0);

		pieceArray[2][0] = new Bishop(0, 2, 0);
		pieceArray[5][0] = new Bishop(0, 5, 0);

		pieceArray[1][0] = new Knight(0, 1, 0);
		pieceArray[6][0] = new Knight(0, 6, 0);

		pieceArray[3][0] = new King(0, 3, 0);
		pieceArray[4][0] = new Queen(0, 4, 0);
		
		pieceArray[0][7] = new Rook(1, 0, 7);
		pieceArray[7][7] = new Rook(1, 7, 7);

		pieceArray[2][7] = new Bishop(1, 2, 7);
		pieceArray[5][7] = new Bishop(1, 5, 7);
		
		pieceArray[1][7] = new Knight(1, 1, 7);
		pieceArray[6][7] = new Knight(1, 6, 7);

		pieceArray[3][7] = new King(1, 3, 7);
		pieceArray[4][7] = new Queen(1, 4, 7);
		
		pieceArray[1][2] = new Camel(0, 1, 2);
		pieceArray[1][5] = new Camel(1, 1, 5);
		
		pieceArray[6][2] = new Squirrel(0, 6, 2);
		pieceArray[6][5] = new Squirrel(1, 6, 5);
		
		int i;
		for(i = 0; i < numPieces - 2; i++){
			if(i < 8){
				blackPieces.add(pieceArray[i][0]);
				whitePieces.add(pieceArray[i][7]);
			} else {
				blackPieces.add(pieceArray[i - 8][1]);
				whitePieces.add(pieceArray[i - 8][6]);
			}
		}
		
		blackPieces.add(pieceArray[1][2]);
		whitePieces.add(pieceArray[1][5]);
		blackPieces.add(pieceArray[6][2]);
		whitePieces.add(pieceArray[6][5]);
	}	
}