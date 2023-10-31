/* Author: Louis Romeo
 * CSC 210
 * Plant.java
 * Purpose: This program contains the plant object, which 
 * inherits Flower, Tree, and Vegetable objects in the Plant class.
 */

public class Plant {
	
	protected String command;
	protected String[][] plot;
	
	protected int growX;
	protected int growY;
	
	// Plant class inherits Tree, Flower and Vegetable objects.
	private Flower flower;
	private Tree tree;
	private Vegetable veg;
	
	protected String seed;

	 // Enumerations are public for accessibility.
	 public enum Flowers {Iris, Lily, Rose, Daisy, Tulip, Sunflower}
	 
	 public enum Trees {Oak, Willow, Banana, Coconut, Pine}
	 
	 public enum Vegetables {Garlic, Zucchini, Tomato, Yam, Lettuce}

	 // Constructor for the Plant object.
	 public Plant() 
	 {  
		 plot = new String[5][5]; // Creates 5 by 5 plot.
		 for (int x = 0; x < 5; x++) 
		 {
			 for (int y = 0; y < 5; y++) 
			 {
				 setPlot(x, y, "."); // Adds items to set grid.
	            }
	        }
	    }
	    
	 // Getter method for Flower object in Plant class.  
	 public Flower getFlower() {return flower;}
	 
	 // Getter method for Tree object in Plant class.
	 public Tree getTree() {return tree;}
	 
	 // Getter method for Vegetable object in Plant class.
	 public Vegetable getVegetable() {return veg;}

	 // String method that gets the seed type and returns it as a string.   
	 public String getSeedType(String seed) {
	        
		 seed = seed.toLowerCase();
		 for (Vegetables veg : Vegetables.values()) 
		 {
			 if (seed.equalsIgnoreCase(veg.name())) {return "Vegetables";}
		 }
		 for (Trees tree : Trees.values()) // Trees.
		 {
			 if (seed.equalsIgnoreCase(tree.name())) {return "Trees";}
	        }
		 for (Flowers flower : Flowers.values()) // Flowers
		 {   
			 if (seed.equalsIgnoreCase(flower.name())) {return "Flowers";}
	        }  
		 return null;
	    }

	 // Getter method for plot.  
	 public String[][] getPlot() {return plot;}
	 
	// Cut method called for Tree objects.
	 public void cut() {tree.cut();}

	 // Pick method called for flower objects.
	public void pick() {flower.pick();}

	// Harvest method called for Vegetable objects. 
	public void harvest() {veg.harvest();}

	// Setter method for the plots of the garden.  
	public void setPlot(int x, int y, String firstLetter) {this.plot[x][y] = firstLetter;}

	 // Method for planting certain objects based on file commands.  
	 public void plant(String seed) 
	 {   
		 String seedType = getSeedType(seed);
		 switch (seedType) 
		 {  
		 case "Flowers":
			 flower = new Flower(seed);
			 flower.plant();
			 break;
	        
		 case "Trees":
			 tree = new Tree(seed);
			 tree.plant();
			 break;
	        
		 case "Vegetables":  
			 veg = new Vegetable(seed);
			 veg.plant();
			 break;
	        }
	    }
	 
	 // Grow method for planted objects in Plant class. Calls appropriate
	 // grow methods for each type.
	 public void grow() 
	 {   
		 if (flower != null) {flower.grow();}
		 if (tree != null) {tree.grow();}
		 if (veg != null) {veg.grow();}
	    }

	 // Grow method utilizing seed argument.  
	 public void grow(String seed) 
	 {
		 String seedType = getSeedType(seed);
		 
		 switch (seedType)
		 {
		 case "Flowers":
			 flower.grow();
			 break;
	        
		 case "Tree":
			 tree.grow();
			 break;
	       
		 case "Vegetables":
			 veg.grow();
			 break;
	        }
	    }

	 // String method that converts a command string to the grid.   
	 public String toString() 
	 {
		 String returnStr = new String();
		 for (int x = 0; x < 5; x++) 
		 {
			 for (int y = 0; y < 5; y++) 
			 {
				 returnStr += plot[x][y];
	            }
	            
			 returnStr += "\n";
	        }
		 return returnStr;
	    }

	 // String method that gets the row elements in the garden.
	 public String getRowElement(int row) 
	 {
		 String rowStr = new String();
		 for (String element : plot[row]) {rowStr += element;}
		 return rowStr;
	 }
}
