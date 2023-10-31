/*
 * Author: Louis Romeo
 * File: DGraph.java
 * Purpose:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DGraph {

	private Set<Integer> vertices;
	private Set<Edge> edges;
	private Map<ArrayList<Integer>, Double> edgeMap;
	private Map<Integer, ArrayList<Integer>> neighborMap;

	public DGraph(int numNodes) {
		vertices = new HashSet<Integer>();
		edges = new HashSet<Edge>();
		edgeMap = new HashMap<ArrayList<Integer>, Double>();
		neighborMap = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 1; i <= numNodes; i++)
			vertices.add(i);
	}


	public void addEdge(int v, int w, double distance) {
		Edge newEdge = new Edge(v, w, distance);
		edges.add(newEdge);
		ArrayList<Integer> vals = new ArrayList<>();
		vals.add(v);
		vals.add(w);
		edgeMap.put(vals, distance);
		if (!neighborMap.containsKey(v)) {
			ArrayList<Integer> lis = new ArrayList<Integer>();
			lis.add(w);
			neighborMap.put(v, lis);
		} else {
			neighborMap.get(v).add(w);
		}
	}

	
	public int getNumNodes() {return vertices.size();}


	public double getWeight(int v, int w) {
		ArrayList<Integer> vals = new ArrayList<>();
		vals.add(v);
		vals.add(w);
		return edgeMap.get(vals);
	}


	public List<Integer> getNeighbors(int v) {
		ArrayList<Integer> neighbors = neighborMap.get(v);
		Collections.sort(neighbors);
		return neighbors;
	}


	public String toDotString() {
		String dot_str = "digraph {\n";
		
		for (Edge e : edges) {
			dot_str += e.toDotString() + "\n";
		}
		return dot_str + "}\n";
	}


	public class Edge implements Comparable<Edge> {

		private final int node1;	
		private final int node2;
		private final double weight;

		public Edge(int node1, int node2, double weight) {
			assert weight >= 0.0;
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}

		public String toDotString() {
			return "" + node1 + " -> " + node2 + " [label=\"" + weight + "\"];";
		}


		public int compareTo(Edge other) {
			if (this.equals(other)) {
				return 0;
			} else if ((node1 < other.node1) || (node1 == other.node1 && node2 < other.node2)) {
				return -1; 
			} else {
				return 1; 
			}
		}

		public boolean equals(Object o) {
			if (!(o instanceof Edge)) {
				return false;
			}
			Edge other = (Edge) o;
			return (node1 == other.node1) && (node2 == other.node2);
		}


		public int hashCode() {return getNumNodes() * node1 + node2;}
	}
}