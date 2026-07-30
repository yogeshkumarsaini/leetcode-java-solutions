# 367. Valid Perfect Square

## Problem Statement

Given a positive integer `num`, return `true` if `num` is a **perfect square**, otherwise return `false`.

A **perfect square** is an integer that can be written as:

> x × x = num

You **must not** use any built-in square root function such as `sqrt()`.

---

## Example

### Example 1

```text
Input: num = 16
Output: true

Explanation:
4 × 4 = 16
```

### Example 2

```text
Input: num = 14
Output: false

Explanation:
No integer exists whose square is 14.
```

---

## Constraints

```text
1 <= num <= 2^31 - 1
```

---

# Approach

Since the numbers from `1` to `num` are sorted, we can use **Binary Search** to efficiently find whether there exists an integer whose square equals `num`.

Instead of checking every number one by one, Binary Search repeatedly cuts the search space into half.

At every iteration:

- Find the middle number.
- Compute `mid × mid`.
- If it equals `num`, we've found a perfect square.
- If the square is smaller than `num`, search the right half.
- Otherwise, search the left half.

To avoid integer overflow while calculating `mid * mid`, we use the `long` data type.

---

# Algorithm

1. Initialize

   - `left = 1`
   - `right = num`

2. While `left <= right`

   - Find middle element.
   - Calculate `square = mid × mid`.

3. Compare `square` with `num`

   - If equal → return `true`
   - If smaller → search right half
   - If larger → search left half

4. If loop finishes, return `false`.

---

# Step-by-Step Traversal

## Example

```text
num = 16
```

### Initial State

| Left | Right |
|------|-------|
| 1 | 16 |

---

### Iteration 1

```text
mid = 8
square = 64
```

Since

```text
64 > 16
```

Search Left Half

```text
right = 7
```

---

### Iteration 2

```text
left = 1
right = 7

mid = 4
square = 16
```

Since

```text
16 == 16
```

Return

```text
true
```

---

## Example 2

```text
num = 14
```

### Iteration 1

```text
left = 1
right = 14

mid = 7
square = 49
```

49 > 14

Move Left

---

### Iteration 2

```text
left = 1
right = 6

mid = 3
square = 9
```

9 < 14

Move Right

---

### Iteration 3

```text
left = 4
right = 6

mid = 5
square = 25
```

25 > 14

Move Left

---

### Iteration 4

```text
left = 4
right = 4

mid = 4
square = 16
```

16 > 14

Move Left

Now

```text
left = 4
right = 3
```

Loop ends.

Return

```text
false
```

---

# Dry Run

## Input

```text
num = 16
```

| Left | Right | Mid | Mid² | Decision |
|------|-------|-----|------|----------|
|1|16|8|64|Search Left|
|1|7|4|16|Found|

Answer:

```text
true
```

---

## Java Solution

```java
class Solution {
    public boolean isPerfectSquare(int num) {

        int l = 1;
        int r = num;

        while (l <= r) {

            long mid = l + (r - l) / 2;
            long square = mid * mid;

            if (square == num)
                return true;
            else if (square < num)
                l = (int) mid + 1;
            else
                r = (int) mid - 1;
        }

        return false;
    }
}
```

---

# Complexity Analysis

### Time Complexity

Binary Search halves the search space in every iteration.

```
Time Complexity = O(log n)
```

---

### Space Complexity

Only a few variables are used.

```
Space Complexity = O(1)
```

---

# Pattern Used

## Binary Search on Answer

---

### Why Binary Search?

The search space

```text
1 ... num
```

is sorted.

The value of

```text
x²
```

increases monotonically as `x` increases.

That means:

- if `mid²` is too small, every number before `mid` is also too small.
- if `mid²` is too large, every number after `mid` is also too large.

This monotonic property makes Binary Search the ideal solution.

---

# Why Use `long`?

The constraint is

```text
num <= 2^31 - 1
```

Suppose

```text
mid = 50000
```

Then

```text
50000 × 50000 = 2500000000
```

which is larger than the maximum value of an `int`.

Using

```java
long square = mid * mid;
```

prevents integer overflow.

---

# Key Takeaways

- No use of `sqrt()`.
- Efficient Binary Search solution.
- Uses monotonic property of square values.
- Prevents overflow using `long`.
- Constant extra space.
- Optimal solution for large inputs.
|