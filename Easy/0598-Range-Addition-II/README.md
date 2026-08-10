# Maximum Count — LeetCode

## Problem

You are given an `m x n` matrix `M` initialized with all `0`s and an array of operations `ops`.

For each operation:

```text
ops[i] = [ai, bi]
```

the following cells are incremented by `1`:

```text
0 <= x < ai
0 <= y < bi
```

After performing all operations, return the **number of cells containing the maximum integer**.

---

## Example 1

### Input

```text
m = 3
n = 3
ops = [[2,2],[3,3]]
```

### Output

```text
4
```

### Explanation

Initially:

```text
0 0 0
0 0 0
0 0 0
```

After `[2,2]`:

```text
1 1 0
1 1 0
0 0 0
```

After `[3,3]`:

```text
2 2 1
2 2 1
1 1 1
```

The maximum value is `2`.

It occurs in:

```text
2 x 2 = 4
```

cells.

Therefore:

```text
Answer = 4
```

---

## Example 2

### Input

```text
m = 3
n = 3
ops = [[2,2],[3,3],[3,3],[3,3],
       [2,2],[3,3],[3,3],[3,3],
       [2,2],[3,3],[3,3],[3,3]]
```

### Output

```text
4
```

The smallest operation dimensions are:

```text
minRow = 2
minCol = 2
```

Therefore, the cells having the maximum value form a:

```text
2 x 2
```

rectangle.

So:

```text
2 * 2 = 4
```

---

## Example 3

### Input

```text
m = 3
n = 3
ops = []
```

### Output

```text
9
```

### Explanation

No operations are performed, so every cell remains `0`.

The maximum value is therefore `0`, and all `3 x 3` cells contain it.

```text
3 * 3 = 9
```

---

# Approach

The important observation is that we **do not need to actually create the matrix**.

Every operation increments a rectangular region starting from:

```text
(0, 0)
```

For example:

```text
[2, 2]
```

increments:

```text
0 <= x < 2
0 <= y < 2
```

So it affects the top-left `2 x 2` area.

Another operation:

```text
[3, 3]
```

affects the top-left `3 x 3` area.

For a cell to have the **maximum value**, it must be included in **every operation**.

Therefore, the common area of all operations determines the number of maximum cells.

The common rectangle has dimensions:

```text
minimum ai × minimum bi
```

So we only need to find:

```text
min(ai)
min(bi)
```

and return:

```text
min(ai) * min(bi)
```

---

# Why Minimum Row and Column?

Suppose:

```text
m = 4
n = 5

ops = [
    [3, 4],
    [2, 5],
    [4, 3]
]
```

The operations affect:

```text
Operation 1 → 3 x 4
Operation 2 → 2 x 5
Operation 3 → 4 x 3
```

The common area is limited by the smallest dimensions:

```text
minRow = min(3, 2, 4) = 2
minCol = min(4, 5, 3) = 3
```

Therefore, the maximum value occurs in:

```text
2 x 3
```

cells.

```text
Answer = 2 * 3 = 6
```

---

# Algorithm

1. If `ops` is empty:

   * No cells are incremented.
   * Every cell contains `0`.
   * Return `m * n`.

2. Initialize:

   ```java
   minRow = m;
   minCol = n;
   ```

3. Traverse every operation.

4. For each operation `[a, b]`:

   * Update the minimum row:

     ```java
     minRow = Math.min(minRow, a);
     ```
   * Update the minimum column:

     ```java
     minCol = Math.min(minCol, b);
     ```

5. After processing all operations, return:

   ```java
   minRow * minCol
   ```

---

# Step-by-Step Traversal

Consider:

```text
m = 3
n = 3

ops = [
    [2,2],
    [3,3]
]
```

### Step 1 — Initialization

```text
minRow = 3
minCol = 3
```

---

### Step 2 — First Operation

```text
[2,2]
```

Update:

```text
minRow = min(3, 2) = 2
minCol = min(3, 2) = 2
```

Now:

```text
minRow = 2
minCol = 2
```

---

### Step 3 — Second Operation

```text
[3,3]
```

Update:

```text
minRow = min(2, 3) = 2
minCol = min(2, 3) = 2
```

Final:

```text
minRow = 2
minCol = 2
```

---

### Step 4 — Calculate Answer

```text
minRow * minCol
```

```text
2 * 2 = 4
```

Therefore:

```text
Answer = 4
```

---

# Java Solution

```java
class Solution {

    public int maxCount(int m, int n, int[][] ops) {

        // If there are no operations,
        // all cells contain 0.
        if (ops.length == 0) {
            return m * n;
        }

        // Initially the entire matrix can be the
        // common maximum area.
        int minRow = m;
        int minCol = n;

        // Find the smallest dimensions among all operations.
        for (int i = 0; i < ops.length; i++) {

            int a = ops[i][0];
            int b = ops[i][1];

            minRow = Math.min(minRow, a);
            minCol = Math.min(minCol, b);
        }

        // The common area contains the maximum value.
        return minRow * minCol;
    }
}
```

---

# Pattern Used

## Pattern: Minimum Intersection / Common Overlap

This solution uses the **Minimum Intersection** pattern.

Each operation represents a rectangle beginning at `(0,0)`:

```text
[ai, bi]
```

For a cell to receive the maximum number of increments, it must belong to **all operation rectangles**.

Because every rectangle starts at `(0,0)`, their common intersection is simply:

```text
minimum row × minimum column
```

So instead of simulating every matrix update, we only track the smallest dimensions.

---

# Why This Pattern?

A brute-force approach would require:

1. Creating the entire matrix.
2. Applying every operation to the matrix.
3. Finding the maximum value.
4. Counting cells having that maximum.

This is inefficient because the matrix can contain up to:

```text
4 * 10^4 × 4 * 10^4
```

cells.

Instead, we observe that all operations start from the same top-left corner.

Therefore, only the **smallest row and column boundaries** matter.

This reduces the problem from matrix simulation to finding two minimum values.

---

# Complexity Analysis

Let:

```text
k = ops.length
```

## Time Complexity

We traverse the operations array once:

```text
O(k)
```

where `k = ops.length`.

There is no matrix traversal.

### Time Complexity

```text
O(ops.length)
```

---

## Space Complexity

We only use two variables:

```java
minRow
minCol
```

No additional matrix or data structure is created.

### Space Complexity

```text
O(1)
```

---

# Complexity Summary

| Complexity | Value           |
| ---------- | --------------- |
| Time       | `O(ops.length)` |
| Space      | `O(1)`          |

---

# Important Observation

The key idea can be summarized as:

```text
Maximum value
      ↓
Cells included in EVERY operation
      ↓
Common intersection of all rectangles
      ↓
Minimum row × Minimum column
```

Therefore:

```java
return minRow * minCol;
```

is sufficient.

---

# Edge Cases

### 1. No Operations

```text
ops = []
```

All cells remain `0`.

Answer:

```text
m * n
```

---

### 2. One Operation

```text
m = 5
n = 5
ops = [[2,3]]
```

Maximum value occurs in the affected rectangle:

```text
2 x 3
```

Answer:

```text
6
```

---

### 3. Repeated Operations

```text
ops = [
    [2,2],
    [2,2],
    [2,2]
]
```

The maximum value increases because the same area is updated repeatedly.

However, the **number of cells having the maximum value** remains:

```text
2 x 2 = 4
```

---

# Key Takeaway

We don't need to build or modify the matrix.

The cells with the maximum value are exactly the cells that are affected by **every operation**.

Since every operation starts from `(0,0)`, the common area is:

```text
min(ai) × min(bi)
```

This gives an efficient:

```text
Time  → O(ops.length)
Space → O(1)
```

solution.
