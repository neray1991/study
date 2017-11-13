package insertInterval;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Merge Intervals
Difficulty: Medium
Source:     https://oj.leetcode.com/problems/merge-intervals/
Notes:
Given a collection of intervals, merge all overlapping intervals.
For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
Solution: 1. Sort in ascending order of 'start'.
          2. Traverse the 'intervals', merge or push...
*/

import java.util.*;

public class MergeIntervals {
	public static  List<Interval> mergeIntervals(List<Interval> intervals) {
		Comparator<Interval> comp = new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		};
		ArrayList<Interval> res = new ArrayList<Interval>();
		Collections.sort(intervals, comp);
		System.out.println(intervals);
		Interval cur = new Interval();
		cur.start = intervals.get(0).start;
		cur.end = intervals.get(0).end;
		//int end = cur.end;
		for (int i = 1; i < intervals.size(); i++) {
			Interval tmp = intervals.get(i);
			if (tmp.start <= cur.end) {
				cur.end = Math.max(cur.end, tmp.end);
			} else {
				res.add(cur);
				cur = new Interval(); //Be careful here! If we do not create a new one, we are actually modifying the old one;
				cur.start = tmp.start;
				cur.end = tmp.end;
			}
		}
		res.add(cur);
		return res;
	}
	
	public static  List<Interval> mergeIntervals_0(List<Interval> intervals) {
		Comparator<Interval> comp = new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				return a.start - b.start;
			}
		};
		ArrayList<Interval> res = new ArrayList<Interval>();
		Collections.sort(intervals, comp);
		System.out.println(intervals);
		Interval cur = intervals.get(0);
		//int end = cur.end;
		for (int i = 1; i < intervals.size(); i++) {
			Interval tmp = intervals.get(i);
			if (tmp.start <= cur.end) {
				cur.end = Math.max(cur.end, tmp.end);
			} else {
				res.add(cur);
				cur = intervals.get(i);
			}
		}
		res.add(cur);
		return res;
	}
	
	public static void main(String args[]) {
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1,8));
		intervals.add(new Interval(6,9));
		intervals.add(new Interval(11,13));
		intervals.add(new Interval(2,7));
		intervals.add(new Interval(1,3));
		//Interval newInterval = new Interval(2, 7);
		System.out.println(MergeIntervals.mergeIntervals(intervals));
		System.out.println(MergeIntervals.mergeIntervals_0(intervals));
	}
}
