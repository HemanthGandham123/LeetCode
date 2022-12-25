package June;

import java.util.Arrays;

public class Round1 {

	public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

		int mh = h, mv = w, hl = horizontalCuts.length, vl = verticalCuts.length;
		int modulo = (int) (Math.pow(10, 9) + 7);
		Arrays.sort(horizontalCuts);
		Arrays.sort(verticalCuts);

		if (hl > 0)
			mh = Math.max(horizontalCuts[0], h - horizontalCuts[hl - 1]);

		if (vl > 0)
			mv = Math.max(verticalCuts[0], w - verticalCuts[vl - 1]);
		for (int i = 1; i < hl; i++) {
			mh = Math.max(mh, horizontalCuts[i] - horizontalCuts[i - 1]);
		}
		for (int j = 1; j < vl; j++) {
			mv = Math.max(mv, verticalCuts[j] - verticalCuts[j - 1]);
		}
		return (int) ((long) mh * mv % modulo);
	}

}
