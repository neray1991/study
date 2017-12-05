package calculate;

/*
Author:     King, wangjingui@outlook.com
Date:       Apr 18, 2013
Problem:    Sqrt(x)
Difficulty: Easy
Source:     http://leetcode.com/onlinejudge#question_69
Notes:
Implement int sqrt(int x).
Compute and return the square root of x.
Solution: 1. Binary search in range [0, x / 2 + 1].
          2. Newton iteration method. x(i+1) = (x(i) + n/x(i)) / 2.
See my blog (http://www.cnblogs.com/AnnieKim/archive/2013/04/18/3028607.html) for more explanation (in Chinese).
*/

public class Sqrt {
	public static int sqrt_0(int x) {
		int left = 1, right = x / 2;
		if (x < 2) return x;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (x / mid == mid) return mid;
			if (x / mid > mid) left = mid + 1;
			else right = mid - 1;
		}
		return right; //left == right
	}
	
	public static void main(String args[]) {
		System.out.println(Sqrt.sqrt_0(100));
	}
}
