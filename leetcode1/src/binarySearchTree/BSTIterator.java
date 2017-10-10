/*
 Author:     king, wangjingui@outlook.com
 Date:       Dec 31, 2014
 Problem:    Binary Search Tree Iterator 
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/binary-search-tree-iterator/
 Notes:
 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 Calling next() will return the next smallest number in the BST.
 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 Solution: Inorder traversal.
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

package binarySearchTree;

import java.util.*;
import dataStructures.TreeNode;

class BSTIterator_1 {
	private TreeNode node;
	private Stack<TreeNode> stk;
	
	public BSTIterator_1(TreeNode root) {
		node = root;
		stk = new Stack<TreeNode>();
	}
	
	public boolean hasNext() {
		if(node == null && stk.isEmpty() == true) return false;
		return true;
	}
	
	public int next() {
		if (node == null && stk.isEmpty() == true) return 0;
		while (node != null) {
			stk.push(node);
			node = node.left;
		}
		int res = 0;
		node = stk.pop();
		res = node.val;
		node = node.right;
		return res;
	}
	
}

class BSTIterator_2 {
	private TreeNode node;
	
	public BSTIterator_2(TreeNode root) {
		node = root;
	}
	
	public boolean hasNext() {
		return node != null;
	}
	
	public int next() {
		if (node == null) return 0;
		int res = 0;
		while (node != null) {
			if (node.left == null) {
				res = node.val;
				node = node.right;
				return res;
			}
			TreeNode pre = node.left;
			while (pre.right != null && pre.right != node)
				pre = pre.right;
			if (pre.right == null) {
				pre.right = node;
				node = node.left;
			} else {
				pre.right = null;
				res = node.val;
				node = node.right;
				return res;
			}
		}
		return res;
	}
}

public class BSTIterator {
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
		
		BSTIterator_1 itr = new BSTIterator_1(input);
		BSTIterator_2 itr2 = new BSTIterator_2(input);
		while(itr.hasNext()) System.out.println(itr.next());
		while(itr2.hasNext()) System.out.println(itr2.next());
	}
}
