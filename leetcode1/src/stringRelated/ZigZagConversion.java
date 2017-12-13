package stringRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 13, 2014
Problem:    ZigZag Conversion
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/zigzag-conversion/
Notes:
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
Solution: ...
*/

public class ZigZagConversion {
	public static String zigZagConvert(String s, int row) {
		int n = s.length();
		if (n <= 2 || row <= 1) return s;
		int k = row * 2 - 2;
		int set = (n - 1) / k; //Should be n-1 here, cause the last idx is n-1;
		StringBuffer res = new StringBuffer();
		for (int j = 0; j < row; j++) {
			for (int i = 0; i <= set; i++){ //should be <= here, to count the mod part
				for (int l = 0; l < k; l++) {
					if (l == j || (l == k - j && l >= row))
						if (i * k + l < n)
							res.append(s.charAt(i * k + l));
				}
			}
		}
		return res.toString();
	}
	
	public static void main(String args[]) {
		System.out.println(ZigZagConversion.zigZagConvert("PAYPALISHIRING", 3));
	}
}
