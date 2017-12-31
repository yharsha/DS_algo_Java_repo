/*
Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function would be added by GfG's Online Judge.*/


// FUNCTION CODE
/* A Binary Tree node
class Node
{
    int data;
    Node left, right;
   Node(int item)    {
        data = item;
        left = right = null;
    }
} */
/* Should return minimum distance between a and b 
   in a tree with given root*/
class GfG
{
     Node findLCA (Node node, int n1,int n2)
    {
        if(node==null){return null;}
        
        // If either n1 or n2 matches with root's key, report
        // the presence by returning root (Note that if a key is
        // ancestor of other, then the ancestor key becomes LCA
        if(node.data==n1 || node.data==n2)
        {
            return node;
        }
        
          // Look for keys in left and right subtrees
        Node left_lca = findLCA(node.left, n1, n2);
        Node right_lca = findLCA(node.right, n1, n2);
        
         // If both of the above calls return Non-NULL, then one key
        // is present in once subtree and other is present in other,
        // So this node is the LCA
        if (left_lca!=null && right_lca!=null)
            return node;
        // Otherwise check if left subtree or right subtree is LCA
        return (left_lca != null) ? left_lca : right_lca;
        
    }
	Node LCA(Node root, int n1,int n2)
	{
		// Your code here
		 return findLCA(root, n1, n2);
	}
	
	int findLevel(Node root,int k,int level)
	{
	     if(root == null) return -1;
    if(root.data == k) return level;
 
    int left = findLevel(root.left, k, level+1);
    if (left == -1)
       return findLevel(root.right, k, level+1);
    return left;
	}
    int findDist(Node root, int a, int b)
    {
        // Your code here
        Node lca = LCA(root,a,b);
            int d1 = findLevel(lca, a, 0);
                int d2 = findLevel(lca, b, 0);
                return d1+d2;


    }
}
