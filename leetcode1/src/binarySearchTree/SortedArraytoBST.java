/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Jan 29, 2015
 Problem:    Convert Sorted Array to Binary Search Tree
 Difficulty: Medium
 Source:     https://oj.leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 Notes:
 Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 Solution: Recursion. 1. preorder
                      2. inorder
 */

package binarySearchTree;

import dataStructures.*;
import java.util.*;

public class SortedArraytoBST {
	public static TreeNode sortedArraytoBSTRe(int num[], int left, int right) {
		if (left > right) return null;
		if (left == right) return new TreeNode(num[left]);
		int mid = (left + right) / 2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = sortedArraytoBSTRe(num, left, mid - 1);
		root.right = sortedArraytoBSTRe(num, mid + 1, right);
		return root;
	}
	
	public static void main(String args[]) {
		int[] input = RandomStruct.getRandomArray(20, 100);
		Arrays.sort(input);
		TreeNode root = sortedArraytoBSTRe(input, 0, input.length - 1);
		List<List<Integer>> bst = LevelOrder.levelOrder_1(root);
		System.out.println(bst);
	}
}
