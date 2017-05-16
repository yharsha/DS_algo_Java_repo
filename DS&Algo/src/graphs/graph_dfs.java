package graphs;

import java.util.*;

class Vertex {
	public char label;
	public boolean visited;

	Vertex(char lab) {
		label = lab;
		visited = false;

	}
}

public class graph_dfs {

	private final int maxVertices = 20;
	private Vertex vertexList[];
	private int adjMatrix[][];
	private int vertexcount;
	private Stack<Integer> theStack;

	public graph_dfs() {
		vertexList = new Vertex[maxVertices];
		adjMatrix = new int[maxVertices][maxVertices];
		vertexcount = 0;
		for (int i = 0; i < maxVertices; i++) {
			for (int j = 0; j < maxVertices; j++) {
				adjMatrix[i][j] = 0;
			}
		}
		theStack = new Stack<Integer>();
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

	public void dfs() {
		if(vertexcount==0){System.out.println("graph is empty");return;}
		theStack.push(0);
		vertexList[0].visited = true;
		displayVertex(0);
		while (!theStack.isEmpty()) {
			int v = getUnvisitedVertex(theStack.peek());
			if (v == -1) {
				// all adj nodes are already visited or no adj nodes
				theStack.pop();
			} else {
				theStack.push(v);
				vertexList[v].visited = true;
				displayVertex(v);
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
graph_dfs g_dfs1 = new graph_dfs();
//adding vertices
g_dfs1.addVertex('A');
g_dfs1.addVertex('B');
g_dfs1.addVertex('C');
g_dfs1.addVertex('D');
g_dfs1.addVertex('E');
g_dfs1.addVertex('F');
g_dfs1.addVertex('G');
g_dfs1.addVertex('H');
//adding edges
g_dfs1.addEdge(0, 1);
g_dfs1.addEdge(1, 2);
g_dfs1.addEdge(2, 3);
g_dfs1.addEdge(2, 4);
g_dfs1.addEdge(1, 7);
g_dfs1.addEdge(4, 7);
g_dfs1.addEdge(4, 5);
g_dfs1.addEdge(4, 6);
g_dfs1.dfs();

	}

}
