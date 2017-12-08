package listRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Swap Nodes in Pairs
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/swap-nodes-in-pairs/
Notes:
Given a linked list, swap every two adjacent nodes and return its head.
For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.
Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
Solution: 1. Iterative solution with constant space.
          2. Recursive solution with O(n) space (for practice).
*/

import dataStructures.ListNode;

public class SwapNodesinPairs {
	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode dummy = new ListNode(0), pre = dummy;
		dummy.next = head;
		ListNode cur = head; //You made a mistake here, cur cannot = dummy;
		while (cur != null && cur.next != null) {
			ListNode next = cur.next;
			cur.next = next.next;
			next.next = cur;
			pre.next = next;
			pre = cur;
			cur = cur.next;
		}
		return dummy.next;
	}
	
	public static ListNode swapPairsRe(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode tmp = head.next;
		head.next = tmp.next;
		tmp.next = head;
		head.next = swapPairsRe(head.next);
		return tmp;
	}
	
	public static void main(String args[]) {
		ListNode input = new ListNode(1);
		ListNode cur = input;
		cur.next = new ListNode(2);
		cur = cur.next;
		cur.next = new ListNode(3);
		cur = cur.next;
		cur.next = new ListNode(4);
		cur = cur.next;
		cur.next = new ListNode(5);
		cur = cur.next;
		cur.next = new ListNode(6);
		cur = cur.next;
		cur.next = new ListNode(7);
		
		//ListNode head = SwapNodesinPairs.swapPairs(input);
		ListNode head = SwapNodesinPairs.swapPairsRe(input);
		System.out.println("*****");
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
}
