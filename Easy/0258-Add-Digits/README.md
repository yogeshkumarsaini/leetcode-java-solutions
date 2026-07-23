# 258. Add Digits

## Problem Statement

Given an integer `num`, repeatedly add all of its digits until the result contains only one digit.

Return the final single-digit number.

### Example 1

```text
Input: num = 38

38 → 3 + 8 = 11
11 → 1 + 1 = 2

Output: 2
```

### Example 2

```text
Input: num = 0

Output: 0
```

---

# Approach (Mathematical - Digital Root)

Instead of repeatedly adding digits using loops or recursion, we can directly calculate the answer using the **Digital Root** property of numbers.

### Observation

For every positive integer:

- If the number is divisible by **9**, its digital root is **9**.
- Otherwise, the digital root is the remainder when divided by **9**.
- The only exception is **0**, whose digital root is **0**.

This gives the formula:

```java
if(num == 0)
    return 0;
return 1 + (num - 1) % 9;
```

This computes the answer in **constant time** without any iteration.

---

# Why This Formula Works

The digital root of a positive number follows a repeating cycle:

| Number | Digital Root |
|---------|--------------|
|1|1|
|2|2|
|3|3|
|4|4|
|5|5|
|6|6|
|7|7|
|8|8|
|9|9|
|10|1|
|11|2|
|12|3|
|18|9|
|19|1|
|20|2|

Notice the pattern repeats every **9 numbers**.

To correctly map:

```
9 → 9
18 → 9
27 → 9
```

instead of producing `0`, we use:

```
1 + (num - 1) % 9
```

This shifts the range from:

```
0-8
```

to

```
1-9
```

which perfectly matches the Digital Root.

---

# Algorithm

1. If `num` is `0`, return `0`.
2. Otherwise calculate:

   ```
   1 + (num - 1) % 9
   ```

3. Return the result.

---

# Step-by-Step Traversal

### Example 1

Input

```text
num = 38
```

### Step 1

```
num != 0
```

Continue.

### Step 2

```
(num - 1)
= 37
```

### Step 3

```
37 % 9
= 1
```

### Step 4

```
1 + 1
= 2
```

Return

```text
2
```

---

### Example 2

Input

```text
num = 0
```

### Step 1

```
num == 0
```

Return

```text
0
```

---

# Dry Run

### Input

```text
38
```

| Step | Calculation | Result |
|------|-------------|--------|
|1|num == 0?|No|
|2|(38-1)%9|37%9 = 1|
|3|1+1|2|

Answer

```text
2
```

---

# Correctness Proof

For every positive integer:

```
Digital Root =
1 + (num - 1) % 9
```

This is a well-known mathematical property of numbers based on modulo arithmetic.

The formula correctly maps:

- multiples of 9 → 9
- all other numbers → remainder modulo 9
- zero → 0

Thus, the algorithm always returns the correct single-digit result.

---

# Complexity Analysis

### Time Complexity

```
O(1)
```

Only one modulo operation and one addition are performed.

### Space Complexity

```
O(1)
```

No extra memory is used.

---

# Pattern Used

## Mathematics (Digital Root)

### Why this pattern?

Normally, we repeatedly add digits until only one digit remains.

Example:

```
9875

9+8+7+5 = 29
2+9 = 11
1+1 = 2
```

Instead of simulating this process, mathematics proves that every number has a unique **Digital Root**, which can be computed instantly using modulo 9.

Therefore:

- No loops
- No recursion
- Constant time
- Most optimized solution

---

# Java Solution

```java
class Solution {
    public int addDigits(int num) {
        if (num == 0)
            return 0;

        return 1 + (num - 1) % 9;
    }
}
```

---

# Key Takeaways

- Uses **Digital Root Mathematics**.
- No loop or recursion.
- Solves the follow-up requirement.
- Constant **O(1)** time.
- Constant **O(1)** space.
- One of the shortest and most optimized LeetCode solutions.
