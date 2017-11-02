package listRelated;


/*
 Author:     King, higuige@gmail.com
 Date:       Oct 5, 2014
 Problem:    Linked List Cycle
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/linked-list-cycle/
 Notes:
 Given a linked list, determine if it has a cycle in it.
 Follow up:
 Can you solve it without using extra space?
 Solution: two pointers.
*/
import dataStructures.ListNode;

public class LinkedListCycle {
	public boolean hasCycle (ListNode head) {
		ListNode ptr1 = head, ptr2 = head;
		while (ptr2 != null && ptr2.next != null) { //Don't need to consider ptr1
			ptr1 = ptr1.next;
			ptr2 = ptr2.next.next;
			if (ptr2 == ptr1)
				break;
		}
		if (ptr2 == null || ptr2.next == null) return false;
		return false;
	}
	
	public ListNode detectCycle (ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) break;
		}
		if (fast == null || fast.next == null) return null;
		slow = head;	//fast steps = a+b+c, slow steps = a+b => c = a+b => a+b+c+a = a+2c == a. c is the length of the cycle
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}
}
