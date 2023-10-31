/* Author: Louis Romeo
 * CSC 210
 * Garden.java
 * Purpose: This program contains the garden object which
 * the planted objects will be placed on.
 */

public class Garden {
	
	private Plant[][] theGarden;

    // Constructor method for garden object.
    public Garden(int rows, int cols) 
    {
    	theGarden = new Plant[rows][cols];
    	for (int x = 0; x < rows; x++) 
    	{ 
    		for (int y = 0; y < cols; y++) {
                
    			theGarden[x][y] = new Plant();
            }
        }
    }
    
    // Getter method for Plant object.
    public Plant getPlant(int rows, int cols) {
    	
    	return theGarden[rows][cols];
    }
    
    // Setter method for Plant object, creates new.
    public void resetPlant(int rows, int cols) {
    	
    	theGarden[rows][cols] = new Plant();
    }
    
    // Method used to combine strings.
    public String combineStr(String one, String two) {
        String combineStr = "";
        for (int i = 0; i < 5; i++) {
            if (!one.substring(i, i + 1).equals(".")) {
                combineStr += one.substring(i, i + 1);
            } else if (!two.substring(i, i + 1).equals(".")) {
                combineStr += two.substring(i, i + 1);
            } else {
                combineStr += ".";
            }
        }
        return combineStr;
    }

    // Override of the toString() method.
    public String toString() 
    {
        String newStr = new String();
        for (Plant[] plantRow : theGarden) 
        {
            for (int i = 0; i < 5; i++) 
            {
                for (Plant plant : plantRow) 
                {
                    String combineStr = ".....";
                    if (plant.getFlower() != null) 
                    {
                    	String rowEle = plant.getFlower().getRowElement(i);
                        combineStr = combineStr(combineStr,rowEle);
                    }
                    if (plant.getTree() != null) 
                    {
                    	String rowEle = plant.getTree().getRowElement(i);
                        combineStr = combineStr(combineStr, rowEle);
                    }
                    if (plant.getVegetable() != null) 
                    {
                    	String rowEle = plant.getVegetable().getRowElement(i);
                        combineStr = combineStr(combineStr, rowEle);
                    }
                    newStr += combineStr;
                }
                newStr += "\n";
            }
        }
        return newStr;
    }

}
