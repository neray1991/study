/* list.c */

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "list.h"

/* list_init */
void list_init(List *list, void (*destroy)(void *data)) {	
		list->size = 0;
		list->destroy = destroy;
		list->head = NULL;
		list->tail = NULL;

		return;
}

/* list_destroy */
void list_destroy(List *list) {
		void	*data;
		
		/* Remove each element */
		while (list_size(list) > 0) {
				if (list_rem_next(list, NULL, (void **)&data) == 0 && list->destroy != NULL) {
						list->destroy(data);
				}
		}
		/* No operations are allowed now, but clear the structure as a precaution. */
		memset(list, 0, sizeof(List));
}

/* list_ins_next */
int list_ins_next(List *list, ListElmt *element, const void *data) {
		ListElmt	*new_element;

		/* Allocate storage for the element. */
		if ((new_element = (ListElmt *)malloc(sizeof(ListElmt))) == NULL) {
				return -1;
		}

		/* Insert the element into the list */
		new_element->data = (void *)data;
		if (element == NULL) {
				if (list_size(list) == 0)
						list->tail = new_element;

				new_element->next = list->head;
				list->head = new_element;
		} else {
				if (element->next == NULL)
						list->tail = new_element;

				new_element->next = element->next;
				element->next = new_element;
		}

		list->size++;
		return 0;
}

/* list_rem_next */
int list_rem_next(List *list, ListElmt *element, void **data) {
		ListElmt	*rem_element;

		if (list->size <= 0) return -1;
		
		if (element == NULL) {
				rem_element = list->head;
				*data = rem_element->data;
				list->head = rem_element->next;
				if (list->tail == rem_element)
						list->tail == NULL;
		} else {
				rem_element = element->next;
				if (rem_element == NULL)
						return -1;
				*data = rem_element->data;
				/*element->next = element->next->next;*/
				element->next = rem_element->next;
				if (rem_element == list->tail)
				/*if (element->next == NULL)*/
						list->tail = element;
		}	
		
		free(rem_element);
		list->size--;
		return 0;
}

int list_reverse(List *list) {
		ListElmt *cur;
		ListElmt *pre;

		pre = list->head;
		if (pre == NULL)
				return -1;
		cur = pre->next;
		pre->next = NULL;
		list->tail = pre;
		list->head = pre;
		while (cur != NULL) {
				pre = cur->next;
				cur->next = list->head;
				list->head = cur;
				cur = pre;
		}
		return 0;
}

int main(void) {
		List *list;
		int array[] = {1, 2, 3, 4, 5, 6};
		int ret = 0;
		int *tmp;
		int i;
		ListElmt *cur;

		list = malloc(sizeof(List));
		if (list == NULL)
				printf("%d malloc failed!\n", __LINE__);
		list_init(list, free);
		tmp = malloc(sizeof(int));
		*tmp = 6;
		ret = list_ins_next(list, NULL, tmp);
		printf("%d ret = %d\n", __LINE__, ret);
		tmp = malloc(sizeof(int));
		*tmp = 5;
		ret = list_ins_next(list, NULL, tmp);
		printf("%d ret = %d\n", __LINE__, ret);
		printf("%d head->data = %d\n", __LINE__, *(int *)(list_head(list)->data));
		printf("%d tail->data = %d\n", __LINE__, *(int *)(list_tail(list)->data));
		ret = list_rem_next(list, list->head, (void **)&tmp);
		printf("%d head->data = %d\n", __LINE__, *(int *)(list_head(list)->data));
		printf("%d tail->data = %d\n", __LINE__, *(int *)(list_tail(list)->data));
		list_destroy(list);
		list_init(list, free);
		for (i = 0; i < 10; i++) {
			tmp = malloc(sizeof(int));
			*tmp = i;
			ret = list_ins_next(list, NULL, tmp);
		}
		cur = list->head;
		for (i = 0; i < list->size; i++) {
			printf("i=%d, data=%d\n", i, *(int *)(cur->data));
			cur = cur->next;
		}
		printf("***************\n");
		list_reverse(list);
		
		cur = list->head;
		for (i = 0; i < list->size; i++) {
			printf("i=%d, data=%d\n", i, *(int *)(cur->data));
			cur = cur->next;
		}

}
