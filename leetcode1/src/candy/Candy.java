package candy;

import java.util.*;

class Solution {
	static List<ArrayList<Integer>> allCandies = new ArrayList<ArrayList<Integer>>();
	
	/*This is my algorithm, it's not right. Please refer to candy_2*/
	public int candy(int[] ratings) {
		ArrayList<Integer> candies = new ArrayList<Integer>();
		candies.add(1);
		int minVal = 1, sum = 1;
		for (int i = 0, j = 0; i < ratings.length - 1; i++, j++) {
			if (ratings[i] < ratings[i+1]) {
				candies.add(candies.get(j) + 1);
				sum += candies.get(j) + 1;
			}
			else if (ratings[i] > ratings[i+1]) {
				candies.add(candies.get(j) - 1);
				sum += candies.get(j) - 1;
				if (candies.get(j) -1 < minVal)
					minVal = candies.get(j) - 1;
			} 
			else {
				if (minVal < 1)
					sum += (1 - minVal) * candies.size();
				allCandies.add(candies);
				candies = new ArrayList<Integer>();
				candies.add(1);
				j = -1;
				sum ++;
			}
		}
		if (minVal < 1)
			sum += (1 - minVal) * candies.size();
		allCandies.add(candies);
		return sum;
	}
	
	/*Not written by me, sth wrong here*/
	public int candy_1(int[] ratings) {
		int N = ratings.length;
		if (N == 0) return 0;
		int[] height = new int[N];
		int res = 0;
		height[0] = 1;
		for (int i = 1; i < N; ++i) {
			height[i] = i;
			if (ratings[i] > ratings[i - 1])
				height[i] = height[i - 1] + 1;
		}
		for (int i = N - 2; i >= 0; --i) {
			if (ratings[i] > ratings[i + 1])
				height[i] = Math.max(height[i], height[i + 1] + 1);
		}
		for (int i = 0; i < N; ++i)
			res += height[i];
		System.out.println(Arrays.toString(height));
		return res;
	}
	
	public int candy_2(int[] ratings) {
		int N = ratings.length;
		if (N == 0) return 0;
		int candy = 1, res = 1;
		int maxVal = 1, maxIdx = 0;
		for (int i = 1; i < N; ++i) {
			if (ratings[i] >= ratings[i - 1]) {
				candy = ratings[i] == ratings[i - 1] ? 1 : candy + 1;
				maxVal = candy;
				maxIdx = i;
			} else {
				if (candy == 1) {
					if (maxVal <= i - maxIdx) {
						++maxVal;
						++res;
					}
					res += i - maxIdx - 1;
				}
				candy = 1;
			}
			res += candy;
		}
		return res;
	}
}

public class Candy {
	public int getRandomInt(int top) {
		int random = (int)(top * Math.random());
		return random;
	}
	public static void main(String args[]) {
		int size = 10;
		int[] rand = new int[size];
		for (int i = 0; i < size; i++) {
			rand[i] = ((int)(20*(Math.random())));
			//System.out.println(rand[i]);
		}
		System.out.println(Arrays.toString(rand));
		int sum = new Solution().candy(rand);
		System.out.println(sum);
		System.out.println(Solution.allCandies);
		sum = new Solution().candy_1(rand);
		System.out.println(sum);
		sum = new Solution().candy_2(rand);
		System.out.println(sum);
	}
}
