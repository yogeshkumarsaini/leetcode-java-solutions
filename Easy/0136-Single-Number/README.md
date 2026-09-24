# Single Number

## Problem

Given a non-empty array of integers `nums`, every element appears twice except for one. Find that single element.

The solution must have:

- **Linear runtime complexity:** `O(n)`
- **Constant extra space:** `O(1)`

### Example 1

```text
Input:  nums = [2,2,1]
Output: 1
```

### Example 2

```text
Input:  nums = [4,1,2,1,2]
Output: 4
```

### Example 3

```text
Input:  nums = [1]
Output: 1
```

---

## Approach

We use the **XOR (`^`) bit manipulation** operator.

XOR has three important properties:

```text
a ^ a = 0
a ^ 0 = a
a ^ b ^ a = b
```

The most important property for this problem is:

```text
same number ^ same number = 0
```

Therefore, all duplicate numbers cancel each other out.

For example:

```text
4 ^ 4 = 0
1 ^ 1 = 0
2 ^ 2 = 0
```

The number that appears only once remains as the final result.

---

## Why XOR Works

Consider:

```text
nums = [4,1,2,1,2]
```

Start with:

```text
xor = 0
```

Now traverse the array:

```text
0 ^ 4 = 4
4 ^ 1 = 5
5 ^ 2 = 7
7 ^ 1 = 6
6 ^ 2 = 4
```

The duplicate values `1` and `2` cancel each other:

```text
1 ^ 1 = 0
2 ^ 2 = 0
```

So only `4` remains.

```text
Answer = 4
```

---

## Algorithm

1. Create an integer variable `xor` and initialize it to `0`.
2. Traverse every element of the array.
3. XOR the current element with `xor`.
4. Duplicate elements cancel each other because `a ^ a = 0`.
5. The element that appears only once remains in `xor`.
6. Return `xor`.

---

## Step-by-Step Traversal

For:

```text
nums = [2,2,1]
```

### Step 1

```text
xor = 0
```

Take `2`:

```text
xor = 0 ^ 2
xor = 2
```

### Step 2

Take the next `2`:

```text
xor = 2 ^ 2
xor = 0
```

The duplicate `2` values cancel each other.

### Step 3

Take `1`:

```text
xor = 0 ^ 1
xor = 1
```

Final result:

```text
1
```

---

## Java Code

```java
class Solution {
    public int singleNumber(int[] nums) {
        int xor = 0;

        for (int num : nums) {
            xor = xor ^ num;
        }

        return xor;
    }
}
```

---

## Code Explanation

### Initialize XOR

```java
int xor = 0;
```

We start with `0` because:

```text
0 ^ number = number
```

### Traverse the Array

```java
for (int num : nums) {
```

The enhanced `for` loop visits every element exactly once.

### Apply XOR

```java
xor = xor ^ num;
```

Each number is combined with the current XOR result.

Duplicate numbers cancel:

```text
2 ^ 2 = 0
```

### Return the Single Number

```java
return xor;
```

After all elements are processed, only the number appearing once remains.

---

## Complexity Analysis

### Time Complexity

```text
O(n)
```

We traverse the array exactly once.

If the array contains `n` elements, the loop performs `n` operations.

### Space Complexity

```text
O(1)
```

Only one extra variable is used:

```java
int xor = 0;
```

No HashMap, Set, sorting, or extra array is required.

---

## Pattern Used

### Bit Manipulation — XOR

This problem uses the **XOR Bit Manipulation pattern**.

### Why this pattern?

The problem specifically says:

- Every number appears exactly twice.
- Only one number appears once.
- We need `O(n)` time.
- We need `O(1)` extra space.

XOR is ideal because duplicate numbers automatically cancel:

```text
a ^ a = 0
```

and:

```text
0 ^ a = a
```

Therefore:

```text
a ^ b ^ a = b
```

This lets us find the unique number using only one variable.

---

## Pattern Recognition Tip

When you see a problem where:

```text
Every element appears twice
except one element
```

Think of:

```text
XOR
```

The standard solution is:

```java
int xor = 0;

for (int num : nums) {
    xor ^= num;
}

return xor;
```

---

## Final Summary

| Item | Result |
|---|---|
| Pattern | Bit Manipulation / XOR |
| Traversal | Single pass |
| Time Complexity | `O(n)` |
| Space Complexity | `O(1)` |
| Extra Data Structure | None |
| Main Property | `a ^ a = 0` |
| Main Property | `a ^ 0 = a` |

The XOR approach satisfies both required constraints: **linear time and constant extra space**.
