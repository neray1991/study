package dataStructures;

public class RandomStruct {
	public static int getRandomInt(int top) {
		int random = (int)(top * Math.random());
		return random;
	}
	
	public static int[] getRandomArray(int size, int range) {
		if (size <= 0)
			return null;
		int[] rand = new int[size];
		for (int i = 0; i < size; i++) {
			rand[i] = ((int)(range*(Math.random())));
			System.out.println(rand[i]);
		}
		return rand;
	}
	
	public static ListNode getRandomList(int size, int range) {
		if (size <= 0)
			return null;
		ListNode head = new ListNode((int)(range*Math.random()));
		ListNode ptr = head;
		for (int i = 1; i < size; i++) {
			ListNode node = new ListNode((int)(range*Math.random()));
			node.next = null;
			ptr.next = node;
			ptr = ptr.next;
		}
		return head;
	}
}