package com.cusat.abc.search;

/**
 * Hill climb search implementation
 * 
 * @author Ajmal
 * 
 */
public class HillClimb {
	/**
	 * 
	 * @param graph
	 * @param start
	 * @param end
	 */
	public void hillClimb(Graph graph, Node start, Node end) {
		graph.setCurrentNode(start);
		graph.addNodeToCurrentPath(start);
		int currentIndex = graph.getNodes().indexOf(start);
		graph.getNodes().get(currentIndex).setVisited(true);
		if (start.equals(end)) {
			System.out.println("Start and end nodes are equal");
		} else {
			System.out.println("------------------- Started Hill climb ----------------- ");
			System.out.print(start.getLabel());
			Node currentNode;
			do {
				currentNode = graph.getMinUnvisitedNeighbour(graph
						.getCurrentNode());
				if (currentNode == null) {
					System.out
							.println("\nReached dead end... Not able to continuee.. ");
				} else if (currentNode.equals(end)) {
					System.out.print("--->" + currentNode.getLabel());
					System.out
							.println("\nReached the destination.. Thank you..");
				} else {
					System.out.print("--->" + currentNode.getLabel());
				}
			} while (currentNode != null && !currentNode.equals(end));

		}

	}
}
