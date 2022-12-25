package ll;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.Set;

public class Medium {

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

	public ListNode oddEvenList(ListNode head) {
		ListNode tmp = head, tail = head;
		int len = 1;
		while (tail != null && tail.next != null) {
			len++;
			tail = tail.next;
		}
		if (len < 3)
			return head;
		len /= 2;
		while (len-- > 0) {
			ListNode tmp2 = tmp.next;
			tmp.next = tmp.next.next;
			tmp2.next = null;
			tail.next = tmp2;
			tail = tmp2;
			tmp = tmp.next;
		}
		return head;
	}

	private int getListLength(ListNode head) {
		ListNode tmp = head;
		int count = 0;
		while (tmp != null) {
			count++;
			tmp = tmp.next;
		}
		return count;

	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		int l1 = getListLength(headA);
		int l2 = getListLength(headB);
		int d = l1 - l2;
		ListNode skip = (d > 0) ? headA : headB;
		ListNode stale = (d > 0) ? headB : headA;
		d = Math.abs(d);
		while (d-- > 0) {
			skip = skip.next;
		}
		while (skip != null && stale != null) {
			if (skip == stale)
				return skip;
			skip = skip.next;
			stale = stale.next;
		}
		return null;
	}

	private double distance(int[] p1, int[] p2) {
		return Math.sqrt(Math.pow((p1[0] - p2[0]), 2) + Math.pow((p1[1] - p2[1]), 2));
	}

	final double THRESHOLD = Math.pow(10, -4);

	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		double distances[] = { distance(p1, p2), distance(p1, p3), distance(p1, p4), distance(p2, p3), distance(p2, p4),
				distance(p3, p4) };
		Arrays.sort(distances);
		BigDecimal b1 = new BigDecimal(distances[0]);
		BigDecimal b2 = new BigDecimal(distances[4]);
		BigDecimal b3 = new BigDecimal(Math.sqrt(2));
		b1 = b1.multiply(b3);
		if (distances[0] == distances[1] && distances[1] == distances[2] && distances[2] == distances[3]
				&& Math.abs(distances[4] - Math.sqrt(2) * distances[0]) < THRESHOLD && distances[4] == distances[5])
			return true;
		return false;
	}

	private ListNode reverseList(ListNode head) {

		if (head == null || head.next == null)
			return head;

		ListNode rest = reverseList(head.next);
		head.next.next = head;
		head.next = null;

		return rest;

	}

	public String removeStars(String s) {
		Stack<Character> charStack = new Stack<>();
		for (Character ch : s.toCharArray()) {
			if (ch != '*') {
				charStack.push(ch);
			} else {
				if (!charStack.isEmpty()) {
					charStack.pop();
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		while (!charStack.isEmpty()) {
			sb.append(charStack.pop());
		}
		return sb.reverse().toString();

	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode l3 = new ListNode(0);
		ListNode res = l3;
		l1 = reverseList(l1);
		l2 = reverseList(l2);
		int num1 = 0, num2 = 0, p = 1;
		while (l1 != null) {
			num1 += l1.val * p;
			l1 = l1.next;
			p *= 10;
		}
		p = 1;
		while (l2 != null) {
			num2 += l2.val * p;
			l2 = l2.next;
			p *= 10;
		}
		int num = num1 + num2;
		if (num == 0)
			return l3;
		while (num > 0) {
			ListNode tmp = new ListNode(num % 10);
			l3.next = tmp;
			l3 = l3.next;
			num /= 10;
		}
		return res.next;
	}

}
