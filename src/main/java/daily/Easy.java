package daily;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Easy {

	public int uniqueMorseRepresentations(String[] words) {
		String[] morseArr = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
				"--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
		Set<String> codeSet = new HashSet<>();
		for (String word : words) {
			String tmp = "";
			for (Character ch : word.toCharArray()) {
				tmp = tmp + morseArr[ch - 'a'];
			}
			codeSet.add(tmp);
		}
		return codeSet.size();
	}

	public int[] answerQueries(int[] nums, int[] queries) {
		Arrays.sort(nums);
		int sum = 0, n = nums.length, m = queries.length;
		int[] cumulativeSum = new int[n];
		int[] result = new int[m];
		for (int i = 0; i < n; i++) {
			sum += nums[i];
			cumulativeSum[i] = sum;
		}
		for (int i = 0; i < m; i++) {
			int queryAmount = queries[i];
			int j = 0, maxElements = 0;
			while (j < n && cumulativeSum[j] <= queryAmount) {
				maxElements++;
				j++;
			}
			result[i] = maxElements;
		}
		return result;
	}

}
