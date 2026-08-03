# Add Strings

## Problem Statement

Given two non-negative integers, `num1` and `num2`, represented as strings, return their sum as a string.

### Constraints

- `1 <= num1.length, num2.length <= 10^4`
- `num1` and `num2` contain only digits.
- No leading zeros except for the number `0`.
- You cannot use:
  - `BigInteger`
  - Any built-in library for large integers.
  - Direct conversion of strings to integers.

---

## Example 1

```text
Input:
num1 = "11"
num2 = "123"

Output:
"134"
```

---

## Example 2

```text
Input:
num1 = "456"
num2 = "77"

Output:
"533"
```

---

## Example 3

```text
Input:
num1 = "0"
num2 = "0"

Output:
"0"
```

---

# Java Solution

```java
class Solution {
    public String addStrings(String num1, String num2) {

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carry = 0;

        StringBuilder ans = new StringBuilder();

        while (i >= 0 || j >= 0 || carry != 0) {

            int d1 = (i >= 0) ? num1.charAt(i) - '0' : 0;
            int d2 = (j >= 0) ? num2.charAt(j) - '0' : 0;

            int sum = d1 + d2 + carry;

            ans.append(sum % 10);

            carry = sum / 10;

            i--;
            j--;
        }

        return ans.reverse().toString();
    }
}
```

---

# Approach

We simulate the addition process exactly as we do on paper.

Since the numbers can be very large, we cannot convert them into integers. Therefore, we start traversing both strings from the last digit (right to left).

For each step:

1. Pick the current digit from both strings.
2. Add the digits and the carry.
3. Store the last digit of the sum.
4. Update the carry.
5. Move to the previous digit.
6. Reverse the final result.

---

# Step-by-Step Traversal

Let's understand with:

```text
num1 = "456"
num2 = "77"
```

Initial values:

```text
i = 2
j = 1
carry = 0
result = ""
```

---

### Iteration 1

```text
d1 = 6
d2 = 7

sum = 6 + 7 + 0 = 13

digit = 3
carry = 1

result = "3"
```

---

### Iteration 2

```text
d1 = 5
d2 = 7

sum = 5 + 7 + 1 = 13

digit = 3
carry = 1

result = "33"
```

---

### Iteration 3

```text
d1 = 4
d2 = 0

sum = 4 + 0 + 1 = 5

digit = 5
carry = 0

result = "335"
```

---

### Reverse Result

```text
Before reverse = "335"

After reverse = "533"
```

Final Answer:

```text
533
```

---

# Algorithm

1. Initialize two pointers:

   - `i = num1.length() - 1`
   - `j = num2.length() - 1`

2. Initialize:

   - `carry = 0`
   - `StringBuilder ans`

3. Traverse while:

```text
i >= 0 OR j >= 0 OR carry != 0
```

4. Extract digits:

```text
d1 = (i >= 0) ? num1.charAt(i) - '0' : 0

d2 = (j >= 0) ? num2.charAt(j) - '0' : 0
```

5. Compute:

```text
sum = d1 + d2 + carry
```

6. Store:

```text
ans.append(sum % 10)
```

7. Update carry:

```text
carry = sum / 10
```

8. Move pointers left.

9. Reverse the result.

10. Return the answer.

---

# Pattern Used

## Pattern: Two Pointers + Simulation

### Why Two Pointers?

We need to process digits from right to left.

Two pointers help us:

- Traverse both strings simultaneously.
- Handle different string lengths.
- Avoid converting strings into numbers.

### Why Simulation?

We simulate the manual addition process taught in mathematics:

```text
  456
+  77
-----
  533
```

Since integer conversion is not allowed, simulation is the most efficient approach.

---

# Complexity Analysis

Let:

- `n = num1.length()`
- `m = num2.length()`

## Time Complexity

```text
O(max(n, m))
```

Reason:

- We traverse each digit exactly once.

---

## Space Complexity

```text
O(max(n, m))
```

Reason:

- `StringBuilder` stores the result.

---

# Dry Run

```text
num1 = "11"
num2 = "123"
```

| Step | d1 | d2 | Carry | Sum | Result |
|------|----|----|--------|-----|--------|
| 1 | 1 | 3 | 0 | 4 | 4 |
| 2 | 1 | 2 | 0 | 3 | 43 |
| 3 | 0 | 1 | 0 | 1 | 431 |

Reverse:

```text
431 → 134
```

Final answer:

```text
134
```

---

# Key Takeaways

✅ No integer conversion used.

✅ No BigInteger used.

✅ Two pointers approach.

✅ Simulates manual addition.

✅ Works for very large numbers.

✅ Efficient solution with linear time complexity.
