package battleship;

import java.util.Scanner;


/**
 * class representing the Battleship game, how it works, & controls the operations of the game.
 * @author Ignacio Villasmil & Chinedu Okoroafor
 *
 */
public class BattleshipGame {
	
	private Ocean ocean;
	
	/**
	 * initializing the game & placing all ships randomly
	 */
	public BattleshipGame() {
		//Create instance of ocean
		this.ocean = new Ocean();
		//place ocean ships
		this.ocean.placeAllShipsRandomly();
	}

	/** 
	 * main function for the operation of the game
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean playAgain = true;
		boolean properIn = false;
		//Create scanner
		Scanner sc = new Scanner(System.in);

		//loop through until user doesn't want to play again
		while(playAgain ) {
			//Welcome the user
			System.out.println("Welcome to Battleship! Ready Your Ships!");
			
			//create instance of battleship
			BattleshipGame battle = new BattleshipGame();
			
			//play and print results
			battle.playGame();
			battle.printResults();
			
			while(properIn==false) {
				
				//ask for replay [USER MUST INPUT SOMETHING STARTING WITH 'Y' or 'N'
				System.out.println("Would you like to play again? (y=yes, n=no)");
				String replay = sc.next().toLowerCase();
				//yes case
				if (replay.startsWith("y") ) {
					playAgain = true;
					properIn = true;
				}
				else if (replay.startsWith("n")) {
					playAgain = false;
					properIn = true;
					System.out.println("Thank you for playing!\n");
				}
				else {
					properIn = false;
				}
			}
			

		}
		sc.close();
	}
	
	/**
	 * used to verify whether user input is valid input
	 * @param input - integer inputs by user for row & column
	 * @return true if the user input is valid; return false otherwise
	 */
	public boolean verifyInput(String [] input) {
		
			//Make sure inputs are numbers in range
			try {
				int num = Integer.parseInt(input[0]);
				int num1 = Integer.parseInt(input[1]);
				//if first number is out of range
				if (num < 0 || num > 9) {
					System.out.println("Inputted value not in range.");
					return false;
				}
				//if second number is out of range
				if (num1 < 0 || num1 > 9) {
					System.out.println("Inputted value not in range.");
					return false;
				}
				//if length is incorrect
				if (input.length != 2) {
					System.out.println("Please only enter 2 coordinates.");
					return false;
				}
				
				return true;
			}
			//check if number is not integer
			catch (NumberFormatException e) {
				System.out.println("Inputted value is not an integer.");
				return false;
			}
			
			//catch if not 1 coordinate entered
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Please enter 2 integers.");
				return false;
			}
			}
			
	/**
	 * print results of the game & offer to play again
	 */
	public void printResults() {
		
		//print all sunk, number of shots fired
		System.out.println("You sank all the opponents ships!");
		System.out.println("In total, you fired " + this.ocean.getShotsFired());
	}
	
	/**
	 * method for playing the game, keeping track if game is over, & printing updates as the game progresses.
	 */
	public void playGame () {
		
		//Create scanner
		Scanner sc = new Scanner(System.in);
		
		//initialize necessary variables
		String input = "";
		String [] rowCol;
		int row;
		int column;

		while(!this.ocean.isGameOver()) {
			
			//print the ocean
			this.ocean.print();
			
			//Input Handling
			System.out.println("Enter row,column (separate by ,): ");
			input = sc.nextLine();
			
			//loop for if wrong input is entered
			while(!verifyInput(input.split(","))) {
				input = sc.nextLine();
			}
			
			//get the row and column array, then grab each individual element
			rowCol = input.split(",");
			row = Integer.parseInt(rowCol[0]);
			column = Integer.parseInt(rowCol[1]);
			System.out.println("\n");
			
			//shoot at this coordinate
			this.ocean.shootAt(row, column);
			
			//print the number of shots fired and ships sunk
			System.out.println("You have fired " + this.ocean.getShotsFired() + " shots.");
			System.out.println("You've sunken " + this.ocean.getShipsSunk() + " ships.\n");
			
		}
		//print a final time after the game has ended
		this.ocean.print();
	}

}



