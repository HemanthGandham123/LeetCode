package Algo1;


import java.util.Arrays;

public class AlgorithmI {

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

    public ListNode middleNode(ListNode head) {
        ListNode dup = head;
        int len = 0;
        while (dup != null) {
            dup = dup.next;
            len++;
        }
        for (int i = 0; i < len / 2; i++)
            head = head.next;
        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dup = head, tmp = head;
        int len = 0;
        while (dup != null) {
            dup = dup.next;
            len++;
        }
        int index = len - n;
        if (index == 0)
            return head.next;
        ListNode prev = tmp;
        for (int i = 0; i < index; i++) {
            prev = tmp;
            tmp = tmp.next;
        }
        prev.next = tmp.next;
        return head;
    }

    public int rob(int[] nums) {

        int len = nums.length;
        int[] dp = new int[len];
        if (len < 3) {
            if (len == 2)
                return Math.max(nums[0], nums[1]);
            return nums[0];
        }

        dp[len - 1] = nums[len - 1];
        dp[len - 2] = Math.max(nums[len - 1], nums[len - 2]);

        for (int i = len - 3; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int search(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] == target)
                return m;
            else if (nums[m] < target)
                l = m + 1;
            else
                h = m - 1;
        }
        return -1;
    }

    public boolean isBadVersion(int version) {
        return true;
    }

    public int firstBadVersion(int n) {
        int l = 1, h = n;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (!isBadVersion(m))
                l = m + 1;
            else
                h = m - 1;
        }
        return l;
    }

    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int i = 0, j = 0;
        while (i < n) {
            if (nums[i] == 0) {
                while ((j < i) || (j < n - 1 && nums[j] == 0))
                    j++;
                if (j > i) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
            i++;
        }
    }

    public int searchInsert(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] == target)
                return m;
            else if (nums[m] < target)
                l = m + 1;
            else
                h = m - 1;
        }
        return l;
    }

    public void reverseString(char[] s) {
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            char temp = s[i];
            s[i] = s[n - i - 1];
            s[n - i - 1] = temp;
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int diff = target - numbers[i];
            int index = search(numbers, diff);
            if (index != -1) {
                res[0] = i;
                res[1] = index;
                return res;
            }
        }
        return res;
    }


    public String reverseWords(String s) {
        String[] arr = s.trim().split("\\s");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder(arr[i]);
            sb.reverse();
            builder.append(sb.toString());
            if (i != arr.length - 1)
                builder.append(" ");
        }

        return builder.toString();
    }
}
