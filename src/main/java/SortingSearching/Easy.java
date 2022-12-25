package SortingSearching;

public class Easy {

	boolean isBadVersion(int version) {
		return true;
	}

	public int firstBadVersion(int n) {

		int l = 1, h = n;

		while (l < h) {
			int m = l + (h - l) / 2;
			if (isBadVersion(m) == true)
				h = m;
			else
				l = m + 1;
		}
		return l;
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {

		int i = 0, j = 0, k = 0;
		int[] nums3 = new int[n + m];

		while (i < m && j < n) {
			if (nums1[i] < nums2[j]) {
				nums3[k] = nums1[i];
				k++;
				i++;
			} else {
				nums3[k] = nums2[j];
				k++;
				j++;
			}

		}

		while (i < m) {
			nums3[k] = nums1[i];
			k++;
			i++;
		}

		while (j < n) {
			nums3[k] = nums2[j];
			k++;
			j++;
		}
		for (int p = 0; p < m + n; p++)
			nums1[p] = nums3[p];

	}
}
