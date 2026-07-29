# 342. Power of Four

## Problem Statement

Given an integer `n`, return `true` if it is a **power of four**. Otherwise, return `false`.

An integer `n` is a power of four if there exists an integer `x` such that:

> n = 4^x

---

## Examples

### Example 1

**Input**

```text
n = 16
```

**Output**

```text
true
```

**Explanation**

```text
16 = 4²
```

---

### Example 2

**Input**

```text
n = 5
```

**Output**

```text
false
```

**Explanation**

```text
5 cannot be represented as 4^x.
```

---

### Example 3

**Input**

```text
n = 1
```

**Output**

```text
true
```

**Explanation**

```text
1 = 4⁰
```

---

# Approach

A number is a **power of four** if:

- It is positive.
- It can be divided by `4` repeatedly.
- After all divisions, the final value becomes exactly `1`.

If at any point the number is not divisible by `4`, then it cannot be a power of four.

---

# Intuition

Think of repeatedly removing one factor of `4`.

For example,

```text
64

64 ÷ 4 = 16
16 ÷ 4 = 4
4 ÷ 4 = 1

Reached 1
Therefore 64 is a power of four.
```

Now consider:

```text
20

20 ÷ 4 = 5

5 is not divisible by 4

Therefore false.
```

---

# Algorithm

1. If `n <= 0`, return `false`.
2. While `n` is divisible by `4`:
   - Divide `n` by `4`.
3. After the loop:
   - If `n == 1`, return `true`.
   - Otherwise return `false`.

---

# Step-by-Step Traversal

### Example: n = 64

| Step | Current n | Divisible by 4? | New n |
|------|-----------:|:---------------:|-------:|
| 1 | 64 | Yes | 16 |
| 2 | 16 | Yes | 4 |
| 3 | 4 | Yes | 1 |
| End | 1 | Stop | Return `true` |

---

### Example: n = 20

| Step | Current n | Divisible by 4? | New n |
|------|-----------:|:---------------:|-------:|
| 1 | 20 | Yes | 5 |
| 2 | 5 | No | Stop |
| End | 5 | Return `false` |

---

# Dry Run

### Input

```text
n = 16
```

Iteration 1

```text
16 % 4 == 0
n = 16 / 4 = 4
```

Iteration 2

```text
4 % 4 == 0
n = 4 / 4 = 1
```

Loop Ends

```text
n == 1

Return true
```

---

# Java Solution

```java
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;

        while (n % 4 == 0) {
            n /= 4;
        }

        return n == 1;
    }
}
```

---

# Complexity Analysis

## Time Complexity

Let the number be `n`.

Each iteration divides `n` by `4`.

Number of iterations:

```text
log₄(n)
```

Therefore,

```text
Time Complexity = O(log₄ n)
```

Since logarithm bases differ only by a constant,

```text
O(log₄ n) = O(log n)
```

---

## Space Complexity

Only one integer variable is used.

```text
Space Complexity = O(1)
```

---

# Pattern Used

## Mathematical Simulation (Repeated Division)

This problem follows the **Mathematical Simulation** pattern.

### Why?

- We continuously remove factors of `4`.
- No extra data structure is required.
- No recursion or dynamic programming is involved.
- The solution simply simulates the mathematical definition of powers of four.

---

# Follow-Up (Without Loop/Recursion)

A constant-time mathematical/bit manipulation solution exists.

Conditions:

1. Number must be positive.
2. Number must be a power of two.
3. The only set bit must be at an odd position (0, 2, 4, ...).

Example:

```java
class Solution {
    public boolean isPowerOfFour(int n) {
        return n > 0 &&
               (n & (n - 1)) == 0 &&
               (n & 0x55555555) != 0;
    }
}
```

### Complexity

- **Time:** `O(1)`
- **Space:** `O(1)`

---

# Key Takeaways

- A power of four remains divisible by `4` until it becomes `1`.
- If the final value is not `1`, the number is not a power of four.
- Repeated division provides a simple and intuitive solution.
- The follow-up can be solved in constant time using bit manipulation.