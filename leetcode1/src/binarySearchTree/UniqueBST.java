package binarySearchTree;

/*
Author:     King, higuige@gmail.com
Date:       Oct 07, 2014
Problem:    Unique Binary Search Trees
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/unique-binary-search-trees/
Notes:
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
For example,
Given n = 3, there are a total of 5 unique BST's.
1         3     3      2      1
 \       /     /      / \      \
  3     2     1      1   3      2
 /     /       \                 \
2     1         2                 3
Solution: dp.
*/

import java.util.*;
import dataStructures.TreeNode;

public class UniqueBST {
	public static int numofBST(int n) {
		if (n == 0) return 1;
		if (n <= 2) return n;
		int res = 0;
		for (int i = 0; i < n; i++) {
			res += numofBST(i) * numofBST(n - 1 - i); //one for the root, then left multiply right.
		}
		return res;
	}
	
	public static int numofBST_0(int n) {
		int[] dp = new int[n+1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i-j-1];
			}
		}
		return dp[n];
	}
	
	public static int numofBST_1(int n) {
		if (n < 0) return 0;
		int[] dp = new int[n+1];
		dp[0] = 1; dp[1] = 1;
		for (int i = 2; i <= n; ++i) {
			dp[i] = dp[i-1] * (4 * i - 2) / (i + 1);
		}
		return dp[n];
	}
	

	/*
	 Author:     King, wangjingui@outlook.com
	 Date:       Dec 25, 2014
	 Problem:    Unique Binary Search Trees II
	 Difficulty: Medium
	 Source:     https://oj.leetcode.com/problems/unique-binary-search-trees-ii/
	 Notes:
	 Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
	 For example,
	 Given n = 3, your program should return all 5 unique BST's shown below.
	 1         3     3      2      1
	  \       /     /      / \      \
	   3     2     1      1   3      2
	  /     /       \                 \
	 2     1         2                 3
	 Solution: 1. DFS directly. (from the Internet)
	           2. DP + DFS. (my solution)
	              a. Generate trees for 'n' from 1 to n. (DP)
	              b. When generate trees for n = i, get the left and right subtrees 
	                 by copying tree structures of dp[1...i-1]. (copy tree uses DFS)
	*/
	public static List<TreeNode> generateBST(int n) {
		return generateBSTRe(n, 1);
	}

	public static List<TreeNode> generateBSTRe(int n, int start) {
		List<TreeNode> res = new ArrayList<TreeNode>();
		if (n == 0) return res;
		if (n == 1) {
			TreeNode node = new TreeNode(start);
			res.add(node);
			return res;
		}
		for (int i = 0; i < n; i++) {
			List<TreeNode> left = generateBSTRe(i, start);
			List<TreeNode> right = generateBSTRe(n - 1 - i, start + i + 1);
			int leftsize = Math.max(left.size(), 1);
			int rightsize = Math.max(right.size(), 1);
			for (int j = 0; j < leftsize; j++) {
				for (int k = 0; k < rightsize; k++) {
					TreeNode root = new TreeNode(i + start);//it should be i + start here 
					if (left.size() == 0) 
						root.left = null;
					else
						root.left = left.get(j);
					if (right.size() == 0)
						root.right = null;
					else
						root.right = right.get(k);
					res.add(root);
				}
			}
		}
		return res;
	}
	
	
	public static void main(String args[]) {
		System.out.println(UniqueBST.numofBST(10));
		System.out.println(UniqueBST.numofBST_0(10));
		System.out.println(UniqueBST.numofBST_1(10));
		
		List<TreeNode> res = generateBST(4);
		for (int i = 0; i < res.size(); i++){
			System.out.println(res.get(i).val);
			if (res.get(i).val > 1) System.out.println("*"+res.get(i).left.val);
		}
	}
}
