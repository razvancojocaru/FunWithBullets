package Projectiles;

import Constants.Symbols;
import ProcessingManagers.TimeManager;
import Screen.Screen;
import Shapes.BasicShape;
import Shapes.Point;

/**
 * Class representing a SpiderShot type projectile.
 * Extends SimpleShell.
 * 
 * @author Razvan Cojocaru
 */
public class SpiderShot extends SimpleShell {
	
	/**
	 * Distance at which the projectile changes it's type.
	 */
	protected int shapeChangingDist;
	
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
	public SpiderShot(Screen screen, int ref, TimeManager currentTime) {
		super(screen, ref, currentTime);
	}

	@Override
	public void shoot(int dist, Point shooterPosition) {
		shapeChangingDist = calculateShapeChangingDist(7);
		if ( dist < shapeChangingDist ) {
			shooterPosition = shooterPosition.translate((int)(Math.round(Math.sin(dist * Math.PI / 2))), 
					(int)(Math.round(Math.cos(dist * Math.PI / 2))));
			this.ref = calculateNewRef(dist, 7);
			this.hitScreenActionSpiderShot(shooterPosition, this.ref);
		}
		// The projectile changes type.
		else {
			dist = dist - shapeChangingDist;
			shooterPosition = shooterPosition.translate((int)(Math.round(Math.sin(shapeChangingDist * Math.PI / 2))), 
					(int)(Math.round(Math.cos(shapeChangingDist * Math.PI / 2))));
			this.ref = calculateNewRef(shapeChangingDist, 7);
			super.shoot(dist, shooterPosition);
		}
	}

	/**
	 * Method used to draw the corresponding shape on the given screen.
	 * Draws a square.
	 * @param shooterPosition 	Center of gravity of the shape.
	 * @param ref				reference size of the shape. 
	 */
	protected void hitScreenActionSpiderShot(Point shooterPosition, int ref) {
		this.rectangle.draw(screen, ref, shooterPosition);
	}
	
	/**
	 * Method used to find the shapeChangingDist for each projectile.
	 * ID: 
	 * 1 	TriGrapeShot
	 * 2 	Carcass
	 * 3 	CanisterShot
	 * 4 	ChainShot
	 * 5 	Shrapnel
	 * 6 	HeatedShot
	 * 7 	SpiderShot
	 * 8 	SimpleShell
	 * @param id		ID for the projectile type.
	 * @return Distance at which the projectile changes type.
	 */
	protected int calculateShapeChangingDist(int id) {
		return 42 + (id * id * currentTime.getH() + id * currentTime.getM() + currentTime.getS()) % 42;
	}
	
	/**
	 * Calculate the new reference of a projectile as it travels towards the screen.
	 * @param dist 		distance traveled by the projectile.
	 * @param id 		ID of the given projectile type (see calculateShapeChangingDist).
	 * @return new reference.
	 */
	protected int calculateNewRef(int dist, int id) {
		return ref - dist/10 - id;
	}
	
}
