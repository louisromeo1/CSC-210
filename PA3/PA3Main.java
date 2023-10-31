/* Author: Louis Romeo
 * CSC 210
 * PA3Main.java
 * Purpose: This program simulates the garden with given
 * user commands to execute the simulator.
 */


import java.util.Scanner;
import java.io.*;

public class PA3Main {
	
	// The main method takes in a file containing a number of rows, columns,
	// and commands that tell the program what to run.
	public static void main(String args[]) {
        
        Scanner sc = null;
        
        try {sc = new Scanner(new File(args[0]));} 
        
        catch (FileNotFoundException e) { 
        	e.printStackTrace();
        }
        
        int rows = Integer.parseInt(sc.nextLine().split(": ")[1]);
        int cols = Integer.parseInt(sc.nextLine().split(": ")[1]);
        
        if (rows >= 17 || cols >= 17) 
        {
            System.out.println("Too many plot columns.");
        
        } else {
            
        	fileCheck(sc, rows, cols);
        }
        sc.close();
    }

	// This method checks the file commands to run the proper simulation.
    public static void fileCheck(Scanner sc, int rows, int cols) 
    {
    	sc.nextLine();
        Garden theGarden = new Garden(rows, cols);
        while (sc.hasNextLine()) 
        {
        	String line = sc.nextLine();
            switch (line.substring(0, 3).toLowerCase()) 
            {
            
            case "pri":
                System.out.println("> PRINT");
                System.out.println(theGarden);
                break;
            
           case "har":
                harvestCommand(line, theGarden, rows, cols);
                break;
            
            case "pla":
                plantCommand(line, theGarden);
                break;
            
            case "gro":
                growCommand(line, theGarden, rows, cols);
                break;
            
            
            case "pic":
                pickCommand(line, theGarden, rows, cols);
                break;
            
            case "cut":
                cutCommand(line, theGarden, rows, cols);
                break;
            }
        }
    }
    
    // Harvest main determines what type of harvesting is necessary and
    // calls the proper sub methods.
    public static void harvestCommand(String line, Garden theGarden, int rows, int cols) 
    {
        if (line.split(" ").length == 1) {
            System.out.println("> " + line.toUpperCase());
            harvestAll(theGarden, rows, cols); // Harvest all.
        } else {
            String coordinate = line.split(" ")[1];
            if (coordinate.substring(0, 1).equals("(")) {
                harvestCoord(coordinate, theGarden, line); // Harvest by coordinate.
            } else {
                String seed = coordinate;
                harvestSeed(seed, rows, cols, theGarden, line); // Harvest by seed.
            }
        }
    }
    // Harvest all Vegetables in garden.
    private static void harvestAll(Garden theGarden, int rows, int cols) 
    {
        for (int x = 0; x < rows; x++) 
        {
            for (int y = 0; y < cols; y++) 
            {
                Plant thePlant = theGarden.getPlant(x, y);
                if (thePlant.getVegetable() != null) 
                {
                    thePlant.harvest();
                }
            }
        }
    }

    private static void harvestCoord(String coordinate, Garden theGarden, String line) 
    {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " "  + line.split(" ")[1]);
        Plant thePlant = theGarden.getPlant(row, col);
        
        if (thePlant.getVegetable() == null) 
        {
            System.out.println("Can't harvest there.");
        } else {
            thePlant.harvest();
        }
    }

    // Harvest method determined by seed.
    private static void harvestSeed(String seed, int rows, int cols, Garden theGarden, String line) 
    {
        String commandLetter = seed.substring(0, 1);
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " " + line.split(" ")[1]);
        for (int x = 0; x < rows; x++) 
        {
            for (int y = 0; y < cols; y++) 
            {
                Plant thePlant = theGarden.getPlant(x, y);
                Vegetable tempVeg = thePlant.getVegetable();
                if (tempVeg != null && tempVeg.command.equals(commandLetter)) 
                {
                    tempVeg.harvest();
                }
            }
        }
    }
    
    // Main plant method plants tree at user coordinates.
    public static void plantCommand(String line, Garden theGarden)
    {
        String coordinate = line.split(" ")[1];
        String seed = line.split(" ")[2];
        
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        
        Plant thePlant = theGarden.getPlant(row, col);
        thePlant.plant(seed);
    }
    
 // Method that determines the necessary type of growing and calls proper sub methods. 
    public static void growCommand(String line, Garden theGarden, int rows, int cols) 
    {
        int time = Integer.parseInt(line.split(" ")[1]);
        if (line.split(" ").length == 2) 
        {
        	System.out.println("> " + line.toUpperCase());
            growAll(theGarden, rows, cols, time); // Grow all.
        
        } else {
            
        	String coordinate = line.split(" ")[2];
            if (coordinate.substring(0, 1).equals("(")) 
            { 
            	growTimeCoord(coordinate, theGarden, time, rows, cols,line); 
                // Growth by time and coordinates.

            } else if (coordinate.equals("flower") || coordinate.equals("tree") || coordinate.contentEquals("vegetable")) {
                
            	growTimeseedType(coordinate, rows, cols, theGarden, time, line);
                // Growth by time and seed type.
            
            } else {
                
            	growTimeSeed(coordinate, rows, cols, theGarden, time, line);
                // Growth by time and seed.
            }
        }

    }
 
    // Method responsible for determining growth based on time variable.
    public static void growTimeCoord(String coordinate, Garden theGarden, int time, int rows, int cols, String line) 
    {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        
        System.out.println("> " + line.toUpperCase());
       
        if (row < rows && row >= 0 && col < cols && col >= 0) 
        {
            Plant thePlant = theGarden.getPlant(row, col);
            for (int i = 0; i < time; i++) {thePlant.grow();}
        
        } else {
            
        	System.out.println("Can't grow there.");
        }

    }
 
    // Method responsible for determining growth and time based on seed.
    public static void growTimeSeed(String coordinate, int rows, int cols, Garden theGarden, int time, String line) {
        String seed = coordinate;
        String commandLetter = seed.substring(0, 1).toLowerCase();
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " "  + line.split(" ")[1] + " " + line.split(" ")[2]);
        
        for (int x = 0; x < rows; x++) 
        {
            for (int y = 0; y < cols; y++) 
            {
                Plant thePlant = theGarden.getPlant(x, y);
                String seedType = thePlant.getSeedType(seed);
                
                switch (seedType) 
                {
                
                case "Flowers":
                    Flower newFlower = thePlant.getFlower();
                    if (newFlower != null && newFlower.command.equals(commandLetter)) 
                    {
                        for (int i = 0; i < time; i++) {newFlower.grow();}
                    }
                
                case "Trees":
                    Tree newTree = thePlant.getTree();
                    if (newTree != null && newTree.command.equals(commandLetter)) 
                    {
                        for (int i = 0; i < time; i++) {newTree.grow();}
                    }
               
                case "Vegetables":
                    Vegetable newVeg = thePlant.getVegetable();
                    if (newVeg != null && newVeg.command.equals(commandLetter)) 
                    {
                        for (int i = 0; i < time; i++) {newVeg.grow();}
                    }
                }
            }
        }
    }
    
    
    // Method responsible for determining growth based on seed.
    public static void growTimeseedType(String coordinate, int rows, int cols, Garden theGarden, int time, String line) 
    {
        String seedType = coordinate;
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " " + line.split(" ")[1] + " " + line.split(" ")[2]);
        for (int x = 0; x < rows; x++) 
        {
            for (int y = 0; y < cols; y++) 
            {
                Plant thePlant = theGarden.getPlant(x, y);
                if (seedType.equals("flower")) 
                {
                    Flower newFlower = thePlant.getFlower();
                    if (newFlower != null) {
                        for (int i = 0; i < time; i++) {newFlower.grow();}
                    }
                
                } else if (seedType.equals("tree")) {
                    
                	Tree newTree = thePlant.getTree();
                    if (newTree != null) 
                    {
                        for (int i = 0; i < time; i++) {newTree.grow();}
                    }
               
                } else if (seedType.equals("vegetable")) {
                   
                	Vegetable newVeg = thePlant.getVegetable();
                    if (newVeg != null) 
                    {
                        for (int i = 0; i < time; i++) {newVeg.grow();}
                    }
                }
            }
        }
    }
    
    // Grow function for the command grow all, takes in the Garden object,
    // row, column and time integers.
    public static void growAll(Garden theGarden, int rows, int cols, int time) 
    {
        for (int x = 0; x < rows; x++) 
        {
            for (int y = 0; y < cols; y++) 
            {
                Plant thePlant = theGarden.getPlant(x, y);
                for (int i = 0; i < time; i++) 
                {
                    thePlant.grow(); // Calls grow function for new plant object.
                }
            }
        }
    }
    
    // This pick method determines which flower picking is needed and 
    // calls the appropriate function.
    public static void pickCommand(String line, Garden theGarden, int rows, int cols) 
    {
        if (line.split(" ").length == 1) 
        {
        	System.out.println("> " + line.toUpperCase());
            pickAll(theGarden, rows, cols); // Pick all.
        
        } else {
            
        	String coordinate = line.split(" ")[1];
            if (coordinate.substring(0, 1).equals("(")) 
            {
                pickCoord(coordinate, theGarden, line); // Pick by coordinate.
            
            } else {
                
                String seed = coordinate;
                pickSeed(seed, rows, cols, theGarden, line); // Pick by seed.
            }
        }
    }
    
    // Picks all flowers.
    private static void pickAll(Garden theGarden, int rows, int cols) 
    {
        for (int x = 0; x < rows; x++) 
        {
            for (int y = 0; y < cols; y++) 
            {
                Plant thePlant = theGarden.getPlant(x, y);
                if (thePlant.getFlower() != null) 
                {
                    thePlant.pick();
                }
            }
        }
    }
    
    // Picks flower at user specific coordinates.
    private static void pickCoord(String coordinate, Garden theGarden, String line) 
    {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " " + line.split(" ")[1]);
        Plant thePlant = theGarden.getPlant(row, col);
        if (thePlant.getFlower() == null) 
        {
            System.out.println("Can't pick there.");
        
        } else {
           
        	thePlant.pick();
        }
    }
    
    // Picks flowers based on user input seed type.
    private static void pickSeed(String seed, int rows, int cols, Garden theGarden, String line) 
    {
        String commandLetter = seed.substring(0, 1);
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " " + line.split(" ")[1]);
        for (int x = 0; x < rows; x++) 
        {
            for (int y = 0; y < cols; y++) 
            {
                Plant thePlant = theGarden.getPlant(x, y);
                Flower newFlower = thePlant.getFlower();
                if (newFlower != null && newFlower.command.equals(commandLetter)) 
                {
                    newFlower.pick();
                }
            }
        }
    }
 
    // Main cut method determines what type of Tree cutting is necessary,
    // and calls the proper method.
    public static void cutCommand(String line, Garden theGarden, int rows, int cols) 
    {
        if (line.split(" ").length == 1) 
        {
        	System.out.println("> " + line.toUpperCase());
            cutAll(theGarden, rows, cols); // Cut all.
        
        } else {
            
        	String coordinate = line.split(" ")[1];
            if (coordinate.substring(0, 1).equals("(")) 
            {
                cutCoord(coordinate, theGarden, line); // Cut at coordinate.
            
            } else {
                
                String seed = coordinate;
                cutSeed(seed, rows, cols, theGarden, line); // Cut specific seed.
            }
        }
    } 

   // Method in charge of cutting all trees in the garden.
    private static void cutAll(Garden theGarden, int rows, int cols) 
    {
        for (int x = 0; x < rows; x++) 
        {
            for (int y = 0; y < cols; y++) 
            {
                Plant thePlant = theGarden.getPlant(x, y);
                if (thePlant.getTree() != null) 
                {
                    thePlant.cut(); // Cuts new plant object in garden.
                }
            }
        }
    }

    // This method is responsible for cutting all trees at the user's coordinate.
    private static void cutCoord(String coordinate, Garden theGarden, String line) 
    {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " " + line.split(" ")[1]);
        Plant thePlant = theGarden.getPlant(row, col);
        
        if (thePlant.getTree() == null) 
        {
            System.out.println("Can't cut there.");
        
        } else {
            
        	thePlant.cut();
        }
    }

    // Cuts all trees of the user inputed enumeration.
    private static void cutSeed(String seed, int rows, int cols, Garden theGarden, String line) 
    {
        String start = seed.substring(0, 1);
        System.out.println("> " + line.split(" ")[0].toUpperCase() + " " + line.split(" ")[1]);
        for (int x = 0; x < rows; x++) 
        {
            for (int y = 0; y < cols; y++) 
            {
                Plant thePlant = theGarden.getPlant(x, y);
                Tree tempTree = thePlant.getTree();
                if (tempTree != null && tempTree.command.equals(start)) 
                {
                    tempTree.cut();
                }
            }
        }
    }
    
    // Integer method to get number of columns.
    public static int getCol(String coor) 
    {
        coor = coor.split(",")[1];
        if (coor.length() == 2) 
        {
        	int colNum = Integer.parseInt(coor.substring(0, 1));
            return colNum;
        
        } else {
        	
        	int colNum = Integer.parseInt(coor.substring(0, 2));
            return colNum;
        }
    }
    
    // Integer method to get number of rows.
    public static int getRow(String coor) 
    {
        coor = coor.split(",")[0];
        if (coor.length() == 2) 
        {
        	int rowNum = Integer.parseInt(coor.substring(1, 2));
            return rowNum;    
        
        } else {  
        	
        	int rowNum = Integer.parseInt(coor.substring(1, 3));
        	return rowNum;
        }
    }
}
