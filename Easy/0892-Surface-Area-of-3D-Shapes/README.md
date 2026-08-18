# Surface Area of 3D Shapes

## Problem

You are given an `n x n` grid where `grid[i][j]` represents the height of a tower made up of `1 x 1 x 1` cubes.

Adjacent cubes are glued together to form 3D shapes.

Return the **total surface area** of all resulting shapes.

The **bottom face** of every shape is also counted.

### Example

```text
Input:
grid = [[1,2],
        [3,4]]

Output:
34
```

---

## Solution

```java
class Solution {
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int area = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int h = grid[i][j];

                if (h > 0) {

                    // Top + Bottom + 4 vertical faces
                    area += 4 * h + 2;

                    // Shared surface with upper neighbor
                    if (i > 0) {
                        area -= 2 * Math.min(h, grid[i - 1][j]);
                    }

                    // Shared surface with left neighbor
                    if (j > 0) {
                        area -= 2 * Math.min(h, grid[i][j - 1]);
                    }
                }
            }
        }

        return area;
    }
}
```

---

# Approach

The main idea is:

> Calculate the surface area of every tower independently, then remove the surfaces that are hidden because two towers are touching.

For a tower of height `h`:

```text
Vertical surfaces = 4 × h
Top surface       = 1
Bottom surface    = 1

Total = 4 × h + 2
```

So for every non-zero cell:

```java
area += 4 * h + 2;
```

However, adjacent towers share some faces.

If two neighboring towers have heights:

```text
h1 = 3
h2 = 5
```

The smaller tower touches the larger tower over `3` cube faces.

Therefore:

```text
Shared surface = min(h1, h2)
```

Since the shared surface was counted once from each tower, we subtract:

```text
2 × min(h1, h2)
```

---

# Why `Math.min()`?

Suppose two adjacent towers are:

```text
   █
   █
   █
   █
█  █
█  █

h1 = 2
h2 = 4
```

Only the first `2` levels are touching.

Therefore:

```text
shared faces = min(2, 4)
             = 2
```

Because each shared face belongs to both towers:

```text
surface to subtract = 2 × 2
                    = 4
```

Hence:

```java
area -= 2 * Math.min(h, neighbor);
```

---

# Algorithm

### Step 1 — Traverse the grid

Use two nested loops:

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
```

This visits every tower exactly once.

---

### Step 2 — Get the tower height

```java
int h = grid[i][j];
```

If the height is `0`, there are no cubes, so skip it.

```java
if (h > 0)
```

---

### Step 3 — Add the independent tower surface

For height `h`:

```text
4 vertical sides = 4h
top               = 1
bottom            = 1
```

Therefore:

```java
area += 4 * h + 2;
```

---

### Step 4 — Check the upper neighbor

If `i > 0`, the current tower has a tower above it in the grid:

```java
if (i > 0) {
    area -= 2 * Math.min(h, grid[i - 1][j]);
}
```

The `Math.min()` gives the number of touching cube faces.

---

### Step 5 — Check the left neighbor

If `j > 0`, the current tower has a tower on its left:

```java
if (j > 0) {
    area -= 2 * Math.min(h, grid[i][j - 1]);
}
```

---

### Step 6 — Return the total

After processing every cell:

```java
return area;
```

---

# Traversal Strategy

The grid is traversed using **row-major order**.

```text
(0,0) → (0,1) → (0,2)
  ↓
(1,0) → (1,1) → (1,2)
  ↓
(2,0) → (2,1) → (2,2)
```

For every cell, we only check:

* Top/previous row neighbor
* Left/previous column neighbor

We don't need to check bottom and right neighbors because those pairs will be processed later.

---

# Why Only Top and Left?

Consider two adjacent cells:

```text
A B
```

When processing `A`, we don't check `B`.

When processing `B`, we check `A`.

So the shared surface is calculated exactly once.

Similarly:

```text
A
B
```

When processing `B`, we check `A`.

This prevents **double subtraction**.

---

# Dry Run

Consider:

```text
grid = [[1,2],
        [3,4]]
```

### Cell `(0,0)`

```text
h = 1

Base area = 4 × 1 + 2
          = 6
```

No top or left neighbor.

```text
area = 6
```

---

### Cell `(0,1)`

```text
h = 2

Base area = 4 × 2 + 2
          = 10
```

Left neighbor = `1`

Shared area:

```text
2 × min(2,1)
= 2
```

Therefore:

```text
area = 6 + 10 - 2
     = 14
```

---

### Cell `(1,0)`

```text
h = 3

Base area = 4 × 3 + 2
          = 14
```

Top neighbor = `1`

Shared area:

```text
2 × min(3,1)
= 2
```

Therefore:

```text
area = 14 + 14 - 2
     = 26
```

---

### Cell `(1,1)`

```text
h = 4

Base area = 4 × 4 + 2
          = 18
```

Top neighbor = `2`

```text
shared = 2 × min(4,2)
       = 4
```

Left neighbor = `3`

```text
shared = 2 × min(4,3)
       = 6
```

Therefore:

```text
area = 26 + 18 - 4 - 6
     = 34
```

### Final Answer

```text
34
```

---

# Pattern Used

## Pattern: Grid Traversal + Local Neighbor Comparison

This solution primarily uses:

```text
2D Grid Traversal
+
Neighbor Comparison
+
Overlap/Subtraction Technique
```

### Why this pattern?

Each cell only depends on:

```text
current cell
top neighbor
left neighbor
```

There is no need to maintain a separate 3D representation of all cubes.

Instead of creating every individual cube, we calculate the contribution mathematically.

This makes the solution much more efficient.

---

# Important Observation

A naive approach could create every cube physically.

For example:

```text
grid[i][j] = 50
```

means 50 cubes.

But the grid can contain:

```text
50 × 50 × 50 = 125,000
```

cubes in the largest possible single tower.

We don't need to create these cubes.

The formula:

```text
4 × h + 2
```

calculates the entire tower's contribution directly.

This is an example of **mathematical optimization**.

---

# Why Does the Formula Work?

For a tower of height `h`:

```text
       Top
        ↓
      ┌───┐
     /   /│
    ┌───┐ │
    │   │ │
    │   │ /
    └───┘/
        ↑
      Bottom
```

There are:

```text
Left side   = h
Right side  = h
Front side  = h
Back side   = h
```

Therefore:

```text
Vertical area = 4h
```

Plus:

```text
Top    = 1
Bottom = 1
```

Total:

```text
4h + 2
```

---

# Complexity Analysis

Let:

```text
n = grid.length
```

There are:

```text
n × n
```

cells.

Each cell performs only constant-time operations.

### Time Complexity

```text
O(n²)
```

Because every cell is visited exactly once.

For `n = 50`:

```text
50 × 50 = 2500
```

cells only.

---

### Space Complexity

```text
O(1)
```

Only a few variables are used:

```java
int n;
int area;
int h;
```

No additional array, list, stack, queue, or 3D structure is created.

---

# Complexity Summary

| Complexity | Value   |
| ---------- | ------- |
| Time       | `O(n²)` |
| Space      | `O(1)`  |

---

# Edge Cases

### 1. Single tower

```text
grid = [[5]]
```

Surface area:

```text
4 × 5 + 2 = 22
```

---

### 2. Zero-height cell

```text
grid = [[1,0],
        [0,1]]
```

The `0` cells contribute nothing.

---

### 3. Equal-height neighboring towers

```text
grid = [[2,2]]
```

Each tower initially contributes:

```text
6 + 6 = 12
```

The touching area is:

```text
2 × min(2,2)
= 4
```

Final:

```text
12 - 4 = 8
```

---

### 4. Different-height neighboring towers

```text
grid = [[2,5]]
```

Shared area:

```text
2 × min(2,5)
= 4
```

Only the smaller tower's side is hidden.

---

# Key Takeaways

1. Don't create individual cubes.
2. Calculate each tower's surface mathematically.
3. Every tower contributes `4 × height + 2`.
4. Adjacent towers hide shared faces.
5. Shared faces are calculated using `Math.min()`.
6. Each shared boundary is processed only once.
7. Row-major traversal makes duplicate calculations unnecessary.
8. The solution uses `O(n²)` time and `O(1)` extra space.

---

# Final Code

```java
class Solution {
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int area = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int h = grid[i][j];

                if (h > 0) {
                    area += 4 * h + 2;

                    if (i > 0) {
                        area -= 2 * Math.min(h, grid[i - 1][j]);
                    }

                    if (j > 0) {
                        area -= 2 * Math.min(h, grid[i][j - 1]);
                    }
                }
            }
        }

        return area;
    }
}
```

## Pattern

```text
2D Grid Traversal
      +
Neighbor Comparison
      +
Overlap Subtraction
      +
Mathematical Optimization
```

## Complexity

```text
Time  : O(n²)
Space : O(1)
```
