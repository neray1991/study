/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 25, 2014
 Problem:    Binary Tree Preorder Traversal
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/binary-tree-preorder-traversal/
 Notes:
 Given a binary tree, return the preorder traversal of its nodes' values.
 For example:
 Given binary tree {1,#,2,3},
    1
     \
      2
     /
    3
 return [1,2,3].
 Note: Recursive solution is trivial, could you do it iteratively?
 Solution: 1. Iterative way (stack).   Time: O(n), Space: O(n).
           2. Recursive solution.      Time: O(n), Space: O(n).
           3. Threaded tree (Morris).  Time: O(n), Space: O(1).
 */

package binarySearchTree;

import dataStructures.TreeNode;
import java.util.*;

class PreOrder {
	public List<Integer> preOrderRe(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) return res;
		res.add(root.val);
		if(root.left != null) res.addAll(preOrderRe(root.left));
		if(root.right != null) res.addAll(preOrderRe(root.right));
		return res;
	}
	
	public List<Integer> preOrder_1(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) return res;
		Stack<TreeNode> stk = new Stack<TreeNode>();
		TreeNode node = root;
		while(node != null || stk.isEmpty() == false){
			while(node != null) {
				res.add(node.val);
				stk.push(node);
				node = node.left;
			}
			node = stk.pop();
			node = node.right;
		}
		return res;
	}
	public List<Integer> preOrder_2(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		if (root == null) return res;
		TreeNode cur = root;
		while(cur != null) {
			if (cur.left == null) {
				res.add(cur.val);
				cur = cur.right;
			} else {
				TreeNode node = cur.left;
				while (node.right != null && node.right != cur)
					node = node.right;
				if (node.right == null) {
					node.right = cur;
					res.add(cur.val);
					cur = cur.left;
				} else {
					node.right = null;
					cur = cur.right;
				}
			}
		}
		return res;
	}
}

public class PreOrderTraversal {
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
	
		System.out.println(new PreOrder().preOrder_1(input));
		System.out.println(new PreOrder().preOrder_2(input));
		System.out.println(new PreOrder().preOrderRe(input));
	}
}
