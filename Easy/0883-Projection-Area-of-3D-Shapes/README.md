# Projection Area of 3D Shapes

## Problem

You are given an `n x n` grid where `grid[i][j]` represents the height of a tower made up of `1 x 1 x 1` cubes.

We need to calculate the total area of the projections of the 3D shape onto:

* **XY plane** — Top view
* **YZ plane** — Front view
* **ZX plane** — Side view

The answer is:

```text
Total Projection Area = XY Projection + YZ Projection + ZX Projection
```

---

## Example

### Example 1

```text
Input:
grid = [[1,2],
        [3,4]]

Output:
17
```

### Example 2

```text
Input:
grid = [[2]]

Output:
5
```

### Example 3

```text
Input:
grid = [[1,0],
        [0,2]]

Output:
8
```

---

## Approach

We calculate the three projections separately.

### 1. XY Projection — Top View

When looking from the top, every cell having at least one cube contributes exactly `1` to the projection.

Therefore:

```java
if (grid[i][j] > 0) {
    xy++;
}
```

For example:

```text
grid = [[1,0],
        [0,2]]
```

There are two non-zero cells, so:

```text
XY = 2
```

---

### 2. YZ Projection — Front View

For each row, only the tallest tower is visible from the front.

So for every row we calculate:

```java
rowMax = Math.max(rowMax, grid[i][j]);
```

Then add it to `yz`:

```java
yz += rowMax;
```

Example:

```text
grid = [[1,2],
        [3,4]]
```

Row maximums:

```text
Row 1 → max(1,2) = 2
Row 2 → max(3,4) = 4
```

Therefore:

```text
YZ = 2 + 4 = 6
```

---

### 3. ZX Projection — Side View

For each column, only the tallest tower contributes to the side projection.

While traversing each row, we access the corresponding column using:

```java
grid[j][i]
```

and calculate:

```java
colMax = Math.max(colMax, grid[j][i]);
```

Then:

```java
zx += colMax;
```

For:

```text
grid = [[1,2],
        [3,4]]
```

Column maximums:

```text
Column 1 → max(1,3) = 3
Column 2 → max(2,4) = 4
```

Therefore:

```text
ZX = 3 + 4 = 7
```

---

## Algorithm

1. Initialize three variables:

   ```text
   xy = 0
   yz = 0
   zx = 0
   ```

2. Traverse every row of the grid.

3. For each row:

   * Initialize `rowMax = 0`.
   * Initialize `colMax = 0`.

4. Traverse every column:

   * If `grid[i][j] > 0`, increment `xy`.
   * Update the maximum height of the current row.
   * Update the maximum height of the current column.

5. After processing the row:

   * Add `rowMax` to `yz`.
   * Add `colMax` to `zx`.

6. Return:

   ```text
   xy + yz + zx
   ```

---

## Step-by-Step Traversal

Consider:

```text
grid = [[1,2],
        [3,4]]
```

### Initial State

```text
xy = 0
yz = 0
zx = 0
```

### i = 0

Row:

```text
[1,2]
```

Start:

```text
rowMax = 0
colMax = 0
```

#### j = 0

```text
grid[0][0] = 1
```

Since `1 > 0`:

```text
xy = 1
```

Update:

```text
rowMax = max(0,1) = 1
colMax = max(0, grid[0][0]) = 1
```

#### j = 1

```text
grid[0][1] = 2
```

Since `2 > 0`:

```text
xy = 2
```

Update:

```text
rowMax = max(1,2) = 2
colMax = max(1, grid[1][0]) = 3
```

After row 0:

```text
yz += 2
zx += 3
```

So:

```text
xy = 2
yz = 2
zx = 3
```

---

### i = 1

Row:

```text
[3,4]
```

Start:

```text
rowMax = 0
colMax = 0
```

#### j = 0

```text
grid[1][0] = 3
```

```text
xy = 3
```

```text
rowMax = 3
colMax = 2
```

#### j = 1

```text
grid[1][1] = 4
```

```text
xy = 4
```

```text
rowMax = 4
colMax = 4
```

After row 1:

```text
yz += 4
zx += 4
```

Final:

```text
xy = 4
yz = 6
zx = 7
```

Therefore:

```text
Answer = 4 + 6 + 7
       = 17
```

---

## Java Solution

```java
class Solution {
    public int projectionArea(int[][] grid) {

        int xy = 0;
        int yz = 0;
        int zx = 0;

        int n = grid.length;

        for (int i = 0; i < n; i++) {

            int rowMax = 0;
            int colMax = 0;

            for (int j = 0; j < n; j++) {

                // XY Projection - Top View
                if (grid[i][j] > 0) {
                    xy++;
                }

                // YZ Projection - Row Maximum
                rowMax = Math.max(rowMax, grid[i][j]);

                // ZX Projection - Column Maximum
                colMax = Math.max(colMax, grid[j][i]);
            }

            yz += rowMax;
            zx += colMax;
        }

        return xy + yz + zx;
    }
}
```

---

## Why `grid[j][i]` for Column Maximum?

Normally, when traversing:

```java
grid[i][j]
```

we are processing row `i`.

To process column `i`, we reverse the indexes:

```java
grid[j][i]
```

For example:

```text
grid =

1  2
3  4
```

For `i = 0`:

```text
grid[0][0] = 1
grid[1][0] = 3
```

This gives the first column:

```text
[1, 3]
```

For `i = 1`:

```text
grid[0][1] = 2
grid[1][1] = 4
```

This gives the second column:

```text
[2, 4]
```

Therefore `grid[j][i]` allows us to find the maximum height of each column.

---

## Pattern Used

### Pattern: Matrix Traversal + Row/Column Aggregation

This solution uses the **2D Matrix Traversal** pattern.

More specifically, it combines:

```text
Matrix Traversal
      ↓
Row Maximum
      +
Column Maximum
      +
Count Non-Zero Cells
```

### Why this pattern?

The three projections have different requirements:

| Projection | What we need            |
| ---------- | ----------------------- |
| XY         | Count non-zero cells    |
| YZ         | Maximum of every row    |
| ZX         | Maximum of every column |

Instead of performing three separate traversals, all three calculations can be performed during one nested-loop traversal.

This makes the solution simple and efficient.

---

## Optimization

A straightforward solution could calculate the three projections using separate loops:

```text
XY → O(n²)
YZ → O(n²)
ZX → O(n²)
```

Total:

```text
O(n²) + O(n²) + O(n²)
= O(3n²)
= O(n²)
```

The provided solution combines all calculations into one traversal:

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        ...
    }
}
```

So practically, the grid is traversed only once.

---

## Complexity Analysis

### Time Complexity

The grid contains:

```text
n × n = n²
```

cells.

Each cell is processed once.

Therefore:

```text
Time Complexity = O(n²)
```

---

### Space Complexity

We only use a few variables:

```java
xy
yz
zx
rowMax
colMax
n
```

No additional array or matrix is created.

Therefore:

```text
Space Complexity = O(1)
```

---

## Complexity Summary

| Complexity | Value     |
| ---------- | --------- |
| Time       | **O(n²)** |
| Space      | **O(1)**  |

---

## Key Insight

The most important observation is:

```text
Top View    → Count cells > 0
Front View  → Sum maximum of every row
Side View   → Sum maximum of every column
```

So the complete formula is:

```text
Answer =
    Number of non-zero cells
    + Sum of row maximums
    + Sum of column maximums
```

This allows the problem to be solved with a single `O(n²)` matrix traversal and `O(1)` extra space.

---

## Edge Cases

### Single Cell

```text
grid = [[2]]
```

```text
XY = 1
YZ = 2
ZX = 2

Answer = 1 + 2 + 2 = 5
```

### Zero Values

```text
grid = [[1,0],
        [0,2]]
```

Only non-zero cells contribute to the top projection.

```text
XY = 2
YZ = 1 + 2 = 3
ZX = 1 + 2 = 3

Answer = 8
```

### All Zeros

If:

```text
grid = [[0,0],
        [0,0]]
```

then:

```text
XY = 0
YZ = 0
ZX = 0
```

So the result is:

```text
0
```

---

## Conclusion

This problem is a good example of **2D matrix traversal with row and column aggregation**.

The key technique is to avoid storing extra information and calculate:

* non-zero cells for the top projection,
* row maximums for the front projection,
* column maximums for the side projection,

during the same nested-loop traversal.

### Final Complexity

```text
Time  : O(n²)
Space : O(1)
```

**Pattern:** Matrix Traversal + Row/Column Maximum + Counting Non-Zero Elements
