package Projectiles;

import Constants.Symbols;
import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.Point;

/**
 * Class representing a basic SimpleShell type projectile.
 * Extends the abstract Projectile class.
 * 
 * @author Razvan Cojocaru
 */
public class SimpleShell extends Projectile {

	
	/**
	 * Simple constructor that calls the constructor of the superclass.
	 * 
	 * @param screen 		to draw the shape onto.
	 * @param ref			reference parameter of the Projectile type.
	 * @param currentTime 	TimeManager containing the time of launch of the projectile.
	 */
	public SimpleShell(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}

	@Override
	public void shoot(int dist, Point shooterPosition) {
		this.hitScreenAction(shooterPosition, this.ref);
	}

	@Override
	protected void hitScreenAction(Point shooterPosition, int ref) {
		screen.drawLineOnScreen(shooterPosition, shooterPosition, Symbols.DOT_SYMBOL);
	}
	

}
