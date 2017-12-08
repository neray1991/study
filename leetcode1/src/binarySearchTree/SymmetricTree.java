package binarySearchTree;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Dec 25, 2014
Problem:    Symmetric Tree
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/symmetric-tree/
Notes:
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
For example, this binary tree is symmetric:
    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
   1
  / \
 2   2
  \   \
  3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
Solution: 1. Recursive solution
*/

import dataStructures.TreeNode;

public class SymmetricTree {
	public static boolean isSymmetric(TreeNode root){
		if (root == null) return true;
		return isSymmetricRe_0 (root.left, root.right);
	}
	public static boolean isSymmetricRe(TreeNode left, TreeNode right) {
		if (left == null && right == null) return true;
		if (left == null || right == null) return false;
		while (left.val == right.val) {
			boolean tmp = isSymmetricRe(left.right, right.left); //Put this in the loop, we need to do this every loop.
			if (!tmp) return false;
			left = left.left;
			right = right.right;
			if (left == null && right == null) return true;
			if (left == null || right == null) return false;
		}
		return false;
	}
	
	public static boolean isSymmetricRe_0(TreeNode left, TreeNode right) {
		if (left == null && right == null) return true;
		if (left == null || right == null || left.val != right.val) return false;
		return isSymmetricRe_0(left.left, right.right) && isSymmetricRe_0(left.right, right.left);
	}
	
	public static void main(String args[]) {
		TreeNode input = new TreeNode(1);
		input.left = new TreeNode(2);
		input.right = new TreeNode(2);
		input.right.left = new TreeNode(3);
		input.left.right = new TreeNode(3);
		input.right.right = new TreeNode(4);
		input.left.left = new TreeNode(4);
		input.right.right.left = new TreeNode(1);
		input.left.left.right = new TreeNode(1);
		
		System.out.println(SymmetricTree.isSymmetric(input));
	}
}
