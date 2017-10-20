/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 12, 2014
 Problem:    Convert Sorted List to Binary Search Tree
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 Notes:
 Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 Solution: 1. Recursion. In-order. O(n)
           2. Recursion . Pre-order.
           3. pre-order.
 */
/*
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */

package binarySearchTree;

import java.util.List;

import dataStructures.*;

public class SortedListtoBST {
	ListNode iter;
	public TreeNode sortedListtoBST_1(ListNode head) {
		iter = head;
		int len = 0;
		while (head != null) {
			++len;
			head = head.next;
		}
		return sortedListtoBSTRe1(len);
	}
	
	public TreeNode sortedListtoBSTRe1(int len) {
		if (len == 0) return null;
		int mid = len / 2;
		TreeNode left = sortedListtoBSTRe1(mid);
		/*iter is pointing to root now!*/
		TreeNode root = new TreeNode(iter.val);
		root.left = left;
		iter = iter.next;
		root.right = sortedListtoBSTRe1(len - 1 - mid);
		return root;
	}
	
	public TreeNode sortedListtoBSTRe2 (ListNode start, ListNode end){
		if (start == end ) return null;
		ListNode pre = null;
		ListNode slow = start;
		ListNode fast = start;
		while (fast != end && fast.next != end) {
			fast = fast.next.next;
			slow = slow.next;
		}
		TreeNode node = new TreeNode(slow.val);
		node.left = sortedListtoBSTRe2(start, slow);
		node.right = sortedListtoBSTRe2(slow.next, end);
		return node;
	}
	
	public TreeNode sortedListtoBST_3 (ListNode head) {
		if (head == null) return null;
		if (head.next == null) return new TreeNode(head.val);
		ListNode slow = head;
		ListNode fast = head;
		ListNode pre = null;
		while(fast.next != null && fast.next.next != null) {
			pre = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		fast = slow.next;
		TreeNode node = new TreeNode(slow.val);
		if(pre != null) {
			pre.next = null;
			node.left = sortedListtoBST_3(head);
		}
		node.right = sortedListtoBST_3(fast);
		return node;
	}
	
	public static void main(String args[]) {
		ListNode input = SortedStruct.getSortedList(14, 100);
		TreeNode root = new SortedListtoBST().sortedListtoBST_1(input);
		List<List<Integer>> bst = LevelOrder.levelOrder_1(root);
		System.out.println(bst);
		
		root = new SortedListtoBST().sortedListtoBSTRe2(input, null);
		bst = LevelOrder.levelOrder_1(root);
		System.out.println(bst);
		
		root = new SortedListtoBST().sortedListtoBST_3(input);
		bst = LevelOrder.levelOrder_1(root);
		System.out.println(bst);
	}

}
