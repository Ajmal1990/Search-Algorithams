package com.cusat.abc.search;
/**
 * Base node class
 * 
 * @author Ajmal
 *
 */
public class Node {
	
	private String label;
	private boolean isVisited = false;
	private double heuristic;
	private String path;
	/**
	 * @param label : The node name
	 * 
	 */
	public Node(String label) {
		this.label = label;
	}
	
	/**
	 * 
	 * @param label : The node name
	 * @param heuristic : heuristic value of the node 
	 */
	public Node(String label,double heuristic) {
		this.label = label;
		this.heuristic = heuristic;
		this.path="";
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public double getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
