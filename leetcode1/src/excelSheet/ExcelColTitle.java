/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 19, 2014
 Problem:    Excel Sheet Column Title 
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/excel-sheet-column-title/
 Notes:
 Given a non-zero positive integer, return its corresponding column title as appear in an Excel sheet.
 For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
 Solution: 1. Iteration.
           2&3. recursion.
 */

package excelSheet;

public class ExcelColTitle {
	public static String excelColTitle(int input) {
		StringBuffer sb = new StringBuffer();
		while (input > 0) {
			sb.insert(0, (char)((input - 1) % 26 + 'A'));
			input = (input -1) / 26;
		}
		return sb.toString();
	}
}
