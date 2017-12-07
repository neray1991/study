package binarySearchTree;

/*
Author:     King, wangjingui@outlook.com
Date:       Jan 02, 2014
Problem:    Sum Root to Leaf Numbers
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/sum-root-to-leaf-numbers/
Notes:
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
An example is the root-to-leaf path 1->2->3 which represents the number 123.
Find the total sum of all root-to-leaf numbers.
For example,
  1
 / \
2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Return the sum = 12 + 13 = 25.
Solution: 1. Recursion (add to sum when reaching the leaf).
*/

import dataStructures.TreeNode;

public class SumRoottoLeafNumbers {
	public static long sumRoottoLeaf(TreeNode root) {
		if (root == null) return 0;
		return sumRoottoLeafRe(root, 0);
		//return sumNumbersRe_0(root, 0);
	}
	
	public static long sumRoottoLeafRe(TreeNode root, int path) {
		long left = 0, right = 0;
		if (root.left != null) {
			left = sumRoottoLeafRe(root.left, path * 10 + root.val);
		}
		if (root.right != null) {
			right = sumRoottoLeafRe(root.right, path * 10 + root.val);
		}
		if (root.left == null && root.right == null) return path * 10 + root.val;
		return left + right;
	}
	
	public static long sumNumbersRe_0(TreeNode root, int last) {
		if (root == null) return 0;
		int res = last * 10 + root.val;
		if (root.left == null && root.right == null) return res;
		if (root.right == null) return sumNumbersRe_0(root.left, res);
		if (root.left == null) return sumNumbersRe_0(root.right, res);
		return sumNumbersRe_0(root.left, res) + sumNumbersRe_0(root.right, res);
	}
	
	public static void main(String args[]) {
		TreeNode input = new TreeNode(1);
		input.left = new TreeNode(5);
		input.right = new TreeNode(3);
		input.right.left = new TreeNode(0);
		input.right.right = new TreeNode(9);
		input.right.right.left = new TreeNode(0);
		
		System.out.println(SumRoottoLeafNumbers.sumRoottoLeaf(input));
	}
}
