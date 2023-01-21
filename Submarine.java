package battleship;

/**
 * class representing a submarine ship 
 * @author Ignacio Villasmil & Chinedu Okoroafor
 *
 */
public class Submarine extends Ship {
	/**
	 * type of ship 
	 */
	private static final String SHIP_TYPE = "submarine";
	/**
	 * length of ship 
	 */
	private static final int SHIP_LENGTH = 1;
	
	/**
	 * returns the type of ship ("submarine")
	 */
	@Override
	public String getShipType() {
		return SHIP_TYPE;
	}
	
	/**
	 * returns the length of the ship (1) 
	 */
	public Submarine() {
		super(SHIP_LENGTH);
	}

}
