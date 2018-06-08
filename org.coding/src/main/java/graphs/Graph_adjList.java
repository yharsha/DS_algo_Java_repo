package graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph_adjList {
	int vertices;
	boolean visited[];
	LinkedList<Integer>[] arr;

	Graph_adjList(int vertices) {
		this.vertices = vertices;
		arr = new LinkedList[vertices];
		visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			arr[i] = new LinkedList<Integer>();
		}

	}

	void addEdge(int src, int des) {
		arr[src].addLast(des);
	}

	void removeEdge(int src, int des) {
		Iterator<Integer> it = arr[src].iterator();
		while (it.hasNext()) {
			if (it.next() == des) {
				it.remove();
				return;
			}
		}

	}

	boolean hasEdge(int i, int j) {
		return arr[i].contains(j);
	}

	/* print all neighbours */
	void BFS(int node) {
		boolean[] visited = new boolean[vertices];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(node);
		visited[node] = true;
		while (!q.isEmpty()) {
			node = q.poll();
			System.out.print(node + "->");
			Iterator<Integer> it = arr[node].iterator();
			while (it.hasNext()) {
				int temp = it.next();
				if (!visited[temp]) {
					q.add(temp);
					visited[temp] = true;
				}
			}

		}

	}

	/* go deeper into nodes */
	void DFS_Util(int node, boolean visited[]) {
		Iterator<Integer> it = arr[node].iterator();
		visited[node] = true;
		System.out.print(node + "->");
		while (it.hasNext()) {
			int val = it.next();
			if (visited[val] != true) {
				DFS_Util(val, visited);
			}
		}
	}

	void DFS(int node) {
		boolean[] visited = new boolean[this.vertices];
		DFS_Util(node, visited);
	}

	/*
	 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of
	 * vertices such that for every directed edge uv, vertex u comes before v in the
	 * ordering.
	 */
	void topologicalSortUtil(int vertex, Stack<Integer> stack) {
		// all child vertices has to be added...before adding it to stack.
		visited[vertex]=true;
		Iterator<Integer> it = arr[vertex].iterator();
		int x;
		while (it.hasNext()) {
			x = (Integer) it.next();
			if (!visited[x]) {
				topologicalSortUtil(x, stack);
			}
		}
		stack.push(vertex);
	}

	void topologicalSort() {
		Stack<Integer> stack = new Stack<Integer>();
		char[] ch= new char[vertices];
		for (int i = 0; i < this.vertices; i++) {
			ch[i]=(char)(65+i);
			if (!visited[i]) {
				topologicalSortUtil(i, stack);
			}
		}
	
		
		System.out.println("Topological  list of graph: ");
		while (!stack.isEmpty()) {
			System.out.print(ch[stack.pop()] + " ");
		}
	}
	

	void printGraph(Graph_adjList graph) {
		for (int v = 0; v < graph.vertices; v++) {
			System.out.println("Adjacency list of vertex " + v);
			System.out.print("head");
			for (Integer pCrawl : graph.arr[v]) {
				System.out.print(" -> " + pCrawl);
			}
			System.out.println("\n");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph_adjList graph = new Graph_adjList(5);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.addEdge(3, 3);

		// print the adjacency list representation of
		// the above graph
		graph.printGraph(graph);
		graph.DFS(2);
		System.out.println("Below BFS ABOVE DFS");
		/* becareful of visited array */
		graph.BFS(2);

		Graph_adjList projGraph = new Graph_adjList(6);
		projGraph.addEdge(0, 3);
		projGraph.addEdge(5, 1);
		projGraph.addEdge(1, 3);
		projGraph.addEdge(5, 0);
		projGraph.addEdge(3, 2);
		projGraph.printGraph(graph);
		projGraph.topologicalSort();

	}

}
