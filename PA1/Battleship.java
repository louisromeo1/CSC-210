/* 
 * Name: Louis Romeo
 * Course: CSC 210
 * Purpose: This program is designed to play the board game
 * Battleship on the command line against the computer.
 */ 

import java.util.Scanner;

public class Battleship {
	
	public static int playerShips = 2;
	public static int comShips = 2;
	
	private static int cols = 5;
	private static int rows = 5;
	
	public static String[][] grid = new String[rows][cols];
	public static String[][] comGrid = new String[rows][cols];

	public static void main(String[] args) {
		/* 
		 * Main method is responsible for calling the proper
		 * game methods in the correct order.
		 */
		
		createGrid(grid);
		
		String [] comCoords = deployComShips();
		
		deployShips(comCoords);
	
	}
	
	public static void createGrid(String [][] curGrid) {
		/*
		 * CreateGrid method initially creates the board for
		 * the Battleship game. Creates one grid for the player's
		 * ships and a separate one for the computer's.
		 */
		
		for(int i = 0; i < 5; i++) {
			
			String [] row = new String[5];
			
			for(int j = 0; j < 5; j++) {
				
				row[j] = "█";
			
			}
			
			curGrid[i] = row;
		
		}
	}
	
	public static void deployShips(String [] comCoords) {
		/*
		 * This method is responsible for taking in user input,
		 * placing ships in the correct coordinates, and eventually
		 * playing the game against the computer. 
		 * param: comCoords an array list of the computer's ship
		 * coordinates utilized for reference.
		 */
		
		Scanner name = new Scanner(System.in);
		System.out.print("What is your first name?\n");
		String yourName = name.nextLine();
		
		System.out.print("Place your ships " + yourName + "!\n");
		System.out.print("You have two cruisers that are both of length 3.\n");
		System.out.print("Place ship number 1:\n");
		
		String [] coordinates = new String[6];
		
		int count = 0;
		
		for(int j=0; j < 2; j++) {
	
			for(int i=0; i < 3; i++) {
				
				Scanner ship = new Scanner(System.in);
				System.out.print("Enter the row and column (eg. B2):\n");
				String yourShip = ship.nextLine();
				
				coordinates[count] = yourShip;
				count++;
				
			}
			
			if(j == 0) {
				
				System.out.print("Place ship number 2:\n");
			
			}
		}
		
		placeShips(coordinates, 0); // Places player inputed ships.
		
		printBoard(0);
		printBoard(1);
		
		boolean gameOn = true; // Boolean true value for continuing game loop.
		String [] shots = null;
		
		// Main game while loop.
		while(playerShips != 0 && comShips != 0) {
			
				Scanner shot = new Scanner(System.in);
				System.out.println("Call your shot, " + yourName + "!");
				System.out.print("Enter the row and column (eg. B2):\n");
				String yourShot = shot.nextLine();
				
				int rows = yourShot.charAt(0) - 65;
				int columns = yourShot.charAt(1) - 48;
				boolean check = outcome(comCoords, yourShot, 0);
				
				if(check == true) {
					
					comShips--;
					comGrid[rows][columns] = "H";
				
				} else {
					
					comGrid[rows][columns] = "M";	
				}
				
				printBoard(0); // Prints computer board.
				printBoard(1); // Prints player's board.
				
				Scanner enter = new Scanner(System.in);
				System.out.println("Press the enter key to see the computer's shot:");
				String comMove = enter.nextLine();
				
				printBoard(0);
				
				int Xcoord = (int)(Math.random() * 4) + 0;
				int Ycoord = (int)(Math.random() * 4) + 0;
				
				if(grid[Xcoord][Ycoord].equals("C")) {
					
					playerShips--;
					grid[Xcoord][Ycoord] = "H";
				
				} else {
					
					grid[Xcoord][Ycoord] = "M";
				
				}
		}
		
		if(playerShips == 0) {
			
			System.out.println("You have lost.");
		
		} else {
			
			System.out.println("You win " + yourName + "!!!");
		}
	}
	
	public static boolean outcome(String [] coordinates, String yourShot, int switch1) {
		/*
		 * Boolean method which returns true or false based on
		 * where the return value is true or false based on
		 * whether or not a ship can be placed.
		 * param: coordinates an array list of ship coordinates.
		 * param: yourShot variable for the user's inputed shot.
		 * param: switch1 integer value determining whether the
		 * computer or player's board should be tested. 0 indicates
		 * the player's ships, anything else is computer.
		 */
		
		if(switch1 == 0) {
			
			int rows = yourShot.charAt(0) - 65;
		
		} else {
			
			int rows = yourShot.charAt(0);
		}
		
		String firstCoord = Integer.toString(rows);
		String curCoord = firstCoord + yourShot.charAt(1);
		
		for(String coord: coordinates) {
			
			if(curCoord.equals(coord)) {
				
				return true;
			}
		}
		return false;
	}
	
	public static void placeShips(String [] coordinates, int switch1) {
		/*
		 * Helper method for deployShips which simply
		 * places a C where the user determines the ships will go.
		 * param: coordinates an array list of ship coordinates.
		 */
		if(switch1 == 0) {
			for(String commands: coordinates) {
			
				int rows = commands.charAt(0) - 65;
				int columns = commands.charAt(1) - 48;
				grid[rows][columns] = "C";
			}
		} else {
			for(String commands: coordinates) {
				
				int rows = commands.charAt(0) - 65;
				int columns = commands.charAt(1) - 48;
				comGrid[rows][columns] = "C";
			}
		}
	}
	
	public static String [] deployComShips() {
		/*
		 * This method is in charge of deploying the computer's
		 * ships, which are randomly generated. It utilizes a switch
		 * integer variable which randomly determines the direction
		 * of the computer's placed ship. Returns an array of the coordinates
		 * at which the computer's ships were placed, and is stored as a
		 * reference in main.
		 */
		
		createGrid(comGrid);
		String [] coordinates = new String[6];
		int counter = 0;
		
		for(int i = 0; i < 2; i++) {
			
			boolean flag = true;
			
			// Loop for randomly generating computer ships.
			while(flag) {
				
				String Xcoord = Integer.toString((int)(Math.random() *4) + 0);
				String Ycoord = Integer.toString((int)(Math.random() *4) + 0);
				
				if(counter == 6) {
					
					flag = false;
					break;
				}
				
				if(outcome(coordinates, Xcoord + Ycoord, 1) == false) {
					
					if(counter == 6) {
						
						flag = false;
						break;
					}
					
					coordinates[counter] = Xcoord + Ycoord;
					counter++;
					int switch1 = (int)(Math.random() * 3) + 0;
					
					if(switch1 == 0) {
						
						if(Integer.valueOf(Ycoord) > 2) {
							
							String curX1 = Xcoord;
							String curY1 = Integer.toString((Integer.valueOf(Ycoord) - 1));
							
							String curX2 = Xcoord;
							String curY2 = Integer.toString((Integer.valueOf(Ycoord) - 2));
							
							if(outcome(coordinates, curX1 + curY1, 1) == false && 
									outcome(coordinates, curX2 + curY2, 1) == false) {
								
								if(counter < 5) {
								
									coordinates[counter] = Xcoord + Integer.toString((Integer.valueOf(Ycoord) - 1));
									coordinates[counter + 1] = Xcoord + Integer.toString((Integer.valueOf(Ycoord) - 2));
									counter += 2;
									flag = false;
									placeShips(coordinates, 1);
								}
							
							} else {
								
								counter--;
							}
						}
					}
					if(switch1 == 1) {
						
						if(Integer.valueOf(Xcoord) > 2) {
							
							String curX1 = Integer.toString((Integer.valueOf(Xcoord) - 1));
							String curY1 = Ycoord;
							String curX2 = Integer.toString((Integer.valueOf(Xcoord) - 2));
							String curY2 = Ycoord;
							
							if(outcome(coordinates, curX1 + curY1, 1) == false && 
									outcome(coordinates, curX2 + curY2, 1) == false) { 
							
								if(counter < 5) {
									
									coordinates[counter] = Integer.toString((Integer.valueOf(Xcoord) - 1)) + Ycoord;
									coordinates[counter + 1] = Integer.toString((Integer.valueOf(Xcoord) - 2)) + Ycoord;
									counter += 2;
									flag = false;
									placeShips(coordinates, 1);
								}
							
							} else {
								
								counter--;
							}
						}
					}
					if(switch1 == 2) {
						
						if(Integer.valueOf(Ycoord) < 2) {
							
							String curX1 = Xcoord;
							String curY1 = Integer.toString((Integer.valueOf(Ycoord) + 1));							
							String curX2 = Xcoord;
							String curY2 = Integer.toString((Integer.valueOf(Ycoord) + 2));
							
							if(outcome(coordinates, curX1 + curY1, 1) == false && 
									outcome(coordinates, curX2 + curY2, 1) == false) { 
								
								if(counter < 5) {
									
									coordinates[counter] = Xcoord + Integer.toString((Integer.valueOf(Ycoord) + 1));
									coordinates[counter + 1] = Xcoord + Integer.toString((Integer.valueOf(Ycoord) + 2));
									counter += 2;
									flag = false;
									placeShips(coordinates, 1);
								}
							
							} else {
								
								counter--;
							}
						}
					}
					if(switch1 == 3) {
						
						if(Integer.valueOf(Xcoord) < 2) {
							
							String curX1 = Integer.toString((Integer.valueOf(Xcoord) + 1));
							String curY1 = Ycoord;
							String curX2 = Integer.toString((Integer.valueOf(Xcoord) + 2));
							String curY2 = Ycoord;
							
							if(outcome(coordinates, curX1 + curY1, 1) == false && 
									outcome(coordinates, curX2 + curY2, 1) == false) { 
	
								if(counter < 5) {
									
									coordinates[counter] = Integer.toString((Integer.valueOf(Xcoord) + 1)) + Ycoord;
									coordinates[counter + 1] = Integer.toString((Integer.valueOf(Xcoord) + 2)) + Ycoord;
									counter += 2;
									flag = false;
									placeShips(coordinates, 1);
								}
							
							} else {
								
								counter--;
						}
					}
				}
			}
		}					
	}
		return coordinates;
}
	public static void printBoard(int switch1) {
		/* 
		 * This method is responsible solely for
		 * printing out the state of the game board. Takes
		 * in a switch value to decipher between computer and
		 * player boards.
		 * param: switch1 integer value determining whether the
		 * computer or player's board should be tested.
		 */
		
		if(switch1 == 0) {
			
			for(String [] row : comGrid) {
				
				String curRow = "";
				
				for(String element : row) {
					
					curRow += element + " ";
				
				}
				
				System.out.println(curRow);
			
			}
		
		} else {
			
			for(String [] row : grid) {
				
				String curRow = "";
				
				for(String element : row) {
					
					curRow += element + " ";
				}
				
				System.out.println(curRow);
			}
		}
		
		System.out.println(" ");
	
	}
}
