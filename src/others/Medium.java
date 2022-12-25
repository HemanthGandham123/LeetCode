package others;

public class Medium {
	public int majorityElement(int[] nums) {
		int maj_ind = 0, counter = 1;
		for (int i = 1; i < nums.length; i++) {
			counter = (nums[maj_ind] == nums[i]) ? counter + 1 : counter - 1;
			if (counter == 0) {
				maj_ind = i;
				counter = 1;
			}
		}
		return nums[maj_ind];
	}
}
