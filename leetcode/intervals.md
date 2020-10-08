# Intervals

+ [Non-overlapping Intervals](#non-overlapping-intervals)
+ [Merge Intervals](#merge-intervals)
+ [Insert Interval](#insert-interval)

## Non-overlapping Intervals

https://leetcode.com/problems/non-overlapping-intervals/

```java
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

## Merge Intervals

https://leetcode.com/problems/merge-intervals/

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int j = intervals.length;
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] >= intervals[i + 1][0]) {
                if (intervals[i][1] <= intervals[i + 1][1]) intervals[i][1] = intervals[i + 1][1];
                else intervals[i + 1][1] = intervals[i][1];
                intervals[i + 1][0] = intervals[i][0];
                j--;
            }
        }

        int[][] res = new int[j][2];
        for (int i = 0, k = 0; i < intervals.length; i++) {
            if (i == 0) {
                res[k][0] = intervals[i][0];
                res[k][1] = intervals[i][1];
                k++;
            } else if (res[k - 1][0] == intervals[i][0] && res[k - 1][1] != intervals[i][1]) {
                res[k - 1][1] = intervals[i][1];
            } else if (res[k - 1][0] != intervals[i][0]) {
                res[k][0] = intervals[i][0];
                res[k][1] = intervals[i][1];
                k++;
            }
        }
        return res;
    }
}
```

## Insert Interval

https://leetcode.com/problems/insert-interval/

```java
class Solution {
    public int[][] insert(int[][] in, int[] n) {
        List<int[]> l = new ArrayList<>();
        int i = 0;
        while (i < in.length && in[i][1] < n[0]) {
            l.add(in[i]);
            i++;
        }
        while (i < in.length && in[i][0] <= n[1]) {
            n[0] = Math.min(n[0], in[i][0]);
            n[1] = Math.max(n[1], in[i][1]);
            i++;
        }
        l.add(n);
        while (i < in.length) {
            l.add(in[i]);
            i++;
        }
        return l.toArray(new int[l.size()][]);
    }
}
```
