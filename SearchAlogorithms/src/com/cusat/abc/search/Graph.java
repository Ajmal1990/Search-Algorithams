package com.cusat.abc.search;

import java.util.ArrayList;
import java.util.List;
/**
 * Base Graph class
 * @author Ajmal
 *
 */
public class Graph {
	
	private List<Node> nodes = new ArrayList<Node>();
	private int[][] adjesancyMatrix;
	private int size;
	private Node currentNode;
	private List<Node> currentPath = new ArrayList<Node>();
	
	public Graph() {
		
	}
	/**
	 * 
	 * @param nodes : list of nodes in graph
	 * 
	 */
	public Graph(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public int[][] getAdjesancyMatrix() {
		return adjesancyMatrix;
	}

	public void setAdjesancyMatrix(int[][] adjesancyMatrix) {
		this.adjesancyMatrix = adjesancyMatrix;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public List<Node> getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(List<Node> currentPath) {
		this.currentPath = currentPath;
	}
	
	public void addNodeToCurrentPath(Node node){
		currentPath.add(node);
	}
	
	/**
	 * Create a path between 2 nodes
	 * @param start 
	 * @param end
	 */
	public void connectNode(Node start,Node end) {
		if(adjesancyMatrix==null) {
			size=nodes.size();
			adjesancyMatrix=new int[size][size];
		}

		int startIndex=nodes.indexOf(start);
		int endIndex=nodes.indexOf(end);
		adjesancyMatrix[startIndex][endIndex]=1;
		adjesancyMatrix[endIndex][startIndex]=1;
	}
	
	public void clearVisitedNodes(){
		int i=0;
		while(i<size){
			nodes.get(i).setVisited(false);
			i++;
		}
	}
	
	/**
	 * 
	 * @param currentNode 
	 * @return Unvisited neighbour node with minimum heuristic value
	 * 
	 */
	public Node getMinUnvisitedNeighbour(Node currentNode){
		Node minNode = null;
		double minHuristcValue = Double.MAX_VALUE;
		int currentNodeIndex = nodes.indexOf(currentNode);
		
		for (int j = 0; j < nodes.size() ; j ++ ) {
			if( adjesancyMatrix[currentNodeIndex][j] == 1 && !nodes.get(j).isVisited() && nodes.get(j).getHeuristic() <= minHuristcValue ){
				minNode = nodes.get(j);
				minHuristcValue = nodes.get(j).getHeuristic();
			}
		}
		if(minNode != null ) {
			nodes.get(nodes.indexOf(minNode)).setVisited(true);
			setCurrentNode(minNode);
			addNodeToCurrentPath(minNode);
		}
		
		return minNode;
	}
	
	public Node getNodeByLabelName(String label){
		Node node = null;
		for(int i =0; i <size; i++){
			if(nodes.get(i).getLabel().equals(label)){
				node = nodes.get(i);
				break;
			}
		}
		return node;
	}
	
}
