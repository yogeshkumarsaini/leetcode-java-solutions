# Largest Triangle Area

## Problem Statement

Given an array of points on the **X-Y plane**, where:

```text
points[i] = [xi, yi]
```

we need to return the **maximum possible area of a triangle** that can be formed using any three different points.

The answer is accepted if it is within `10^-5` of the actual answer.

### Example 1

```text
Input:
points = [[0,0],[0,1],[1,0],[0,2],[2,0]]

Output:
2.00000
```

### Example 2

```text
Input:
points = [[1,0],[0,0],[0,1]]

Output:
0.50000
```

---

## Approach

We need to select **any three different points** and calculate the area of the triangle formed by them.

Since the maximum number of points is only:

```text
n <= 50
```

we can safely check **every possible combination of three points**.

For every combination `(i, j, k)`:

1. Select point `i`.
2. Select point `j`.
3. Select point `k`.
4. Calculate the triangle area.
5. Compare it with the maximum area found so far.
6. Return the maximum area.

---

## Triangle Area Formula

For three points:

```text
(x1, y1)
(x2, y2)
(x3, y3)
```

the area of the triangle is:

```text
Area =
| x1(y2 - y3)
+ x2(y3 - y1)
+ x3(y1 - y2) |
/ 2
```

This is also known as the **Shoelace Formula** / determinant-based area formula.

### Why use `Math.abs()`?

The expression can be positive or negative depending on the order of the points.

Since area cannot be negative, we use:

```java
Math.abs(...)
```

---

## Algorithm

```text
1. Initialize maxArea = 0.
2. Let n = points.length.
3. Use three nested loops.
4. First loop chooses point i.
5. Second loop chooses point j after i.
6. Third loop chooses point k after j.
7. Calculate the area of points i, j and k.
8. Update maxArea.
9. Return maxArea.
```

---

## Step-by-Step Traversal

Suppose:

```text
points = [[0,0], [0,1], [1,0], [0,2], [2,0]]
```

There are `5` points.

The loops generate combinations like:

```text
(0, 1, 2)
(0, 1, 3)
(0, 1, 4)
(0, 2, 3)
(0, 2, 4)
(0, 3, 4)
(1, 2, 3)
(1, 2, 4)
(1, 3, 4)
(2, 3, 4)
```

For example, for:

```text
(0, 3, 4)
```

the points are:

```text
(0,0)
(0,2)
(2,0)
```

Area:

```text
|0(2-0) + 0(0-0) + 2(0-2)| / 2

= |-4| / 2

= 2
```

So:

```text
maxArea = 2
```

After checking every possible combination, we return:

```text
2.0
```

---

## Java Solution

```java
class Solution {

    public double largestTriangleArea(int[][] points) {

        double maxArea = 0.0;
        int n = points.length;

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                for (int k = j + 1; k < n; k++) {

                    double area = Math.abs(
                            points[i][0] * (points[j][1] - points[k][1]) +
                            points[j][0] * (points[k][1] - points[i][1]) +
                            points[k][0] * (points[i][1] - points[j][1])
                    ) / 2.0;

                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }
}
```

---

## Pattern Used

### Pattern: Brute Force + Combinations

This solution uses the:

**Brute Force / Exhaustive Search pattern**

with a combination of:

**Three Nested Loops**

We need to select exactly **3 different points** from the array.

Therefore, we generate every possible combination:

```text
i < j < k
```

This guarantees that:

* Every group of 3 points is checked.
* No point is selected twice.
* Duplicate combinations are avoided.

---

## Why Brute Force?

The constraint is:

```text
3 <= points.length <= 50
```

The number of combinations is:

```text
C(n, 3)
= n(n-1)(n-2) / 6
```

For the maximum value:

```text
C(50, 3) = 19,600
```

Only **19,600 triangles** need to be checked.

This is very small, so brute force is completely acceptable.

There is no need for a complicated optimization.

---

## Why `i < j < k`?

Instead of checking:

```text
i = 0 to n
j = 0 to n
k = 0 to n
```

we use:

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        for (int k = j + 1; k < n; k++) {
```

This ensures every combination is checked only once.

For example:

```text
(0,1,2)
```

does not need to be checked again as:

```text
(1,0,2)
(2,1,0)
(2,0,1)
```

because they represent the same three points and therefore the same triangle.

---

## Complexity Analysis

### Time Complexity

We use three nested loops:

```text
O(n³)
```

More precisely, the number of triangles is:

```text
C(n,3) = n(n-1)(n-2) / 6
```

Therefore:

```text
Time Complexity: O(n³)
```

With `n <= 50`, this is easily fast enough.

### Space Complexity

We only use a few variables:

```text
maxArea
n
i
j
k
area
```

No additional data structure is required.

Therefore:

```text
Space Complexity: O(1)
```

---

## Complexity Summary

| Complexity | Value   |
| ---------- | ------- |
| Time       | `O(n³)` |
| Space      | `O(1)`  |

---

## Key Points

* Select every combination of 3 points.
* Use the determinant/shoelace formula to calculate triangle area.
* Use `Math.abs()` because area must be positive.
* Maintain the maximum area using `Math.max()`.
* `i < j < k` prevents duplicate combinations.
* Brute force is suitable because `n <= 50`.
* No extra data structure is required.

---

## Formula Used in Code

```java
double area = Math.abs(
        x1 * (y2 - y3) +
        x2 * (y3 - y1) +
        x3 * (y1 - y2)
) / 2.0;
```

---

## Final Takeaway

This problem is a straightforward example of using **Brute Force with Combinations**.

Because the input size is small, checking all possible groups of three points is efficient enough.

The important idea is:

```text
Choose 3 points
        ↓
Calculate their area
        ↓
Compare with maximum
        ↓
Repeat for all combinations
        ↓
Return maximum area
```
