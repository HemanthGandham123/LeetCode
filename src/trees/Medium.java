package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trees.Easy.TreeNode;

public class Medium {

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

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		levelTraverse(root, 0);
		List<List<Integer>> rs = new ArrayList<>();
		for (int i = 0; i < lmp.size(); i++) {
			if (i % 2 == 0)
				rs.add(lmp.get(i));
			else {
				List<Integer> revList = lmp.get(i);
				Collections.reverse(revList);
				rs.add(revList);
			}
		}
		return rs;
	}

	private List<Integer> inOrderList = new ArrayList<>();

	private void inOrderTraversalHelper(TreeNode root) {
		if (root != null) {
			inOrderTraversalHelper(root.left);
			inOrderList.add(root.val);
			inOrderTraversalHelper(root.right);
		}
	}

	public List<Integer> inorderTraversal(TreeNode root) {
		inOrderTraversalHelper(root);
		return inOrderList;
	}

	public int kthSmallest(TreeNode root, int k) {
		return inorderTraversal(root).get(k - 1);
	}

}
