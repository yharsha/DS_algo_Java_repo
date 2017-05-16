package graphs;

import java.util.*;

//class Vertex {
//	public char label;
//	public boolean visited;
//
//	Vertex(char lab) {
//		label = lab;
//		visited = false;
//
//	}
//}

public class Graph_bfs {

	private final int maxVertices = 20;
	private Vertex vertexList[];
	private int adjMatrix[][];
	private int vertexcount;
	private Queue<Integer> theQueue;

	public Graph_bfs() {
		vertexList = new Vertex[maxVertices];
		adjMatrix = new int[maxVertices][maxVertices];
		vertexcount = 0;
		for (int i = 0; i < maxVertices; i++) {
			for (int j = 0; j < maxVertices; j++) {
				adjMatrix[i][j] = 0;
			}
		}
		theQueue = new LinkedList<Integer>();
	}

	public void addVertex(char lab) {
		vertexList[vertexcount++] = new Vertex(lab);
	}

	public void addEdge(int i, int j) {
		adjMatrix[i][j] = 1;
		adjMatrix[j][i] = 1;
	}

	public void displayVertex(int v) {
		System.out.println(vertexList[v].label);
	}

	public void bfs() {
		System.out.println("BFS");
		if (vertexcount == 0) {
			System.out.println("graph is empty");
			return;
		}
		vertexList[0].visited = true;
		theQueue.add(0);
		displayVertex(0);
		int v1,v2;
		while (!theQueue.isEmpty()) {
			v1=theQueue.remove();
			while ((v2 = getUnvisitedVertex(v1) )!= -1) {
				theQueue.add(v2);
				vertexList[v2].visited=true;
				displayVertex(v2);
			}
		}

		for (int i = 0; i < vertexcount; i++) {
			vertexList[i].visited = false;
		}
	}

	public int getUnvisitedVertex(int v) {
		for (int i = 0; i < vertexcount; i++) {
			if (adjMatrix[v][i] == 1 && vertexList[i].visited == false) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph_bfs g_bfs1 = new Graph_bfs();
		// adding vertices
		g_bfs1.addVertex('A');
		g_bfs1.addVertex('B');
		g_bfs1.addVertex('C');
		g_bfs1.addVertex('D');
		g_bfs1.addVertex('E');
		g_bfs1.addVertex('F');
		g_bfs1.addVertex('G');
		g_bfs1.addVertex('H');
		// adding edges
		g_bfs1.addEdge(0, 1);
		g_bfs1.addEdge(1, 2);
		g_bfs1.addEdge(2, 3);
		g_bfs1.addEdge(2, 4);
		g_bfs1.addEdge(1, 7);
		g_bfs1.addEdge(4, 7);
		g_bfs1.addEdge(4, 5);
		g_bfs1.addEdge(4, 6);
		g_bfs1.bfs();

	}

}
