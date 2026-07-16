# 171. Excel Sheet Column Number

## Problem Statement

Given a string `columnTitle` that represents the column title as it appears in an Excel sheet, return its corresponding column number.

### Examples

**Example 1**

```text
Input: columnTitle = "A"
Output: 1
```

**Example 2**

```text
Input: columnTitle = "AB"
Output: 28
```

**Example 3**

```text
Input: columnTitle = "ZY"
Output: 701
```

---

# Approach

Excel columns follow the **Base-26 Number System**, but unlike a normal base-26 system, there is **no digit 0**.

Instead:

| Letter | Value |
|---------|-------|
| A | 1 |
| B | 2 |
| C | 3 |
| ... | ... |
| Z | 26 |

This is similar to converting a number from another base into decimal.

For every new character:

- Multiply the current result by **26**
- Add the current character's value (`A = 1`, `B = 2`, ..., `Z = 26`)

Formula:

```text
result = result × 26 + currentLetterValue
```

---

# Dry Run

### Input

```text
columnTitle = "AB"
```

### Initial

```text
result = 0
```

### Step 1

Character = 'A'

```text
Value = 1

result = 0 × 26 + 1
       = 1
```

### Step 2

Character = 'B'

```text
Value = 2

result = 1 × 26 + 2
       = 28
```

Final Answer

```text
28
```

---

# Another Dry Run

### Input

```text
columnTitle = "ZY"
```

### Initial

```text
result = 0
```

### Step 1

```text
Character = Z
Value = 26

result = 0 × 26 + 26
       = 26
```

### Step 2

```text
Character = Y
Value = 25

result = 26 × 26 + 25
       = 701
```

Output

```text
701
```

---

# Algorithm

1. Initialize `result = 0`.
2. Traverse the string from left to right.
3. Convert each character into its alphabet position.

   ```text
   value = character - 'A' + 1
   ```

4. Update the answer using

   ```text
   result = result × 26 + value
   ```

5. Return `result`.

---

# Step-by-Step Traversal

Suppose:

```text
columnTitle = "ABC"
```

| Step | Character | Value | Calculation | Result |
|------|-----------|------|-------------|--------|
| 1 | A | 1 | 0 × 26 + 1 | 1 |
| 2 | B | 2 | 1 × 26 + 2 | 28 |
| 3 | C | 3 | 28 × 26 + 3 | 731 |

Final Answer:

```text
731
```

---

# Java Solution

```java
class Solution {
    public int titleToNumber(String columnTitle) {
        int result = 0;

        for (int i = 0; i < columnTitle.length(); i++) {
            result = result * 26 + (columnTitle.charAt(i) - 'A' + 1);
        }

        return result;
    }
}
```

---

# Complexity Analysis

### Time Complexity

Let **n** be the length of the string.

We visit every character exactly once.

```text
Time Complexity = O(n)
```

---

### Space Complexity

Only one integer variable is used.

```text
Space Complexity = O(1)
```

---

# Pattern Used

## Positional Number System (Base-26 Conversion)

This problem follows the **Base Conversion / Positional Number System** pattern.

Every character represents a digit whose position determines its contribution.

Just like decimal numbers:

```text
123

= 1 × 10²
+ 2 × 10¹
+ 3 × 10⁰
```

Excel columns work similarly:

```text
AB

= A × 26¹
+ B × 26⁰

= 1 × 26
+ 2

= 28
```

The iterative formula:

```text
result = result × 26 + currentValue
```

automatically builds the decimal value.

---

# Why This Pattern?

Because each new character shifts the previous value one position to the left in a Base-26 number system.

Instead of storing powers of 26 separately, multiplying the current result by 26 performs the positional shift efficiently.

This makes the solution:

- Simple
- Fast
- Constant Space
- Easy to understand

---

# Key Takeaways

- Excel columns are based on a **1-indexed Base-26 system**.
- `'A'` represents **1**, not **0**.
- Multiply the accumulated result by **26** before adding the next character.
- Traverse the string only once.
- Optimal complexity:
  - **Time:** O(n)
  - **Space:** O(1)

