package stringRelated;

/*
Author:     Andy, nkuwjg@gmail.com
Date:       Jan 11, 2015
Problem:    Simplify Path
Difficulty: Easy
Source:     https://oj.leetcode.com/problems/simplify-path/
Notes:
Given an absolute path for a file (Unix-style), simplify it.
For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
Solution: Add an additional '/' at the end of 'path' for simply detecting the end.
*/

import java.util.*;

public class SimplifyPath {
	public static String simplifyPath(String path) {
		if (path.length() == 0) return "/";
		String res = "/";
		if (path.charAt(0) != '/')  {
			return "/";
		}
		boolean dot = false, dot2 = false;
		boolean slash = true;
		for (int i = 1; i < path.length(); i++)	{
			if (slash == true && path.charAt(i) == '.') {
				dot = true;
				slash = false;
				continue;
			} 
			if (dot == true && path.charAt(i) == '.') {
				dot = false;
				dot2 = true;
				continue;
			}
			if (dot == true && path.charAt(i) == '/') {
				slash = true;
				dot = false;
				continue;
			}
			if (dot2 == true && (path.charAt(i) == '/' || i == path.length() - 1)) {
				if (res.length() > 1) {
					res = res.substring(0, res.length() - 1);
					while (res.length() > 1 && res.charAt(res.length() - 1) != '/') {
						res = res.substring(0, res.length() - 1);
					}
				}
				slash = true;
				dot2 = false;
				continue;
			}
			if (dot == true) {
				res = res + '.';
				dot = false;
			}
			if (dot2 == true) {
				res = res + "..";
				dot2 = false;
			}
			if (path.charAt(i) == '/' && slash == true)
				continue;
			res = res + path.charAt(i);
			if (path.charAt(i) == '/')
				slash = true;
			else
				slash = false;
		}
		if (slash = true && res.length() > 1)
			res = res.substring(0, res.length() - 1);
		return res;
	}
	
	public static String simplifyPath_0(String path) {
		if (path.length() == 0) return "/";
		if (path.charAt(0) != '/') return "/";
		ArrayList<String> dirs = new ArrayList<String>();
		String[] str = path.split("/"); //String.split is using here!
		for (int i = 0; i < str.length; i++) {
			if ((i == 0 || i == str.length - 1) && str[i].compareTo("") == 0) continue; //The first and last split might be "". 
			if (str[i].compareTo("..") == 0) {
				if (dirs.isEmpty() == false) {
					dirs.remove(dirs.size() - 1);
				}
			} else if ((str[i].compareTo(".") != 0) && (str[i].compareTo("") != 0)) { //This line is correct. The above continue is only for the first split and the last split
				dirs.add(str[i]);
			}
		}
		if (dirs.isEmpty() == true) return "/";
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < dirs.size(); i++) {
			res.append("/");
			res.append(dirs.get(i));
		}
		return res.toString();
	}
	
	public static void main(String args[]) {
		String path = "/c.a/.ba/abc/../../././aaa/ab/..//";
		System.out.println(SimplifyPath.simplifyPath(path));
		System.out.println(SimplifyPath.simplifyPath_0(path));
	}
}
