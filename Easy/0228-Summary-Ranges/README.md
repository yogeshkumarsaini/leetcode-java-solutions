# 228. Summary Ranges

## Problem Statement

You are given a sorted and unique integer array `nums`.

Your task is to find the smallest sorted list of ranges that covers all numbers in the array exactly.

Each range should be represented as:

- `"a->b"` if the starting and ending numbers are different.
- `"a"` if the starting and ending numbers are the same.

### Example 1

**Input:**
```java
nums = [0, 1, 2, 4, 5, 7]
```

**Output:**
```java
["0->2", "4->5", "7"]
```

**Explanation:**

- `[0, 1, 2]` → `"0->2"`
- `[4, 5]` → `"4->5"`
- `[7]` → `"7"`

### Example 2

**Input:**
```java
nums = [0, 2, 3, 4, 6, 8, 9]
```

**Output:**
```java
["0", "2->4", "6", "8->9"]
```

---

## Java Solution

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> summaryRanges(int[] nums) {

        List<String> result = new ArrayList<>();

        int i = 0;

        while (i < nums.length) {

            int start = nums[i];

            // Find the end of the consecutive range
            while (i + 1 < nums.length &&
                   nums[i + 1] == nums[i] + 1) {

                i++;
            }

            int end = nums[i];

            // Convert the range into a string
            if (start == end) {
                result.add(String.valueOf(start));
            } else {
                result.add(start + "->" + end);
            }

            i++;
        }

        return result;
    }
}
```

---

## 1. Approach: Linear Traversal

We use a single traversal of the sorted array to identify consecutive numbers and group them into ranges.

### How does it work?

1. Initialize an empty `result` list to store the ranges.
2. Start traversing the array using index `i`.
3. Store the current element in `start`.
4. Check whether the next element is consecutive to the current element.
5. If the next element is consecutive, move `i` forward.
6. Continue until a consecutive sequence ends.
7. Store the last element of the sequence in `end`.
8. If `start == end`, add only the number to the result.
9. Otherwise, add the range in `"start->end"` format.
10. Move to the next unprocessed element and repeat.

### Why this approach?

- The array is already sorted.
- We don't need an additional sorting operation.
- Every element is visited only once.
- We can identify consecutive sequences directly.
- It avoids unnecessary nested traversal.

---

## 2. Algorithm

**Step 1:** Create an empty ArrayList named `result`.

**Step 2:** Initialize `i = 0`.

**Step 3:** Run a while loop while `i < nums.length`.

**Step 4:** Store `nums[i]` in `start`.

**Step 5:** Check the consecutive condition:

```java
i + 1 < nums.length &&
nums[i + 1] == nums[i] + 1
```

**Step 6:** If the condition is true, increment `i` and continue checking.

**Step 7:** Store the last consecutive element in `end`.

**Step 8:** Check:

- If `start == end`, add `start`.
- Otherwise, add `start + "->" + end`.

**Step 9:** Increment `i` to process the next range.

**Step 10:** Return the result list.

---

## 3. Step-by-Step Traversal

Let's understand the code using Example 1.

**Input:**
```java
nums = [0, 1, 2, 4, 5, 7]
```

### Iteration 1

- `i = 0`
- `start = 0`
- Check `nums[1] == nums[0] + 1`
- `1 == 0 + 1` → True
- Move `i = 1`
- Check `nums[2] == nums[1] + 1`
- `2 == 1 + 1` → True
- Move `i = 2`
- Check `nums[3] == nums[2] + 1`
- `4 == 2 + 1` → False

Now:
- `start = 0`
- `end = 2`

Add `"0->2"`.

### Iteration 2

- `i = 3`
- `start = 4`
- Check `nums[4] == nums[3] + 1`
- `5 == 4 + 1` → True
- Move `i = 4`
- Check `nums[5] == nums[4] + 1`
- `7 == 5 + 1` → False

Now:
- `start = 4`
- `end = 5`

Add `"4->5"`.

### Iteration 3

- `i = 5`
- `start = 7`
- No next element exists.
- `start == end` → True

Add `"7"`.

### Final Output

```java
["0->2", "4->5", "7"]
```

---

## 4. Traversal Summary Table

| Iteration | Start | End | Range Added |
|---|---:|---:|---|
| 1 | 0 | 2 | `"0->2"` |
| 2 | 4 | 5 | `"4->5"` |
| 3 | 7 | 7 | `"7"` |

---

## 5. Pattern Used

### Linear Traversal + Consecutive Sequence Grouping

This problem uses a **Linear Traversal pattern**, where we process elements sequentially and group consecutive values into ranges.

**Why this pattern?**

- The input array is sorted.
- Consecutive elements can be identified by checking whether the next element equals the current element plus one.
- We maintain a starting point and extend the current range until the sequence breaks.
- Once the sequence ends, we record the range and start a new one.

### Important Concepts

1. Array Traversal
2. Consecutive Element Checking
3. Two Index Variables (`i` and `start` value)
4. Greedy Grouping
5. String Manipulation

**Note:** This is primarily a single-pointer linear traversal solution, not the classic two-pointer technique with two independently moving pointers.

---

## 6. Complexity Analysis

Let `n` be the length of the input array.

### Time Complexity: O(n)

- The outer `while` loop processes each range.
- The inner `while` loop checks consecutive elements.
- The index `i` only moves forward.
- Every array element is visited at most once by the traversal.

Therefore:

**Time Complexity = O(n)**

### Space Complexity: O(1) Auxiliary Space

- We use only a few variables: `i`, `start`, and `end`.
- No additional array or data structure is created for processing.

Therefore:

**Auxiliary Space = O(1)**

**Output Space = O(n)**

The result list stores the generated ranges. In the worst case, every number forms an individual range, resulting in `n` strings.

---

## 7. Edge Cases

| Input | Output |
|---|---|
| `[]` | `[]` |
| `[5]` | `["5"]` |
| `[1, 2, 3]` | `["1->3"]` |
| `[1, 3, 5]` | `["1", "3", "5"]` |
| `[-3, -2, -1, 1]` | `["-3->-1", "1"]` |
| `[0, 1, 2, 4]` | `["0->2", "4"]` |

---

## 8. Important Interview Points

**Q1. Why don't we sort the array?**

Because the problem guarantees that the array is already sorted in ascending order.

**Q2. Why do we use `i + 1 < nums.length`?**

To ensure the next element exists before accessing `nums[i + 1]`, avoiding an ArrayIndexOutOfBoundsException.

**Q3. Why do we use `nums[i + 1] == nums[i] + 1`?**

To check whether two adjacent numbers are consecutive.

**Q4. Why do we increment `i` after adding a range?**

To move to the next unprocessed element and avoid processing the same range again.

**Q5. Can this solution handle negative numbers?**

Yes. Integer addition and comparison work with negative numbers as well.

**Q6. Can integer overflow occur?**

Yes, `nums[i] + 1` can overflow when `nums[i] == Integer.MAX_VALUE`. However, the given sorted unique array cannot have a following integer after `Integer.MAX_VALUE`, so the condition remains safe in this problem.

---

## 9. Final Complexity

| Complexity | Result |
|---|---|
| Time Complexity | O(n) |
| Auxiliary Space | O(1) |
| Output Space | O(n) |
| Pattern | Linear Traversal |
| Approach | Consecutive Sequence Grouping |

**Conclusion:**

We solve Summary Ranges using a single linear traversal. We identify consecutive numbers, group them into ranges, and convert those ranges into strings.

The solution takes **O(n) time** and **O(1) auxiliary space**, excluding the output list.
