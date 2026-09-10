# Merge Sorted Array

## Problem

You are given two integer arrays `nums1` and `nums2`, both sorted in non-decreasing order.

- `nums1` has a length of `m + n`.
- The first `m` elements of `nums1` are valid elements.
- The last `n` positions contain `0` placeholders and should be ignored.
- `nums2` contains `n` valid elements.

The goal is to merge both arrays into `nums1` in sorted order.

### Example

```text
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6], n = 3

Output:
[1,2,2,3,5,6]
```

---

## Solution

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {

            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }

            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
```

---

# Approach

We use the **Two Pointers** pattern and merge the arrays from **right to left**.

Normally, if we start from the beginning, inserting an element into `nums1` could overwrite elements that we still need.

But `nums1` already has `n` empty spaces at the end.

So, we fill those empty spaces from the **back**.

We use three pointers:

- `i` → points to the last valid element of `nums1`
- `j` → points to the last element of `nums2`
- `k` → points to the last position of the final merged array

Then:

1. Compare `nums1[i]` and `nums2[j]`.
2. Put the larger value at `nums1[k]`.
3. Move the corresponding pointer backward.
4. Move `k` backward.
5. Continue until one array is completely processed.
6. If elements of `nums2` are still remaining, copy them into `nums1`.

---

# Why Start From the Right?

Consider:

```text
nums1 = [1,2,3,0,0,0]
             ↑
             i

nums2 = [2,5,6]
           ↑
           j

Final position:
nums1 = [1,2,3,0,0,0]
                   ↑
                   k
```

The largest element among the remaining elements should go at the last position.

Compare:

```text
nums1[i] = 3
nums2[j] = 6
```

`6` is larger, so:

```text
nums1[k] = 6
```

Now:

```text
nums1 = [1,2,3,0,0,6]
                   ↑
                   k
```

Move `j` and `k` backward.

Next compare:

```text
3 and 5
```

Put `5` at the current `k`.

This continues until the complete array is merged.

---

# Algorithm

### Step 1: Initialize pointers

```java
int i = m - 1;
int j = n - 1;
int k = m + n - 1;
```

For example:

```text
nums1 = [1,2,3,0,0,0]
nums2 = [2,5,6]

i = 2
j = 2
k = 5
```

---

### Step 2: Compare from the end

```java
while (i >= 0 && j >= 0)
```

Compare:

```java
nums1[i]
nums2[j]
```

The larger value is placed at:

```java
nums1[k]
```

---

### Step 3: Move pointers

If `nums1[i]` is larger:

```java
nums1[k] = nums1[i];
i--;
```

Otherwise:

```java
nums1[k] = nums2[j];
j--;
```

After either case:

```java
k--;
```

---

### Step 4: Copy remaining `nums2` elements

After the main loop, `nums2` may still contain elements.

So:

```java
while (j >= 0) {
    nums1[k] = nums2[j];
    j--;
    k--;
}
```

We only need this loop for `nums2`.

### Why don't we copy remaining `nums1` elements?

Because they are already in their correct positions.

For example:

```text
nums1 = [1,2,3]
nums2 = []
```

There is nothing to move. The elements of `nums1` are already sorted and correctly positioned.

---

# Step-by-Step Traversal

For:

```text
nums1 = [1,2,3,0,0,0]
nums2 = [2,5,6]

i = 2, j = 2, k = 5
```

### Iteration 1

```text
nums1[i] = 3
nums2[j] = 6
```

`6` is larger:

```text
nums1[5] = 6
```

Pointers:

```text
i = 2
j = 1
k = 4
```

Array:

```text
[1,2,3,0,0,6]
```

### Iteration 2

```text
3 vs 5
```

Put `5`:

```text
[1,2,3,0,5,6]
```

Pointers:

```text
i = 2
j = 0
k = 3
```

### Iteration 3

```text
3 vs 2
```

Put `3`:

```text
[1,2,3,3,5,6]
```

Pointers:

```text
i = 1
j = 0
k = 2
```

### Iteration 4

```text
2 vs 2
```

The `else` condition runs because the code uses `>`.

Put `2` from `nums2`:

```text
[1,2,2,3,5,6]
```

Pointers:

```text
i = 1
j = -1
k = 1
```

`nums2` is finished, so the main loop stops.

The remaining `nums1` elements are already in the correct place.

### Final Output

```text
[1,2,2,3,5,6]
```

---

# Pattern Used: Two Pointers

## Pattern

**Two Pointers**

We use:

```text
i → nums1
j → nums2
```

And an additional write pointer:

```text
k → final position in nums1
```

So technically we are using **two comparison pointers + one write pointer**.

---

## Why Two Pointers?

Both input arrays are already sorted.

Therefore, we don't need to search repeatedly.

At every step, we only need to compare the largest remaining elements:

```text
nums1[i] vs nums2[j]
```

The larger one must go at the current last position.

This makes the solution efficient.

---

# Why Reverse Traversal?

Reverse traversal is important because the result must be stored **inside `nums1`**.

If we started from the beginning:

```text
[1,2,3,0,0,0]
 ↑
```

Writing new values could overwrite `2` or `3` before we process them.

By starting from the end:

```text
[1,2,3,0,0,0]
             ↑
```

we use the empty positions first, so no valid element is overwritten.

---

# Complexity

## Time Complexity

```text
O(m + n)
```

Each element is processed at most once.

- `nums1` has `m` valid elements.
- `nums2` has `n` elements.

Therefore:

```text
O(m + n)
```

This satisfies the follow-up requirement.

---

## Space Complexity

```text
O(1)
```

We do not create another array.

Only three integer variables are used:

```java
i
j
k
```

Therefore, the extra space is constant.

---

# Final Complexity

| Complexity | Result |
|---|---|
| Time | `O(m + n)` |
| Extra Space | `O(1)` |

---

# Key Takeaways

- Use **Two Pointers** because both arrays are sorted.
- Start from the **right side** to avoid overwriting elements in `nums1`.
- Use `k` to place the largest remaining element.
- Only remaining elements of `nums2` need to be copied.
- The solution works **in-place**.
- Time complexity is **O(m + n)**.
- Extra space complexity is **O(1)**.

---

# Pattern Recognition

When you see:

```text
Two sorted arrays
+
Merge into one array
+
Destination array has extra space
```

Think:

> **Two Pointers + Reverse Traversal + In-place Merge**

This is a very common array pattern and is also useful for problems involving merging sorted data efficiently.
