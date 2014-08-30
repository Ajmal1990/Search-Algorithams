package com.cusat.abc.search;

import java.util.Stack;

/**
 * BFS : Best First search implementation
 * @author Ajmal
 *
 */
public class BFS {
	/**
	 * 
	 * @param graph
	 * @param start
	 * @param end
	 */
	public void bfs(Graph graph, Node start, Node end){
		graph.setCurrentNode(start);
		graph.addNodeToCurrentPath(start);
		System.out.println("----------------- Started BFS ----------------- ");
		//System.out.println(start.getLabel());
		System.out.println("Push  " + start.getLabel() +"  into stack");
		
		int currentIndex = graph.getNodes().indexOf(start);
		graph.getNodes().get(currentIndex).setVisited(true);
		
		Stack stack=new Stack();
		stack.push(start);
		while(!stack.isEmpty()) {
			Node n=(Node)stack.peek();
			Node currentNode=graph.getMinUnvisitedNeighbour(n);
			
			if (end.equals(currentNode)) {
				//System.out.print("--->" + currentNode.getLabel());
				System.out
						.println("Reached the destination.." + currentNode.getLabel());
				stack.clear();
				break;
			} else if(currentNode!=null) {
				System.out.println("Push  " + currentNode.getLabel()+"  into stack");
				stack.push(currentNode);
			} else {
				System.out.println("Pop  " + n.getLabel() +"  into stack");
				graph.getCurrentPath().remove(graph.getCurrentPath().indexOf(n));
				stack.pop();
			}
		}
		System.out.print("The BFS PATH  " );
		for(Node n: graph.getCurrentPath()){
			System.out.print("---> "+n.getLabel());
		}
	}
}


