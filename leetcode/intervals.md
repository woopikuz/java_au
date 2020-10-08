# Intervals

+ [Non-overlapping Intervals](#Non-overlapping Intervals)

## Non-overlapping Intervals

https://leetcode.com/problems/non-overlapping-intervals/

```
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (i1, i2) -> (i1[0] - i2[0]));

        int min = 0;
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] < end) {
                min++;
                end = Math.min(end, interval[1]);
            } else {
                end = interval[1];
            }
        }
        return min;
    }
}
```