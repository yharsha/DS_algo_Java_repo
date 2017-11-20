package graphs;

import java.util.LinkedList;

public class Gr_adlist {
	
	static class Graph_l{
		
		int V;
		LinkedList<Integer> adjListArray[];//array of linkedlists
		//for weighted us this LinkedList<Integer,Integer>
		
		Graph_l(int V)
		{
			this.V=V;
		
			// define the size of array as 
            // number of vertices
			adjListArray = new LinkedList[V];
			
			for(int i=0;i<V;i++)
			{
				adjListArray[i] = new LinkedList<>();
			}
		}
		
	}
	
	static void addEdge(Graph_l gr,int src,int des)
	{
		gr.adjListArray[src].addLast(des);
		// Since graph is undirected, add an edge from dest
        // to src also
		gr.adjListArray[des].addLast(src);
	}
	
	static void printGraph(Graph_l gr)
	{
		for(int j=0;j<gr.V;j++)
		{
			 System.out.println("Adjacency list of vertex "+ j);
	            System.out.print("head");
	            for(Integer p : gr.adjListArray[j])
	            {
	            	System.out.print(" ->"+p);
	            }
	            System.out.println("\n");
		}
	}
	public static void main(String args[])
	{
		int V=5;
		Graph_l gr = new Graph_l(V);
		addEdge(gr,0,1);
	     addEdge(gr, 0, 4);
	        addEdge(gr, 1, 2);
	        addEdge(gr, 1, 3);
	        addEdge(gr, 1, 4);
	        addEdge(gr, 2, 3);
	        addEdge(gr, 3, 4);
	      
	        // print the adjacency list representation of 
	        // the above gr
	        printGraph(gr);
		
		
	}

}
