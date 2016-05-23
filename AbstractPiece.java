import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * The abstract superclass for pieces (L, J, Z, T, Square, S, and Bar shapes) in
 * the Tetris Game.
 * 
 * Each piece is made up of 4 squares, and the configurations for the squares
 * are contained each individual shape class.
 *
 * Each game piece "floats above" the Grid. The (row, col) coordinates are the
 * location of the middle Square on the side within the Grid
 * 
 * @author Annmarie
 */
public abstract class AbstractPiece implements Piece {

	protected boolean ableToMove; // can this piece move

	protected Square[] square; // the squares that make up this piece

	// Made up of PIECE_COUNT squares
	protected Grid grid; // the board this piece is on

	// number of squares in one Tetris game piece
	protected static final int PIECE_COUNT = 4;

	/**
	 * Creates an Abstract Shape piece. See individual shape class description
	 * for actual location of r and c
	 * 
	 * @param r
	 *            row location for the piece
	 * @param c
	 *            column location for the piece
	 * @param g
	 *            the grid for the game piece
	 * 
	 */

	// CONSTRUCTOR CONTAINING THINGS IN COMMON FOR ALL SHAPES
	public AbstractPiece(int r, int c, Grid g) {
		this.grid = g;
		this.square = new Square[PIECE_COUNT];
		this.ableToMove = true;
	}

	/**
	 * Draws the piece on the given Graphics context
	 */
	public void draw(Graphics g) {
		for (int i = 0; i < PIECE_COUNT; i++) {
			square[i].draw(g);
		}
	}

	/**
	 * Moves the piece if possible Freeze the piece if it cannot move down
	 * anymore
	 * 
	 * @param direction
	 *            the direction to move
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(direction);
		}
		// if we couldn't move, see if because we're at the bottom
		else if (direction == Direction.DOWN) {
			ableToMove = false;
		}

	}

	public void drop(Direction direction) { // DROP DOWN. MODIFIED THE CODE FROM
											// move()
		// THE DIFFERENCE BETWEEN drop AND move IS THE WHILE LOOP HERE:
		while (canMove(direction)) {
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(direction);
		}
		// if we couldn't move, see if because we're at the bottom
		if (direction == Direction.DOWN) {
			ableToMove = false;
		}
	}

	public void rotate(Direction direction) { // ROTATE. SAME CODE FROM move()

		if (canRotate()) {
			for (int i = 0; i < PIECE_COUNT; i++) {
				//square[i].move(direction);
				square[i].rotate(square[1].getRow(), square[1].getCol());
			}

			System.out.println("AbstractPiece.rotate ACTIVATED");
		}

		/*
		 * // if we couldn't move, see if because we're at the bottom else if
		 * (direction == Direction.DOWN) { ableToMove = false; }
		 */

	}

	/**
	 * Returns the (row,col) grid coordinates occupied by this Piece
	 * 
	 * @return an Array of (row,col) Points
	 */
	public Point[] getLocations() {
		Point[] points = new Point[PIECE_COUNT];
		for (int i = 0; i < PIECE_COUNT; i++) {
			points[i] = new Point(square[i].getRow(), square[i].getCol());
		}
		return points;
	}

	/**
	 * Return the color of this piece
	 */
	public Color getColor() {
		// all squares of this piece have the same color
		return square[0].getColor();
	}

	 //HELP FROM F.LEPEINTRE
	public boolean canRotate() {
		/* 
		// Remove this
		if (Math.sqrt(2) > 1) {
			return true;
		}
		*/
		for (Square s : square) {
			if (!s.canRotate(square[1].getRow(), square[1].getCol())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns if this piece can move in the given direction
	 * 
	 */
	public boolean canMove(Direction direction) {

		if (!ableToMove)
			return false;

		// Each square must be able to move in that direction
		boolean answer = true;
		for (int i = 0; i < PIECE_COUNT; i++) {
			answer = answer && square[i].canMove(direction);
		}

		return answer;
	}

}// END CLASS ABSTRACTPIECE
