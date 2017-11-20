package trees;

import trees.Trees_Demo.Tree_node;
import java.util.*;

public class Tree_VerticalOrder {

	
	public void printTreeInVerticalOrder(Tree_node rootnode,TreeMap<Integer,String> map,int hd)
	{
		if(rootnode==null)
		{
			return ;
		}
		if(map.get(hd)==null)
		{
			map.put(hd, String.valueOf(rootnode.data));
		}
		else
		{
			map.put(hd, map.get(hd)+" "+rootnode.data);
		}
		printTreeInVerticalOrder(rootnode.left,map,hd-1);
		printTreeInVerticalOrder(rootnode.right,map,hd+1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree_VerticalOrder tv1 = new Tree_VerticalOrder();
		Tree_node t7 = new Tree_node(null, 7, null);
		Tree_node t6 = new Tree_node(null, 6, null);
		Tree_node t5 = new Tree_node(null, 5, null);
		Tree_node t4 = new Tree_node(null, 4, null);
		Tree_node t3 = new Tree_node(t6, 3, t7);
		Tree_node t2 = new Tree_node(t4, 2, t5);
		Tree_node t1 = new Tree_node(t2, 1, t3);
	
		TreeMap<Integer,String> map = new TreeMap<Integer,String>();
		tv1.printTreeInVerticalOrder(t1,map,0);
		for(Map.Entry<Integer, String> entry : map.entrySet())
		{
			System.out.println(entry.getValue());
		}
	}

}
