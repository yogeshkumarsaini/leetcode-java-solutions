# 441. Arranging Coins

## Problem Statement

You have `n` coins and want to build a staircase.

The staircase consists of `k` rows, where:

- The 1st row contains `1` coin.
- The 2nd row contains `2` coins.
- The 3rd row contains `3` coins.
- And so on.

The last row may be incomplete.

Return the number of complete rows that can be formed.

---

## Examples

### Example 1

```text
Input: n = 5

Output: 2
```

Explanation:

```text
Row 1 -> 1 coin
Row 2 -> 2 coins
Row 3 -> 3 coins (not enough)

Total used = 1 + 2 = 3

Complete rows = 2
```

---

### Example 2

```text
Input: n = 8

Output: 3
```

Explanation:

```text
Row 1 -> 1 coin
Row 2 -> 2 coins
Row 3 -> 3 coins
Row 4 -> 4 coins (not enough)

Total used = 1 + 2 + 3 = 6

Complete rows = 3
```

---

## Java Solution

```java
class Solution {
    public int arrangeCoins(int n) {

        long left = 1;
        long right = n;

        while (left <= right) {

            long mid = left + (right - left) / 2;

            long coins = mid * (mid + 1) / 2;

            if (coins == n) {
                return (int) mid;
            } else if (coins < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return (int) right;
    }
}
```

---

# Approach

We need to find the maximum value of `k` such that:

```text
1 + 2 + 3 + ... + k <= n
```

Using the arithmetic progression formula:

```text
k × (k + 1) / 2 <= n
```

Instead of checking every value one by one, we use **Binary Search** to find the largest valid `k`.

---

# Why Binary Search?

The number of coins required increases in sorted order:

```text
1 row  -> 1 coin
2 rows -> 3 coins
3 rows -> 6 coins
4 rows -> 10 coins
5 rows -> 15 coins
```

This sequence is monotonic (always increasing).

Whenever data is sorted or monotonic, Binary Search is an efficient choice.

---

# Algorithm

1. Initialize:

   ```text
   left = 1
   right = n
   ```

2. Find the middle value:

   ```text
   mid = left + (right - left) / 2
   ```

3. Calculate the coins needed:

   ```text
   coins = mid × (mid + 1) / 2
   ```

4. Compare:

   - If `coins == n`, return `mid`.
   - If `coins < n`, search on the right side.
   - If `coins > n`, search on the left side.

5. Continue until `left > right`.

6. Return `right`, because it stores the largest valid row count.

---

# Step-by-Step Traversal

## Input

```text
n = 8
```

### Initial State

```text
left = 1
right = 8
```

---

### Iteration 1

```text
mid = 4

coins = 4 × 5 / 2 = 10
```

```text
10 > 8
```

Move left:

```text
right = 3
```

---

### Iteration 2

```text
left = 1
right = 3

mid = 2

coins = 2 × 3 / 2 = 3
```

```text
3 < 8
```

Move right:

```text
left = 3
```

---

### Iteration 3

```text
left = 3
right = 3

mid = 3

coins = 3 × 4 / 2 = 6
```

```text
6 < 8
```

Move right:

```text
left = 4
```

---

Loop ends:

```text
left = 4
right = 3
```

Return:

```text
3
```

---

# Dry Run Table

| left | right | mid | coins | Action |
|-------|--------|-----|--------|--------|
| 1 | 8 | 4 | 10 | right = 3 |
| 1 | 3 | 2 | 3 | left = 3 |
| 3 | 3 | 3 | 6 | left = 4 |

Answer:

```text
3
```

---

# Pattern Used

## Binary Search on Answer

We are not searching for an element inside an array.

Instead, we are searching for the maximum possible answer `k`.

Condition:

```text
k × (k + 1) / 2 <= n
```

This pattern is called:

- Binary Search on Answer
- Monotonic Search Space

---

# Why This Pattern?

Because:

- The answer space is sorted (`1 ... n`).
- The condition changes only once.

```text
1 row  -> valid
2 rows -> valid
3 rows -> valid
4 rows -> invalid
5 rows -> invalid
```

We need the last valid value.

Binary Search is perfect for this type of problem.

---

# Complexity Analysis

### Time Complexity

Binary Search reduces the search space by half in every iteration.

```text
O(log n)
```

---

### Space Complexity

Only a few variables are used.

```text
O(1)
```

---

# Mathematical Formula Used

Sum of first `k` natural numbers:

```text
k × (k + 1) / 2
```

Example:

```text
1 + 2 + 3 + 4 + 5

= 5 × 6 / 2

= 15
```

---

# Key Takeaways

✅ Binary Search on Answer

✅ Monotonic Search Space

✅ Arithmetic Progression Formula

✅ Time Complexity: O(log n)

✅ Space Complexity: O(1)