import Constants.ProjectileNames;
import ProcessingManagers.TimeManager;
import Projectiles.CanisterShot;
import Projectiles.Carcass;
import Projectiles.ChainShot;
import Projectiles.HeatedShot;
import Projectiles.Projectile;
import Projectiles.Shrapnel;
import Projectiles.SimpleShell;
import Projectiles.SpiderShot;
import Projectiles.TriGrapeShot;
import Screen.Screen;
import Shapes.Point;


/**
 * This execution entry point class handles the parsing, processing and writing
 * the data into the output file.
 * 
 * @author Razvan Cojocaru
 */
public class Main {

	/**
	 * Execution entry point
	 * 
	 * @param args	String with the input file name.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java -cp <classpath> Main <input file>");
			System.exit(1);
		}
		
		int ex, ey, N;
		int ref, h, m, s, dist, pos_x, pos_y;	
		
		String[] line;
		String type;
		Projectile bullet;
		
		FileParser inputFile = new FileParser(args[0]);
		
		inputFile.open();
		
		// Read the screen size coordinates
		line = inputFile.parseNextLine();
		ex = Integer.parseInt(line[0]);
		ey = Integer.parseInt(line[1]);
		
		Screen screen = new Screen(ex, ey);
		
		line = inputFile.parseNextLine();
		N = Integer.parseInt(line[0]);
		
		// Parse the input file line by line
		for (int i = 0; i < N; i++) {
			line = inputFile.parseNextLine();
			type = line[0];
			ref = Integer.parseInt(line[1]);
			h = Integer.parseInt(line[2]);
			m = Integer.parseInt(line[3]);
			s = Integer.parseInt(line[4]);
			dist = Integer.parseInt(line[5]);
			pos_x = Integer.parseInt(line[6]);
			pos_y = Integer.parseInt(line[7]);
			
			// Shoot!
			if ( type.equals(ProjectileNames.SIMPLE_SHELL) )
				bullet = new SimpleShell(screen, ref, new TimeManager(h, m, s));
			else if ( type.equals(ProjectileNames.SPIDER_SHOT) )
				bullet = new SpiderShot(screen, ref, new TimeManager(h, m, s));
			else if ( type.equals(ProjectileNames.SHRAPNEL) )
				bullet = new Shrapnel(screen, ref, new TimeManager(h, m, s));
			else if ( type.equals(ProjectileNames.HEATED_SHOT) )
				bullet = new HeatedShot(screen, ref, new TimeManager(h, m, s));
			else if ( type.equals(ProjectileNames.TRI_GRAPE_SHOT) )
				bullet = new TriGrapeShot(screen, ref, new TimeManager(h, m, s));
			else if ( type.equals(ProjectileNames.CHAIN_SHOT) )
				bullet = new ChainShot(screen, ref, new TimeManager(h, m, s));
			else if ( type.equals(ProjectileNames.CARCASS) )
				bullet = new Carcass(screen, ref, new TimeManager(h, m, s));
			else // CANISTER_SHOT
				bullet = new CanisterShot(screen, ref, new TimeManager(h, m, s));
			
			bullet.shoot(dist, new Point(pos_x, pos_y));
			
		}	
		inputFile.close();
		
		// Write to the output file
		Writer outputFile = new Writer(args[0]+"_out");
		outputFile.open();
		
		for ( int i = 0; i < ey; i++ ) {
			for ( int j = 0; j < ex; j++ ) {
				outputFile.writeLine( screen.getMatrix()[i][j] + "" );
			}
			outputFile.writeLine("\n");
		}
		outputFile.close();
		
	}
}
