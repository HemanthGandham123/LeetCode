package contest;

import java.util.Scanner;

public class CCFeb02Starters24 {

	public static int badmintonServes(int n) {
		return 1 + n / 2;
	}

	public static int avoidContact(int x, int c) {
		if (x == c)
			return x + c - 1;
		return x + c;
	}

	public static boolean evmHack(int a, int b, int c, int p, int q, int r) {
		int k = Math.max(Math.max(p - a, q - b), r - c);
		return 2 * (a + b + c + k) > (p + q + r);
	}

	public static long divisorsAndReciprocals(long x, long a, long b) {
		if (x == 1 && a == 1 && b == 1)
			return 1;

		if (b < 2)
			return -1;
		if (x % a != 0)
			return -1;
		long p = x / a;
		p *= b;
		if (x <= p)
			return -1;
		int sum = 0;
		for (long i = 1; i <= p; i++) {
			if (p % i == 0)
				sum += i;
		}

		if (sum != x)
			return -1;

		return p;
	}

	public static void bitwiseProb(int x) {
		int a = 0, b = 0, c = 0, d = 1;
		while (x > 0) {
			int p = x % 2;
			if (p == 1) {
				int k = Math.max(Math.max(a, b), c);
				if (k == a) {
					b += d;
					c += d;
				} else if (k == b) {
					a += d;
					c += d;
				} else {
					a += d;
					b += d;
				}
			}
			d *= 2;
			x /= 2;
		}

		if (b == c)
			b *= 2;
		if (a == b || a == c) {
			a *= 4;
		}

		System.out.println(a + " " + b + " " + c);
	}

	public static void main(String[] args) throws java.lang.Exception {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t > 0) {
			int x = sc.nextInt();
			bitwiseProb(x);
			t--;
		}
		sc.close();
	}

}
