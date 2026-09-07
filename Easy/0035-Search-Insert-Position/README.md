# Search Insert Position

## Problem

Given a sorted array of distinct integers and a target value, return the index if the target is found.

If the target is not found, return the index where it would be inserted in order.

The algorithm must have **O(log n)** runtime complexity.

---

## Examples

### Example 1

```text
Input: nums = [1,3,5,6], target = 5
Output: 2
```

Explanation:

`5` is already present at index `2`.

---

### Example 2

```text
Input: nums = [1,3,5,6], target = 2
Output: 1
```

Explanation:

`2` is not present.

It should be inserted between `1` and `3`, so the correct index is `1`.

---

### Example 3

```text
Input: nums = [1,3,5,6], target = 7
Output: 4
```

Explanation:

`7` is greater than all elements, so it should be inserted at the end.

Therefore, the answer is `4`.

---

# Approach

We use **Binary Search**.

The array is already sorted in ascending order, so instead of checking every element one by one, we repeatedly divide the search range into two halves.

At every step:

* Calculate the middle index.
* If `nums[mid] == target`, return `mid`.
* If `target < nums[mid]`, search in the left half.
* If `target > nums[mid]`, search in the right half.
* If the target is not found, `left` will finally point to the correct insertion position.

---

# Why Binary Search?

The array is sorted.

For a sorted array, Binary Search is more efficient than Linear Search because it eliminates half of the remaining elements after every comparison.

For example:

```text
[1, 3, 5, 6, 8, 10, 12, 15]

             ↑
            mid
```

If the target is smaller than `nums[mid]`, we don't need to check the right half.

```text
[1, 3, 5, 6] | [8, 10, 12, 15]
     search ↑
```

Similarly, if the target is greater than `nums[mid]`, we only search the right half.

This reduces the search space approximately like:

```text
n
n / 2
n / 4
n / 8
...
```

Therefore, the time complexity is:

```text
O(log n)
```

---

# Algorithm

1. Initialize `left = 0`.
2. Initialize `right = nums.length - 1`.
3. Run Binary Search while `left <= right`.
4. Calculate the middle index:

   ```java
   int mid = left + (right - left) / 2;
   ```
5. If `nums[mid] == target`, return `mid`.
6. If `target < nums[mid]`, move `right` to `mid - 1`.
7. Otherwise, move `left` to `mid + 1`.
8. If the loop finishes, return `left`.
9. `left` represents the correct insertion position.

---

# Step-by-Step Traversal

Consider:

```text
nums = [1, 3, 5, 6]
target = 2
```

### Step 1

```text
left = 0
right = 3

mid = 0 + (3 - 0) / 2
mid = 1
```

So:

```text
nums[mid] = nums[1] = 3
```

Compare:

```text
target = 2
nums[mid] = 3
```

Since:

```text
2 < 3
```

Search the left half:

```text
right = mid - 1
right = 0
```

---

### Step 2

Now:

```text
left = 0
right = 0

mid = 0 + (0 - 0) / 2
mid = 0
```

So:

```text
nums[mid] = nums[0] = 1
```

Compare:

```text
2 > 1
```

Therefore, search the right side:

```text
left = mid + 1
left = 1
```

---

### Step 3

Now:

```text
left = 1
right = 0
```

The condition:

```java
left <= right
```

is false.

The loop ends.

Finally:

```java
return left;
```

Therefore:

```text
Output = 1
```

So `2` should be inserted at index `1`.

---

# Important Observation

The most important part of this solution is:

```java
return left;
```

Why?

When Binary Search finishes without finding the target:

```text
right < left
```

At this point, `left` is exactly the position where the target can be inserted without breaking the sorted order.

For example:

```text
nums = [1, 3, 5, 6]
target = 2
```

After searching:

```text
left = 1
right = 0
```

Therefore:

```text
return left;
       ↓
       1
```

---

# Java Solution

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
```

---

# Dry Run

### Case 1

```text
nums = [1,3,5,6]
target = 5
```

| left | right | mid | nums[mid] | Action       |
| ---- | ----- | --- | --------- | ------------ |
| 0    | 3     | 1   | 3         | Search right |
| 2    | 3     | 2   | 5         | Found        |

```text
Output = 2
```

---

### Case 2

```text
nums = [1,3,5,6]
target = 2
```

| left | right | mid | nums[mid] | Action       |
| ---- | ----- | --- | --------- | ------------ |
| 0    | 3     | 1   | 3         | Search left  |
| 0    | 0     | 0   | 1         | Search right |
| 1    | 0     | -   | -         | Stop         |

```text
Output = 1
```

---

### Case 3

```text
nums = [1,3,5,6]
target = 7
```

| left | right | mid | nums[mid] | Action       |
| ---- | ----- | --- | --------- | ------------ |
| 0    | 3     | 1   | 3         | Search right |
| 2    | 3     | 2   | 5         | Search right |
| 3    | 3     | 3   | 6         | Search right |
| 4    | 3     | -   | -         | Stop         |

```text
Output = 4
```

---

# Pattern Used

## Binary Search Pattern

**Pattern:** `Binary Search`

### Why?

Binary Search is suitable because:

1. The array is sorted.
2. Elements are distinct.
3. We need to find a target or its insertion position.
4. The problem explicitly requires `O(log n)` time.
5. Binary Search removes half of the search space after every iteration.

---

# Complexity Analysis

## Time Complexity

```text
O(log n)
```

In every iteration, the search space is divided approximately by 2.

For example:

```text
n → n/2 → n/4 → n/8 → ...
```

Therefore:

```text
Time = O(log n)
```

---

## Space Complexity

```text
O(1)
```

Only a few variables are used:

```java
left
right
mid
```

No additional array, list, recursion, or data structure is created.

Therefore:

```text
Space = O(1)
```

---

# Complexity Summary

| Complexity | Result       |
| ---------- | ------------ |
| Time       | **O(log n)** |
| Space      | **O(1)**     |

---

# Key Takeaway

The key idea is:

> **After Binary Search finishes, `left` is the correct insertion position.**

So even when the target doesn't exist, we can simply return:

```java
return left;
```

This gives us an efficient:

```text
Time  → O(log n)
Space → O(1)
```

solution.

