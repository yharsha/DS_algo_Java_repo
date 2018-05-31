package trees;

class BSTNode {
	BSTNode left, right;
	int key;

	BSTNode(int key) {
		this.key = key;
		this.left = null;
		this.right = null;

	}

}

public class BinarySearchTree {
	BSTNode root;

	/*
	 * To create a tree of minimal height, we need to match the number of nodes in
	 * the left subtree to the number of nodes in the right subtree as much as
	 * possible
	 */
	BSTNode createMinimalBst(int[] arr, int start, int end) {
		if (start > end)
			return null;
		int mid = (start + end) / 2;
		BSTNode curr = new BSTNode(arr[mid]);
		curr.left = createMinimalBst(arr, start, mid - 1);
		curr.right = createMinimalBst(arr, mid + 1, end);
		return curr;

	}

	void InorderTraversal(BSTNode root) {
		if (root == null)
			return;
		// LDR...d-->root...gives results in ascending order
		InorderTraversal(root.left);
		System.out.print(root.key + " ");
		InorderTraversal(root.right);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 1, 2, 3, 4, 5,9,10 };
		BinarySearchTree bst1 = new BinarySearchTree();
		bst1.root = bst1.createMinimalBst(arr, 0, arr.length-1);
		bst1.InorderTraversal(bst1.root);

	}

}
