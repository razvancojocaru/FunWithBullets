package Screen;

import java.util.Arrays;

import Constants.Symbols;
import ProcessingManagers.DrawManager;
import Shapes.Point;

/**
 * Class that represents the screen to be projected on
 */
public class Screen {
	private char[][] matrix;
	
	/**
	 * Builds the necessary data of the screen
	 * 
	 * @param sizeX	screen size on X axis
	 * @param sizeY	screen size on Y axis
	 */
	public Screen(int sizeX, int sizeY) {
		this.matrix = new char[sizeX][sizeY];
		for ( char[] row: matrix )
			Arrays.fill(row, Symbols.SCREEN_SYMBOL);
	}
	
	/**
	 * Draws a line on the screen between the given points
	 * 
	 * @param startPoint	the first end of the line
	 * @param endPoint		the second end of the line
	 * @param symbol		the symbol the line is drawn with
	 */
	public void drawLineOnScreen(Point startPoint, Point endPoint, 
			char symbol) {
		DrawManager.drawLine(this.matrix, startPoint, endPoint, symbol);
	}
	
	/**
	 * Draws multiple lines, each defined by startPoints[index] and
	 * endPoints[index] 
	 * 
	 * @param startPoints	array of first ends of the lines
	 * @param endPoints		array of second ends of the lines
	 * @param symbol		the symbol with which ALL lines are drawn
	 */
	public void drawMultipleLinesOnScreen(Point[] startPoints,
			Point[] endPoints, char symbol) {
		for ( int i = 0; i > startPoints.length; i++ )
			drawLineOnScreen(startPoints[i], startPoints[i], symbol);
	}
	
	public char[][] getMatrix() {
		return this.matrix;
	}
}
