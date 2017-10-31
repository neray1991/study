package listRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Nov 28, 2014
Problem:    Intersection of Two Linked Lists 
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/intersection-of-two-linked-lists/
Notes:
Write a program to find the node at which the intersection of two singly linked lists begins.
Hints:
   If the two linked lists have no intersection at all, return null.
   The linked lists must retain their original structure after the function returns.
   You may assume there are no cycles anywhere in the entire linked structure.
   Your code should preferably run in O(n) time and use only O(1) memory.
Solution: Two iteration.
*/

import dataStructures.*;

public class IntersectionofLinkedLists {
	public static ListNode getIntersectNode (ListNode headA, ListNode headB) {
		if (headA == null || headB == null) return null;
		int lenA = 0, lenB = 0;
		ListNode nodeA = headA, nodeB = headB;
		while (nodeA != null) {
			nodeA = nodeA.next;
			lenA++;
		}
		while (nodeB != null) {
			nodeB = nodeB.next;
			lenB++;
		}
		if (lenA >= lenB) {
			int diff = lenA - lenB;
			nodeA = headA;
			while (diff > 0) {
				nodeA = nodeA.next;
				diff--;
			}
			nodeB = headB;
		} else {
			int diff = lenB - lenA;
			nodeB = headB;
			while (diff > 0) {
				nodeB = nodeB.next;
				diff--;
			}
			nodeA = headA;
		}
		while (nodeA != nodeB) {
			nodeA = nodeA.next;
			nodeB = nodeB.next;
		}
		return nodeA;
	}
	
	public static void main(String args[]) {
		ListNode headA = RandomStruct.getRandomList(10, 20);
		ListNode headB = RandomStruct.getRandomList(4, 20);
		ListNode nodeB = headB, nodeA = headA;
		while (nodeB.next != null) {
			nodeB = nodeB.next;
			nodeA = nodeA.next;
		}
		nodeB.next = nodeA.next;
		System.out.println(nodeA.next.val);
		System.out.println(IntersectionofLinkedLists.getIntersectNode(headA, headB).val);
	}
}
