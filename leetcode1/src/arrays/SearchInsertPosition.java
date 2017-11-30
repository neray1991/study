package arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Search Insert Position
Difficulty: Easy
Source:     http://leetcode.com/onlinejudge#question_35
Notes:
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
You may assume no duplicates in the array.
Here are few examples.
[1,3,5,6], 5 -> 2
[1,3,5,6], 2 -> 1
[1,3,5,6], 7 -> 4
[1,3,5,6], 0 -> 0
Solution: Binary search.
*/


public class SearchInsertPosition {
	public static int searchInsertPosition(int[] num, int k) {
		int n = num.length;
		if (n == 0) return 0;
		int start = 0, end = num.length - 1;
		do {
			int mid = (start + end) / 2;
			System.out.println("mid="+mid);
			if (k == num[mid]) return mid;
			if (k < num[mid]) end = mid;
			else start = mid;
		} while (start < end - 1);
		if (k <= num[start]) return start;
		if (k > num[end]) return end + 1; // Careful here, larger ones must insert after end!
		return start + 1;
	}
	
	public static int searchInsertPosition_0(int[] num, int k) {
		int i = 0, j = num.length - 1;
		while (i <= j) {
			int mid = (i + j) / 2;
			if (num[mid] == k) return mid;
			if (num[mid] < k) i = mid + 1; //This is the key point, we know that it's OK if the target is out of the boundary. 
			else j = mid - 1;
		}
		return i;
	}
	
	public static void main(String args[]) {
		int[] num = {1,3,5,6};
		System.out.println(SearchInsertPosition.searchInsertPosition(num, 7));
	}
}
