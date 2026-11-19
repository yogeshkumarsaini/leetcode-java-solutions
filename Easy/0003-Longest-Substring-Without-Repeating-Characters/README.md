# Longest Substring Without Repeating Characters

## Problem Statement

Given a string `s`, return the length of the **longest substring** that
contains no duplicate characters.

A **substring** is a continuous sequence of characters from a string.
The characters must appear next to each other in the original string.

### Examples

**Example 1**

``` text
Input:  s = "abcabcbb"
Output: 3
Explanation: "abc", "bca", and "cab" are valid longest substrings.
```

**Example 2**

``` text
Input:  s = "bbbbb"
Output: 1
Explanation: "b" is the longest substring without repeating characters.
```

**Example 3**

``` text
Input:  s = "pwwkew"
Output: 3
Explanation: "wke" is a valid answer. "pwke" is not a substring because its characters are not continuous.
```

### Constraints

-   `0 <= s.length <= 10^5`
-   `s` may contain English letters, digits, symbols, and spaces.

------------------------------------------------------------------------

## Java Solution

``` java
import java.util.HashSet;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            // Remove characters until the duplicate is removed
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            // Add the current character to the window
            set.add(s.charAt(right));

            // Update the maximum window length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
```

------------------------------------------------------------------------

## Approach: Sliding Window + HashSet

We use the **Sliding Window** technique with a **HashSet** to keep track
of the characters currently inside the window.

A window is the continuous part of the string between two pointers:

-   `left`: points to the beginning of the current window.
-   `right`: moves through the string and expands the window.
-   `set`: stores the characters currently present in the window.
-   `maxLength`: stores the largest valid window length found so far.

### Why this pattern?

A brute-force approach would generate many substrings and check each one
for duplicate characters. That repeats work and can take quadratic or
worse time.

Sliding Window is suitable because we need a **continuous substring**
and can maintain a valid range while moving through the string. The
HashSet lets us check whether a character is already in the current
window efficiently.

When a duplicate is found, move `left` forward and remove characters
from the set until the duplicate is no longer present. Then add the
current character and update the answer.

Each character is added to the set once and removed at most once, so the
solution is efficient.

------------------------------------------------------------------------

## Algorithm

1.  Create an empty `HashSet<Character>` to store characters in the
    current window.
2.  Initialize `left = 0` and `maxLength = 0`.
3.  Iterate through the string using `right`, from index `0` to
    `s.length() - 1`.
4.  While the current character `s.charAt(right)` is already in the set:
    -   Remove `s.charAt(left)` from the set.
    -   Increment `left` to shrink the window from the beginning.
5.  Add the current character to the set.
6.  Calculate the current window length using `right - left + 1`.
7.  Update `maxLength` if the current window is longer.
8.  After the loop, return `maxLength`.

------------------------------------------------------------------------

## Step-by-Step Traversal

Let's dry-run the algorithm for:

``` text
s = "abcabcbb"
```

  -----------------------------------------------------------------------------
       `right`  Current char   Action      `left` after Current     `maxLength`
                                                 action window    
  ------------ --------------- ----------- ------------ --------- -------------
             0        a        Add `a`                0 `a`                   1

             1        b        Add `b`                0 `ab`                  2

             2        c        Add `c`                0 `abc`                 3

             3        a        Duplicate              1 `bca`                 3
                               `a`; remove                        
                               `a`, move                          
                               `left`,                            
                               then add                           
                               `a`                                

             4        b        Duplicate              2 `cab`                 3
                               `b`; remove                        
                               `b`, move                          
                               `left`,                            
                               then add                           
                               `b`                                

             5        c        Duplicate              3 `abc`                 3
                               `c`; remove                        
                               `c`, move                          
                               `left`,                            
                               then add                           
                               `c`                                

             6        b        Duplicate              5 `cb`                  3
                               `b`; remove                        
                               `a`, then                          
                               `b`; add                           
                               `b`                                

             7        b        Duplicate              7 `b`                   3
                               `b`; remove                        
                               `c`, then                          
                               `b`; add                           
                               `b`                                
  -----------------------------------------------------------------------------

The maximum valid window length is `3`, so the answer is:

``` text
Output: 3
```

### Visual understanding

For `abcabcbb`, the window expands while all characters are unique:

``` text
a
ab
abc  <- maximum length = 3
```

When the next `a` appears, the window shrinks from the left until the
previous `a` is removed. The process repeats for each character.

------------------------------------------------------------------------

## Complexity Analysis

Let `n` be the length of the input string.

### Time Complexity: `O(n)` --- Linear

-   The `right` pointer traverses the string once.
-   The `left` pointer only moves forward; it never moves backward.
-   Every character is added to the HashSet at most once and removed at
    most once.
-   HashSet `add`, `remove`, and `contains` operations take `O(1)`
    average time.

Therefore, the total time complexity is **O(n)** on average.

### Space Complexity: `O(min(n, k))`

-   The HashSet stores only the characters in the current window.
-   `n` is the length of the string.
-   `k` is the size of the possible character set.

Therefore, the space complexity is **O(min(n, k))**. Since the input is
limited to English letters, digits, symbols, and spaces, the character
set is bounded; in general, the set can contain at most `n` characters.

------------------------------------------------------------------------

## Pattern Used

**Sliding Window (Variable Size) + HashSet**

  -----------------------------------------------------------------------
  Pattern / Data Structure            Purpose
  ----------------------------------- -----------------------------------
  Sliding Window                      Maintains a continuous substring
                                      and adjusts its boundaries

  Two Pointers                        `left` and `right` define the
                                      current window

  HashSet                             Detects duplicate characters in the
                                      current window

  Greedy window adjustment            Shrinks the window only when a
                                      duplicate appears
  -----------------------------------------------------------------------

### Key takeaway

Whenever a problem asks for the **longest or shortest continuous
substring/subarray** satisfying a condition, consider the Sliding Window
pattern.

If the condition involves checking whether elements are repeated, a
HashSet can help maintain the current window efficiently.

------------------------------------------------------------------------

## Edge Cases

  Input          Output Reason
  ------------ -------- --------------------------------------
  `""`                0 Empty string
  `"a"`               1 One character
  `"aaaa"`            1 Every character repeats
  `"abcdef"`          6 All characters are unique
  `"a b a"`           3 Space is also treated as a character

------------------------------------------------------------------------

## LeetCode

-   **Problem:** Longest Substring Without Repeating Characters
-   **Pattern:** Sliding Window + Two Pointers + HashSet
-   **Time Complexity:** `O(n)` average
-   **Space Complexity:** `O(min(n, k))`
