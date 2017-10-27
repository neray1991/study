package gasStation;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Jan 3, 2015
Problem:    Gas Station
Difficulty: Easy
Source:     http://oj.leetcode.com/problems/gas-station/
Notes:
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
The solution is guaranteed to be unique.
Solution: ...
*/

public class GasStation {
	public static int gasStation(int[] gas, int[] cost) {
		int N = gas.length;
		if (N == 0) return -1;
		int res = 0, sum = 0;
		for (int i = 0; i < N; i++) {
			sum = sum + gas[i] - cost[i];
			if (res == -1)
				if(sum >= 0)
					res = i;
			if (sum < 0)
				res = -1;
		}
		return res;
	}
	
	public static void main(String args[]) {
		int[] gas = {1,1,1,10,1,1,1};
		int[] cost = {2,2,5,2,2,2,2};
		System.out.println(GasStation.gasStation(gas, cost));
		
	}
}

