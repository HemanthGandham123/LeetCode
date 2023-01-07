package medium;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Solution {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public int minPathSum(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] dp = new int[m][n];
		dp[0][0] = grid[0][0];

		for (int i = 1; i < m; i++)
			dp[i][0] = dp[i - 1][0] + grid[i][0];
		for (int j = 1; j < n; j++)
			dp[0][j] = dp[0][j - 1] + grid[0][j];

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[m - 1][n - 1];
	}

	public int countNumbersWithUniqueDigits(int n) {
		if (n == 0)
			return 1;
		int sum = 10;
		for (int i = 1; i < n; i++) {
			int product = 9;
			for (int k = 0; k < i; k++) {
				product *= 9 - k;
			}
			sum += product;
		}
		return sum;
	}

	private static final int MODULO = (int) (Math.pow(10, 9)) + 7;

	List<List<Integer>> levelOrderList = new ArrayList<>();
	Map<Integer, List<Integer>> lmp = new HashMap<>();

	private void levelTraverse(TreeNode root, int height) {

		if (root == null)
			return;
		List<Integer> list = new ArrayList<>();
		if (lmp.get(height) == null || lmp.get(height).isEmpty()) {
			list = new ArrayList<Integer>(Arrays.asList(root.val));
		} else {
			list = lmp.get(height);
			list.add(root.val);
		}
		lmp.put(height, list);
		levelTraverse(root.left, height + 1);
		levelTraverse(root.right, height + 1);

	}

	public int numRollsToTarget(int n, int k, int target) {

		int dp[][] = new int[n + 1][target + 1];

		for (int i = 1; i <= target; i++) {
			dp[1][i] = (i <= k) ? 1 : 0;
		}
		for (int m = 1; m <= target; m++) {
			for (int i = 2; i <= n; i++) {
				int sum = 0;
				for (int j = 1; j <= k; j++) {
					if (m - j > 0)
						sum = (sum % MODULO + dp[i - 1][m - j] % MODULO) % MODULO;
				}
				dp[i][m] = sum;
			}
		}
		return dp[n][target] % MODULO;

	}

	public String reverseWords(String s) {
		String[] arr = s.split("\\s");
		StringBuilder builder = new StringBuilder();
		for (int i = arr.length - 1; i >= 0; i--) {
			builder.append(arr[i]);
			if (i != 0)
				builder.append(" ");
		}
		return builder.toString();
	}

	public int binarySearch(int[] nums, int target) {
		int l = 0, h = nums.length - 1;
		while (l <= h) {
			int m = l + (h - l) / 2;
			if (nums[m] == target)
				return m;
			else if (nums[m] < target)
				l = m + 1;
			else
				h = m - 1;
		}
		return -1;
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		levelTraverse(root, 0);
		List<List<Integer>> rs = new ArrayList<>();
		for (int i = 0; i < lmp.size(); i++) {
			rs.add(lmp.get(i));
		}
		return rs;
	}

	public int[] searchRange(int[] nums, int target) {
		int index = binarySearch(nums, target);
		int[] res = { -1, -1 };
		int a = index, b = index;
		if (index != -1) {
			int k = nums[index];
			while (a >= 0 && nums[a] == k) {
				res[0] = a;
				a--;
			}
			while (b < nums.length && nums[b] == k) {
				res[1] = b;
				b++;
			}
		}
		return res;
	}

	public List<Integer> rightSideView(TreeNode root) {
		List<List<Integer>> lvlOrder = levelOrder(root);
		List<Integer> rs = new ArrayList<>();
		for (List<Integer> list : lvlOrder) {
			rs.add(list.get(list.size() - 1));
		}
		return rs;
	}

	private class MapComparator implements Comparator<Entry<Character, Integer>> {

		@Override
		public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
			if (o1.getValue() > o2.getValue() || (o1.getValue() == o2.getValue() && o1.getKey() < o2.getKey()))
				return -1;
			return 1;
		}

	}

	public int wateringPlants(int[] plants, int capacity) {
		int res = 0, i = 0, len = plants.length;
		while (i < len) {
			res += i;
			int curr = capacity;
			while (i < len && plants[i] <= curr) {
				curr -= plants[i];
				i++;
			}
			res += i;
		}
		return res;
	}

	private Map<Character, Integer> freqMap(String s) {
		Map<Character, Integer> mp1 = new HashMap<>();
		for (Character ch : s.toCharArray()) {
			mp1.put(ch, mp1.getOrDefault(ch, 0));
		}
		return mp1;
	}

	public int numOfPairs(String[] nums, String target) {
		Map<String, Integer> targetMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			targetMap.put(nums[i], targetMap.getOrDefault(nums[i], 0) + 1);
		}
		int result = 0;
		for (int i = 1; i < target.length(); i++) {
			String a = target.substring(0, i);
			String b = target.substring(i, target.length());
			if (targetMap.containsKey(a) && targetMap.containsKey(b)) {
				if (a.equals(b)) {
					result += targetMap.get(a) * (targetMap.get(a) - 1);
				} else {
					result += targetMap.get(a) * (targetMap.get(b));
				}
			}
		}
		return result;
	}

	public String largestNumber(int[] nums) {
		String[] arr = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			arr[i] = String.valueOf(nums[i]);
		}
		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for (int i = nums.length - 1; i >= 0; i--) {
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	public String frequencySort(String s) {
		Map<Character, Integer> mp = new HashMap<>();
		for (Character c : s.toCharArray()) {
			mp.merge(c, 1, Integer::sum);
		}

		StringBuilder result = new StringBuilder();
		mp.entrySet().stream().sorted(new MapComparator()).forEach((e) -> {
			System.out.println(e);
			for (int i = 0; i < e.getValue(); i++) {
				result.append(e.getKey());
			}
		});
		return result.toString();
	}

	// CoinChange2
	public int change(int amount, int[] coins) {
		Arrays.sort(coins);
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, 0);
		dp[0] = 1;

		for (int j : coins) {
			for (int i = j; i <= amount; i++) {
				if (j <= i)
					dp[i] += dp[i - j];
			}
		}
		return dp[amount];
	}

	// diagnolTraverse
	public int[] findDiagonalOrder(int[][] mat) {
		int m = mat.length;
		int n = mat[0].length;
		List<Integer> result = new ArrayList<>();
		for (int k = 0; k < m + n; k++) {
			List<Integer> tmp = new ArrayList<>();
			for (int i = 0; i < m; i++) {
				if (i < k && k - i > 0 && k - i < n)
					tmp.add(mat[i][k - i]);
			}
			if (k % 2 == 1)
				Collections.reverse(tmp);
			result.addAll(tmp);
		}
		int[] arr = new int[result.size()];
		for (int i = 0; i < result.size(); i++)
			arr[i] = result.get(i);
		return arr;
	}

	// diagnolTraverse2
	public int[] findDiagonalOrder(List<List<Integer>> nums) {
		List<Integer> result = new ArrayList<>();
		int ml = 0, len = 0;
		for (List<Integer> list : nums)
			ml = Math.max(ml, list.size());
		for (int k = 0; k < ml + nums.size() - 1; k++) {
			for (int i = 0; i < ml; i++) {
				if (k - i >= 0 && k - i < nums.size() && i < nums.get(k - i).size()) {
					len++;
					result.add(nums.get(k - i).get(i));
				}
			}
		}
		int arr[] = new int[len];
		for (int j = 0; j < result.size(); j++) {
			arr[j] = result.get(j);
		}
		return arr;
	}

	// jump game2
	public int jump(int[] nums) {
		int len = nums.length;
		int[] dp = new int[len];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 1; j <= nums[i]; j++) {
				if (i + j >= len)
					break;
				dp[i + j] = Math.min(dp[i + j], 1 + dp[i]);
			}
		}
		return dp[len - 1];
	}

	public int maxArea(int[] height) {
		int l = 0, r = height.length - 1;
		int result = 0;
		while (l < r) {
			result = Math.max(result, Math.min(height[l], height[r]) * (r - l));
			if (height[l] < height[r])
				l++;
			else
				r--;
		}
		return result;
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode l3 = new ListNode(0);
		ListNode l4 = l3;
		boolean carry = false;
		while (l1 != null || l2 != null) {
			int sum = 0;
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}

			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}

			sum = carry ? sum + 1 : sum;
			l3.val = sum % 10;
			carry = sum >= 10;

			if (l1 != null || l2 != null) {
				ListNode tmp = new ListNode(0);
				l3.next = tmp;
				l3 = tmp;
			} else if (carry) {
				ListNode tmp = new ListNode(1);
				l3.next = tmp;
			}
		}
		return l4;
	}

	public int divide(int dividend, int divisor) {
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		int count = 0;
		boolean positive = true;
		if (dividend > 0) {
			positive = !positive;
			dividend *= -1;
		}
		if (divisor > 0) {
			positive = !positive;
			divisor *= -1;
		}
		while (dividend <= divisor) {
			dividend -= divisor;
			count++;
		}
		if (positive)
			return count;
		return count * -1;
	}

	private class StringComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			if (s1.length() == s2.length())
				return s1.compareTo(s2) > 0 ? 1 : -1;
			if (s1.length() < s2.length())
				return -1;
			return 1;
		}
	}

	String rearrangeTheSentence(String sentence) {
		String tmp = sentence.substring(0, 1).toLowerCase() + sentence.substring(1, sentence.length() - 1);
		String[] words = sentence.split("\\s+");
		List<String> wordsList = new ArrayList<>();
		for (String word : words)
			wordsList.add(word);
		wordsList.stream().sorted(new StringComparator());
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < wordsList.size(); i++) {
			if (i == 0) {
				builder.append(wordsList.get(i).substring(0, 1).toUpperCase()
						+ wordsList.get(i).substring(1, wordsList.get(i).length()) + " ");
			} else if (i == wordsList.size() - 1) {
				builder.append(wordsList.get(i) + ".");
			} else {
				builder.append(wordsList.get(i) + " ");
			}
		}
		return builder.toString();
	}

	private class FrequencyComparator implements Comparator<Entry<String, Integer>> {
		@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			if (o1.getValue() == o2.getValue())
				return o1.getKey().compareTo(o2.getKey());
			if (o1.getValue() > o2.getValue())
				return -1;
			return 1;
		}
	}

	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> mp = new HashMap<>();
		for (String str : words) {
			if (mp.get(str) == null)
				mp.put(str, 1);
			else
				mp.put(str, mp.get(str) + 1);
		}

		return mp.entrySet().stream().sorted(new FrequencyComparator()).limit(k).map(e -> e.getKey())
				.collect(Collectors.toList());
	}

	private int treeNumSum = 0;

	private void traverseTreeNumber(TreeNode root, String numString) {
		if (root == null) {
			return;
		}
		int value = Integer.parseInt(numString) * 10 + root.val;
		if (root.left == null && root.right == null) {
			treeNumSum += value;
			return;
		}
		if (root.left != null)
			traverseTreeNumber(root.left, String.valueOf(value));
		if (root.right != null)
			traverseTreeNumber(root.right, String.valueOf(value));
	}

	public int sumNumbers(TreeNode root) {
		traverseTreeNumber(root, "0");
		return treeNumSum;
	}

	public String maximumNumber(String num, int[] change) {
		char[] arr = num.toCharArray();
		boolean changed = false;
		for (int i = 0; i < arr.length; i++) {
			int x = Character.getNumericValue(arr[i]);
			if (x <= change[x]) {
				arr[i] = (char) ('0' + change[x]);
				changed = true;
			}
			if (changed)
				break;
		}
		return new String(arr);
	}

	public int findPairs(int[] nums, int k) {
		int count = 0;
		Set<Integer> numSet = new HashSet<>();
		Set<Integer> dupSet = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (numSet.contains(nums[i]))
				dupSet.add(nums[i]);
			numSet.add(nums[i]);
		}

		if (k == 0)
			return dupSet.size();

		List<Integer> numList = numSet.stream().sorted().collect(Collectors.toList());
		for (int i = 0; i < numList.size() - 1; i++) {
			for (int j = i + 1; j < numList.size(); j++) {
				if (numList.get(j) - numList.get(i) == k)
					count++;
				else if (numList.get(j) - numList.get(i) > k)
					break;
			}
		}
		return count;
	}

	private List<List<Integer>> permutationsList = new ArrayList<>();

	private void permuteArray(List<Integer> nums, int l, int h) {
		if (l == h) {
			List<Integer> tmp = new ArrayList<>();
			tmp.addAll(nums);
			permutationsList.add(tmp);
		}
		for (int i = l; i <= h; i++) {
			Collections.swap(nums, i, l);
			permuteArray(nums, l + 1, h);
			Collections.swap(nums, i, l);
		}
	}

	public List<List<Integer>> permute(int[] nums) {
		permuteArray(Arrays.stream(nums).boxed().collect(Collectors.toList()), 0, nums.length - 1);
		return permutationsList;
	}

	private Set<String> permutationSet = new HashSet<>();

	private void permuteArrayNoDuplicates(List<Integer> nums, int l, int h) {
		if (l == h && !permutationSet.contains(nums.toString())) {
			permutationSet.add(nums.toString());
			List<Integer> tmp = new ArrayList<>();
			tmp.addAll(nums);
			permutationsList.add(tmp);
		}
		for (int i = l; i <= h; i++) {
			Collections.swap(nums, i, l);
			permuteArrayNoDuplicates(nums, l + 1, h);
			Collections.swap(nums, i, l);
		}
	}

	public List<List<Integer>> permuteUnique(int[] nums) {
		permuteArrayNoDuplicates(Arrays.stream(nums).boxed().collect(Collectors.toList()), 0, nums.length - 1);
		return permutationsList;
	}

	public int numSquares(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, 0);
		int i;
		for (i = 1; i <= Math.sqrt(n); i++)
			dp[i * i] = 1;
		i--;
		if (i * i == n)
			return 1;
		for (i = 2; i <= n; i++) {
			if (dp[i] == 0) {
				int tmp = Integer.MAX_VALUE;
				for (int j = 1; j < i; j++) {
					tmp = Math.min(tmp, dp[j] + dp[i - j]);
				}
				dp[i] = tmp;
			}
		}
		return dp[n];
	}

	public static int minStoneSum(int[] piles, int k) {
		PriorityQueue<Integer> maxElemQueue = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < piles.length; i++) {
			maxElemQueue.add(piles[i]);
		}
		while (k-- > 0) {
			int tmp = maxElemQueue.poll();
			int newElem = tmp - (int) Math.floor(tmp / 2);
			maxElemQueue.add(newElem);
		}
		int minSum = 0;
		while (!maxElemQueue.isEmpty()) {
			minSum += maxElemQueue.poll();
		}
		return minSum;
	}

	public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
		int maximumBags = 0, n = rocks.length;
		int[] diff = new int[n];
		for (int i = 0; i < n; i++) {
			if (rocks[i] >= capacity[i]) {
				diff[i] = Integer.MIN_VALUE;
			} else {
				diff[i] = capacity[i] - rocks[i];
			}
		}
		Arrays.sort(diff);
		for (int i = 0; i < n; i++) {
			if (diff[i] == Integer.MIN_VALUE) {
				maximumBags++;
			} else {
				if (additionalRocks >= diff[i]) {
					additionalRocks -= diff[i];
					maximumBags++;
				} else {
					break;
				}
			}
		}
		return maximumBags;
	}

	private Map<Character, List<Character>> keyPad = new HashMap<Character, List<Character>>() {{
		put('2', Arrays.asList('a', 'b', 'c'));
		put('3', Arrays.asList('d', 'e', 'f'));
		put('4', Arrays.asList('g', 'h', 'i'));
		put('5', Arrays.asList('j', 'k', 'l'));
		put('6', Arrays.asList('m', 'n', 'o'));
		put('7', Arrays.asList('p', 'q', 'r', 's'));
		put('8', Arrays.asList('t', 'u', 'v'));
		put('9', Arrays.asList('w', 'x', 'y', 'z'));
	}};


	private void addCombinations(List<String> phoneLetterCombinations, String result, String digits) {
		if (digits.length() == 0) {
			if (result.length() != 0) {
				phoneLetterCombinations.add(result);
			}
			return;
		}
		for (Character key : keyPad.get(digits.charAt(0))) {
			addCombinations(phoneLetterCombinations, result + key, digits.substring(1));
		}
	}

	public List<String> letterCombinations(String digits) {
		List<String> phoneLetterCombinations = new ArrayList<>();
		addCombinations(phoneLetterCombinations, "", digits);
		return phoneLetterCombinations;
	}

	public int nthUglyNumber(int n) {
		if (n <= 0)
			return 0;

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);

		int i = 0;
		int j = 0;
		int k = 0;

		while (list.size() < n) {
			int m2 = list.get(i) * 2;
			int m3 = list.get(j) * 3;
			int m5 = list.get(k) * 5;

			int min = Math.min(m2, Math.min(m3, m5));
			list.add(min);

			if (min == m2)
				i++;

			if (min == m3)
				j++;

			if (min == m5)
				k++;
		}

		return list.get(list.size() - 1);
	}

	public int minimumRounds(int[] tasks) {
		Map<Integer, Integer> taskCountMap = new HashMap<>();
		for (int taskID : tasks) {
			taskCountMap.merge(taskID, 1, Integer::sum);
		}
		int sum = 0;
		for (Integer taskID : taskCountMap.keySet()) {
			int taskCount = taskCountMap.get(taskID);
			if (taskCount == 1) {
				return -1;
			}
			sum += (taskCount % 3 == 0) ? taskCount / 3 : (taskCount / 3) + 1;
		}
		return sum;
	}

	public int findMinArrowShots(int[][] points) {
		int n = points.length;
		Interval[] intervals = new Interval[n];
		for (int i = 0; i < n; i++) {
			Interval interval = new Interval(points[i][0], points[i][1]);
			intervals[i] = interval;
		}
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2) {
				if (i1.start == i2.start) {
					return Integer.compare(i1.end, i2.end);
				}
				return Integer.compare(i1.start, i2.start);
			}
		});

		int end = intervals[0].end, minArrowShots = 1;
		for (int i = 1; i < n; i++) {
			if (intervals[i].start > end) {
				end = intervals[i].end;
				minArrowShots++;
			} else {
				end = Math.min(end, intervals[i].end);
			}
		}
		return minArrowShots;
	}


	class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public int maxIceCream(int[] costs, int coins) {
		Arrays.sort(costs);
		int maxIceCreams = 0;
		for (int cost : costs) {
			if (coins - cost >= 0) {
				coins -= cost;
				maxIceCreams++;
			}
		}
		return maxIceCreams;
	}



	public static void main(String[] args) {
		int[] piles = { 5, 4, 9 };
		int k = 2;
		System.out.println(minStoneSum(piles,k));
	}
}