package binarySearchTree;


/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Jan 28, 2015
 Problem:    Flatten Binary Tree to Linked List
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/flatten-binary-tree-to-linked-list/
 Notes:
 Given a binary tree, flatten it to a linked list in-place.
 For example,
 Given
     1
    / \
   2   5
  / \   \
 3   4   6
 The flattened tree should look like:
 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
 Hints:
 If you notice carefully in the flattened tree, each node's right child points to the next node
 of a pre-order traversal.
 Solution: Recursion. Return the last element of the flattened sub-tree.
 */

import dataStructures.TreeNode;
import dataStructures.ListNode;
import java.util.*;

public class FlattenBinaryTree {
	public static void flattenBinaryTree(TreeNode root) {
		if (root == null) return;
		Stack<TreeNode> stk = new Stack<TreeNode>();
		stk.push(root);
		while(stk.isEmpty() == false) {
			TreeNode cur = stk.pop();
			if (cur.right != null) stk.push(cur.right);
			if (cur.left != null) stk.push(cur.left);
			cur.left = null;
			cur.right = stk.empty() == true ? null : stk.peek();
		}
	}
	
	public static void flatten_2(TreeNode root) {
		if (root == null) return;
		while (root.left != null || root.right != null) {
			if (root.left != null) {
				TreeNode cur = root.left;
				while (cur.right != null)
					cur = cur.right;
				cur.right = root.right;
				root.right = root.left;
				root.left = null;
				root = root.right;
			} else {
				root = root.right;
			}
		}
	}
	
	public static ListNode flattenRe(TreeNode root) {
		if (root == null) return null;
		ListNode head = new ListNode(root.val);
		ListNode res = head;
		head.next = flattenRe(root.left);
		while (head.next != null)
			head = head.next;
		head.next = flattenRe(root.right);
		return res;
	}
	
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.left.right = new TreeNode(6);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
	
		ListNode head = FlattenBinaryTree.flattenRe(input);
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
		//FlattenBinaryTree.flattenBinaryTree(input);
		FlattenBinaryTree.flatten_2(input);
		List<List<Integer>> res = LevelOrder.levelOrder_1(input);
		System.out.println(res);
	}
}
