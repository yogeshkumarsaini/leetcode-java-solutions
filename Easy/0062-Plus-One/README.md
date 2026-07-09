# 66. Plus One

## Problem Statement

You are given a large integer represented as an integer array `digits`, where each `digits[i]` represents a digit of the integer.

The digits are stored from **most significant digit** to **least significant digit**.

Increment the integer by **one** and return the resulting array.

---

## Example 1

Input:
```text
digits = [1,2,3]
```

Output:
```text
[1,2,4]
```

Explanation:

```
123 + 1 = 124
```

---

## Example 2

Input:

```text
digits = [4,3,2,1]
```

Output:

```text
[4,3,2,2]
```

Explanation:

```
4321 + 1 = 4322
```

---

## Example 3

Input:

```text
digits = [9]
```

Output:

```text
[1,0]
```

Explanation:

```
9 + 1 = 10
```

---

# Approach

Since addition always starts from the **last digit (Least Significant Digit)**, we traverse the array **from right to left**.

There are only two possible situations:

### Case 1

Current digit is less than 9.

Example:

```
123

Last digit = 3

3 + 1 = 4
```

Result:

```
124
```

No carry is generated.

Return immediately.

---

### Case 2

Current digit is 9.

Example:

```
129

9 + 1 = 10
```

Store

```
0
```

and carry

```
1
```

Move to the previous digit.

Example:

```
199

↓

190

↓

200
```

Keep repeating until a digit smaller than 9 is found.

---

### Special Case

If every digit is 9

Example

```
999

↓

1000
```

Original array cannot store one extra digit.

Create a new array of size

```
n + 1
```

Store

```
1
```

at index 0.

Remaining positions automatically remain 0.

Result

```
1000
```

---

# Step-by-Step Traversal

Example

```
digits = [4,5,9,9]
```

### Initial

```
[4,5,9,9]
```

Start from last index.

---

### Step 1

```
9 == 9

Make it 0

[4,5,9,0]
```

Carry moves left.

---

### Step 2

```
9 == 9

Make it 0

[4,5,0,0]
```

Carry moves left.

---

### Step 3

```
5 < 9

Increment it

5 → 6
```

Array becomes

```
[4,6,0,0]
```

Return immediately.

---

Another Example

```
digits = [9,9,9]
```

Traversal

```
[9,9,9]

↓

[9,9,0]

↓

[9,0,0]

↓

[0,0,0]
```

Loop ends.

Create new array.

```
[1,0,0,0]
```

Return.

---

# Algorithm

1. Start from the last index.
2. If current digit is less than 9:
   - Increment it.
   - Return the array.
3. Otherwise:
   - Make current digit 0.
   - Continue moving left.
4. If loop finishes, every digit was 9.
5. Create a new array of size `n + 1`.
6. Put `1` at index `0`.
7. Return the new array.

---

# Dry Run

Input

```
digits = [8,9,9]
```

Iteration 1

```
Index = 2

9 → 0
```

Array

```
[8,9,0]
```

Carry continues.

---

Iteration 2

```
Index = 1

9 → 0
```

Array

```
[8,0,0]
```

Carry continues.

---

Iteration 3

```
Index = 0

8 < 9

8 → 9
```

Result

```
[9,0,0]
```

---

# Java Solution

```java
class Solution {
    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {

            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        int[] ans = new int[digits.length + 1];
        ans[0] = 1;

        return ans;
    }
}
```

---

# Complexity Analysis

## Time Complexity

Worst Case

```
O(n)
```

Reason:

In the worst case (9999...), every digit is visited once.

Example

```
9999999
```

Every element is traversed.

---

## Space Complexity

Normal Case

```
O(1)
```

No extra space is used.

Worst Case

```
O(n)
```

When all digits are 9, a new array of size `n+1` is created.

Example

```
999

↓

1000
```

---

# Pattern Used

## Simulation / Array Traversal

This problem follows the **Simulation** pattern.

Why?

Because we simulate the manual addition process exactly as humans perform addition.

It also uses:

- Reverse Traversal
- Carry Propagation

---

# Key Observation

Addition always starts from the last digit.

If the current digit is less than 9:

```
Increment
Return
```

If the digit is 9:

```
Make it 0
Carry = 1
Move Left
```

If all digits become 0:

```
Create a new array.

Put 1 at the front.
```

---

# Interview Explanation (30 Seconds)

- Traverse the array from right to left because addition starts from the least significant digit.
- If a digit is less than 9, increment it and return immediately since no carry is generated.
- If the digit is 9, change it to 0 and propagate the carry to the previous digit.
- If every digit becomes 0 after traversal, create a new array of size `n+1`, place `1` at index `0`, and return it.
- Overall complexity is **O(n)** time with **O(1)** auxiliary space (or **O(n)** only in the special case where a new array is required).