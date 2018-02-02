package stringRelated;

import java.util.*;

public class EncodeDecode {

	public static String encode(List<String> lst) {
		if (lst == null || lst.size() == 0) return null;
		StringBuffer rst = new StringBuffer();
		for (String str : lst) {
			rst.append(str.length() + "#" + str);
		}
		return rst.toString();
	}
	
	public static List<String> decode(String s) {
		if (s == null || s.length() == 0) return null;
		List<String> rst = new ArrayList<String>();
		int start = 0;
		while (start < s.length()) {
			int ind = s.indexOf('#', start);
			int leng = Integer.parseInt(s.substring(start, ind));
			start = ind + leng + 1;
			rst.add(s.substring(ind + 1, start));
		}
		return rst;
	}
	
	public static void main(String args[]) {
		List<String> input= new ArrayList<String>();
		input.add("haha");
		input.add("hehe");
		input.add("heihei");
		input.add("xi");
		String enc = EncodeDecode.encode(input);
		System.out.println(enc);
		System.out.println(EncodeDecode.decode(enc));
	}
}
