package graphs;

import java.util.*;
//doing tropological sort also
//acyclic directed graph

import linkedlist.LinkedList;
import linkedlist.LinkedList.Node;

public class Graph_adjlist {
	public ArrayList<Integer> vertices;
	public LinkedList[] edges;
	private int vertex_count = 0;

	public Graph_adjlist(int vertex_count) {
		this.vertex_count = vertex_count;
		vertices = new ArrayList<Integer>();
		edges = new LinkedList[vertex_count];
		for (int i = 0; i < vertex_count; i++) {
			vertices.add(i);
			edges[i] = new LinkedList();
		}

	}

	public void addEdge(int source, int destination) {
		int i = vertices.indexOf(source);
		int j = vertices.indexOf(destination);
		if (i != -1 || j != -1) {
			edges[i].insertAtBegin(new Node(j));
			System.out.println("");
		}
	}

	public void topologicalSort() {
		Stack<Integer> tstack = new Stack<Integer>();
		boolean[] visited_arr = new boolean[vertex_count];

		for (int i = 0; i < vertex_count; i++) {
			visited_arr[i] = false;
		}
		// Call the recursive helper function to store
		// Topological Sort starting from all vertices
		// one by one
		for (int i = 0; i < vertex_count; i++) {
			if (visited_arr[i] == false) {
				topologicalSortUtil(i, visited_arr, tstack);
			}
		}

		System.out.println("// Print contents of stack");
		while (tstack.empty() == false)
			System.out.print(tstack.pop() + " ");

	}

	public void topologicalSortUtil(int v, boolean[] visited_arr, Stack<Integer> tstack) {
		visited_arr[v] = true;
		// recur all vertices adjacent to it
		Node temp;
		temp = edges[v].getHead();
		while (temp != null) {
			int i = temp.getData(temp);
			temp = temp.next;
			if (!visited_arr[i]) {
				topologicalSortUtil(i, visited_arr, tstack);
			}
		}
		tstack.push(new Integer(v));
	}

	public static void main(String[] args) {
		Graph_adjlist g1 = new Graph_adjlist(6);
		g1.addEdge(5, 2);
		g1.addEdge(5, 0);
		g1.addEdge(4, 0);
		g1.addEdge(4, 1);
		g1.addEdge(2, 3);
		g1.addEdge(3, 1);
		System.out.println("Following is a Topological " + "sort of the given graph");
		g1.topologicalSort();

	}

}
