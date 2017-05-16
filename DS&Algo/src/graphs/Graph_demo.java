package graphs;


public class Graph_demo {
	public static class Graph {
		public boolean adjMatxix[][];
		public int vertexCount;

		public Graph(int vertexCount) {
			// tc:o(v2) ..sc:O(v2)
			//by adjmatrix method
			this.vertexCount = vertexCount;
			adjMatxix = new boolean[vertexCount][vertexCount];
		}

		public void addEdge(int i, int j) {
			// undirected graph
			if (i < this.vertexCount && j < this.vertexCount && i >= 0 && j >= 0) {
				adjMatxix[i][j] = true;
				adjMatxix[j][i] = true;
			} else {
				adjMatxix[i][j] = false;
				adjMatxix[j][i] = false;
			}
		}

		public void remove_Edge(int i, int j) {
			if (i < this.vertexCount && j < this.vertexCount && i >= 0 && j >= 0) {
				adjMatxix[i][j] = false;
				adjMatxix[j][i] = false;
			} else {
				adjMatxix[i][j] = true;
				adjMatxix[j][i] = true;
			}
		}

		public boolean isEdge(int i, int j) {
			if (i < this.vertexCount && j < this.vertexCount && i >= 0 && j >= 0) {

				return adjMatxix[i][j];
			} else
				return false;
		}

		public void printmat() {
			for (int i = 0; i < this.vertexCount; i++) {
				System.out.println("");
				for (int j = 0; j < this.vertexCount; j++) {
					if (adjMatxix[i][j] == true) {
						System.out.print(1 + " : ");
					} else
						System.out.print(0 + " : ");
				}
			}
		}

	}

	public static void main(String[] args) {
		//Graph_demo gd1 = new Graph_demo();
		Graph g1 = new Graph(4);
		g1.addEdge(0, 3);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(2, 3);
		g1.addEdge(1, 3);
		g1.printmat();
	}
}
