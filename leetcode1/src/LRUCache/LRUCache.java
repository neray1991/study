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
import java.util.*;
import dataStructures.RandomStruct;

/*
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
*/
public class LRUCache<K, V>{

    // Define Node with pointers to the previous and next items and a key, value pair
    class Node<T, U> {
        Node<T, U> previous;
        Node<T, U> next;
        T key;
        U value;

        public Node(Node<T, U> previous, Node<T, U> next, T key, U value){
            this.previous = previous;
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<K, Node<K, V>> cache;
    private Node<K, V> leastRecentlyUsed;
    private Node<K, V> mostRecentlyUsed;
    private int maxSize;
    private int currentSize;

    public LRUCache(int maxSize){
        this.maxSize = maxSize;
        this.currentSize = 0;
        leastRecentlyUsed = new Node<K, V>(null, null, null, null);
        mostRecentlyUsed = leastRecentlyUsed;
        cache = new HashMap<K, Node<K, V>>();
    }

    public V get(K key){
        Node<K, V> tempNode = cache.get(key);
        if (tempNode == null){
            return null;
        }
        // If MRU leave the list as it is
        else if (tempNode.key == mostRecentlyUsed.key){
            return mostRecentlyUsed.value;
        }

        // Get the next and previous nodes
        Node<K, V> nextNode = tempNode.next;
        Node<K, V> previousNode = tempNode.previous;

        // If at the left-most, we update LRU 
        if (tempNode.key == leastRecentlyUsed.key){
            nextNode.previous = null;
            leastRecentlyUsed = nextNode;
        }

        // If we are in the middle, we need to update the items before and after our item
        else if (tempNode.key != mostRecentlyUsed.key){
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
        }

        // Finally move our item to the MRU
        tempNode.previous = mostRecentlyUsed;
        mostRecentlyUsed.next = tempNode;
        mostRecentlyUsed = tempNode;
        mostRecentlyUsed.next = null;

        return tempNode.value;

    }

    public void put(K key, V value){
        if (cache.containsKey(key)){
            return;
        }

        // Put the new node at the right-most end of the linked-list
        Node<K, V> myNode = new Node<K, V>(mostRecentlyUsed, null, key, value);
        mostRecentlyUsed.next = myNode;
        cache.put(key, myNode);
        mostRecentlyUsed = myNode;

        // Delete the left-most entry and update the LRU pointer
        if (currentSize == maxSize){
            cache.remove(leastRecentlyUsed.key);
            leastRecentlyUsed = leastRecentlyUsed.next;
            leastRecentlyUsed.previous = null;
        }

        // Update cache size, for the first added entry update the LRU pointer
        else if (currentSize < maxSize){
            if (currentSize == 0){
                leastRecentlyUsed = myNode;
            }
            currentSize++;
        }
    }
	public static void main(String args[]) {
		LRUCache<Integer, Integer> cache = new LRUCache(5);
		for (int i = 0; i < 10; i++) {
			int rand1 = RandomStruct.getRandomInt(10);
			int rand2 = RandomStruct.getRandomInt(10);
			cache.put(rand1, rand2);
			System.out.println("***"+rand1+","+rand2);
		}
		System.out.println(cache.get(3));

	}
}