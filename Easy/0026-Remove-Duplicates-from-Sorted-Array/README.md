# 26. Remove Duplicates from Sorted Array

## 🔗 Problem
Given a sorted integer array `nums`, remove the duplicates **in-place** so that each unique element appears only once.

Return the number of unique elements `k`.

The first `k` elements of the array should contain the unique elements in sorted order.

---

## 📝 Example

### Example 1

**Input**
```text
nums = [1,1,2]
```

**Output**
```text
k = 2
nums = [1,2,_]
```

---

### Example 2

**Input**
```text
nums = [0,0,1,1,1,2,2,3,3,4]
```

**Output**
```text
k = 5
nums = [0,1,2,3,4,_,_,_,_,_]
```

---

## 💡 Approach (Two Pointer Technique)

Since the array is already **sorted**, all duplicate values appear next to each other.

We maintain two pointers:

- **i (Slow Pointer)** → Points to the last unique element.
- **j (Fast Pointer)** → Traverses the array searching for the next unique element.

Whenever a new unique element is found:

1. Move the slow pointer one step forward.
2. Copy the unique value to the slow pointer's position.

Finally, the first `(i + 1)` positions contain all unique elements.

---

## 🧠 Intuition

Because the array is sorted:

```text
1 1 2 2 2 3 3 4
```

Duplicate values are always adjacent.

Instead of creating another array, we overwrite duplicate positions with the next unique value.

This satisfies the problem requirement of **in-place modification**.

---

# Algorithm

1. If the array is empty, return `0`.
2. Initialize a slow pointer `i = 0`.
3. Traverse the array using a fast pointer `j` from index `1`.
4. Compare `nums[i]` with `nums[j]`.
5. If both are different:
   - Increment `i`.
   - Copy `nums[j]` into `nums[i]`.
6. Continue until the traversal ends.
7. Return `i + 1` because array indexing starts from `0`.

---

# Step-by-Step Traversal

### Input

```text
nums = [0,0,1,1,1,2,2,3,3,4]
```

Initially

```text
i = 0
j = 1
```

| Step | j | nums[j] | nums[i] | Action | Array |
|------|---|----------|----------|--------|-------|
|Start|-|-|-|Initial|[0,0,1,1,1,2,2,3,3,4]|
|1|1|0|0|Duplicate → Ignore|[0,0,1,1,1,2,2,3,3,4]|
|2|2|1|0|Unique → i++, copy|[0,1,1,1,1,2,2,3,3,4]|
|3|3|1|1|Duplicate|No Change|
|4|4|1|1|Duplicate|No Change|
|5|5|2|1|Unique → i++, copy|[0,1,2,1,1,2,2,3,3,4]|
|6|6|2|2|Duplicate|No Change|
|7|7|3|2|Unique → i++, copy|[0,1,2,3,1,2,2,3,3,4]|
|8|8|3|3|Duplicate|No Change|
|9|9|4|3|Unique → i++, copy|[0,1,2,3,4,2,2,3,3,4]|

Final

```text
k = 5

nums = [0,1,2,3,4,_,_,_,_,_]
```

---

# Dry Run

```text
nums = [1,1,2]

i = 0

j = 1
nums[0] == nums[1]
Duplicate
Do nothing

j = 2
nums[0] != nums[2]

i++
i = 1

nums[1] = nums[2]

Array

[1,2,2]

Return i + 1

2
```

---

# Java Solution

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        if (nums.length == 0)
            return 0;

        int i = 0;

        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }
}
```

---

# Complexity Analysis

### Time Complexity

- The fast pointer visits every element exactly once.
- Each comparison and assignment takes constant time.

**Time Complexity:**

```text
O(n)
```

where `n` is the length of the array.

---

### Space Complexity

No extra array or data structure is used.

Only two integer variables (`i` and `j`) are maintained.

**Space Complexity:**

```text
O(1)
```

---

# Pattern Used

## Two Pointer Technique (Slow & Fast Pointer)

### Why this pattern?

The array is already sorted.

All duplicate elements appear consecutively.

The Two Pointer pattern efficiently:

- Traverses the array only once.
- Removes duplicates in-place.
- Avoids using extra memory.
- Preserves the relative order of unique elements.

This is one of the most common interview patterns for sorted arrays.

---

# Key Observations

- The array is **sorted**, so duplicates are adjacent.
- We only copy when a new unique value is found.
- The first `k` positions contain all unique elements.
- Elements after index `k - 1` are irrelevant.
- No additional space is required.

---

# Interview Takeaways

- ✔ In-place Array Modification
- ✔ Two Pointer Technique
- ✔ Slow & Fast Pointer Pattern
- ✔ Single Pass Traversal
- ✔ Constant Space Solution
- ✔ Efficient for Sorted Arrays

---

## Final Complexity

| Complexity | Value |
|------------|--------|
| Time | **O(n)** |
| Space | **O(1)** |
