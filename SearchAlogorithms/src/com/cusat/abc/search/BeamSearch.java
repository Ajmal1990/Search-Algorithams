package com.cusat.abc.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BeamSearch base class
 * 
 * @author Ajmal
 * 
 */
public class BeamSearch {
	/**
	 * 
	 * @param garph
	 * @param start
	 * @param end
	 */
	public void beamserach(Graph graph, Node start, Node end, int beamSize) {

		System.out
				.println("----------------- Started Beam Search ----------------");
		start.setPath(start.getLabel());
		Queue openNodeQueue = new LinkedList();
		openNodeQueue.add(start);
		int currentIndex = graph.getNodes().indexOf(start);
		graph.getNodes().get(currentIndex).setVisited(true);
		

		while (true) {
			List<Node> expandedNodes = new ArrayList<Node>();

			while (!openNodeQueue.isEmpty()) {
				Node currentExapndedNode = graph
						.getMinUnvisitedNeighbour((Node) openNodeQueue.peek());
				if (end.equals(currentExapndedNode)) {
					System.out.println("Expanding... "
							+ ((Node) openNodeQueue.peek()).getLabel() + "===>"
							+ currentExapndedNode.getLabel());
					currentExapndedNode.setPath(((Node) openNodeQueue.peek()).getPath()+" ==> "+currentExapndedNode.getLabel());
					System.out.println("Reached destination......... :) ");
					System.out.println("Path === " + currentExapndedNode.getPath());
					
					return;
				} else if (currentExapndedNode != null) {
					System.out.println("Expanding... "
							+ ((Node) openNodeQueue.peek()).getLabel() + "===>"
							+ currentExapndedNode.getLabel());
					currentExapndedNode.setPath(((Node) openNodeQueue.peek()).getPath()+" ==> "+currentExapndedNode.getLabel());
					System.out.println("Path === " + currentExapndedNode.getPath());
					expandedNodes.add(currentExapndedNode);
				} else {
					openNodeQueue.remove();
				}
			}
			//Sorting the expanded nodes based on the heuristic value
			if (expandedNodes.size() > 1) {
				Collections.sort(expandedNodes, new Comparator<Node>() {
					@Override
					public int compare(Node node1, Node node2) {
						return Double.compare(node1.getHeuristic(),
								node2.getHeuristic());
					}
				});
			}
			//Reduce the expanded node list to beam size 
			if (expandedNodes.size() > beamSize) {
				System.out.println("The expanded nodes size "
						+ expandedNodes.size() + " Grater than beam size "
						+ beamSize);
				for (int i = 0; i < beamSize; i++) {
					System.out.println("Selecting "
							+ expandedNodes.get(i).getLabel() + " To exapnd");
					openNodeQueue.add(expandedNodes.get(i));
				}
			} else if (expandedNodes.size() == 0) {
				System.out.println("reached dead end ... ");
				break;
			} else {
				System.out.println("The expanded nodes size "
						+ expandedNodes.size() + " Less than beam size "
						+ beamSize);
				for (int i = 0; i < expandedNodes.size(); i++) {
					System.out.println("Selecting "
							+ expandedNodes.get(i).getLabel() + " To exapnd");
					openNodeQueue.add(expandedNodes.get(i));
				}
			}

		}
	}
}
