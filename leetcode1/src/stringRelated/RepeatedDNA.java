package stringRelated;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Feb 3, 2015
Problem:    Repeated DNA Sequences
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/repeated-dna-sequences/
Notes:
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
For example,
Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
Return:
["AAAAACCCCC", "CCCCCAAAAA"].
Solution: ...
*/

import java.util.*;

public class RepeatedDNA {
	public List<String> findRepeatedDNA (String s) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		HashMap<Character, Integer> mole = new HashMap<Character, Integer>();
		mole.put('A', 0); mole.put('C', 0); mole.put('G', 2); mole.put('T', 3);
		List<String> res = new ArrayList<String>();
		if (s.length() < 11) return res;
		int x = 0, i = 0, mask = (1<<20) - 1;
		for (; i < 10; i++) {
			x = (x << 2) | mole.get(s.charAt(i));
		}
	}
}
