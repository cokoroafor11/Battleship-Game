package battleship;

/**
 * class representing a battleship
 * @author Ignacio Villasmil & Chinedu Okoroafor 
 *
 */
public class Battleship extends Ship {
	
	/**
	 * type of ship 
	 */
	private static final String SHIP_TYPE = "battleship";
	/**
	 * length of ship 
	 */
	private static final int SHIP_LENGTH = 4;
	
	/**
	 * returns the type of ship ("battleship")
	 */
	@Override
	public String getShipType() {
		return SHIP_TYPE;
	}
	
	/**
	 * returns the length of the ship (4) 
	 */
	public Battleship() {
		super(SHIP_LENGTH);
	}
}



