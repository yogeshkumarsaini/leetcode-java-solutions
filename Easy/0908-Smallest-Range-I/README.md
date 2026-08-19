# Smallest Range I

## Problem

You are given an integer array `nums` and an integer `k`.

In one operation, you can choose an index `i` and change:

```text
nums[i] = nums[i] + x
```

where:

```text
-k <= x <= k
```

Each index can be modified **at most once**.

The **score** of the array is:

```text
maximum element - minimum element
```

The goal is to return the **minimum possible score** after applying the operations.

---

## Example

### Example 1

```text
Input:
nums = [1]
k = 0

Output:
0
```

There is only one element, so:

```text
max - min = 1 - 1 = 0
```

---

### Example 2

```text
Input:
nums = [0, 10]
k = 2

Output:
6
```

We can change the values to:

```text
[2, 8]
```

Therefore:

```text
max - min
= 8 - 2
= 6
```

---

### Example 3

```text
Input:
nums = [1, 3, 6]
k = 3

Output:
0
```

We can change the values to:

```text
[4, 4, 4]
```

Therefore:

```text
max - min = 4 - 4 = 0
```

---

# Approach

The important observation is that we only need to know the **minimum** and **maximum** values in the array.

Suppose:

```text
min = smallest value
max = largest value
```

The original score is:

```text
max - min
```

We can increase the minimum value by at most `k`:

```text
min -> min + k
```

And decrease the maximum value by at most `k`:

```text
max -> max - k
```

Therefore, the range can be reduced by at most:

```text
k + k = 2 * k
```

So the minimum possible score is:

```text
(max - min) - 2 * k
```

However, the score can never be negative.

Therefore:

```text
answer = max(0, max - min - 2 * k)
```

---

# Why `2 * k`?

Consider:

```text
nums = [0, 10]
k = 2
```

Initially:

```text
min = 0
max = 10
```

Initial range:

```text
10 - 0 = 10
```

We can increase the minimum:

```text
0 + 2 = 2
```

We can decrease the maximum:

```text
10 - 2 = 8
```

New range:

```text
8 - 2 = 6
```

So the range decreases by:

```text
10 - 6 = 4
```

And:

```text
4 = 2 * k
```

---

# Algorithm

1. Initialize `min` and `max` using the first element.
2. Traverse the complete array.
3. Find the smallest element.
4. Find the largest element.
5. Calculate the original range:

```text
max - min
```

6. Reduce the range by `2 * k`.
7. Since the score cannot be negative, return:

```text
max(0, max - min - 2 * k)
```

---

# Step-by-Step Traversal

Consider:

```text
nums = [1, 3, 6]
k = 3
```

### Step 1: Initialize

```java
int min = nums[0];
int max = nums[0];
```

So:

```text
min = 1
max = 1
```

---

### Step 2: Traverse `nums`

#### First element

```text
num = 1
```

```text
min = min(1, 1) = 1
max = max(1, 1) = 1
```

Current:

```text
min = 1
max = 1
```

---

#### Second element

```text
num = 3
```

```text
min = min(1, 3) = 1
max = max(1, 3) = 3
```

Current:

```text
min = 1
max = 3
```

---

#### Third element

```text
num = 6
```

```text
min = min(1, 6) = 1
max = max(3, 6) = 6
```

Final:

```text
min = 1
max = 6
```

---

### Step 3: Calculate Answer

```text
max - min - 2 * k
```

Substitute the values:

```text
6 - 1 - 2 * 3
```

```text
5 - 6
```

```text
-1
```

The score cannot be negative, so:

```text
max(0, -1) = 0
```

Final answer:

```text
0
```

---

# Java Solution

```java
class Solution {
    public int smallestRangeI(int[] nums, int k) {

        int min = nums[0];
        int max = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return Math.max(0, max - min - 2 * k);
    }
}
```

---

# Pattern Used

## Greedy / Range Reduction

The main pattern used here is:

```text
Greedy + Min/Max Tracking
```

### Why Greedy?

For minimizing the range:

```text
max - min
```

we should make:

* the minimum as large as possible
* the maximum as small as possible

Therefore:

```text
minimum -> minimum + k
maximum -> maximum - k
```

This gives the maximum possible reduction in the range:

```text
2 * k
```

There is no need to modify every element individually or simulate operations.

---

# Why We Don't Need Sorting

A sorting-based solution could find the minimum and maximum:

```java
Arrays.sort(nums);
```

Then:

```text
min = nums[0]
max = nums[n - 1]
```

But sorting is unnecessary.

We only need the minimum and maximum values, so a single traversal is enough:

```java
for (int num : nums) {
    min = Math.min(min, num);
    max = Math.max(max, num);
}
```

This makes the solution faster.

---

# Complexity Analysis

Let:

```text
n = nums.length
```

### Time Complexity

We traverse the array exactly once:

```text
O(n)
```

There is no sorting and no nested loop.

### Space Complexity

We only use two variables:

```java
int min;
int max;
```

Therefore:

```text
O(1)
```

---

# Complexity Summary

| Complexity | Value  |
| ---------- | ------ |
| Time       | `O(n)` |
| Space      | `O(1)` |

---

# Key Insight

The most important formula is:

```text
Minimum Score = max(0, max - min - 2 * k)
```

Because:

```text
minimum can increase by k
maximum can decrease by k
```

Therefore:

```text
Range reduction = k + k = 2k
```

If:

```text
max - min <= 2 * k
```

then all values can effectively be brought into the same range, so the answer becomes:

```text
0
```

---

# Final Takeaway

This problem is a good example of **Greedy Thinking**.

Instead of changing every element, focus only on the two elements that determine the score:

```text
minimum element
maximum element
```

Move them toward each other as much as allowed:

```text
min + k
max - k
```

Then calculate:

```text
max(0, (max - k) - (min + k))
```

which simplifies to:

```text
max(0, max - min - 2 * k)
```

This gives an optimal:

```text
Time:  O(n)
Space: O(1)
```

solution.
