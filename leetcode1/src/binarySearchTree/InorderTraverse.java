/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 20, 2014
 Problem:    Binary Tree Inorder Traversal
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/binary-tree-inorder-traversal/
 Notes:
 Given a binary tree, return the inorder traversal of its nodes' values.
 For example:
 Given binary tree {1,#,2,3},
 1
  \
   2
  /
 3
 return [1,3,2].
 Note: Recursive solution is trivial, could you do it iteratively?
 Solution: 1. Recursive solution.      Time: O(n), Space: O(n).
           2. Iterative way (stack).   Time: O(n), Space: O(n).
           3. Threaded tree (Morris).  Time: O(n), Space: O(1).
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

class Solution {
	public void recursiveSolution(TreeNode node) {
		if (node == null) return;
		recursiveSolution(node.left);
		System.out.println(node.val);
		recursiveSolution(node.right);
	}
	
	public void iterativeSolution(TreeNode node) {
		Stack<TreeNode> stk = new Stack<TreeNode>();

		while (node != null || !stk.isEmpty()) {
			while (node != null) {
				stk.push(node);
				node = node.left;
			}
			if (stk.isEmpty()) return;
			node = stk.pop();
			System.out.println(node.val);
			node = node.right;
		}
		return;
	}
	
	/*
	 * This is called Morris traversal. Unlike iterativeSolution, it does not require extra space to traverse the Tree.
	 */
	public List<Integer> inorderTraversal(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		TreeNode cur = root;
		while (cur != null) {
			if (cur.left == null) {
				res.add(cur.val);
				cur = cur.right;
			} else {
				TreeNode node = cur.left;
				while (node.right != null && node.right != cur)
					node = node.right;
				if (node.right == null) {
					node.right = cur;
					cur = cur.left;
				} else {
					node.right = null;
					res.add(cur.val);
					cur = cur.right;
				} 
			}
		}
		return res;
	}
}

public class InorderTraverse {
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
		
		new Solution().recursiveSolution(input);
		new Solution().iterativeSolution(input);
		System.out.println(new Solution().inorderTraversal(input));
	}
}