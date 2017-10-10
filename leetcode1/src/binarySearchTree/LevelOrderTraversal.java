
/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Dec 12, 2014
 Problem:    Binary Tree Level Order Traversal
 Difficulty: easy
 Source:     https://oj.leetcode.com/problems/binary-tree-level-order-traversal/
 Notes:
 Given a binary tree, return the level order traversal of its nodes' values. 
 (ie, from left to right, level by level).
 For example:
 Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
 return its level order traversal as:
 [
  [3],
  [9,20],
  [15,7]
 ]
 
 Solution: 1. Use queue. In order to seperate the levels, use 'NULL' as the end indicator of one level.
           2. DFS.
 */

package binarySearchTree;

import java.util.*;
import dataStructures.TreeNode;

class LevelOrder {
	public void levelOrderTraversal(TreeNode root) {
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		if (root != null) list.add(root);
		while (!list.isEmpty()) {
			ArrayList<TreeNode> next = new ArrayList<TreeNode>();
			for (int i=0; i<list.size(); i++) {
				TreeNode current = list.get(i);
				System.out.println(current.val);
				if (current.left != null)
					next.add(current.left);
				if (current.right != null)
					next.add(current.right);
			}
			list.clear();
			list.addAll(next);
		}
	}
	
	public List<List<Integer>> levelOrder_1(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null) return res;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		q.offer(null);
		List<Integer> level = new ArrayList<Integer>();
		
		while(true) {
			TreeNode node = q.poll();
			if (node != null) {
				level.add(node.val);
				if(node.left != null) q.offer(node.left);
				if(node.right != null) q.offer(node.right);
			} else {
				res.add(level);
				level = new ArrayList<Integer>();
				if(q.isEmpty()) break;
				q.offer(null);
			}
		}
		return res;
	}
	
	public List<List<Integer>> levelOrder_2(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(root == null) return res;
		levelOrderRe(root, 0, res);
		return res;
	}
	
	void levelOrderRe(TreeNode root, int level, List<List<Integer>> res) {
		if(root == null) return;
		if(level == res.size()) res.add(new ArrayList<Integer>());
		res.get(level).add(root.val);
		levelOrderRe(root.left, level+1, res);
		levelOrderRe(root.right, level+1, res);
	}
}

public class LevelOrderTraversal {
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(5);
		input.right = new TreeNode(13);
		input.right.left = new TreeNode(12);
		input.right.right = new TreeNode(15);
		input.right.right.left = new TreeNode(14);
		
		new LevelOrder().levelOrderTraversal(input);
		List<List<Integer>> res = new LevelOrder().levelOrder_1(input);
		System.out.println(res);
		List<List<Integer>> res2 = new LevelOrder().levelOrder_2(input);
		System.out.println(res2);
	}
}
