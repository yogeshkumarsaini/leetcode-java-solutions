# Fibonacci Number

## Problem

The Fibonacci numbers form a sequence where each number is the sum of the two preceding numbers, starting from `0` and `1`.

```text
F(0) = 0
F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1
```

Given an integer `n`, return the `n`th Fibonacci number.

### Examples

```text
Input: n = 2
Output: 1

Input: n = 3
Output: 2

Input: n = 4
Output: 3
```

### Constraints

```text
0 <= n <= 30
```

---

## Solution

```java
class Solution {
    public int fib(int n) {
        if (n <= 1)
            return n;

        int a = 0;
        int b = 1;

        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

        return b;
    }
}
```

---

## Approach

We use an **iterative Dynamic Programming / Space Optimization** approach.

The Fibonacci formula needs only the previous two values:

```text
F(n) = F(n - 1) + F(n - 2)
```

There is no need to store the complete Fibonacci sequence in an array.

We maintain only two variables:

- `a` → represents `F(i - 2)`
- `b` → represents `F(i - 1)`
- `c` → calculates the current Fibonacci number `F(i)`

After calculating `c`, we shift the values:

```text
a = b
b = c
```

This reduces the extra space from `O(n)` to `O(1)`.

---

## Why This Pattern?

### Pattern: Iterative Dynamic Programming + State Compression

The Fibonacci problem has overlapping subproblems because the same Fibonacci values are required repeatedly.

A normal recursive solution would look like:

```text
fib(n)
 ├── fib(n - 1)
 │    ├── fib(n - 2)
 │    └── fib(n - 3)
 └── fib(n - 2)
      ├── fib(n - 3)
      └── fib(n - 4)
```

This causes the same values to be calculated multiple times.

Instead of recursion, we calculate the values from the smallest cases toward `n`:

```text
F(0) → F(1) → F(2) → F(3) → ... → F(n)
```

Because each new value depends only on the previous two values, we can keep only those two values.

### Why not use an array?

An array would store:

```text
F(0), F(1), F(2), ..., F(n)
```

This requires `O(n)` extra space.

But we only need:

```text
previous two values
```

Therefore, two variables are sufficient and give us `O(1)` auxiliary space.

---

## Algorithm

1. Check if `n <= 1`.
   - If yes, return `n`.
2. Initialize:
   - `a = 0` → `F(0)`
   - `b = 1` → `F(1)`
3. Start a loop from `i = 2` to `n`.
4. Calculate:
   ```text
   c = a + b
   ```
5. Move the previous value:
   ```text
   a = b
   ```
6. Move the current value:
   ```text
   b = c
   ```
7. After the loop, return `b`.

---

## Step-by-Step Traversal

Let's take:

```text
n = 6
```

Initial values:

```text
a = 0
b = 1
```

### Iteration 1: i = 2

```text
c = a + b
c = 0 + 1
c = 1

a = b → 1
b = c → 1
```

Current state:

```text
a = 1
b = 1
```

This represents:

```text
F(1) = 1
F(2) = 1
```

### Iteration 2: i = 3

```text
c = 1 + 1 = 2

a = 1
b = 2
```

Represents:

```text
F(2) = 1
F(3) = 2
```

### Iteration 3: i = 4

```text
c = 1 + 2 = 3

a = 2
b = 3
```

Represents:

```text
F(3) = 2
F(4) = 3
```

### Iteration 4: i = 5

```text
c = 2 + 3 = 5

a = 3
b = 5
```

Represents:

```text
F(4) = 3
F(5) = 5
```

### Iteration 5: i = 6

```text
c = 3 + 5 = 8

a = 5
b = 8
```

Represents:

```text
F(5) = 5
F(6) = 8
```

Final result:

```text
return b
```

```text
F(6) = 8
```

---

## Dry Run Table

For `n = 6`:

| Iteration | `a` before | `b` before | `c = a + b` | `a` after | `b` after |
|---:|---:|---:|---:|---:|---:|
| Initial | 0 | 1 | - | 0 | 1 |
| `i = 2` | 0 | 1 | 1 | 1 | 1 |
| `i = 3` | 1 | 1 | 2 | 1 | 2 |
| `i = 4` | 1 | 2 | 3 | 2 | 3 |
| `i = 5` | 2 | 3 | 5 | 3 | 5 |
| `i = 6` | 3 | 5 | 8 | 5 | 8 |

Answer:

```text
8
```

---

## Complexity Analysis

### Time Complexity

```text
O(n)
```

The loop runs from `2` through `n`, so approximately `n - 1` iterations are performed.

### Space Complexity

```text
O(1)
```

Only three integer variables are used:

```text
a
b
c
```

The algorithm does not use an array, recursion stack, or any data structure that grows with `n`.

---

## Comparison With Other Approaches

| Approach | Time | Space | Notes |
|---|---:|---:|---|
| Recursive | `O(2^n)` | `O(n)` | Repeats calculations |
| DP Array | `O(n)` | `O(n)` | Stores all Fibonacci values |
| Iterative + Two Variables | `O(n)` | `O(1)` | Best simple approach |
| Matrix/Fast Doubling | `O(log n)` | `O(1)` | Useful for very large `n` |

For the given constraint `0 <= n <= 30`, the iterative two-variable solution is simple, efficient, and easy to understand.

---

## Key Takeaway

The important optimization is that Fibonacci calculation does **not** require the entire sequence.

At every step, we only need:

```text
F(n - 2)
F(n - 1)
```

So we keep two variables and continuously update them.

```text
a = previous previous
b = previous
c = current
```

This gives:

```text
Time  : O(n)
Space : O(1)
Pattern: Iterative Dynamic Programming / State Compression
```
