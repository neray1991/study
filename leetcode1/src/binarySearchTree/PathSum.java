package binarySearchTree;

/*
Author:     Annie Kim, anniekim.pku@gmail.com
Date:       Apr 6, 2013
Update:     Jul 26, 2013
Problem:    Path Sum
Difficulty: easy
Source:     http://www.leetcode.com/onlinejudge
Notes:
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up 
all the values along the path equals the given sum.
For example:
Given the below binary tree and sum = 22,
             5
            / \
           4   8
          /   / \
         11  13  4
        /  \      \
       7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

Solution: Recursion.
*/
import dataStructures.TreeNode; 
import java.util.*;

public class PathSum {
	/*
	 * This is not right!
	 * 1. It must be a root-to-leaf path;
	 * 2. the value can be both positive and negative.
	 */
	public static boolean pathSumRe(TreeNode root, int n, int sum) {
		if (root == null) return false;
		if (n == sum + root.val) return true;
		if (n < sum + root.val) return false;
		if (pathSumRe(root.left, n, sum + root.val))
			return true;
		return pathSumRe(root.right, n, sum + root.val);
	}
	
	public static boolean pathSumRe_0(TreeNode root, int n) {
		if (root == null) return false;
		if (root.left == null && root.right == null) return root.val == n;
		return pathSumRe_0(root.left, n - root.val) || pathSumRe_0(root.right, n - root.val);
	}
	
	/*
	 Author:     King, higuige@gmail.com
	 Date:       Oct 7, 2014
	 Problem:    Path Sum 2
	 Difficulty: easy
	 Source:     https://oj.leetcode.com/problems/path-sum-ii/
	 Notes:
	 Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	 For example:
	 Given the below binary tree and sum = 22,
	              5
	             / \
	            4   8
	           /   / \
	          11  13  4
	         /  \    / \
	        7    2  5   1
	 return
	 [
	   [5,4,11,2],
	   [5,8,4,5]
	 ]
	 
	 Solution: DFS. 
	 */
	public static List<List<Integer>> pathSumII(TreeNode root, int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer>();
		pathSumRe2(root, n, res, path);
		return res;
	}
	
	public static void pathSumRe2(TreeNode root, int n, List<List<Integer>> res, List<Integer> path) {
		if (root == null) return;
		path.add(root.val);
		if (root.left == null && root.right == null && root.val == n) {
			List<Integer> tmp = new ArrayList<Integer>(path);
			res.add(tmp); //When we add tmp here we add a REFERENCE. So we cannot add path here, cause we need to change path in the future.
			path.remove(path.size()-1);
			return;
		}
		pathSumRe2(root.left, n - root.val, res, path);
		pathSumRe2(root.right, n - root.val, res, path);
		path.remove(path.size()-1);
	}
	
	public static void main(String args[]) {
		TreeNode input = new TreeNode(10);
		input.left = new TreeNode(15);
		input.right = new TreeNode(7);
		input.right.left = new TreeNode(8);
		input.right.right = new TreeNode(3);
		input.right.right.left = new TreeNode(5);
		
		System.out.println(PathSum.pathSumRe_0(input, 25));
		System.out.println(PathSum.pathSumII(input, 25));

	}
}
