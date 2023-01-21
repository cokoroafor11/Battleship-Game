package battleship;

import java.util.Random;

/**
 * class representing the 10x10 ocean for the Battleship game
 * @author Ignacio Villasmil & Chinedu Okoroafor
 *
 */
public class Ocean {
	/**
	 * Number of each ship type in the fleet
	 */
	static final int NUM_BATTLE = 1;
	static final int NUM_CRUISE = 2;
	static final int NUM_DESTROY = 3;
	static final int NUM_SUB = 4;
	
	
	
	
	
	/**
	 * Used to quickly determine which ship is in any given location
	 */
	private Ship [][]ships = new Ship[10][10];
	
	/**
	 * Used to track places that have been hit
	 */
	private String [][]oceanHit = new String[10][10];
	
	/**
	 * The total number of shots fired by the user
	 */
	private int shotsFired;
	
	/**
	 * The number of times a shot hit a ship. 
	 */
	private int hitCount;
	
	/**
	 * The number of ships sunk (10 ships in all)
	 */
	private int shipsSunk;

	
	/**
	 * represents the ocean.
	 * will keep count of shots fired, sunk ships, hits, etc.
	 */
	public Ocean() {
		//initialize necessary variables
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
		
		//Place dots for the array used to represent the ocean board
		for (int i=0;i <oceanHit.length; i++) {
			for (int j=0; j < oceanHit[i].length; j++) {
				oceanHit[i][j] = ".";
			}
		}
		//Place Empty ships 	
		for (int i=0;i <this.ships.length; i++) {
			for (int j=0; j < this.ships[i].length; j++) {
				Ship ship = new EmptySea();
				ship.placeShipAt(i, j, true, this);
			}
		}
		
	}
	
	
	/**
	 * Place all ten ships randomly on the (initially empty) ocean. 
	 * Place larger ships before smaller ones
	 */
	void placeAllShipsRandomly() {
		
		//create random, row, column, horizontal variables
		Random rand = new Random();
		int row;
		int column;
		int toHorizontal;
		boolean horizontal;
		
		//PlACE BATTLESHIP
		
		//Create instance of battleship and get random location, horizontality
	 	Battleship battleship = new Battleship();
	 	row = rand.nextInt(10);
		column = rand.nextInt(10);
		toHorizontal = rand.nextInt(2);
		if (toHorizontal == 0) {
			horizontal = true;
		}
		else {
			horizontal = false;
		}
		//Check if ok to place ship, if not, run through loop
	 	while(!battleship.okToPlaceShipAt(row, column, horizontal, this)) {
	 		//If not ok, get new location and horizontal values
	 		row = rand.nextInt(10);
			column = rand.nextInt(10);
			toHorizontal = rand.nextInt(2);
			if (toHorizontal == 0) {
				horizontal = true;
			}
			else {
				horizontal = false;
			}
	 	}
		
	 	//once acceptable location found, place ship
		battleship.placeShipAt(row, column, horizontal, this);
		
		
		//PLACE CRUISER
		
		//Loop for creating the correct number of cruisers
		for (int i=0; i<NUM_CRUISE;i++) {
			//Create instance of cruiser
			Cruiser cruiser = new Cruiser();
			//Check if ok to place ship, if not, run through loop
		 	while(!cruiser.okToPlaceShipAt(row, column, horizontal, this)) {
		 		//If not ok, get new location and horizontal values
		 		row = rand.nextInt(10);
				column = rand.nextInt(10);
				toHorizontal = rand.nextInt(2);
				if (toHorizontal == 0) {
					horizontal = true;
				}
				else {
					horizontal = false;
				}
		 	}
		 	//once acceptable location found, place ship
			cruiser.placeShipAt(row, column, horizontal, this);
		}
			
		//PLACE DESTROYERS
		
		//Loop for creating the correct number of cruisers
		for (int i=0; i<NUM_DESTROY;i++) {
			//Create instance of cruiser
			Destroyer destroyer = new Destroyer();
			//Check if ok to place ship, if not, run through loop
		 	while(!destroyer.okToPlaceShipAt(row, column, horizontal, this)) {
		 		//If not ok, get new location and horizontal values
		 		row = rand.nextInt(10);
				column = rand.nextInt(10);
				toHorizontal = rand.nextInt(2);
				if (toHorizontal == 0) {
					horizontal = true;
				}
				else {
					horizontal = false;
				}
		 	}
		 	//once acceptable location found, place ship
			destroyer.placeShipAt(row, column, horizontal, this);
		}
		
		//PLACE SUBMARINES
		//Loop for creating the correct number of cruisers
		for (int i=0; i<NUM_SUB;i++) {
			//Create instance of cruiser
			Submarine submarine = new Submarine();
			//Check if ok to place ship, if not, run through loop
		 	while(!submarine.okToPlaceShipAt(row, column, horizontal, this)) {
		 		//If not ok, get new location and horizontal values
		 		row = rand.nextInt(10);
				column = rand.nextInt(10);
				toHorizontal = rand.nextInt(2);
				if (toHorizontal == 0) {
					horizontal = true;
				}
				else {
					horizontal = false;
				}
		 	}
		 	//once acceptable location found, place ship
			submarine.placeShipAt(row, column, horizontal, this);
		}
		
	}
		
	
	
	/**
	 * Returns true if the given location contains a ship, false if it does not 
	 * @param row of interest 
	 * @param column of interest 
	 * @return true (if it has a ship) or false (if it does not)
	 */
	boolean isOccupied(int row, int column) {
		//check if a specific location has empty ship
		if(this.ships[row][column].getShipType().equals("empty")) {
			return false;
		}
		//if not, return true
		return true;
	}
	
	/**
	 * Returns true if the given location contains a ”real” ship, still afloat, (not an  
	 * EmptySea), false if it does not. In addition, this method updates the number of 
	 * shots  that have been fired, and the number of hits. 
	 * @param row of interest
	 * @param column of interest
	 * @return true (if it hits a non-sunken ship) or false (otherwise)
	 */
	boolean shootAt(int row, int column)  {
		
		//increment shots fired
		this.shotsFired+=1;
		
		Ship ship = this.ships[row][column];
		//case where ship is sunk, return false
		if(ship.isSunk()) {
			return false;
		}
		
		//case where location has a ship - returning true
		if(ship.shootAt(row,column)) {
			//increment hit count
			this.hitCount+=1;
			//set ocean array to x
			oceanHit[row][column] = "x";
			//if it then becomes sunk
			if(ship.isSunk()) {
				//increment ships sunk and set array to s
				this.shipsSunk+=1;
				oceanHit[row][column] = "s";
			}
			return true;
		}
		//nothing hit, so set oceann array to - and return false
		oceanHit[row][column] = "-";
		return false;
	}
	
	/**
	 * 
	 * @return number of shots fired
	 */
	int getShotsFired() {
		return shotsFired;
	}
	
	/**
	 * @return number of hits
	 */
	int getHitCount() {
		return hitCount;
	}
	
	/** 
	 * @return number of ships sunk
	 */
	int getShipsSunk() {
		return shipsSunk;
	}
	
	/**
	 * @return boolean for if game is over (true if game is over)
	 */
	boolean isGameOver() {
		if(this.getShipsSunk()==10) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return the ship array 
	 */
	Ship[][] getShipArray() {
		return this.ships;
	}
	
	/**
	 * print the current status of the game:
	 * - where shots have hit a ship
	 * - where ships have sunk
	 * - where shots have missed & hit nothing 
	 */
	void print() {
			//print the top line
			System.out.println("   0  1  2  3  4  5  6  7  8  9");
			//loop through the 2D array
			for (int i = 0; i < 10; i++) {
				//for each new line, print the number for the row
				System.out.print(i+"  ");
				for (int j = 0; j < 10; j++) {
					//check if the ship has just been sunk, if so, change the location to s
					if(this.ships[i][j].isSunk()) {
						oceanHit[i][j] = "s";
					}
					//print the element in the ocean array
					System.out.print( oceanHit[i][j] + "  ");
				}
				//create new line
				System.out.println();
		}
	}
}
