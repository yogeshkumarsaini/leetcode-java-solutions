# 263. Ugly Number

## Problem Statement

An **ugly number** is a positive integer whose prime factors are limited to **2, 3, and 5**.

Given an integer `n`, return `true` if `n` is an ugly number; otherwise, return `false`.

**LeetCode Problem:** 263 - Ugly Number

---

## Examples

### Example 1

**Input**

```text
n = 6
```

**Output**

```text
true
```

**Explanation**

```
6 = 2 × 3
```

Both prime factors are allowed.

---

### Example 2

**Input**

```text
n = 1
```

**Output**

```text
true
```

**Explanation**

```
1 has no prime factors.
```

By definition, 1 is considered an ugly number.

---

### Example 3

**Input**

```text
n = 14
```

**Output**

```text
false
```

**Explanation**

```
14 = 2 × 7
```

Since 7 is not one of the allowed prime factors (2, 3, or 5), it is **not** an ugly number.

---

# Approach

The only valid prime factors of an ugly number are:

- 2
- 3
- 5

The idea is simple:

- If `n` is divisible by **2**, keep dividing it by 2.
- Then remove every factor of **3**.
- Then remove every factor of **5**.
- If the remaining number becomes **1**, then every prime factor belonged to `{2,3,5}`.
- Otherwise, another prime factor exists, so the number is not ugly.

---

# Algorithm

1. If `n <= 0`, return `false`.
2. Divide `n` repeatedly by **2** while divisible.
3. Divide `n` repeatedly by **3** while divisible.
4. Divide `n` repeatedly by **5** while divisible.
5. If the remaining value is **1**, return `true`.
6. Otherwise, return `false`.

---

# Step-by-Step Traversal

## Example: n = 30

Initial value

```
30
```

### Remove factor 2

```
30 ÷ 2 = 15
```

Current value

```
15
```

### Remove factor 3

```
15 ÷ 3 = 5
```

Current value

```
5
```

### Remove factor 5

```
5 ÷ 5 = 1
```

Final value

```
1
```

Return

```text
true
```

---

## Example: n = 14

Initial value

```
14
```

### Remove factor 2

```
14 ÷ 2 = 7
```

Current value

```
7
```

Cannot divide by 3.

Cannot divide by 5.

Remaining value

```
7
```

Since the remaining number is not **1**, return

```text
false
```

---

# Dry Run

| Step | n | Operation |
|------|---|-----------|
| Start | 30 | Initial value |
| 1 | 15 | Divide by 2 |
| 2 | 5 | Divide by 3 |
| 3 | 1 | Divide by 5 |
| End | 1 | Return true |

---

# Java Solution

```java
class Solution {
    public boolean isUgly(int n) {
        if (n <= 0)
            return false;

        while (n % 2 == 0)
            n /= 2;

        while (n % 3 == 0)
            n /= 3;

        while (n % 5 == 0)
            n /= 5;

        return n == 1;
    }
}
```

---

# Complexity Analysis

### Time Complexity

Let the input number be **n**.

Each division reduces the number significantly.

Number of divisions:

- By 2 → at most **log₂(n)**
- By 3 → at most **log₃(n)**
- By 5 → at most **log₅(n)**

Overall,

```
O(log n)
```

---

### Space Complexity

No extra data structures are used.

```
O(1)
```

---

# Pattern Used

## Prime Factor Reduction

This problem follows the **Prime Factor Reduction** pattern.

The algorithm repeatedly removes all occurrences of the allowed prime factors from the number.

Instead of finding every prime factor using expensive factorization, we only care about the three allowed primes:

- 2
- 3
- 5

After removing them completely:

- Remaining value = **1** → Ugly Number ✅
- Remaining value > **1** → Contains another prime factor ❌

---

# Why This Pattern?

A brute-force factorization would unnecessarily check many prime numbers.

Since the problem explicitly states that only **2**, **3**, and **5** are allowed, removing these factors directly is the simplest and most efficient solution.

Benefits:

- Easy to understand
- Constant extra memory
- Logarithmic time complexity
- No prime generation required

---

# Edge Cases

| Input | Output | Reason |
|--------|--------|--------|
| 1 | true | Defined as ugly |
| 0 | false | Not positive |
| -5 | false | Negative number |
| 2 | true | Allowed prime |
| 3 | true | Allowed prime |
| 5 | true | Allowed prime |
| 8 | true | 2 × 2 × 2 |
| 25 | true | 5 × 5 |
| 14 | false | Contains prime factor 7 |
| 49 | false | Contains prime factor 7 |

---

# Key Takeaways

- Ugly numbers only contain prime factors **2, 3, and 5**.
- Continuously divide by these three numbers.
- If the final value becomes **1**, the number is ugly.
- Efficient solution with **O(log n)** time and **O(1)** space.
