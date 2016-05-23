import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;


/**
 * This is the Tetris board represented by a (HEIGHT - by - WIDTH) matrix of
 * Squares.
 * 
 * The upper left Square is at (0,0). The lower right Square is at (HEIGHT -1,
 * WIDTH -1).
 * 
 * Given a Square at (x,y) the square to the left is at (x-1, y) the square
 * below is at (x, y+1)
 * 
 * Each Square has a color. A white Square is EMPTY; any other color means that
 * spot is occupied (i.e. a piece cannot move over/to an occupied square). A
 * grid will also remove completely full rows.
 * 
 * @author CSC 143
 */
public class Grid {
	private Square[][] board;

	// Width and Height of Grid in number of squares
	public static final int HEIGHT = 20;

	public static final int WIDTH = 10;

	private static final int BORDER = 5;

	public static final int LEFT = 100; // pixel position of left of grid

	public static final int TOP = 50; // pixel position of top of grid

	public static final Color EMPTY = Color.WHITE;
	
	ArrayList <Integer> rowsToRemove = new ArrayList<Integer>();

	/**
	 * Creates the grid
	 */
	public Grid() {
		board = new Square[HEIGHT][WIDTH];

		// put squares in the board
		for (int row = 0; row < HEIGHT; row++) {
			
			for (int col = 0; col < WIDTH; col++) {
				
				board[row][col] = new Square(this, row, col, EMPTY, false);

			}
		}

	}

	/**
	 * Returns true if the location (row, col) on the grid is occupied
	 * 
	 * @param row
	 *            the row in the grid
	 * @param col
	 *            the column in the grid
	 */
	public boolean isSet(int row, int col) {
		return !board[row][col].getColor().equals(EMPTY);
	}

	/**
	 * Changes the color of the Square at the given location
	 * 
	 * @param row
	 *            the row of the Square in the Grid
	 * @param col
	 *            the column of the Square in the Grid
	 * @param c
	 *            the color to set the Square
	 * @throws IndexOutOfBoundsException
	 *             if row < 0 || row>= WIDTH || col < 0 || col >= HEIGHT
	 */
	public void set(int row, int col, Color c) {
		board[row][col].setColor(c);
	}

	/**
	 * Checks for and remove all solid rows of squares.
	 * 
	 * If a solid row is found and removed, all rows above it are moved down and
	 * the top row set to empty
	 */
	//public void checkRows(int row, int col, Color c) {
	
	
	public void checkRows() {
		
		//Loop 1
		for (int row = 0; row < HEIGHT; row++){
			
			//counter 
			int counterFullSquares = 0;
			
				//Loop 2
				for (int col = 0; col< WIDTH; col++){
					
					//if there an empty square in the row, break out of Loop 2 because it means that the row is not full.
					//Then, move on to the next row. 
					if(board[row][col].getColor().equals(EMPTY)){
						break;
					}
					//increase counter
					counterFullSquares ++;
					
					//if the number of full squares is the same as the board width
					//then the row is full
					if(counterFullSquares == WIDTH){
						//Remove full row and copy all rows above the full row
						//This effectively brings all rows down by one row
						//The illusion of bringing the rows down is that the colors of a row changes 
						//to the colors of the row above it
						for(int fullRow = row; fullRow > 0; fullRow--){
							
							for(int fullCol = 0; fullCol < WIDTH; fullCol++){
								
								board[fullRow][fullCol].setColor(board[fullRow-1][fullCol].getColor());
							}
						}
						
						//Make sure that the very top row of the grid is EMPTY
						for(int fullCol = 0; fullCol < WIDTH; fullCol++){
							board[0][fullCol].setColor(EMPTY);
						}
						
					}
					
				}
			
			}
	
		}
	
	
	

	/**
	 * Draws the grid on the given Graphics context
	 */
	public void draw(Graphics g) {

		// draw the edges as rectangles: left, right in blue then bottom in red
		g.setColor(Color.BLUE);
		g.fillRect(LEFT - BORDER, TOP, BORDER, HEIGHT * Square.HEIGHT);
		g.fillRect(LEFT + WIDTH * Square.WIDTH, TOP, BORDER, HEIGHT
				* Square.HEIGHT);
		g.setColor(Color.RED);
		g.fillRect(LEFT - BORDER, TOP + HEIGHT * Square.HEIGHT, WIDTH
				* Square.WIDTH + 2 * BORDER, BORDER);

		// draw all the squares in the grid
		// empty ones first (to avoid masking the black lines of the pieces that have already fallen)
		for (int r = 0; r < HEIGHT; r++) {
			for (int c = 0; c < WIDTH; c++) {
				if (board[r][c].getColor().equals(EMPTY)) {
					board[r][c].draw(g);
				}
			}
		}
		for (int r = 0; r < HEIGHT; r++) {
			for (int c = 0; c < WIDTH; c++) {
				if (!board[r][c].getColor().equals(EMPTY)) {
					board[r][c].draw(g);
				}
			}
		}
	}
}
