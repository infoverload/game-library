/**
 * This program allows the user to play either "rock, paper, scissors" or "one two penny pickup" 
 * against a computer opponent and informs the user of how many games have been played, won, lost,
 * and tied upon quitting.
 */

import java.io.*; 

public class GameLibrary {
	
	final static int USER_WON = 1;  
	final static int USER_LOST = 2; 
	final static int USER_TIED = 3; 
	
	/**
	 * Presents a menu of choices to the user
	 * @param args Not Used This Time
	 * return NOTHING
	 */
	public static void main (String [] args) throws Exception {
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in),1); 
		int choice;           
		int rpsGameWon = 0;   
		int rpsGameLost = 0;  
		int rpsGameTied = 0;  
		int otpGameWon = 0;   
		int otpGameLost = 0;  
		
		// present menu with three choices
		do {
			System.out.println("-------------------------------------");
			System.out.println("Daisy's Game Library");
			System.out.println();
			System.out.println("1: Play ROCK/PAPER/SCISSORS");
			System.out.println("2: Play ONE TWO PENNY PICKUP");
			System.out.println("3: Quit"); 
			System.out.println();
			System.out.println("Please enter your choice: ");
			choice = Integer.parseInt(keyboard.readLine()); 
			
			if (choice == 1) {
				int gameResult = rockPaperScissors(keyboard); // stores choice of game and initiates game chosen
				if (gameResult == USER_TIED) {
					rpsGameTied++;
				}
				else if (gameResult == USER_WON) {
					rpsGameWon++;
				}
				else {
					rpsGameLost++;
				}
			} 
			else if (choice == 2) {
				int gameResult = oneTwoPenny(keyboard); // stores choice of game and initiates game chosen
				if (gameResult == USER_WON) {
					otpGameWon++;
				}
				else {
					otpGameLost++;
				}
			} 
			else if (choice != 3) {
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 3); 
		
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println("You played " + (rpsGameTied + rpsGameWon + rpsGameLost) + " games of ROCK PAPER SCISSORS and you won " 
				+ rpsGameWon + " games, tied " + rpsGameTied + " games, and lost " + rpsGameLost + " games.");
		System.out.println("You played " + (otpGameWon + otpGameLost) + "games of ONE TWO PENNY PICKUP and lost " + otpGameLost + " games.");
		System.out.println();
		System.out.println("Thanks for playing!");
		
	} // main
	
	/**
	 * Allows user to play Rock/Paper/Scissors
	 * @param args Not Used This Time
	 * @param keyboard parameter 
	 * @return number of games won, lost, tied
	 */
	public static int rockPaperScissors (BufferedReader keyboard) throws Exception {
		
		final int ROCK = 0;
		final int PAPER = 1;
		final int SCISSORS = 2;
		int userChoice = 0;
		int computerChoice = 0;
		
		System.out.println();
		System.out.println("------------------------------------");
		System.out.println("ROCK PAPER SCISSORS");
		System.out.println("Please enter either 0 (for ROCK), 1 (for PAPER), or 2 (for SCISSORS): ");
		userChoice = Integer.parseInt(keyboard.readLine());
		
		while ((userChoice < 0) || (userChoice > 2)) {
			System.out.print("Invalid choice. Try again: ");
			userChoice = Integer.parseInt(keyboard.readLine());
		}
		
		if (userChoice == ROCK) {
			System.out.println("You chose: ROCK");
		}
		else if (userChoice == PAPER) {
			System.out.println("You chose: PAPER");
		}
		else {
			System.out.println("You chose: SCISSORS");
		}
		
		computerChoice = (int)(Math.random()*3);
		if (computerChoice == ROCK) {
			System.out.println("The computer chose: ROCK");
		}
		else if (computerChoice == PAPER) {
			System.out.println("The computer chose: PAPER");
		}
		else {
			System.out.println("The computer chose: SCISSORS");
		}
		System.out.println(); 
		
		// conditions for winning and losing the game AND return games won/lost/tied back to main method
		if (userChoice == computerChoice) {
			System.out.println("Tied game");
			return USER_TIED;
		}
		else if ((userChoice == ROCK) && (computerChoice == SCISSORS)) {
			System.out.println("You win!");
			return USER_WON;
		}
		else if ((userChoice == PAPER) && (computerChoice == ROCK)) {
			System.out.println("You win!");
			return USER_WON;
		}
		else if ((userChoice == SCISSORS) && (computerChoice == PAPER)) {
			System.out.println("You win!");
			return USER_WON;
		}
		else {
			System.out.println("Computer wins");
			return USER_LOST;
		}
			
	} // rockPaperScissors
	
	/**
	 * Allows user to play One Two Penny Pickup
	 * @param keyboard parameter
	 * @return number of games won and lost 
	 */
	public static int oneTwoPenny (BufferedReader keyboard) throws Exception {
		
		int userChoice;
		int computerChoice = 0;
		int startPennies = 0;
		int endPennies = 0;
		int MIN_PENNIES = 4;
		int MAX_PENNIES = 10; 
		boolean userMoveLast = true;
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("ONE TWO PENNY PICKUP");
		
		startPennies = (int)(Math.random()*(MAX_PENNIES - MIN_PENNIES + 1)) + MIN_PENNIES; 
		endPennies = startPennies;
		
		System.out.println("This game will have " + startPennies + " pennies to begin with.");
		
		// actions to be executed while ending pennies does not equal to zero
		do {
			userMoveLast = true;
			
			do {
				System.out.print("Please enter how many pennies you wish to pick up: "); 
				userChoice = Integer.parseInt(keyboard.readLine());
				if ((userChoice < 1) || (userChoice > 2) || (userChoice > endPennies)) {
					System.out.println("Sorry, the number of pennies must be 1 or 2 and you cannot pick up more pennies than there are left.");
				}
			} while ((userChoice < 1) || (userChoice > 2) || (userChoice > endPennies)); 
			
			endPennies = endPennies - userChoice; 
			System.out.println("Now there " + (endPennies !=1 ? "are " : "is ") + endPennies + " penn" + (endPennies !=1 ? "ies" : "y") + " left.");
			
			if (endPennies > 0) {
				userMoveLast = false;
				if (endPennies <= 3) {
					computerChoice = endPennies - 1; 
					if (computerChoice == 0) {
						computerChoice = 1;
					}
					
				}
				else {
					computerChoice = (int)(Math.random()*(2 -1 + 1)) + 1; 
				}
				
				System.out.println("The computer picked up " + computerChoice + " penn" + (computerChoice !=1 ? "ies." : "y.")); 
				endPennies = endPennies - computerChoice;
				System.out.println("Now there " + (endPennies !=1 ? "are " : "is ") + endPennies + " penn" + (endPennies !=1 ? "ies" : "y") + " left."); 
				
			} // if
			
		} while (endPennies > 0); 
		
		// return games won and lost back to main method
		if (userMoveLast) {
			System.out.println();
			System.out.println("You picked up the last penny: You lost");
			return USER_LOST; 
		}
		else {
			System.out.println();
			System.out.println("The computer picked up the last penny: You won!");
			return USER_WON; 
		}
		
	} // oneTwoPenny

} // end of class
