# Pascal's Triangle II

## Problem

Given an integer `rowIndex`, return the `rowIndex`th **0-indexed** row of Pascal's Triangle.

In Pascal's Triangle, each number is the sum of the two numbers directly above it.

### Example

```text
Input: rowIndex = 3

Output: [1,3,3,1]
```

Another example:

```text
Input: rowIndex = 0

Output: [1]
```

---

## Pascal's Triangle

The first few rows of Pascal's Triangle are:

```text
              1
            1   1
          1   2   1
        1   3   3   1
      1   4   6   4   1
```

For `rowIndex = 3`, the required row is:

```text
[1, 3, 3, 1]
```

---

## Approach

Instead of generating the complete Pascal's Triangle, we calculate only the required row.

Every element in a Pascal row is a **binomial coefficient**.

The first value of every row is:

```text
1
```

The next values can be calculated from the previous value using:

```text
next = current × (rowIndex - i + 1) / i
```

This allows us to calculate the complete row without storing previous rows.

For example, for:

```text
rowIndex = 3
```

We get:

```text
1
1 × 3 / 1 = 3
3 × 2 / 2 = 3
3 × 1 / 3 = 1
```

Therefore:

```text
[1, 3, 3, 1]
```

---

## Algorithm

1. Create an empty result list.
2. Initialize `val = 1`.
3. Run a loop from `i = 1` to `rowIndex`.
4. Add the current `val` to the result.
5. Calculate the next value using:

```text
val = val × (rowIndex - i + 1) / i
```

6. After the loop, add the final `1`.
7. Return the result.

---

## Step-by-Step Traversal

Suppose:

```text
rowIndex = 3
```

### Initial State

```text
res = []
val = 1
```

### Iteration 1

```text
i = 1

Add val:

res = [1]

Calculate next value:

val = 1 × (3 - 1 + 1) / 1
    = 3
```

### Iteration 2

```text
i = 2

Add val:

res = [1, 3]

Calculate next value:

val = 3 × (3 - 2 + 1) / 2
    = 3
```

### Iteration 3

```text
i = 3

Add val:

res = [1, 3, 3]

Calculate next value:

val = 3 × (3 - 3 + 1) / 3
    = 1
```

### After Loop

Add the final `1`:

```text
res = [1, 3, 3, 1]
```

Return:

```text
[1, 3, 3, 1]
```

---

## Java Solution

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();

        long val = 1;

        for (int i = 1; i <= rowIndex; i++) {
            res.add((int) val);

            val = val * (rowIndex - i + 1) / i;
        }

        res.add(1);

        return res;
    }
}
```

---

## Why `long` is Used?

We use:

```java
long val = 1;
```

instead of:

```java
int val = 1;
```

because multiplication happens while calculating the next value:

```java
val * (rowIndex - i + 1)
```

Using `long` provides a larger range for intermediate calculations and makes the calculation safer.

The constraints are:

```text
0 <= rowIndex <= 33
```

---

## Pattern Used

### Mathematical Pattern / Binomial Coefficient

This solution uses the **Binomial Coefficient** pattern.

The relationship is:

```text
C(n, k) = C(n, k-1) × (n-k+1) / k
```

We use the previous element to calculate the next element.

For example:

```text
C(3,0) = 1

C(3,1) = 1 × 3 / 1 = 3

C(3,2) = 3 × 2 / 2 = 3

C(3,3) = 3 × 1 / 3 = 1
```

So:

```text
[1, 3, 3, 1]
```

---

## Why This Pattern?

A normal Pascal Triangle solution would generate previous rows and use:

```text
current[j] = previous[j-1] + previous[j]
```

That requires storing previous rows.

But this problem asks for only **one specific row**.

Therefore, generating the complete triangle is unnecessary.

Using the binomial coefficient relationship allows us to:

* Calculate only the required row.
* Avoid storing previous rows.
* Use constant extra working space.
* Keep the implementation simple.

---

## Complexity Analysis

### Time Complexity

```text
O(rowIndex)
```

We iterate from `1` to `rowIndex`.

There are `rowIndex + 1` elements in the answer.

Therefore:

```text
Time = O(rowIndex)
```

### Space Complexity

The returned result contains:

```text
rowIndex + 1
```

elements.

Therefore, output space is:

```text
O(rowIndex)
```

However, the algorithm itself uses only a few variables:

```text
val
i
```

So the **extra working space excluding the output** is:

```text
O(1)
```

### Final Complexity

```text
Time:  O(rowIndex)

Space: O(rowIndex)  -> including output
       O(1)         -> excluding output
```

---

## Key Takeaway

The important idea is:

> We don't need to build the entire Pascal's Triangle when only one row is required.

We can calculate every element from the previous element using the binomial coefficient formula:

```text
next = current × (rowIndex - i + 1) / i
```

This gives:

```text
Time Complexity  : O(rowIndex)
Extra Space      : O(1)
Output Space     : O(rowIndex)
```

---

## Pattern Summary

| Concept              | Used        |
| -------------------- | ----------- |
| Array / List         | Yes         |
| Mathematical Formula | Yes         |
| Binomial Coefficient | Yes         |
| Dynamic Programming  | No          |
| Recursion            | No          |
| Two Pointers         | No          |
| Time Complexity      | O(rowIndex) |
| Extra Space          | O(1)        |
| Output Space         | O(rowIndex) |
