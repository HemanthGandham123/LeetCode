package Easy;

import java.util.*;
import java.util.Map.Entry;

public class Solution {

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

	private TreeNode inversion(TreeNode root) {
		if (root == null)
			return null;
		TreeNode tmp = root.left;
		root.left = inversion(root.right);
		root.right = inversion(tmp);
		return root;
	}

	List<Integer> preOrderList = new ArrayList<>();

	private void preOrder(TreeNode root) {
		if (root != null) {
			preOrderList.add(root.val);
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public List<Integer> preorderTraversal(TreeNode root) {
		preOrder(root);
		return preOrderList;
	}

	public int findNumbers(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (String.valueOf(nums[i]).length() % 2 == 0)
				count++;
		}
		return count;
	}

	private int countVowels(String str) {
		int count = 0;
		for (Character ch : str.toCharArray()) {
			if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
				count++;
			}
		}
		return count;
	}

	public boolean halvesAreAlike(String s) {
		int len = s.length();
		String firstHalf = s.toLowerCase().substring(0, len / 2);
		String secondHalf = s.toLowerCase().substring(len / 2);
		return countVowels(firstHalf) == countVowels(secondHalf);
	}

	public char repeatedCharacter(String s) {
		Set<Character> charSet = new HashSet<>();
		for (Character ch : s.toCharArray()) {
			if (charSet.contains(ch)) {
				return ch;
			} else {
				charSet.add(ch);
			}
		}
		return ' ';
	}

	public boolean wordPattern(String pattern, String s) {
		String[] words = s.split("\\s+");
		if (words.length != pattern.length()) {
			return false;
		}
		Map<Character, String> patternMap = new HashMap<>();
		Set<String> patternSet = new HashSet<>();
		for (int i = 0; i < pattern.length(); i++) {
			Character currentChar = pattern.charAt(i);
			String currentWord = words[i];
			if (!patternMap.containsKey(currentChar)) {
				if (patternSet.contains(currentWord)) {
					return false;
				}
				patternSet.add(currentWord);
				patternMap.put(currentChar, currentWord);
			} else {
				if (!currentWord.equals(patternMap.get(currentChar))) {
					return false;
				}
			}
		}
		return true;
	}

	public List<String> commonChars(String[] words) {
		List<Map<Character, Integer>> mapList = new ArrayList<>();
		for (String word : words) {
			Map<Character, Integer> wordMap = freqMap(word);
			mapList.add(wordMap);
		}
		List<String> res = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			int min = Integer.MAX_VALUE;
			Character current = (char) ('a' + i);
			for (Map<Character, Integer> wordMap : mapList) {
				int freq = wordMap.containsKey(current) ? wordMap.get(current) : 0;
				min = Math.min(freq, min);
			}
			for (int j = 0; j < min; j++) {
				res.add(current.toString());
			}
		}
		return res;
	}

	public int addDigits(int num) {
		return (num - 1) % 9 + 1;
	}

	public int countOperations(int num1, int num2) {
		if (num1 == 0 || num2 == 0) {
			return 0;
		}
		if (num1 >= num2) {
			return 1 + countOperations(num1 - num2, num2);
		}
		return 1 + countOperations(num1, num2 - num1);
	}

	private String capitalizeWord(String str) {
		String result = str.toLowerCase();
		return new Character((char) (result.toCharArray()[0] - 'a' + 'A')) + result.substring(1);
	}

	public String capitalizeTitle(String title) {
		String result = "";
		String[] words = title.split("\\s+");
		for (String word : words) {
			if (word.length() < 3) {
				result = result + word.toLowerCase();
			} else {
				result = result + capitalizeWord(word);
			}
			result = result + " ";
		}
		return result.trim();
	}

	public int maxProduct(int[] nums) {
		int max = Integer.MIN_VALUE, secMax = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= max) {
				secMax = max;
				max = nums[i];
			} else {
				secMax = Math.max(secMax, nums[i]);
			}
		}
		return (secMax - 1) * (max - 1);
	}

	public boolean areOccurrencesEqual(String s) {
		Map<Character, Integer> mp = freqMap(s);
		int firstVal = 0;
		boolean isFirst = true;
		for (Entry<Character, Integer> entry : mp.entrySet()) {
			if (isFirst) {
				firstVal = entry.getValue();
				isFirst = false;
			} else {
				if (firstVal != entry.getValue())
					return false;
			}
		}
		return true;
	}

	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, List<Integer>> indexMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			List<Integer> tmpList = new ArrayList<>();
			if (indexMap.containsKey(nums[i])) {
				tmpList = indexMap.get(nums[i]);
			}
			tmpList.add(i);
			indexMap.put(nums[i], tmpList);
		}

		for (Entry<Integer, List<Integer>> entry : indexMap.entrySet()) {
			List<Integer> currList = entry.getValue();
			Collections.sort(currList);
			for (int i = 1; i < currList.size(); i++) {
				if (currList.get(i) - currList.get(i - 1) <= k)
					return true;
			}
		}
		return false;
	}

	public boolean checkPerfectNumber(int num) {
		int sum = 0;
		Set<Integer> numSet = new HashSet<>();
		for (int i = 1; i < Math.sqrt(num) + 1; i++) {
			if (num % i == 0) {
				numSet.add(i);
				numSet.add(num / i);
			}
		}
		numSet.remove(num);
		for (Integer number : numSet) {
			sum += number;
		}
		if (sum == num)
			return true;
		return false;
	}

	public ListNode deleteDuplicates(ListNode head) {
		ListNode tmp = head;
		while (tmp != null) {
			if (tmp.next == null) {
				return head;
			}
			if (tmp.val == tmp.next.val) {
				tmp.next = tmp.next.next;
			} else {
				tmp = tmp.next;
			}
		}
		return head;
	}

	public String interpret(String command) {

		StringBuffer str = new StringBuffer();
		for (int i = 0; i < command.length(); i++) {
			char ch = command.charAt(i);
			if (ch == 'G')
				str.append('G');
			else if (ch == '(') {
				if (command.charAt(i + 1) == ')') {
					str.append('o');
					i++;
				} else {
					str.append("al");
					i += 3;
				}
			}
		}
		return str.toString();
	}

	private boolean isVowel(char ch) {
		return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
	}

	public String reverseVowels(String s) {
		Stack<Character> stck = new Stack<>();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (isVowel(ch))
				stck.add(ch);
		}

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (isVowel(ch)) {
				str.append(stck.pop());
			} else
				str.append(ch);
		}

		return str.toString();
	}

	public int[] getConcatenation(int[] nums) {

		int[] res = new int[2 * nums.length];
		for (int i = 0; i < 2 * nums.length; i++) {
			res[i] = nums[i % nums.length];
		}
		return res;
	}

	private Set<Integer> pathSum = new HashSet<>();

	private void populatePathSum(TreeNode root, int curr) {
		if (root.left == null && root.right == null)
			pathSum.add(curr + root.val);
		else {
			if (root.left != null)
				populatePathSum(root.left, curr + root.val);
			if (root.right != null)
				populatePathSum(root.right, curr + root.val);
		}

	}

	public boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null)
			return false;

		populatePathSum(root, 0);
		return pathSum.contains(targetSum);
	}

	List<Integer> postOrderList = new ArrayList<>();

	private void postOrder(TreeNode root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			postOrderList.add(root.val);
		}
	}

	public List<Integer> postorderTraversal(TreeNode root) {
		postOrder(root);
		return postOrderList;
	}

	public TreeNode invertTree(TreeNode root) {
		return inversion(root);
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;
		if (p.val != q.val)
			return false;
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	public int sumOfUnique(int[] nums) {
		Map<Integer, Integer> mp = new HashMap<>();
		for (int i : nums) {
			if (mp.get(i) == null)
				mp.put(i, 1);
			else
				mp.put(i, mp.get(i) + 1);
		}

		return mp.entrySet().stream().filter((e) -> e.getValue() == 1).map(e -> e.getKey()).mapToInt(Integer::intValue)
				.sum();
	}

	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		String s = String.valueOf(x);
		StringBuilder builder = new StringBuilder(s);
		return builder.reverse().toString().compareTo(s) == 0;
	}

	public int searchInsert(int[] nums, int target) {

		int m = 0, l = 0, h = nums.length - 1;
		while (l <= h) {
			m = l + (h - l) / 2;
			if (nums[m] == target)
				return m;
			else if (nums[m] > target)
				h = m - 1;
			else
				l = m + 1;
		}
		if (target < nums[m])
			return m;
		return m + 1;
	}

	public int lengthOfLastWord(String s) {
		String[] arr = s.split("\\s+");
		if (arr.length == 0)
			return 0;
		return arr[arr.length - 1].length();
	}

	public boolean isUgly(int n) {
		while (n % 2 == 0)
			n /= 2;
		while (n % 3 == 0)
			n /= 3;
		while (n % 5 == 0)
			n /= 5;
		if (n == 1)
			return true;
		return false;
	}

	public int maxPower(String s) {
		int tmpMax = 0, max = 0;
		Character sch = s.charAt(0);
		for (int i = 0; i < s.length(); i++) {
			Character cch = s.charAt(i);
			if (cch == sch) {
				tmpMax++;
			} else {
				max = Math.max(tmpMax, max);
				tmpMax = 1;
				sch = cch;
			}
		}
		return Math.max(max, tmpMax);
	}

	public char findTheDifference(String s, String t) {

		Map<Character, Integer> mp1 = freqMap(s);
		Map<Character, Integer> mp2 = freqMap(t);

		for (char ch : mp2.keySet()) {
			if (mp1.get(ch) == null || mp1.get(ch) != mp2.get(ch))
				return ch;
		}

		return ' ';
	}

	int md = Integer.MAX_VALUE;

	private void minDepthTree(TreeNode root, int h) {
		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			md = Math.min(md, h);
		}
		minDepthTree(root.left, h + 1);
		minDepthTree(root.right, h + 1);
	}

	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;

		minDepthTree(root, 1);
		return md;
	}

	public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		for (String word : word1)
			builder1.append(word);
		for (String word : word2)
			builder2.append(word);
		return builder1.toString().equals(builder2.toString());
	}

	public int countOdds(int low, int high) {
		int count = (high - low) / 2;
		if ((high - low) % 2 == 0 && low % 2 == 0) {
			return count;
		}
		return ++count;
	}

	public double average(int[] salary) {
		int min = salary[0], max = salary[0], sum = 0;
		for (int i = 0; i < salary.length; i++) {
			min = Math.min(min, salary[i]);
			max = Math.max(max, salary[i]);
			sum += salary[i];
		}
		return (double) (sum - min - max) / (salary.length - 2);
	}

	private Map<Character, Integer> freqMap(String s) {
		Map<Character, Integer> mp1 = new HashMap<>();
		for (Character ch : s.toCharArray()) {
			if (mp1.get(ch) == null)
				mp1.put(ch, 1);
			else
				mp1.put(ch, mp1.get(ch) + 1);
		}
		return mp1;
	}

	public int hammingWeight(int n) {
		return Integer.bitCount(n);
	}

	public int percentageLetter(String s, char letter) {
		Map<Character, Integer> freqmap = freqMap(s);
		return (int) Math.floor((freqmap.getOrDefault(letter, 0) * 100) / s.length());
	}

	public boolean canConstruct(String ransomNote, String magazine) {
		Map<Character, Integer> ransomNoteMap = freqMap(ransomNote);
		Map<Character, Integer> magazineMap = freqMap(magazine);
		for (Character ch : ransomNoteMap.keySet()) {
			if (!magazineMap.containsKey(ch)) {
				return false;
			}
			if (ransomNoteMap.get(ch) > magazineMap.get(ch)) {
				return false;
			}
		}
		return true;
	}

	public int subtractProductAndSum(int n) {
		int sum = 0, product = 1;
		while (n > 0) {
			int digit = n % 10;
			product *= digit;
			sum += digit;
			n /= 10;
		}
		return product - sum;
	}

	public int nearestValidPoint(int x, int y, int[][] points) {
		int minIndex = -1, min = Integer.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			if (points[i][0] == x || points[i][1] == y) {
				int dist = Math.abs(x - points[i][0]) + Math.abs(y - points[i][1]);
				if (dist < min) {
					min = dist;
					minIndex = i;
				}
			}
		}
		return minIndex;
	}

	public int diagonalSum(int[][] mat) {
		int sum = 0;
		for (int i = 0; i < mat.length; i++) {
			sum += mat[i][i] + mat[i][mat.length - i - 1];
		}
		if (mat.length % 2 != 0) {
			sum -= mat[mat.length / 2][mat.length / 2];
		}
		return sum;
	}

	public int arraySign(int[] nums) {
		int negativeNumbers = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0)
				return 0;
			if (nums[i] < 0)
				negativeNumbers++;
		}
		if (negativeNumbers % 2 == 0)
			return 1;
		return -1;
	}

	public int largestPerimeter(int[] nums) {
		Arrays.sort(nums);
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i - 1] + nums[i - 2] > nums[i])
				return nums[i] + nums[i - 1] + nums[i - 2];
		}
		return 0;
	}

	public boolean isIsomorphic(String s, String t) {
		int n = s.length();
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		int i = 0;
		while (i < n) {
			char c = s.charAt(i);
			int sum = 0;
			while (i < n && s.charAt(i) == c) {
				sum++;
				i++;
			}
			list1.add(sum);
		}
		i = 0;
		while (i < n) {
			char c = t.charAt(i);
			int sum = 0;
			while (i < n && t.charAt(i) == c) {
				sum++;
				i++;
			}
			list2.add(sum);
		}
		if (list1.size() != list2.size())
			return false;
		for (int j = 0; j < list1.size(); j++) {
			if (list1.get(j) != list2.get(j))
				return false;
		}
		return true;
	}
}
