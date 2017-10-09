/*
 Author:     King, higuige@gmail.com
 Date:       Oct 07, 2014
 Problem:    Balanced Binary Tree
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/balanced-binary-tree/
 Notes:
 Given a binary tree, determine if it is height-balanced.
 For this problem, a height-balanced binary tree is defined as a binary tree in which 
 the depth of the two subtrees of every node never differ by more than 1.
 Solution: DFS.
 */
/*
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

package balancedBinaryTree;

import dataStructures.*;

class Solution {
	public boolean balancedBinaryTree(TreeNode root) {
		if (root == null) return true;
		return balancedBinaryTreeRe(root) > -1;
	}
	int balancedBinaryTreeRe(TreeNode root) {
		if (root == null) return 0;
		int left = balancedBinaryTreeRe(root.left);
		int right = balancedBinaryTreeRe(root.right);
		if (left == -1 || right == -1) return -1;
		if (Math.abs(left - right) > 1) return -1;
		return Math.max(left, right) + 1;
	}
}

public class BalancedBinaryTree {
	public static void main(String args[]) {
		TreeNode input = new TreeNode(1);
		input.left = new TreeNode(1);
		input.right = new TreeNode(1);
		input.right.left = new TreeNode(1);
		input.right.right = new TreeNode(1);
		input.right.right.left = new TreeNode(1);
		System.out.println(new Solution().balancedBinaryTree(input));
	}
}
