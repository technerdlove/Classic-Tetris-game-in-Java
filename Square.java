import java.awt.Color;
import java.awt.Graphics;

/**
 * One Square on our Tetris Grid or one square in our Tetris game piece
 * 
 * @author CSC 143
 */

public class Square {
	private Grid grid; // the environment where this Square is

	private int row, col; // the grid location of this Square

	//private int rowRotate, colRotate; // THE GRID LOCATION OF THIS SQUARE AFTER
										// ROTATION

	//private int changeInRow, changeInCol; // THE DERIVATIVE BETWEEN TEH ORIGINAL
											// LOCATION AND THE NEW LOCATION
	
	private int newRow; // THE GRID LOCATION OF THIS SQUARE AFTER ROTATION
	private int newCol; // THE GRID LOCATION OF THIS SQUARE AFTER ROTATION
	
	private boolean ableToMove; // true if this Square can move

	private Color color; // the color of this Square

	// possible move directions are defined by the Game class

	// dimensions of a Square
	public static final int WIDTH = 20;

	public static final int HEIGHT = 20;

	/**
	 * Creates a square
	 * 
	 * @param g
	 *            the Grid for this Square
	 * @param row
	 *            the row of this Square in the Grid
	 * @param col
	 *            the column of this Square in the Grid
	 * @param c
	 *            the Color of this Square
	 * @param mobile
	 *            true if this Square can move
	 * 
	 * @throws IllegalArgumentException
	 *             if row and col not within the Grid
	 */
	public Square(Grid g, int row, int col, Color c, boolean mobile) {
		if (row < 0 || row > Grid.HEIGHT - 1)
			throw new IllegalArgumentException("Invalid row =" + row);
		if (col < 0 || col > Grid.WIDTH - 1)
			throw new IllegalArgumentException("Invalid column  = " + col);

		// initialize instance variables
		grid = g;
		this.row = row;
		this.col = col;
		color = c;
		ableToMove = mobile;
	}

	/**
	 * Returns the row for this Square
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column for this Square
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Returns true if this Square can move 1 spot in direction d
	 * 
	 * @param direction
	 *            the direction to test for possible move
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		boolean move = true;
		// if the given direction is blocked, we can't move
		// remember to check the edges of the grid
		switch (direction) {
		case DOWN:
		case DROP:
			if (row == (Grid.HEIGHT - 1) || grid.isSet(row + 1, col))
				move = false;
			break;

		// HERE WE MAKE SURE THAT THE ROTATION DOES NOT HAPPEN IF THE PIECE WILL
		// ROTATE OUTSIDE OF THE BOUNDS OF THE GRID (THE HEIGHT AND WIDTH)
		case ROTATEDOWN:
		case ROTATEUP: // COVERS BOTH ROTATEDOWN AND ROTATEUP

			// OLD CODE
			// if (col == (Grid.WIDTH + 1) || row == (Grid.HEIGHT - 1) ||
			// grid.isSet(row, col + 1) ||grid.isSet(row + 1, col))

			// HERE WE COMBINE THE CONDITIONS OF THE LEFT AND RIGHT CASES AS ONE
			// LONG CONDITION FOR ROTATEDOWN AND ROTATEUP:
			if (col == (Grid.WIDTH - (Grid.WIDTH)) || grid.isSet(row, col - 1) || col == Grid.WIDTH - 1
					|| grid.isSet(row, col + 1))
				move = false;
			// System.out.println("Square.canMove FOR ROTATE is FALSE");
			break;

		// currently doesn't support checking LEFT or RIGHT
		// MODIFY so that it correctly returns if it can move left or right
		case LEFT: // IF THE COL IS OUT OF BOUNDS OR THERE IS SOMETHING IN THE
					// SQUARE TO THE LEFT
			if (col == (Grid.WIDTH - (Grid.WIDTH)) || grid.isSet(row, col - 1))// isSet
																				// DETERMINES
																				// IF
																				// THERE
																				// IS
																				// SOMETHING
																				// IN
																				// A
																				// SQUARE
				move = false;
			break;
		case RIGHT: // IF THE COL IS OUT OF BOUNDS OR THERE IS SOMETHING IN THE
					// SQUARE TO THE RIGHT
			if (col == Grid.WIDTH - 1 || grid.isSet(row, col + 1))// isSet
																	// DETERMINES
																	// IF THERE
																	// IS
																	// SOMETHING
																	// IN A
																	// SQUARE
				move = false;
			break;
		/*
		 * OLD ATTEMPT case DROP: //DROP DOWN USING DOWN OR SPACEBAR if (col ==
		 * (Grid.WIDTH - 1) || grid.isSet(row, col + 1)) move = false; break;
		 */

		default:
			break;
		}
		return move;
	}

	/**
	 * moves this square in the given direction if possible.
	 * 
	 * The square will not move if the direction is blocked, or if the square is
	 * unable to move.
	 * 
	 * If it attempts to move DOWN and it can't, the square is frozen and cannot
	 * move anymore
	 * 
	 * @param direction
	 *            the direction to move
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			switch (direction) {
			case DOWN:
			case DROP:
				row = row + 1;
				break;
			// ROTATE CLOCKWISE, ACTIVATED BY DOWN BUTTON
			case ROTATEDOWN:

				/*
				 * FIRST PASS
				 * 
				 * 
				 * //CLOCKWISE: //x2 = y1 //y2 = (maxWidth- 1) - x1
				 * 
				 * 
				 * colRotate = row; rowRotate = (WIDTH -1) - col; col =
				 * colRotate; row = rowRotate;
				 * 
				 */

				/*
				 * 
				 * //THIS IS THE MAGIC FORMULA TO ROTATE THE PIECE 90 DEGREES
				 * //FROM PROF.LP IN CLASS changeInRow = rowRotate - row;
				 * changeInCol = col - colRotate;
				 * 
				 * row = rowRotate + changeInCol; col = colRotate - changeInRow;
				 */

				// x, y
				// y, -x
				
				/*

				// THIS IS THE LAST FORMULA I HAD AS OF 4/27/16
				colRotate = row;
				rowRotate = -col;

				col = colRotate;
				row = -rowRotate;

				System.out.println("ROTATEDOWN ACTIVATED in Square.java");
*/
				break;
			// ROTATE COUNTERCLOCKWISE, ACTIVATED BY UP BUTTON

			case ROTATEUP:

				/*
				 * FIRST PASS
				 * 
				 * //COUNTERCLOCKWISE: //x2 = (maxHeight -1) - y1 //y2 = x1
				 * 
				 * colRotate = (HEIGHT - 1) - row; rowRotate = col; col =
				 * colRotate; row = rowRotate;
				 * 
				 * break;
				 */
				// x, y
				// y, -x

				// new
				// -1, 4
				// -4, 1

				// x, y
				// -y, -x
				
				/*
				 //THIS IS THE MOST RECENT CODE FOR ROTATEUP
				  //IT'S REALLY NOT NECESSARY, BECAUSE ROTATE DOWN IS WHAT IS USED

				colRotate = row;
				rowRotate = col;

				col = -colRotate;
				row = -rowRotate;
				
				*/

				break;

			// currently doesn't support moving LEFT or RIGHT
			// MODIFY so that the Square moves appropriately
			case LEFT: // MOVE LEFT
				col = col - 1;
				break;
			case RIGHT: // MOVE RIGHT
				col = col + 1;
				break;

			/*
			 * OLD ATTEMPT case DROP: //DROP DOWN while (!grid.isSet(row, col)){
			 * row = row + 1; } break;
			 */

			/*
			 * case FASTDOWN: while(canMove(Direction.DOWN)){ for(int i=0;
			 * i<PIECE_COUNT; i++{ square[i].move(Direction.DOWN); }
			 * 
			 */

			default: // ADDED DEFAULT FOR GOOD MEASURE
				break;
			}
		}
	}

	/**
	 * Changes the color of this square
	 * 
	 * @param c
	 *            the new color
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * Gets the color of this square
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Draws this square on the given graphics context
	 */
	public void draw(Graphics g) {

		// calculate the upper left (x,y) coordinate of this square
		int actualX = Grid.LEFT + col * WIDTH;
		int actualY = Grid.TOP + row * HEIGHT;
		g.setColor(color);
		g.fillRect(actualX, actualY, WIDTH, HEIGHT);
		// black border (if not empty)
		if (!color.equals(Grid.EMPTY)) {
			g.setColor(Color.BLACK);
			g.drawRect(actualX, actualY, WIDTH, HEIGHT);
		}
	}

	//HELP FROM F. LEPEINTRE
	public void rotate(int row0, int col0) {
		//int newRow = row0 + (col - col0);
		//int newCol = col0 + (row0 - row);
		
		newRow = row0 + (col - col0);
		newCol = col0 + (row0 - row);
		
		row = newRow;
		col = newCol;
	}

	//HELP FROM F.LEPEINTRE
	public boolean canRotate(int row0, int col0) {
		//int newRow = row0 + (col - col0);
		//int newCol = col0 + (row0 - row);

		newRow = row0 + (col - col0);
		newCol = col0 + (row0 - row);
		
		return true; // Change this!!
	}
}
