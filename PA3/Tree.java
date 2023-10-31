/* Author: Louis Romeo
 * CSC 210
 * Tree.java
 * Purpose: This program contains the tree object, which 
 * is inherited as an object in the Plant class.
 */

public class Tree extends Plant 
{
	private int growSize; // Private variable for grow count.
	
	// Constructor method for Tree object.
    public Tree(String seed) 
    {
        growSize = 0;
        growX = 4;
        growY = 2;
        command = seed.substring(0, 1).toLowerCase();
    }
    
    // Plant method adds Tree object to garden/grid.
    public void plant() {super.setPlot(growX, growY, command);}
    
    // Cut method responsible for removing trees and replacing them with periods.
    public void cut() 
    {
        for (int x = 0; x < 5; x++) 
        {
            for (int y = 0; y < 5; y++) 
            {
                if (super.plot[x][y].equals(command)) 
                {
                    super.plot[x][y] = "."; // Place period at cut Tree.
                }
            }
        }
    }
    
    // This method defines the first Tree grow on the garden.
    private void firstGrow() {super.plot[3][2] = command;}
    
    // Grow method is in charge of growing Tree object.
    public void grow() 
    {
        growSize++; // Grow count iterates.
        if (growSize == 1) 
        {
            firstGrow(); 
        } else {
            for (int x = 3; x > 0; x--) 
            {
                for (int y = 3; y > 0; y--) 
                {
                    if (super.plot[x][y].equals(command) && super.plot[x - 1][y].equals(".")) 
                    {
                        super.plot[x - 1][y] = "&";
                    }
                }
            }
        }
        for (int x = 0; x < 5; x++) 
        {
            for (int y = 0; y < 5; y++) 
            {
                if (super.plot[x][y].equals("&")) 
                {
                    super.plot[x][y] = command;
                }
            }
        }
    }

}
