/* Name: Louis Romeo
 * Course: CSC 210
 * Purpose: This program is designed to simulate a 
 * housing plot distribution map, which shows what houses
 * are available based on the user's price input.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import javax.swing.*;

public class CheapHouses {
	
	/* Instance Variables:
	 * 
	 * String userFileInput: Saves user inputed file as string.
	 * int inputPrice: Saves user inputed price value as an integer.
	 * Map houseMap: Map that is used to place coordinates and plot points.
	 * ArrayList<Double> coords: Array containing the coordinates of the homes.
	 * Double minX, maxX, minY, maxY: Doubles used to track map size.
	 */
	
	static String userFileInput;
	static int inputPrice;
	
	static Map<String, ArrayList<Double>> houseMap;
	static ArrayList<Double> cords = new ArrayList<>();
	
	static Double minX = Double.POSITIVE_INFINITY;
	static Double maxX = Double.NEGATIVE_INFINITY;
	static Double minY = Double.POSITIVE_INFINITY;
	static Double maxY = Double.NEGATIVE_INFINITY;
	
	public static void main(String [] args) {
		
		createAndShowGUI();
	}
	
	/* This method utilizes exception handling to access the user's
	 * inputed file. Returns a Scanner object containing the text
	 * contained in the file. Returns null if file is not accessible.
	 */
	
	public static Scanner accessFile() {
		
		try {

			Scanner textFile = new Scanner(new File(userFileInput));
			houseMap = getHouseMap(textFile);
			return textFile;
		}
		
		catch ( java.io.FileNotFoundException e) {
			
			System.out.println("Error: File not found");
			return null;
		}
	}
	
	/* The getHouseMap method takes in the textFile as a parameter, and
	 * stores the longitude, latitude, and price of each individual home,
	 * and maps that data to the houses' address. Returns the house map
	 * after adding coordinates.
	 * param textFile: The user inputed house data file. 
	 */
	
	public static Map <String, ArrayList<Double>> getHouseMap(Scanner textFile) {
		
		Map <String, ArrayList<Double>> houseMap = new HashMap <>();
		String curLine = textFile.nextLine();
		
		while (textFile.hasNext()){
			
			curLine = textFile.nextLine();
			
			String[] tempArray = curLine.split(",");
			ArrayList<Double> tempCords = new ArrayList<>();
			
			tempCords.add(Double.parseDouble(tempArray[9]));
			tempCords.add(Double.parseDouble(tempArray[10]));
			tempCords.add(Double.parseDouble(tempArray[11]));
			houseMap.put(tempArray[0],tempCords);
		}
		textFile.close();
		return houseMap;
	}
	
	/* This method contains all of the graphics and GUI for the home
	 * distribution map. Utilizing the javax.swing graphics class,
	 * this method creates a main frame window, and all of the click-able
	 * buttons and text fields in the window. Does not have a return value,
	 * is the only method accessed in main.
	 */
	
	public static void createAndShowGUI() {
		
		JFrame mainFrame = new JFrame("Home Price Distribution");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(400,500);
		
		JPanel mainPanel = new JPanel(null);
		
		JPanel graphicsPanel  = new GPanel();
		graphicsPanel.setLocation(0, 0);
		graphicsPanel.setSize(400,400);
		graphicsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(graphicsPanel);
		
		JPanel widgetsPanel  = new JPanel();
		widgetsPanel.setLocation(0, 400);
		widgetsPanel.setSize(400,100);
		
		widgetsPanel.add(new JLabel("File: "));
		
		JTextField fileName = new JTextField("houses.csv", 6);
		widgetsPanel.add(fileName);
		
		widgetsPanel.add(new JLabel("Price: "));
		
		JTextField housePrice = new JTextField(6);
		widgetsPanel.add(housePrice);
		
		JButton plotButton = new JButton("Plot");
		
		plotButton.addActionListener(new ActionListener() { // For plot button.
			
			/* Helper method responsible for responding to the user clicking
			 * the plot button. Handles errors and exceptions for user
			 * input, and prints a console line for each scenario.
			 * param e: Object variable representing ActionEvent.
			 */
			
			public void actionPerformed(ActionEvent e) {
				
				userFileInput = fileName.getText();
				accessFile();
				
				try {
					
					inputPrice = Integer.parseInt(housePrice.getText());
					System.out.println("Plot Button Clicked!");
				}
				catch ( NumberFormatException e2 ) {
					
					System.out.println("Enter a valid price!");
				}
				
				cords = getHouseCords(houseMap);
				mainFrame.getContentPane().repaint();
			}
		});
			
		widgetsPanel.add(plotButton);
		mainPanel.add(widgetsPanel);

		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);  
	}
	
	/* GPanel is an extension of the java given JPanel class,
	 * and contains the paintComponent method which is responsible
	 * for placing the dots representing the homes on the grid.
	 */
	
	private static class GPanel extends JPanel {	
		
		/* paintComponent method is a graphics helper method, which
		 * takes in a Graphics object as a parameter. It adds dots onto
		 * the GUI housing map to represent house price values. It calculates
		 * position by subtracting the X and Y coordinate values.
		 * param g: Graphics object used to paint home dots. 
		 */
		
	    public void paintComponent(Graphics g) {  
	    	
            int width = getSize().width;
            int height = getSize().height;
			
            g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);
			
			if (cords != null) {
				
				for (int i = 0; i < cords.size(); i += 2) {
					
					double tempX = maxX - minX;
			    	int X = (int) ((cords.get(i) - minY) / (tempX) * getSize().width);
			    	double tempY = maxY - minY;
			    	int Y = (int) ((cords.get(i + 1) - minX) / (tempY) * getSize().height);
			    	
			    	g.setColor(Color.black);
			    	g.fillOval (Y, X, 5, 5);
				}
			}
			
		}
	}
	
	/* This method utilizes the houseMap as a parameter, and creates
	 * a separate ArrayList containing the minimum and maximum longitude
	 * and latitude values of the houses to correctly size the Map.
	 * Returns the ArrayList containing the current home list, and
	 * null if the map is empty.
	 * param houseMap: The address to price, longitude and latitude ArrayList.
	 */
	
	public static ArrayList<Double> getHouseCords(Map <String, ArrayList<Double>> houseMap) {
		
		ArrayList<Double> cords = new ArrayList<>();
		ArrayList<Double> curHouseList;
		
		if (houseMap != null) {
			
			for ( String address : houseMap.keySet() ) {
				curHouseList = houseMap.get(address);
				int curHousePrice = curHouseList.get(0).intValue();
				
				if (maxX < curHouseList.get(2)) {
					maxX = curHouseList.get(2);
				}
				if (minX > curHouseList.get(2)) {
					minX = curHouseList.get(2);
				}
				if (maxY < curHouseList.get(1)) {
					maxY = curHouseList.get(1);
				}
				if (minY > curHouseList.get(1)) {
					minY = curHouseList.get(1);
				}
				if (curHousePrice <= inputPrice) {
							
					cords.add(curHouseList.get(1));
					cords.add(curHouseList.get(2));
				}
			}
			return cords;
		}
		return null;
	}
}
