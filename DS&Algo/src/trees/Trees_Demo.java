package trees;

import java.util.*;

public class Trees_Demo {
	Tree_node root;

	// creating a tree data structure
	static class Tree_node {

		Tree_node left;
		int data;
		Tree_node right;

		Tree_node(Tree_node left, int data, Tree_node right) {
			this.left = left;
			this.data = data;
			this.right = right;
		}

		Tree_node() {
		}

		Tree_node(int data) {
			this.left = null;
			this.data = data;
			this.right = null;
		}

		public void set_dat_t(Tree_node left, int data, Tree_node right) {
			this.left = left;
			this.data = data;
			this.right = right;
		}

	}

	public void preOrder(Tree_node root) {
		// DLR //node is visited before its sub trees
		if (root == null) {
			return;
		}
		Tree_node current = root;
		System.out.print(current.data);
		preOrder(current.left);
		preOrder(current.right);
	}

	public void inOrder(Tree_node root) {
		// DLR //node is visited before its sub trees
		if (root == null) {
			return;
		}
		Tree_node current = root;
		inOrder(current.left);
		System.out.print(current.data);
		inOrder(current.right);
	}

	public void postOrder(Tree_node root) {
		// DLR //node is visited before its sub trees
		if (root == null) {
			return;
		}
		Tree_node current = root;
		postOrder(current.left);
		postOrder(current.right);
		System.out.print(current.data);
	}

	public void iterative_preOrder(Tree_node root) {
		// get the current,push it childs to stack..repeat same
		ArrayList<Integer> arrlist = new ArrayList<Integer>();
		if (root == null) {
			return;
		}
		Stack<Tree_node> s1 = new Stack<Tree_node>();
		s1.push(root);
		while (!s1.isEmpty()) {
			Tree_node current = s1.pop();
			arrlist.add(current.data);
			if (current.right != null) {
				s1.push(current.right);
			}
			if (current.left != null) {
				s1.push(current.left);
			}
		}
		Iterator<Integer> itr1 = arrlist.iterator();
		while (itr1.hasNext()) {
			System.out.print(itr1.next());
		}
	}

	public void levelOrder(Tree_node root) {
		if (root == null) {
			return;
		}
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Queue<Tree_node> q1 = new LinkedList<Tree_node>();
		q1.add(root);
		while (!q1.isEmpty()) {
			Tree_node temp = q1.remove();
			arr.add(temp.data);
			if (temp.left != null) {
				q1.add(temp.left);
			}
			if (temp.right != null) {
				q1.add(temp.right);
			}
		}

		Iterator<Integer> itr1 = arr.iterator();
		while (itr1.hasNext()) {
			System.out.print(itr1.next());
		}

	}

	public int treeMaxValue(Tree_node root) {
		int max;
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		int leftmax = treeMaxValue(root.left);
		int rightmax = treeMaxValue(root.right);
		if (leftmax > rightmax) {
			max = leftmax;
		} else
			max = rightmax;
		if (root.data > max) {
			max = root.data;
		}
		return max;
	}

	// finding max of an element
	public boolean findElement_t(Tree_node temp, int x) {
		if (temp == null) {
			return false;
		}
		if (temp.data == x) {
			return true;
		} else {
			return (findElement_t(temp.left, x) || findElement_t(temp.right, x));
		}
	}

	// insert an element at null ...end of tree
	public void insert_t(Tree_node temp, int data) {
		if (temp == null) {
			temp = new Tree_node(data);
		} else if (data > root.data) {
			if (temp.right == null) {
				temp.right = new Tree_node(data);
				return;
			}
			insert_t(temp.right, data);
		} else {
			if (temp.left == null) {
				temp.left = new Tree_node(data);
				return;
			}
			insert_t(temp.left, data);
		}
	}
	//size of tree..no of descendants including  itself
	public int size_t(Tree_node temp)
	{
		if(temp==null){return 0;}
		return (size_t(temp.left)+size_t(temp.right)+1);
	}
	//delete a tree using post order
	public void delete_t(Tree_node temp,int data)
	{
		if(temp==null){return;}
		if(root.data==data){root=null;return;}
		if(temp.data==data){temp=null;return;}
		if(temp.left!=null){if(temp.left.data==data){temp.left=null;return;}}
		if(temp.right!=null){if(temp.right.data==data){temp.right=null;return;}}
		delete_t(temp.left,data);
		delete_t(temp.right,data);
	}
	//reverse print the tree
	public void reverse_t(Tree_node root)
	{
		if(root==null){return;}
		Queue<Tree_node> q = new LinkedList<Tree_node>();
		Stack <Integer> s = new Stack<Integer>();
		q.offer(root);
		while(!q.isEmpty())
		{
			Tree_node temp = new Tree_node();
			temp = q.poll();
			s.push(temp.data);
			if(temp.left!=null)
			{
				q.offer(temp.left);
			}
			if(temp.right!=null)
			{
				q.offer(temp.right);
			}
			
		}
		System.out.println("#Reverse the tree");
		while(!s.isEmpty())
		{
			System.out.print(s.pop());
		}
	}
	//height of tree...length of path from current node to deepest node
	public int height_t(Tree_node temp)
	{
		if(temp==null){return -1;}
		int left_height = height_t(temp.left);
		int right_height = height_t(temp.right);
		return ((left_height > right_height ? left_height : right_height)+1);
	}
	//min depth is the number of nodes along the shortest path
	//from the root node down to the nearest leaf node.
	//min = 0, max=1
	public int min_depth(Tree_node temp,int indicator)
	{
		if(temp==null){return -1;}
		
		int left_dep = min_depth(temp.left,indicator);
		int right_dep = min_depth(temp.right,indicator);
		if(indicator==0){
		return((left_dep < right_dep ? left_dep : right_dep)+1);}
		else{return((left_dep > right_dep ? left_dep : right_dep)+1);}
		
	}
	public static void main(String[] args) {

		Trees_Demo obj = new Trees_Demo();

		Tree_node t7 = new Tree_node(null, 7, null);
		Tree_node t6 = new Tree_node(null, 6, null);
		Tree_node t5 = new Tree_node(null, 5, null);
		Tree_node t4 = new Tree_node(null, 4, null);
		Tree_node t3 = new Tree_node(t6, 3, t7);
		Tree_node t2 = new Tree_node(t4, 2, t5);
		Tree_node t1 = new Tree_node(t2, 1, t3);

		obj.root = t1;
		System.out.println("*********Preorder-DLR**********");
		obj.preOrder(obj.root);
		System.out.println("");
		System.out.println("*********Preorder-DLR-iterative**********");
		obj.iterative_preOrder(obj.root);
		System.out.println("");
		System.out.println("*********INorder**********");
		obj.inOrder(obj.root);
		System.out.println("");
		System.out.println("*********Postorder**********");
		obj.postOrder(obj.root);
		System.out.println("");
		System.out.println("*********Level order traversal**********");
		obj.levelOrder(obj.root);
		System.out.println("");
		System.out.println("...............Max_Value:...........................");
		System.out.println("Max_Value:" + obj.treeMaxValue(obj.root));
		System.out.println("");
		System.out.println("...............Findelement:...........................");
		System.out.println("Findelement:" + obj.findElement_t(obj.root, 11));
		System.out.println("...............Insertelement:...........................");
		obj.insert_t(obj.root, 10);
		System.out.println("*********Level order traversal**********");
		obj.levelOrder(obj.root);
		System.out.println("");
		System.out.println("*Size of tree**********");
		System.out.println(obj.size_t(obj.root));
		System.out.println("*Delete a node  of tree**********");
//		obj.delete_t(obj.root,10);
		System.out.println("*********Level order traversal**********");
		obj.levelOrder(obj.root);
		obj.reverse_t(obj.root);
		System.out.println("Tree Height:"+obj.height_t(obj.root));
		System.out.println(" ");
		System.out.println("Min Dep:"+ obj.min_depth(obj.root,0));
		System.out.println("Max Dep:"+ obj.min_depth(obj.root,1));
		
	}
}
