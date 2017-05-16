package graphs;

class vertex_ds {
	public char label;
	int distance;
	char prev_vertex;

	public vertex_ds(char lab) {
		this.label = lab;
		this.distance = Integer.MAX_VALUE;
	}
}

public class Dijkstra {
	public vertex_ds[] vertex_list_ds;
	public int v_count;
	public int vertices = 0;
	public int adj_matrix_ds[][];

	public Dijkstra(int v_count) {
		this.v_count = v_count;
		adj_matrix_ds = new int[v_count][v_count];
		vertex_list_ds = new vertex_ds[v_count];
		for (int i = 0; i < v_count; i++) {
			for (int j = 0; j < v_count; j++) {
				adj_matrix_ds[i][j] = 0;
			}
		}
	}

	public void addEdge_ds(int i, int j, int val) {
		adj_matrix_ds[i][j] = val;
		adj_matrix_ds[j][i] = val;
	}

	public void addvertex_ds(char lab) {
		vertex_list_ds[this.vertices++] = new vertex_ds(lab);
	}

	public void print_edges_ds() {
		for (int i = 0; i < this.v_count; i++) {
			System.out.println("");
			for (int j = 0; j < v_count; j++) {
				System.out.print(" ");
				System.out.print(adj_matrix_ds[i][j]);
			}
		}
		System.out.println(" ");
	}

	public void dijkstra_shortpath(int s) {
		// vertex s from where we need to find short path to all nodes
		boolean visited[] = new boolean[v_count];
		vertex_list_ds[s].distance = 0;
		// int oe,min,min_index;
		utility(s, visited);
	}

	public void utility(int s, boolean visited[]) {
		if(visited[s]==true){return;}
		visited[s] = true;
		int min, min_index = -1;
		// find outgoing nodes and update distance
		for (int i = 0; i < v_count; i++) {
			if (adj_matrix_ds[s][i] != 0 && visited[i] == false
					&& (vertex_list_ds[i].distance > vertex_list_ds[s].distance + adj_matrix_ds[s][i])) {
				// calculated distance shorter than shown distance
				vertex_list_ds[i].distance = vertex_list_ds[s].distance + adj_matrix_ds[s][i];
				vertex_list_ds[i].prev_vertex = vertex_list_ds[s].label;
			}
		}

		// find minimum of short distance apart from one
		min = Integer.MAX_VALUE;
		for (int i = 0; i < v_count; i++) {
			if (visited[i]!=true) {

				if (vertex_list_ds[i].distance < min) {
					min = vertex_list_ds[i].distance;
					min_index = i;
				}
			}
		}
		if (min_index != -1) {
			utility(min_index, visited);
		}

	}

	public void print_vertex_table_ds() {
		for (int i = 0; i < v_count; i++) {
			System.out.println(
					vertex_list_ds[i].label + "::" + vertex_list_ds[i].distance + "::" + vertex_list_ds[i].prev_vertex);
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		Dijkstra d1 = new Dijkstra(5);
		d1.addvertex_ds('A');
		d1.addvertex_ds('B');
		d1.addvertex_ds('C');
		d1.addvertex_ds('D');
		d1.addvertex_ds('E');
		d1.addEdge_ds(0, 1, 6);
		d1.addEdge_ds(0, 3, 1);
		d1.addEdge_ds(1, 2, 5);
		d1.addEdge_ds(1, 4, 2);
		d1.addEdge_ds(1, 3, 2);
		d1.addEdge_ds(2, 4, 5);
		d1.addEdge_ds(3, 4, 1);
		d1.print_edges_ds();
		d1.dijkstra_shortpath(0);
		System.out.println("Dijkstra algorithm...finding shortest from A to all other Nodes in vertex");
		d1.print_vertex_table_ds();
	}

}
