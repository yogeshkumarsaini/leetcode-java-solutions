# All Cells Dist Order

## Problem

You are given four integers:

* `rows` — number of rows in the matrix
* `cols` — number of columns in the matrix
* `rCenter` — row index of the center cell
* `cCenter` — column index of the center cell

Return the coordinates of **all cells** in the matrix sorted by their Manhattan distance from `(rCenter, cCenter)`.

### Manhattan Distance

The distance between two cells:

```text
(r1, c1) and (r2, c2)
```

is:

```text
|r1 - r2| + |c1 - c2|
```

The answer can contain cells with the same distance in any order.

---

## Example

### Example 1

```text
Input:
rows = 1
cols = 2
rCenter = 0
cCenter = 0

Output:
[[0,0],[0,1]]
```

Distances:

```text
(0,0) → |0-0| + |0-0| = 0
(0,1) → |0-0| + |1-0| = 1
```

So the sorted order is:

```text
[[0,0],[0,1]]
```

---

### Example 2

```text
Input:
rows = 2
cols = 2
rCenter = 0
cCenter = 1

Output:
[[0,1],[0,0],[1,1],[1,0]]
```

Distances:

```text
(0,1) → 0
(0,0) → 1
(1,1) → 1
(1,0) → 2
```

Therefore:

```text
[[0,1],[0,0],[1,1],[1,0]]
```

Any order between cells having the same distance is valid.

---

# Approach

We use a simple **Generate + Calculate Distance + Sort** approach.

The solution has two main steps:

1. Generate every cell of the matrix.
2. Sort all cells according to their Manhattan distance from `(rCenter, cCenter)`.

---

# Step 1: Create Result Array

There are:

```text
rows * cols
```

total cells in the matrix.

Therefore, we create:

```java
int[][] result = new int[rows * cols][2];
```

Each element contains:

```text
[row, column]
```

For example:

```text
result[0] = [0, 0]
result[1] = [0, 1]
result[2] = [0, 2]
...
```

---

# Step 2: Traverse the Matrix

We use two nested loops:

```java
for (int r = 0; r < rows; r++) {
    for (int c = 0; c < cols; c++) {
```

The outer loop traverses rows.

The inner loop traverses columns.

For example, for:

```text
rows = 2
cols = 3
```

Traversal will be:

```text
(0,0)
(0,1)
(0,2)

(1,0)
(1,1)
(1,2)
```

We store every coordinate in the `result` array.

---

# Step 3: Calculate Manhattan Distance

For every cell we calculate:

```text
distance = |row - rCenter| + |column - cCenter|
```

In Java:

```java
int distance =
    Math.abs(row - rCenter)
    + Math.abs(column - cCenter);
```

### Example

Suppose:

```text
rCenter = 1
cCenter = 2
```

and current cell is:

```text
(0,1)
```

Distance:

```text
|0 - 1| + |1 - 2|
= 1 + 1
= 2
```

So:

```text
(0,1) → distance 2
```

---

# Step 4: Sort According to Distance

Java's `Arrays.sort()` can sort a 2D array using a custom comparator.

```java
Arrays.sort(result, (a, b) -> {
```

Here:

```text
a = first cell
b = second cell
```

We calculate the distance of both cells.

```java
int distA = Math.abs(a[0] - rCenter)
          + Math.abs(a[1] - cCenter);

int distB = Math.abs(b[0] - rCenter)
          + Math.abs(b[1] - cCenter);
```

Then:

```java
return distA - distB;
```

This means:

```text
distA < distB → a comes before b
distA > distB → b comes before a
distA = distB → either order is acceptable
```

---

# Complete Algorithm

```text
1. Create an array of size rows * cols.
2. Traverse every row from 0 to rows - 1.
3. For every row, traverse every column from 0 to cols - 1.
4. Store each coordinate [row, column] in the result array.
5. Sort the result array using a custom comparator.
6. For every two cells:
      Calculate Manhattan distance from (rCenter, cCenter).
7. Put the cell with smaller distance first.
8. Return the sorted result.
```

---

# Step-by-Step Traversal

Consider:

```text
rows = 2
cols = 3
rCenter = 1
cCenter = 2
```

Matrix:

```text
        c0      c1      c2
      -----------------------
r0    | (0,0) | (0,1) | (0,2) |
      -----------------------
r1    | (1,0) | (1,1) | (1,2) |
      -----------------------
```

Center:

```text
(1,2)
```

Now calculate the distance of every cell.

### Cell (0,0)

```text
|0 - 1| + |0 - 2|
= 1 + 2
= 3
```

```text
(0,0) → 3
```

### Cell (0,1)

```text
|0 - 1| + |1 - 2|
= 1 + 1
= 2
```

```text
(0,1) → 2
```

### Cell (0,2)

```text
|0 - 1| + |2 - 2|
= 1 + 0
= 1
```

```text
(0,2) → 1
```

### Cell (1,0)

```text
|1 - 1| + |0 - 2|
= 0 + 2
= 2
```

```text
(1,0) → 2
```

### Cell (1,1)

```text
|1 - 1| + |1 - 2|
= 0 + 1
= 1
```

```text
(1,1) → 1
```

### Cell (1,2)

```text
|1 - 1| + |2 - 2|
= 0 + 0
= 0
```

```text
(1,2) → 0
```

So the cells with distances are:

```text
(1,2) → 0
(0,2) → 1
(1,1) → 1
(0,1) → 2
(1,0) → 2
(0,0) → 3
```

Therefore a valid answer is:

```text
[[1,2],
 [0,2],
 [1,1],
 [0,1],
 [1,0],
 [0,0]]
```

---

# Java Code

```java
import java.util.Arrays;

class Solution {

    public int[][] allCellsDistOrder(
            int rows,
            int cols,
            int rCenter,
            int cCenter) {

        // Total number of cells
        int[][] result = new int[rows * cols][2];

        int index = 0;

        // Traverse every cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                result[index][0] = r;
                result[index][1] = c;

                index++;
            }
        }

        // Sort cells according to Manhattan distance
        Arrays.sort(result, (a, b) -> {

            int distA = Math.abs(a[0] - rCenter)
                      + Math.abs(a[1] - cCenter);

            int distB = Math.abs(b[0] - rCenter)
                      + Math.abs(b[1] - cCenter);

            return distA - distB;
        });

        return result;
    }
}
```

---

# Pattern Used

## Pattern: Sorting + Custom Comparator

The main pattern used in this solution is:

```text
Generate all elements
        ↓
Calculate a value for each element
        ↓
Sort using that value
```

Here:

```text
Element = [row, column]

Value = Manhattan Distance
       = |row - rCenter| + |column - cCenter|
```

So conceptually:

```text
Cell
 ↓
Calculate Distance
 ↓
Compare Distance
 ↓
Sort
```

---

# Why This Pattern?

The problem specifically asks:

> Return all cells sorted by their distance from a given cell.

We can directly generate all cells and use Java's built-in sorting functionality.

Instead of manually finding the nearest cell repeatedly, we:

```text
1. Generate all cells
2. Calculate distance when comparing
3. Sort them
```

This makes the implementation simple and easy to understand.

---

# Why Not Use BFS?

Another possible approach is **BFS (Breadth-First Search)**.

BFS can work because cells at distance:

```text
0
1
2
3
...
```

can be visited level by level.

However, for this problem, BFS is not necessary.

The matrix has at most:

```text
100 × 100 = 10,000 cells
```

So generating all cells and sorting them is efficient enough.

The sorting solution is also much simpler to implement.

---

# Complexity Analysis

Let:

```text
N = rows * cols
```

be the total number of cells.

## Time Complexity

### 1. Traversing the matrix

We visit every cell once:

```text
O(rows * cols)
```

or:

```text
O(N)
```

### 2. Sorting

There are `N` cells.

Sorting takes:

```text
O(N log N)
```

### Total

Therefore:

```text
O(N) + O(N log N)
```

The dominant term is:

```text
O(N log N)
```

So:

```text
Time Complexity = O(rows * cols * log(rows * cols))
```

---

# Space Complexity

The `result` array stores every cell.

There are:

```text
rows * cols
```

cells.

Each cell contains two integers:

```text
[row, column]
```

Therefore:

```text
Space Complexity = O(rows * cols)
```

The sorting algorithm may also use additional internal stack/temp memory, but the main auxiliary storage required by our solution is the result array itself.

---

# Complexity Summary

| Operation          | Complexity            |
| ------------------ | --------------------- |
| Generate all cells | `O(rows * cols)`      |
| Calculate distance | `O(1)` per comparison |
| Sorting            | `O(N log N)`          |
| **Total Time**     | **O(N log N)**        |
| **Space**          | **O(N)**              |

Where:

```text
N = rows * cols
```

---

# Important Concept

The most important thing to understand is the **Manhattan Distance**.

For:

```text
(r1, c1)
```

and:

```text
(r2, c2)
```

distance is:

```text
|r1 - r2| + |c1 - c2|
```

It is **not**:

```text
sqrt((r1-r2)^2 + (c1-c2)^2)
```

That would be Euclidean distance.

Here we only move:

```text
Up
Down
Left
Right
```

Therefore Manhattan distance is used.

---

# Short Interview Explanation

> I first generate all coordinates of the matrix using nested loops. Then I sort these coordinates using a custom comparator. For every comparison, I calculate the Manhattan distance of both cells from `(rCenter, cCenter)` using `|r-rCenter| + |c-cCenter|`. The cell having the smaller distance comes first. Since there are `N = rows * cols` cells, generating them takes `O(N)` and sorting takes `O(N log N)`, giving an overall time complexity of `O(N log N)` and space complexity of `O(N)`.

---

# Key Takeaways

* Use **nested loops** to generate every matrix cell.
* Use **Manhattan Distance** to measure distance.
* Use **custom comparator** with `Arrays.sort()`.
* Equal-distance cells can appear in any order.
* Pattern: **Generate → Calculate → Sort**
* Time: **O(N log N)**
* Space: **O(N)**
* `N = rows * cols`
