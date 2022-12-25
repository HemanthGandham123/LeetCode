package dp;

public class Easy {

	public int climbStairs(int n) {
		if (n == 1)
			return 1;

		int[] path = new int[n + 1];
		path[1] = 1;
		path[2] = 2;
		for (int i = 3; i <= n; i++) {
			path[i] = path[i - 1] + path[i - 2];
		}

		return path[n];
	}

	public int rob(int[] nums) {
		int n = nums.length;
		if (n == 1)
			return nums[0];
		int max = Math.max(nums[0], nums[1]);
		if (n == 2)
			return max;
		int[] maxR = new int[n];
		maxR[0] = nums[0];
		maxR[1] = max;
		for (int i = 2; i < nums.length; i++) {
			maxR[i] = Math.max(nums[i] + maxR[i - 2], maxR[i - 1]);
			if (maxR[i] > max)
				max = maxR[i];
		}
		return max;
	}

	public int maxProfit(int[] prices) {
		int mrs = 0, n = prices.length;
		int min = prices[0], max = prices[n - 1];
		int[] misf = new int[n];
		int[] maxa = new int[n];
		for (int i = 0; i < n; i++) {
			min = Math.min(min, prices[i]);
			misf[i] = min;

			max = Math.max(max, prices[n - i - 1]);
			maxa[n - i - 1] = max;
		}

		for (int i = 0; i < n; i++) {
			mrs = Math.max(mrs, maxa[i] - misf[i]);
		}

		return mrs;
	}

	public int maxSubArray(int[] nums) {
		int msf = 0, meh = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			msf += nums[i];
			meh = Math.max(meh, msf);
			if (msf < 0)
				msf = 0;
		}
		return meh;
	}

}
