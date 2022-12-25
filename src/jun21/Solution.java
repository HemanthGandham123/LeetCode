package jun21;

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

	public int numMatchingSubseq(String s, String[] words) {
		int count = 0;
		for (String text : words) {
			if (s.contains(text))
				count++;
		}
		return count;
	}

}
