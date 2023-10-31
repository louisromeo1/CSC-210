/* Author: Louis Romeo
 * CSC 210
 * Vegetable.java
 * Purpose: This program contains the vegetable object, which 
 * is inherited as an object in the Plant class.
 */

public class Vegetable extends Plant 
{
	private int growSize; // Private variable for grow count.
	
	// Constructor method for Vegetable object.
    public Vegetable(String seed)
    {
        growSize = 0;
        growY = 2;
        growX = 0;
        command = seed.substring(0, 1).toLowerCase();
    }
    
    // Plant method puts Vegetable object on grid/garden.
    public void plant() {super.setPlot(growX, growY, command);}
    
    // Harvest method places dots on harvested Vegetables.
    public void harvest() 
    {
        for (int x = 0; x < 5; x++) 
        {
            for (int y = 0; y < 5; y++) 
            {
                if (super.plot[x][y].equals(command)) 
                {
                    super.plot[x][y] = ".";
                }
            }
        }
    }
    
    // Private method responsible for the first Vegetable grow in the garden.
    private void firstGrow() {super.plot[1][2] = command;}
    
    // Grow method is in charge of growing Vegetable object.
    public void grow() 
    {
        growSize++; // Grow stack iterates.
        
        if (growSize == 1)
        {
            firstGrow(); // If the grow stack is only one it is first grow.
        } else {
            for (int x = 1; x < 4; x++) 
            {
                for (int y = 1; y < 4; y++) 
                {
                    if (super.plot[x][y].equals(command) && super.plot[x + 1][y].equals(".")) 
                    {
                        super.plot[x + 1][y] = "c";
                    }
                }
            }
        }
        for (int x = 0; x < 5; x++) 
        {
            for (int y = 0; y < 5; y++) 
            {
                if (super.plot[x][y].equals("c")) 
                {
                    super.plot[x][y] = command;
                }
            }
        }
    }
}
