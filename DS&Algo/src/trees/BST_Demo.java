package trees;

import java.util.*;

//Main need for BSt is Searchingin a tree...worst case is o(nlogn)
//bcoz left subtree data < root && right sub tree> root

//...Inoreder traversal will give sorted list
public class BST_Demo {
	bst_node root;

	static class bst_node {
		int data;
		bst_node left;
		bst_node right;

		bst_node(bst_node left, int data, bst_node right) {
			this.left = left;
			this.data = data;
			this.right = right;
		}

		void bst_node_set(bst_node left, int data, bst_node right) {
			this.left = left;
			this.data = data;
			this.right = right;
		}

	}

	void insert_in_bst(int data, bst_node temp) {
		if (root == null) {
			root = new bst_node(null, data, null);
			return;
		}
		if (data < temp.data) {
			if (temp.left == null) {
				temp.left = new bst_node(null, data, null);
				return;
			}
			insert_in_bst(data, temp.left);
		} else if (data > temp.data) {
			if (temp.right == null) {
				temp.right = new bst_node(null, data, null);
				return;
			}
			insert_in_bst(data, temp.right);
		}
	}

	void levelorder_bst(bst_node temp) {
		if (temp == null) {
			System.out.println("Empty tree");
			return;
		}
		Queue<bst_node> q1 = new LinkedList<bst_node>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		q1.add(temp);
		bst_node current;
		while (!q1.isEmpty()) {
			current = q1.remove();
			if (current.left != null) {
				q1.add(current.left);
			}
			if (current.right != null) {
				q1.add(current.right);
			}
			arr.add(current.data);
		}
		Iterator<Integer> itr1 = arr.iterator();
		while (itr1.hasNext()) {
			System.out.print(itr1.next());
		}
	}

	public bst_node find_element_bst(int data, bst_node temp) {
		// TC:o(n),skew trees...sc:o(n)
		if (temp == null) {
			return null;
		}

		if (data < temp.data && temp.left != null) {
			return find_element_bst(data, temp.left);
		} else if (data > temp.data && temp.right != null) {
			return find_element_bst(data, temp.right);
		} else if (data == temp.data) {
			return temp;
		} else {
			System.out.println("Element Not found");
			return null;
		}

	}

	public bst_node min_max_element_bst(bst_node temp,int indicator) {
		if (temp == null) {
			return null;
		}
		if(indicator ==0){
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp;}
		else if(indicator==1)
		{
			while (temp.right != null) {
				temp = temp.right;
			}
			return temp;	
		}
		else{
			return null;
		}
	}

	public bst_node delete_in_bst(int data,bst_node temp)
	{
		
		if(temp==null){System.out.println("Didnt delete");return null;}
		else if(data>temp.data){temp.right = delete_in_bst(data, temp.right); return temp;}
		else if(data<temp.data){temp.left=delete_in_bst(data, temp.left);return temp;}
		else if(data==temp.data)
		{
			//if data matches...
			//1.Deleting leaf node
			if(temp.left==null && temp.right==null)
			{
				temp=null;
				return null;
			}
			//only on child for node to deleted
			else if(temp.left!=null && temp.right==null)
			{
				return temp.left;
			}
			else if(temp.left==null && temp.right!=null)
			{
				return temp.right;
			}//if both childs exists
			else if(temp.left!=null && temp.right!=null)
			{
				temp.data = min_max_element_bst(temp.left, 1).data;
				temp.left = delete_in_bst(temp.data, temp.left);
				return temp;
			}
			else {System.out.println("Didnt delete");return null;}
		}
		else {System.out.println("Didnt delete");return null;}
		
	}
	public boolean check_bst(bst_node temp)
	{
			if(temp==null){return true;}
			if(temp.left!=null && temp.data<min_max_element_bst(temp.left,1).data)
			{
				return false;
			}
			if(temp.right!=null && temp.data>min_max_element_bst(temp.right,0).data)
			{
				return false;
			}
			if(!check_bst(temp.left)||!check_bst(temp.right))
			{
				return false;
			}			
			return true;
	}
	
	public static void main(String[] args) {
		BST_Demo b1 = new BST_Demo();
		b1.insert_in_bst(5, b1.root);
		b1.insert_in_bst(8, b1.root);
		b1.insert_in_bst(6, b1.root);
		b1.insert_in_bst(9, b1.root);
		b1.insert_in_bst(3, b1.root);
		b1.insert_in_bst(4, b1.root);
		b1.insert_in_bst(2, b1.root);
		b1.levelorder_bst(b1.root);
		System.out.println("");
		System.out.println(b1.find_element_bst(3, b1.root));
		System.out.println("Min element:" + b1.min_max_element_bst(b1.root,0).data);
		System.out.println("Max element:" + b1.min_max_element_bst(b1.root,1).data);
		b1.insert_in_bst(10, b1.root);
		b1.levelorder_bst(b1.root);
		b1.delete_in_bst(10, b1.root);
		System.out.println("after deletion");
		b1.levelorder_bst(b1.root);
		System.out.println(b1.check_bst(b1.root));

	}

}
