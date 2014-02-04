package Projectiles;

import Constants.Symbols;
import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.BasicShape;
import Shapes.Point;

/**
 * Class representing a CanisterShot type projectile.
 * Extends HeatedShot.
 * 
 * @author Razvan Cojocaru
 */
public class CanisterShot extends HeatedShot {
	
	
	/**
	 * Anonymous class used to draw a rectangle.
	 */
	protected BasicShape rectangle = new BasicShape() {		
		@Override
		public void draw(Screen screen, int ref, Point centerGrav) {

		    Point vf_s_jos = centerGrav.translate(-2 * ref, -ref);
		    Point vf_d_jos = centerGrav.translate(2 * ref, -ref);
		    Point vf_s_sus = centerGrav.translate(-2 * ref, ref);
		    Point vf_d_sus = centerGrav.translate(2 * ref, ref);
		    
		    screen.drawLineOnScreen(vf_s_jos, vf_s_sus, Symbols.RECTANGLE_SYMBOL);
		    screen.drawLineOnScreen(vf_s_sus, vf_d_sus, Symbols.RECTANGLE_SYMBOL);
		    screen.drawLineOnScreen(vf_d_sus, vf_d_jos, Symbols.RECTANGLE_SYMBOL);
		    screen.drawLineOnScreen(vf_d_jos, vf_s_jos, Symbols.RECTANGLE_SYMBOL);
		    		
		}		
	};

	/**
	 * Simple constructor that calls the constructor of the superclass.
	 * 
	 * @param screen 		to draw the shape onto.
	 * @param ref			reference parameter of the Projectile type.
	 * @param currentTime 	TimeManager containing the time of launch of the projectile.
	 */
	public CanisterShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}

	@Override
	public void shoot(int dist, Point shooterPosition) {
		shapeChangingDist = calculateShapeChangingDist(3);
		if ( dist < shapeChangingDist ) {
			shooterPosition = shooterPosition.translate(-dist, 0);
			this.ref = calculateNewRef(dist, 3);
			this.hitScreenActionCanisterShot(shooterPosition, this.ref);
		}
		else {
			dist = dist - shapeChangingDist;
			shooterPosition = shooterPosition.translate(-shapeChangingDist, 0);
			this.ref = calculateNewRef(shapeChangingDist, 3);
			super.shoot(dist, shooterPosition);
		}
	}

	/**
	 * Method used to draw the corresponding shape on the given screen.
	 * Draws a rectangle.
	 * @param 	shooterPosition 	Center of gravity of the shape.
	 * @param 	ref					reference size of the shape. 
	 */
	protected void hitScreenActionCanisterShot(Point shooterPosition, int ref) {
		this.rectangle.draw(screen, ref, shooterPosition);
	}
}
