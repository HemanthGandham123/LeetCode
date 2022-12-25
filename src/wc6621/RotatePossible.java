package wc6621;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RotatePossible {

	private int[][] rotate(int[][] matrix) {

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

		return matrix;

	}

	public boolean findRotation(int[][] mat, int[][] target) {
		int[][] tmp = mat;
		for (int i = 0; i < 4; i++) {
			tmp = rotate(tmp);
			boolean flag = true;
			for (int j = 0; j < target.length; j++) {
				if (!Arrays.equals(target[j], tmp[j])) {
					flag = false;
				}
			}
			if (flag)
				return true;
		}

		return false;
	}

	public int reductionOperations(int[] nums) {
		Arrays.sort(nums);
		int count = 0, n = nums.length - 1, i = n, j = n;
		int max = nums[n];
		while (i >= 0) {
			if (nums[i] != max) {
				int p = nums[i];
				max = p;
				for (int k = i + 1; k <= j; k++) {
					count++;
					nums[k] = p;
				}
			}
			i--;
		}
		return count;
	}

}
