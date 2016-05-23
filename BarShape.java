import java.awt.Color;


/**
 * An Bar-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration:
 *  
 *<br>Sq Sq Sq Sq <br>
 * 
 * The game piece "floats above" the Grid. The (row, col) coordinates are the
 * location of the middle Square on the side within the Grid
 * 
 * @author Annmarie
 */

public class BarShape extends AbstractPiece {
	
	/**
	 * Creates an Bar-Shape piece. See class description for actual location of r
	 * and c
	 * 
	 * @param r
	 *            row location for this piece
	 * @param c
	 *            column location for this piece
	 * @param g
	 *            the grid for this game piece
	 * 
	 */

	public BarShape(int r, int c, Grid g) {
		super(r, c, g);
	
		// Create the squares
		square[0] = new Square(g, r, c -1, Color.pink, true);
		square[1] = new Square(g, r, c, Color.pink, true); //center
		square[2] = new Square(g, r, c + 1, Color.pink, true);
		square[3] = new Square(g, r, c + 2, Color.pink, true);
	}
	
	
	/*

	// HELP FROM F. LEPEINTRE:
		 //THIS ROTATE METHOD IS NOT NECESSARY
		 //THE CLASS AUTOMATICALLY HAS THE ROTATE METHOD BECAUSE IT EXTENDS AbstractPiece
		 //INHERITANCE  - REMEMBER?
		 //SO, ALL THIS METHOD DOES IS BLOAT THE CLASS
		 //GET RID OF IT
	
	
	public void rotate(Direction direction) { //ROTATE.  MODIFIED THE CODE FROM move()
		
		super.rotate(direction);
			
		}
			
	
	*/

}
