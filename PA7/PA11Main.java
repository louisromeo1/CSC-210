import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PA11Main {
	public static void main(String[] args) {
		DGraph obj = null;
		try {
			obj = readFile(args[0]);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File not found");
		}
		handleCommand(args[1], obj);
	}
	

	public static void handleCommand(String command, DGraph obj) {
		
		if (command.equals("HEURISTIC")) {
			Trip heuristicTrip = heuristic(obj);
			System.out.println(heuristicTrip.toString(obj));
		}
		if (command.equals("BACKTRACK")) {
			Trip backtrackingTrip = new Trip(obj.getNumNodes());
			backtrackingTrip.chooseNextCity(1);
			backtrackingTrip = backtracking(obj, backtrackingTrip,new Trip(obj.getNumNodes()));
			System.out.println(backtrackingTrip.toString(obj));
		}

		if (command.equals("MINE")) {
			Trip mineTrip = new Trip(obj.getNumNodes());
			mineTrip.chooseNextCity(1);
			mineTrip = mine(obj, mineTrip, new Trip(obj.getNumNodes()));
			System.out.println(mineTrip.toString(obj));
		}
		if (command.equals("TIME")) {
			timeCommand(obj);
		}
	}

	public static void timeCommand(DGraph obj) {
		long startTime = System.nanoTime();
		Trip heuristicTrip = heuristic(obj);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("heuristic: cost = " + heuristicTrip.tripCost(obj)
		+ ", " + duration + " milliseconds");

		Trip backtrackingTrip = new Trip(obj.getNumNodes());
		backtrackingTrip.chooseNextCity(1);
		backtrackingTrip = backtracking(obj, backtrackingTrip,
				new Trip(obj.getNumNodes()));
		startTime = System.nanoTime();
		Trip backtrackingTrip1 = new Trip(obj.getNumNodes());
		backtrackingTrip1.chooseNextCity(1);
		backtrackingTrip1 = backtracking(obj, backtrackingTrip1,
				new Trip(obj.getNumNodes()));
		endTime = System.nanoTime();
		double backtrackDuration = (endTime - startTime) / 1000000;

		Trip mineTrip = new Trip(obj.getNumNodes());
		mineTrip.chooseNextCity(1);
		mineTrip = mine(obj, mineTrip, new Trip(obj.getNumNodes()));
		startTime = System.nanoTime();
		Trip mineTrip1 = new Trip(obj.getNumNodes());
		mineTrip1.chooseNextCity(1);
		mineTrip1 = mine(obj, mineTrip1, new Trip(obj.getNumNodes()));
		endTime = System.nanoTime();
		double mineDuration = (endTime - startTime) / 1000000;
		System.out.println("mine: cost = " + mineTrip1.tripCost(obj) + "," + mineDuration + " milliseconds");
		System.out.println("backtracking: cost = " + backtrackingTrip.tripCost(obj) + ", " + backtrackDuration + " milliseconds");
	}

	public static DGraph readFile(String loc) throws IOException {
		File fl = new File(loc);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fl));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		boolean comment = true;
		String line = br.readLine();
		while (comment) {
			line = br.readLine();
		comment = line.startsWith("%");
		}
		String[] str = line.split("( )+");
		int nRows = (Integer.valueOf(str[0].trim())).intValue();
		DGraph obj = new DGraph(nRows);
		while (true) {
			line = br.readLine();
			if (line == null)
				break;
			str = line.split("( )+");
			obj.addEdge((Integer.valueOf(str[0].trim())).intValue(),
					(Integer.valueOf(str[1].trim())).intValue(),
					(Double.valueOf(str[2].trim())).doubleValue());
		}
		return obj;
	}

	public static Trip heuristic(DGraph obj) {
		int numCities = obj.getNumNodes();
		Trip trip = new Trip(numCities);
		trip.chooseNextCity(1);
		int currentCity = 1;
		Double minDistance = Double.MAX_VALUE;
		int chooseCity = 2;
		for (int k = 2; k <= numCities; k++) {
			DGraph graph = obj;
			for (int city : graph.getNeighbors(currentCity)) {
				if (trip.isCityAvailable(city) && graph.getWeight(currentCity, city) < minDistance) {
					minDistance = graph.getWeight(currentCity, city);
					chooseCity = city;
				}
			}
			trip.chooseNextCity(chooseCity);
			currentCity = chooseCity;
			minDistance = Double.MAX_VALUE;
		}
		return trip;
	}

	
	public static Trip backtracking(DGraph graph, Trip currentTrip, Trip minTrip) {
			if (currentTrip.tripCost(graph) < minTrip.tripCost(graph)) {
				minTrip.copyOtherIntoSelf(currentTrip);
				return null;
	}
			if (currentTrip.tripCost(graph) < minTrip.tripCost(graph)) {
				for (int k : currentTrip.citiesLeft()) {
					currentTrip.chooseNextCity(k);
					backtracking(graph, currentTrip, minTrip);
					currentTrip.unchooseLastCity();
				}
			}
		return minTrip;
	}


	public static Trip mine(DGraph graph, Trip currentTrip, Trip minTrip) {
		double minTripCost = minTrip.tripCost(graph);
		if (currentTrip.citiesLeft().isEmpty()) {
			if (currentTrip.tripCost(graph) < minTrip.tripCost(graph))
				minTrip.copyOtherIntoSelf(currentTrip);
			return null;
		}
		if (currentTrip.tripCost(graph) < minTrip.tripCost(graph))
			minTripCost = minTrip.tripCost(graph);
		for (int k : currentTrip.citiesLeft()) {
			currentTrip.chooseNextCity(k);
			if (currentTrip.tripCost(graph) > minTripCost) {
				currentTrip.unchooseLastCity();
				continue;
			}
			backtracking(graph, currentTrip, minTrip);
			currentTrip.unchooseLastCity();
		}
		return minTrip;
	}
}
