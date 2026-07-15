# 168. Excel Sheet Column Title

## Problem Statement

Given an integer `columnNumber`, return its corresponding Excel sheet column title.

### Examples

**Example 1**
```
Input: columnNumber = 1
Output: "A"
```

**Example 2**
```
Input: columnNumber = 28
Output: "AB"
```

**Example 3**
```
Input: columnNumber = 701
Output: "ZY"
```

---

# Approach

Excel columns follow a **1-based numbering system**, unlike the normal **0-based alphabet indexing**.

```
A -> 1
B -> 2
...
Z -> 26
AA -> 27
```

Since there is **no zero character**, we first decrement the number by **1** during every iteration.

```
columnNumber--;
```

Now the values become:

```
0 -> A
1 -> B
...
25 -> Z
```

which perfectly matches the alphabet indices.

For every iteration:

1. Decrease `columnNumber` by 1.
2. Find the current character using `% 26`.
3. Append the character.
4. Divide the number by `26`.
5. Since characters are generated from right to left, reverse the string at the end.

---

# Algorithm

1. Create an empty `StringBuilder`.
2. Repeat while `columnNumber > 0`.
3. Decrease `columnNumber` by 1.
4. Calculate the current character:
   ```
   char ch = (char)('A' + columnNumber % 26);
   ```
5. Append the character.
6. Update:
   ```
   columnNumber /= 26;
   ```
7. Reverse the `StringBuilder`.
8. Return the final string.

---

# Step-by-Step Traversal

## Example

```
columnNumber = 701
```

|Iteration|columnNumber(after --)|columnNumber % 26|Character|Result|
|---------|---------------------:|----------------:|---------|------|
|1|700|24|Y|Y|
|2|26|25|Z|YZ|
|Stop|0|-|-|Reverse → ZY|

**Answer**

```
ZY
```

---

## Dry Run

### Input

```
28
```

### Iteration 1

```
columnNumber = 28

columnNumber--
=27

27 % 26 = 1

Character = B

Result = "B"

columnNumber = 27 / 26 = 1
```

---

### Iteration 2

```
columnNumber = 1

columnNumber--
=0

0 % 26 = 0

Character = A

Result = "BA"

columnNumber = 0
```

Reverse

```
BA → AB
```

Final Answer

```
AB
```

---

# Why do we decrement (`columnNumber--`)?

Normal Base-26:

```
0 -> A
1 -> B
...
25 -> Z
```

Excel Mapping:

```
1 -> A
2 -> B
...
26 -> Z
```

Subtracting one converts Excel's **1-based indexing** into **0-based indexing**, allowing `% 26` to work correctly.

Without decrement:

```
26 % 26 = 0
```

which would incorrectly produce

```
A
```

instead of

```
Z
```

---

# Pattern Used

## Modified Base-26 Conversion

This problem is a variation of the **Base Conversion** pattern.

Unlike normal base conversion:

- Base-10 uses digits **0-9**
- Base-2 uses digits **0-1**
- Excel uses **A-Z (1-26)**

Because Excel has **no zero digit**, we subtract **1** before each calculation.

This is why it is called a **Modified Base-26** conversion.

---

# Correctness

At every iteration:

- `% 26` extracts the current alphabet.
- `/ 26` removes the processed digit.
- Characters are generated from least significant to most significant.
- Reversing restores the correct order.

Hence the algorithm always produces the correct Excel column title.

---

# Complexity Analysis

### Time Complexity

Each iteration divides the number by `26`.

```
Time Complexity = O(log26(n))
```

or simply

```
O(log n)
```

---

### Space Complexity

The `StringBuilder` stores the output characters.

If the answer contains `k` letters:

```
Space Complexity = O(k)
```

where

```
k = log26(n)
```

---

# Java Solution

```java
class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--;

            char ch = (char) ('A' + columnNumber % 26);
            result.append(ch);

            columnNumber /= 26;
        }

        return result.reverse().toString();
    }
}
```

---

# Key Takeaways

- Uses **Modified Base-26 Conversion**.
- Excel columns are **1-indexed**, so subtract **1** before processing.
- Characters are generated from **right to left**.
- Reverse the result to obtain the final column title.
- Efficient solution with **O(log n)** time complexity.