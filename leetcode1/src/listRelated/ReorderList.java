package listRelated;

/*
 Author:     King, wangjingui@outlook.com
 Date:       Jan 8, 2015
 Problem:    Reorder List
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/reorder-list/
 Notes:
 Given a singly linked list L: L0->L1->...->Ln-1->Ln,
 reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->...
 You must do this in-place without altering the nodes' values.
 For example,
 Given {1,2,3,4}, reorder it to {1,4,2,3}.
 Solution: Reverse the back half first, then reorder.
*/

import dataStructures.ListNode;

public class ReorderList {
	public static ListNode reorderList(ListNode head) {
		if (head == null) return null;
		ListNode cur = head;
		int len = 0;
		while (cur != null) {
			cur = cur.next;
			len++;
		}
		int mid = len / 2;
		ListNode middle = head;
		while (mid-- > 0)
			middle = middle.next;
		ListNode dummy = middle.next;
		if (dummy == null)
			return head;
		cur = dummy.next;
		while (cur != null) {
			dummy.next = cur.next;
			cur.next = middle.next;
			middle.next = cur;
			cur = dummy.next;
		}
		cur = head;
		ListNode cur2 = middle.next;
		middle.next = null;
		while (cur2 != null) {
			ListNode tmp1 = cur.next;
			ListNode tmp2 = cur2.next;
			cur.next = cur2;
			cur2.next = tmp1;
			cur = tmp1;
			cur2 = tmp2;
		}
		return head;
	}
	
	public void reorderList_1(ListNode head) {
		ListNode slow = head, fast = head;
		if (head == null || head.next == null) return;
		while(fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = slow.next;
		slow.next = null;
		slow = head;
		ListNode pre = null;
		while (fast != null) { //Reverse the list from slow.next. Put fast in front of pre each time.
			ListNode next = fast.next;
			fast.next = pre;
			pre = fast;
			fast = next;
		}
		fast = pre;
		while (fast != null) {
			ListNode fastnext = fast.next;
			fast.next = slow.next;
			slow.next = fast;
			fast = fastnext;
			slow = slow.next.next;
		}
	}
	
	public void reorderList_2(ListNode head) {
		ListNode slow = head, fast = head;
		if (head == null || head.next == null) return;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = slow.next;
		while (fast.next != null) {
			ListNode move = fast.next;
			fast.next = move.next;
			move.next = slow.next;
			slow.next = move;
		}
		fast = slow.next;
		slow.next = null;
		slow = head;
		while (fast != null) {
			ListNode fastnext = fast.next;
			fast.next = slow.next;
			slow.next = fast;
			fast = fastnext;
			slow = slow.next.next;
		}
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
		//cur.next = new ListNode(7);
		ListNode head = input;
		System.out.println("*****");
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
		System.out.println("@@@@@");
		head = ReorderList.reorderList(input);
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
}
