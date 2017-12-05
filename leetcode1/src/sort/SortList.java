package sort;

/*
Author:     King, wangjingui@outlook.com
Date:       Jan 2, 2015
Problem:    Sort List
Difficulty: Medium
Source:     http://oj.leetcode.com/problems/sort-list/
Notes:
Sort a linked list in O(nlogn) time using constant space complexity.
Solution: merge sort.
*/

import dataStructures.ListNode;
import dataStructures.RandomStruct;

public class SortList {
	public static ListNode sortList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode fast = head, slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = slow.next;
		slow.next = null;
		ListNode L1 = sortList(fast); //We do recursion here.
		ListNode L2 = sortList(head);
		return mergeTwoLists(L1, L2);
	}
	
	public static ListNode mergeTwoLists(ListNode L1, ListNode L2) {
		ListNode head = new ListNode(0);
		ListNode cur = head; //Just let cur = head
		while (L1 != null && L2 != null) {
			if (L1.val < L2.val) {
				cur.next = L1;
				L1 = L1.next;
			} else {
				cur.next = L2;
				L2 = L2.next;
			}
			cur = cur.next;
		}
		if (L1 != null) cur.next = L1;
		if (L2 != null) cur.next = L2;
		return head.next;
	}
	
	public static void main(String args[]) {
		ListNode input = RandomStruct.getRandomList(10, 20);
		ListNode head = input;
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
		head = SortList.sortList(input);
		System.out.println("*****");
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
}
