/**
 * Handles events for the Tetris Game.  User events (key strokes) as well as periodic timer
 * events.
 * 
 * @author CSC 143 and Annmarie
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class EventController extends KeyAdapter implements ActionListener {

	private Game game; // current game: grid and current piece
	private Timer timer;

	private static final double PIECE_MOVE_TIME = 0.8; // wait 0.8 s every time
														// the piece moves down
														// increase to slow it
														// down

	private boolean gameOver;

	/**
	 * Creates an EventController to handle key and timer events.
	 * 
	 * @param game
	 *            the game this is controlling
	 */
	public EventController(Game game) {
		this.game = game;
		gameOver = false;
		double delay = 1000 * PIECE_MOVE_TIME; // in milliseconds
		timer = new Timer((int) delay, this);
		timer.setCoalesce(true); // if multiple events pending, bunch them to
		// 1 event
		timer.start();
	}

	/**
	 * Responds to special keys being pressed.
	 * 
	 * Currently just responds to the space key and the q(uit) key
	 */
	public void keyPressed(KeyEvent e) {
		// if 'Q', quit the game
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			((JFrame) e.getSource()).dispose();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_N){
			//START A NEW GAME
			//ADD CODE HERE LATER
			//EXAMPLE:
			// theGame.newGame();
			// if ('timer.isRunning()){
			//	timer.start();
			//	}
		
		}
		
		if (!gameOver) {
			switch (e.getKeyCode()) {
			/*case KeyEvent.VK_SPACE: //THIS CODE WAS ALREADY HERE. THIS IS THE SPACEBAR
				handleMove(Direction.DOWN);
				break; */
			
			
			// AT THE TIME OF ASSIGNMENT 2, 
			//THERE IS NO KEYEVENT THAT ACTIVATES Direction.DOWN
			//ONLY THE actionPerformed(Direction direction) METHOD IN THIS CLASS
			//USES IT (WHICH OVERRIDES THE actionPerformed() in standard class ActionListener)
			//TO MOVE THE PIECE DOWN ACCORDING TO THE TIMER
			
			// HANDLE other keystrokes here
			case KeyEvent.VK_LEFT: //LEFT ARROW
				handleMove(Direction.LEFT);
				break;
			case KeyEvent.VK_RIGHT: //RIGHT ARROW
				handleMove(Direction.RIGHT);
				break;
			case KeyEvent.VK_DOWN:
				handleMove(Direction.ROTATEDOWN);
				break;
			case KeyEvent.VK_UP:
				handleMove(Direction.ROTATEUP);
				break;
			case KeyEvent.VK_SPACE: 
				handleMove(Direction.DROP);//DROP DOWN
				break;
			case KeyEvent.VK_S: //S BUTTON
				timer.stop();//PAUSES THE GAME
				break;
			case KeyEvent.VK_R: //R BUTTON
				timer.restart();//STARTS THE GAME FROM PAUSE POSITION
				break;
			default:
				break;
			}
		}
	}

	/** Updates the game periodically based on a timer event */
	public void actionPerformed(ActionEvent e) {
		handleMove(Direction.DOWN);
	}

	/**
	 * Update the game by moving in the given direction
	 */
	private void handleMove(Direction direction) {
		
		switch (direction){
		case DOWN:
			game.movePiece(direction);
			helperMethod();
			break;
		case DROP: //GAME CALLS SQUARE, WHERE THE DROP CODE LIES
			game.dropPiece(direction);
			helperMethod();
			break;
		case ROTATEDOWN:
			game.rotatePiece(direction);//GAME CALLS SQUARE, WHERE THE ROTATE CODE LIES
			System.out.println("EventController.rotateMove method ACTIVATED");
			helperMethod();
			break;
		case ROTATEUP:
			game.rotatePiece(direction);//GAME CALLS SQUARE, WHERE THE ROTATE CODE LIES
			System.out.println("EventController.rotateMove method ACTIVATED");
			helperMethod();
			break;
		default:
			game.movePiece(direction);
			helperMethod();
			break;
		
		}
		
	}

	//HELPER METHOD COMMON TO MOTION METHODS IN THIS CLASS
	//USES OTHER METHODS TO CHECK IF THE GAME IS OVER
	//IF THE GAME IS OVER, IT STOPS THE TIMER.
	private void helperMethod(){
		gameOver = game.isGameOver();
		if (gameOver)
			timer.stop();
	}
}
