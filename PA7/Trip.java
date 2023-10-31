import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Trip {
		
		public List<Integer> visitOrder;
		public TreeSet<Integer> citiesLeft;
		
		public Trip(int numCities) {
			visitOrder = new ArrayList<>();
			citiesLeft = new TreeSet<>();
			for (int i = 1; i <= numCities; i++) {
				citiesLeft.add(i);
			}
		}

		public void copyOtherIntoSelf(Trip tripSofar) {

			citiesLeft = new TreeSet<>(tripSofar.citiesLeft);
			visitOrder = new ArrayList<>(tripSofar.visitOrder);
		}

		public boolean isCityAvailable(int city) {return citiesLeft.contains(city);}

		
		public void chooseNextCity(int next) {
			assert isCityAvailable(next);
			visitOrder.add(next);
			citiesLeft.remove(next);
		}

		
		public void unchooseLastCity() {
			assert visitOrder.size() > 0;
			int city = visitOrder.get(visitOrder.size() - 1);
			visitOrder.remove(visitOrder.size() - 1);
			citiesLeft.add(city);
		}

		public boolean isPossible(DGraph graph) {
			double cost = tripCost(graph);
			if (visitOrder.size() == graph.getNumNodes()
					&& cost != Double.MAX_VALUE) {
				return true;
			} else {
				return false;
			}
		}


		public double tripCost(DGraph graph) {
			double cost = 0;
			if (visitOrder.size() == 0) {
				return Double.MAX_VALUE;
			} else if (visitOrder.size() == 1) {
				return 0;
			}
			int prevCity = visitOrder.get(0);

			for (int i = 1; i < visitOrder.size(); i++) {
				double distance = graph.getWeight(prevCity, visitOrder.get(i));
				prevCity = visitOrder.get(i);
				if (distance < 0) {
					cost = Double.MAX_VALUE;
					break;
				} else {
					cost += distance;
				}
			}
			if (citiesLeft.isEmpty()) {
				double goingHome = graph.getWeight(
						visitOrder.get(visitOrder.size() - 1),
						visitOrder.get(0));
				if (goingHome < 0 || cost == Double.MAX_VALUE) {
					cost = Double.MAX_VALUE;
				} else {
					cost = cost + goingHome;
				}
			}
			return cost;
		}
		
		public List<Integer> citiesLeft() {
			List<Integer> retval = new ArrayList<>();
			for (Integer city : citiesLeft) {
				retval.add(city);
			}
			return retval;
		}

		public String toString(DGraph graph) {
			String str = "";
			str += "cost = " + String.format("%.1f", tripCost(graph));
			str += ", visitOrder = " + visitOrder;
			return str;
		}

		public String toString() {
			String str = "";
			str += "visitOrder = " + visitOrder;
			str += ", citiesLeft = " + citiesLeft;
			return str;
		}

	}
