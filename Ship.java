package battleship;

/**
 * class for ship placement, orientation, length, hit array of ships, 
 * @author Ignacio Villasmil & Chinedu Okoroafor
 *
 */
public abstract class Ship {
	
	
	/**
	 * The row that contains the bow (front part of the ship)
	 */
	private int bowRow;
	
	
	/**
	 * The column that contains the bow (front part of the ship)  
	 */
	private int bowColumn;
	
	
	/**
	 * The length of the ship 
	 */
	private int length;
	
	
	/**
	 * A boolean that represents whether the ship is going 
	 * to be placed horizontally or vertically
	 */
	private boolean horizontal;
	
	
	/**
	 * An array of booleans that indicate whether that 
	 * part of the ship has been hit or not 
	 */
	private boolean[] hit;
	
	/**
	 * generates ship of given length & its associated hit array
	 * @param length
	 */
	public Ship(int length) {
		//set length
		this.length = length;
		//initialize hit array to this length
		hit =  new boolean[length];
		//set each value to false
		for (int i=0;i<length;i++) {
			hit[i] = false;
		}
	}
	
	/** 
	 * @return the length of ship
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 *
	 * @return the bow row
	 */
	public int getBowRow() {
		return bowRow;
	}
	
	/**
	 * 
	 * @return the bow column
	 */
	public int getBowColumn() {
		return bowColumn;
	}
	
	/**
	 * 
	 * @return the hit array
	 */
	public boolean[] getHit() {
		return hit;
	}
	
	/**
	 * 
	 * @return if the ship is horizontal or not
	 */
	public boolean isHorizontal() {
		return horizontal;
	}
	
	/**
	 * Set the bow row
	 * @param row
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * Set the bow column
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * Set the ship to horizontal
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * 
	 * @return the type of ship
	 */
	public abstract String getShipType();
	
	
	/**
	 * For determining if the desired location is an acceptable spot to place the ship. 
	 * Take into account the following:
	 * - ship cannot be placed where another ship is already placed
	 * - ship cannot be placed directly adjacent to another ship (either horizontally, vertically, or diagonally)
	 * - ship cannot be placed outside of the bounds of the 10x10 array of the ocean
	 * 
	 * The entirety of the ship must follow the rules above (not one part can interfere with the above criteria).
	 * 
	 * NOTE: bow of the ship faces east/right if horizontal & south/down if vertical.
	 * 
	 * @param row for desired placement of bow of ship 
	 * @param column for desired placement of bow of ship 
	 * @param horizontal - boolean value to represent if it is to be placed horizontally (true) or vertically (false)
	 * @param ocean - the ocean 10x10 array where the ship can be placed
	 * @return boolean value indicating whether it is ok to place the ship in the desired location or not
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, 
			Ocean ocean) {
		
		boolean ok = true;
		
		//check if input is outside boundaries
		if (row > 9 || column > 9 || row < 0 || column < 0) {
			ok = false;
		}
		
		
		//Horizontal Case
		if(horizontal) {
			
			//check if end of ship is out of bounds
			if(column-this.getLength()+1 < 0) {
				ok = false;
			}
			//Check if ship actual location is occupied or if end of ship goes out of bounds
//			for (int i=0; i < this.getLength(); i++) {
//					if(ocean.isOccupied(row,column-i)) {
//						ok = false;
//				}
//
//			}
			//Check actual ship plus surrounding areas (ONLY IF SURROUNDING IS IN BOUNDS)
			for (int i=column-this.getLength(); i <= column+1; i++) {
				for (int j=row-1; j<=row+1; j++) {
					if (i >= 0 && i < 10 && j >= 0 && j < 10) {
						if (ocean.isOccupied(j, i)) {
							ok = false;
						}
					}
				}
			}
		}
		
		//Vertical Case
		else if (horizontal == false) {
			
			//check if end of ship is out of bounds
			if(row-this.getLength()+1 < 0) {
				ok=  false;
			}
			
			//Check if ship actual location is occupied or if end of ship goes out of bounds
//			for (int i=0; i < this.getLength(); i++) {
//					if(ocean.isOccupied(row-i,column)) {
//						ok= false;
//				}
//			}
			
			//Check actual ship plus surrounding areas (ONLY IF SURROUNDING IS IN BOUNDS)
			for (int i = row-this.getLength(); i <= row+1; i++) {
				for (int j = column-1; j <= column+1; j++) {
					if ((i >= 0) && (i < 10) && (j >= 0) && (j <10)) {
						if(ocean.isOccupied(i,j)) {
							ok= false;
						}
					}
				}
			}
		}
		
		return ok;
		
	}
	
	/**
	 * For placement of the ship (more specifically, the bow of the ship) in the desired location & orientation. 
	 * 
	 * This relies on the okToPlaceShipAt() method in order to determine whether it is ok to place the ship at the desired location 
	 * before actually placing it there. 
	 * 
	 * @param row for desired placement of bow of ship
	 * @param column for desired placement of bow of ship
	 * @param horizontal - boolean indicating the orientation of ship placement (true = horizontal; false = vertical)
	 * @param ocean - the 10x10 array representing the positions for possible placement of ships
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean 
			ocean) {
		//Set bow row, column, horizontal boolean
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		
		//initialize ship array
		Ship[][] ship = ocean.getShipArray();
		
		//horizontal case
		if(horizontal) {
			//iterate through and set array coordinates to ship
			for(int i=column; i>column-getLength(); i--) {
				ship[row][i] = this;
			}
		}
		//vertical case
		else {
			//iterate through and set array coordinates to ship
			for(int j=row; j>row-getLength(); j--) {
				ship[j][column] = this;
			}
		}
	}
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been 
	 * sunk, mark that part of the ship as “hit” (in the hit array, index 0 indicates the 
	 * bow) and return true; otherwise return false. 
	 * @param row to shoot at 
	 * @param column to shoot at
	 * @return true if the shot hits any part of the (not sunk) ship; false if the shot misses or lands at where a sunken ship is 
	 */
	boolean shootAt(int row, int column)  {
		//Horizontal Case
		if(this.isHorizontal() && !this.isSunk()) {
			//Check that it is correct range of ship column, then correct row
			if(column > this.getBowColumn()-this.getLength() && column <= this.getBowColumn()) {
				if(this.getBowRow() == row) {
					//update hit array
					this.hit[this.getBowColumn()-column] = true;
					return true;
					}
				}
		}
		
		//Vertical Case
		else if(!(this.isHorizontal() && this.isSunk())) {
			//Check that it is correct range of ship row, then correct column
			if(row > this.getBowRow()-this.getLength() && row <= this.getBowRow()) {
				if(this.getBowColumn() == column) {
					//update hit array
					this.hit[this.getBowRow()-row] = true;
					return true;
					}
				}
		}
		return false;
		
	}
	
	/**
	 * Determines whether a ship has sunk or not.
	 * @return true if the ship has sunk; false otherwise
	 */
	boolean isSunk() {
		//Iterate through hit array, see if any points are not hit
		for (int i=0; i < this.hit.length; i++) {
			if (this.hit[i] == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Overrides the toString() method to print "x" if a shot hits a part of a ship or "s" if the ship has now been sunk
	 */
	@Override
	public String toString() {
		//if sunk return s
		if (this.isSunk()) {
			return "s";
		}
		//else return x
		else {
			return "x";
		}
	}
}
