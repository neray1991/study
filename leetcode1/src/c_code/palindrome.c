#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool is_palindrome(char *str) {
		int i, j;

		i = 0;
		j = strlen(str) - 1;
		while (i < j) {
			if (str[i] != str[j])
					return false;
			i++;
			j--;
		}
		return true;
}

int main() {
		char *str1 = "1rabbar10";
		char str2[] = "02rabbar20";
		char *str3;
		int i, j;

		printf("str1 strlen=%lu, sizeof(str1)=%lu\n", strlen(str1), sizeof(str1));
//		str1[0] = 'x';
		printf("str1:%s\n", str1);
		printf("str2 strlen=%lu, sizeof(str2)=%lu\n", strlen(str2), sizeof(str2));
		str2[0] = 'x';
		printf("str2:%s\n", str2);
		str3 = malloc(sizeof(str2));
		memset(str3, 65, sizeof(str2));
		memcpy(str3, str1, strlen(str1));
		printf("str3 strlen=%lu, sizeof(str3)=%lu\n", strlen(str3), sizeof(str3));
		str3[0] = 'x';
		printf("str3:%s\n", str3);

		if (!is_palindrome(str2)) {
			i = 0;
			j = strlen(str2) - 1;
			while (i < j) {
				str2[i] ^= str2[j];
				str2[j] ^= str2[i];
				str2[i] ^= str2[j];
				i++;
				j--;
			}
		}
		printf("str2:%s\n", str2);

		return 0;
}
