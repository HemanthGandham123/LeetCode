package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Medium {

	public void setZeroes(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		boolean firstRow = false, firstCol = false;

		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) {
				firstCol = true;
				break;
			}
		}

		for (int i = 0; i < n; i++) {
			if (matrix[0][i] == 0) {
				firstRow = true;
				break;
			}
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0)
					matrix[i][j] = 0;
			}
		}

		for (int i = 0; i < m; i++) {
			if (firstCol == true) {
				matrix[i][0] = 0;
			}
		}

		for (int i = 0; i < n; i++) {
			if (firstRow == true) {
				matrix[0][i] = 0;
			}
		}
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		Map<String, List<String>> mp = new HashMap<>();

		for (int i = 0; i < strs.length; i++) {
			char[] charrArray = strs[i].toCharArray();
			Arrays.sort(charrArray);
			String key = new String(charrArray);
			List<String> anagramList = mp.get(key);
			if (anagramList == null)
				anagramList = new ArrayList<>();
			anagramList.add(strs[i]);
			mp.put(key, anagramList);
		}
		for (Entry<String, List<String>> entry : mp.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
	}
	
	
	
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Set<List<Integer>> numSet = new HashSet<>();
		Map<Integer, List<Integer>> numMap = new HashMap<>();
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			List<Integer> valList = !numMap.containsKey(-1 * nums[i]) ? new ArrayList<>() : numMap.get(-1 * nums[i]);
			valList.add(i);
			numMap.put(-1 * nums[i], valList);
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (numMap.containsKey(nums[i] + nums[j])) {
					List<Integer> mapList = numMap.get(nums[i] + nums[j]);
					int p = -1;
					for (Integer k : numMap.get(nums[i] + nums[j])) {
						if (k != i || k != j) {
							p = k;
						}
					}
					if (p >= 0) {
						List<Integer> tmp = Stream.of(nums[i], nums[j], nums[p]).collect(Collectors.toList());
					}
				}
			}
		}
		res.addAll(numSet);
		return res;
	}
}
