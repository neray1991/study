package arrays2D;

import java.util.Arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Sudoku Solver
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/sudoku-solver/
Notes:
Write a program to solve a Sudoku puzzle by filling the empty cells.
Empty cells are indicated by the character '.'.
You may assume that there will be only one unique solution.
Solution: back-tracking..
*/

public class SudokuSolver {
	boolean solveSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					for (char ch = '1'; ch <= '9'; ch++) {
						board[i][j] = ch;
						if (isSudoku(board, i, j) && solveSudoku(board)) return true;
						board[i][j] = '.';
					}
					return false; //Note where this line is.
				}
			}
		}
		return true; //Note where this line is.
	}
	
	boolean isSudoku(char[][] board, int x, int y) {
		for (int i = 0; i < 9; i++) {
			if (i != y && board[x][i] == board[x][y]) return false;
			if (i != x && board[i][y] == board[x][y]) return false;
		}
		for (int i = x / 3 * 3, j = y / 3 * 3, k = 0; k < 9; k++) {
			int row = i + k / 3, col = j + k % 3;
			if (row == x && col == y) continue; //Careful here.
			if (board[row][col] == board[x][y]) return false;
		}
		return true;
	}
	
	public static void main(String args[]) {
		char[][] board = new char[9][9];
		board[0] = "53..7....".toCharArray();
		board[1] = "6..195...".toCharArray();
		board[2] = ".98....6.".toCharArray();
		board[3] = "8...6...3".toCharArray();
		board[4] = "4..8.3..1".toCharArray();
		board[5] = "7...2...6".toCharArray();
		board[6] = ".6....28.".toCharArray();
		board[7] = "...419..5".toCharArray();
		board[8] = "....8..79".toCharArray();
		
		System.out.println(new SudokuSolver().solveSudoku(board));
		System.out.println(ValidSudoku.isSudoku(board));
		System.out.println(ValidSudoku.isSudoku_0(board));
		for (int i = 0; i < 9; i++)
			System.out.println(Arrays.toString(board[i]));
	}
}
