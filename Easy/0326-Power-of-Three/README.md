# 326. Power of Three

## Problem Statement

Given an integer `n`, return `true` if it is a power of **3**. Otherwise, return `false`.

An integer `n` is a power of three if there exists an integer `x` such that:

```
n = 3^x
```

---

## Example

### Example 1

```text
Input: n = 27
Output: true

Explanation:
27 = 3 × 3 × 3 = 3³
```

### Example 2

```text
Input: n = 0
Output: false
```

### Example 3

```text
Input: n = -1
Output: false
```

---

# Approach 1 — Repeated Division (Recommended)

## Intuition

A power of three can be divided by `3` repeatedly until it becomes `1`.

For example,

```
27
↓ divide by 3
9
↓ divide by 3
3
↓ divide by 3
1
```

Since it reaches `1`, it is a power of three.

If at any point the number is **not divisible by 3**, then it is **not** a power of three.

---

# Algorithm

1. If `n <= 0`, return `false`.
2. While `n % 3 == 0`
   - Divide `n` by `3`.
3. After the loop,
   - If `n == 1`, return `true`.
   - Otherwise return `false`.

---

# Step-by-Step Traversal

## Input

```
n = 81
```

### Initial

```
n = 81
```

### Iteration 1

```
81 % 3 == 0

81 / 3 = 27
```

### Iteration 2

```
27 % 3 == 0

27 / 3 = 9
```

### Iteration 3

```
9 % 3 == 0

9 / 3 = 3
```

### Iteration 4

```
3 % 3 == 0

3 / 3 = 1
```

Loop ends.

```
n == 1

Return true
```

---

# Dry Run

|Current n|Divisible by 3?|New n|
|---------:|:-------------:|----:|
|81|Yes|27|
|27|Yes|9|
|9|Yes|3|
|3|Yes|1|

Result

```
true
```

---

# Java Solution

```java
class Solution {
    public boolean isPowerOfThree(int n) {

        if (n <= 0) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}
```

---

# Complexity Analysis

### Time Complexity

```
O(log₃ n)
```

Reason:

Every iteration divides the number by `3`.

---

### Space Complexity

```
O(1)
```

Only one variable is used.

---

# Pattern Used

## Mathematical Reduction Pattern

### Why?

Instead of generating powers of three,

we continuously reduce the number by dividing by `3`.

Every division removes one power of three.

This is a common mathematical reduction technique.

---

# Follow-Up (Without Loop / Recursion)

There is a mathematical trick.

The largest power of three within the 32-bit signed integer range is:

```
3^19 = 1162261467
```

If `n` is a power of three, then it must divide this largest power exactly.

---

## Java Solution (Without Loop)

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
```

---

# Why Does This Work?

Largest power:

```
1162261467 = 3^19
```

All smaller powers of three are factors of this number.

Example

```
1162261467 % 27 == 0

true
```

But

```
1162261467 % 45 != 0

false
```

---

# Complexity (Follow-Up)

### Time Complexity

```
O(1)
```

Only one modulo operation.

### Space Complexity

```
O(1)
```

---

# Comparison

|Approach|Time|Space|Loop|
|---------|----|-----|----|
|Repeated Division|O(log₃ n)|O(1)|Yes|
|Largest Power Trick|O(1)|O(1)|No|

---

# Key Takeaways

- Check `n > 0` first.
- Repeatedly divide by `3`.
- If the final value becomes `1`, it is a power of three.
- The follow-up can be solved in **constant time** using the largest power of three (`1162261467`).
- The optimal follow-up solution avoids both loops and recursion.

---

# Interview Tips

If asked in an interview:

1. Start with the repeated division solution.
2. Explain why it works.
3. Mention its complexity (`O(log₃ n)`).
4. Then impress the interviewer by discussing the mathematical trick using the largest power of three for an `O(1)` solution without loops or recursion.