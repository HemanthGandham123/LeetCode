package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Easy {

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			Character ch = s.charAt(i);
			if (ch == '}' && (stack.size() > 0 && stack.peek() == '{'))
				stack.pop();
			else if (ch == ')' && (stack.size() > 0 && stack.peek() == '('))
				stack.pop();
			else if (ch == ']' && (stack.size() > 0 && stack.peek() == '['))
				stack.pop();
			else
				stack.push(ch);
		}
		if (stack.size() == 0)
			return true;
		return false;
	}

	public int missingNumber(int[] nums) {
		int n = nums.length, sum = 0;
		for (int i = 0; i < n; i++)
			sum += nums[i];
		return ((n * (n + 1) / 2) - sum);
	}

	public List<List<Integer>> generate(int numRows) {
		List<Integer> tmp = Arrays.asList(1);
		List<List<Integer>> rs = new ArrayList<List<Integer>>();
		rs.add(tmp);
		if (numRows == 1)
			return rs;
		tmp = Arrays.asList(1, 1);
		rs.add(tmp);
		if (numRows == 2)
			return rs;
		for (int i = 3; i <= numRows; i++) {
			tmp = new ArrayList<>();
			for (int j = 0; j < i; j++) {
				if (j == 0 || j == i - 1)
					tmp.add(1);
				else {
					tmp.add(rs.get(i - 2).get(j) + rs.get(i - 2).get(j - 1));
				}
			}
			rs.add(tmp);
		}

		return rs;
	}

	public int hammingWeight(int n) {
		return Integer.bitCount(n);
	}

	public int hammingDistance(int x, int y) {
		int count = 0;
		int n = x ^ y;
		while (n > 0) {
			count += n & 1;
			n = n >> 1;
		}
		return count;
		// return Integer.bitCount(X^y);
	}

	public int reverseBits(int n) {
		int result = 0;
		for (int i = 0; i < 32; i++) {
			result = result << 1;
			if ((n & 1) == 1)
				result++;
			n = n >> 1;
		}
		return result;
	}

}
