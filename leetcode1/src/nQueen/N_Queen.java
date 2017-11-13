package nQueen;

/*
Author:     King, nkuwjg@gmail.com
Date:       Jul 25, 2013
Problem:    N-Queens
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/n-queens/
Notes:
The n-queens puzzle is the problem of placing n queens on an n*n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.
Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
For example,
There exist two distinct solutions to the 4-queens puzzle:
[
[".Q..",  // Solution 1
"...Q",
"Q...",
"..Q."],
["..Q.",  // Solution 2
"Q...",
"...Q",
".Q.."]
]
Solution: Recursion (DFS). Use bit-manipulation solution (See N-QueensII for more details).
*/

import java.util.*;

public class N_Queen {
	public List<String[]> solveNQueens(int n) {
		List<String[]> res = new ArrayList<String[]>();
		List<char[]> sol = new ArrayList<char[]>();
		solveNQueensRe(n, 0, 0, 0, sol, res);
		return res;
	}
	
	public void solveNQueensRe(int n, int row, int ld, int rd, List<char[]> sol, List<String[]> res) {
		if (row == (1 << n) - 1) {
			String[] temp = new String[n];
			for (int i = 0; i < n; i++)
				temp[i] = String.valueOf(sol.get(i));
			res.add(temp);
			return;
		}
		int avail = ~(row | rd | ld);
		for (int i = n - 1; i >= 0; i--) {
			int pos = 1 << i;
			if ((int)(avail & pos) != 0) {
				char[] str = new char[n];
				Arrays.fill(str, '.');
				str[i] = 'Q';
				sol.add(str);
				solveNQueensRe(n, row | pos, (ld | pos) << 1, (rd | pos) >> 1, sol, res);
				sol.remove(sol.size() - 1);
				
			}
		}
	}
	public static void main(String args[]) {
		List<String[]> res = new N_Queen().solveNQueens(8);
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).length; j++)
				System.out.println(res.get(i)[j]);
			System.out.println("**********");
		}
	}
}
