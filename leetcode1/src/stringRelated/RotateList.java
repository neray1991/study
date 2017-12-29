package stringRelated;


/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 25, 2014
 Problem:    Rotate List
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/rotate-list/
 Notes:
 Given a list, rotate the list to the right by k places, where k is non-negative.
 For example:
 Given 1->2->3->4->5->NULL and k = 2,
 return 4->5->1->2->3->NULL.
 Solution: Notice that k can be larger than the list size (k % list_size).
           This solution traverses the list twice at most.
*/

import dataStructures.ListNode;

public class RotateList {
	public static ListNode rotateList(ListNode head, int k) { //This is not right!
		if (head == null) return null;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = head;
		int size = 1;
		while (k-- != 0) {
			if (cur.next == null) {
				k = k % size;//k is k here, when it get to the beginning of the loop, everything will be the same as the first loop. So don't minus one here.
				if (k == 0) return head;
				cur = dummy;
			}
			cur = cur.next;
			size++;
		}
		dummy.next = cur.next;
		cur.next = null;
		cur = dummy;
		while (cur.next != null) cur = cur.next;
		cur.next = head;
		return dummy.next;
	}
	
	public static ListNode rotateList_0(ListNode head, int k) {
		if (head == null) return null;
		int size = 1;
		ListNode tail = head, cur = head;
		while (tail.next != null) {
			tail = tail.next;
			size++;
		}
		k = k % size;
		if (k == 0) return head;
		for (int i = 1; i < size - k; i++) {
			cur = cur.next;
		}
		ListNode newhead = cur.next;
		cur.next = null;
		tail.next = head;
		return newhead;
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
		
		ListNode head = RotateList.rotateList_0(input, 2);
		System.out.println("*****");
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
}
