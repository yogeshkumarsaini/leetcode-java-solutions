# Contains Duplicate

## Problem

Given an integer array `nums`, return `true` if any value appears at least twice in the array, and return `false` if every element is distinct.

### Example 1

```text
Input: nums = [1,2,3,1]
Output: true
Explanation: The element 1 occurs at indices 0 and 3.
```

### Example 2

```text
Input: nums = [1,2,3,4]
Output: false
Explanation: All elements are distinct.
```

### Example 3

```text
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
```

---

## Java Solution

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {

            if (set.contains(num)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }
}
```

> If your LeetCode submission does not already provide the import, add:
>
> ```java
> import java.util.HashSet;
> ```

---

## Approach

We use a **HashSet** to keep track of all elements that we have already visited.

A `HashSet` stores only unique values.

While traversing the array:

- If the current number is already present in the set, it means we have seen it before.
- Therefore, a duplicate exists and we immediately return `true`.
- Otherwise, add the current number to the set.
- If the complete array is traversed without finding a duplicate, return `false`.

---

## Why HashSet?

The main requirement is to quickly check:

> "Have I already seen this number?"

`HashSet` is a good choice because `contains()` and `add()` take **O(1) average time**.

It also automatically maintains unique elements, which makes it ideal for detecting duplicates.

---

## Pattern Used

### Pattern: Hashing / HashSet

This problem uses the **Hashing pattern**.

### Why this pattern?

Whenever a problem asks us to:

- Find duplicates
- Check whether an element has appeared before
- Count frequencies
- Quickly check membership

we can often use a **HashMap or HashSet**.

Here, we only need to know whether an element has already appeared, so `HashSet` is enough.

---

## Algorithm

1. Create an empty `HashSet<Integer>`.
2. Traverse the array from left to right.
3. For each element:
   - Check whether it already exists in the set.
   - If yes, return `true`.
   - If no, add it to the set.
4. If the loop finishes, return `false`.

---

## Step-by-Step Traversal

For:

```text
nums = [1, 2, 3, 1]
```

### Step 1

Current element = `1`

```text
Set = {}
```

`1` is not present.

Add `1`:

```text
Set = {1}
```

### Step 2

Current element = `2`

```text
Set = {1}
```

`2` is not present.

Add `2`:

```text
Set = {1, 2}
```

### Step 3

Current element = `3`

```text
Set = {1, 2}
```

`3` is not present.

Add `3`:

```text
Set = {1, 2, 3}
```

### Step 4

Current element = `1`

```text
Set = {1, 2, 3}
```

`1` is already present.

Therefore:

```text
return true
```

---

## Code Explanation

```java
HashSet<Integer> set = new HashSet<>();
```

Creates an empty HashSet to store elements that we have already seen.

```java
for (int num : nums)
```

Traverses every element of the array one by one.

```java
if (set.contains(num))
```

Checks whether the current number has already appeared.

```java
return true;
```

If it has appeared before, a duplicate exists.

```java
set.add(num);
```

If the number is new, store it in the set.

```java
return false;
```

If the entire array is processed without finding a duplicate, all elements are distinct.

---

## Complexity Analysis

### Time Complexity

**O(n)** average

We traverse the array once.

For every element:

- `HashSet.contains()` → O(1) average
- `HashSet.add()` → O(1) average

Therefore:

```text
O(n)
```

### Space Complexity

**O(n)**

In the worst case, all elements are unique, so the HashSet stores all `n` elements.

```text
O(n)
```

---

## Complexity Summary

| Complexity | Value |
|---|---|
| Time | **O(n)** average |
| Space | **O(n)** |
| Pattern | **Hashing / HashSet** |

---

## Key Takeaway

The important idea is:

```text
Have I seen this element before?
        ↓
    HashSet
        ↓
Yes → Duplicate found → true
No  → Add to Set → continue
```

This is one of the most common applications of the **HashSet / Hashing pattern** in coding interviews.

---

## Tags

`Array` `HashSet` `Hashing` `Duplicate Detection` `LeetCode` `Java`

---

## LeetCode

**Problem:** Contains Duplicate  
**Difficulty:** Easy  
**Language:** Java
