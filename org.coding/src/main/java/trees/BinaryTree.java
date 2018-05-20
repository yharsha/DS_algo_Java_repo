package trees;

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

	public static void InorderTraversal(BNode root)
	{
		if(root==null)return;
		//LDR...d-->root
		InorderTraversal(root.left);
		System.out.print(root.key+" ");
		InorderTraversal(root.right);
	}
	
	public static void PreorderTraversal(BNode root)
	{
		if(root==null)return;
		//DLR...D-->root
		System.out.print(root.key+" ");
		PreorderTraversal(root.left);
		PreorderTraversal(root.right);
	}
	
	public static void PostorderTraversal(BNode root)
	{
		if(root==null)return;
		//LRD...D-->root
		PostorderTraversal(root.left);
		PostorderTraversal(root.right);
		System.out.print(root.key+" ");
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
	}

}
