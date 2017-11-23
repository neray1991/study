package listRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 12, 2014
Problem:    Remove Duplicates from Sorted List
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/remove-duplicates-from-sorted-list/

Notes:
Given a sorted linked list, delete all duplicates such that each element appear only once.
For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
Solution: 1. Delete duplicates directly.
          2. Copy value first (like Remove Duplicates from Array) and then delete the remaining elements.
*/

import dataStructures.ListNode;
import dataStructures.RandomStruct;

public class RemoveDuplicates {
	public static ListNode removeDuplicates(ListNode head) {
		if (head == null) return null;
		ListNode cur = head;
		while (cur.next != null) {//use cur.next here, otherwise there will be NULL pointer error!
			if (cur.val == cur.next.val) {
				cur.next = cur.next.next;
			} else {
				cur = cur.next;
			}
		}
		return head;
	}
	
	/*
	 Author:     King, wangjingui@outlook.com
	 Date:       Dec 12, 2014
	 Problem:    Remove Duplicates from Sorted List II
	 Difficulty: Medium
	 Source:     https://oj.leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
	 Notes:
	 Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
	 For example,
	 Given 1->2->3->3->4->4->5, return 1->2->5.
	 Given 1->1->1->2->3, return 2->3.
	 Solution: 1. iterative 2. recursive
	 */
	public static ListNode removeDuplicatesII(ListNode head) {
		if (head == null) return null;
		ListNode cur = head;
		ListNode tail = null;
		boolean dup = false;
		while (cur.next != null) {
			if (cur.val == cur.next.val) {
				cur = cur.next;
				dup = true;
			} else {
				if (dup == false) {
					if (tail == null) {
						head = cur;
						tail = cur;
						cur = cur.next;
						continue;
					}
					tail.next = cur;
					tail = cur;
				}
				dup = false;
				cur = cur.next;
			}
		}
		/*
		 * Remember the last element!!!
		 */
		if (dup == false) {     
			if (tail == null)
				return cur;
			tail.next = cur;
		} else {
			tail.next = null;
		}
		return head;
	}
	
	public static void main(String args[]) {
		ListNode input = RandomStruct.getRandomList(10, 10);
		ListNode head = input;
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
		head = InsertionSortList.insertSortList(input);
		ListNode tmp = head;
		System.out.println("*****");
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
		System.out.println("@@@@@@");
		//head = RemoveDuplicates.removeDuplicates(tmp);
		head = RemoveDuplicates.removeDuplicatesII(tmp);
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
}
