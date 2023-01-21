package battleship;

/**
 * class that represents an empty spot in the 10x10 ocean array 
 * @author Ignacio Villasmil & Chinedu Okoroafor
 *
 */
public class EmptySea extends Ship {
	
	/**
	 * type of "ship", which is the string "empty"
	 */
	private static final String SHIP_TYPE = "empty";
	/**
	 * "length" of the ship (1)
	 */
	private static final int SHIP_LENGTH = 1;
	
	/**
	 * returns the type of ship ("empty")
	 */
	@Override
	public String getShipType() {
		return SHIP_TYPE;
	}
	
	/**
	 * always returns false to indicate that a shot at an empty spot in the sea never hits a ship
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}
	
	/**
	 * always returns false, since there are no ships to sink here 
	 */
	@Override  
	boolean isSunk() {
		return false;
	}
	
	/** 
	 * returns a "-" to indicate that the shot done at an empty location always misses a ship
	 */
	@Override
	public String toString()  {
		return "-";
	}
	
	/**
	 * "ship" of length 1 
	 */
	public EmptySea() {
		super(SHIP_LENGTH);
	}
}
