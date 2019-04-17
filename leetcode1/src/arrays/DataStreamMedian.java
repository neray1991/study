package arrays;

import java.util.*;

public class DataStreamMedian {
	public static int[] dataStreamMedian(int[] num) {
		if (num == null || num.length == 0) return null;
		int[] rst = new int[num.length];
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();//No need to overide Comparator, cause minHeap is nature ordering.
		/*PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) { //Integer parameters here, not int.
				// TODO Auto-generated method stub
				return y - x;
			}
		});*/
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) { //Integer parameters here, not int.
				// TODO Auto-generated method stub
				return y - x;
			}
		});
		rst[0] = num[0];
		maxHeap.offer(num[0]);
		
		for (int i = 1; i < num.length; i++) {
			int preMedian = maxHeap.peek();
			if (num[i] > preMedian) {
				minHeap.offer(num[i]);
			} else {
				maxHeap.offer(num[i]);
			}
			
			if (maxHeap.size() > minHeap.size() + 1) {
				minHeap.offer(maxHeap.poll());
			} else if (maxHeap.size() < minHeap.size()) {
				maxHeap.offer(minHeap.poll());
			}
			rst[i] = maxHeap.peek();
			System.out.println(minHeap.peek());
		}
		return rst;
	}
	
	public static void main(String args[]) {
		int[] num = {6,5,1,3,2,4,0};
		System.out.println(Arrays.toString(DataStreamMedian.dataStreamMedian(num)));
		final List<Integer> a= new ArrayList<Integer>();
		a.add(5);
		System.out.println(a);
	}
}
