# 231. Power of Two

## 📌 Problem Statement

Given an integer `n`, return **true** if it is a power of two. Otherwise, return **false**.

An integer `n` is a power of two if there exists an integer `x` such that:

> n = 2^x

---

## Examples

### Example 1

```text
Input: n = 1
Output: true

Explanation:
2^0 = 1
```

### Example 2

```text
Input: n = 16
Output: true

Explanation:
2^4 = 16
```

### Example 3

```text
Input: n = 3
Output: false
```

---

# Approach

A power of two has a unique property in binary representation.

Every power of two contains **exactly one bit set to 1**, while all remaining bits are 0.

Examples:

| Decimal | Binary |
|---------:|:------:|
| 1 | 0001 |
| 2 | 0010 |
| 4 | 0100 |
| 8 | 1000 |
| 16 | 10000 |

Now observe what happens when we subtract **1**.

| n | Binary |
|---|--------|
|8|1000|
|7|0111|

Performing **AND** operation:

```text
1000
0111
----
0000
```

The result becomes **0**.

This property is true for every power of two.

For numbers that are **not** powers of two:

Example:

```text
6 = 110
5 = 101

110
101
---
100
```

Result is **not zero**, so it is not a power of two.

---

# Why does `(n & (n - 1))` work?

Subtracting **1** from a power of two:

- changes the only `1` bit into `0`
- converts every bit to its right into `1`

Example:

```text
16 = 10000
15 = 01111
```

Now,

```text
10000
01111
-----
00000
```

Result = **0**

For every other number, more than one bit is set, so the AND operation cannot become zero.

---

# Algorithm

1. Check whether `n` is positive.
   - If `n <= 0`, return `false`.

2. Compute:

   ```java
   n & (n - 1)
   ```

3. If the result equals `0`, return `true`.

4. Otherwise return `false`.

---

# Step-by-Step Traversal

## Example 1

```text
n = 16
```

### Step 1

```text
Binary of 16

10000
```

### Step 2

```text
16 - 1 = 15

01111
```

### Step 3

```text
10000
01111
-----
00000
```

Result:

```text
0
```

Return

```text
true
```

---

## Example 2

```text
n = 8
```

Binary:

```text
1000
0111
----
0000
```

Return

```text
true
```

---

## Example 3

```text
n = 6
```

Binary:

```text
110
101
---
100
```

Result is not zero.

Return

```text
false
```

---

## Example 4

```text
n = 1
```

Binary:

```text
1
0
-
0
```

Return

```text
true
```

Because

```text
2^0 = 1
```

---

## Example 5

```text
n = -8
```

Since

```text
n <= 0
```

Immediately return

```text
false
```

---

# Dry Run

```text
Input:

n = 32

Step 1:
n > 0
Yes

Step 2:
n - 1 = 31

Binary:

32 -> 100000
31 -> 011111

Step 3:

100000
011111
------
000000

Result = 0

Return true
```

---

# Correctness Proof

A power of two has exactly one set bit in its binary representation.

Subtracting one:

- removes that single set bit
- turns all lower bits into 1

Therefore,

```text
n & (n - 1)
```

always equals

```text
0
```

for powers of two.

If `n` contains two or more set bits, at least one common set bit remains after the AND operation.

Hence,

```text
(n & (n - 1)) != 0
```

Therefore the algorithm correctly identifies whether `n` is a power of two.

---

# Pattern Used

## ✅ Bit Manipulation

This problem belongs to the **Bit Manipulation** pattern.

### Why use Bit Manipulation?

- Uses binary properties of numbers.
- Avoids loops.
- Avoids recursion.
- Requires only one bitwise operation.
- Constant-time solution.

This is the most efficient solution possible.

---

# Complexity Analysis

### Time Complexity

```text
O(1)
```

Only one comparison and one bitwise operation are performed.

---

### Space Complexity

```text
O(1)
```

No extra memory is used.

---

# Java Solution

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

---

# Key Takeaways

- A power of two has exactly one set bit.
- `(n & (n - 1))` removes the lowest set bit.
- If the result becomes `0`, only one set bit existed.
- Always check `n > 0` because zero and negative numbers cannot be powers of two.
- This is the optimal solution with **O(1)** time and **O(1)** space complexity.
