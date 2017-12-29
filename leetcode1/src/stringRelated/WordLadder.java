package stringRelated;

/*
Author:     King, wangjingui@outlook.com
Date:       Dec 20, 2014
Problem:    Word Ladder
Difficulty: High
Source:     https://oj.leetcode.com/problems/word-ladder/
Notes:
Given two words (start and end), and a dictionary, find the length of shortest transformation 
sequence from start to end, such that:
Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
Solution: BFS.
*/
/*Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
[
 ["hit","hot","dot","dog","cog"],
 ["hit","hot","lot","log","cog"]
]
*/

import java.util.*;

public class WordLadder {
	public static int ladderLength(String start, String end, Set<String> dict) {
		if (start.compareTo(end) == 0) return 1;
		int depth = 1;
		Queue<String> cur = new LinkedList<String>();
		cur.offer(start);
		Set<String> visited = new HashSet<String>();
		while (cur.isEmpty() == false) {
			Queue<String> queue = new LinkedList<String>();
			while (cur.isEmpty() == false) { //Check out, there are two while loop, this loop will poll every element in cur. Generated words will be in queue.
				String str = cur.poll();
				char[] word = str.toCharArray();
				for (int i = 0; i < word.length; i++) {
					char before = word[i]; //Remember this line
					for (char ch = 'a'; ch <= 'z'; ch++) {
						word[i] = ch;
						String temp = new String(word);
						if (end.compareTo(temp) == 0) return depth + 1;
						if (dict.contains(temp) && !visited.contains(temp)) {
							queue.offer(temp);
							visited.add(temp);
						}
					}
					word[i] = before;
				}
			}
			cur = queue;
			depth++;
		}
		return 0;
	}
	
	public static List<List<String>> findLadders(String start, String end, Set<String> dict) {
		List<List<String>> res = new ArrayList<List<String>>();
		Set<String> visited = new HashSet<String>();
		Set<String> cur = new HashSet<String>();
		HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
		graph.put(end, new ArrayList<String>());
		cur.add(start);
		boolean found = false;
		while (cur.isEmpty() == false && found == false) {
			Set<String> queue = new HashSet<String>();
			for (Iterator<String> it = cur.iterator(); it.hasNext();) {
				visited.add(it.next());
			}
			for (Iterator<String> it = cur.iterator(); it.hasNext();) {
				String str = it.next();
				char[] word = str.toCharArray();
				for (int i = 0; i < word.length; i++) {
					char before = word[i];	
					for (char ch = 'a'; ch <= 'z'; ch++) {
						if (ch == before) continue;
						word[i] = ch;
						String temp = new String(word); //Remember this way
						if (found == true && end.compareTo(temp) != 0) continue;
						if (end.compareTo(temp) == 0) {
							found = true;
							(graph.get(end)).add(str);
							continue;
						}
						if (dict.contains(temp) == false) continue; //Intermediate, end doesn't have to be in dict.
						if (visited.contains(temp) == false) {
							queue.add(temp);
							if (graph.containsKey(temp) == false) {
								ArrayList<String> l = new ArrayList<String>();
								l.add(str);
								graph.put(temp, l);
							} else {
								(graph.get(temp)).add(str);
							}
						}
					}
					word[i] = before;
				}
			}
			cur = queue;
		}
		System.out.println(found);
		if (found == true) {
			ArrayList<String> path = new ArrayList<String>();
			trace(res, graph, path, start, end);
		}
		return res;
	}
	
	public static void trace(List<List<String>> res,
								HashMap<String, ArrayList<String>> graph,
								ArrayList<String> path,
								String start, String now) {
		path.add(now);
		if (now.compareTo(start) == 0) {
			ArrayList<String> ans = new ArrayList<String>(path);
			Collections.reverse(ans);
			res.add(ans);
			path.remove(path.size() - 1);//Remove now means remove start, one step back cause we need to find all the ways
			return;
		}
		ArrayList<String> cur = graph.get(now);
		for (int i = 0; i < cur.size(); i++) {
			trace(res, graph, path, start, cur.get(i));
		}
		path.remove(path.size() - 1);//Remove now here cause we have checked all the ways out from now.
	}
	
	public static void main(String args[]) {
		String start = "hit", end = "cog";
		Set<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("dog");
		dict.add("log");
		dict.add("lot");
	//	dict.add("cog");
	//	System.out.println(WordLadder.ladderLength(start, end, dict));
		System.out.println(WordLadder.findLadders(start, end, dict));
	}
}
