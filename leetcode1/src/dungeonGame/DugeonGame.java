package dungeonGame;
import java.util.Arrays;

/*
 Author:     King, wangjingui@outlook.com
 Date:       Jan 6, 2015
 Problem:    Dungeon Game
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/dungeon-game/
 Notes:
 The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. 
 Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) 
 or contain magic orbs that increase the knight's health (positive integers).
 In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 -2 (K)  -3  3
 -5  -10 1
 10  30  -5 (P)
 Notes:
 The knight's health has no upper bound.
 Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 Solution: 
 Fortunately, this problem can be solved through a table-filling Dynamic Programming technique, similar to other "grid walking" problems:
To begin with, we should maintain a 2D array D of the same size as the dungeon, where D[i][j] represents the minimum health that guarantees the survival of the knight 
for the rest of his quest BEFORE entering room(i, j). Obviously D[0][0] is the final answer we are after. Hence, for this problem, we need to fill the table from the 
bottom right corner to left top.
Then, let us decide what the health should be at least when leaving room (i, j). There are only two paths to choose from at this point: (i+1, j) and (i, j+1). 
Of course we will choose the room that has the smaller D value, or in other words, the knight can finish the rest of his journey with a smaller initial health. 

Therefore we have:
  min_HP_on_exit = min(D[i+1][j], D[i][j+1])
Now D[i][j] can be computed from dungeon[i][j] and min_HP_on_exit based on one of the following situations:
If dungeon[i][j] == 0, then nothing happens in this room; the knight can leave the room with the same health he enters the room with, i.e. D[i][j] = min_HP_on_exit.
If dungeon[i][j] < 0, then the knight must have a health greater than min_HP_on_exit before entering (i, j) in order to compensate for the health lost in this room. 
The minimum amount of compensation is "-dungeon[i][j]", so we have D[i][j] = min_HP_on_exit - dungeon[i][j].
If dungeon[i][j] > 0, then the knight could enter (i, j) with a health as little as min_HP_on_exit - dungeon[i][j], since he could gain "dungeon[i][j]" health in this room. 
However, the value of min_HP_on_exit - dungeon[i][j] might drop to 0 or below in this situation. When this happens, we must clip the value to 1 in order to make sure D[i][j] 
stays positive: D[i][j] = max(min_HP_on_exit - dungeon[i][j], 1).
Notice that the equation for dungeon[i][j] > 0 actually covers the other two situations. We can thus describe all three situations with one common equation, i.e.:
D[i][j] = max(min_HP_on_exit - dungeon[i][j], 1)
for any value of dungeon[i][j].
Take D[0][0] and we are good to go. Also, like many other "table-filling" problems, the 2D array D can be replaced with a 1D "rolling" array here.
 */

import java.util.*;

public class DugeonGame {
	public static int minHP(int[][] dungeon) {
		int M = dungeon.length;
		int N = dungeon[0].length;
		if (M == 0 || N == 0) return 0;
		int[][] dp = new int[M][N];
		if (dungeon[M-1][N-1] < 0)
			dp[M-1][N -1] = 1 - dungeon[M-1][N-1];
		else
			dp[M-1][N-1] = 1;
		for (int i = M - 2; i >= 0; i--)
			dp[i][N-1] = Math.max(1, dp[i+1][N-1] - dungeon[i][N-1]);
		for (int i = N - 2; i >= 0; i--)
			dp[M-1][i] = Math.max(1, dp[M-1][i+1] - dungeon[M-1][i]);
		for (int i = M - 2; i >= 0; i--)
			for (int j = N - 2; j >= 0; j--) {
				int tmp = Math.min(dp[i][j+1], dp[i+1][j]);
				dp[i][j] = Math.max(1, tmp - dungeon[i][j]);
			}
		return dp[0][0];
	}
	
	public static int minHP_2(int[][] dungeon) {
		int M = dungeon.length;
		int N = dungeon[0].length;
		if (M == 0 || N == 0) return 0;
		List<Integer> res = new ArrayList<Integer>();
		minHPRe(dungeon, 0, 0, 1, 0, res);
	//	System.out.println(res);
		int max = res.get(0);
		for (int i = 0; i < res.size(); i++)
			if (res.get(i) > max)
				max = res.get(i);
		if (max == 1) return 1;
		return 1 - max;
	}
	
	public static void minHPRe (int[][] dungeon, int i, int j, int minHP, int HP, List<Integer> res) {
		HP += dungeon[i][j];
		if (HP < minHP) minHP = HP;
		if (i + 1 >= dungeon.length && j + 1 >= dungeon.length)
			res.add(minHP);
		if (i + 1 < dungeon.length)
			minHPRe(dungeon, i + 1, j, minHP, HP, res);
		if (j + 1 < dungeon[0].length)
			minHPRe(dungeon, i, j + 1, minHP, HP, res);
		
	}
	
	public static void main(String args[]) {
		int row = 5, col = 5;
		int[][] input = new int[row][col]; 
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				input[i][j] = dataStructures.RandomStruct.getRandomInt(20) - 13;
			System.out.println(Arrays.toString(input[i]));
		}
		System.out.println(DugeonGame.minHP(input));
		System.out.println(DugeonGame.minHP_2(input));
	}
}