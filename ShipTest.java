package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * class for test cases of the Ship class & all its methods
 * @author Ignacio Villasmil & Chinedu Okoroafor
 *
 */
class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	/**
	 * testing the getLength() method
	 */
	@Test
	void testGetLength() {
		// battleship
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		
		// cruiser
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		// destroyer
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
		// submarine
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		
	}


	/**
	 * testing the getBowRow() method
	 */
	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		
		// vertical battleship (instead of horizontal)
		Ship battleshipVertical = new Battleship();
		row = 5;
		column = 9;
		horizontal = false;
		battleshipVertical.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleshipVertical.getBowRow());
		
		// for submarine (vertical)
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
		
		// for cruiser (vertical)
		Ship cruiser = new Cruiser();
		row = 9;
		column = 0;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		
		// for destroyer (vertical)
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		
		
		
		
	}

	/**
	 * testing the getBowColumn() method
	 */
	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		
		// vertical battleship (instead of horizontal) 
		Ship battleshipVertical = new Battleship();
		row = 5;
		column = 9;
		horizontal = false;
		battleshipVertical.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, battleshipVertical.getBowColumn());
		
		// for submarine (vertical)
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, submarine.getBowColumn());
		
		// for cruiser (vertical)
		Ship cruiser = new Cruiser();
		row = 9;
		column = 0;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, cruiser.getBowColumn());
		
		// for destroyer (vertical)
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, destroyer.getBowColumn());
		
		
	}

	/**
	 * testing the getHit() method
	 */
	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		
		// test for when a ship is hit at a specific position 
		// (place the ship first)
		int row = 9;
		int column = 9;
		boolean horizontal = true;
		ship.placeShipAt(row, column, horizontal, ocean);		
		ship.shootAt(row, column);
		assertTrue(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
		
		// shoot a missed shot & the hit array should not change 
		ship.shootAt(row-1, column-5);
		assertTrue(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
		
		// shoot at another spot of ship & update hit array
		ship.shootAt(row, column-1);
		assertTrue(ship.getHit()[0]);
		assertTrue(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
		
		// shoot a spot that was already shot & the hit array should not change
		ship.shootAt(row, column-1);
		assertTrue(ship.getHit()[0]);
		assertTrue(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);

		
		// shoot at another spot of ship & update hit array
		ship.shootAt(row, column-2);
		assertTrue(ship.getHit()[0]);
		assertTrue(ship.getHit()[1]);
		assertTrue(ship.getHit()[2]);
		assertFalse(ship.getHit()[3]);
		
		// shoot at another spot of ship & update hit array
		ship.shootAt(row, column-3);
		assertTrue(ship.getHit()[0]);
		assertTrue(ship.getHit()[1]);
		assertTrue(ship.getHit()[2]);
		assertTrue(ship.getHit()[3]);
		
	
		
	}
	
	/**
	 * testing the getShipType() method
	 */
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		
		// for cruiser
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		
		// for destroyer
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
		// for submarine 
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
	}
	
	/**
	 * testing the isHorizontal() method 
	 */
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests	
		
		// test for vertical cases of cruiser placement
		Ship cruiser = new Cruiser();
		row = 5;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isHorizontal());
		
		// test for horizontal cases of submarine placement
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertTrue(submarine.isHorizontal());
		
		// test for vertical cases of submarine placement
		Ship submarineVertical = new Submarine();
		row = 0;
		column = 1;
		horizontal = false;
		submarineVertical.placeShipAt(row, column, horizontal, ocean);
		assertFalse(submarineVertical.isHorizontal());
		
		
		
		
		
	}
	
	/**
	 * testing the setBowRow() method
	 */
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		
		// test for vertical placements of cruisers
		Ship cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());
		
		// after setting the bow row, xcheck that the bow is NOT facing north (up)
		assertTrue((row-2) != cruiser.getBowRow());
		
		// test for horizontal placements of destroyers
		Ship destroyer = new Destroyer();
		row = 1;
		column = 9;
		horizontal = true;
		destroyer.setBowRow(row);
		assertEquals(row, destroyer.getBowRow());
		
	}

	/**
	 * testing the setBowColumn() method
	 */
	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		
		// after setting bow column, check that the bow is NOT facing west (left)
		assertTrue((column-3) != battleship.getBowColumn());
		
		// test for vertical placements of cruisers
		Ship cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());
		
		// test for horizontal placements of destroyers
		Ship destroyer = new Destroyer();
		row = 1;
		column = 9;
		horizontal = true;
		destroyer.setBowColumn(column);
		assertEquals(column, destroyer.getBowColumn());
		
	}

	/**
	 * testing the setHorizontal() method
	 */
	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		
		// test for when it is placed vertically
		Ship battleshipVertical = new Battleship();
		row = 9;
		column = 0;
		horizontal = false;
		battleshipVertical.setHorizontal(horizontal);
		assertFalse(battleshipVertical.isHorizontal());
		
		// test on different type of ships:
		// cruiser
		Ship cruiser = new Cruiser();
		row = 5;
		column = 9;
		horizontal = true;
		cruiser.setHorizontal(horizontal);
		assertTrue(cruiser.isHorizontal());
		
		// destroyer
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = true;
		destroyer.setHorizontal(horizontal);
		assertTrue(destroyer.isHorizontal());
		
		// submarine 
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.setHorizontal(horizontal);
		assertFalse(submarine.isHorizontal());
	}
	
	

	/**
	 * testing the okToPlaceShipAt() method 
	 */
	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
		
		// test adding different type of ships and seeing if it's ok (cases where they should be ok):
		
		// cruiser:
		Ship cruiser = new Cruiser();
		row = 0;
		column = 9;
		horizontal = true;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		// destroyer:
		Ship destroyer = new Destroyer();
		row = 9;
		column = 9;
		horizontal = true;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		// submarine:
		Ship submarine = new Submarine();
		row = 9;
		column = 0;
		horizontal = true;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		submarine.placeShipAt(row,column,horizontal,ocean);
		assertTrue(ok, "OK to place ship here.");
		
		Ship destroyerIllegal = new Destroyer();
		row = 0;
		column=1;
		horizontal = true;
		ok = destroyerIllegal.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		// test adding different types of ships in illegal positions (cases where ok is False):
		
		// trying to place ship directly to the left/right of where another ship is placed:
		Ship submarineIllegal1 = new Submarine();
		row = 9;
		column=1;
		horizontal = true;
		ok = submarineIllegal1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Not OK to place ship here.");
		
		// trying to place ship directly above/below another ship:
		Ship battleshipIllegal = new Battleship();
		row = 8;
		column=3;
		horizontal = true;
		ok = battleshipIllegal.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Not OK to place ship here.");
		
		// trying to place a cruiser positioned diagonally to another ship (submarine)
		Ship cruiserIllegal = new Cruiser();
		row = 8;
		column=1;
		horizontal = false;
		ok = cruiserIllegal.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Not OK to place ship here.");
		
		// testing trying to place a ship outside of the map of the game (row/column positions less than 0 or greater than 9):
		
		
		// placing at positions greater than 9
		Ship outOfBoundsSubmarine = new Submarine();
		row = 10;
		column=10;
		horizontal = false;
		ok = outOfBoundsSubmarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Not OK to place ship here.");
		
		// placing at positions less than 0
		Ship outOfBoundsCruiser = new Cruiser();
		row = -1;
		column = 9;
		horizontal = false;
		ok = outOfBoundsCruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "Not OK to place ship here.");
		
		
	}
	
	/**
	 * testing the okToPlaceShipAt() method specifically against where other ships are placed
	 */
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);
		
		//TODO
		//More tests
		// trying to place ship destroyer where battleship1 is
		Ship destroyerIllegal = new Destroyer();
		row = 0;
		column=1;
		horizontal = true;
		boolean ok3 = destroyerIllegal.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship here.");
		
		
		// trying to place ship directly to the left of battleship1:
		Ship submarineIllegal = new Submarine();
		row = 0;
		column=0;
		horizontal = true;
		boolean ok4 = submarineIllegal.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship here.");
		
		//Try (and succeed) to place second battleship
		Battleship battleship2 = new Battleship();
		row = 3;
		column = 6;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok2, "OK to place ship vertically adjacent below.");
		battleship2.placeShipAt(row, column, horizontal, ocean);
		
		// trying to place ship directly above:
		Ship battleshipIllegal = new Battleship();
		row = 2;
		column=6;
		horizontal = true;
		boolean ok5 = battleshipIllegal.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok5, "Not OK to place ship here.");
		
		//Add cruiser
		Cruiser cruiser1 = new Cruiser();
		row = 8;
		column = 8;
		horizontal = false;
		ok1 = cruiser1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		cruiser1.placeShipAt(row, column, horizontal, ocean);
		
		//Try to add cruiser2 to the right of cruiser1
		Cruiser cruiser2 = new Cruiser();
		row = 8;
		column = 9;
		horizontal = false;
		ok1 = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok1, "Not OK to place ship here.");

		
		//add destroyer
		Destroyer destroyer1 = new Destroyer();
		row = 9;
		column = 1;
		horizontal = true;
		ok1 = destroyer1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		destroyer1.placeShipAt(row, column, horizontal, ocean);
		
		// trying to place a ship positioned diagonally to destroyer1
		Ship cruiserIllegal = new Cruiser();
		row = 8;
		column=2;
		horizontal = false;
		boolean ok6 = cruiserIllegal.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok6, "Not OK to place ship here.");
		

		
	}
	
	
	
	/**
	 * testing the placeShipAt() method
	 */
	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests
		
		// placing a vertical cruiser ship
		Ship cruiser = new Cruiser();
		row = 9;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertFalse(cruiser.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[6][9].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[7][9]);
		assertEquals(cruiser, ocean.getShipArray()[8][9]);
		assertEquals(cruiser, ocean.getShipArray()[9][9]);
		
		// placing a submarine:
		Ship submarine = new Submarine();
		row = 5;
		column = 5;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
		assertEquals(column, submarine.getBowColumn());
		assertFalse(submarine.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[4][5].getShipType()); // empty all around the submarine
		assertEquals("empty", ocean.getShipArray()[6][5].getShipType()); // empty all around the submarine
		assertEquals("empty", ocean.getShipArray()[4][4].getShipType()); // empty all around the submarine
		assertEquals("empty", ocean.getShipArray()[4][6].getShipType()); // empty all around the submarine

		assertEquals(submarine, ocean.getShipArray()[5][5]);
		
		// placing a destroyer:
		Ship destroyer = new Destroyer();
		row = 0;
		column = 9;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		assertEquals(column, destroyer.getBowColumn());
		assertTrue(destroyer.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][7].getShipType());
		assertEquals(destroyer.getShipType(), ocean.getShipArray()[0][9].getShipType());
		assertEquals(destroyer.getShipType(), ocean.getShipArray()[0][8].getShipType());

	}
	
	
	/**
	 * testing the shootAt() method
	 */
	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		
		// shooting at the ship successfully (hit):
		assertTrue(battleship.shootAt(0, 9));
		boolean[] hitArray1 = {true, false, false, false};
		assertArrayEquals(hitArray1, battleship.getHit());
		
		assertTrue(battleship.shootAt(0, 8));
		boolean[] hitArray2 = {true, true, false, false};
		assertArrayEquals(hitArray2, battleship.getHit());
		
		assertTrue(battleship.shootAt(0, 7));
		boolean[] hitArray3 = {true, true, true, false};
		assertArrayEquals(hitArray3, battleship.getHit());
		
		assertTrue(battleship.shootAt(0, 6));
		boolean[] hitArray4 = {true, true, true, true};
		assertArrayEquals(hitArray4, battleship.getHit());
		
		// ship should be sunk now:
		assertTrue(battleship.isSunk());
		
		// ship should be sunk now, so shooting at any of these positions again should return false:
		assertFalse(battleship.shootAt(0, 9)); // since it's already sunk


		
	}
	
	/**
	 * testing the isSunk() method
	 */
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		
		// hit the submarine & sink it:
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());
		assertFalse(submarine.shootAt(3, 3)); // since it has already sunk, should return false
		
		// hitting a longer ship several times, and not sinking until all parts are hit:
		Ship cruiser = new Cruiser();
		row = 0;
		column = 9;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(cruiser.isSunk());
		
		assertTrue(cruiser.shootAt(0, 9)); // hit 1/4 parts of ship
		assertFalse(cruiser.isSunk());
		
		assertTrue(cruiser.shootAt(0, 8)); // hit 2/4 parts of ship
		assertFalse(cruiser.isSunk());
		
		assertTrue(cruiser.shootAt(0, 7)); // hit 3/4 parts of ship
		assertTrue(cruiser.isSunk());
		
		

	}
	

	
	/**
	 * testing the toString() method
	 */
	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
		
		// shoot at the remaining parts of the ship: not sunk yet
		battleship.shootAt(8, 1);
		assertEquals("x", battleship.toString());
		
		// shoot at the remaining parts of the ship: not sunk yet
		battleship.shootAt(7, 1);
		assertEquals("x", battleship.toString());
		
		// shooting at an already hit part of the ship:
		battleship.shootAt(7, 1);
		assertEquals("x", battleship.toString());
		
		// shoot at the remaining parts of the ship: NOW SUNK
		battleship.shootAt(6, 1);
		assertEquals("s", battleship.toString());
		
		
	}

}


