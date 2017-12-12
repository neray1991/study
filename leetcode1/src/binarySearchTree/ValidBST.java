package binarySearchTree;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Validate Binary Search Tree
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/validate-binary-search-tree/
Notes:
Given a binary tree, determine if it is a valid binary search tree (BST).
Assume a BST is defined as follows:
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Solution: Recursion. 1. Add lower & upper bound. O(n)
                     2. Inorder traversal with one additional parameter (value of predecessor). O(n)
*/

import dataStructures.TreeNode;

public class ValidBST {
	public static boolean isBST(TreeNode root) {
		if (root == null) return true;
		return isBSTRe(root.left, Integer.MIN_VALUE, root.val) && isBSTRe(root.right, root.val, Integer.MAX_VALUE);
	}
	
	public static boolean isBSTRe(TreeNode root, int lower, int upper) {
		if (root == null) return true;
		if (root.val >= upper || root.val <= lower) return false;
		return isBSTRe(root.left, lower, root.val) && isBSTRe(root.right, root.val, upper); 
	}
	
	public static boolean isBST_1(TreeNode root) {
		if (root == null) return true;
		TreeNode cur = root;
		int pre = Integer.MIN_VALUE;
		while (cur != null) {
			if (cur.left == null) {
				if (cur.val <= pre) return false;
				pre = cur.val;
				cur = cur.right;
				continue;
			}
			TreeNode tmp = cur.left;
			while (tmp.right != null && tmp.right != cur) //Careful, it's && here!
				tmp = tmp.right;
			if (tmp.right == null) {
				tmp.right = cur;
				cur = cur.left;
			} else {
				tmp.right = null;
				if (cur.val <= pre) return false;
				pre = cur.val;
				cur = cur.right;
			}
		}
		return true;
	}
	
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
		
		System.out.println(ValidBST.isBST(input));
		System.out.println(ValidBST.isBST_1(input));
	}
}
