package math;

import java.util.HashSet;
import java.util.Set;

public class Medium {


    private int sumOfSquaresOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            int k = n % 10;
            sum += k * k;
            n /= 10;
        }
        return sum;
    }

    public int mySqrt(int x) {
        if (x < 4) {
            if (x == 0) {
                return 0;
            }
            return 1;
        }
        long l = 1, h = x, m = 0;
        while (l <= h) {
            m = l + (h - l) / 2;
            if (m * m < x)
                l = m + 1;
            else if (m * m > x)
                h = m - 1;
            else
                return (int) m;
        }
        if (m * m > x)
            return (int) m - 1;
        return (int) m;
    }


    public boolean isHappy(int n) {
        Set<Integer> storage = new HashSet<>();
        storage.add(n);
        while (n != 1) {
            n = sumOfSquaresOfDigits(n);
            if (storage.contains(n))
                return false;
            storage.add(n);
        }
        return true;
    }

    public int trailingZeroes(int n) {
        int dup = n, sum = 0;
        while (n / 5 > 0) {
            sum++;
            n /= 5;
        }
        int res = 0;
        for (int i = 1; i <= sum; i++) {
            res += dup / (Math.pow(5, i));
        }
        return res;
    }

    public int titleToNumber(String columnTitle) {
        int sum = 0, p = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            sum += ((columnTitle.charAt(i) - 'A') + 1) * p;
            p *= 26;
        }
        return sum;
    }
}
