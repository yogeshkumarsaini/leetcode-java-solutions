# Pascal's Triangle

## Problem

Given an integer `numRows`, return the first `numRows` of **Pascal's Triangle**.

In Pascal's Triangle:

* The first and last element of every row is `1`.
* Every middle element is the sum of the two elements directly above it.

### Example

```text
Input:
numRows = 5

Output:
[
    [1],
    [1,1],
    [1,2,1],
    [1,3,3,1],
    [1,4,6,4,1]
]
```

---

## Approach

We build Pascal's Triangle **row by row**.

For every row:

1. Add `1` as the first element.
2. If it is not the first row:

   * Take the previous row.
   * Calculate the middle elements using:

```text
previous[j - 1] + previous[j]
```

3. Add `1` as the last element.
4. Add the completed row to `result`.

### Why this approach?

Each row depends only on the **previous row**, so we don't need to calculate values from scratch.

For example:

```text
Previous Row:
[1, 3, 3, 1]

Current Row:
[1, 4, 6, 4, 1]

Calculations:

1 + 3 = 4
3 + 3 = 6
3 + 1 = 4
```

So:

```text
[1, 4, 6, 4, 1]
```

---

## Algorithm

1. Create an empty `result` list.
2. Loop from `i = 0` to `numRows - 1`.
3. Create an empty `row`.
4. Add `1` to the beginning of the row.
5. If `i > 0`:

   * Get the previous row.
   * Loop through its middle elements.
   * Add the sum of two adjacent elements from the previous row.
   * Add `1` at the end.
6. Add the current row to `result`.
7. Return `result`.

---

## Step-by-Step Traversal

Suppose:

```text
numRows = 5
```

### Step 1 — `i = 0`

Create:

```text
row = [1]
```

Add to result:

```text
[
    [1]
]
```

---

### Step 2 — `i = 1`

Previous row:

```text
[1]
```

There are no middle elements.

Add `1` at the beginning and end:

```text
[1, 1]
```

Result:

```text
[
    [1],
    [1,1]
]
```

---

### Step 3 — `i = 2`

Previous row:

```text
[1,1]
```

Calculate middle:

```text
1 + 1 = 2
```

Current row:

```text
[1,2,1]
```

Result:

```text
[
    [1],
    [1,1],
    [1,2,1]
]
```

---

### Step 4 — `i = 3`

Previous row:

```text
[1,2,1]
```

Calculations:

```text
1 + 2 = 3
2 + 1 = 3
```

Current row:

```text
[1,3,3,1]
```

Result:

```text
[
    [1],
    [1,1],
    [1,2,1],
    [1,3,3,1]
]
```

---

### Step 5 — `i = 4`

Previous row:

```text
[1,3,3,1]
```

Calculations:

```text
1 + 3 = 4
3 + 3 = 6
3 + 1 = 4
```

Current row:

```text
[1,4,6,4,1]
```

Final result:

```text
[
    [1],
    [1,1],
    [1,2,1],
    [1,3,3,1],
    [1,4,6,4,1]
]
```

---

## Java Code

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {

            List<Integer> row = new ArrayList<>();

            // First element is always 1
            row.add(1);

            // Build middle elements
            if (i > 0) {

                List<Integer> prev = result.get(i - 1);

                for (int j = 1; j < i; j++) {

                    row.add(prev.get(j - 1) + prev.get(j));
                }

                // Last element is always 1
                row.add(1);
            }

            result.add(row);
        }

        return result;
    }
}
```

---

## Pattern Used

### Dynamic Programming (DP)

This solution follows the **Dynamic Programming** pattern because the current row is constructed using information from the previously calculated row.

```text
Previous Row
     ↓
Calculate middle values
     ↓
Current Row
     ↓
Store in result
```

For example:

```text
[1, 3, 3, 1]
       ↓
1+3   3+3   3+1
 ↓     ↓     ↓
 4     6     4
       ↓
[1, 4, 6, 4, 1]
```

### Why DP?

We already have the previous row available in:

```java
result.get(i - 1)
```

Instead of recalculating previous values, we reuse them to build the current row.

This is essentially **bottom-up row construction using previously computed results**.

---

## Time Complexity

For `numRows = n`:

```text
Row 1 → 0 calculations
Row 2 → 0 calculations
Row 3 → 1 calculation
Row 4 → 2 calculations
...
Row n → n - 2 calculations
```

Total work is:

```text
0 + 0 + 1 + 2 + ... + (n - 2)
```

This gives:

```text
O(n²)
```

### Time Complexity

```text
O(n²)
```

---

## Space Complexity

The output itself contains:

```text
1 + 2 + 3 + ... + n
```

elements.

Therefore, storing the complete Pascal's Triangle requires:

```text
O(n²)
```

### Space Complexity

```text
O(n²)
```

This includes the space required for the returned `result`.

> Auxiliary working space apart from the output is approximately `O(n)` for the current row/reference structure, but because the problem requires returning the complete triangle, the overall space complexity is `O(n²)`.

---

## Key Observation

There are two types of values in every row:

### Boundary values

Always:

```text
1
```

Example:

```text
[1, ?, ?, ?, 1]
```

### Middle values

Calculated using the previous row:

```text
prev[j - 1] + prev[j]
```

Example:

```text
Previous:
[1, 4, 6, 4, 1]

Next:
[1, 5, 10, 10, 5, 1]

Middle calculations:

1 + 4 = 5
4 + 6 = 10
6 + 4 = 10
4 + 1 = 5
```

---

## Important Code Logic

### Get previous row

```java
List<Integer> prev = result.get(i - 1);
```

This gives us the row immediately above the current row.

### Calculate middle elements

```java
row.add(prev.get(j - 1) + prev.get(j));
```

For example:

```text
prev = [1, 3, 3, 1]

j = 1:
prev[0] + prev[1]
1 + 3 = 4

j = 2:
prev[1] + prev[2]
3 + 3 = 6

j = 3:
prev[2] + prev[3]
3 + 1 = 4
```

### Add last `1`

```java
row.add(1);
```

Because every Pascal's Triangle row ends with `1`.

---

## Complexity Summary

| Complexity | Value                                        |
| ---------- | -------------------------------------------- |
| Time       | `O(n²)`                                      |
| Space      | `O(n²)`                                      |
| Pattern    | Dynamic Programming / Bottom-Up Construction |

---

## Pattern Recognition

When you see a problem where:

* Current result depends on a previously calculated result.
* We can reuse previously calculated values.
* We build the answer step-by-step.
* There is overlapping/reusable computation.

Think about:

```text
Dynamic Programming
```

For Pascal's Triangle:

```text
Previous Row
     ↓
Reuse previous values
     ↓
Calculate current row
     ↓
Store current row
```

---

## Final Takeaway

The key idea is very simple:

> **First and last elements are always `1`; every middle element is the sum of two adjacent elements from the previous row.**

Your implementation directly follows this rule and produces the complete Pascal's Triangle efficiently.

### Pattern

```text
Dynamic Programming
        +
Array / List Traversal
        +
Bottom-Up Construction
```
