/*
 Author:     King, wangjingui@outlook.com
 Date:       Nov 20, 2014
 Problem:    Binary Tree Postorder Traversal 
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/binary-tree-postorder-traversal/
 Notes:
 Given a binary tree, return the postorder traversal of its nodes' values.
 For example:
 Given binary tree {1,#,2,3},
    1
     \
      2
     /
    3
 return [3,2,1].
 Note: Recursive solution is trivial, could you do it iteratively?
 Solution: 1. Iterative way (stack).   Time: O(n), Space: O(n).
           2. Recursive solution.      Time: O(n), Space: O(n).
           3. Threaded tree (Morris).  Time: O(n), Space: O(n/1). 
              Space: O(1) if in-place reverse.
              You may refer to my blog for more detailed explanations: 
              http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
*/

package binarySearchTree;

import dataStructures.TreeNode;
import java.util.*;

class PostOrder {
	public List<Integer> postOrder_1(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) return res;
		Stack<TreeNode> stk = new Stack<TreeNode>();
		TreeNode node = root;
		TreeNode pre = null;
		while(!stk.isEmpty() || node != null) {
			if (node != null) {
				stk.push(node);
				node = node.left;
			} else {
				TreeNode top = stk.peek();
				if (top.right != null && pre != top.right) {
					node = top.right;
				} else {
					res.add(top.val);
					stk.pop();
					pre = top;
				}
			}
		}
		return res;
	}
	
	public List<Integer> postOrder_2(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) return res;
		Stack<Integer> stk = new Stack<Integer>();
		TreeNode dummy = new TreeNode(-1);
		dummy.left = root;
		TreeNode node = dummy;
		while (node != null) {
			if (node.left == null) {
				node = node.right;
			} else {
				TreeNode pre = node.left;
				while (pre.right != null && pre.right != node)
					pre = pre.right;
				if (pre.right == null) {
					pre.right = node;
					node = node.left;
				} else {
					TreeNode temp = node.left;
					while (temp != node) {
						stk.push(temp.val);
						temp = temp.right;
					}
					while (stk.isEmpty() == false) res.add(stk.pop());
					pre.right = null;
					node = node.right;
				}
			}
		}
		return res;
	}
	
	public List<Integer> postOrderRe(TreeNode root) {
		if (root == null) return null;
		List<Integer> res = new ArrayList<Integer>(); 
		if(root.left != null) res.addAll(postOrderRe(root.left));
		if(root.right != null) res.addAll(postOrderRe(root.right));
		res.add(root.val);
		return res;
	}
}

public class PostOrderTraversal {
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.left.left = new TreeNode(3);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
	
		System.out.println(new PostOrder().postOrder_1(input));
		System.out.println(new PostOrder().postOrder_2(input));
		System.out.println(new PostOrder().postOrderRe(input));
	}
}
