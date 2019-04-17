/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 28, 2014
 Problem:    Excel Sheet Column Number
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/excel-sheet-column-number/
 Notes:
 Related to question Excel Sheet Column Title
 Given a column title as appear in an Excel sheet, return its corresponding column number.
 For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
 Solution: 1. ...
*/

package excelSheet;

public class ExcelColNumber {
	public static int excelColNumber(String input) {
		if (input.length() == 0) return 0;
		int res = 0;
		for (int i = 0; i < input.length(); i++) {
			res = res * 26 + input.charAt(i) - 'A' + 1;
		}
		return res;
	}
	
	public static void main(String args[]) {
		String input = "YSYEZ";
		System.out.println(ExcelColNumber.excelColNumber(input));
		System.out.println(ExcelColTitle.excelColTitle(11775400));
	}
}
