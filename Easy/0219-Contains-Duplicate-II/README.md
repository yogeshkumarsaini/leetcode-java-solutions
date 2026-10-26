# Contains Duplicate II

## Problem

Given an integer array `nums` and an integer `k`, return `true` if there are two distinct indices `i` and `j` such that:

- `nums[i] == nums[j]`
- `abs(i - j) <= k`

### Example 1

```text
Input:  nums = [1,2,3,1], k = 3
Output: true
```

### Example 2

```text
Input:  nums = [1,0,1,1], k = 1
Output: true
```

### Example 3

```text
Input:  nums = [1,2,3,1,2,3], k = 2
Output: false
```

---

## Java Solution

```java
import java.util.HashMap;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(nums[i])) {
                int previousIndex = map.get(nums[i]);

                if (i - previousIndex <= k) {
                    return true;
                }
            }

            map.put(nums[i], i);
        }

        return false;
    }
}
```

---

## Approach

We use a **HashMap** to store:

```text
Number -> Most Recent Index
```

For example:

```text
1 -> 3
2 -> 1
3 -> 2
```

Whenever we see a number again, we check the distance between its current index and previous index.

```text
current index - previous index <= k
```

If this condition is true, we found a nearby duplicate and return `true`.

If the condition is false, we update the number's index with the current index because the current occurrence is closer to any future occurrence.

---

## Why Store the Most Recent Index?

Suppose:

```text
nums = [1,2,3,1]
```

When we reach the second `1`:

```text
previousIndex = 0
currentIndex = 3
```

Distance:

```text
3 - 0 = 3
```

If `k = 3`:

```text
3 <= 3
```

Therefore, the answer is:

```text
true
```

The **most recent index** is important because it gives the smallest possible distance to the current occurrence.

---

## Algorithm

1. Create a `HashMap<Integer, Integer>`.
2. Traverse the array from left to right.
3. For every `nums[i]`:
   - Check whether the number already exists in the map.
   - If it exists, get its previous index.
   - Calculate `i - previousIndex`.
   - If the distance is `<= k`, return `true`.
   - Otherwise, update the number's index to `i`.
4. If the complete array is traversed without finding a valid pair, return `false`.

---

## Step-by-Step Traversal

Consider:

```text
nums = [1,2,3,1]
k = 3
```

### Step 1

```text
i = 0
nums[i] = 1
```

`1` is not in the map.

```text
map = {1=0}
```

### Step 2

```text
i = 1
nums[i] = 2
```

`2` is not in the map.

```text
map = {1=0, 2=1}
```

### Step 3

```text
i = 2
nums[i] = 3
```

`3` is not in the map.

```text
map = {1=0, 2=1, 3=2}
```

### Step 4

```text
i = 3
nums[i] = 1
```

`1` already exists.

```text
previousIndex = 0
currentIndex = 3
```

Calculate:

```text
3 - 0 = 3
```

Since:

```text
3 <= k
3 <= 3
```

Return:

```text
true
```

---

## Pattern Used

### HashMap / Hashing Pattern

This problem uses the **HashMap / Hashing** pattern.

The main idea is:

```text
Value -> Latest Index
```

A HashMap allows us to quickly check whether we have already seen a value and retrieve its latest index.

### Why HashMap?

Without a HashMap, we might need to compare every element with other elements, which can take:

```text
O(n²)
```

With a HashMap, lookup takes **O(1) average time**, making the complete solution:

```text
O(n)
```

---

## Complexity

Let:

```text
n = nums.length
```

### Time Complexity

```text
O(n)
```

We traverse the array only once.

HashMap operations such as:

```java
containsKey()
get()
put()
```

take **O(1) average time**.

Therefore:

```text
Time = O(n)
```

### Space Complexity

```text
O(n)
```

In the worst case, all elements are different, so the HashMap can contain `n` entries.

Therefore:

```text
Space = O(n)
```

---

## Important Point

We do **not** need to store every index of a number.

We only store its **most recent index**:

```java
map.put(nums[i], i);
```

This is enough because the most recent occurrence always gives the smallest distance to the current index.

---

## Dry Run

```text
nums = [1,2,3,1,2,3]
k = 2
```

| Index | Value | Previous Index | Distance | Result |
|------:|------:|---------------:|---------:|--------|
| 0 | 1 | - | - | Store 1 -> 0 |
| 1 | 2 | - | - | Store 2 -> 1 |
| 2 | 3 | - | - | Store 3 -> 2 |
| 3 | 1 | 0 | 3 | 3 > 2, update 1 -> 3 |
| 4 | 2 | 1 | 3 | 3 > 2, update 2 -> 4 |
| 5 | 3 | 2 | 3 | 3 > 2, update 3 -> 5 |

No valid nearby duplicate is found.

```text
Output: false
```

---

## Key Takeaway

The main trick is:

> **Store the latest index of every number and check the distance whenever the number appears again.**

### Pattern

```text
HashMap + Last Seen Index
```

### Complexity

```text
Time:  O(n)
Space: O(n)
```