package Projectiles;

import Constants.Symbols;
import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.BasicShape;
import Shapes.Point;

/**
 * Class representing a ChainShot type projectile.
 * Extends Shrapnel.
 * 
 * @author Razvan Cojocaru
 */
public class ChainShot extends Shrapnel {
	
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
	public ChainShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}
	
	@Override
	public void shoot(int dist, Point shooterPosition) {
		shapeChangingDist = calculateShapeChangingDist(4);
		if ( dist < shapeChangingDist ) {
			shooterPosition = shooterPosition.translate(0, -dist);
			this.ref = calculateNewRef(dist, 4);
			this.hitScreenActionChainShot(shooterPosition, this.ref);
		}
		else {
			dist = dist - shapeChangingDist;
			shooterPosition = shooterPosition.translate(0, -shapeChangingDist);
			this.ref = calculateNewRef(shapeChangingDist, 4);
			super.shoot(dist, shooterPosition);
		}
	}

	/**
	 * Method used to draw the corresponding shape on the given screen.
	 * Draws a triangle.
	 * @param shooterPosition 	Center of gravity of the shape.
	 * @param ref 				reference size of the shape. 
	 */
	protected void hitScreenActionChainShot(Point shooterPosition, int ref) {
		this.triangle.draw(screen, ref, shooterPosition);
	}

}
