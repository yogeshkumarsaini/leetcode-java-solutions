# Boomerang

## Problem

Given an array `points` containing exactly three points on an X-Y plane, return `true` if these three points form a **boomerang**.

A boomerang means:

1. All three points are **distinct**.
2. The three points are **not in a straight line**.

### Example 1

```text
Input:
points = [[1,1],[2,3],[3,2]]

Output:
true
```

### Example 2

```text
Input:
points = [[1,1],[2,2],[3,3]]

Output:
false
```

---

## Approach

We need to check whether the three points are on the **same straight line** or not.

Normally, we can calculate the slope between points:

```text
slope = (y2 - y1) / (x2 - x1)
```

But using division can create problems when:

* `x2 - x1 = 0`
* Floating-point precision is involved.

So instead of calculating the slope, we use the **cross multiplication** formula.

For three points:

```text
P1 = (x1, y1)
P2 = (x2, y2)
P3 = (x3, y3)
```

The points are collinear when:

```text
(x2 - x1) * (y3 - y1)
==
(y2 - y1) * (x3 - x1)
```

Therefore, they form a boomerang when:

```text
(x2 - x1) * (y3 - y1)
!=
(y2 - y1) * (x3 - x1)
```

---

## Why This Formula Works

The slope between `P1` and `P2` is:

```text
(y2 - y1) / (x2 - x1)
```

The slope between `P1` and `P3` is:

```text
(y3 - y1) / (x3 - x1)
```

For points to be on the same straight line, both slopes must be equal:

```text
(y2 - y1) / (x2 - x1)
=
(y3 - y1) / (x3 - x1)
```

Cross multiply:

```text
(y2 - y1) * (x3 - x1)
=
(y3 - y1) * (x2 - x1)
```

So if both sides are **not equal**, the points are not collinear and therefore form a boomerang.

---

# Algorithm

1. Get the coordinates of the first point:

   ```text
   x1, y1
   ```

2. Get the coordinates of the second point:

   ```text
   x2, y2
   ```

3. Get the coordinates of the third point:

   ```text
   x3, y3
   ```

4. Calculate:

   ```text
   left  = (x2 - x1) * (y3 - y1)
   right = (y2 - y1) * (x3 - x1)
   ```

5. Compare `left` and `right`.

6. If:

   ```text
   left != right
   ```

   return `true`.

7. Otherwise, return `false`.

---

# Step-by-Step Traversal

Consider:

```text
points = [[1,1],[2,3],[3,2]]
```

So:

```text
P1 = (1,1)
P2 = (2,3)
P3 = (3,2)
```

### Step 1: Calculate X differences

```text
x2 - x1
= 2 - 1
= 1
```

```text
x3 - x1
= 3 - 1
= 2
```

### Step 2: Calculate Y differences

```text
y2 - y1
= 3 - 1
= 2
```

```text
y3 - y1
= 2 - 1
= 1
```

### Step 3: Calculate both sides

```text
left = (x2 - x1) * (y3 - y1)
     = 1 * 1
     = 1
```

```text
right = (y2 - y1) * (x3 - x1)
      = 2 * 2
      = 4
```

### Step 4: Compare

```text
1 != 4
```

Therefore:

```text
return true
```

So these three points form a **boomerang**.

---

# Example 2 Traversal

```text
points = [[1,1],[2,2],[3,3]]
```

Coordinates:

```text
P1 = (1,1)
P2 = (2,2)
P3 = (3,3)
```

Calculate:

```text
(x2 - x1) * (y3 - y1)
= (2 - 1) * (3 - 1)
= 1 * 2
= 2
```

Other side:

```text
(y2 - y1) * (x3 - x1)
= (2 - 1) * (3 - 1)
= 1 * 2
= 2
```

Both are equal:

```text
2 == 2
```

Therefore, all three points are on the same straight line.

```text
return false
```

---

# Java Solution

```java
class Solution {
    public boolean isBoomerang(int[][] points) {
        int x1 = points[0][0];
        int y1 = points[0][1];

        int x2 = points[1][0];
        int y2 = points[1][1];

        int x3 = points[2][0];
        int y3 = points[2][1];

        return (x2 - x1) * (y3 - y1)
                != (y2 - y1) * (x3 - x1);
    }
}
```

---

# Pattern Used

## Geometry Pattern — Collinearity Check

This problem uses a **Geometry / Coordinate Geometry** pattern.

More specifically, we use the **Cross Product / Collinearity Check**.

The important idea is:

```text
Three points are collinear
        ↓
Their slopes are equal
        ↓
Avoid division
        ↓
Use cross multiplication
        ↓
Compare both products
```

### Formula

```text
(x2 - x1) * (y3 - y1)
==
(y2 - y1) * (x3 - x1)
```

If equal:

```text
Collinear → Straight line → false
```

If not equal:

```text
Not collinear → Boomerang → true
```

---

# Why This Pattern Is Used

We could use slope:

```text
(y2 - y1) / (x2 - x1)
```

But this is not the best approach because division creates two problems.

### Problem 1: Vertical Lines

If:

```text
x2 - x1 = 0
```

then we would divide by zero.

### Problem 2: Floating Point

Using `double` for slopes can introduce precision issues.

For example, comparing:

```text
0.333333333...
```

with another calculated floating-point value is less reliable.

Cross multiplication avoids both problems:

```text
(a / b) == (c / d)
```

becomes:

```text
a * d == c * b
```

So we only use integer arithmetic.

---

# Complexity

There are exactly **3 points**, and we only perform a fixed number of calculations.

### Time Complexity

```text
O(1)
```

Why?

We access only three points and perform a constant number of arithmetic operations.

### Space Complexity

```text
O(1)
```

Why?

We only store six integer variables:

```text
x1, y1
x2, y2
x3, y3
```

No extra array, loop, or data structure is required.

---

# Complexity Summary

| Complexity | Value  |
| ---------- | ------ |
| Time       | `O(1)` |
| Space      | `O(1)` |

---

# Key Takeaway

The most important thing to remember from this problem is:

> **To check whether three points are in a straight line, don't calculate slopes using division. Use cross multiplication.**

```text
(x2 - x1) * (y3 - y1)
!=
(y2 - y1) * (x3 - x1)
```

If the values are different, the three points are **not collinear**, so they form a **boomerang**.
