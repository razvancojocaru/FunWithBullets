package Projectiles;

import Constants.Symbols;
import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.BasicShape;
import Shapes.Point;

/**
 * Class representing a TriGrapeShot type projectile.
 * Extends Shrapnel.
 * 
 * @author Razvan Cojocaru
 */
public class TriGrapeShot extends Shrapnel {

	/**
	 * Anonymous class used to draw a triangle.
	 */
	protected BasicShape triangle = new BasicShape() {		
		@Override
		public void draw(Screen screen, int ref, Point centerGrav) {

		    Point vf_s = centerGrav.translate(-ref, ref); 
		    Point vf_d = centerGrav.translate(ref, ref); 
		    Point vf_sus = centerGrav.translate(0, -2 * ref); 
		    
		    screen.drawLineOnScreen(vf_s, vf_sus, Symbols.TRIANGLE_SYMBOL);
		    screen.drawLineOnScreen(vf_sus, vf_d, Symbols.TRIANGLE_SYMBOL);
		    screen.drawLineOnScreen(vf_d, vf_s, Symbols.TRIANGLE_SYMBOL);
		    		
		}		
	};
	
	/**
	 * Simple constructor that calls the constructor of the superclass.
	 * 
	 * @param screen 		to draw the shape onto.
	 * @param ref			reference parameter of the Projectile type.
	 * @param currentTime 	TimeManager containing the time of launch of the projectile.
	 */
	public TriGrapeShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}

	@Override
	public void shoot(int dist, Point shooterPosition) {
		shapeChangingDist = calculateShapeChangingDist(1);
		if ( dist < shapeChangingDist ) {
			shooterPosition = shooterPosition.translate(dist, 0);
			this.ref = calculateNewRef(dist, 1);
			this.hitScreenActionTriGrapeShot(shooterPosition, this.ref);
		}
		else {
			dist = dist - shapeChangingDist;
			shooterPosition = shooterPosition.translate(shapeChangingDist, 0);
			this.ref = calculateNewRef(shapeChangingDist, 1);
			super.shoot(dist, shooterPosition);
		}
	}

	/**
	 * Method used to draw the corresponding shape on the given screen.
	 * Draws a triangle.
	 * @param shooterPosition 	Center of gravity of the shape.
	 * @param ref				reference size of the shape. 
	 */
	protected void hitScreenActionTriGrapeShot(Point shooterPosition, int ref) {
		this.triangle.draw(screen, ref, shooterPosition);
	}
	
}
