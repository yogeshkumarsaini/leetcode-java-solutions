# Add Binary

## Problem Statement

Given two binary strings `a` and `b`, return their sum as a binary string.

The input strings contain only `'0'` and `'1'`.

---

## Example 1

**Input**

```text
a = "11"
b = "1"
```

**Output**

```text
100
```

---

## Example 2

**Input**

```text
a = "1010"
b = "1011"
```

**Output**

```text
10101
```

---

# Java Solution

```java
class Solution {
    public String addBinary(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0 || carry != 0) {

            int sum = carry;

            if (i >= 0)
                sum += a.charAt(i) - '0';

            if (j >= 0)
                sum += b.charAt(j) - '0';

            result.append(sum % 2);

            carry = sum / 2;

            i--;
            j--;
        }

        return result.reverse().toString();
    }
}
```

---

# Approach

This problem is solved exactly like **manual binary addition**.

Since addition starts from the **Least Significant Bit (LSB)**, we traverse both strings from **right to left**.

At every step:

- Take the current bit from `a`
- Take the current bit from `b`
- Add the previous carry
- Store the current bit
- Calculate the new carry

Finally, reverse the generated string because we build the answer from right to left.

---

# Algorithm

1. Initialize two pointers

   - `i = a.length()-1`
   - `j = b.length()-1`

2. Initialize

   - `carry = 0`
   - `StringBuilder result`

3. Repeat while

   - `i >= 0`
   - OR `j >= 0`
   - OR `carry != 0`

4. Start with

```text
sum = carry
```

5. If `i >= 0`

```text
sum += a[i]
```

6. If `j >= 0`

```text
sum += b[j]
```

7. Current binary digit

```text
sum % 2
```

Append it to result.

8. Update carry

```text
carry = sum / 2
```

9. Move both pointers left.

10. Reverse the StringBuilder.

11. Return the answer.

---

# Step-by-Step Dry Run

## Input

```text
a = 1010
b = 1011
```

```
      1 0 1 0
    + 1 0 1 1
----------------
```

Initial

```
i = 3
j = 3
carry = 0
result = ""
```

---

### Iteration 1

```
a[i] = 0
b[j] = 1

sum = 0 + 0 + 1 = 1

bit = 1
carry = 0

result = "1"
```

---

### Iteration 2

```
a[i] = 1
b[j] = 1

sum = 1 + 1 + 0 = 2

bit = 0
carry = 1

result = "10"
```

---

### Iteration 3

```
a[i] = 0
b[j] = 0

sum = 0 + 0 + 1 = 1

bit = 1
carry = 0

result = "101"
```

---

### Iteration 4

```
a[i] = 1
b[j] = 1

sum = 1 + 1 + 0 = 2

bit = 0
carry = 1

result = "1010"
```

---

### Iteration 5

Now both pointers are out of bounds.

```
carry = 1

sum = 1

bit = 1

result = "10101"
```

Reverse

```
10101
```

Final Answer

```
10101
```

---

# Traversal

```
a : 1 0 1 0
            ↑

b : 1 0 1 1
            ↑
```

Move from **Right → Left**

```
← ← ← ←
```

Because binary addition always starts from the Least Significant Bit.

---

# Why Reverse?

Suppose

```
11
+1
```

Processing order

```
1 + 1 = 10
```

First digit generated is

```
0
```

Next

```
1 + carry

↓

1
```

Generated string

```
01
```

Correct answer

```
10
```

Hence we reverse at the end.

---

# Pattern Used

## Simulation Pattern

This problem follows the **Simulation Pattern**.

We simulate exactly how humans perform binary addition.

---

## Two Pointer Pattern

We use two pointers.

```
i → end of a

j → end of b
```

Both move toward the beginning.

```
i--
j--
```

---

## Carry Propagation Pattern

Carry from one digit affects the next digit.

```
sum = bit1 + bit2 + carry
```

Then

```
carry = sum / 2
```

This is identical to elementary binary arithmetic.

---

# Why This Pattern?

Because

- Binary addition always starts from the last digit.
- We cannot directly add entire binary strings.
- Carry must propagate to the next position.
- Two pointers efficiently process both strings simultaneously.

---

# Complexity Analysis

Let

```
n = length of a

m = length of b
```

Maximum iterations

```
max(n, m) + 1
```

---

## Time Complexity

```
O(max(n, m))
```

Reason:

Each character is visited only once.

---

## Space Complexity

```
O(max(n, m))
```

Reason:

The result string stores at most

```
max(n, m) + 1
```

characters.

---

# Key Observations

- Traverse from right to left.
- Keep track of carry.
- Append current binary digit.
- Reverse the answer at the end.
- Handles different length strings naturally.
- Handles final carry automatically.

---

# Interview Explanation (30 Seconds)

> We simulate manual binary addition using two pointers starting from the end of both strings. At each step, we add the current bits along with the carry, append `sum % 2` to the result, and update the carry using `sum / 2`. Since digits are generated from least significant to most significant, we reverse the `StringBuilder` before returning the final binary string. The algorithm runs in **O(max(n, m))** time and uses **O(max(n, m))** extra space.