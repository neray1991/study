package binarySearchTree;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 25, 2014
Problem:    Maximum Depth of Binary Tree
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/maximum-depth-of-binary-tree/
Notes:
Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
Solution: Recursion.
*/

import dataStructures.TreeNode;

public class MaxDepthofBinaryTree {
	public static int maxDepthofBinaryTree(TreeNode root) {
		if (root == null) return 0;
		int left = maxDepthofBinaryTree(root.left);
		int right = maxDepthofBinaryTree(root.right);
		return Math.max(left, right) + 1;
	}
	
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
		
		System.out.println(MaxDepthofBinaryTree.maxDepthofBinaryTree(input));
	}
}
