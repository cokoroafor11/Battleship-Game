package battleship;

/**
 * class representing a cruiser ship 
 * @author Ignacio Villasmil & Chinedu Okoroafor 
 *
 */
public class Cruiser extends Ship {
	
	/**
	 * type of ship 
	 */
	private static final String SHIP_TYPE = "cruiser";
	/**
	 * length of ship 
	 */
	private static final int SHIP_LENGTH = 3;
	
	/**
	 * returns the type of ship ("cruiser")
	 */
	@Override
	public String getShipType() {
		return SHIP_TYPE;
	}
	
	/**
	 * returns the length of the ship ("3")
	 */
	public Cruiser() {
		super(SHIP_LENGTH);
	}
	
}
