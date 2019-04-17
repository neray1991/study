package listRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Jan 02, 2015
Problem:    Insertion Sort List
Difficulty: Easy
Source:     http://oj.leetcode.com/problems/insertion-sort-list/
Notes:
Sort a linked list using insertion sort.
Solution: ...*/

import dataStructures.*;

public class InsertionSortList {
	public static ListNode insertSortList(ListNode head) {
		if (head == null) return null;
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		dummy.next = head;
		ListNode cur = head.next;
		head.next = null;
		/* dummy->head->null, cur->cur.next..., looping through these two link list, insert each cur to the right place. 
		 * 
		 */
		while (cur != null) {
			ListNode tmp = dummy;
			while (tmp.next != null && tmp.next.val <= cur.val) tmp = tmp.next;
			ListNode next = cur.next;
			cur.next = tmp.next;
			tmp.next = cur;
			cur = next;
		}
		return dummy.next;
	}
	
	public static void main(String args[]) {
		ListNode input = RandomStruct.getRandomList(10, 20);
		ListNode head = input;
		while (head != null) {
			System.out.print(head.val+",");
			head = head.next;
		}
		head = InsertionSortList.insertSortList(input);
		System.out.println("*****");
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
}
