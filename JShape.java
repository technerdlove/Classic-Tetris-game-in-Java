import java.awt.Color;
//import java.util.ArrayList;
//import java.util.List;

/**
 * An J-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration:
 * 
 * 		 Sq <br>
 * 		 Sq <br>
 *<br>Sq Sq 
 * 
 * The game piece "floats above" the Grid. The (row, col) coordinates are the
 * location of the middle Square on the side within the Grid
 * 
 * @author Annmarie
 */

public class JShape extends AbstractPiece {
	/**
	 * Creates an J-Shape piece. See class description for actual location of r
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
	


	public JShape(int r, int c, Grid g) {
		super(r, c, g);
		// TODO Auto-generated constructor stub
		
		// Create the squares
				square[0] = new Square(g, r - 1, c, Color.cyan, true);
				square[1] = new Square(g, r, c, Color.cyan, true); //center
				square[2] = new Square(g, r + 1, c , Color.cyan, true);
				square[3] = new Square(g, r + 1, c - 1, Color.cyan, true);
				
						
		//CREATE ARRAYLIST TO HOLD SHAPE POSITION IF USER ROTATES SHAPE
				//this.grids = new ArrayList<Square>();
				//Square shapeSquare = this.square;
				
				
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
		
		/*
		switch (direction) {
		
			case ROTATEDOWN:
				break;
				
			case ROTATEUP:
				break;
			
		default:
			break;
		}
		*/
		



	

}
