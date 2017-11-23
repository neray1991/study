package listRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 12, 2014
Problem:    Remove Nth Node From End of List
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/remove-nth-node-from-end-of-list/
Notes:
Given a linked list, remove the nth node from the end of list and return its head.
For example,
Given linked list: 1->2->3->4->5, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
Solution: head---back------front------>NULL
                  |          |
                  ---> n <----
*/

import dataStructures.ListNode;
import dataStructures.RandomStruct;

public class RemoveNthNodefromEnd {
	public static ListNode removeNthNodefromEnd(ListNode head, int n) {
		if (head == null) return null;
		ListNode front = head;
		//ListNode back = head; //Be careful, we may delete head here!
		ListNode back = new ListNode(0);
		back.next = head;
		ListNode dummy = back;
		while (n-- > 1) { //n-- > 1, we starts from head, not dummy!!! Count carefully.
			front = front.next;
			if (front == null)
				return head;
		}
		while (front.next != null) { //If we are gonna return dummy.next, we have to use front.next here.
			back = back.next;
			front = front.next;
		}
		back.next = back.next.next;
		return dummy.next;
	}
	
	public static void main(String args[]) {
		ListNode input = RandomStruct.getRandomList(10, 20);
		ListNode head = input;
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
		System.out.println("****");
		ListNode res = RemoveNthNodefromEnd.removeNthNodefromEnd(input, 1);
		head = res;
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
		System.out.println("****");
	}
}
