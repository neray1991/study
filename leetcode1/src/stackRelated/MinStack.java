package stackRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Nov 14, 2014
Problem:    Min Stack 
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/min-stack/
Notes:
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
   push(x) -- Push element x onto stack.
   pop() -- Removes the element on top of the stack.
   top() -- Get the top element.
   getMin() -- Retrieve the minimum element in the stack.
*/

import java.util.*;

public class MinStack {
	private Stack<Integer> stk = new Stack<Integer>();
	private Stack<Integer> minstk = new Stack<Integer>();
	
	public void push(int x) {
		stk.push(x);
		if (minstk.isEmpty() == true || x < minstk.peek()) {
			minstk.push(x);
		}
	}
	
	public int pop() {
		int x = stk.pop();
		if (minstk.isEmpty() != true && x == minstk.peek())
			minstk.pop();
		return x;
	}
	
	public int top() {
		return stk.peek();
	}
	
	public int getMin() {
		return minstk.peek();
	}
}
