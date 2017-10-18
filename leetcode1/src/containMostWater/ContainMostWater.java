/*
 Author:     King, wangjingui@outlook.com
 Date:       Dec 20, 2014
 Problem:    Container With Most Water
 Difficulty: Easy
 Source:     https://oj.leetcode.com/problems/container-with-most-water/
 Notes:
 Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
 n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 Note: You may not slant the container.
 Solution: From both sides to the center.
*/

package containMostWater;

import java.util.Arrays;

public class ContainMostWater {
	public static int maxArea(int[] height) {
		int left = 0, right = height.length - 1;
		int res = 0;
		int side[] = {left, right};
		while (left < right ) {
			int water = Math.min(height[left], height[right]) * (right - left);
			//System.out.println(water);
			if (water > res) {
				side[0] = left;
				side[1] = right;
				res = water;
			}
			if (height[left] > height[right]) right--;
			else left++;
		}
		System.out.println(Arrays.toString(side));
		return res;
	}
	
	public int getRandomInt(int top) {
		int random = (int)(top * Math.random());
		return random;
	}
	
	public static void main(String args[]) {
		int size = 10;
		int[] rand = new int[size];
		for (int i = 0; i < size; i++) {
			rand[i] = ((int)(20*(Math.random())));
		}
		System.out.println(Arrays.toString(rand));
		System.out.println(maxArea(rand));
	}
}
