package listRelated;

/*
Author:     King, higuige@gmail.com
Date:       Oct 7, 2014
Problem:    Partition List
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/partition-list/
Notes:
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.
For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
Solution: ...
*/

import dataStructures.ListNode;
import dataStructures.RandomStruct;

public class PartitionList {
	//public static void partitionList(ListNode head, int x) {
	public static ListNode partitionList(ListNode head, int x) {
		if (head == null) return head;
		ListNode left = head, right = new ListNode(0);
		ListNode lcur = head, rcur = right;
		while (lcur.next != null) {
			if (lcur.next.val >= x) {
				rcur.next = lcur.next;
				rcur = rcur.next;
				lcur.next = lcur.next.next;
			//	if (lcur.next == null) break;
			} else {
				lcur = lcur.next;
			}
		}

		rcur.next = null;
		//
		rcur = right.next;
		while (rcur != null) {
			System.out.println(rcur.val);
			rcur = rcur.next;
		}
	//	System.out.println("@@head.next="+head.next.val);
		//
		if (head.val >= x) {
			System.out.println("^^"+head.next.val);
			left = head.next;
			head.next = right.next;
			lcur.next = head;
			return left;
		}
		lcur.next = right.next;
		return head;
	}
	
	public static void main(String args[]) {
		ListNode input = RandomStruct.getRandomList(10, 20);
		ListNode head = input;
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
		System.out.println("****");
		ListNode res = PartitionList.partitionList(input, 10);
		head = res;
		//System.out.println("****");
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
}
