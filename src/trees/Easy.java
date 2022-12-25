package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Easy {

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

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
	}

	List<Integer> list = new ArrayList<>();
	int check = 0, temp = Integer.MIN_VALUE;
	boolean flag = true;

	private void traverse(TreeNode root) {
		if (root == null)
			return;
		traverse(root.left);
		if (root.val <= temp && check > 0)
			flag = false;
		temp = root.val;
		check++;
		traverse(root.right);

	}

	public boolean isValidBST(TreeNode root) {
		traverse(root);
		return flag;
	}

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

	public List<List<Integer>> levelOrder(TreeNode root) {
		levelTraverse(root, 0);
		List<List<Integer>> rs = new ArrayList<>();
		for (int i = 0; i < lmp.size(); i++) {
			rs.add(lmp.get(i));
		}
		return rs;
	}

	private boolean isMirror(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;

		return root1.val == root2.val && isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);

	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		return isMirror(root.left, root.right);
	}

	private TreeNode getBST(int arr[], int l, int h) {
		if (l > h)
			return null;
		TreeNode root = new TreeNode();
		int m = (l + h) / 2;
		root.val = arr[m];
		root.left = getBST(arr, l, m - 1);
		root.right = getBST(arr, m + 1, h);
		return root;
	}

	public TreeNode sortedArrayToBST(int[] nums) {

		TreeNode root = getBST(nums, 0, nums.length - 1);
		return root;

	}
}
