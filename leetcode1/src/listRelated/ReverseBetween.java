package listRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Jan 2, 2015
Problem:    Reverse Linked List II
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/reverse-linked-list-ii/
Notes:
Reverse a linked list from position m to n. Do it in-place and in one-pass.
For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,
return 1->4->3->2->5->NULL.
Note:
Given m, n satisfy the following condition:
1 <= m <= n <= length of list.
Solution: in-place & one-pass.
*/

import dataStructures.ListNode;

public class ReverseBetween {
	public static ListNode reverseBetween(ListNode head, int m, int n) {
		int diff = n - m;
		ListNode fast = head, slow = new ListNode(0);
		ListNode dummy = slow;
		dummy.next = head;
		for (int i = 0; i < diff; i++)
			fast = fast.next;
		for (int i = 0; i < m - 1; i++) {
			slow = slow.next;
			fast = fast.next;
		}
		ListNode last = fast.next;
		fast.next = null;
		ListNode cur = slow.next;
		System.out.println(cur.val+","+last.val);
		while (cur.next != null) {
			ListNode tmp = cur;
			cur = cur.next;
			tmp.next = cur.next;
			cur.next = slow.next;
			slow.next = cur;
			cur = tmp;
		}
		cur.next = last;
		return dummy.next;
	}
	
	public static ListNode reverseBetween_0(ListNode head, int m, int n) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode first = dummy;
		for (int i = 0; i < m - 1; i++) first = first.next;
		ListNode cur = first.next;
		for (int i = 0; i < n - m; i++) {//Just do n - m times.
			ListNode move = cur.next; //Do not move cur, it will be easier.
			cur.next = move.next;
			move.next = first.next;
			first.next = move;
		}
		return dummy.next;
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
		ListNode head = ReverseBetween.reverseBetween_0(input, 1, 5);
		System.out.println("*****");
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
}
