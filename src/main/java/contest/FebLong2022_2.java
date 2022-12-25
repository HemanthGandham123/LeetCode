package contest;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class FebLong2022_2 {

	public static boolean helium3(int a, int b, int x, int y) {
		return a * b <= x * y;
	}

	public static int wcc(String s, int x) {
		long count = s.chars().filter(c -> c == 'C').count() - s.chars().filter(c -> c == 'N').count();
		if (count == 0)
			return 55 * x;
		else if (count > 0)
			return 60 * x;
		return 40 * x;
	}

	public static boolean xorpal(String s, int n) {
		long ones = s.chars().filter(c -> c == '1').count();
		long zeros = s.chars().filter(c -> c == '0').count();
		if (ones + zeros != n)
			return false;
		long p = ones % 2, q = zeros % 2;
		if (p == 1 && q == 1 && ones != zeros)
			return false;

		return true;
	}

	private static int nofix(int[] a) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == i + count + 1) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) throws java.lang.Exception {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t > 0) {
			int n = sc.nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
			}
			System.out.println(nofix(a));
			t--;
		}
		sc.close();
	}

}
