# String

+ [Group Anagrams](#group-anagrams)
+ [Valid Palindrome](#valid-palindrome)
+ [Longest Palindromic Substring](#longest-palindromic-substring)
+ [Palindromic Substrings](#palindromic-substrings)

## Group Anagrams

https://leetcode.com/problems/group-anagrams/

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
```

## Valid Palindrome

https://leetcode.com/problems/valid-palindrome/

```java
public class Solution {
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while (head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if (!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
        }

        return true;
    }
}
```

## Longest Palindromic Substring

https://leetcode.com/problems/longest-palindromic-substring/

```java
class Solution {
    public String longestPalindrome(String s) {
        char[] ca = s.toCharArray();
        int rs = 0, re = 0;
        int max = 0;
        for (int i = 0; i < ca.length; i++) {
            if (isPalindrome(ca, i - max - 1, i)) {
                rs = i - max - 1;
                re = i;
                max += 2;
            } else if (isPalindrome(ca, i - max, i)) {
                rs = i - max;
                re = i;
                max += 1;
            }
        }
        return s.substring(rs, re + 1);
    }

    private boolean isPalindrome(char[] ca, int s, int e) {
        if (s < 0) return false;

        while (s < e) {
            if (ca[s++] != ca[e--]) return false;
        }
        return true;
    }
}
```

## Palindromic Substrings

https://leetcode.com/problems/palindromic-substrings/

```java
public class Solution {
    int count = 0;

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }

        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }
}
```
