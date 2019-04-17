list : list.o
	gcc -o $@ $<
list.o : list.c list.h
	gcc -c -g list.c
missing_number : missing_number.o
	gcc -o $@ $<
missing_number.o : missing_number.c
	gcc -c -g $<

clean:
	rm -rf list missing_number *.o
