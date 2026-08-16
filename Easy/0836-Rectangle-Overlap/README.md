# Rectangle Overlap

## Problem

An axis-aligned rectangle is represented as:

```text
[x1, y1, x2, y2]
```

where:

* `(x1, y1)` → bottom-left corner
* `(x2, y2)` → top-right corner

Given two rectangles `rec1` and `rec2`, return `true` if they overlap with **positive area**.

If the rectangles only touch at an edge or corner, they are **not considered overlapping**.

---

## Example 1

```text
Input:
rec1 = [0,0,2,2]
rec2 = [1,1,3,3]

Output:
true
```

The two rectangles overlap in the region:

```text
[1,1] → [2,2]
```

---

## Example 2

```text
Input:
rec1 = [0,0,1,1]
rec2 = [1,0,2,1]

Output:
false
```

The rectangles only touch at `x = 1`.

Since the intersection area is `0`, they do not overlap.

---

## Example 3

```text
Input:
rec1 = [0,0,1,1]
rec2 = [2,2,3,3]

Output:
false
```

The rectangles are completely separated.

---

# Approach

We can determine whether two rectangles overlap by checking their projections on the **X-axis** and **Y-axis**.

For two rectangles to have a positive-area intersection:

1. Their X-ranges must overlap.
2. Their Y-ranges must overlap.

For:

```text
rec1 = [x1, y1, x2, y2]
rec2 = [a1, b1, a2, b2]
```

The rectangles overlap horizontally when:

```text
x1 < a2 && x2 > a1
```

They overlap vertically when:

```text
y1 < b2 && y2 > b1
```

Therefore, both conditions must be true:

```text
x1 < a2 &&
y1 < b2 &&
x2 > a1 &&
y2 > b1
```

---

# Why `<` and `>` Instead of `<=` and `>=`?

This is very important.

The problem says rectangles that only touch at an edge or corner are **not overlapping**.

For example:

```text
rec1 = [0,0,1,1]
rec2 = [1,0,2,1]
```

Here:

```text
rec1[2] == rec2[0]
```

Both rectangles meet at `x = 1`, but there is no positive-width intersection.

Therefore:

```text
rec1[2] > rec2[0]
```

is false.

If we used:

```text
rec1[2] >= rec2[0]
```

we would incorrectly consider touching rectangles as overlapping.

---

# Algorithm

### Step 1

Extract the rectangle boundaries conceptually:

```text
rec1 = [x1, y1, x2, y2]
rec2 = [a1, b1, a2, b2]
```

### Step 2

Check whether there is horizontal overlap:

```text
rec1[0] < rec2[2] &&
rec1[2] > rec2[0]
```

### Step 3

Check whether there is vertical overlap:

```text
rec1[1] < rec2[3] &&
rec1[3] > rec2[1]
```

### Step 4

If both horizontal and vertical overlaps exist, the rectangles have a positive-area intersection.

```text
rec1[0] < rec2[2] &&
rec1[1] < rec2[3] &&
rec1[2] > rec2[0] &&
rec1[3] > rec2[1]
```

### Step 5

Return the result.

---

# Step-by-Step Traversal

Consider:

```text
rec1 = [0,0,2,2]
rec2 = [1,1,3,3]
```

### Check 1 — Left edge of Rectangle 1 vs Right edge of Rectangle 2

```text
rec1[0] < rec2[2]

0 < 3

true
```

### Check 2 — Bottom edge of Rectangle 1 vs Top edge of Rectangle 2

```text
rec1[1] < rec2[3]

0 < 3

true
```

### Check 3 — Right edge of Rectangle 1 vs Left edge of Rectangle 2

```text
rec1[2] > rec2[0]

2 > 1

true
```

### Check 4 — Top edge of Rectangle 1 vs Bottom edge of Rectangle 2

```text
rec1[3] > rec2[1]

2 > 1

true
```

All four conditions are `true`.

Therefore:

```text
Output = true
```

---

# Java Solution

```java
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

        return rec1[0] < rec2[2] &&
               rec1[1] < rec2[3] &&
               rec1[2] > rec2[0] &&
               rec1[3] > rec2[1];
    }
}
```

---

# Pattern Used

## Pattern: Interval Overlap / Boundary Comparison

This solution uses the **Interval Overlap Pattern**.

A rectangle can be considered as two independent intervals:

```text
X-axis → [x1, x2]
Y-axis → [y1, y2]
```

Two rectangles overlap only when their intervals overlap on **both axes**.

### X-axis

```text
[x1, x2]
[a1, a2]
```

Need:

```text
x1 < a2 && x2 > a1
```

### Y-axis

```text
[y1, y2]
[b1, b2]
```

Need:

```text
y1 < b2 && y2 > b1
```

Therefore:

```text
X overlap && Y overlap
```

---

# Why This Pattern?

We use this pattern because the rectangles are **axis-aligned**.

That means:

* Their edges are parallel to the X-axis or Y-axis.
* Their X and Y ranges are independent.
* We don't need complex geometry calculations.
* No area calculation is necessary.
* No loops are necessary.

Instead of calculating the actual intersection rectangle, we only need to determine whether a valid intersection exists.

This makes the solution simple and efficient.

---

# Alternative Way to Think About It

A rectangle does **not** overlap another rectangle if it is completely:

* Left of the other rectangle
* Right of the other rectangle
* Above the other rectangle
* Below the other rectangle

We could check those four non-overlapping cases and return their opposite.

However, directly checking the four overlap conditions is cleaner:

```text
Left1 < Right2
Right1 > Left2
Bottom1 < Top2
Top1 > Bottom2
```

---

# Complexity Analysis

## Time Complexity

```text
O(1)
```

Only four comparisons are performed.

The input size does not affect the number of operations.

---

## Space Complexity

```text
O(1)
```

No extra data structure or array is created.

Only constant extra memory is used.

---

# Complexity Summary

| Complexity | Result |
| ---------- | ------ |
| Time       | `O(1)` |
| Space      | `O(1)` |

---

# Important Edge Case

### Rectangles Touch at an Edge

```text
rec1 = [0,0,1,1]
rec2 = [1,0,2,1]
```

Here:

```text
rec1[2] = rec2[0]
```

They touch, but:

```text
intersection area = 0
```

So the answer is:

```text
false
```

This is why we use strict comparisons:

```text
<
>
```

instead of:

```text
<=
>=
```

---

# Key Takeaway

For axis-aligned rectangles:

```text
Rectangle Overlap
        ↓
X-axis intervals overlap
        &&
Y-axis intervals overlap
        ↓
Positive-area intersection
```

The core condition is:

```java
rec1[0] < rec2[2] &&
rec1[1] < rec2[3] &&
rec1[2] > rec2[0] &&
rec1[3] > rec2[1]
```

### Pattern to Remember

> **For 2D axis-aligned objects, check interval overlap independently on the X-axis and Y-axis.**

This gives an optimal:

```text
Time  → O(1)
Space → O(1)
```
