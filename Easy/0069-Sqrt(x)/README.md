# 69. Sqrt(x)

## Problem Statement

Given a non-negative integer `x`, return the square root of `x` rounded down to the nearest integer.

The returned integer should be non-negative.

**Note:**
You must **not** use any built-in exponent function such as:

- `pow(x, 0.5)`
- `Math.sqrt(x)`

---

## Examples

### Example 1

**Input**

```text
x = 4
```

**Output**

```text
2
```

**Explanation**

```
√4 = 2
```

---

### Example 2

**Input**

```text
x = 8
```

**Output**

```text
2
```

**Explanation**

```
√8 = 2.828...
Floor value = 2
```

---

## Java Solution

```java
class Solution {
    public int mySqrt(int x) {

        if (x < 2)
            return x;

        int left = 1;
        int right = x / 2;
        int ans = 0;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            long sqr = (long) mid * mid;

            if (sqr == x)
                return mid;

            else if (sqr < x) {
                ans = mid;
                left = mid + 1;
            }

            else
                right = mid - 1;
        }

        return ans;
    }
}
```

---

# Approach

The square root of a number always lies between:

- **1** and **x/2** (for x ≥ 2)

Instead of checking every number one by one, we use **Binary Search** to efficiently find the answer.

At every step:

- Find the middle value.
- Square it.
- Compare with `x`.

Three cases are possible:

### Case 1

```
mid² == x
```

We found the exact square root.

Return `mid`.

---

### Case 2

```
mid² < x
```

The answer is greater than `mid`.

Store `mid` as the current best answer.

Search the right half.

---

### Case 3

```
mid² > x
```

The answer lies in the left half.

Discard the right half.

---

Eventually Binary Search finishes.

The last stored answer is the floor value of the square root.

---

# Step-by-Step Traversal

Let's understand using

```
x = 8
```

Initial values

```
left = 1
right = 4
ans = 0
```

---

### Iteration 1

```
mid = 2

2² = 4
```

Since

```
4 < 8
```

Store

```
ans = 2
```

Move right

```
left = 3
```

Current state

```
left = 3
right = 4
ans = 2
```

---

### Iteration 2

```
mid = 3

3² = 9
```

Since

```
9 > 8
```

Move left

```
right = 2
```

Now

```
left > right
```

Binary Search ends.

Return

```
ans = 2
```

Correct Answer ✔

---

# Dry Run

| Left | Right | Mid | Mid² | Action | Answer |
|------|-------|-----|------|--------|--------|
|1|4|2|4|Move Right|2|
|3|4|3|9|Move Left|2|

Return **2**

---

# Algorithm

1. If `x < 2`, return `x`.
2. Set:
   - `left = 1`
   - `right = x / 2`
3. While `left <= right`
   - Find `mid`.
   - Compute `mid * mid`.
4. If square equals `x`, return `mid`.
5. If square is smaller than `x`
   - Save `mid` as answer.
   - Search right half.
6. Otherwise
   - Search left half.
7. Return the stored answer.

---

# Why Binary Search?

A normal linear search would check every number.

Example:

```
1²
2²
3²
4²
...
46340²
```

This takes **O(√x)** time.

Binary Search halves the search space every iteration.

```
1 → 500000000
↓

1 → 250000000
↓

1 → 125000000
↓

...
```

This reduces the complexity to **O(log x)**, making it much faster for large values.

---

# Overflow Handling

Instead of

```java
int sqr = mid * mid;
```

we use

```java
long sqr = (long) mid * mid;
```

because

```
46341 × 46341
```

is larger than the maximum value of an `int`.

Using `long` prevents integer overflow.

---

# Pattern Used

## Binary Search on Answer

This is a classic **Binary Search on Answer** problem.

Instead of searching in an array, we search over the possible answer range.

Search Space:

```
1 ............ x/2
```

Each iteration decides whether:

- the answer is on the left, or
- the answer is on the right.

This makes the algorithm logarithmic.

---

# Time Complexity

Binary Search halves the search range every iteration.

```
Time Complexity = O(log x)
```

---

# Space Complexity

Only a few variables are used.

```
Space Complexity = O(1)
```

---

# Key Concepts Learned

- Binary Search
- Binary Search on Answer
- Floor Square Root
- Overflow Prevention using `long`
- Optimized Searching
- Logarithmic Time Complexity

---

# Final Complexity

| Complexity | Value |
|------------|-------|
| Time | **O(log x)** |
| Space | **O(1)** |
