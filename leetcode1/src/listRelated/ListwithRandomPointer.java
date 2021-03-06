/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Jan 13, 2015
 Problem:    Copy List with Random Pointer
 Difficulty: Easy
 Source:     http://oj.leetcode.com/problems/copy-list-with-random-pointer/
 Notes:
 A linked list is given such that each node contains an additional random pointer 
 which could point to any node in the list or null.
 Return a deep copy of the list.
 Solution:  1. HashMap
            2. uses constant extra space.
            3. Recursive. (Stack)-->StackOverflow in Java.
            4. Iterative. (Queue)
*/
/*
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

package listRelated;

import java.util.*;

import dataStructures.RandomStruct;

class RandomListNode {
	int label;
	RandomListNode next, random;
	RandomListNode(int x) {this.label = x;}
}

public class ListwithRandomPointer {
	public RandomListNode copyRandomList_1(RandomListNode head) {
		if (head == null) return null;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode dummy = new RandomListNode(-1);
		RandomListNode curNew = dummy, cur = head;
		while(cur != null) {
			if (map.containsKey(cur) == false) {
				map.put(cur, new RandomListNode(cur.label));
			}
			if (cur.random != null && map.containsKey(cur.random) == false) {
				map.put(cur.random, new RandomListNode(cur.random.label));
			}
			curNew.next = map.get(cur);
			curNew.next.random = map.get(cur.random);
			curNew = curNew.next;
			cur = cur.next;
		}
		return dummy.next;
	}
	
	public RandomListNode copyRandomList_2(RandomListNode head) {
		if (head == null) return null;
		RandomListNode cur = head;
		/*The first loop duplicate every node and link them to a single linked list*/
		while(cur != null) {
			RandomListNode newnode = new RandomListNode(cur.label);
			newnode.next = cur.next;
			cur.next = newnode;
			cur = cur.next.next;
		}
		cur = head;
		/*The second loop builds every random pointer*/
		while(cur != null) {
			if (cur.random != null)
				cur.next.random = cur.random.next;
			cur = cur.next.next;
		}
		RandomListNode dummy = new RandomListNode(-1);
		RandomListNode curNew = dummy;
		cur = head;
		while(cur != null) {
			curNew.next = cur.next;
			curNew = curNew.next;
			cur.next = cur.next.next; /*This line actually break the newly set links and rebuild the original ones*/
			cur = cur.next;
		}
		return dummy.next;
	}
	
	public RandomListNode copyRandomList_3(RandomListNode head) {
		if (head == null) return null;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		return copy(head, map);
	}
	public RandomListNode copy(RandomListNode root, HashMap<RandomListNode, RandomListNode> map) {
		if (root == null) return null;
		if (map.containsKey(root) == true) {
			return map.get(root);
		}
		RandomListNode newnode = new RandomListNode(root.label);
		map.put(root, newnode);
		newnode.next = copy(root.next, map);
		newnode.random = copy(root.random, map);
		return newnode;
	}
	
	public RandomListNode copyRandomList_4(RandomListNode head) {
		if (head == null) return null;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		Queue<RandomListNode> queue = new LinkedList<RandomListNode>();
		queue.offer(head);
		map.put(head, new RandomListNode(head.label));
		while (queue.isEmpty() == false) {
			RandomListNode cur = queue.poll();
			if (cur.next != null && map.containsKey(cur.next) == false) {
				RandomListNode newnode = new RandomListNode(cur.next.label);
				map.put(cur.next, newnode);
				queue.offer(cur.next);
			}
			map.get(cur).next = map.get(cur.next);
			if (cur.random != null && map.containsKey(cur.random) == false) {
				RandomListNode newnode = new RandomListNode(cur.random.label);
				map.put(cur.random, newnode);
				queue.offer(cur.random);
			}
			map.get(cur).random = map.get(cur.random);
		}
		return map.get(head);
	}
	
	public static void main(String args[]) {
		ArrayList<RandomListNode> input = new ArrayList<RandomListNode>();
		for (int i = 1; i < 10; i++) {
			RandomListNode node = new RandomListNode(i);
			input.add(node);
		}
		for (int i = 0; i < input.size(); i++) {
			if (i + 1 == input.size())
				input.get(i).next = null;
			else
				input.get(i).next = input.get(i + 1);
			int rand = RandomStruct.getRandomInt(input.size() - 1);
			if (rand == 0)
				//input.get(i).random = null; No null pointer here to make print easier
				input.get(i).random = input.get(i);
			else
				input.get(i).random = input.get(rand);
		}
		RandomListNode res1 = new ListwithRandomPointer().copyRandomList_1(input.get(0));
		RandomListNode res2 = new ListwithRandomPointer().copyRandomList_2(input.get(0));
		RandomListNode res3 = new ListwithRandomPointer().copyRandomList_3(input.get(0));
		RandomListNode res4 = new ListwithRandomPointer().copyRandomList_4(input.get(0));
		for (int i = 0; i < input.size(); i++) {
			System.out.println("next  :" + input.get(i).label + "," + res1.label+ "," + res2.label + "," + res3.label + "," + res4.label);
			System.out.println("Random:" + input.get(i).random.label + "," + res1.random.label+ "," + res2.random.label + "," + res3.random.label + "," + res4.random.label);
			res1 = res1.next;
			res2 = res2.next;
			res3 = res3.next;
			res4 = res4.next;
		}
	}
}
