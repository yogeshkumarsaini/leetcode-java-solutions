# Remove Element

## Problem Statement

Given an integer array `nums` and an integer `val`, remove all occurrences of `val` from `nums` **in-place**.

The order of the remaining elements may be changed.

Return `k`, where `k` is the number of elements in `nums` that are not equal to `val`.

After the operation:

- The first `k` elements of `nums` must contain all elements that are not equal to `val`.
- Elements after index `k - 1` are not important.
- The array itself must be modified **in-place**.
- Extra array space should not be used.

---

## Examples

### Example 1

```text
Input:
nums = [3,2,2,3]
val = 3

Output:
k = 2

Modified nums:
[2,2,_,_]
```

The value `3` occurs twice, so the remaining elements are `2, 2`.

---

### Example 2

```text
Input:
nums = [0,1,2,2,3,0,4,2]
val = 2

Output:
k = 5

Modified nums:
[0,1,3,0,4,_,_,_]
```

The first `5` elements contain all values that are not equal to `2`.

The order does not matter.

---

# Java Solution

```java
class Solution {

    public int removeElement(int[] nums, int val) {

        int k = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != val) {

                nums[k] = nums[i];
                k++;
            }
        }

        return k;
    }
}
```

---

# Approach

We use the **Two Pointer / Write Pointer** approach.

There are two indexes:

- `i` → used to **traverse/read** every element of the array.
- `k` → used as a **write pointer** to place valid elements (elements not equal to `val`) at the beginning of the array.

### Main Idea

Whenever `nums[i] != val`:

1. Copy `nums[i]` to `nums[k]`.
2. Increment `k`.

If `nums[i] == val`, we simply skip it.

At the end, `k` represents the number of elements that are not equal to `val`.

---

# Why Two Pointers?

The problem requires the array to be modified **in-place**.

Creating another array would require `O(n)` extra space.

Instead, we use:

```text
i = read pointer
k = write pointer
```

This allows us to overwrite unwanted values directly inside the original array.

Therefore, we achieve:

```text
Time Complexity  = O(n)
Space Complexity = O(1)
```

---

# Algorithm

1. Initialize `k = 0`.
2. Traverse the array using `i` from `0` to `nums.length - 1`.
3. Check whether `nums[i]` is different from `val`.
4. If `nums[i] != val`:
   - Store `nums[i]` at `nums[k]`.
   - Increment `k`.
5. If `nums[i] == val`, do nothing and continue.
6. After traversal, return `k`.

---

# Step-by-Step Traversal

Consider:

```text
nums = [0,1,2,2,3,0,4,2]
val = 2
```

Initial:

```text
k = 0
```

### Step 1

```text
i = 0
nums[i] = 0
```

`0 != 2`, so:

```text
nums[k] = nums[i]
nums[0] = 0

k = 1
```

Array:

```text
[0,1,2,2,3,0,4,2]
 ^
 k
```

---

### Step 2

```text
i = 1
nums[i] = 1
```

`1 != 2`

```text
nums[1] = 1
k = 2
```

Array:

```text
[0,1,2,2,3,0,4,2]
   ^
   k
```

---

### Step 3

```text
i = 2
nums[i] = 2
```

`2 == val`

So we **skip** it.

```text
k = 2
```

---

### Step 4

```text
i = 3
nums[i] = 2
```

Again:

```text
2 == val
```

Skip it.

```text
k = 2
```

---

### Step 5

```text
i = 4
nums[i] = 3
```

`3 != 2`

Write it at index `k`:

```text
nums[2] = 3
k = 3
```

Array becomes:

```text
[0,1,3,2,3,0,4,2]
```

---

### Step 6

```text
i = 5
nums[i] = 0
```

`0 != 2`

```text
nums[3] = 0
k = 4
```

Array:

```text
[0,1,3,0,3,0,4,2]
```

---

### Step 7

```text
i = 6
nums[i] = 4
```

`4 != 2`

```text
nums[4] = 4
k = 5
```

Array:

```text
[0,1,3,0,4,0,4,2]
```

Only the first `5` elements matter:

```text
[0,1,3,0,4]
```

---

### Step 8

```text
i = 7
nums[i] = 2
```

`2 == val`

Skip it.

Final:

```text
k = 5
```

So the answer is:

```text
Output: 5
```

The first five elements are:

```text
[0,1,3,0,4]
```

Their order is allowed to be different.

---

# Pointer Visualization

For:

```text
nums = [0,1,2,2,3,0,4,2]
        ↑
        i
        ↑
        k
```

`i` continuously moves through the complete array.

`k` moves only when a valid element is found.

```text
i →  Reads every element

k →  Writes only elements != val
```

Therefore:

```text
i moves: 0 → 1 → 2 → 3 → 4 → 5 → 6 → 7

k moves:
0 → 1 → 2 → 2 → 3 → 4 → 5 → 5
```

---

# Dry Run Table

| `i` | `nums[i]` | Condition | Action | `k` |
|---:|---:|---|---|---:|
| 0 | 0 | `0 != 2` | `nums[0] = 0` | 1 |
| 1 | 1 | `1 != 2` | `nums[1] = 1` | 2 |
| 2 | 2 | `2 == 2` | Skip | 2 |
| 3 | 2 | `2 == 2` | Skip | 2 |
| 4 | 3 | `3 != 2` | `nums[2] = 3` | 3 |
| 5 | 0 | `0 != 2` | `nums[3] = 0` | 4 |
| 6 | 4 | `4 != 2` | `nums[4] = 4` | 5 |
| 7 | 2 | `2 == 2` | Skip | 5 |

Final:

```text
k = 5
```

---

# Pattern Used

## Two Pointer Pattern — Read & Write Pointer

This solution uses the **Two Pointer** pattern, specifically a **Read Pointer + Write Pointer** technique.

### Read Pointer

```java
int i
```

`i` scans every element in the array.

### Write Pointer

```java
int k = 0
```

`k` keeps track of where the next valid element should be placed.

---

# Why This Pattern Is Used?

The main requirements are:

1. Remove elements equal to `val`.
2. Modify the original array.
3. Do it in-place.
4. Return the number of remaining elements.
5. Avoid using another array.

The Read/Write Pointer technique is ideal because:

```text
Read → Check → Write valid element
```

We do not need to physically delete an element from the array.

Instead, we overwrite the unwanted positions with valid values.

For example:

```text
Before:

[3, 2, 2, 3]
 ↑
 val = 3
```

After processing:

```text
[2, 2, _, _]
```

Only the first `k = 2` positions are important.

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Where `n = nums.length`.

The array is traversed exactly once.

```java
for (int i = 0; i < nums.length; i++)
```

Therefore:

```text
Time = O(n)
```

---

## Space Complexity

```text
O(1)
```

We only use a few variables:

```java
int k;
int i;
```

No additional array, list, or other data structure is created.

Therefore:

```text
Space = O(1)
```

This is also called **constant extra space**.

---

# Final Complexity

| Complexity | Result |
|---|---|
| Time | `O(n)` |
| Extra Space | `O(1)` |
| Pattern | Two Pointer — Read/Write Pointer |
| In-place | Yes |

---

# Important Observation

We don't actually need to delete elements from the array.

Java arrays have a fixed size, so we simply make sure that:

```text
nums[0 ... k-1]
```

contains all elements that are not equal to `val`.

Anything after index `k - 1` is irrelevant according to the problem.

This is why the solution is both simple and efficient.

---

# Key Takeaway

The important pattern to remember is:

```text
Read Pointer → scans the array
Write Pointer → stores valid elements
```

Whenever a problem asks you to:

- remove/filter elements **in-place**
- keep only elements satisfying a condition
- use `O(1)` extra space

think about the **Read/Write Two Pointer** pattern.

---

## Related Pattern

This same technique is useful for problems such as:

- Remove Duplicates from Sorted Array
- Move Zeroes
- Remove Element
- Filtering elements in-place
- Compacting valid elements in an array


