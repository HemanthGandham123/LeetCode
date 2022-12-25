package daily;

import java.util.*;

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

	public ListNode partition(ListNode head, int x) {

		if (head == null)
			return head;
		ListNode lessThanList = null;
		ListNode greaterThanList = null;
		ListNode copyList1 = null;
		ListNode copyList2 = null;

		while (head != null) {
			if (head.val < x) {
				if (lessThanList == null) {
					lessThanList = new ListNode(head.val);
					copyList1 = lessThanList;
				} else {
					lessThanList.next = new ListNode(head.val);
					lessThanList = lessThanList.next;
				}
			} else {
				if (greaterThanList == null) {
					greaterThanList = new ListNode(head.val);
					copyList2 = greaterThanList;
				} else {
					greaterThanList.next = new ListNode(head.val);
					greaterThanList = greaterThanList.next;
				}
			}
			head = head.next;
		}
		head = copyList1;
		if (copyList1 == null) {
			return copyList2;
		}
		while (copyList1.next != null) {
			copyList1 = copyList1.next;
		}
		copyList1.next = copyList2;

		return head;

	}
	
	class NumArray {
		
		private int[] numArray ;

	    public NumArray(int[] nums) {
	        numArray = new int[nums.length];
	        for(int i=0;i<nums.length;i++) {
	        	numArray[i] = nums[i];
	        }
	    }
	    
	    public void update(int index, int val) {
	        numArray[index]=val;
	    }
	    
	    public int sumRange(int left, int right) {
	        int sum=0;
	        for(int i=left;i<=right;i++) {
	        	sum+=numArray[i];
	        }
	        return sum;
	    }
	}

	public int[] dailyTemperatures(int[] temperatures) {
		int n = temperatures.length;
		int[] result = new int[n];
		int[] warmerIndex = new int[n];
		Stack<Integer> indexStack = new Stack<>();
		warmerIndex[n - 1] = -1;
		for (int i = n - 1; i >= 0; i--) {
			while (!indexStack.isEmpty()) {
				int top = indexStack.peek();
				if (temperatures[top] > temperatures[i]) {
					warmerIndex[i] = top;
					indexStack.push(i);
					break;
				} else {
					indexStack.pop();
				}
			}
			if (indexStack.isEmpty()) {
				warmerIndex[i] = -1;
				result[i] = 0;
				indexStack.push(i);
			} else {
				result[i] = warmerIndex[i] - i;
			}
		}
		return result;
	}

	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		Set<Integer> visited = new HashSet<>();
		Queue<Integer> keysQueue = new ArrayDeque<>();
		keysQueue.add(0);
		while (!keysQueue.isEmpty()) {
			int currentKey = keysQueue.poll();
			for (Integer key : rooms.get(currentKey)) {
				if (!visited.contains(key)) {
					keysQueue.add(key);
				}
			}
			visited.add(currentKey);
		}
		return visited.size() == rooms.size();
	}
}
