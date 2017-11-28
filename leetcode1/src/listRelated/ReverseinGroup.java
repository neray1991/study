package listRelated;


/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Jan 18, 2015
 Problem:    Reverse Nodes in k-Group
 Difficulty: Hard
 Source:     https://oj.leetcode.com/problems/reverse-nodes-in-k-group/
 Notes:
 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 You may not alter the values in the nodes, only nodes itself may be changed.
 Only constant memory is allowed.
 For example,
 Given this linked list: 1->2->3->4->5
 For k = 2, you should return: 2->1->4->3->5
 For k = 3, you should return: 3->2->1->4->5
 Solution: ...
 */

import dataStructures.ListNode;

public class ReverseinGroup {
	public static ListNode reverseinGroup(ListNode head, int k) {
		/*
		 * If only constant memory is allowed, we cannot use recursive function
		 */
		if (head == null || k <= 1) return head;
		
		ListNode cur = head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode start = dummy;
		while (start.next != null) {
			int i = k;
			while (i-- > 1) {
				System.out.println(cur.val);
				cur = cur.next;
				if (cur == null) return dummy.next;
			}
			ListNode first = start;
			ListNode tmp = cur.next;
		//	System.out.println("tmp="+cur.val);
			cur.next = null;
			cur = first.next;
			while (cur.next != null) {
				ListNode next = cur.next;
				cur.next = next.next;
				next.next = first.next;
				first.next = next;
			}
			start = cur;
			cur.next = tmp;	//Reconnect the link
			cur = cur.next;	//point to the first node of next group.
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
		cur.next = new ListNode(7);
		
		ListNode head = ReverseinGroup.reverseinGroup(input, 4);
		System.out.println("*****");
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}
}
