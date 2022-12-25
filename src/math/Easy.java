package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Easy {
	public List<String> fizzBuzz(int n) {
		List<String> result = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			String tmp;
			if (i % 15 == 0)
				tmp = "FizzBuzz";
			else if (i % 5 == 0)
				tmp = "Buzz";
			else
				tmp = (i % 3 == 0) ? "Fizz" : String.valueOf(i);
			result.add(tmp);

		}
		return result;
	}

	private boolean endingThree(int n) {
		int temp = n % 10;
		if (temp == 1 || temp == 3 || temp == 9 || temp == 7)
			return true;
		return false;
	}

	public boolean isPowerOfThree(int n) {
		if (n <= 0)
			return false;

		while (n > 1) {
			if (n % 3 != 0 || endingThree(n) == false)
				return false;
			n /= 3;
		}
		return true;
	}

	public int countPrimes(int n) {

		boolean[] prime = new boolean[n + 1];
		for (int i = 2; i <= n; i++)
			prime[i] = true;

		for (int p = 2; p * p <= n; p++) {
			if (prime[p]) {
				for (int i = p * p; i <= n; i += p) {
					prime[i] = false;
				}
			}
		}
		int count = 0;
		for (int i = 2; i < n; i++) {
			count = prime[i] ? count + 1 : count;
		}
		return count;
	}

	public int romanToInt(String s) {
		Map<Character, Integer> mp = new HashMap<Character, Integer>() {
			{
				put('I', 1);
				put('V', 5);
				put('X', 10);
				put('L', 50);
				put('C', 100);
				put('D', 500);
				put('M', 1000);
			}
		};
		int i = 0, value = 0, l = s.length();
		while (i < l) {
			int s1 = mp.get(s.charAt(i));
			i++;
			if (i < l) {
				int s2 = mp.get(s.charAt(i));
				if (s1 >= s2)
					value += s1;
				else {
					value += s2 - s1;
					i++;
				}

			} else
				value += s1;

			System.out.println(i + "." + value);
		}
		return value;
	}
}
