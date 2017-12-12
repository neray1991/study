package arrays2D;

import java.util.Arrays;

/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 20, 2014
 Problem:    Valid Sudoku
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/valid-sudoku/
 Notes:
 Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules (http://sudoku.com.au/TheRules.aspx).
 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 Solution: 1. Traverse the Sudoku only once.
           2. Bit manipulation. Use only one bit to represent a number. Space: sizeof(int) * (1+9+9).
 */

public class ValidSudoku {
	public static boolean isSudoku_0(char[][] board) {
		boolean[] used = new boolean[9];
		
		for (int i = 0; i < 9; i++) {
			Arrays.fill(used, false);
			for (int j = 0; j < 9; j++) {
				if (check(board[i][j], used) == false) return false;
			}
			Arrays.fill(used, false);
			for (int j = 0; j < 9; j++) {
				if (check(board[j][i], used) == false) return false;
			}
		}
		
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c <3; c++) {
				Arrays.fill(used, false);
				for (int i = r * 3; i < r * 3 + 3; i++) {
					for (int j = c * 3; j < c * 3 +3; j++)
						if (check(board[i][j], used) == false) return false;
				}
			}
		}
		return true;
	}
	static boolean check(char ch, boolean[] used) {
		if (ch == '.') return true;
		if (used[ch - '1']) return false;
		used[ch-'1'] = true;
		return true;
	}
	
	public static boolean isSudoku(char[][] board) {
		int[] row = new int[9];
		int[] col = new int[9];
		int[] blk = new int[9];
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') continue;
				int tmp = board[i][j] - '1';
				if ((row[i] & (1 << tmp)) != 0) return false;
				if ((col[j] & (1 << tmp)) != 0) return false;
				if ((blk[i/3*3+j/3] & (1 << tmp)) != 0) return false;
				row[i] |= 1 << tmp; //It's |= here, not &=
				col[j] |= 1 << tmp;
				blk[i/3*3+j/3] |= 1 << tmp;
			}
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
		
//		System.out.println(new SudokuSolver().solveSudoku(board));
		System.out.println(ValidSudoku.isSudoku(board));
		System.out.println(ValidSudoku.isSudoku_0(board));
	}
}
