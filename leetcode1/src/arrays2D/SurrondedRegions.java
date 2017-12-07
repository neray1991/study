package arrays2D;

import java.util.Arrays;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Jan 20, 2015
Problem:    Surrounded Regions
Difficulty: Easy
Source:     http://leetcode.com/onlinejudge#question_130
Notes:
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region .
For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:
X X X X
X X X X
X X X X
X O X X
Solution: Traverse from the boarder to the inside and mark all the 'O's that are not surrounded by 'X' as 'V' (visited).
          1. BFS (queue).
*/

public class SurrondedRegions {
	public static void surrondedRegions(char[][] board) {
		if (board.length < 3 || board[0].length < 3) return;
		int M = board.length, N = board[0].length;
		int level = Math.min(M, N) / 2;
		for (int i = 0; i < N; i++) {
			if (board[0][i] == 'O') {
				board[0][i] = 'V';
				if (board[1][i] == 'O')
					board[1][i] = 'V';
			}
			if (board[M-1][i] == 'O') {
				board[M-1][i] = 'V';
				if (board[M-2][i] == 'O') 
					board[M-2][i] = 'V';
			}
		}
		for (int i = 0; i < M; i++) {
			if (board[i][N-1] == 'O') {
				board[i][N-1] = 'V';
				if (board[i][N-2] == 'O')
					board[i][N-2] = 'V';
			}
			if (board[i][0] == 'O') {
				board[i][0] = 'V';
				if (board[i][1] == 'O')
					board[i][1] = 'V';
			}
		}
		for (int k = 1; k < level; k++) {
			for (int i = k; i < N - k; i++) {
				if (board[k][i] == 'O') {
					if (board[k-1][i] == 'V' || board[k][i+1] == 'V' || board[k][i-1] == 'V') {
						board[k][i] = 'V';
						if (board[k+1][i] == 'O') board[k+1][i] = 'V';
					}
				}
				if (board[M-1-k][i] == 'O')	{
					if (board[M-k][i] == 'V' || board[M-1-k][i+1] == 'V' || board[M-1-k][i-1] == 'V') {
						board[M-1-k][i] = 'V';
						if (board[M-2-k][i] == 'O') board[M-2-k][i] = 'V';
					}
				}
			}
			for (int i = k; i < M - k; i++) {
				if (board[i][k] == 'O') {
					if (board[i][k-1] == 'V' || board[i+1][k] == 'V' || board[i-1][k] == 'V') {
						board[i][k] = 'V';
						if (board[i][k+1] == 'O') board[i][k+1] = 'V';
					}
				}
				if (board[i][N-1-k] == 'O') {
					if (board[i][N-k] == 'V' || board[i+1][N-1-k] == 'V' || board[i-1][N-1-k] == 'V') {
						board[i][N-1-k] = 'V';
						if (board[i][N-2-k] == 'O') board[i][N-2-k] = 'V';			
					}
				}
			}
		}
		if (Math.min(M, N) % 2 == 1) {
			if (M <= N) {
				for (int i = level; i < N - level; i++) {
					if (board[level][i] == 'O')
						if (board[level][i-1] == 'V' || board[level][i+1] == 'V') board[level][i] = 'V';
				}
			} else {
				for (int i = level; i < M - level; i++) {
					if (board[i][level] == 'O')
						if (board[i-1][level] == 'V' || board[i+1][level] == 'V') board[level][i] = 'V';
				}
			}
		}
	}
	
	public static void main(String args[]) {
		char[][] board = new char[4][4];
		board[0] = "XXXX".toCharArray();
		board[1] = "XOOX".toCharArray();
		board[2] = "XXOX".toCharArray();
		board[3] = "XOXX".toCharArray();
		/*
		board[4] = "4..8.3..1".toCharArray();
		board[5] = "7...2...6".toCharArray();
		board[6] = ".6....28.".toCharArray();
		board[7] = "...419..5".toCharArray();
		board[8] = "....8..79".toCharArray();
		*/
		SurrondedRegions.surrondedRegions(board);
		for (int i = 0; i < 4; i++)
			System.out.println(Arrays.toString(board[i]));
	}
}
