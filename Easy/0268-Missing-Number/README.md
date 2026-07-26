# 268. Missing Number

## Problem Statement

Given an array `nums` containing `n` distinct numbers in the range `[0, n]`, return the only number that is missing from the array.

**LeetCode:** 268 - Missing Number

---

# Approach (XOR)

### Intuition

The array contains all numbers from `0` to `n`, except one missing number.

Instead of calculating the sum, we can use the **XOR (^) operator**.

### Why XOR?

XOR has two important properties:

- `a ^ a = 0`
- `a ^ 0 = a`

If we XOR all numbers from `0` to `n` and also XOR every element of the array:

- Every existing number cancels itself.
- Only the missing number remains.

This gives an **O(n)** solution with **O(1)** extra space.

---

# Algorithm

1. Initialize `xor` with `nums.length` (which is `n`).
2. Traverse the array from index `0` to `n-1`.
3. For every index:
   - XOR the index with `xor`.
   - XOR the current array element with `xor`.
4. After the traversal, return `xor`.
5. The remaining value is the missing number.

---

# Step-by-Step Traversal

### Example

```
nums = [3,0,1]
```

Length:

```
n = 3
```

Initialize:

```
xor = 3
```

| i | xor ^= i | xor ^= nums[i] | xor Value |
|---|-----------|----------------|----------|
| Initial | - | - | 3 |
| 0 | 3 ^ 0 = 3 | 3 ^ 3 = 0 | 0 |
| 1 | 0 ^ 1 = 1 | 1 ^ 0 = 1 | 1 |
| 2 | 1 ^ 2 = 3 | 3 ^ 1 = 2 | 2 |

Final Answer:

```
2
```

---

# Dry Run

```
nums = [9,6,4,2,3,5,7,0,1]

n = 9

Initial xor = 9

After XORing all indices and array values,
every number appears twice and cancels out.

Remaining number = 8
```

---

# Correctness Proof

Numbers expected:

```
0,1,2,3,4,5,6,7,8,9
```

Array contains:

```
9,6,4,2,3,5,7,0,1
```

Both sets are XORed together.

```
0^0 = 0
1^1 = 0
2^2 = 0
3^3 = 0
4^4 = 0
5^5 = 0
6^6 = 0
7^7 = 0
9^9 = 0
```

Only:

```
8
```

remains.

Hence the algorithm always returns the missing number.

---

# Java Solution

```java
class Solution {
    public int missingNumber(int[] nums) {
        int xor = nums.length;

        for (int i = 0; i < nums.length; i++) {
            xor ^= i;
            xor ^= nums[i];
        }

        return xor;
    }
}
```

---

# Complexity Analysis

### Time Complexity

```
O(n)
```

- We traverse the array only once.

---

### Space Complexity

```
O(1)
```

- Only one integer variable (`xor`) is used.

---

# Pattern Used

## Bit Manipulation (XOR Pattern)

This problem belongs to the **Bit Manipulation** category.

### Why use this pattern?

Because XOR has the unique property of eliminating duplicate values.

Every number appears twice:

- Once from the complete range `0...n`
- Once from the array

These duplicate numbers cancel each other, leaving only the missing number.

This makes XOR one of the most efficient solutions.

---

# Alternative Approaches

## 1. Brute Force

- Search every number from `0` to `n`.
- Time: **O(n²)**
- Space: **O(1)**

---

## 2. Sorting

- Sort the array.
- Compare indices with values.
- Time: **O(n log n)**
- Space: **O(1)** or **O(log n)** depending on sorting algorithm.

---

## 3. Sum Formula

Expected Sum:

```
n × (n + 1) / 2
```

Missing Number:

```
Expected Sum − Actual Sum
```

Time: **O(n)**

Space: **O(1)**

---

## 4. XOR (Optimal ✅)

Time: **O(n)**

Space: **O(1)**

No overflow issue unlike the sum formula for very large values.

---

# Key Takeaways

- XOR removes duplicate numbers automatically.
- No sorting is required.
- No extra array or HashSet is required.
- Works in one traversal.
- Optimal solution for both time and space.

---

# Interview Tips

If the interviewer asks why XOR is preferred over the Sum Formula:

- XOR never suffers from integer overflow.
- Uses constant extra space.
- Runs in linear time.
- Demonstrates understanding of bit manipulation.

---

## Complexity Summary

| Approach | Time | Space |
|----------|------|-------|
| Brute Force | O(n²) | O(1) |
| Sorting | O(n log n) | O(1) |
| Sum Formula | O(n) | O(1) |
| XOR (Optimal) | **O(n)** | **O(1)** ✅ |

