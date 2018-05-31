package graphs;

//undirected graph
public class Graph_adjMat {
	
	int vertices;
	boolean arr[][];
	Graph_adjMat(int vertices)
	{
		this.vertices=vertices;
		arr=new boolean[vertices][vertices];
	}
	
	void addEdge(int src,int des)
	{
		arr[src][des]=true;
		arr[des][src]=true;
	}
	
	void removeEdge(int src,int des)
	{
		arr[src][des]=false;
		arr[des][src]=false;
	}
	
	boolean isEdge(int src,int des)
	{
		return arr[src][des];
	}
	
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < vertices; i++) {
            s.append(i + ": ");
            for (boolean j : arr[i]) {
                s.append((j?1:0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    
	public static void main(String[] args) {
		//https://www.programiz.com/dsa/graph-adjacency-matrix
		Graph_adjMat g = new Graph_adjMat(4);
		 
	        g.addEdge(0, 1);
	        g.addEdge(0, 2);
	        g.addEdge(1, 2);
	        g.addEdge(2, 0);
	        g.addEdge(2, 3);
	        System.out.print(g.toString());
	}

}
