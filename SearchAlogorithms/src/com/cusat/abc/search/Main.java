package com.cusat.abc.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
/**
 * The Main entry point of the application
 * @author Ajmal
 *
 */
public class Main {
	public static void main(String args[]){
		
		try {
			File configXmlFile = new File(
					"C:\\Users\\Ajmal\\Desktop\\nodeconfig.xml"); //Chnage the location of input file 

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(configXmlFile);
			doc.getDocumentElement().normalize();
			String size = doc.getElementsByTagName("Size").item(0)
					.getFirstChild().getTextContent().trim();
			System.out.println(size);

			Graph graph = new Graph();
			graph.setSize(Integer.parseInt(size));

			String nodeLabels = doc.getElementsByTagName("Labels").item(0)
					.getFirstChild().getTextContent();
			String heuristicValues = doc.getElementsByTagName("Values").item(0)
					.getFirstChild().getTextContent();

			List<String> nodeLabelList = Arrays.asList(nodeLabels
					.split("\\s*,\\s*"));
			List<String> nodeHeuristicValueList = Arrays.asList(heuristicValues
					.split("\\s*,\\s*"));
			List<Node> nodeList = new ArrayList<Node>();
			for (int i = 0; i < graph.getSize(); i++) {
				nodeList.add(new Node(nodeLabelList.get(i).trim(), Double
						.parseDouble(nodeHeuristicValueList.get(i).trim())));
			}
			graph.setNodes(nodeList);

			NodeList edgeList = doc.getElementsByTagName("Edge");

			for (int i = 0; i < edgeList.getLength(); i++) {
				String edgeString = edgeList.item(i).getFirstChild()
						.getTextContent();
				List<String> edgeLabelList = Arrays.asList(edgeString
						.split("\\s*,\\s*"));
				Node node1 = graph.getNodeByLabelName(edgeLabelList.get(0)
						.trim());
				Node node2 = graph.getNodeByLabelName(edgeLabelList.get(1)
						.trim());
				graph.connectNode(node1, node2);
			}

			System.out.println(graph.getAdjesancyMatrix());

			String startLabel = doc.getElementsByTagName("StartNode").item(0)
					.getFirstChild().getTextContent().trim();
			String endLabel = doc.getElementsByTagName("EndNode").item(0)
					.getFirstChild().getTextContent().trim();

			int beamSize = Integer.parseInt(doc
					.getElementsByTagName("BeamSize").item(0).getFirstChild()
					.getTextContent().trim());

			Node startNode = graph.getNodeByLabelName(startLabel);
			Node endNode = graph.getNodeByLabelName(endLabel);

			HillClimb hillClimb = new HillClimb();
			hillClimb.hillClimb(graph, startNode, endNode);

			// clear the graph details
			graph.clearVisitedNodes();
			graph.setCurrentPath(new ArrayList<Node>());

			System.out.println("\n\n\n\n");

			// BFS on graph
			BFS bfs = new BFS();
			bfs.bfs(graph, startNode, endNode);

			System.out.println("\n\n\n\n");

			// clear the graph details
			graph.clearVisitedNodes();
			graph.setCurrentPath(new ArrayList<Node>());

			BeamSearch beamSerch = new BeamSearch();
			beamSerch.beamserach(graph, startNode, endNode, beamSize);
			
			System.exit(0); 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
