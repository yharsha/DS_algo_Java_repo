package graphs;

import java.io.*;
import java.util.*;

class CNode {
	int index;
	int value;

}

public class LightImpl {

	public static void printPaths(LinkedList<CNode>[] lookup, int vertices, int startNode) {

		boolean visited[] = new boolean[vertices + 1];
		visited[0] = true;
		visited[startNode] = true;
		// start from start node and check for next closer one
		while (true) {
			int minlen = Integer.MAX_VALUE, vertex = -1;
			for (int i = 1; i < visited.length; i++) {
				// for all lit up nodes find min len node which is not visited
				if (visited[i]) {
					LinkedList<CNode> tempList;
					tempList = lookup[i];
					for (CNode temp : tempList) {
						if (temp.value < minlen && !visited[temp.index]) {
							minlen = temp.value;
							vertex = temp.index;
						}
					}
				}
			}
			if (vertex == -1)
				break;
			System.out.print(minlen + " ");
			visited[vertex] = true;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tests = Integer.parseInt(br.readLine());

		for (int i = 0; i < tests; i++) {
			String[] tokens = br.readLine().split("\\s");
			int vertices = Integer.parseInt(tokens[0]);
			int paths = Integer.parseInt(tokens[1]);

			LinkedList<CNode>[] lookup = new LinkedList[vertices + 1];
			for (int j = 0; j < vertices + 1; j++) {
				lookup[j] = new LinkedList<CNode>();
			}

			for (int j = 0; j < paths; j++) {
				String[] values = br.readLine().split("\\s");
				int index = Integer.parseInt(values[0]);
				CNode temp1 = new CNode();
				temp1.index = Integer.parseInt(values[1]);
				temp1.value = Integer.parseInt(values[2]);
				lookup[index].addLast(temp1);
				CNode temp2 = new CNode();
				temp2.index = index;
				temp2.value = temp1.value;
				// System.out.println(temp2);
				lookup[temp1.index].addLast(temp2);
			}
			int startNode = Integer.parseInt(br.readLine());
			printPaths(lookup, vertices, startNode);
		}
	}
}
