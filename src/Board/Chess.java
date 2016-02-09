package Board;

import Graphics.GUI;

/*
 * Chess Game class. Includes MVC structure, where the GUI is the View, the Board is the Model, 
 * and ChessController is the Controller.
 * 
 */

public class Chess {
	
	public static void main(String [] args){
		GUI gui = new GUI(8, 8);
		Board board = new Board(8, 8);
		
		ChessController controller = new ChessController(gui, board);
		gui.setActionListeners(controller);		
		
	}
}
