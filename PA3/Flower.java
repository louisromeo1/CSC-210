/* Author: Louis Romeo
 * CSC 210
 * Flower.java
 * Purpose: This program contains the flower object, which 
 * is inherited as an object in the Plant class.
 */


public class Flower extends Plant {
    
	private int growSize; // Private variable for grow count.
    
	// Constructor method for Flower object.
	public Flower(String seed) 
    {
        growSize = 0;
        growX = 2;
        growY = 2;
        command = seed.substring(0, 1).toLowerCase();
    }
	// Plant method places flower object in garden/plot.
    public void plant() {
    	
    	super.setPlot(growX, growY, command);
    
    }
	
	// Full grow sets flowers to "c" in the simulator to 
    // represent fully grown flowers.
    private void fullGrow() 
    {
        super.plot[0][0] = "c";
        super.plot[0][4] = "c";
        super.plot[4][0] = "c";
        super.plot[4][4] = "c";
    }
    
    /* Grow method simulates flower growth in the garden.
     * Takes no parameters.
     */
    
    public void grow() 
    {
    	growSize++; // Grow count iterates.
        if (growSize >= 4 && super.plot[2][2].equals(command)) 
        {
            fullGrow();
        } 
        else 
        {
            for (int x = 1; x < 4; x++) 
            {
                for (int y = 1; y < 4; y++) 
                {
                    if (super.plot[x][y].equals(command)) 
                    {
                        super.plot[x - 1][y] = "c";
                        super.plot[x + 1][y] = "c";
                        super.plot[x][y - 1] = "c";
                        super.plot[x][y + 1] = "c";
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

    /* Pick method is responsible for replacing picked flowers
     * with periods in the garden. Takes no parameters.
     */
    
    public void pick() 
    {
        for (int x = 0; x < 5; x++){
        	
            for (int y = 0; y < 5; y++){
            	
                if (super.plot[x][y].equals(command)) 
                {
                    super.plot[x][y] = ".";
                }
            }
        }
    }
     
}