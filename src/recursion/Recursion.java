package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import trees.Easy.TreeNode;

public class Recursion {

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

	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode next = head.next;
		head.next = swapPairs(next.next);
		next.next = head;

		return next;
	}

	public TreeNode searchBST(TreeNode root, int val) {
		if (root == null)
			return null;
		if (root.val == val)
			return root;
		if (val < root.val)
			return searchBST(root.left, val);
		return searchBST(root.right, val);
	}

	private int dp[];

	public int fib(int n) {
		if (n == 0)
			return 0;
		dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	public List<Integer> getRow(int rowIndex) {
		List<Integer> tmp = new ArrayList<>();
		tmp.add(1);
		if (rowIndex == 0)
			return tmp;
		tmp.add(1);
		if (rowIndex == 1)
			return tmp;
		tmp.remove(1);
		List<Integer> prev = getRow(rowIndex - 1);
		for (int i = 1; i < rowIndex; i++) {
			tmp.add(prev.get(i) + prev.get(i - 1));
		}
		tmp.add(1);
		return tmp;
	}

	private double myPowUtil(double x, int n, double res) {
		if (n == 0)
			return 1;
		if (Math.abs(n) == 1)
			return res;
		if (n > 0)
			return myPowUtil(x, n - 1, res * x);
		return myPowUtil(x, n + 1, res * x);
	}

	public double myPow(double x, int n) {
		if (n == 0 & n != 0)
			return 0;
		if (n < 0)
			return myPowUtil(1 / x, n, 1 / x);
		return myPowUtil(x, n, x);
	}

}
