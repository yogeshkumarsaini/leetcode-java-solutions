# Convert to Base 7

## Problem Statement

Given an integer `num`, return its **base 7 representation** as a string.

### Example 1

```text
Input: num = 100
Output: "202"
```

### Example 2

```text
Input: num = -7
Output: "-10"
```

---

# Solution (Java)

```java
class Solution {
    public String convertToBase7(int num) {

        if (num == 0)
            return "0";

        StringBuilder sb = new StringBuilder();
        int n = Math.abs(num);

        while (n > 0) {
            sb.append(n % 7);
            n /= 7;
        }

        if (num < 0)
            sb.append('-');

        return sb.reverse().toString();
    }
}
```

---

# Approach

This problem asks us to convert a decimal number into its **Base-7 representation**.

The standard way to convert a decimal number into another base is:

1. Divide the number by the target base (`7`).
2. Store the remainder.
3. Continue dividing the quotient by `7`.
4. The remainders obtained are in **reverse order**, so reverse them at the end.

For negative numbers, first convert the absolute value and then add the negative sign.

---

# Explanation

Suppose:

```text
num = 100
```

### Iteration 1

```text
100 % 7 = 2
100 / 7 = 14

Result = "2"
```

### Iteration 2

```text
14 % 7 = 0
14 / 7 = 2

Result = "20"
```

### Iteration 3

```text
2 % 7 = 2
2 / 7 = 0

Result = "202"
```

The digits are collected from **Least Significant Digit (LSD)** to **Most Significant Digit (MSD)**.

So after reversing:

```text
"202"
```

---

## Dry Run

### Input

```text
num = 100
```

| Iteration | Number | Remainder (num % 7) | Quotient (num / 7) | StringBuilder |
|-----------|--------|---------------------|--------------------|---------------|
| 1 | 100 | 2 | 14 | "2" |
| 2 | 14 | 0 | 2 | "20" |
| 3 | 2 | 2 | 0 | "202" |

Reverse:

```text
202
```

Output:

```text
"202"
```

---

### Dry Run (Negative Number)

Input

```text
num = -7
```

Absolute value:

```text
n = 7
```

| Iteration | Number | Remainder | Quotient | StringBuilder |
|-----------|---------|-----------|-----------|---------------|
|1|7|0|1|"0"|
|2|1|1|0|"01"|

Append '-'

```text
"01-"
```

Reverse

```text
"-10"
```

Output

```text
"-10"
```

---

# Algorithm

1. If `num` is `0`, return `"0"`.
2. Take the absolute value of the number.
3. Create an empty `StringBuilder`.
4. While the number is greater than `0`:
   - Find remainder using `% 7`.
   - Append the remainder.
   - Divide the number by `7`.
5. If the original number was negative, append `'-'`.
6. Reverse the string.
7. Return the final Base-7 representation.

---

# Step-by-Step Traversal

```
num = 100

Start
│
├── n = 100
│
├── 100 % 7 = 2 → append(2)
│
├── n = 14
│
├── 14 % 7 = 0 → append(0)
│
├── n = 2
│
├── 2 % 7 = 2 → append(2)
│
├── n = 0
│
├── Reverse StringBuilder
│
└── Return "202"
```

---

# Why Reverse?

During conversion:

```text
100

↓

2
↓

0
↓

2
```

The digits are generated from **right to left**.

We collect:

```text
2 → 20 → 202
```

which represents

```text
LSD → MSD
```

Reversing converts it into

```text
MSD → LSD
```

which is the correct Base-7 representation.

---

# Pattern Used

## Repeated Division (Base Conversion Pattern)

This problem follows the classic **Repeated Division Algorithm** used for converting numbers between numeral systems.

### Why this pattern?

Because every digit of the new base is obtained using:

```text
digit = number % base
```

and

```text
number = number / base
```

until the number becomes `0`.

This is the standard and most efficient method for converting any decimal number into another base.

The same pattern is used in:

- Convert to Binary
- Convert to Octal
- Convert to Hexadecimal
- Convert to Base 3
- Convert to Base 7
- Decimal to Any Base conversion

---

# Complexity Analysis

Let **N** be the absolute value of the given number.

### Time Complexity

Each iteration divides the number by `7`.

Number of iterations:

```text
log₇(N)
```

Therefore,

```text
Time Complexity = O(log₇ N)
```

---

### Space Complexity

The StringBuilder stores one digit for every Base-7 digit.

Number of digits:

```text
log₇(N)
```

Therefore,

```text
Space Complexity = O(log₇ N)
```

---

# Key Observations

- Base conversion always uses **Repeated Division**.
- Remainders represent the digits of the new base.
- Digits are generated in reverse order.
- `StringBuilder.reverse()` efficiently restores the correct order.
- Negative numbers are handled by converting the absolute value first and then adding the minus sign.

---

# Edge Cases

| Input | Output |
|--------|--------|
| 0 | "0" |
| 1 | "1" |
| 7 | "10" |
| -7 | "-10" |
| 49 | "100" |
| -100 | "-202" |

---

# Interview Takeaways

- Understand the standard base conversion algorithm.
- Use modulus (`%`) to extract digits.
- Use division (`/`) to move to the next digit.
- Reverse the collected digits before returning.
- Handle negative numbers separately without complicating the conversion logic.

---

## Final Complexity

| Complexity | Value |
|------------|-------|
| Time | **O(log₇ N)** |
| Space | **O(log₇ N)** |

---