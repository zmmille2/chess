package Board;

/*
 * 
 * This is the player class, which helps keep track of turns as well as score of the players.
 * 
 */
public class Player {
	int color;
	String name;
	boolean inCheck;
	boolean turn;
	int score;
	
	public static void setTurn(Player [] array){
		array[0].turn = !array[0].turn;
		array[1].turn = !array[1].turn;
	}
	
	public static void setRestartScore(Player [] array){
		array[0].score++;
		array[1].score++;
	}
	
	public static void setForfeitScore(Player [] array){
		if(array[0].turn){
			array[1].score++;
		} else{
			array[0].score++;
		}
	}
	
	public static Player getPlayer(Player [] array){
		if(array[0].turn){
			return array[0];
		} else{
			return array[1];
		}
	}
	
	public Player(int color, String name){
		this.score = 0;
		this.name = name;
		this.color = color;
		inCheck = false;
		if(color == 1){
			turn = true;
		} else{
			turn = false;
		}
	}
	
}
