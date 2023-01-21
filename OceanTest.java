package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * class for testing the Ocean class & all its methods 
 * @author Ignacio Villasmil & Chinedu Okoroafor  
 *
 */
class OceanTest {

	Ocean ocean;
	/**
	 * number of each type of ship in the game to start 
	 */
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	
	/**
	 * size fo the ocean for the game (10x10)
	 */
	static int OCEAN_SIZE = 10;

	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	/** 
	 * test for all positions in the ocean to be empty to start
	 */
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	/**
	 * test for placeAllShipsRandomly() method
	 */
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	/**
	 * test for isOccupied() method
	 */
	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//TODO
		//More tests
		
		// checking for all other parts of the destroyer ship:
		assertTrue(ocean.isOccupied(0, 5));
		
		
		// checking for all parts of a submarine ship:
		assertTrue(ocean.isOccupied(0, 0));
		
		// checking the surrounding area of a ship:
		assertFalse(ocean.isOccupied(1, 4));
		assertFalse(ocean.isOccupied(1, 6));
		assertFalse(ocean.isOccupied(0, 4));
		assertFalse(ocean.isOccupied(0, 6));
		assertFalse(ocean.isOccupied(2, 4));



	}

	/**
	 * test for shootAt() method
	 */
	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//TODO
		//More tests
		
		
		// ship should now be sunk 
		assertTrue(destroyer.isSunk());
		
		// shooting at an already sunken ship should return False:
		assertFalse(ocean.shootAt(1, 5));
		assertFalse(ocean.shootAt(0, 5));
		
		// create a different ship:
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = true;
		
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(9, 6)); // missed shot
		assertFalse(cruiser.isSunk());

		assertTrue(ocean.shootAt(9, 7)); // hit the ship
		assertFalse(cruiser.isSunk());

		assertTrue(ocean.shootAt(9, 7)); // hit the same spot of the ship again
		assertFalse(cruiser.isSunk());

		assertTrue(ocean.shootAt(9, 8)); // hit next part of ship
		assertFalse(cruiser.isSunk());

		assertTrue(ocean.shootAt(9, 9)); // hit last part of ship
		assertTrue(cruiser.isSunk());


		
	}

	/**
	 * test for getShotsFired() method
	 */
	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//TODO
		//More tests
		
		// shoot missed shots around the submarine:
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 1));
		assertFalse(ocean.shootAt(1, 0));
		// count should now have 3 more:
		assertEquals(9, ocean.getShotsFired());
		
		// add a cruiser to be hit later:
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		
		// hit and sink the submarine:
		assertFalse(submarine.isSunk());
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		// count should now have 10:
		assertEquals(10, ocean.getShotsFired());
		
		// hit the added cruiser & sink it:
		
		// hit the same spot on the cruiser 3 times
		assertTrue(ocean.shootAt(9, 9));
		assertTrue(ocean.shootAt(9, 9));
		assertTrue(ocean.shootAt(9, 9));
		
		// should not be sunk yet:
		assertFalse(cruiser.isSunk());
		// count should now have 3 more:
		assertEquals(13, ocean.getShotsFired());

		// hit the other 2 spots of the cruiser
		assertTrue(ocean.shootAt(9, 8));
		assertTrue(ocean.shootAt(9, 7));
		// should now be sunk:
		assertTrue(cruiser.isSunk());
		// count should now have 2 more:
		assertEquals(15, ocean.getShotsFired());
		
		// shoot more at the already sunken ship & the count should go up:
		assertFalse(ocean.shootAt(9, 7));
		assertEquals(16, ocean.getShotsFired());

		

		
		
	}

	/**
	 * test for getHitCount() method
	 */
	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//TODO
		//More tests
		
		// hit the same spot of the ship 3 more times: 
		assertTrue(ocean.shootAt(1, 5));
		assertTrue(ocean.shootAt(1, 5));
		assertTrue(ocean.shootAt(1, 5));

		// hit count should now have 3 more:
		assertEquals(4, ocean.getHitCount());
		
		// shoot and miss twice; the hit count should stay the same:
		assertFalse(ocean.shootAt(0, 0));
		assertFalse(ocean.shootAt(0, 0));
		assertEquals(4, ocean.getHitCount());
		
		// shoot at the last part of the ship to sink it:
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		
		// count should now have 1 more:
		assertEquals(5, ocean.getHitCount());
		
		// shoot at the already sunken ship position:
		assertFalse(ocean.shootAt(0, 5));
		assertFalse(ocean.shootAt(0, 5));
		// this should NOT update hit count:
		assertEquals(5, ocean.getHitCount());

		
	}
	
	/**
	 * test for getShipsSunk() method
	 */
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//TODO
		//More tests
		
		// shoot at the same spot 3 more times:
		assertTrue(ocean.shootAt(1, 5));
		assertTrue(ocean.shootAt(1, 5));
		assertTrue(ocean.shootAt(1, 5));
		// update hit count but not ships sunk
		assertFalse(destroyer.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		// add a submarine:
		Submarine submarine = new Submarine();
		row=9;
		column=9;
		horizontal=false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		// hitting the submarine 
		assertTrue(ocean.shootAt(9, 9));
		assertTrue(submarine.isSunk());
		// update hit count & sunken ship count:
		assertEquals(5, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		// shoot missed shots (count should stay the same):
		assertFalse(ocean.shootAt(0, 0));
		assertFalse(ocean.shootAt(0, 0));
		assertFalse(ocean.shootAt(0, 0));
		// same counts:
		assertEquals(5, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());

		
		// shoot at the remaining part of the destroyer to sink it:
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		// update both counts:
		assertEquals(6, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		

		
	}

	/**
	 * test for getShipArray() method
	 */
	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//TODO
		//More tests
		
		// add a battleship
		Battleship battleship = new Battleship();
		int row = 9;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		// check the 2 ends of the battleship 
		assertEquals("battleship", shipArray[9][9].getShipType());
		assertEquals("battleship", shipArray[9][6].getShipType());
		
		// check on the side of the ship; should be empty:
		assertEquals("empty", shipArray[9][5].getShipType());
		
		// add a cruiser & check:
		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 0;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals("cruiser", shipArray[9][0].getShipType());

		
		// add a destroyer & check:
		Destroyer destroyer = new Destroyer();
		row = 0;
		column = 9;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals("destroyer", shipArray[0][9].getShipType());
		
		// add a submarine & check:
		Submarine submarine = new Submarine();
		row = 5;
		column = 5;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals("submarine", shipArray[5][5].getShipType());
		
		
		
	}

}