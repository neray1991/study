package arrays;

import java.util.Arrays;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 25, 2014
Problem:    Rotate Image
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/rotate-image/
Notes:
You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).
Follow up:
Could you do this in-place?
Solution: 1. 123   ->  147   ->   741    (preferable)
             456       258        852
             789       369        963
          2. Rotate one-fourth of the image clockwise.
*/

public class RotateImage {
	public static void rotateImage(int[][] img) {
		int n = img.length;
		if (n < 2) return;
		for (int i = 0; i < n/2; i++){
			for (int j = i; j < (n - 1 - i); j++) {// j < (n - 1 - i) we have leave the last element! It belongs to the other edge.
				int tmp = img[i][j];
				img[i][j] = img[n-1-j][i];
				img[n-1-j][i] = img[n-1-i][n-1-j];
				img[n-1-i][n-1-j] = img[j][n-1-i];
				img[j][n-1-i] = tmp;
			}
		}
	}
	
	public static void rotateImage_2(int[][] matrix) {
		int n = matrix.length;
		if (n <= 1) return;
		for (int i = 0; i < n; i++){
			for(int j = 0; j < i; j++){
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < n/2; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[i][n-1-j];
				matrix[i][n-1-j] = tmp;
			}
		}
	}
	
	public static void main(String args[]) {
		int[][] img = {{1,2,3,4},
					   {5,6,7,8},
					   {9,10,11,12},
					   {13,14,15,16}};
		RotateImage.rotateImage(img);
		for (int i = 0; i < img.length; i++)
			System.out.println(Arrays.toString(img[i]));
		System.out.println("***");
		RotateImage.rotateImage_2(img);
		for (int i = 0; i < img.length; i++)
			System.out.println(Arrays.toString(img[i]));
	}
}
