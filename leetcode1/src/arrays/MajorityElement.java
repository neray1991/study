package arrays;

public class MajorityElement {
	public static int majorityElement(int[] input) {
		int n = input.length;
		if (n == 0) return 0;
		if (n == 1) return input[0];
		int count = 0, res = 0;
		for (int i = 0; i < n; i++) {
			if (count == 0) {
				res = input[i];
				count++;
				continue;
			}
			if (res == input[i])
				count++;
			else count--;
		}
		return res;
	}
	
	public static void main(String args[]) {
		int[] input = {1,2,3,3,3,3,4,4,4,5,3,3};
		System.out.println(MajorityElement.majorityElement(input));
	}
}
