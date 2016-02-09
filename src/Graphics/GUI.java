package Graphics;
import Pieces.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Board.Board;
import Board.Chess;
import Board.ChessController;

/*
 * 
 * This is the View class, that is responsible for the visual respresentation of the chessboard.
 * 
 */

public class GUI{
	private Board board;
	public JButton [][] chessboardSquares;
	private JPanel chessBoard;
	private JLayeredPane layeredPane;
	private Dimension boardSize;
	private int width, height;
	private final JLabel message = new JLabel("Welcome to chess!");
	private JLabel score = new JLabel("Score: 0 - 0 ");
	private static final String [] messages = {"Player1, your move.", "Player2, your move."};
	public JMenuBar tools;
	public JFrame window;
	private final JLabel winnerMsg = new JLabel("");
	private final JLabel illegalMove = new JLabel("");
	
	public void setMessage(int color){
		if( color == 0 ){
			message.setText(messages[0]);
		} else{
			message.setText(messages[1]);
		}
	}
	
	public void displayError(int error){
		if(error == 0){
			illegalMove.setText("Error: you are in check!");
		}
		
		else if(error == 1){
			illegalMove.setText("Error: you are in checkmate!");
		}
		else if(error == 2){
			illegalMove.setText("Error: that is not your piece!");
		}
		
		else if(error == 3){
			illegalMove.setText("Error: that is an illegal move!");
		}
		else{
			illegalMove.setText("");
		}
	}
	
	public void displayWinner(int player){
		String msg = "";
		if(player == 1){
			msg = "Player two wins!";
		}
		else if(player == 0){
			msg = "Player one wins!";
		}
		else{
			msg = "";
		}
		
		winnerMsg.setText(msg);
	}
	
	public void setScore(int x, int y){
		String newScore = "Score: "+ x + " - " + y;
		score.setText(newScore);
	}
	
	public void setActionListeners(ChessController logic){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				chessboardSquares[x][y].addActionListener(logic);
			}
		}
		
		Component[] components = tools.getComponents();
		for(int i = 0; i<components.length; i++)
	    {
	        if (components[i] instanceof JButton)
	        {
	            JButton button = (JButton)components[i];
	            button.addActionListener(logic);
	        }

	    }
	}
	
	public void clear(){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
                chessboardSquares[x][y].setIcon(null);
			}
		}
		
		arrangePieces();
		
		window.repaint();
	}
	
	public GUI(int width, int height){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        
        this.width = width;
        this.height = height;
        
        int size = width * height * 10;  
        
        window = new JFrame("Chess Board");
        window.setLayout( new GridLayout( width , height ) );
        
        Dimension boardSize = new Dimension( size, size+8 );
        window.setPreferredSize( boardSize );
        
        tools = new JMenuBar();
        JButton undo = new JButton("Undo");
        tools.add(new JButton("Undo"));
	    tools.add(new JButton("Forfeit"));
	    tools.add(new JButton("Restart"));
	    tools.add(message);
	    tools.add(new JSeparator());
	    tools.add(winnerMsg);
	    tools.add(new JSeparator());
	    tools.add(score);
	    tools.add(new JSeparator());
	    illegalMove.setForeground(Color.RED);
	    tools.add(illegalMove);
        
        window.setJMenuBar(tools);
        
        chessboardSquares = new JButton[width][height];
        
        for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				chessboardSquares[x][y] = new JButton();
				if( (x % 2 == 1 && y % 2 == 1) || (x % 2 == 0 && y % 2 == 0) ) { 
                    chessboardSquares[x][y].setBackground(Color.BLACK);
                }
                else {
                	chessboardSquares[x][y].setBackground(Color.WHITE);
                }
				chessboardSquares[x][y].setOpaque(true);
				chessboardSquares[x][y].setBorderPainted(false);
				window.add(chessboardSquares[x][y]);
			}
		}    

        arrangePieces();
        
        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	private void arrangePieces(){
		for(int i = 0; i < width; i++){
			
			ImageIcon blackPawn = new ImageIcon("Images/b_pawn.png");
			ImageIcon whitePawn = new ImageIcon("Images/w_pawn.png");
			chessboardSquares[i][1].setIcon(blackPawn);
			chessboardSquares[i][6].setIcon(whitePawn);
		}
		
		chessboardSquares[0][0].setIcon(new ImageIcon("Images/b_rook.png"));
		chessboardSquares[7][0].setIcon(new ImageIcon("Images/b_rook.png"));

		chessboardSquares[2][0].setIcon(new ImageIcon("Images/b_bishop.png"));
		chessboardSquares[5][0].setIcon(new ImageIcon("Images/b_bishop.png"));

		chessboardSquares[1][0].setIcon(new ImageIcon("Images/b_knight.png"));
		chessboardSquares[6][0].setIcon(new ImageIcon("Images/b_knight.png"));

		chessboardSquares[3][0].setIcon(new ImageIcon("Images/b_king.png"));
		chessboardSquares[4][0].setIcon(new ImageIcon("Images/b_queen.png"));
		
		chessboardSquares[0][7].setIcon(new ImageIcon("Images/w_rook.png"));
		chessboardSquares[7][7].setIcon(new ImageIcon("Images/w_rook.png"));

		chessboardSquares[2][7].setIcon(new ImageIcon("Images/w_bishop.png"));
		chessboardSquares[5][7].setIcon(new ImageIcon("Images/w_bishop.png"));
		
		chessboardSquares[1][7].setIcon(new ImageIcon("Images/w_knight.png"));
		chessboardSquares[6][7].setIcon(new ImageIcon("Images/w_knight.png"));

		chessboardSquares[3][7].setIcon(new ImageIcon("Images/w_king.png"));
		chessboardSquares[4][7].setIcon(new ImageIcon("Images/w_queen.png"));
		
		chessboardSquares[1][2].setIcon(new ImageIcon("Images/b_camel.png"));
		chessboardSquares[1][5].setIcon(new ImageIcon("Images/w_camel.png"));
		
		chessboardSquares[6][2].setIcon(new ImageIcon("Images/b_squirrel.png"));
		chessboardSquares[6][5].setIcon(new ImageIcon("Images/w_squirrel.png"));
	}
 
    public static void main(String[] args) {
        new GUI(8, 8);
    }
}
