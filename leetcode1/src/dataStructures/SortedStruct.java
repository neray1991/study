package dataStructures;

import java.util.Arrays;

public class SortedStruct {
	public static ListNode getSortedList(int size, int range) {
		int[] array = RandomStruct.getRandomArray(size, range);
		if (array == null) return null;
		Arrays.sort(array);
		ListNode head = new ListNode(array[0]);
		ListNode ptr = head;
		for (int i = 1; i < size; i++) {
			ListNode node = new ListNode(array[i]);
			node.next = null;
			ptr.next = node;
			ptr = ptr.next;
		}
		return head;
	}
}
