# 14. Longest Common Prefix

> **LeetCode #14 | Easy**

## Problem Statement

Write a function to find the **longest common prefix** string amongst an array of strings.

If there is no common prefix, return an empty string `""`.

### Example 1

**Input**

```text
strs = ["flower","flow","flight"]
```

**Output**

```text
"fl"
```

### Example 2

**Input**

```text
strs = ["dog","racecar","car"]
```

**Output**

```text
""
```

---

# Java Solution

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {

            int j = 0;

            while (j < prefix.length()
                    && j < strs[i].length()
                    && prefix.charAt(j) == strs[i].charAt(j)) {

                j++;
            }

            prefix = prefix.substring(0, j);

            if (prefix.isEmpty()) {
                return "";
            }
        }

        return prefix;
    }
}
```

---

# Approach

The idea is to use the **first string as the initial prefix**.

Then compare this prefix with every remaining string one by one.

Whenever characters stop matching, shorten the prefix up to the matched position.

Repeat this process until all strings have been checked.

If at any point the prefix becomes empty, there is no common prefix, so immediately return `""`.

---

# Algorithm

1. Take the first string as the initial prefix.
2. Traverse the remaining strings one by one.
3. For each string:
   - Compare characters of the current prefix and current string.
   - Continue while:
     - Characters are equal.
     - Both indices are within their string lengths.
4. Count the number of matching characters.
5. Update the prefix using:

   ```java
   prefix = prefix.substring(0, matchedLength);
   ```

6. If the prefix becomes empty:
   - Return `""`.
7. After all strings are processed, return the final prefix.

---

# Step-by-Step Traversal

### Input

```text
["flower","flow","flight"]
```

### Initial

```text
prefix = "flower"
```

---

## Iteration 1

Current String

```text
flow
```

Comparison

| Prefix | Current | Match |
|---------|----------|-------|
| f | f | ✅ |
| l | l | ✅ |
| o | o | ✅ |
| w | w | ✅ |
| e | End | ❌ |

Updated Prefix

```text
flow
```

---

## Iteration 2

Current String

```text
flight
```

Comparison

| Prefix | Current | Match |
|---------|----------|-------|
| f | f | ✅ |
| l | l | ✅ |
| o | i | ❌ |

Updated Prefix

```text
fl
```

---

All strings processed.

Final Answer

```text
fl
```

---

# Dry Run

```
prefix = flower

↓

Compare with flow

flower
flow

Matching = flow

↓

prefix = flow

↓

Compare with flight

flow
flight

Matching = fl

↓

prefix = fl

↓

Answer = fl
```

---

# Why This Approach Works

The longest common prefix must be common across **every string**.

If the prefix doesn't match with even one string, it cannot be part of the final answer.

Therefore, after comparing each string, we reduce the prefix to only the matching part.

The prefix keeps shrinking until it becomes the longest prefix common to all strings.

---

# Pattern Used

## Horizontal Scanning

This problem uses the **Horizontal Scanning Pattern**.

### Why?

We compare the first string with every other string **one by one**.

```
String 1
      ↓
String 2
      ↓
String 3
      ↓
String 4
```

At every step the prefix becomes smaller (or remains the same).

This is exactly the Horizontal Scanning approach.

---

# Complexity Analysis

Let

- **N** = Number of strings
- **M** = Length of the shortest/common prefix

## Time Complexity

```
O(N × M)
```

### Explanation

For every string, we compare characters until they stop matching.

Worst case:

```
N strings

×

M characters

=

O(N × M)
```

---

## Space Complexity

```
O(1)
```

### Explanation

Only a few variables (`prefix`, `i`, `j`) are used.

No extra data structures are created.

(The returned substring is considered output, so auxiliary space remains constant.)

---

# Key Observations

- Start with the first string as the prefix.
- Compare it with every remaining string.
- Shrink the prefix whenever characters differ.
- Stop early if the prefix becomes empty.
- Efficient because unnecessary comparisons are avoided once the prefix shortens.

---

# Interview Explanation

Suppose the strings are

```
flower
flow
flight
```

First assume

```
prefix = flower
```

Compare with

```
flow
```

Now the common prefix becomes

```
flow
```

Next compare with

```
flight
```

Now only

```
fl
```

matches.

Since every string has `"fl"` at the beginning, this is the answer.

---

## Final Complexity

| Complexity | Value |
|------------|-------|
| Time | **O(N × M)** |
| Space | **O(1)** |

y.