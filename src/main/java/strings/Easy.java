package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Easy {

	public void reverseString(char[] s) {
		int n = s.length;
		for (int i = 0; i < n / 2; i++) {
			char temp = s[i];
			s[i] = s[n - i - 1];
			s[n - i - 1] = temp;
		}
	}

	public String longestCommonPrefix(String[] strs) {

		StringBuilder str = new StringBuilder();
		int min = strs[0].length();
		for (int i = 1; i < strs.length; i++) {
			min = Math.min(min, strs[i].length());
		}
		for (int i = 0; i < min; i++) {
			char c = strs[0].charAt(i);
			for (int j = 0; j < strs.length; j++) {
				if (strs[j].charAt(i) != c)
					return str.toString();
			}
			str.append(c);
		}
		return str.toString();

	}

	private Map<Character, Integer> frequencyMap(String str) {
		Map<Character, Integer> mp = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			Integer val = mp.get(str.charAt(i));
			if (val == null) {
				mp.put(str.charAt(i), 1);
			} else {
				mp.put(str.charAt(i), val + 1);
			}
		}
		return mp;
	}

	public boolean isPalindrome(String s) {
		s = s.replaceAll("[^a-zA-Z0-9]", "");
		StringBuilder str = new StringBuilder(s);
		if (s.equalsIgnoreCase(str.reverse().toString()))
			return true;
		return false;
	}

	public boolean isAnagram(String s, String t) {

		Map<Character, Integer> mp1 = frequencyMap(s);
		Map<Character, Integer> mp2 = frequencyMap(t);

		if (mp1.entrySet().containsAll(mp2.entrySet()) && mp2.entrySet().containsAll(mp1.entrySet()))
			return true;

		return false;
	}

	public int reverse(int x) {
		boolean negativeFlag = false;
		if (x < 0) {
			negativeFlag = true;
			x = -x;
		}

		int prev_rev_x = 0, rev_x = 0;
		while (x != 0) {
			int curr_digit = x % 10;

			rev_x = (rev_x * 10) + curr_digit;

			// checking if the reverse overflowed or not.
			// The values of (rev_x - curr_digit)/10 and
			// prev_rev_x must be same if there was no
			// problem.
			if ((rev_x - curr_digit) / 10 != prev_rev_x)
				return 0;

			prev_rev_x = rev_x;
			x = x / 10;
		}

		return (negativeFlag == true) ? -rev_x : rev_x;
	}

	public int firstUniqChar(String s) {
		int result = -1;
		int n = s.length();
		int min = n;
		Map<Character, Integer> mp = new HashMap<>();
		for (int i = 0; i < n; i++) {
			if (mp.containsKey(s.charAt(i)) == true) {
				mp.put(s.charAt(i), -1);
			} else {
				mp.put(s.charAt(i), i + 1);
			}
		}

		for (Entry<Character, Integer> entry : mp.entrySet()) {
			if (entry.getValue() > 0)
				min = Math.min(min, entry.getValue() - 1);
		}
		if (min != n)
			return min;
		return result;
	}

	public int myAtoi(String str) {
		if (str.length() == 0) {
			return 0;
		}

		int idx = 0;
		int n = str.length();
		int sign = 1;
		while (idx < n && str.charAt(idx) == ' ') {
			idx++;
		}

		if (idx < n && (str.charAt(idx) == '-' || str.charAt(idx) == '+')) {
			sign = str.charAt(idx) == '-' ? -1 : 1;
			idx++;
		}

		long num = 0;
		while (idx < n && Character.isDigit(str.charAt(idx))) {
			num = num * 10 + Character.getNumericValue(str.charAt(idx++));

			if ((sign == 1 && num > Integer.MAX_VALUE) || (-num < Integer.MIN_VALUE)) {
				return getOverflowValue(sign);
			}
		}

		return sign * (int) num;
	}

	private int getOverflowValue(int sign) {
		return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
	}

	public String countAndSay(int n) {
		if (n == 1)
			return "1";

		StringBuilder s1 = new StringBuilder("11");
		if (n == 2)
			return s1.toString();

		for (int i = 3; i <= n; i++) {
			StringBuilder s2 = new StringBuilder();
			int j = 0, l = s1.length();
			while (j < l) {
				int count = 0;
				char ch = s1.charAt(j);
				while (j < l && s1.charAt(j) == ch) {
					count++;
					j++;
				}
				s2.append(String.valueOf(count));
				s2.append(ch);
			}
			s1 = new StringBuilder(s2.toString());
		}
		return s1.toString();
	}

	public int strStr(String haystack, String needle) {
		if (needle.length() == 0) {
			return 0;
		}
		int[] kmp = new int[needle.length()];
		computeKMP(needle, kmp);
		int i = 0, j = 0;// i is for haystack and j is for needle
		while (i < haystack.length()) {
			if (j == -1) {
				j = 0;
				i++;
				continue;
			}
			if (haystack.charAt(i) == needle.charAt(j)) {
				if (j == needle.length() - 1) {
					return i - needle.length() + 1;
				}
				i++;
				j++;
			} else {
				j = kmp[j];
			}
		}
		return -1;
	}

	private void computeKMP(String s, int[] kmp) {
		int prefixEnd = -1;
		int suffixEnd = 0;
		kmp[0] = -1;
		// while loop updates kmp[suffixEnd + 1]
		while (suffixEnd < s.length() - 1) {
			if (prefixEnd == -1 || s.charAt(prefixEnd) == s.charAt(suffixEnd)) {
				kmp[suffixEnd + 1] = prefixEnd + 1;
				prefixEnd++;
				suffixEnd++;
			} else {
				prefixEnd = kmp[prefixEnd];
			}
		}
	}

}
