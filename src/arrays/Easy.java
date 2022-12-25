package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Easy {

	public void rotate(int[] nums, int k) {
		int n = nums.length;
		k %= n;
		for (int i = 0; i < k; i++) {
			nums[n + i] = nums[i];
		}
		for (int i = 0; i < n; i++) {
			nums[i] = nums[i + k];
		}
	}

	public void moveZeroes(int[] nums) {

		int n = nums.length;
		int i = 0, j = 0;
		while (i < n) {
			if (nums[i] == 0) {
				while ((j < i) || (j < n - 1 && nums[j] == 0))
					j++;
				if (j > i) {
					int temp = nums[j];
					nums[j] = nums[i];
					nums[i] = temp;
				}

			}
			i++;
		}
	}

	public int[] intersect(int[] nums1, int[] nums2) {
		int n1 = nums1.length;
		int n2 = nums2.length;
		Map<Integer, Integer> mp = new HashMap<>();
		Map<Integer, Integer> mp2 = new HashMap<>();
		for (int i = 0; i < n1; i++) {
			if (mp.get(nums1[i]) == null)
				mp.put(nums1[i], 1);
			else
				mp.put(nums1[i], mp.get(nums1[i]) + 1);
		}

		for (int i = 0; i < n2; i++) {
			if (mp2.get(nums2[i]) == null)
				mp2.put(nums2[i], 1);
			else
				mp2.put(nums2[i], mp2.get(nums2[i]) + 1);
		}

		List<Integer> list = new ArrayList<>();
		for (java.util.Map.Entry<Integer, Integer> e : mp.entrySet()) {
			Integer key = e.getKey();
			if (mp.get(key) != null && mp2.get(key) != null) {
				Integer value = Math.min(mp.get(key), mp2.get(key));
				while (value-- > 0) {
					list.add(key);
				}
			}
		}
		return list.stream().mapToInt(i -> i).toArray();
	}

	public int[] plusOne(int[] digits) {

		int n = digits.length, m = n;
		int flag = 0;
		do {
			if (digits[n - 1] == 9) {
				digits[n - 1] = 0;
				flag = 1;
			} else {
				digits[n - 1]++;
				flag = 0;
			}
			n--;
		} while (n > 0 && flag == 1);

		if (flag == 1) {
			int result[] = new int[m + 1];
			result[0] = 1;
			for (int k = 1; k <= m; k++) {
				result[k] = 0;
			}
			return result;
		}
		return digits;
	}

	public void rotate(int[][] matrix) {

		int m = matrix.length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < i; j++) {
				int temp = matrix[j][i];
				matrix[j][i] = matrix[i][j];
				matrix[i][j] = temp;
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m / 2; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][m - j - 1];
				matrix[i][m - j - 1] = temp;
			}
		}

	}

	public boolean isValidSudoku(char[][] board) {

		for (int i = 0; i < 9; i++) {
			HashSet<Character> set = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				char c = board[i][j];
				if (c != '.') {
					if (set.contains(c))
						return false;
					set.add(c);
				}
			}
		}

		for (int i = 0; i < 9; i++) {
			HashSet<Character> set = new HashSet<>();
			for (int j = 0; j < 9; j++) {
				char c = board[j][i];
				if (c != '.') {
					if (set.contains(c))
						return false;
					set.add(c);
				}
			}
		}

		for (int k = 0; k < 3; k++) {
			for (int l = 0; l < 3; l++) {
				HashSet<Character> set = new HashSet<>();
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						char c = board[k * 3 + i][l * 3 + j];
						if (c != '.') {
							if (set.contains(c))
								return false;
							set.add(c);
						}
					}
				}
			}
		}

		return true;
	}

	public void test() {
		int[] a1 = { 1, 2, 2, 1 };
		int[] a2 = { 2, 2 };
		intersect(a1, a2);
	}

}
