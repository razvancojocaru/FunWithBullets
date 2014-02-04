package Projectiles;

import Constants.Symbols;
import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.BasicShape;
import Shapes.Point;

/**
 * Class representing a HeatedShot type projectile.
 * Extends SpiderShot.
 * 
 * @author Razvan Cojocaru
 */
public class HeatedShot extends SpiderShot {
	
	/**
	 * Anonymous class used to draw a rhombus.
	 */
	protected BasicShape rhombus = new BasicShape() {		
		@Override
		public void draw(Screen screen, int ref, Point centerGrav) {

		    Point vf_sus = centerGrav.translate(0, -2 * ref); 
		    Point vf_jos = centerGrav.translate(0, 2 * ref);
		    Point vf_s = centerGrav.translate(ref, 0); 
		    Point vf_d = centerGrav.translate(-ref, 0); 
    
		    screen.drawLineOnScreen(vf_s, vf_sus, Symbols.RHOMBUS_SYMBOL);
		    screen.drawLineOnScreen(vf_sus, vf_d, Symbols.RHOMBUS_SYMBOL);
		    screen.drawLineOnScreen(vf_d, vf_jos, Symbols.RHOMBUS_SYMBOL);
		    screen.drawLineOnScreen(vf_jos, vf_s, Symbols.RHOMBUS_SYMBOL);
		    		
		}		
	};

	/**
	 * Simple constructor that calls the constructor of the superclass.
	 * 
	 * @param screen 		to draw the shape onto.
	 * @param ref			reference parameter of the Projectile type.
	 * @param currentTime 	TimeManager containing the time of launch of the projectile.
	 */
	public HeatedShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}
	
	@Override
	public void shoot(int dist, Point shooterPosition) {
		shapeChangingDist = calculateShapeChangingDist(6);
		if ( dist < shapeChangingDist ) {
			shooterPosition = shooterPosition.translate(
					0, (int)(Math.round(Math.cos(dist * Math.PI / 2))));
			this.ref = calculateNewRef(dist, 6);
			this.hitScreenActionHeatedShot(shooterPosition, this.ref);
		}
		else {
			dist = dist - shapeChangingDist;
			shooterPosition = shooterPosition.translate(
					0, (int)(Math.round(Math.cos(shapeChangingDist * Math.PI / 2))));
			this.ref = calculateNewRef(shapeChangingDist, 6);
			super.shoot(dist, shooterPosition);
		}
	}

	/**
	 * Method used to draw the corresponding shape on the given screen.
	 * Draws a rhombus.
	 * @param shooterPosition 	Center of gravity of the shape.
	 * @param ref				reference size of the shape. 
	 */
	protected void hitScreenActionHeatedShot(Point shooterPosition, int ref) {
		this.rhombus.draw(screen, ref, shooterPosition);
	}

}
