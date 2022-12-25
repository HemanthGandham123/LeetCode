package dp;

import java.util.Arrays;

public class Medium {

	public static int coinChange(int[] coins, int amount) {
		Arrays.sort(coins);
		int[] dp = new int[amount + 1];
		dp[0] = 1;
		for (int i = 1; i <= amount; i++) {
			int tmp = amount + 1;
			for (int j = 0; j < coins.length; j++) {
				if (i >= coins[j] && dp[i - coins[j]] > 0)
					tmp = Math.min(dp[i - coins[j]], tmp);
			}
			dp[i] = (tmp < amount) ? tmp + 1 : -1;
		}
		if (dp[amount] < 0)
			return -1;
		return dp[amount] - 1;
	}

	public static int uniquePaths(int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		for (int[] rows : dp)
			Arrays.fill(rows, 0);
		dp[1][1] = 1;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] += (j > 1) ? dp[i][j - 1] : 0;
				dp[i][j] += (i > 1) ? dp[i - 1][j] : 0;
			}
		}
		return dp[m][n];
	}

	public boolean canJump(int[] nums) {
		int len = nums.length;
		boolean dp[] = new boolean[len];
		dp[0] = true;
		for (int i = 0; i < len; i++) {
			if (!dp[i])
				return false;
			int k = nums[i];
			for (int j = 1; j <= k && i + j < len; j++)
				dp[i + j] = true;
		}
		return true;
	}

	public static void main(String[] args) {
		int[] coins = { 1, 2, 5 };
		int amount = 11;
//		Medium.coinChange(coins, amount);
		System.out.println(Medium.uniquePaths(3, 7));
	}
}
