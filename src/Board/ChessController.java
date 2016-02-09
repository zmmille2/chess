package Board;

import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.JButton;

import Graphics.GUI;
import Pieces.Piece;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class ChessController implements ActionListener{
	public static final int numPlayers = 2;
	public static ArrayList<ArrayList<Integer>> moves;
	public static ArrayList<Piece> takenPieces;
	public static ArrayList<Icon> takenIcons;
	public static Player player1, player2;
	GUI gui;
	Board board;
	boolean flag = false;
	int startX = 0;
	int startY = 0;
	int endX = 0;
	int endY = 0;
	Player [] playerArray = new Player[numPlayers];
	
	/*
	 * 
	 * This class is the Controller class that handles player input, and respectively changes
	 * the view and model based on input.
	 * 
	 */
	
	public ChessController(GUI gui, Board board) {
		this.gui = gui;
		this.board = board;
		moves = new ArrayList<>();
		takenPieces = new ArrayList<>();
		takenIcons = new ArrayList<>();
		
		boolean gameOver = false;
		
		player1 = new Player(1, "Player One");
		player2 = new Player(0, "Player Two");

		playerArray[0] = player1;
		playerArray[1] = player2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.displayWinner(-1);
		gui.displayError(-1);
		Player currentPlayer = Player.getPlayer(playerArray);
		
		String command = e.getActionCommand();
		if(command.equals("Undo")){
			//do undo stuff
			if(takenPieces.size() < 1){
				System.out.println("Beginning of the game, cannot undo. Please try again");
			}
			else{
				int i = 0;
//				while(i != 2){
					Piece piece = takenPieces.get(takenPieces.size() - 1);
					ArrayList<Integer> m = moves.get(moves.size() - 1);
					if(piece == null){
						//case: nothing was taken
						Icon temp = gui.chessboardSquares[m.get(2)][m.get(3)].getIcon();
						gui.chessboardSquares[m.get(0)][m.get(1)].setIcon(temp);
						gui.chessboardSquares[m.get(2)][m.get(3)].setIcon(null);
						
						Piece tempPiece = board.getPiece(m.get(2), m.get(3));
						board.removePiece(m.get(2), m.get(3));
						board.setPiece(m.get(0), m.get(1), tempPiece.getColor(), tempPiece.getClass().getName().substring(7).toLowerCase());
						
						System.out.println(board.getPiece(m.get(0), m.get(1)));
						System.out.println(board.getPiece(m.get(2), m.get(3)));
						
					} else{
						//piece was taken, tricky
						Icon tempTaken = takenIcons.get(takenIcons.size() - 1);
						Icon temp = gui.chessboardSquares[m.get(2)][m.get(3)].getIcon();
						gui.chessboardSquares[m.get(0)][m.get(1)].setIcon(temp);
						gui.chessboardSquares[m.get(2)][m.get(3)].setIcon(tempTaken);
						
						Piece tempPiece = board.getPiece(m.get(2), m.get(3));
						board.removePiece(m.get(2), m.get(3));
						board.setPiece(m.get(2), m.get(3), piece.getColor(), piece.getClass().getName().substring(7).toLowerCase());
						board.setPiece(m.get(0), m.get(1), tempPiece.getColor(), tempPiece.getClass().getName().substring(7).toLowerCase());
					
						System.out.println(board.getPiece(m.get(0), m.get(1)));
						System.out.println(board.getPiece(m.get(2), m.get(3)));
					}
					
					takenIcons.remove(takenIcons.size() - 1);
					takenPieces.remove(takenPieces.size() - 1);
					moves.remove(moves.size() - 1);
					
					
//					i++;
//				}
			}
			
			Player.setTurn(playerArray);
			gui.setMessage(currentPlayer.color);
		}
		
		else if(command.equals("Restart")){
			//do restart stuff
			Player.setTurn(playerArray);
			
			currentPlayer = Player.getPlayer(playerArray);
			
			System.out.println(currentPlayer.name+", would you like to restart?");
		}
		
		else if(command.equals("Forfeit")){
			Player.setTurn(playerArray);
			currentPlayer = Player.getPlayer(playerArray);
			currentPlayer.score++;
			
			gui.displayWinner(currentPlayer.color);
			
			Player.setTurn(playerArray);
			gui.setScore(playerArray[0].score, playerArray[1].score);
			
			gui.clear();
			board.clear();
		}
		
		else{
			if(!flag){
				for(int i = 0; i < 8; i++){
					for(int j = 0; j < 8; j++){
						if(e.getSource() == gui.chessboardSquares[i][j]){
							System.out.println("hi");
							startX = i;
							startY = j;
							break;
						}
					}
				}
				
				flag = true;
			} else{
				for(int i = 0; i < 8; i++){
					for(int j = 0; j < 8; j++){
						if(e.getSource() == gui.chessboardSquares[i][j]){
							endX = i;
							endY = j;
							break;
						}
					}
				}
				
				System.out.println("Moving from ("+ startX+", "+ startY+") to ("+endX+", "+endY+")");
				
				Piece taken = board.getPiece(endX, endY);
				Icon takenIcon = gui.chessboardSquares[endX][endY].getIcon();
				
				if( board.inCheck(currentPlayer.color)){
					gui.displayError(0);
				}
				
				if( board.getPiece(startX, startY) != null && board.getPiece(startX, startY).getColor() != currentPlayer.color){
					System.out.println("This is not your color piece. Please try again.");
					gui.displayError(2);
				}
				
				else if(board.inCheckMate(currentPlayer.color)){
					gui.displayError(1);
					Player.setTurn(playerArray);
					currentPlayer = Player.getPlayer(playerArray);
					currentPlayer.score++;
					
					gui.displayWinner(currentPlayer.color);
					
					Player.setTurn(playerArray);
					gui.setScore(playerArray[0].score, playerArray[1].score);
					
					gui.clear();
					board.clear();
				}
				
				else if( !board.movePiece(startX, startY, endX, endY) ){
					
					System.out.println("This is not a valid move, please try again.");
					gui.displayError(-1);
				}
				
				else{
					Icon temp = gui.chessboardSquares[startX][startY].getIcon();
					gui.chessboardSquares[startX][startY].setIcon(null);
					gui.chessboardSquares[endX][endY].setIcon(temp);
					
					takenPieces.add(taken);
					takenIcons.add(takenIcon);
					
					System.out.println(takenIcon);
					
					ArrayList<Integer> move = new ArrayList<>();
					move.add(startX);
					move.add(startY);
					move.add(endX);
					move.add(endY);
					moves.add(move);
					Player.setTurn(playerArray);
					gui.setMessage(currentPlayer.color);
				}
				
				flag = false;
			}
			
		}
	}
}