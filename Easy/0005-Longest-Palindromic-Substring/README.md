# Longest Palindromic Substring

## Problem Statement

Given a string `s`, return the **longest palindromic substring** in `s`.

A palindrome is a string that reads the same forward and backward.

### Examples

**Example 1**

``` text
Input:  s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
```

**Example 2**

``` text
Input:  s = "cbbd"
Output: "bb"
```

### Constraints

-   `1 <= s.length <= 1000`
-   `s` contains only digits and English letters.

------------------------------------------------------------------------

## Java Solution

``` java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {

            // Odd length palindrome
            int len1 = expand(s, i, i);

            // Even length palindrome
            int len2 = expand(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expand(String s, int left, int right) {

        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        return right - left - 1;
    }
}
```

------------------------------------------------------------------------

## Approach: Expand Around Center

The solution uses the **Expand Around Center** technique.

A palindrome is symmetric around its center. Instead of checking every
possible substring, we select each possible center and expand outward
while the characters on both sides are equal.

There are two possible types of palindrome centers:

1.  **Odd-length palindrome:** The center is one character.
    -   Example: `"racecar"` --- center is `e`.
    -   Start expansion with `left = i` and `right = i`.
2.  **Even-length palindrome:** The center is between two characters.
    -   Example: `"abba"` --- center is between the two `b` characters.
    -   Start expansion with `left = i` and `right = i + 1`.

At every index, we check both types and keep the longest palindrome
found.

## Why This Pattern?

Every palindrome has a center, and its characters are mirrored around
that center. This property makes center expansion a natural fit.

-   It avoids generating and storing all possible substrings.
-   It checks both odd- and even-length palindromes.
-   It uses only constant extra space.
-   It is simpler to implement than a dynamic programming solution for
    this problem.

## Algorithm

1.  If the input is `null` or contains fewer than two characters, return
    it directly.
2.  Initialize `start = 0` and `end = 0` to store the boundaries of the
    longest palindrome found.
3.  Iterate over every index `i` in the string.
4.  For each index:
    -   Call `expand(s, i, i)` to check an odd-length palindrome.
    -   Call `expand(s, i, i + 1)` to check an even-length palindrome.
5.  Take the maximum of the two returned lengths.
6.  If this palindrome is longer than the current best:
    -   Calculate its starting index: `i - (len - 1) / 2`.
    -   Calculate its ending index: `i + len / 2`.
7.  After checking all possible centers, return
    `s.substring(start, end + 1)`.

### How the `expand()` Method Works

The helper method expands from the given center:

1.  Check that `left` is not outside the beginning of the string.
2.  Check that `right` is not outside the end of the string.
3.  Compare `s.charAt(left)` and `s.charAt(right)`.
4.  If they match, move `left` one step backward and `right` one step
    forward.
5.  Stop when the characters do not match or a boundary is reached.
6.  Return `right - left - 1`.

**Why `right - left - 1`?**

The `while` loop moves one step beyond the actual palindrome before
stopping. Therefore, the palindrome's length is `right - left - 1`.

------------------------------------------------------------------------

## Step-by-Step Traversal

Consider:

``` text
s = "babad"
Index:  0  1  2  3  4
Chars:  b  a  b  a  d
```

### Traversal overview

    Center index `i` Odd check            Even check            Longest found
  ------------------ -------------------- --------------------- -----------------
                   0 `"b"` (length 1)     No match (length 0)   `"b"`
                   1 `"bab"` (length 3)   No match (length 0)   `"bab"`
                   2 `"aba"` (length 3)   No match (length 0)   `"bab"` remains
                   3 `"a"` (length 1)     No match (length 0)   `"bab"` remains
                   4 `"d"` (length 1)     No match (length 0)   `"bab"` remains

At index `1`, expanding around `a`:

``` text
        a
       / \
      b   b

Step 1: s[1] == s[1]  -> "a"
Step 2: s[0] == s[2]  -> "bab"
Step 3: boundaries reached -> stop
```

The longest palindrome has length `3`, with `start = 0` and `end = 2`.

``` java
return s.substring(0, 3); // "bab"
```

`"aba"` is also a valid longest answer. This implementation keeps the
first longest palindrome it finds because it updates the answer only
when `len` is strictly greater than the current best length.

### Even-length example: `"cbbd"`

``` text
s = "cbbd"
Index:  0  1  2  3
Chars:  c  b  b  d
```

At `i = 1`, the even-center call is:

``` java
expand(s, 1, 2);
```

-   `s[1] == s[2]` → both are `b`, so the palindrome is `"bb"`.
-   Expand outward: compare `s[0]` (`c`) with `s[3]` (`d`).
-   They do not match, so stop.
-   Length = `2`.

The method returns `"bb"`.

------------------------------------------------------------------------

## Complexity Analysis

Let `n` be the length of the input string.

### Time Complexity: `O(n²)`

-   The `for` loop visits `n` possible center positions.
-   At each position, the helper method is called twice: once for an odd
    center and once for an even center.
-   In the worst case, each expansion can move across up to `n`
    characters.
-   Therefore, the total worst-case time complexity is **O(n × n) =
    O(n²)**.

For example, a string containing the same character repeatedly can cause
long expansions around many centers.

### Auxiliary Space Complexity: `O(1)`

-   Only a fixed number of variables are used: `start`, `end`, `i`,
    `len1`, `len2`, `left`, and `right`.
-   No extra array, map, or dynamic programming table is created.
-   Therefore, the auxiliary space complexity is **O(1)**.

The returned substring occupies space for the output itself; it is not
counted as auxiliary working space.

------------------------------------------------------------------------

## Pattern Summary

  Item              Details
  ----------------- -------------------------------------------
  Pattern           Expand Around Center
  Technique         Two pointers
  Center types      Odd and even
  Time Complexity   `O(n²)`
  Auxiliary Space   `O(1)`
  Best suited for   Finding the longest palindromic substring

## Key Takeaways

-   A palindrome reads the same from left to right and right to left.
-   Every palindrome has a center.
-   Always check both odd and even centers.
-   Expand while the left and right characters match.
-   Store only the starting and ending indices of the best palindrome.
-   Use `substring(start, end + 1)` because Java's ending index is
    exclusive.

------------------------------------------------------------------------

**LeetCode Topic:** String, Two Pointers\
**Main Pattern:** Expand Around Center
