import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 * Manages the game Tetris. Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 * 
 * @author CSC 143
 */
public class Game {

	private Grid grid; // the grid that makes up the Tetris board

	private Tetris display; // the visual for the Tetris game

	// private LShape piece; // the current piece that is dropping

	private AbstractPiece piece; // the current piece that is dropping //GAME
									// CAN ACCESS ABSTRACTPIECE CLASS BECAUSE IT
									// IS IN THE SAME PACKAGE. //DON'T EXTEND
									// HERE BECAUSE IT DOESN'T INHERIT ANY
									// METHODS OF ABSTRACTPIECE

	private boolean isOver; // has the game finished?

	// private int randomPiece = Random.nextInt(8);

	// DECLARE VARIABLE TO HOLD THE NUMBER OF THE RANDOM PIECE TO DROP FROM
	// ABOVE
	private int randomPieceNum;

	// INSTANTIATE NEW RANDOM AT THE CLASS LEVEL SO IT CAN BE ACCESSED BY
	// MULTIPLE METHODS
	Random rand = new Random();

	/**
	 * Creates a Tetris game
	 * 
	 * @param Tetris
	 *            the display
	 */
	public Game(Tetris display) {
		grid = new Grid();
		this.display = display;
		// piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);

		// GET RANDOM NUMBER
		// GENERATES A NUMBER FROM 1 TO 7, INCLUSIVE
		// THEN CALL newRandomPiece TO RETURN A SHAPE

		randomPieceNum = rand.nextInt(7) + 1; // from 1 to 7, inclusive
		newRandomPiece(randomPieceNum);

		isOver = false;
	}

	// TO-DO: PUT CODE IN HERE THAT WILL CREATE ANY PIECE
	/*
	 * public void newPiece(AbstractPiece ap, int x, int y, Grid grid){
	 * 
	 * //piece = new LShape(1, Grid.WIDTH / 2 - 1, grid); //piece2 = new
	 * AbstractPiece(x,y,grid);
	 * 
	 * 
	 * //AbstractPiece piece = new ap(x,y,grid); }
	 */

	public AbstractPiece newRandomPiece(int randomPieceNum) {

		switch (randomPieceNum) {

		case 1:
			piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
			break;

		case 2:
			piece = new JShape(1, Grid.WIDTH / 2 - 1, grid);
			break;

		case 3:
			piece = new ZShape(1, Grid.WIDTH / 2 - 1, grid);
			break;

		case 4:
			piece = new TShape(1, Grid.WIDTH / 2 - 1, grid);
			break;

		case 5:
			piece = new SquareShape(1, Grid.WIDTH / 2 - 1, grid);
			break;

		case 6:
			piece = new BarShape(1, Grid.WIDTH / 2 - 1, grid);
			break;

		case 7:
			piece = new SShape(1, Grid.WIDTH / 2 - 1, grid);
			break;

		default:
			System.out.println("Could not create random new piece");
			break;

		}

		return piece;

	}

	/**
	 * Draws the current state of the game
	 * 
	 * @param g
	 *            the Graphics context on which to draw
	 */
	public void draw(Graphics g) {
		grid.draw(g);
		if (piece != null) {
			piece.draw(g);
		}
	}

	/**
	 * Moves the piece in the given direction
	 * 
	 * @param the
	 *            direction to move
	 */
	public void movePiece(Direction direction) {
		if (piece != null) {
			if (direction == Direction.ROTATEDOWN) {
				System.out.println("Rotate!!");
				piece.rotate(direction);
			} else {
				piece.move(direction);
			}
		}
		updateDisplay();
		/*
		 * updatePiece(); display.update(); grid.checkRows();
		 */
	}

	public void dropPiece(Direction direction) {
		if (piece != null) {
			piece.drop(direction);
		}
		updateDisplay();
		/*
		 * updatePiece(); display.update(); grid.checkRows();
		 */
	}

	public void rotatePiece(Direction direction) {
		if (piece != null) {
			piece.rotate(direction);
			System.out.println("Game.rotatePiece ACTIVATED");
		}
		updateDisplay();
	}

	// CREATED THIS AS A HELPER METHOD TO CONTAIN UPDATE NEEDS
	// COMMON TO THE METHODS IN THIS CLASS THAT DEAL WITH MOTION
	public void updateDisplay() {
		updatePiece();
		display.update();
		grid.checkRows();
	}

	/**
	 * Returns true if the game is over
	 */
	public boolean isGameOver() {
		// game is over if the piece occupies the same space as some non-empty
		// part of the grid. Usually happens when a new piece is made
		if (piece == null) {
			return false;
		}

		// check if game is already over
		if (isOver) {
			return true;
		}

		// check every part of the piece
		Point[] p = piece.getLocations();
		for (int i = 0; i < p.length; i++) {
			if (grid.isSet((int) p[i].getX(), (int) p[i].getY())) {
				isOver = true;
				return true;
			}
		}
		return false;
	}

	/** Updates the piece */
	private void updatePiece() {
		if (piece == null) {
			// CREATE A NEW PIECE HERE
			// piece = new LShape(1, Grid.WIDTH/2 -1, grid); //THIS CODE IS FROM
			// THE HOMEWORK DETAILS

			randomPieceNum = rand.nextInt(7) + 1; // UPDATE int randomPieceNum
													// WITH A NEW NUMBER SO THAT
													// A NEW RANDOM PIECE IS
													// CREATED
			newRandomPiece(randomPieceNum);
		}

		// set Grid positions corresponding to frozen piece
		// and then release the piece
		else if (!piece.canMove(Direction.DOWN)) {
			Point[] p = piece.getLocations();
			Color c = piece.getColor();
			for (int i = 0; i < p.length; i++) {
				grid.set((int) p[i].getX(), (int) p[i].getY(), c);
			}
			piece = null;
		}

		/*
		 * 
		 * else if (!piece.canMove(Direction.LEFT) ||
		 * !piece.canMove(Direction.RIGHT)) { Point[] p = piece.getLocations();
		 * Color c = piece.getColor(); for (int i = 0; i < p.length; i++) {
		 * grid.set((int) p[i].getX(), (int) p[i].getY(), c); } piece = null; }
		 */

	}

}
