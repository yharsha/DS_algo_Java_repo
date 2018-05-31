package graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph_adjList {
	int vertices;
	LinkedList<Integer>[] arr;

	Graph_adjList(int vertices) {
		this.vertices = vertices;
		arr = new LinkedList[vertices];
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
	}

}
