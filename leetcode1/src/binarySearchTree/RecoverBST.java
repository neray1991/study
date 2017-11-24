package binarySearchTree;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Recover Binary Search Tree
Difficulty: High
Source:     https://oj.leetcode.com/problems/recover-binary-search-tree/
Notes:
Two elements of a binary search tree (BST) are swapped by mistake.
Recover the tree without changing its structure.
Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
Solution: 1. recursive solution. O(n) space. get inorder list first.
          2. recursive solution. O(n) space. with only auxiliary two pointers.
          3. Use a stack. Iteration.
          4. Morris inorder traversal. O(1) space. with only auxiliary two pointers.
*/

import dataStructures.TreeNode;
//import java.util.*;

public class RecoverBST {
	public static void recoverBST_1(TreeNode root) {
		if (root == null) return;
		TreeNode cur = root;
		TreeNode[] mis = {null, null};
		int i = 0;
		while (cur != null) {
			if (cur.left == null) {
				if (cur.right.val < cur.val) {
					if (i == 0)
						mis[i++] = cur;
					else
						mis[i] = cur.right;
				}
				cur = cur.right;
			} else {
				TreeNode node = cur.left;
				while (node.right != null && node.right != cur)
					node = node.right;
				if (node.right == null) {
					node.right = cur;
					if (cur.left.val > cur.val) {
						if (i == 0)
							mis[i++] = cur.left;
						else
							mis[i] = cur;
					}
					cur = cur.left;
				} else {
					node.right = null;
					if (cur.right != null && cur.right.val < cur.val) {
						if (i == 0)
							mis[i++] = cur;
						else
							mis[i] = cur.right;
					}
					cur = cur.right;
				}
			}
		}
		int tmp = mis[0].val;
		mis[0].val = mis[1].val;
		mis[1].val = tmp;
	}
	
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);//Should be 5 in BST
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);//Should be 15 in BST
		input.right.right.left = new TreeNode(14);
		
		RecoverBST.recoverBST_1(input);
		new Solution().iterativeSolution(input);
		//System.out.println(new Solution().inorderTraversal(input));
	}
	
}
