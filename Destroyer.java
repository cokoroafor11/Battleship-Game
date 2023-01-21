package battleship;

/** 
 * class representing a destroyer ship 
 * @author Ignacio Villasmil & Chinedu Okoroafor
 *
 */
public class Destroyer extends Ship {
	
	/**
	 * type of ship 
	 */
	private static final String SHIP_TYPE = "destroyer";
	/**
	 * length of ship
	 */
	private static final int SHIP_LENGTH = 2;
	
	/**
	 * returns type of ship ("destroyer")
	 */
	@Override
	public String getShipType() {
		return SHIP_TYPE;
	}
	/**
	 * returns length of ship (2) 
	 */
	public Destroyer() {
		super(SHIP_LENGTH);
	}
}
