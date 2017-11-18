package calculate;


/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 25, 2014
 Problem:    Plus One
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/plus-one/
 Notes:
 Given a number represented as an array of digits, plus one to the number.
 Solution: ...
 */

public class PlusOne {
	public static int[] plusOne(int[] num) {
		if (num.length == 0) return num;
		int carry = 1;
		for (int i = num.length - 1; i >= 0; i--) {
			int tmp = num[i] + carry;
			carry = tmp / 10;
			num[i] = tmp % 10;
		}
		if (carry == 0) return num;
		int[] res = new int[num.length+1];
		res[0] = carry;
		System.arraycopy(num, 0, res, 1, num.length);
		return res;
	}
	
	public static void main(String args[]) {
		int[] num = {9,9,9,9,9};
		for (int i : PlusOne.plusOne(num))
			System.out.println(i);
	}
}
