package binarySearchTree;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 25, 2014
Problem:    Same Tree
Difficulty: easy
Source:     http://leetcode.com/onlinejudge#question_100
Notes:
Given two binary trees, write a function to check if they are equal or not.
Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
Solution: recursion.
*/

import dataStructures.TreeNode;

public class SameTree {
	public static boolean sameTree(TreeNode root1, TreeNode root2){
		if (root1 == null && root2 == null) return true;
		if (root1 == null || root2 == null) return false;
		if (root1.val != root2.val) return false;
		return sameTree(root1.left, root2.left) && sameTree(root1.right, root2.right);
	}
	
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
		
		TreeNode input2 = input;
		input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(142);
		
		System.out.println(SameTree.sameTree(input, input2));
	}
}
