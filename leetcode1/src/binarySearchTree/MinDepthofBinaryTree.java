package binarySearchTree;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 25, 2014
Problem:    Minimum Depth of Binary Tree
Difficulty: easy
Source:     https://oj.leetcode.com/problems/minimum-depth-of-binary-tree/
Notes:
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node 
down to the nearest leaf node.

Solution: 1. Recursion. Pay attention to cases when the non-leaf node has only one child.
          PS. 2. Iteration + Queue. 
*/

import dataStructures.TreeNode;

public class MinDepthofBinaryTree {
	public static int minDepthofBinaryTreeRe(TreeNode root) {
		if (root == null) return 0;
		if (root.left == null) return minDepthofBinaryTreeRe(root.right) + 1; //leaf means both left child and right child are null.
		if (root.right == null) return minDepthofBinaryTreeRe(root.left) + 1;
		int left = minDepthofBinaryTreeRe(root.left) + 1;
		int right = minDepthofBinaryTreeRe(root.right) + 1;
		return Math.min(left, right);
	}
	
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
		
		System.out.println(MinDepthofBinaryTree.minDepthofBinaryTreeRe(input));
	}
}
