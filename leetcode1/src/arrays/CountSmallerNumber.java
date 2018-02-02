package arrays;

import java.util.*;

class SegmentTreeNode {
	public int start;
	public int end;
	public int count;
	public SegmentTreeNode left, right;
	
	public SegmentTreeNode(int start, int end) {
		this.start = start;
		this.end = end;
		this.count = 0;
		this.left = null;
		this.right = null;
	}
	
	public static SegmentTreeNode build(int start, int end) {
		if (start > end) return null;
		if (start == end) return new SegmentTreeNode(start, end);
		int mid = (start + end) / 2;
		SegmentTreeNode root = new SegmentTreeNode(start, end);
		root.left = build(start, mid);
		root.right = build(mid + 1, end);
		return root;
	}
	
	public static void modify(SegmentTreeNode root, int index, int count) {
		if (root.start == index && root.end == index) {
			root.count += count;
			return;
		}
		int mid = (root.start + root.end) / 2;
		//System.out.println(root.start+","+root.end+","+index);
		if (index <= mid) modify(root.left, index, count);
		if (index > mid) modify(root.right, index, count);
		root.count = root.left.count + root.right.count;
	}
	
	public static int query(SegmentTreeNode root, int start, int end) {
		if (root.start == start && root.end == end) return root.count;
		int sum = 0;
		int mid = (root.start + root.end) / 2;
		if (end <= mid) 
			sum += query(root.left, start, end);
		else if (start > mid) 
			sum += query(root.right, start, end);
		else if (start <= mid && mid < end) {
			sum += query(root.left, start, mid);
			sum += query(root.right, mid+1, end);
		}
		return sum;
	}
}

public class CountSmallerNumber {
	public static ArrayList<Integer> countSmallerNumber(int[] A, int[] queries) {
		ArrayList<Integer> rst = new ArrayList<Integer>();
		SegmentTreeNode root = SegmentTreeNode.build(0, 10000);
		for (int value : A) {
			SegmentTreeNode.modify(root, value, 1);
		}
		//System.out.println(root.count);
		for (int value : queries) {
			if (value > 0)
				rst.add(SegmentTreeNode.query(root, 0, value - 1)); //Remember to minus 1 here!!!
			else
				rst.add(0);
		}
		return rst;
	}
	
	public static ArrayList<Integer> countSmallerNumberII(int[] A) {
		ArrayList<Integer> rst = new ArrayList<Integer>();
		SegmentTreeNode root = SegmentTreeNode.build(0, 10000);

		for (int i = 0; i < A.length; i++) {
			if (A[i] > 0)
				rst.add(SegmentTreeNode.query(root, 0, A[i] - 1)); //Remember to minus 1 here!!!
			else
				rst.add(0);
			SegmentTreeNode.modify(root, A[i], 1);
		}
		return rst;
	}
	
	public static void main(String args[]) {
		int[] A = {1,2,7,8,5,3};
		int[] queries = {1,8,5};
		System.out.println(CountSmallerNumber.countSmallerNumber(A, queries));
		System.out.println(CountSmallerNumber.countSmallerNumberII(A));
	}
}
