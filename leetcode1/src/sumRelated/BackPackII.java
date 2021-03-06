package sumRelated;

/*
Given n items with size Ai and value Vi, and a backpack with size m. 
What's the maximum value can you put into the backpack?
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. 
The maximum value is 9.
Note
You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
Challenge
O(n x m) memory is acceptable, can you do it in O(m) memory?
Tags Expand 
LintCode Copyright Dynamic Programming Backpack
*/


public class BackPackII {
	public static int backPackII(int m, int[] A, int[] V) {
		if (A == null || V == null || A.length == 0 || V.length == 0 || A.length != V.length)
			return 0;
		int[] dp = new int[m + 1]; //Remember it's m+1 here
		dp[0] = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = m; j >0; j--) {
				if (j - A[i] >= 0 && dp[j - A[i]] + V[i] > dp[j]) 
					dp[j] = dp[j - A[i]] + V[i];
			}
		}
		return dp[m];
	}
	public static void main(String args[]) {
		int[] A = {2,3,5,7};
		int[] V = {1,5,2,4};
		System.out.println(BackPackII.backPackII(10, A, V));
	}
}
