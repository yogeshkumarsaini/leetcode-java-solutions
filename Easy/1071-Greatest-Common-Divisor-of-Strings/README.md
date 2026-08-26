# GCD of Strings

## Problem Statement

For two strings `s` and `t`, we say that **`t` divides `s`** if `s` can be constructed by concatenating `t` one or more times.

Given two strings `str1` and `str2`, return the **largest string `x`** such that `x` divides both `str1` and `str2`.

### Example 1

```text
Input:
str1 = "ABCABC"
str2 = "ABC"

Output:
"ABC"
```

### Example 2

```text
Input:
str1 = "ABABAB"
str2 = "ABAB"

Output:
"AB"
```

### Example 3

```text
Input:
str1 = "LEET"
str2 = "CODE"

Output:
""
```

### Example 4

```text
Input:
str1 = "AAAAAB"
str2 = "AAA"

Output:
""
```

---

# Approach

The problem is similar to finding the **GCD (Greatest Common Divisor)** of two numbers.

For strings, we need to find the largest common repeating pattern.

There are two important steps:

1. Check whether `str1` and `str2` are made from the same repeating pattern.
2. If they are compatible, find the GCD of their lengths and return that prefix.

---

## Key Observation

Suppose:

```text
str1 = "ABCABC"
str2 = "ABC"
```

Both strings are constructed using:

```text
"ABC"
```

Also:

```text
str1 + str2 = "ABCABCABC"
str2 + str1 = "ABCABCABC"
```

Therefore:

```text
str1 + str2 == str2 + str1
```

This is a very useful condition.

### Why does this work?

If both strings are generated from the same base pattern, changing their concatenation order should not change the final string.

For example:

```text
str1 = "ABABAB"
str2 = "ABAB"

str1 + str2 = "ABABABABAB"
str2 + str1 = "ABABABABAB"
```

Both are equal.

But:

```text
str1 = "LEET"
str2 = "CODE"

str1 + str2 = "LEETCODE"
str2 + str1 = "CODELEET"
```

They are different.

Therefore, no common divisor string exists.

---

# Pattern Used

## GCD / Euclidean Algorithm Pattern

The main mathematical pattern used in this solution is:

> **Euclidean Algorithm for GCD**

We use GCD to find the largest possible length of a string that can divide both strings.

For example:

```text
str1.length() = 6
str2.length() = 4
```

Then:

```text
GCD(6, 4) = 2
```

So the answer can have a maximum length of `2`.

For:

```text
str1 = "ABABAB"
str2 = "ABAB"
```

The GCD of their lengths is:

```text
GCD(6, 4) = 2
```

Therefore:

```text
str1.substring(0, 2)
```

gives:

```text
"AB"
```

---

# Why This Pattern Is Used

We use the **GCD pattern** because the answer must repeat an integer number of times in both strings.

If the answer has length `L`, then:

```text
str1.length() % L == 0
str2.length() % L == 0
```

Therefore, `L` must be a common divisor of both string lengths.

The **largest possible value of `L`** is:

```text
GCD(str1.length(), str2.length())
```

So the GCD gives us the maximum possible length of the answer.

---

# Algorithm

### Step 1: Check String Compatibility

Check:

```java
(str1 + str2).equals(str2 + str1)
```

If false:

```text
return ""
```

because both strings cannot be generated from the same repeating pattern.

---

### Step 2: Find GCD of String Lengths

Calculate:

```text
gcd(str1.length(), str2.length())
```

For example:

```text
str1.length() = 6
str2.length() = 4

GCD(6, 4) = 2
```

---

### Step 3: Extract the Prefix

Take the first `gcdLength` characters from `str1`.

```java
str1.substring(0, gcdLength)
```

This is the largest string that divides both strings.

---

# Step-by-Step Traversal

Let's take:

```text
str1 = "ABABAB"
str2 = "ABAB"
```

### Step 1 — Concatenation Check

```text
str1 + str2
```

becomes:

```text
"ABABABABAB"
```

And:

```text
str2 + str1
```

becomes:

```text
"ABABABABAB"
```

Both are equal.

So a common divisor string exists.

---

### Step 2 — Find Lengths

```text
str1.length() = 6
str2.length() = 4
```

Now calculate:

```text
GCD(6, 4)
```

Using Euclidean Algorithm:

```text
6 % 4 = 2
4 % 2 = 0
```

Therefore:

```text
GCD = 2
```

---

### Step 3 — Extract Prefix

Take the first `2` characters:

```text
str1 = "ABABAB"
        ^^
```

Therefore:

```text
answer = "AB"
```

---

# Euclidean Algorithm Traversal

For:

```text
a = 6
b = 4
```

We repeatedly calculate:

```text
a % b
```

### Iteration 1

```text
6 % 4 = 2
```

Update:

```text
a = 4
b = 2
```

### Iteration 2

```text
4 % 2 = 0
```

Update:

```text
a = 2
b = 0
```

Now `b == 0`, so:

```text
GCD = 2
```

---

# Correctness

The solution is correct because:

1. If

```text
str1 + str2 != str2 + str1
```

then the strings cannot be generated from the same repeating base string. Therefore, the answer is empty.

2. If

```text
str1 + str2 == str2 + str1
```

then both strings are compatible with the same repeating pattern.

3. Any valid divisor string must have a length that divides both string lengths.

4. The largest such length is:

```text
GCD(str1.length(), str2.length())
```

5. The prefix of `str1` having this length is therefore the largest common divisor string.

---

# Java Solution

```java
class Solution {
    public String gcdOfStrings(String str1, String str2) {

        // Check whether both strings are made
        // from the same repeating pattern
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // Find GCD of both string lengths
        int gcdLength = gcd(str1.length(), str2.length());

        // Return the largest common divisor string
        return str1.substring(0, gcdLength);
    }

    // Euclidean Algorithm
    private int gcd(int a, int b) {

        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
```

---

# Complexity Analysis

Let:

```text
n = str1.length()
m = str2.length()
```

## Time Complexity

The concatenation comparison:

```java
(str1 + str2).equals(str2 + str1)
```

takes:

```text
O(n + m)
```

The GCD calculation takes:

```text
O(log(min(n, m)))
```

The substring operation takes:

```text
O(GCD(n, m))
```

Therefore, the overall time complexity is:

```text
O(n + m)
```

---

## Space Complexity

The algorithm itself uses only a few variables:

```text
a
b
temp
gcdLength
```

So auxiliary space is:

```text
O(1)
```

However, Java creates temporary concatenated strings for:

```java
str1 + str2
str2 + str1
```

Therefore, considering those temporary strings, the practical space usage can be:

```text
O(n + m)
```

### Final Complexity

```text
Time Complexity:  O(n + m)
Space Complexity: O(n + m)   // due to concatenated strings
Auxiliary Space:  O(1)
```

---

# Important Takeaway

The most important trick in this problem is:

```java
(str1 + str2).equals(str2 + str1)
```

If this condition is true, both strings share the same repeating pattern.

Then:

```java
gcd(str1.length(), str2.length())
```

gives the maximum length of the common pattern.

So the complete idea can be remembered as:

```text
1. Check concatenation compatibility
2. Find GCD of lengths
3. Return prefix of GCD length
```

### Short Formula

```text
if (str1 + str2 != str2 + str1)
    return ""

answer = str1.substring(0, GCD(length1, length2))
```

This combines the **String Pattern + GCD + Euclidean Algorithm** patterns into one efficient solution.
