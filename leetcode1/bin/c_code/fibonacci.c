#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LENGTH	256

void add_big_int(char *str1, char *str2, char* res) {
		int i, j, k, c, tmp;
		
		i = strlen(str1) - 1;
		j = strlen(str2) - 1;
		k = i > j ? i + 2 : j + 2;
		res[k--] = '\0';

		c = 0;
		while (i >= 0 || j >= 0) {
			if (i < 0) {
				tmp = str2[j] - '0' + c;
				c = tmp/10;
				res[k] = tmp % 10 + '0';
				k--, j--;
			} else if (j < 0) {
				tmp = str1[i] - '0' + c;
				c = tmp/10;
				res[k] = tmp % 10 + '0';
				k--, i--;
			} else {
				tmp = str1[i] - '0' + str2[j] - '0' + c;
				c = tmp/10;
				res[k] = tmp % 10 + '0';
				k--, i--, j--;
			}
		}
		if (c) {
			res[k] = c + '0';
		} else {
			res[k] = '0';
			memcpy(res, res + 1, strlen(res));
		}
}

char * fibonacci(int n) {
		char *res, *str1, *str2;

		res = malloc(MAX_LENGTH);
		if (n == 0 || n == 1) {
			res[0] = '1';
			res[1] = '\0';
			return res;
		}
		str1 = fibonacci(n - 1);
		str2 = fibonacci(n - 2);
		add_big_int(str1, str2, res);
		free(str1);
		free(str2);
		return res;
}

char * fibonacci_loop(int n) {
		char *k1, *k2, *tmp, *tmp2;
		int i;

		k1 = malloc(MAX_LENGTH);
		k2 = malloc(MAX_LENGTH);
		tmp = malloc(MAX_LENGTH);

		k1[0] = '1';
		k1[1] = '\0';
		k2[0] = '1';
		k2[1] = '\0';

		for (i = 0; i < n; i++) {
			if (i == 0)
				printf("%s, ", k1);
			else {
				add_big_int(k1, k2, tmp);
				tmp2 = k1;
				k1 = k2;
				k2 = tmp;
				tmp = tmp2;
				printf("%s, ", k1);
			}
		}
		free(tmp);
		free(k2);
		return k1;
}

int main() {
		char *res;
		int i, n;
		
		scanf("%d", &n);
		res = fibonacci_loop(n);
		res = fibonacci(n);
		printf("%s!!\n", res);
		for (i = 0; i <= n; i++) {
			//res = fibonacci(i);
			//printf("%s,	", res);
//			free(res);
		}
		return 0;
}
