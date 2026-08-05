# Construct Rectangle

## Problem Statement

A web developer needs to design a rectangular web page with a given area.

Given an integer `area`, find the length `L` and width `W` such that:

1. `L * W = area`
2. `L >= W`
3. `L - W` is as small as possible

Return the dimensions as an array:

```text
[L, W]
```

---

## Examples

### Example 1

```text
Input: area = 4

Output: [2,2]
```

Explanation:

Possible rectangles:

```text
1 × 4
2 × 2
4 × 1
```

Since `L >= W` and the difference should be minimum, the answer is:

```text
[2,2]
```

---

### Example 2

```text
Input: area = 37

Output: [37,1]
```

---

### Example 3

```text
Input: area = 122122

Output: [427,286]
```

---

# Java Solution

```java
class Solution {

    public int[] constructRectangle(int area) {

        int w = (int) Math.sqrt(area);

        while (w > 0) {

            if (area % w == 0) {

                int l = area / w;

                return new int[]{l, w};
            }

            w--;
        }

        return new int[]{0, 0};
    }
}
```

---

# Approach

The goal is to find two numbers `L` and `W` such that:

```text
L × W = area
```

and

```text
L >= W
```

while keeping:

```text
L - W
```

as small as possible.

To minimize the difference, we start searching from the square root of the area.

Why?

Because the factors closest to each other are always near the square root.

For example:

```text
area = 36

√36 = 6

Factors:

1 × 36   Difference = 35

2 × 18   Difference = 16

3 × 12   Difference = 9

4 × 9    Difference = 5

6 × 6    Difference = 0
```

The pair closest to each other is nearest to `√area`.

---

# Algorithm

1. Compute the square root of `area`.

2. Store it in `w`.

3. Start traversing downward from `w`.

4. For each value of `w`, check:

   ```text
   area % w == 0
   ```

5. If divisible:

   - Width:

     ```text
     W = w
     ```

   - Length:

     ```text
     L = area / w
     ```

6. Return:

   ```text
   [L, W]
   ```

---

# Step-by-Step Traversal

## Example

```text
area = 122122
```

### Step 1

Find square root:

```text
√122122 ≈ 349
```

Start:

```text
w = 349
```

---

### Step 2

Check:

```text
122122 % 349 ≠ 0
```

Move:

```text
w = 348
```

---

### Step 3

Check:

```text
122122 % 348 ≠ 0
```

Continue decreasing.

---

### Step 4

Eventually:

```text
w = 286
```

Check:

```text
122122 % 286 = 0
```

Compute length:

```text
L = 122122 / 286

L = 427
```

Return:

```text
[427,286]
```

---

# Dry Run

```text
area = 37

√37 ≈ 6
```

Check:

```text
37 % 6 ≠ 0
37 % 5 ≠ 0
37 % 4 ≠ 0
37 % 3 ≠ 0
37 % 2 ≠ 0
37 % 1 = 0
```

Therefore:

```text
L = 37 / 1 = 37

W = 1
```

Answer:

```text
[37,1]
```

---

# Pattern Used

## Mathematical Pattern (Factorization)

This problem uses:

- Square Root Optimization
- Factorization
- Greedy Observation

### Why this pattern?

The factors having the minimum difference always lie near the square root.

Instead of checking all values from:

```text
1 → area
```

we start from:

```text
√area → 1
```

which reduces the number of iterations significantly.

---

# Complexity Analysis

## Time Complexity

Finding the square root:

```text
O(1)
```

Loop:

```text
O(√n)
```

Total:

```text
O(√n)
```

---

## Space Complexity

We only use:

- `w`
- `l`

No extra data structure is used.

```text
O(1)
```

---

# Key Observation

The rectangle with the minimum difference between length and width is always formed by the factor pair closest to:

```text
√area
```

Therefore, starting from the square root guarantees the optimal answer.

---

# Summary

✅ Factorization approach

✅ Square-root optimization

✅ Minimum difference guaranteed

✅ Time Complexity: `O(√n)`

✅ Space Complexity: `O(1)`