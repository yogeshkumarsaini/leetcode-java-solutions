# 9. Palindrome Number

## Problem Statement

Given an integer `x`, return `true` if `x` is a palindrome, otherwise return `false`.

A palindrome number reads the same from left to right and from right to left.

---

## Examples

### Example 1

**Input**

```text
x = 121
```

**Output**

```text
true
```

**Explanation**

```
121 → Reverse = 121
Both are equal.
```

---

### Example 2

**Input**

```text
x = -121
```

**Output**

```text
false
```

**Explanation**

```
Reverse becomes 121-
Negative numbers cannot be palindromes.
```

---

### Example 3

**Input**

```text
x = 10
```

**Output**

```text
false
```

**Explanation**

```
Reverse = 01 = 1
10 != 1
```

---

# Intuition

A palindrome number remains exactly the same after reversing its digits.

So,

- Reverse the given integer.
- Compare the reversed number with the original number.
- If both are equal, it is a palindrome.

---

# Approach

1. Store the original number.
2. Reverse the digits using modulo (`%`) and division (`/`).
3. Compare the reversed number with the original.
4. Return `true` if both are equal; otherwise return `false`.

---

# Algorithm

### Step 1

Store the original number.

```text
original = x
```

---

### Step 2

Initialize the reversed number.

```text
reverse = 0
```

---

### Step 3

Repeat until the number becomes 0.

- Extract the last digit.
- Append it to the reversed number.
- Remove the last digit from the original number.

```text
digit = x % 10
reverse = reverse × 10 + digit
x = x / 10
```

---

### Step 4

Compare

```text
original == reverse
```

If equal → return `true`

Else → return `false`

---

# Step-by-Step Dry Run

## Input

```
x = 121
```

### Initial State

| Variable | Value |
|----------|------:|
| original | 121 |
| reverse | 0 |

---

### Iteration 1

```
digit = 121 % 10 = 1
reverse = 0 × 10 + 1 = 1
x = 121 / 10 = 12
```

| x | digit | reverse |
|--:|------:|--------:|
|12|1|1|

---

### Iteration 2

```
digit = 12 % 10 = 2
reverse = 1 × 10 + 2 = 12
x = 12 / 10 = 1
```

| x | digit | reverse |
|--:|------:|--------:|
|1|2|12|

---

### Iteration 3

```
digit = 1 % 10 = 1
reverse = 12 × 10 + 1 = 121
x = 0
```

| x | digit | reverse |
|--:|------:|--------:|
|0|1|121|

---

Final Comparison

```
121 == 121
```

Return

```
true
```

---

# Complexity Analysis

## Time Complexity

Let **n** be the number of digits.

Each digit is processed exactly once.

```
Time Complexity = O(n)
```

---

## Space Complexity

Only a few integer variables are used.

```
Space Complexity = O(1)
```

---

# Pattern Used

## Number Reversal Pattern

This problem uses the **Number Reversal Pattern**.

### Why?

Because we reverse the digits one by one using:

- Modulo (`%`) → Extract last digit
- Division (`/`) → Remove last digit
- Multiplication (`×10`) → Build the reversed number

This pattern is commonly used in problems like:

- Reverse Integer
- Palindrome Number
- Armstrong Number
- Sum of Digits
- Count Digits
- Happy Number

---

# Java Solution

```java
class Solution {
    public boolean isPalindrome(int x) {

        if (x < 0) return false;

        int n = x;
        int rev = 0;

        while (x > 0) {
            int rem = x % 10;
            rev = rev * 10 + rem;
            x /= 10;
        }

        return n == rev;
    }
}
```

---

# Key Takeaways

- Store the original number before modifying it.
- Reverse the digits using modulo and division.
- Compare the reversed number with the original.
- This solution uses constant extra space.
- It avoids converting the integer into a string.