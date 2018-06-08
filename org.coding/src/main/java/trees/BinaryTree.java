package trees;

import java.util.ArrayList;
import java.util.LinkedList;

class BNode {
	int key;
	BNode left;
	BNode right;

	BNode(int data) {
		key = data;
		left = right = null;
	}
}

public class BinaryTree {
	BNode root;

	public static void InorderTraversal(BNode root) {
		if (root == null)
			return;
		// LDR...d-->root...gives results in ascending order
		InorderTraversal(root.left);
		System.out.print(root.key + " ");
		InorderTraversal(root.right);
	}

	public static void PreorderTraversal(BNode root) {
		if (root == null)
			return;
		// DLR...D-->root
		System.out.print(root.key + " ");
		PreorderTraversal(root.left);
		PreorderTraversal(root.right);
	}

	public static void PostorderTraversal(BNode root) {
		if (root == null)
			return;
		// LRD...D-->root
		PostorderTraversal(root.left);
		PostorderTraversal(root.right);
		System.out.print(root.key + " ");
	}

	/*
	 * List of Depths: Given a binary tree, design an algorithm which creates a
	 * linked list of all the nodes at each depth (e.g., if you have a tree with
	 * depth D, you'll have D linked lists)
	 */
	public static void createDepthList(BNode root, ArrayList<LinkedList<BNode>> depthLists, int level) {
		if (root == null)
			return;
		// preorder traversal
		LinkedList<BNode> list = null;
		if (depthLists.size() == level) {
			list = new LinkedList<BNode>();
			depthLists.add(list);
		} else {
			list = depthLists.get(level);
		}
		list.add(root);
		createDepthList(root.left, depthLists, level + 1);
		createDepthList(root.right, depthLists, level + 1);
	}

	static ArrayList<LinkedList<BNode>> createDepthList(BNode root) {
		ArrayList<LinkedList<BNode>> depthLists = new ArrayList<LinkedList<BNode>>();
		createDepthList(root, depthLists, 0);
		return depthLists;

	}

	/* Validate BST */
	boolean isBST(BNode root, int min, int max) {
		if (root == null)
			return true;

		if (root.key < min || root.key > max)
			return false;

		return isBST(root.left, min, root.key) && isBST(root.right, root.key, max);
	}

	BNode findLCA(BNode node, int n1, int n2) {
		if (node == null)
			return null;

		if (node.key == n1 || node.key == n2)
			return node;

		BNode left_key = findLCA(node.left, n1, n2);
		BNode right_key = findLCA(node.right, n1, n2);

		if (left_key != null && right_key != null)
			return node;

		return left_key == null ? right_key : left_key;

	}

	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree();
		tree.root = new BNode(1);
		tree.root.left = new BNode(2);
		tree.root.right = new BNode(3);
		tree.root.left.left = new BNode(4);
		tree.root.left.right = new BNode(5);
		InorderTraversal(tree.root);
		System.out.println("-------Inorder--------");
		PreorderTraversal(tree.root);
		System.out.println("--------Preorder-------");
		PostorderTraversal(tree.root);
		System.out.println("--------Postorder-------");
		System.out.println("--------Depth List Traversal-------");
		int depth_count = 0;
		for (LinkedList<BNode> list : createDepthList(tree.root)) {
			System.out.println("");
			System.out.print("depth_count:" + depth_count + "#> ");
			for (BNode temp : list) {
				System.out.print(temp.key + "->");
			}
			depth_count++;
		}

		System.out.println("--------Check is BST-------");
		System.out.println(tree.isBST(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE));

		BinaryTree tree1 = new BinaryTree();
		tree1.root = new BNode(1);
		tree1.root.left = new BNode(2);
		tree1.root.right = new BNode(3);
		tree1.root.left.left = new BNode(4);
		tree1.root.left.right = new BNode(5);
		tree1.root.right.left = new BNode(6);
		tree1.root.right.right = new BNode(7);
		System.out.println("LCA(4, 5) = " + tree1.findLCA(tree1.root, 4, 5).key);
		System.out.println("LCA(4, 6) = " + tree1.findLCA(tree1.root, 4, 6).key);
		System.out.println("LCA(3, 4) = " + tree1.findLCA(tree1.root, 3, 4).key);
		System.out.println("LCA(2, 4) = " + tree1.findLCA(tree1.root, 2, 4).key);
	}

}
