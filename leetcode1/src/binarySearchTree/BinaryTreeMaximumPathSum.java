/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Jan 28, 2015
 Problem:    Binary Tree Maximum Path Sum
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/binary-tree-maximum-path-sum/
 Notes:
 Given a binary tree, find the maximum path sum.
 The path may start and end at any node in the tree.
 For example:
 Given the below binary tree,
   1
  / \
 2   3
 Return 6.
 Solution: Recursion...
 */

package binarySearchTree;

import dataStructures.TreeNode;

class MaxPathSum {
	int maxPathSumRe(TreeNode root, int[] res) {
		if (root == null) return 0;
		int left = maxPathSumRe(root.left, res);
		int right = maxPathSumRe(root.right, res);
		res[0] = Math.max(res[0], root.val + Math.max(left, 0) + Math.max(right, 0));
		return Math.max(root.val, root.val + Math.max(left, right));
 	}
	
	public int maxPathSum(TreeNode root) {
		int[] res = {Integer.MIN_VALUE}; //This is just like a pointer.
		System.out.println(maxPathSumRe(root, res)); //Check it out here, the return value does not include root.val.
		return res[0];
	}
}

public class BinaryTreeMaximumPathSum {
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
	
		System.out.println(new MaxPathSum().maxPathSum(input));
	}
}
