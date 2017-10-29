/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 13, 2014
 Problem:    Add Two Numbers
 Difficulty: easy
 Source:     https://oj.leetcode.com/problems/add-two-numbers/
 Notes:
 You are given two linked lists representing two non-negative numbers. 
 The digits are stored in reverse order and each of their nodes contain a single digit. 
 Add the two numbers and return it as a linked list.
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Solution: dummy head...
 */

package calculate;

import dataStructures.ListNode;

public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode a, ListNode b) {
		if (a == null && b == null) return null;
		ListNode head = new ListNode(a.val + b.val);
		ListNode prev = head;
		int carry = 0;
		a = a.next;
		b = b.next;
		while (a != null || b != null) {
			int sum = carry;
			if (a != null) {
				sum += a.val;
				a = a.next;
			}
			if (b != null) { 
				sum += b.val;
				b = b.next;
			}
			carry = sum / 10;
			ListNode res = new ListNode(sum % 10);
			prev.next = res;
			prev = prev.next;
		}
		if (carry > 0) prev.next = new ListNode(carry); 
		return head;
	}
	
	public static void main (String args[]) {
		ListNode a = new ListNode(1);
		a.next = new ListNode(2);
		a.next.next = new ListNode(9);
		
		ListNode b = new ListNode(8);
		b.next = new ListNode(9);
		b.next.next = new ListNode(6);
		b.next.next.next = new ListNode(9);
		
		ListNode res = new AddTwoNumbers().addTwoNumbers(a, b);
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}
}
