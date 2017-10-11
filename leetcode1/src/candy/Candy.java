package candy;

import java.util.*;

class Solution {
	static List<ArrayList<Integer>> allCandies = new ArrayList<ArrayList<Integer>>();
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
}

public class Candy {
	public int getRandomInt(int top) {
		int random = (int)(top * Math.random());
		return random;
	}
	public static void main(String args[]) {
		int size = 20;
		int[] rand = new int[size];
		for (int i = 0; i < size; i++) {
			rand[i] = ((int)(20*(Math.random())));
			//System.out.println(rand[i]);
		}
		System.out.println(Arrays.toString(rand));
		int sum = new Solution().candy(rand);
		System.out.println(sum);
		System.out.println(new Solution().allCandies);
	}
}
