# 202. Happy Number

## Problem Statement

Write an algorithm to determine if a number `n` is a **happy number**.

A **happy number** is defined by the following process:

1. Start with any positive integer.
2. Replace the number with the sum of the squares of its digits.
3. Repeat the process until:
   - The number becomes **1** (Happy Number), or
   - The process enters a cycle that never reaches **1** (Not Happy Number).

Return `true` if `n` is a happy number, otherwise return `false`.

**LeetCode:** https://leetcode.com/problems/happy-number/

---

# Example

### Example 1

**Input**

```text
n = 19
```

**Output**

```text
true
```

### Explanation

```text
19
↓
1² + 9² = 82

82
↓
8² + 2² = 68

68
↓
6² + 8² = 100

100
↓
1² + 0² + 0² = 1

Since it reaches 1,
19 is a Happy Number.
```

---

### Example 2

**Input**

```text
n = 2
```

**Output**

```text
false
```

Explanation

```text
2
↓
4
↓
16
↓
37
↓
58
↓
89
↓
145
↓
42
↓
20
↓
4

The sequence repeats forever.

So, 2 is NOT a Happy Number.
```

---

# Approach

Instead of storing every visited number in a HashSet, we use **Floyd's Cycle Detection Algorithm (Slow & Fast Pointer)**.

This is the same algorithm used for detecting cycles in Linked Lists.

The sequence generated from a number behaves like a linked list:

```text
19
 ↓
82
 ↓
68
 ↓
100
 ↓
1
```

or

```text
2
 ↓
4
 ↓
16
 ↓
37
 ↓
58
 ↓
89
 ↓
145
 ↓
42
 ↓
20
 ↺
4
```

If a cycle exists, the slow and fast pointers will eventually meet.

If the fast pointer reaches **1**, then the number is happy.

---

# Algorithm

1. Create a helper function `getNext(n)`.

2. Compute the sum of the squares of every digit.

3. Initialize

```text
slow = n
fast = getNext(n)
```

4. Repeat until

- fast becomes 1, or
- slow == fast

5. Move

```text
slow = getNext(slow)
fast = getNext(getNext(fast))
```

6. If `fast == 1`

Return `true`

Otherwise

Return `false`.

---

# Step-by-Step Traversal

## Input

```text
n = 19
```

### Initial State

```text
slow = 19
fast = getNext(19)

getNext(19)
= 1² + 9²
= 82

slow = 19
fast = 82
```

---

### Iteration 1

```text
slow

19
↓

82

fast

82
↓

68
↓

100

slow = 82
fast = 100
```

---

### Iteration 2

```text
slow

82
↓

68

fast

100
↓

1
↓

1

slow = 68
fast = 1
```

Since

```text
fast == 1
```

Return

```text
true
```

---

# Dry Run (Not Happy Number)

Input

```text
2
```

Sequence

```text
2
↓

4
↓

16
↓

37
↓

58
↓

89
↓

145
↓

42
↓

20
↓

4
```

Eventually,

```text
slow == fast
```

before reaching **1**.

Therefore,

```text
false
```

---

# Why Floyd's Cycle Detection?

A cycle is guaranteed for every non-happy number.

Instead of remembering every visited number using a HashSet, Floyd's algorithm detects the cycle using only **two pointers**.

Advantages:

- No extra memory
- Faster in practice
- Elegant solution
- Interview favorite

---

# Pattern Used

## Pattern

**Fast & Slow Pointer (Floyd's Cycle Detection)**

---

## Why this Pattern?

The generated sequence behaves exactly like a linked list.

Every number points to its next transformed value.

If there is a cycle:

```text
4
↓
16
↓
37
↓
58
↓
89
↓
145
↓
42
↓
20
↺
4
```

Slow pointer moves one step.

Fast pointer moves two steps.

If they meet, a cycle exists.

If fast reaches **1**, no cycle exists.

---

# Complexity Analysis

Let

- **k** = Number of digits in `n`

Each call to `getNext()` processes every digit once.

### Time Complexity

Each transformation takes

```text
O(k)
```

The number of transformations before reaching either **1** or a cycle is bounded by a small constant (for 32-bit integers).

Overall:

```text
Time Complexity: O(k)
```

For integers, this is effectively:

```text
O(1)
```

---

### Space Complexity

Only a few integer variables are used.

```text
Space Complexity: O(1)
```

---

# Java Solution

```java
class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);

        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }

    private int getNext(int n) {
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }

        return sum;
    }
}
```

---

# Key Takeaways

- A Happy Number eventually reaches **1**.
- Non-happy numbers always enter a cycle.
- Floyd's Cycle Detection efficiently detects cycles without extra memory.
- The `getNext()` function computes the sum of the squares of digits.
- This solution is optimal with **O(1)** space and effectively **O(1)** time for fixed-size integers.

---

## Topics Covered

- Mathematics
- Digit Manipulation
- Fast & Slow Pointer
- Floyd's Cycle Detection
- Cycle Detection
- Simulation

---

## Interview Tip

If an interviewer asks **"Can you solve it without using a HashSet?"**, the expected optimal solution is **Floyd's Cycle Detection Algorithm**. It eliminates extra space while efficiently detecting cycles, making it the preferred interview approach.