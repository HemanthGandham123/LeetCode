package SortingSearching;

import java.util.*;

public class Medium {
	public void sortColors(int[] nums) {
		int rc = 0, wc = 0, bc = 0;
		for (int i : nums) {
			if (i == 0)
				rc++;
			else if (i == 1)
				wc++;
			else
				bc++;
		}
		int k = 0;
		while (rc-- > 0) {
			nums[k] = 0;
			k++;
		}
		while (wc-- > 0) {
			nums[k] = 1;
			k++;
		}
		while (bc-- > 0) {
			nums[k] = 2;
			k++;
		}
	}

	public int findKthLargest(int[] nums, int k) {
		TreeMap<Integer, Integer> tMap = new TreeMap<>();
		for (int i = 0; i < nums.length; i++) {
			tMap.put(nums[i], tMap.getOrDefault(nums[i], 0) + 1);
		}
		int sum = 0;
		for (Map.Entry<Integer, Integer> entry : tMap.entrySet()) {
			sum += entry.getValue();
			if (sum >= nums.length - k + 1)
				return entry.getKey();
		}
		return -1;
	}

	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> freqMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (!freqMap.containsKey(num)) {
				freqMap.put(num, 1);
			} else {
				freqMap.put(num, freqMap.get(num)+1);
			}
		}
		List<String> strList = new ArrayList<>();
		for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
			strList.add(entry.getValue() + "." + entry.getKey());
		}
		Collections.sort(strList);
		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = Integer.parseInt(strList.get(strList.size() - i - 1).split("\\.")[1]);
		}
		return res;
	}
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> numSet = new ArrayList<>();
		for(Integer num : numSet){
			System.out.println("beep!");
		}
		res.add(numSet);
		return res;
	}

}
