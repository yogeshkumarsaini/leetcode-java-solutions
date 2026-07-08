# 13. Roman to Integer

## Problem Statement

Roman numerals are represented by seven symbols:

| Symbol | Value |
|---------|------:|
| I | 1 |
| V | 5 |
| X | 10 |
| L | 50 |
| C | 100 |
| D | 500 |
| M | 1000 |

Roman numerals are generally written from largest to smallest. However, there are six subtraction cases:

- IV = 4
- IX = 9
- XL = 40
- XC = 90
- CD = 400
- CM = 900

Given a Roman numeral, convert it into an integer.

---

## Example

### Example 1

```
Input: "III"

Output: 3
```

---

### Example 2

```
Input: "LVIII"

Output: 58
```

Explanation

```
L = 50
V = 5
III = 3

50 + 5 + 3 = 58
```

---

### Example 3

```
Input: "MCMXCIV"

Output: 1994
```

Explanation

```
M  = 1000
CM = 900
XC = 90
IV = 4

1000 + 900 + 90 + 4 = 1994
```

---

# Approach

Traverse the Roman numeral from **left to right**.

For every character,

- Find its integer value.
- Compare it with the next character (if it exists).

There are two possibilities:

### Case 1

If

```
current >= next
```

Simply add the current value.

Example

```
VI

5 > 1

Answer = 5 + 1
```

---

### Case 2

If

```
current < next
```

It is a subtraction pair.

Subtract the current value.

Example

```
IV

1 < 5

Answer = -1 + 5 = 4
```

Continue until the end of the string.

The last character is always added because there is no next character to compare.

---

# Step-by-Step Traversal

## Example

```
s = "MCMXCIV"
```

| Index | Current | Value | Next | Action | Result |
|------:|---------|------:|------|---------|-------:|
|0|M|1000|C|Add|1000|
|1|C|1000? no 100|M|Subtract|900|
|2|M|1000|X|Add|1900|
|3|X|10|C|Subtract|1890|
|4|C|100|I|Add|1990|
|5|I|1|V|Subtract|1989|
|6|V|5|-|Add|1994|

Final Answer

```
1994
```

---

# Algorithm

1. Initialize answer as 0.
2. Traverse the string from left to right.
3. Convert current Roman character into its integer value.
4. If next character exists:
   - If current value is smaller than next value, subtract it.
   - Otherwise add it.
5. Add the last character.
6. Return the answer.

---

# Dry Run

Input

```
LVIII
```

### Iteration 1

```
L = 50

Next = V (5)

50 > 5

Answer = 50
```

---

### Iteration 2

```
V = 5

Next = I (1)

5 > 1

Answer = 55
```

---

### Iteration 3

```
I = 1

Next = I

Answer = 56
```

---

### Iteration 4

```
I = 1

Next = I

Answer = 57
```

---

### Iteration 5

```
Last Character

I = 1

Answer = 58
```

---

# Complexity Analysis

### Time Complexity

```
O(n)
```

Reason:

- Only one traversal of the string.
- Each character is processed exactly once.

---

### Space Complexity

```
O(1)
```

Reason:

Only a few integer variables are used regardless of input size.

---

# Pattern Used

## Linear Traversal + Adjacent Comparison

This problem belongs to the **Linear Traversal** pattern.

During traversal, every character is compared with its immediate next character.

```
Current
   ↓
M C M X C I V
  ↓
 Next
```

Whenever

```
current < next
```

subtract.

Otherwise

```
add.
```

---

# Why This Pattern?

Roman numerals only require comparing adjacent symbols.

We never need

- Stack
- HashMap
- Recursion
- Extra Array

A single scan with neighboring comparison is sufficient.

Therefore,

**Linear Traversal with Adjacent Comparison** is the most efficient approach.

---

# Java Solution

```java
class Solution {
    public int romanToInt(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int current = value(s.charAt(i));

            if (i < s.length() - 1) {
                int next = value(s.charAt(i + 1));

                if (current < next)
                    ans -= current;
                else
                    ans += current;
            } else {
                ans += current;
            }
        }

        return ans;
    }

    private int value(char ch) {
        switch (ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }
}
```

---

# Key Takeaways

- Single pass solution.
- Compare current symbol with next symbol.
- Smaller before larger ⇒ subtract.
- Otherwise ⇒ add.
- Optimal Time Complexity: **O(n)**
- Constant Space Complexity: **O(1)**