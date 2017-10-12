
/*
 Author:     Andy, nkuwjg@gmail.com
 Date:       Jan 13, 2015
 Problem:    Clone Graph
 Difficulty: Medium
 Source:     http://oj.leetcode.com/problems/clone-graph/
 Notes:
 Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 
 OJ's undirected graph serialization:
 Nodes are labeled from 0 to N - 1, where N is the total nodes in the graph.
 We use # as a separator for each node, and , as a separator for each neighbor of the node.
 As an example, consider the serialized graph {1,2#2#2}.
 The graph has a total of three nodes, and therefore contains three parts as separated by #.
 Connect node 0 to both nodes 1 and 2.
 Connect node 1 to node 2.
 Connect node 2 to node 2 (itself), thus forming a self-cycle.
 Visually, the graph looks like the following:
       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 Solution: 1. DFS. 2. BFS.
 */

/*
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

package cloneGraph;

import java.util.*;
import dataStructures.UndirectedGraphNode;

class Solution {
	public UndirectedGraphNode cloneGraph_1(UndirectedGraphNode node) {
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		return cloneGraphRe(node, map);
	}
	
	public UndirectedGraphNode cloneGraphRe(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
		if (node == null) return null;
		if (map.containsKey(node))
			return map.get(node);
		UndirectedGraphNode newnode = new UndirectedGraphNode(node.label);
		map.put(node, newnode);
		for (UndirectedGraphNode cur :node.neighbors) {
			newnode.neighbors.add(cloneGraphRe(cur, map));
		}
		return newnode;
	}
	
	public UndirectedGraphNode cloneGraph_2(UndirectedGraphNode node) {
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		if (node == null) return null;
		queue.offer(node);
		map.put(node, new UndirectedGraphNode(node.label));
		while (!queue.isEmpty()) {
			UndirectedGraphNode cur = queue.poll();
			for (UndirectedGraphNode neighbor : cur.neighbors) {
				if (!map.containsKey(neighbor)) {
					UndirectedGraphNode newnode = new UndirectedGraphNode(neighbor.label);
					System.out.println(newnode.label);
					map.put(neighbor, newnode);
					queue.offer(neighbor);
				}
				map.get(cur).neighbors.add(map.get(neighbor));
			}
		}
		return map.get(node);
	}
}

public class CloneGraph {
	public static void main(String args[]) {
		UndirectedGraphNode node1 = new UndirectedGraphNode(1);
		UndirectedGraphNode node2 = new UndirectedGraphNode(2);
		UndirectedGraphNode node3 = new UndirectedGraphNode(3);
		node1.neighbors.add(node2);
		node1.neighbors.add(node3);
		node2.neighbors.add(node1);
		node2.neighbors.add(node3);
		node3.neighbors.add(node1);
		node3.neighbors.add(node2);
		
		System.out.println(new Solution().cloneGraph_2(node2).label);
	}
}
