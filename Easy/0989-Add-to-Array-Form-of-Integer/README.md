# Add to Array-Form of Integer

## Problem

The array-form of an integer `num` is an array representing its digits from left to right.

For example:

```text
num = 1321
array-form = [1, 3, 2, 1]
```

Given an integer array `num` and an integer `k`, return the array-form of:

```text
num + k
```

### Example

```text
Input:
num = [1,2,0,0]
k = 34

Output:
[1,2,3,4]

Explanation:
1200 + 34 = 1234
```

---

## Approach

We cannot directly convert the entire `num` array into an integer because `num` can contain up to `10^4` digits.

For example:

```text
num.length = 10000
```

Such a number cannot be stored inside Java's `int` or `long`.

Therefore, we perform addition **digit by digit**, just like normal mathematical addition.

We start from the **rightmost digit** and move towards the left.

The important operations are:

```java
k % 10
```

This gives us the current digit.

And:

```java
k /= 10;
```

This keeps the carry/remaining value for the next iteration.

---

## Pattern Used

### Right-to-Left Traversal + Carry Propagation

This problem follows the **Digit-by-Digit Arithmetic** pattern.

The same pattern is used in normal addition:

```text
    2 7 4
+     1 8 1
-----------
    4 5 5
```

We start from the right:

```text
4 + 1 = 5
7 + 8 = 15
2 + 1 = 3
```

The code combines the carry handling with `k`.

```text
k % 10  → current digit
k / 10  → carry / remaining value
```

### Why use this pattern?

Because:

1. `num` can contain up to `10^4` digits.
2. The complete number cannot fit inside `int` or `long`.
3. We only need to process individual digits.
4. Each digit needs to be visited only once.
5. The approach is simple and efficient.

---

## Algorithm

1. Create an empty `result` list.
2. Set `i` to the last index of `num`.
3. Continue while either:

   * `i >= 0`, or
   * `k > 0`.
4. If `i >= 0`, add `num[i]` to `k`.
5. Extract the last digit using:

   ```java
   k % 10
   ```
6. Add that digit to `result`.
7. Remove the processed digit/carry using:

   ```java
   k /= 10
   ```
8. Move to the previous digit using:

   ```java
   i--
   ```
9. Since digits were added from right to left, reverse the result.
10. Return the result.

---

## Step-by-Step Traversal

Consider:

```text
num = [2,7,4]
k = 181
```

We need:

```text
274 + 181 = 455
```

### Step 1

Start from the rightmost digit:

```text
num[i] = 4
k = 181
```

Add them:

```text
181 + 4 = 185
```

Extract last digit:

```text
185 % 10 = 5
```

Add `5` to result:

```text
result = [5]
```

Now remove the processed digit:

```text
185 / 10 = 18
```

So:

```text
k = 18
```

---

### Step 2

Move to:

```text
num[i] = 7
k = 18
```

Add:

```text
18 + 7 = 25
```

Extract digit:

```text
25 % 10 = 5
```

Result:

```text
[5,5]
```

Carry/remaining value:

```text
25 / 10 = 2
```

So:

```text
k = 2
```

---

### Step 3

Move to:

```text
num[i] = 2
k = 2
```

Add:

```text
2 + 2 = 4
```

Extract digit:

```text
4 % 10 = 4
```

Result:

```text
[5,5,4]
```

Remaining value:

```text
4 / 10 = 0
```

Now both conditions become false:

```text
i < 0
k == 0
```

Loop ends.

---

## Why Do We Reverse the Result?

We process the number from right to left.

Therefore, digits are inserted into the result in reverse order.

During traversal:

```text
5 → 5 → 4
```

So we get:

```text
[5,5,4]
```

But the required array-form is:

```text
[4,5,5]
```

Therefore:

```java
Collections.reverse(result);
```

Finally:

```text
[4,5,5]
```

---

## Java Solution

```java
class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {

        List<Integer> result = new ArrayList<>();

        int i = num.length - 1;

        while (i >= 0 || k > 0) {

            if (i >= 0) {
                k += num[i];
                i--;
            }

            result.add(k % 10);

            k /= 10;
        }

        Collections.reverse(result);

        return result;
    }
}
```

---

## Understanding the Important Lines

### 1. Start from the last digit

```java
int i = num.length - 1;
```

If:

```text
num = [2,7,4]
```

Then:

```text
i = 2
```

So we start from:

```text
4
```

---

### 2. Continue while digits or carry remain

```java
while (i >= 0 || k > 0)
```

This is important because after all digits of `num` are processed, `k` may still contain a carry.

For example:

```text
num = [9,9,9]
k = 1
```

Result:

```text
1000
```

The extra `1` is handled because:

```java
k > 0
```

---

### 3. Add current digit

```java
k += num[i];
```

This combines the current digit with `k`.

---

### 4. Get current result digit

```java
result.add(k % 10);
```

For example:

```text
k = 25
```

Then:

```text
25 % 10 = 5
```

So `5` is the current digit.

---

### 5. Keep carry

```java
k /= 10;
```

For:

```text
25
```

we get:

```text
25 / 10 = 2
```

So `2` becomes the value used in the next iteration.

---

### 6. Reverse the answer

```java
Collections.reverse(result);
```

Because we processed from:

```text
Right → Left
```

but output requires:

```text
Left → Right
```

---

## Dry Run

### Example 1

```text
num = [1,2,0,0]
k = 34
```

| Step | Current Digit | `k` After Addition | Result Digit | Remaining `k` |
| ---- | ------------- | ------------------ | ------------ | ------------- |
| 1    | 0             | 34                 | 4            | 3             |
| 2    | 0             | 3                  | 3            | 0             |
| 3    | 2             | 2                  | 2            | 0             |
| 4    | 1             | 1                  | 1            | 0             |

Result before reverse:

```text
[4,3,2,1]
```

After reverse:

```text
[1,2,3,4]
```

---

### Example 2

```text
num = [2,7,4]
k = 181
```

Traversal:

```text
4 + 181 = 185 → digit 5 → k = 18

7 + 18 = 25   → digit 5 → k = 2

2 + 2 = 4     → digit 4 → k = 0
```

Result:

```text
[5,5,4]
```

Reverse:

```text
[4,5,5]
```

---

### Example 3

```text
num = [2,1,5]
k = 806
```

Traversal:

```text
5 + 806 = 811 → digit 1 → k = 81

1 + 81 = 82  → digit 2 → k = 8

2 + 8 = 10   → digit 0 → k = 1

1 remains    → digit 1 → k = 0
```

Result before reverse:

```text
[1,2,0,1]
```

After reverse:

```text
[1,0,2,1]
```

Therefore:

```text
215 + 806 = 1021
```

---

## Complexity Analysis

Let:

```text
n = num.length
```

### Time Complexity

We traverse each digit of `num` once:

```text
O(n)
```

Then we reverse the result:

```text
O(n)
```

Therefore the overall time complexity remains:

```text
O(n)
```

### Space Complexity

The result list can contain up to `n + 1` digits.

Therefore:

```text
O(n)
```

If the output list is excluded from auxiliary space calculation, the extra working space is approximately:

```text
O(1)
```

---

## Key Takeaways

The main concepts used in this problem are:

```text
1. Right-to-left traversal
2. Digit-by-digit addition
3. Carry propagation
4. Modulo (%) to extract digit
5. Integer division (/) to keep carry
6. Reverse result
```

The most important trick is:

```java
k % 10
```

gets the current digit, while:

```java
k /= 10
```

keeps the carry/remaining value.

This allows us to add a potentially very large number without converting the entire array into an integer.

---

## Pattern Summary

```text
Array of Digits
      ↓
Start from Right
      ↓
Add Current Digit to k
      ↓
k % 10
      ↓
Store Current Digit
      ↓
k / 10
      ↓
Carry / Remaining Value
      ↓
Move Left
      ↓
Reverse Result
```

### Pattern

**Digit-by-Digit Arithmetic + Right-to-Left Traversal + Carry Propagation**

### Complexity

```text
Time  : O(n)
Space : O(n)
```
