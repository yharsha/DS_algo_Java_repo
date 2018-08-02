package graphs;

//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node {
	int number;
	int cost;
}

public class TestClass {

	public static int findMaxSum(LinkedList<Node>[] lookup, int money) {
		int number = 9;
		int maxsum = 0;
		LinkedList<Node> tempList = new LinkedList<Node>();
		System.out.println("Money:" + money);
		while (money > 0) {

			int tempNum = Integer.MIN_VALUE, tempCost = Integer.MAX_VALUE;
			// loop for that paticular number
			tempList = lookup[number];
			for (Node temp : tempList) {
				System.out.println(temp.cost + " :: " + tempCost + "**" + tempNum + " :: " + temp.number);
				if (temp.cost <= tempCost && tempNum < temp.number) {
					tempNum = temp.number;
					tempCost = temp.cost;
				}
			}
			System.out.println("num:" + tempNum + " cost:" + tempCost);
			maxsum += tempNum;
			money -= tempCost;
			number = tempNum;
		}

		return maxsum;

	}

	@SuppressWarnings("unchecked")
	public static void main(String args[]) throws Exception {

		LinkedList<Node>[] lookup = new LinkedList[10];
		for (int i = 0; i < 10; i++) {
			lookup[i] = new LinkedList<Node>();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int money = Integer.parseInt(br.readLine());

		for (int i = 0; i < 12; i++) {
			String[] tokens = br.readLine().split("\\s");
			int index = Integer.parseInt(tokens[0]);
			Node temp = new Node();
			temp.number = Integer.parseInt(tokens[1]);
			temp.cost = Integer.parseInt(tokens[2]);
			lookup[index].addFirst(temp);

			System.out.println(index + " Hi, " + temp.number + " : " + temp.cost); // Writing output to STDOUT
		}
		System.out.println(findMaxSum(lookup, money));
	}
}
