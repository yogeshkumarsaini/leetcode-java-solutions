# 70. Climbing Stairs

## Problem Statement

You are climbing a staircase. It takes **n** steps to reach the top.

Each time you can climb either **1 step** or **2 steps**.

Return the number of distinct ways to reach the top.

### Example 1

Input:
```
n = 2
```

Output:
```
2
```

Explanation:
```
1. 1 + 1
2. 2
```

### Example 2

Input:
```
n = 3
```

Output:
```
3
```

Explanation:
```
1. 1 + 1 + 1
2. 1 + 2
3. 2 + 1
```

---

# Approach

This problem follows the **Dynamic Programming (Fibonacci Pattern)**.

To reach the current stair, there are only two possibilities:

- Come from the previous stair (n-1)
- Come from two stairs before (n-2)

Therefore,

```
ways(n) = ways(n-1) + ways(n-2)
```

Instead of storing all previous values in an array, we only keep the last two values because the current answer depends only on them.

This reduces the space complexity from **O(n)** to **O(1)**.

---

# Dry Run

### Example: n = 5

Initially

```
first = 1
second = 2
```

| Iteration | current = first + second | first | second |
|-----------|-------------------------|-------|--------|
| i = 3 | 3 | 2 | 3 |
| i = 4 | 5 | 3 | 5 |
| i = 5 | 8 | 5 | 8 |

Return

```
8
```

There are **8 different ways** to climb 5 stairs.

---

# Algorithm

1. If `n <= 2`, return `n`.
2. Initialize:
   - `first = 1`
   - `second = 2`
3. Traverse from stair **3** to **n**.
4. Calculate

   ```
   current = first + second
   ```

5. Move both variables forward:

   ```
   first = second
   second = current
   ```

6. After the loop finishes, return `second`.

---

# Step-by-Step Traversal

Suppose

```
n = 6
```

### Initial State

```
first = 1
second = 2
```

### i = 3

```
current = 1 + 2 = 3

first = 2
second = 3
```

### i = 4

```
current = 2 + 3 = 5

first = 3
second = 5
```

### i = 5

```
current = 3 + 5 = 8

first = 5
second = 8
```

### i = 6

```
current = 5 + 8 = 13

first = 8
second = 13
```

Answer:

```
13
```

---

# Why Fibonacci?

Let's observe:

| Stair | Ways |
|--------|------|
| 1 | 1 |
| 2 | 2 |
| 3 | 3 |
| 4 | 5 |
| 5 | 8 |
| 6 | 13 |

Notice that

```
3 = 2 + 1
5 = 3 + 2
8 = 5 + 3
13 = 8 + 5
```

This is exactly the **Fibonacci Sequence**, with different starting values.

---

# Pattern Used

## Dynamic Programming (DP)

Reason:

The answer for stair **n** depends on answers of

- n-1
- n-2

This is the hallmark of Dynamic Programming.

---

## Fibonacci Pattern

Since

```
ways(n) = ways(n-1) + ways(n-2)
```

the recurrence is identical to the Fibonacci sequence.

Instead of storing every DP value, we optimize the solution by storing only the previous two results.

This optimization is called the **Space Optimized Dynamic Programming (Fibonacci DP)** approach.

---

# Complexity Analysis

### Time Complexity

```
O(n)
```

Reason:

- We traverse from 3 to n exactly once.

---

### Space Complexity

```
O(1)
```

Reason:

Only three integer variables are used:

- first
- second
- current

No extra array or recursion stack is required.

---

# Java Solution

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2)
            return n;

        int first = 1;
        int second = 2;

        for (int i = 3; i <= n; i++) {
            int current = first + second;
            first = second;
            second = current;
        }

        return second;
    }
}
```

---

# Key Takeaways

- Dynamic Programming problem.
- Follows the Fibonacci recurrence.
- Uses Space Optimization.
- No recursion.
- No extra array.
- Efficient for all constraints (`1 <= n <= 45`).
