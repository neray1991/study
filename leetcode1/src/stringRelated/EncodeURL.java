package stringRelated;

import java.util.*;

public class EncodeURL {
	final String PREFIX = "http://tinyurl.com/";
	final Map<String, String> map = new HashMap<>();
	
	public String encode(String longUrl) {
		final String key = PREFIX + (map.size() + 1);
		map.put(key, longUrl);
		return key;
	}
	
	public String decode(String shortUrl) {
		return map.get(shortUrl);
	}
	
	public static void main(String args[]) {
		EncodeURL encodeUrl = new EncodeURL();
		String en1 = encodeUrl.encode("yes");
		System.out.println(en1);
		System.out.println(encodeUrl.decode(en1));
		en1 = encodeUrl.encode("no");
		System.out.println(en1);
		System.out.println(encodeUrl.decode(en1));
	}
}
