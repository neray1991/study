package listRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 17, 2014
Problem:    Merge k Sorted Lists
Difficulty: easy
Source:     https://oj.leetcode.com/problems/merge-k-sorted-lists/
Notes:
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Solution: Find the smallest list-head first using minimum-heap(lgk).
          complexity: O(N*KlgK)
*/

import dataStructures.ListNode;
import dataStructures.RandomStruct;

import java.util.*;

public class MergeSortedLists {
	public ListNode mergeKList_1(List<ListNode> lists) {
		int k = lists.size();
		Comparator<ListNode> comp = new Comparator<ListNode>() {
			public int compare(ListNode a, ListNode b) {
				return a.val - b.val;
			}
		};
		
		Queue<ListNode> q = new PriorityQueue<ListNode>(k, comp);
		for (int i = 0; i < k; i++)
			if (lists.get(i) != null)
				q.add(lists.get(i));
		
		ListNode dummy = null;
		if (q.isEmpty() != true)
			 dummy = q.poll();
		else return dummy;
		ListNode cur = dummy;
		while (q.isEmpty() != true) {
			ListNode tmp = q.poll();
			if (tmp.next != null)
				q.add(tmp.next);
			cur.next = tmp;
			cur = cur.next;
		}
		return dummy;
	}
	
	public ListNode merge2Lists (ListNode A, ListNode B) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (A != null || B != null) {
			if (A == null) { 
				cur.next = B;
				break;
			}
			if (B == null) {
				cur.next = A;
				break;
			}
			if (A.val < B.val) {
				cur.next = A;
				A = A.next;
			} else {
				cur.next = B;
				B = B.next;
			}
			cur = cur.next;
		}
		return dummy.next;
	}
	
	public ListNode mergeKList_2(List<ListNode> lists) {
		int k = lists.size();
		List<ListNode> tmp1 = lists;
		while (k > 1) {
			List<ListNode> tmp = new ArrayList<ListNode>();
			for (int i = 0; i < k/2; i++) {
				tmp.add(merge2Lists(tmp1.get(i), tmp1.get(k-i-1)));
			}
			if (k % 2 == 1) { 
				tmp.add(tmp1.get(k/2));
				k = k / 2 + 1;
			} else {
				k = k / 2;
			}
			tmp1 = tmp;
		}
		return tmp1.get(0);
	}
	
	public static void main(String args[]) {
		List<ListNode> lists = new ArrayList<ListNode>();
		List<ListNode> lists2 = new ArrayList<ListNode>();
		for (int i = 0; i < 5; i++) {
			ListNode input = RandomStruct.getRandomList(RandomStruct.getRandomInt(7)+1, 20);
			ListNode head = input;
			while (head != null) {
				System.out.println(head.val);
				head = head.next;
			}
			head = InsertionSortList.insertSortList(input);
			lists.add(head);
			
			ListNode tmp1 = head.next;
			ListNode head2 = new ListNode(head.val);
			ListNode tmp2 = head2;
			while (tmp1 != null) {
				tmp2.next = new ListNode(tmp1.val);
				tmp2 = tmp2.next;
				tmp1 = tmp1.next;
			}
			lists2.add(head2);
			System.out.println("*****");
		}
		ListNode merged = new MergeSortedLists().mergeKList_1(lists);
		System.out.println("*****");
		List<Integer> res = new ArrayList<Integer>();
		while (merged != null) {
			res.add(merged.val);
			merged = merged.next;
		}
		System.out.println(res);
		
		merged = new MergeSortedLists().mergeKList_1(lists2);
		System.out.println("*****");
		res = new ArrayList<Integer>();
		while (merged != null) {
			res.add(merged.val);
			merged = merged.next;
		}
		System.out.println(res);
	}
}
