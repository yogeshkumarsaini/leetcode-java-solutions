# Self Dividing Numbers

## Problem

A **self-dividing number** is a number that is divisible by every digit it contains.

For example:

```text
128
```

`128` is a self-dividing number because:

```text
128 % 1 == 0
128 % 2 == 0
128 % 8 == 0
```

A self-dividing number **cannot contain the digit `0`**, because division by zero is not possible.

Given two integers `left` and `right`, return all self-dividing numbers in the range:

```text
[left, right]
```

Both `left` and `right` are inclusive.

---

## Examples

### Example 1

```text
Input:
left = 1
right = 22

Output:
[1,2,3,4,5,6,7,8,9,11,12,15,22]
```

### Example 2

```text
Input:
left = 47
right = 85

Output:
[48,55,66,77]
```

---

## Constraints

```text
1 <= left <= right <= 10^4
```

---

## Java Solution

```java
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {

        List<Integer> list = new ArrayList<>();

        for (int i = left; i <= right; i++) {

            int temp = i;
            boolean isSelf = true;

            while (temp > 0) {

                int digit = temp % 10;

                // Number cannot contain zero
                // and must be divisible by every digit
                if (digit == 0 || i % digit != 0) {
                    isSelf = false;
                    break;
                }

                temp /= 10;
            }

            if (isSelf) {
                list.add(i);
            }
        }

        return list;
    }
}
```

---

# Approach

The main idea is to check every number from `left` to `right`.

For each number:

1. Extract each digit.
2. Check whether the digit is `0`.
3. Check whether the original number is divisible by that digit.
4. If any digit fails the condition, the number is not self-dividing.
5. If all digits satisfy the condition, add the number to the result list.

---

# Algorithm

Suppose:

```text
left = 1
right = 22
```

We check every number from `1` to `22`.

For each number `i`:

### Step 1 — Copy the number

```java
int temp = i;
```

We use `temp` for digit traversal because we need to keep the original number `i` unchanged.

---

### Step 2 — Extract the last digit

```java
int digit = temp % 10;
```

The `% 10` operation gives the last digit.

Example:

```text
128 % 10 = 8
```

---

### Step 3 — Check for zero

```java
if (digit == 0)
```

A self-dividing number cannot contain zero.

For example:

```text
10
```

contains `0`, so it is immediately invalid.

---

### Step 4 — Check divisibility

```java
i % digit != 0
```

The important point is that we divide the **original number `i`** by the extracted digit.

Example:

```text
128 % 8 == 0
128 % 2 == 0
128 % 1 == 0
```

Therefore `128` is self-dividing.

---

### Step 5 — Remove the last digit

```java
temp /= 10;
```

For example:

```text
128 / 10 = 12
12 / 10 = 1
1 / 10 = 0
```

When `temp` becomes `0`, all digits have been checked.

---

### Step 6 — Add valid numbers

If every digit passed the condition:

```java
if (isSelf) {
    list.add(i);
}
```

The number is added to the result.

---

# Step-by-Step Traversal

Consider:

```text
i = 128
```

Initial:

```text
temp = 128
isSelf = true
```

### Traversal 1

```text
digit = 128 % 10
      = 8
```

Check:

```text
128 % 8 == 0
```

Valid.

Remove last digit:

```text
temp = 128 / 10
     = 12
```

---

### Traversal 2

```text
digit = 12 % 10
      = 2
```

Check:

```text
128 % 2 == 0
```

Valid.

Remove last digit:

```text
temp = 12 / 10
     = 1
```

---

### Traversal 3

```text
digit = 1 % 10
      = 1
```

Check:

```text
128 % 1 == 0
```

Valid.

Remove last digit:

```text
temp = 1 / 10
     = 0
```

Loop ends.

Since every digit was valid:

```text
128 is a self-dividing number
```

---

# Pattern Used

## Digit Extraction / Digit Traversal Pattern

This solution uses the **Digit Extraction** pattern.

The key operations are:

```java
temp % 10
```

and

```java
temp /= 10
```

These two operations allow us to process a number digit by digit.

### Why this pattern?

We need to inspect **every individual digit** of each number.

Instead of converting the number to a String:

```java
String.valueOf(i)
```

we can process digits mathematically.

This makes the solution simple and efficient.

---

# Why `% 10`?

The modulo operator `%` gives the remainder.

For a decimal number:

```text
number % 10
```

returns its last digit.

Examples:

```text
123 % 10 = 3
456 % 10 = 6
789 % 10 = 9
```

---

# Why `/= 10`?

Integer division by `10` removes the last digit.

Examples:

```text
123 / 10 = 12
12 / 10 = 1
1 / 10 = 0
```

Therefore:

```java
while (temp > 0)
```

allows us to traverse all digits.

---

# Dry Run

For:

```text
left = 1
right = 22
```

Some numbers:

| Number | Digits | Result        |
| -----: | ------ | ------------- |
|      1 | 1      | Self-dividing |
|      2 | 2      | Self-dividing |
|      3 | 3      | Self-dividing |
|      4 | 4      | Self-dividing |
|      5 | 5      | Self-dividing |
|      6 | 6      | Self-dividing |
|      7 | 7      | Self-dividing |
|      8 | 8      | Self-dividing |
|      9 | 9      | Self-dividing |
|     10 | 1, 0   | Not valid     |
|     11 | 1, 1   | Self-dividing |
|     12 | 1, 2   | Self-dividing |
|     13 | 1, 3   | Not valid     |
|     15 | 1, 5   | Self-dividing |
|     22 | 2, 2   | Self-dividing |

Final result:

```text
[1,2,3,4,5,6,7,8,9,11,12,15,22]
```

---

# Complexity Analysis

Let:

```text
N = right - left + 1
```

be the number of integers in the range.

Let:

```text
D = number of digits in each number
```

Since the maximum value is:

```text
10^4
```

the number of digits is very small.

## Time Complexity

For every number, we inspect each digit.

Therefore:

```text
O(N × D)
```

Since `D` is at most `5` for the given constraints:

```text
O(5N)
```

which simplifies to:

```text
O(N)
```

So the overall time complexity is:

```text
O((right - left + 1) × D)
```

or effectively:

```text
O(N)
```

---

## Space Complexity

Apart from the returned result list, we only use a few variables:

```java
int temp;
int digit;
boolean isSelf;
```

Therefore auxiliary space is:

```text
O(1)
```

The output list itself requires:

```text
O(K)
```

where `K` is the number of self-dividing numbers found.

So:

```text
Auxiliary Space = O(1)
Output Space = O(K)
```

---

# Important Observation

We must always check divisibility using the **original number**:

```java
i % digit
```

and **not**:

```java
temp % digit
```

For example, with:

```text
128
```

we need:

```text
128 % 8
128 % 2
128 % 1
```

not:

```text
8 % 8
2 % 2
1 % 1
```

Therefore keeping `i` unchanged and using `temp` only for digit traversal is important.

---

# Edge Cases

### 1. Single-digit numbers

Every number from `1` to `9` is self-dividing because:

```text
number % number == 0
```

---

### 2. Number containing zero

Example:

```text
10
```

Since it contains `0`, it is not self-dividing.

```java
if (digit == 0)
```

handles this case.

---

### 3. Number not divisible by one of its digits

Example:

```text
13
```

Check:

```text
13 % 1 == 0
13 % 3 != 0
```

Therefore:

```text
13 is not self-dividing
```

---

### 4. Same left and right

Example:

```text
left = 22
right = 22
```

Only `22` is checked.

Result:

```text
[22]
```

---

# Key Java Concepts Used

* `for` loop
* `while` loop
* Modulo operator `%`
* Integer division `/`
* Boolean flag
* ArrayList
* Digit extraction
* Mathematical traversal

---

# Summary

The solution checks every number in the given range and examines its digits one by one.

The core logic is:

```java
int digit = temp % 10;
```

to extract a digit, and:

```java
temp /= 10;
```

to remove that digit.

For every digit we verify:

```java
digit != 0
```

and:

```java
i % digit == 0
```

If all digits satisfy these conditions, the number is added to the result.

### Pattern

```text
Digit Extraction / Digit Traversal
```

### Time Complexity

```text
O(N × D)
```

Effectively:

```text
O(N)
```

for the given constraints.

### Auxiliary Space

```text
O(1)
```

### Output Space

```text
O(K)
```

where `K` is the number of self-dividing numbers returned.
