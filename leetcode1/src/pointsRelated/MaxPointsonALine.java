package pointsRelated;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Dec 03, 2014
Problem:    Max Points On a Line
Difficulty: Easy
Notes:
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
Solution: 1. hashmap. Time: O(n^2), Space: O(n);
*/

import java.util.*;
import dataStructures.Point;

public class MaxPointsonALine {
	public static int maxPointsonALine(Point[] points) {
		int res = 0;
		int N = points.length;
		for (int i = 0; i < N; i++) {
			HashMap<Double, Integer> map = new HashMap<Double, Integer>();
			int ss = 1, sp = 0;
			for (int j = i + 1; j < N; j++) {
				Double slope = Double.MIN_VALUE;
				if (points[i].x != points[j].x) {
					slope = (double)(points[i].y - points[j].y) / (points[i].x - points[j].x);
					System.out.println("slope="+slope);
					if (slope == -0.0) slope = 0.0;
				} else if (points[i].y == points[j].y) {
					sp++;
					continue;
				}
				int tmp = 2;
				if (map.containsKey(slope) == true)
					tmp = map.get(slope) + 1;
				map.put(slope, tmp);
				ss = Math.max(ss, tmp);
				System.out.println("**"+ss+","+i+","+j);
			}
			res = Math.max(res, ss + sp);
		}
		return res;
	}
	
	public static void main(String args[]) {
		Point[] points = new Point[5];
		points[0] = new Point(1,1);
		points[1] = new Point(2,2);
		points[2] = new Point(1,1);
		points[3] = new Point(2,1);
		points[4] = new Point(2,0);
		
		System.out.println(MaxPointsonALine.maxPointsonALine(points));
	}
}
