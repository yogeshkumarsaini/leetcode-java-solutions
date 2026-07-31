# Convert a Number to Hexadecimal

## Problem Statement

Given a **32-bit integer** `num`, return its **hexadecimal (base-16)** representation.

- Use lowercase letters (`a` - `f`).
- Do not include leading zeros.
- If `num` is negative, represent it using **32-bit two's complement**.
- Do not use any built-in hexadecimal conversion functions.

### Example 1

```text
Input: num = 26
Output: "1a"
```

### Example 2

```text
Input: num = -1
Output: "ffffffff"
```

---

# Approach

A hexadecimal digit represents **4 bits**.

Instead of repeatedly dividing by **16**, we can directly extract the last **4 bits** using a **bitwise AND** operation.

```java
num & 15
```

Since

```text
15 = 1111 (binary)
```

the operation keeps only the last four bits.

Each extracted value (0–15) is converted into its hexadecimal character using the string:

```text
"0123456789abcdef"
```

After extracting one hexadecimal digit, shift the number **4 bits to the right**.

```java
num >>>= 4;
```

We use the **unsigned right shift (`>>>`)** instead of `>>`.

This is important because for negative numbers we must fill the leftmost bits with **0**, otherwise the number would never become zero.

Since digits are obtained from **least significant to most significant**, reverse the final string before returning it.

---

# Algorithm

1. If `num == 0`, return `"0"`.
2. Create a hexadecimal lookup string:
   `"0123456789abcdef"`.
3. Create an empty `StringBuilder`.
4. While `num != 0`:
   - Extract last 4 bits using `num & 15`.
   - Convert it into hexadecimal character.
   - Append the character.
   - Unsigned right shift the number by 4 bits (`num >>>= 4`).
5. Reverse the string.
6. Return the answer.

---

# Step-by-Step Traversal

## Example 1

Input

```text
num = 26
```

Binary

```text
26 = 00011010
```

|Iteration|num|num & 15|Hex Digit|Result|
|---------|---|---------|---------|------|
|1|26|10|a|"a"|
|2|1|1|1|"a1"|

Reverse

```text
"a1" → "1a"
```

Output

```text
1a
```

---

## Example 2

Input

```text
num = -1
```

32-bit Representation

```text
11111111111111111111111111111111
```

Every group of 4 bits is

```text
1111
```

which equals

```text
15 → 'f'
```

Eight hexadecimal digits are produced.

Output

```text
ffffffff
```

---

# Dry Run

```text
num = 26

Iteration 1
num = 26
num & 15 = 10
append 'a'

num >>>= 4
num = 1

Iteration 2
num = 1
num & 15 = 1
append '1'

num >>>= 4
num = 0

Current String = "a1"

Reverse

Answer = "1a"
```

---

# Why Bitwise AND with 15?

```text
15 = 1111 (binary)
```

Example

```text
26 = 11010

11010
01111
-----
01010 = 10
```

Only the last four bits remain.

This directly gives one hexadecimal digit.

---

# Why Unsigned Right Shift (`>>>`)?

### Signed Shift (`>>`)

Maintains the sign bit.

Example

```text
-1 >> 4

11111111111111111111111111111111
```

It remains `-1`, causing an infinite loop.

---

### Unsigned Shift (`>>>`)

Fills leftmost bits with zero.

```text
11111111111111111111111111111111

>>>

00001111111111111111111111111111
```

Eventually the number becomes zero and the loop ends.

---

# Complexity Analysis

### Time Complexity

Each iteration processes **4 bits**.

A 32-bit integer has

```text
32 / 4 = 8
```

maximum hexadecimal digits.

Therefore,

```text
Time Complexity = O(8)
```

Since 8 is constant,

```text
O(1)
```

---

### Space Complexity

The StringBuilder stores at most **8 characters**.

```text
Space Complexity = O(8)
```

Constant space,

```text
O(1)
```

---

# Pattern Used

## Bit Manipulation

### Why?

- Efficient extraction of 4-bit groups.
- Direct conversion into hexadecimal digits.
- Avoids repeated division and modulo operations.
- Naturally handles two's complement representation for negative numbers.
- Constant time because a 32-bit integer always has at most 8 hexadecimal digits.

---

# Java Solution

```java
class Solution {
    public String toHex(int num) {
        if (num == 0) return "0";

        String hex = "0123456789abcdef";
        StringBuilder result = new StringBuilder();

        while (num != 0) {
            result.append(hex.charAt(num & 15));
            num >>>= 4;
        }

        return result.reverse().toString();
    }
}
```

---

# Key Concepts Learned

- Bit Manipulation
- Bit Masking (`& 15`)
- Unsigned Right Shift (`>>>`)
- Two's Complement Representation
- Hexadecimal Number System
- StringBuilder
- Constant-Time Processing

---

# Interview Questions

### Q1. Why use `num & 15`?

Because `15 (1111₂)` extracts the last four bits, which represent one hexadecimal digit.

---

### Q2. Why use `>>>` instead of `>>`?

`>>>` fills leading bits with zeros, allowing negative numbers to eventually become zero. Using `>>` keeps the sign bit and can cause an infinite loop.

---

### Q3. Why reverse the string?

Digits are extracted from the least significant nibble first, so reversing restores the correct order.

---

### Q4. Why is the complexity O(1)?

A 32-bit integer always produces at most **8 hexadecimal digits**, making the number of iterations constant.

---

### Q5. Which DSA pattern is used?

**Bit Manipulation (Bit Masking + Unsigned Right Shift)** because hexadecimal digits correspond exactly to 4-bit groups, enabling efficient extraction and conversion.