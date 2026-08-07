# 507. Perfect Number

## Problem Statement

A **perfect number** is a positive integer that is equal to the sum of all its **positive divisors**, excluding the number itself.

A divisor of an integer `x` is a number that divides `x` without leaving a remainder.

Given an integer `num`, return **true** if it is a perfect number; otherwise return **false**.

### Example 1

**Input**

```text
num = 28
```

**Output**

```text
true
```

**Explanation**

```
Divisors of 28 (excluding itself):

1 + 2 + 4 + 7 + 14 = 28
```

Since the sum of its divisors equals the number itself, 28 is a perfect number.

---

### Example 2

**Input**

```text
num = 7
```

**Output**

```text
false
```

**Explanation**

```
Divisors of 7:

1

Sum = 1 ≠ 7
```

So, 7 is not a perfect number.

---

# Java Solution

```java
class Solution {
    public boolean checkPerfectNumber(int num) {

        if (num <= 1)
            return false;

        int sum = 1;

        for (int i = 2; i * i <= num; i++) {

            if (num % i == 0) {
                sum += i;

                if (i != num / i) {
                    sum += num / i;
                }
            }
        }

        return sum == num;
    }
}
```

---

# Approach

Instead of checking every number from **1 to num-1**, we only check divisors up to the **square root of the number**.

### Key Observation

Divisors always appear in **pairs**.

For example, for **28**:

| Divisor | Paired Divisor |
|---------|----------------|
| 1 | 28 |
| 2 | 14 |
| 4 | 7 |

When we find one divisor, we automatically know its paired divisor.

Therefore, we only need to iterate until √num.

This significantly reduces the number of iterations.

---

# Why Start Sum = 1?

Every positive integer greater than 1 has **1** as a divisor.

Since the problem asks us to exclude the number itself, we initialize:

```java
sum = 1;
```

Then we only search for the remaining divisors.

---

# Step-by-Step Traversal

## Example

```
num = 28
```

### Initial State

```
sum = 1
```

Loop condition:

```
i * i <= 28
```

So,

```
i = 2,3,4,5
```

---

### Iteration 1

```
i = 2
```

```
28 % 2 == 0
```

Divisor found.

Add

```
sum += 2
```

```
sum = 3
```

Pair divisor

```
28 / 2 = 14
```

Different from 2.

Add it.

```
sum = 17
```

---

### Iteration 2

```
i = 3
```

```
28 % 3 != 0
```

Skip.

```
sum = 17
```

---

### Iteration 3

```
i = 4
```

```
28 % 4 == 0
```

Add divisor

```
sum = 21
```

Pair divisor

```
28 / 4 = 7
```

Different from 4.

Add it.

```
sum = 28
```

---

### Iteration 4

```
i = 5
```

Condition

```
5 × 5 > 28
```

Loop ends.

---

Final

```
sum = 28
```

```
sum == num
```

Return

```
true
```

---

# Dry Run Table

For `num = 28`

| i | Divisible | Added | Sum |
|---|-----------|-------|-----|
|Start|-|1|1|
|2|Yes|2 + 14|17|
|3|No|-|17|
|4|Yes|4 + 7|28|

Final Answer:

```
true
```

---

# Why Check

```java
if (i != num / i)
```

Consider

```
num = 36
```

When

```
i = 6
```

Pair is also

```
36 / 6 = 6
```

Without this condition,

```
6
```

would be added twice.

To avoid duplicate counting, we check

```java
if (i != num / i)
```

---

# Algorithm

1. If `num <= 1`, return false.
2. Initialize `sum = 1`.
3. Iterate from `2` to `√num`.
4. If `i` divides the number:
   - Add `i`.
   - Add `num / i` if it is different from `i`.
5. After the loop, compare:
   - If `sum == num`, return true.
   - Otherwise return false.

---

# Correctness

The algorithm works because:

- Every divisor smaller than √num has exactly one paired divisor greater than √num.
- Every divisor pair is counted exactly once.
- Perfect squares are handled separately to avoid duplicate counting.
- The number itself is never added.
- The final sum contains exactly all proper divisors.

---

# Complexity Analysis

## Time Complexity

The loop runs only up to:

```
√n
```

Therefore,

**Time Complexity**

```
O(√n)
```

---

## Space Complexity

Only two integer variables are used.

```
sum
i
```

No extra array or collection is created.

**Space Complexity**

```
O(1)
```

---

# Pattern Used

## √N Divisor Enumeration (Factor Pair Technique)

This problem uses the **Square Root Divisor Enumeration** pattern.

### Why this pattern?

Because divisors always exist in pairs.

Example:

```
36

1 ↔ 36
2 ↔ 18
3 ↔ 12
4 ↔ 9
6 ↔ 6
```

Checking only until √N allows us to find both divisors together.

---

# Why is this Better than Brute Force?

### Brute Force

Check every number

```
1 → num-1
```

Time Complexity

```
O(n)
```

---

### Optimized Solution

Check only until

```
√num
```

Time Complexity

```
O(√n)
```

This is much faster for large inputs (up to 10⁸).

---

# Key Takeaways

- Every divisor has a paired divisor.
- Traverse only up to √N.
- Avoid double-counting for perfect squares.
- Exclude the number itself from the sum.
- Compare the divisor sum with the original number.
- Achieves **O(√n)** time with **O(1)** extra space.

---

## Complexity Summary

| Complexity | Value |
|------------|-------|
| Time | **O(√n)** |
| Space | **O(1)** |
