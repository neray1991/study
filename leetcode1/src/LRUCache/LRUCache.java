package LRUCache;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       March 12, 2014
Problem:    LRU Cache
Difficulty: Hard
Source:     http://oj.leetcode.com/problems/lru-cache/
Notes:
Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and set. 
get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Solution: Hash + list.
*/

import java.util.Map;
import java.util.LinkedHashMap;
import dataStructures.RandomStruct;

public class LRUCache {
	private Map<Integer, Integer> map;
	private int capacity;
	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.map = new LinkedHashMap<Integer, Integer>(capacity + 1);
	}
	
	public int get(int key) {
		Integer val = map.get(key);
		if (val == null) return -1;
		map.remove(key);
		map.put(key, val);
		return val;
	}
	
	public void set(int key, int val) {
		map.remove(key);
		map.put(key, val);
		if (map.size() > capacity)
			map.remove(map.entrySet().iterator().next().getKey());
	}
	
	public static void main(String args[]) {
		LRUCache cache = new LRUCache(5);
		for (int i = 0; i < 10; i++) {
			int rand1 = RandomStruct.getRandomInt(10);
			int rand2 = RandomStruct.getRandomInt(10);
			cache.set(rand1, rand2);
			System.out.println("***"+rand1+","+rand2);
		}
		System.out.println(cache.get(3));
	}
}
