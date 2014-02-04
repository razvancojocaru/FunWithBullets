package Projectiles;

import Constants.Symbols;
import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.BasicShape;
import Shapes.Point;

/**
 * Class representing a Carcass type projectile.
 * Extends HeatedShot.
 * 
 * @author Razvan Cojocaru
 */
public class Carcass extends HeatedShot {
	
	/**
	 * Anonymous class used to draw a square.
	 */
	protected BasicShape square = new BasicShape() {		
		@Override
		public void draw(Screen screen, int ref, Point centerGrav) {

		    Point vf_s_jos = centerGrav.translate(-ref, -ref); 
		    Point vf_d_jos = centerGrav.translate(ref, -ref); 
		    Point vf_s_sus = centerGrav.translate(-ref, ref); 
		    Point vf_d_sus = centerGrav.translate(ref, ref); 
		    
		    screen.drawLineOnScreen(vf_s_jos, vf_s_sus, Symbols.SQUARE_SYMBOL);
		    screen.drawLineOnScreen(vf_s_sus, vf_d_sus, Symbols.SQUARE_SYMBOL);
		    screen.drawLineOnScreen(vf_d_sus, vf_d_jos, Symbols.SQUARE_SYMBOL);
		    screen.drawLineOnScreen(vf_d_jos, vf_s_jos, Symbols.SQUARE_SYMBOL);
		    		
		}		
	};

	/**
	 * Simple constructor that calls the constructor of the superclass.
	 * 
	 * @param screen 		to draw the shape onto.
	 * @param ref			reference parameter of the Projectile type.
	 * @param currentTime 	TimeManager containing the time of launch of the projectile.
	 */
	public Carcass(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}
	
	@Override
	public void shoot(int dist, Point shooterPosition) {
		shapeChangingDist = calculateShapeChangingDist(2);
		if ( dist < shapeChangingDist ) {
			shooterPosition = shooterPosition.translate(0, dist);
			this.ref = calculateNewRef(dist, 2);
			this.hitScreenActionCarcass(shooterPosition, this.ref);
		}
		else {
			dist = dist - shapeChangingDist;
			shooterPosition = shooterPosition.translate(0, shapeChangingDist);
			this.ref = calculateNewRef(shapeChangingDist, 2);
			super.shoot(dist, shooterPosition);
		}
	}

	/**
	 * Method used to draw the corresponding shape on the given screen.
	 * Draws a rectangle.
	 * @param shooterPosition 	Center of gravity of the shape.
	 * @param ref				reference size of the shape. 
	 */
	protected void hitScreenActionCarcass(Point shooterPosition, int ref) {
		this.square.draw(screen, ref, shooterPosition);
	}

}
