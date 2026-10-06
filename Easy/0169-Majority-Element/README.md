# Majority Element — Boyer-Moore Voting Algorithm

## Problem

Given an integer array `nums` of size `n`, return the **majority element**.

The majority element is the element that appears **more than `⌊n / 2⌋` times**.

The problem guarantees that a majority element always exists.

### Examples

```text
Input:  nums = [3,2,3]
Output: 3
```

```text
Input:  nums = [2,2,1,1,1,2,2]
Output: 2
```

---

## Java Solution

```java
class Solution {
    public int majorityElement(int[] nums) {

        int candidate = 0;
        int count = 0;

        for (int num : nums) {

            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
```

---

## Approach

We use the **Boyer-Moore Voting Algorithm**.

The main idea is:

- Keep one possible `candidate`.
- Keep a `count` representing the candidate's current advantage.
- If the current number is the candidate, increase `count`.
- Otherwise, decrease `count`.
- When `count` becomes `0`, choose the current number as the new candidate.

Why does this work?

The majority element appears **more than half of the array**.

So even if every occurrence of a non-majority element is paired against one occurrence of the majority element, the majority element will still have some occurrences left.

Therefore, the final candidate must be the majority element.

---

## Important Variables

```java
int candidate = 0;
int count = 0;
```

### `candidate`

Stores the current possible majority element.

### `count`

Stores the current vote/strength of the candidate.

- Same as candidate → `count++`
- Different from candidate → `count--`
- `count == 0` → choose a new candidate

---

## Algorithm

1. Initialize `candidate = 0`.
2. Initialize `count = 0`.
3. Traverse the array from left to right.
4. If `count == 0`, make the current number the candidate.
5. If the current number equals the candidate:
   - Increase `count`.
6. Otherwise:
   - Decrease `count`.
7. Continue until the array ends.
8. Return `candidate`.

---

## Step-by-Step Traversal

Consider:

```text
nums = [2,2,1,1,1,2,2]
```

Start:

```text
candidate = 0
count = 0
```

### Step 1

Current number = `2`

`count == 0`, so:

```text
candidate = 2
```

Current number equals candidate:

```text
count = 1
```

State:

```text
candidate = 2
count = 1
```

### Step 2

Current number = `2`

Same as candidate:

```text
count = 2
```

### Step 3

Current number = `1`

Different from candidate:

```text
count = 1
```

### Step 4

Current number = `1`

Different from candidate:

```text
count = 0
```

### Step 5

Current number = `1`

`count == 0`, so choose a new candidate:

```text
candidate = 1
```

Same as candidate:

```text
count = 1
```

### Step 6

Current number = `2`

Different from candidate:

```text
count = 0
```

### Step 7

Current number = `2`

`count == 0`, so:

```text
candidate = 2
count = 1
```

End of array.

Return:

```text
2
```

So the answer is:

```text
2
```

---

## Traversal Table

For:

```text
[2,2,1,1,1,2,2]
```

| Step | Current `num` | Candidate | Count |
|------|----------------|-----------|-------|
| 1 | 2 | 2 | 1 |
| 2 | 2 | 2 | 2 |
| 3 | 1 | 2 | 1 |
| 4 | 1 | 2 | 0 |
| 5 | 1 | 1 | 1 |
| 6 | 2 | 1 | 0 |
| 7 | 2 | 2 | 1 |

Final candidate:

```text
2
```

---

## Why `count--` Works

Think of `count` as votes.

Suppose:

```text
candidate = 2
```

When we see:

```text
2
```

the candidate gets one vote:

```text
count++
```

When we see another value, for example:

```text
1
```

the candidate loses one vote:

```text
count--
```

So one `2` and one `1` effectively cancel each other.

Because the majority element occurs more than `n/2` times, it cannot be completely cancelled by all other elements.

That is the key reason the algorithm works.

---

## Pattern Used

### Boyer-Moore Voting Pattern

This problem uses the **Boyer-Moore Voting Algorithm**.

It is useful when:

- We need to find an element occurring more than half the time.
- A majority element is guaranteed to exist.
- We want `O(n)` time.
- We want `O(1)` extra space.

### Why use this pattern?

A HashMap solution can count every element, but it needs extra memory:

```text
Time:  O(n)
Space: O(n)
```

Boyer-Moore avoids storing frequencies.

It only keeps:

```text
candidate
count
```

Therefore:

```text
Time:  O(n)
Space: O(1)
```

---

## Complexity

### Time Complexity

```text
O(n)
```

We traverse the array only once.

If the array contains `n` elements, each element is processed one time.

### Space Complexity

```text
O(1)
```

Only two variables are used:

```java
candidate
count
```

No HashMap, array, or additional data structure is required.

---

## Follow-Up

> Could you solve the problem in linear time and in O(1) space?

Yes.

This solution satisfies the follow-up:

```text
Time  = O(n)
Space = O(1)
```

---

## Quick Interview Explanation

You can explain the solution like this:

> I use the Boyer-Moore Voting Algorithm. I maintain a candidate and a count. If the current element is equal to the candidate, I increment the count; otherwise, I decrement it. Whenever the count becomes zero, I select the current element as the new candidate. Since the majority element appears more than n/2 times, it cannot be completely cancelled by all other elements. Therefore, the final candidate is the majority element. The time complexity is O(n) and the space complexity is O(1).

---

## Key Takeaway

Remember this pattern:

```text
count == 0
    ↓
choose new candidate

same as candidate
    ↓
count++

different from candidate
    ↓
count--
```

Final candidate = **Majority Element**.

---

## LeetCode

Problem: **Majority Element**

Pattern: **Boyer-Moore Voting Algorithm**

Complexity:

```text
Time  → O(n)
Space → O(1)
```
