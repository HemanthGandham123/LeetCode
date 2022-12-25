package ll;

import java.util.Stack;

public class Easy {

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

	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode copy = head;
		int l = 0;
		while (copy != null) {
			l++;
			copy = copy.next;
		}
		l -= n;
		copy = dummy;
		while (l > 0) {
			l--;
			copy = copy.next;
		}

		copy.next = copy.next.next;
		return dummy.next;
	}

	public ListNode reverseList(ListNode head) {

		if (head == null || head.next == null)
			return head;

		ListNode rest = reverseList(head.next);
		head.next.next = head;
		head.next = null;

		return rest;

	}

	public boolean hasCycle(ListNode head) {
		ListNode slow = head, fast = head;
		while (slow != null && fast != null) {
			if (slow.next != null)
				slow = slow.next;
			else
				break;
			if (fast.next != null && fast.next.next != null)
				fast = fast.next.next;
			else
				break;
			if (slow == fast)
				return true;
		}
		return false;
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		ListNode l3 = new ListNode(0);
		ListNode copy = l3;

		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				l3.next = l1;
				l1 = l1.next;
				l3 = l3.next;
			} else {
				l3.next = l2;
				l2 = l2.next;
				l3 = l3.next;
			}
		}

		while (l1 != null) {
			l3.next = l1;
			l1 = l1.next;
			l3 = l3.next;
		}
		while (l2 != null) {
			l3.next = l2;
			l2 = l2.next;
			l3 = l3.next;
		}
		return copy.next;
	}

	public boolean isPalindrome(ListNode head) {
		ListNode copy = head;
		Stack<Integer> stack = new Stack<>();
		while (copy != null) {
			stack.push(copy.val);
			copy = copy.next;
		}
		while (head != null) {
			if (stack.pop() != head.val)
				return false;
			head = head.next;
		}
		return true;
	}

}
