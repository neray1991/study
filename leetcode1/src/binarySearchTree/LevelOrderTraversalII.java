/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Dec 12, 2014
 Problem:    Binary Tree Level Order Traversal II
 Difficulty: easy
 Source:     https://oj.leetcode.com/problems/binary-tree-level-order-traversal-ii/
 Notes:
 Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
 (ie, from left to right, level by level from leaf to root).
 For example:
 Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
 return its bottom-up level order traversal as:
 [
  [15,7]
  [9,20],
  [3],
 ]
 
 Solution: Queue version. On the basis of 'Binary Tree Level Order Traversal', reverse the final vector.
 */

package binarySearchTree;

import java.util.*;

import dataStructures.TreeNode;

public class LevelOrderTraversalII {
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
	
		List<List<Integer>> res = new LevelOrder().levelOrder_1(input);
		Collections.reverse(res);
		System.out.println(res);
	}
}
