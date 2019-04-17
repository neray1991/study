#include <stdio.h>

int missing_number(int *a, int size) {
		int i;
		int tmp = 0;

		for (i = 0; i < size; i++) {
				tmp ^= a[i];
				tmp ^= i + 1; //skipped 0, cause we don't need that.
		}
		return tmp;
}

int missing_number_1(int *a, int size) {
		int i;
		int tmp = 0;

		for (i = 1; i < size; i++) {
				if (a[i] - a[i-1] > 1) {
						tmp = a[i - 1] + 1;
						break;
				}
		}
		return tmp;
}
int main(void) {
		int a[] = {0,1,2,3,4,6,7,8,9};

		printf("%d\n", (int)(sizeof(a)/sizeof(int)));
		printf("%d\n", missing_number(a, sizeof(a)/sizeof(int)));
		printf("%d\n", missing_number_1(a, sizeof(a)/sizeof(int)));
}
