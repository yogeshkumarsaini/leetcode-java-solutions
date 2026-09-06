# Find the Index of the First Occurrence in a String

## Problem Statement

Given two strings `haystack` and `needle`, return the index of the **first occurrence** of `needle` in `haystack`.

If `needle` is not part of `haystack`, return `-1`.

### Example 1

```text
Input:
haystack = "sadbutsad"
needle = "sad"

Output:
0
```

Explanation:

`"sad"` occurs at index `0` and `6`.

The first occurrence is at index `0`, so we return `0`.

### Example 2

```text
Input:
haystack = "leetcode"
needle = "leeto"

Output:
-1
```

Explanation:

`"leeto"` does not occur in `"leetcode"`.

---

# Approach

We use the **Naive String Matching (Brute Force)** approach.

The idea is:

1. Start from every possible position in `haystack`.
2. Compare `needle` character-by-character with the substring of `haystack` starting at that position.
3. If all characters match, return that starting index.
4. If any character does not match, move to the next position.
5. If no position contains the complete `needle`, return `-1`.

### Pattern Used

**Pattern: Naive / Brute Force String Matching**

We use this pattern because the problem simply asks us to find whether one string exists inside another and return its first position.

At every possible starting index, we directly compare the characters of `needle` with `haystack`.

This approach is simple, easy to understand, and does not require any additional data structure.

---

# Algorithm

Suppose:

```text
haystack = "sadbutsad"
needle   = "sad"
```

### Step 1

Calculate the maximum possible starting index:

```text
haystack.length() - needle.length()
```

For this example:

```text
9 - 3 = 6
```

So we only need to check indexes:

```text
0, 1, 2, 3, 4, 5, 6
```

There is no need to check index `7` or `8` because three characters cannot fit there.

### Step 2

For every possible starting index `i`:

```java
int j = 0;
```

Start comparing characters.

### Step 3

Compare:

```java
haystack.charAt(i + j)
```

with:

```java
needle.charAt(j)
```

If they are equal:

```text
j++
```

Continue comparing.

### Step 4

If:

```java
j == needle.length()
```

then the complete `needle` has been found.

Return:

```java
i
```

### Step 5

If the complete `needle` is never found, return:

```java
-1
```

---

# Step-by-Step Traversal

Consider:

```text
haystack = "sadbutsad"
needle   = "sad"
```

## Iteration 1

```text
i = 0
```

Compare:

```text
haystack[0] = s
needle[0]   = s
```

Match.

```text
haystack[1] = a
needle[1]   = a
```

Match.

```text
haystack[2] = d
needle[2]   = d
```

Match.

All characters matched.

```text
j == needle.length()
3 == 3
```

Therefore:

```text
return 0
```

We stop immediately because the problem asks for the **first occurrence**.

---

# Traversal Visualization

```text
haystack =  s a d b u t s a d
           ↑ ↑ ↑
needle  =  s a d

           Match!
           ↓
         index = 0
```

So the answer is:

```text
0
```

---

# Example Where Match Is Not Found

```text
haystack = "leetcode"
needle   = "leeto"
```

Start from index `0`.

```text
haystack = l e e t c o d
           ↑ ↑ ↑ ↑ ↑
needle   = l e e t o
```

Comparison:

```text
l == l  → Match
e == e  → Match
e == e  → Match
t == t  → Match
c != o  → Mismatch
```

The complete `needle` is not matched.

Move to the next starting index and continue checking.

Eventually, no complete match is found.

Therefore:

```text
return -1
```

---

# Java Solution

```java
class Solution {
    public int strStr(String haystack, String needle) {

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {

            int j = 0;

            while (j < needle.length() &&
                   haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }

            if (j == needle.length()) {
                return i;
            }
        }

        return -1;
    }
}
```

---

# Code Explanation

## Outer Loop

```java
for (int i = 0; i <= haystack.length() - needle.length(); i++)
```

The outer loop selects every possible starting position of `needle`.

For example:

```text
haystack.length() = 9
needle.length()   = 3
```

Then:

```text
i <= 9 - 3
i <= 6
```

So valid starting positions are:

```text
0 → 6
```

---

## Inner Loop

```java
int j = 0;

while (j < needle.length() &&
       haystack.charAt(i + j) == needle.charAt(j)) {
    j++;
}
```

The inner loop compares characters one by one.

For example:

```text
i = 0
j = 0

haystack.charAt(0 + 0) == needle.charAt(0)
        s             ==       s
```

Then:

```text
j = 1
```

Next:

```text
haystack.charAt(0 + 1) == needle.charAt(1)
        a             ==       a
```

And so on.

---

## Checking Complete Match

```java
if (j == needle.length()) {
    return i;
}
```

If `j` reaches the length of `needle`, it means every character matched.

Therefore, `i` is the starting index of the first occurrence.

---

## If No Match Exists

```java
return -1;
```

If the outer loop finishes without finding a match, `needle` does not exist inside `haystack`.

So we return `-1`.

---

# Dry Run

### Input

```text
haystack = "sadbutsad"
needle   = "sad"
```

### Traversal

| `i` | Comparison     | Result |
| --: | -------------- | ------ |
|   0 | `sad` vs `sad` | Match  |
|   1 | `adb` vs `sad` | No     |
|   2 | `dbu` vs `sad` | No     |
|   3 | `but` vs `sad` | No     |
|   4 | `uts` vs `sad` | No     |
|   5 | `tsa` vs `sad` | No     |
|   6 | `sad` vs `sad` | Match  |

Since the first match occurs at index `0`:

```text
Answer = 0
```

The algorithm stops immediately and does not need to check index `6`.

---

# Why Do We Use `i + j`?

This is an important part of the solution.

`i` represents the starting position in `haystack`.

`j` represents the current character position in `needle`.

Therefore:

```java
haystack.charAt(i + j)
```

means:

> Start from position `i` in `haystack` and move `j` positions forward.

Example:

```text
i = 2
j = 1

i + j = 3
```

So we compare:

```text
haystack[3]
```

with:

```text
needle[1]
```

---

# Why `i <= haystack.length() - needle.length()`?

We should only check positions where the complete `needle` can fit.

Suppose:

```text
haystack.length() = 9
needle.length() = 3
```

The last possible starting position is:

```text
9 - 3 = 6
```

At index `6`:

```text
6 7 8
```

Three characters are available.

But at index `7`:

```text
7 8
```

Only two characters are available, so `needle` cannot fit.

Therefore:

```java
i <= haystack.length() - needle.length()
```

is used.

---

# Complexity Analysis

Let:

```text
n = haystack.length()
m = needle.length()
```

## Time Complexity

### Worst Case

At every starting position, we may compare almost all characters of `needle`.

There can be approximately:

```text
n - m + 1
```

starting positions.

Each position can require up to:

```text
m
```

comparisons.

Therefore:

```text
O((n - m + 1) × m)
```

which is commonly expressed as:

```text
O(n × m)
```

### Time Complexity

```text
O(n × m)
```

---

## Space Complexity

We only use a few variables:

```java
i
j
```

No extra array, HashMap, List, or other data structure is used.

Therefore:

```text
O(1)
```

### Space Complexity

```text
O(1)
```

---

# Complexity Summary

| Complexity | Value      |
| ---------- | ---------- |
| Time       | `O(n × m)` |
| Space      | `O(1)`     |

Where:

```text
n = length of haystack
m = length of needle
```

---

# Pattern Summary

```text
Pattern:
Naive String Matching / Brute Force
```

### Why this pattern?

Because we:

* Check every possible starting position.
* Compare the pattern (`needle`) character-by-character.
* Stop when the complete pattern is found.
* Return the first matching index.
* Do not use extra memory.

---

# Key Takeaways

1. `i` represents the starting position in `haystack`.
2. `j` represents the current position in `needle`.
3. `i + j` is used to access the corresponding character in `haystack`.
4. We only check valid starting positions.
5. As soon as the complete `needle` matches, return `i`.
6. If no match is found, return `-1`.
7. This is a **Naive / Brute Force String Matching** approach.
8. Time Complexity: **O(n × m)**.
9. Space Complexity: **O(1)**.

---

# Complete Example

```java
class Solution {
    public int strStr(String haystack, String needle) {

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {

            int j = 0;

            while (j < needle.length() &&
                   haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }

            if (j == needle.length()) {
                return i;
            }
        }

        return -1;
    }
}
```

### Example

```text
Input:
haystack = "sadbutsad"
needle = "sad"

Output:
0
```

### Result

```text
First occurrence of "sad" is at index 0.
```
