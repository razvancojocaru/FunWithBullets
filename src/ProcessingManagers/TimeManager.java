package ProcessingManagers;

/**
 * Time object containing the format hours:minutes:seconds
 */
public class TimeManager {
	private int h,m,s;

	public TimeManager(int h, int m, int s) {
		this.h = h;
		this.m = m;
		this.s = s;
	}
	
	/**
	 * @return Hours of the Time object
	 */
	public int getH(){
		return this.h;
	}
	
	/**
	 * @return Minutes of the Time object
	 */
	public int getM(){
		return this.m;
	}
	
	/**
	 * @return Seconds of the Time object
	 */
	public int getS(){
		return this.s;
	}
}