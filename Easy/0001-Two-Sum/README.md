# Two Sum

## Problem Statement

Given an array of integers `nums` and an integer `target`, return the indices of the two numbers such that they add up to the target.

You may assume that:

- Each input has exactly one solution.
- You cannot use the same element twice.
- The answer can be returned in any order.

---

## Examples

### Example 1

```text
Input: nums = [2,7,11,15], target = 9

Output: [0,1]

Explanation:

nums[0] + nums[1] = 2 + 7 = 9
```

### Example 2

```text
Input: nums = [3,2,4], target = 6

Output: [1,2]

Explanation:

nums[1] + nums[2] = 2 + 4 = 6
```

### Example 3

```text
Input: nums = [3,3], target = 6

Output: [0,1]
```

---

# Java Solution

```java
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i);
        }

        return new int[] {};
    }
}
```

---

# Approach

We use the **HashMap** data structure to solve this problem efficiently.

For every element:

1. Calculate the required number:

   ```text
   complement = target - current element
   ```

2. Check whether the complement already exists in the HashMap.

3. If it exists, return both indices.

4. Otherwise, store the current element and its index in the HashMap.

This allows us to find the answer in a single traversal.

---

# Step-by-Step Traversal

## Input

```text
nums = [2, 7, 11, 15]

target = 9
```

---

### Iteration 1

```text
i = 0

nums[i] = 2

complement = 9 - 2 = 7
```

HashMap:

```text
{}
```

7 is not present.

Insert:

```text
{2 : 0}
```

---

### Iteration 2

```text
i = 1

nums[i] = 7

complement = 9 - 7 = 2
```

HashMap:

```text
{2 : 0}
```

2 is present.

Return:

```text
[0, 1]
```

---

# Algorithm

1. Create an empty HashMap.

2. Traverse the array from left to right.

3. Calculate:

   ```text
   complement = target - nums[i]
   ```

4. Check whether the complement exists in the HashMap.

5. If found:

   - Return the stored index and the current index.

6. Otherwise:

   - Store the current element and its index.

7. Continue until the answer is found.

---

# Dry Run

```text
nums = [3, 2, 4]

target = 6
```

| Index | Value | Complement | HashMap  | Action         |
| ----- | ----- | ---------- | -------- | -------------- |
| 0     | 3     | 3          | {}       | Store (3,0)    |
| 1     | 2     | 4          | {3=0}    | Store (2,1)    |
| 2     | 4     | 2          | {3=0,2=1}| Return [1,2]   |

---

# Pattern Used

## Pattern: Hashing / Lookup Pattern

We use the **Hashing Pattern** because:

- HashMap provides **O(1)** average lookup time.
- Searching in an array takes **O(n)** time.
- HashMap reduces the overall complexity.

---

# Why HashMap?

Without HashMap:

```text
For each element,
check every other element.
```

Time Complexity:

```text
O(n²)
```

With HashMap:

```text
Store visited numbers and search instantly.
```

Time Complexity:

```text
O(n)
```

Therefore, HashMap is the optimal choice.

---

# Complexity Analysis

## Time Complexity

The array is traversed only once.

```text
O(n)
```

where `n` is the size of the array.

---

## Space Complexity

In the worst case, HashMap stores all elements.

```text
O(n)
```

---

# Comparison

| Approach         | Time Complexity | Space Complexity |
| ---------------- | ---------------- | ---------------- |
| Brute Force      | O(n²)            | O(1)             |
| HashMap Approach | O(n)             | O(n)             |

---

# Key Points

✅ Single traversal

✅ Efficient lookup using HashMap

✅ Optimal solution

✅ Handles negative numbers

✅ Avoids nested loops

---

# Follow-Up

Can we solve it in less than O(n²)?

**Yes.**

Using a HashMap, we can solve it in:

```text
Time Complexity: O(n)

Space Complexity: O(n)
```

which is much better than the brute-force solution.
