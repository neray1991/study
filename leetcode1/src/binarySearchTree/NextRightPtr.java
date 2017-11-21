package binarySearchTree;

/*
Author:     King, higuige@gmail.com
Date:       Oct 7, 2014
Problem:    Populating Next Right Pointers in Each Node
Difficulty: Easy
Source:     http://leetcode.com/onlinejudge#question_116
Notes:
Given a binary tree
struct TreeLinkNode {
   TreeLinkNode *left;
   TreeLinkNode *right;
   TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.
Note:
You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
     1
   /  \
  2    3
 / \  / \
4  5  6  7
After calling your function, the tree should look like:
     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
Solution: 1. Iterative: Two 'while' loops.
          2. Iterative: Queue. Use extra space.
          3. Recursive: DFS. Defect: Use extra stack space for recursion.
*/

import java.util.*;

/*
* Definition for binary tree with next pointer.
*/
class TreeLinkNode {
     int val;
     TreeLinkNode left, right, next;
     TreeLinkNode(int x) { val = x; }
}


public class NextRightPtr {
	public static void nextRightPtrRe(TreeLinkNode root) {
		if (root == null) return;
		TreeLinkNode tmp = new TreeLinkNode(0);
		tmp.next = null;
		TreeLinkNode cur = tmp;
		while (root != null) {
			if (root.left != null) {
				cur.next = root.left;
				cur = cur.next;
			}
			if (root.right != null) {
				cur.next = root.right;
				cur = cur.next;
			}
			root = root.next;
		}
		nextRightPtrRe(tmp.next);
	}
	
	public static void nextRightPtr_2(TreeLinkNode root) {
		if (root == null) return;
		Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
		q.offer(root);
		TreeLinkNode cur = new TreeLinkNode(-1);
		while (q.isEmpty() != true) {
			TreeLinkNode tmp = q.poll();
			if (tmp.left != null) {
				cur.next = tmp.left;
				cur = cur.next;
				q.offer(tmp.left);
			}
			if (tmp.right != null) {
				cur.next = tmp.right;
				cur = cur.next;
				q.offer(tmp.right);
			}
			if (tmp.next == null)
				cur = new TreeLinkNode(-1);
		}
	}
	
	private static void levelOrderTraversal(TreeLinkNode root) {
		ArrayList<TreeLinkNode> list = new ArrayList<TreeLinkNode>();
		if (root != null) list.add(root);
		while (!list.isEmpty()) {
			ArrayList<TreeLinkNode> next = new ArrayList<TreeLinkNode>();
			for (int i=0; i<list.size(); i++) {
				TreeLinkNode current = list.get(i);
				System.out.println(current.val);
				if (current.next != null)
					System.out.println(current.next.val);
				else
					System.out.println("null");
				if (current.left != null)
					next.add(current.left);
				if (current.right != null)
					next.add(current.right);
			}
			list.clear();
			list.addAll(next);
		}
	}
	
	public static void main(String args[]) {
		TreeLinkNode input = new TreeLinkNode(10);
		input.left = new TreeLinkNode(5);
		input.right = new TreeLinkNode(13);
		input.right.left = new TreeLinkNode(12);
		input.right.right = new TreeLinkNode(15);
		input.right.right.left = new TreeLinkNode(14);
		
		//NextRightPtr.nextRightPtrRe(input);
		NextRightPtr.nextRightPtr_2(input);
		NextRightPtr.levelOrderTraversal(input);
	}
}
