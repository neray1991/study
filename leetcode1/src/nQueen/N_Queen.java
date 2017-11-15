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
		if (row == (1 << n) - 1) {//Only here we find a solution.
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
				solveNQueensRe(n, row | pos, (ld | pos) << 1, (rd | pos) >> 1, sol, res); //Row can also be column here.
				sol.remove(sol.size() - 1);
				
			}
		}
	}
	
	public int totalNQueens_1(int n) {
		int[] board = new int[n];
		Arrays.fill(board, -1);
		int[] res = new int[1];
		totalNQueensRe(n, 0, board, res);
		return res[0];
	}
	
	public void totalNQueensRe(int n, int row, int[] board, int[] res) {
		if (n == row) {
			res[0]++;
			return;
		}
		for (int i = 0; i < n; i++) {
			if (isValid(board, row, i)) {
				board[row] = i;
				totalNQueensRe(n, row + 1, board, res);
				board[row] = -1;
			}
		}
	}
	
	public boolean isValid(int[] board, int row, int col) {
		for (int i = 0; i < row; i++) {
			if (board[i] == col || row - i == Math.abs(col - board[i])) //row - row = +-(col - col) means they are on the same diagonal.
				return false;
		}
		return true;
	}
	
	public int totalNQueens_2(int n) {
		int[] res = new int[1];
		totalNQueensRe2(n, 0, 0, 0, res);
		return res[0];
	}
	
	public void totalNQueensRe2(int n, int row, int ld, int rd, int[] res) {
		if (row == (1<<n) - 1) {
			res[0]++;
			return;
		}
		int avail = ~(row | ld | rd);
		for (int i = n - 1; i >= 0; i--) {
			int pos = 1 << i;
			if ((int)(pos & avail) != 0) {
				totalNQueensRe2(n, row | pos, (ld | pos) << 1, (rd | pos) >> 1, res);
			}
		}
	}
	
	public int totalNQueens_3(int n) {
		int[] a = new int[n];
		Arrays.fill(a, -1);
		int res = 0;
		int row = 0;
		while (row >= 0) {
			if (row == n) {
				res++; row--;
			}
			int i = a[row] == -1 ? 0 : a[row] + 1;
			for (; i < n; i++) {
				if (isValid(a, row, i)) {
					a[row] = i;
					row++;
					break;
				}
			}
			if (i == n) {
				a[row] = -1;
				row--;
			}
		}
		return res;
	}
	
	public static void main(String args[]) {
		List<String[]> res = new N_Queen().solveNQueens(5);
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < res.get(i).length; j++)
				System.out.println(res.get(i)[j]);
			System.out.println("**********");
		}
		System.out.println(new N_Queen().totalNQueens_1(5));
		System.out.println(new N_Queen().totalNQueens_2(5));
		System.out.println(new N_Queen().totalNQueens_3(5));
	}
}
