# Maximum Product of Three Numbers

## Problem

Given an integer array `nums`, find three numbers whose product is maximum and return the maximum product.

### Examples

**Example 1**

```text
Input: nums = [1,2,3]
Output: 6
```

**Example 2**

```text
Input: nums = [1,2,3,4]
Output: 24
```

**Example 3**

```text
Input: nums = [-1,-2,-3]
Output: -6
```

### Constraints

```text
3 <= nums.length <= 10^4
-1000 <= nums[i] <= 1000
```

---

## Solution

### Java Code

```java
class Solution {
    public int maximumProduct(int[] nums) {

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {

            // Find three largest numbers
            if (num >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num >= max2) {
                max3 = max2;
                max2 = num;
            } else if (num >= max3) {
                max3 = num;
            }

            // Find two smallest numbers
            if (num <= min1) {
                min2 = min1;
                min1 = num;
            } else if (num <= min2) {
                min2 = num;
            }
        }

        return Math.max(
            max1 * max2 * max3,
            max1 * min1 * min2
        );
    }
}
```

---

# Approach

The important observation is that the maximum product of any three numbers can only come from **two possible combinations**.

### Case 1: Three largest numbers

If the array contains positive numbers, the maximum product can be:

```text
max1 × max2 × max3
```

For example:

```text
nums = [1, 2, 3, 4]

Three largest:
4, 3, 2

Product:
4 × 3 × 2 = 24
```

---

### Case 2: One largest number + two smallest numbers

Two negative numbers produce a positive number when multiplied.

Therefore, the maximum product can also be:

```text
max1 × min1 × min2
```

For example:

```text
nums = [-10, -10, 1, 2, 3]

Two smallest:
-10, -10

Largest:
3

Product:
3 × (-10) × (-10)
= 300
```

So we calculate both possibilities:

```text
max1 × max2 × max3
```

and

```text
max1 × min1 × min2
```

Then return the larger product.

---

# Pattern Used

## Pattern: Single Pass / One-Pass Traversal

This solution uses a **single-pass array traversal**.

Instead of sorting the complete array, we maintain only:

```text
3 largest numbers
2 smallest numbers
```

During each traversal, these values are updated.

### Why use this pattern?

Sorting would require:

```text
O(n log n)
```

time.

But we only need five important values:

```text
Largest 1
Largest 2
Largest 3

Smallest 1
Smallest 2
```

Therefore, we can find everything in one traversal:

```text
O(n)
```

This makes the solution more efficient.

---

# Algorithm

1. Initialize three variables for the three largest numbers:

   * `max1`
   * `max2`
   * `max3`

2. Initialize two variables for the two smallest numbers:

   * `min1`
   * `min2`

3. Traverse the array once.

4. For every number:

   * Update the three largest values.
   * Update the two smallest values.

5. Calculate the first possible maximum product:

```text
max1 × max2 × max3
```

6. Calculate the second possible maximum product:

```text
max1 × min1 × min2
```

7. Return the maximum of these two products.

---

# Step-by-Step Traversal

Consider:

```text
nums = [-10, -10, 1, 3, 2]
```

We need:

```text
Three largest = 3, 2, 1
Two smallest = -10, -10
```

### Initial State

```text
max1 = MIN_VALUE
max2 = MIN_VALUE
max3 = MIN_VALUE

min1 = MAX_VALUE
min2 = MAX_VALUE
```

---

### Step 1: num = -10

Largest values:

```text
max1 = -10
max2 = MIN_VALUE
max3 = MIN_VALUE
```

Smallest values:

```text
min1 = -10
min2 = MAX_VALUE
```

---

### Step 2: num = -10

Largest values:

```text
max1 = -10
max2 = -10
max3 = MIN_VALUE
```

Smallest values:

```text
min1 = -10
min2 = -10
```

---

### Step 3: num = 1

Largest values:

```text
max1 = 1
max2 = -10
max3 = -10
```

Smallest values remain:

```text
min1 = -10
min2 = -10
```

---

### Step 4: num = 3

Largest values:

```text
max1 = 3
max2 = 1
max3 = -10
```

Smallest values:

```text
min1 = -10
min2 = -10
```

---

### Step 5: num = 2

Largest values:

```text
max1 = 3
max2 = 2
max3 = 1
```

Smallest values:

```text
min1 = -10
min2 = -10
```

---

## Calculate Both Products

### Product 1: Three largest

```text
max1 × max2 × max3

= 3 × 2 × 1
= 6
```

### Product 2: Two smallest + largest

```text
max1 × min1 × min2

= 3 × (-10) × (-10)
= 300
```

Therefore:

```text
max(6, 300) = 300
```

### Output

```text
300
```

---

# Why Do We Need Two Smallest Numbers?

This is the key part of the problem.

When two negative numbers are multiplied:

```text
(-a) × (-b) = positive
```

For example:

```text
(-10) × (-10) = 100
```

If we then multiply by the largest positive number:

```text
100 × 3 = 300
```

Therefore, simply finding the three largest numbers is not enough.

We must also track the **two smallest numbers**.

---

# Example Dry Run

Consider:

```text
nums = [-1, -2, -3]
```

After traversal:

```text
max1 = -1
max2 = -2
max3 = -3

min1 = -3
min2 = -2
```

Calculate:

```text
max1 × max2 × max3

= (-1) × (-2) × (-3)
= -6
```

Second possibility:

```text
max1 × min1 × min2

= (-1) × (-3) × (-2)
= -6
```

Therefore:

```text
max(-6, -6) = -6
```

Output:

```text
-6
```

---

# Why This Solution Works

For any three-number maximum product, there are only two relevant possibilities:

### Possibility 1

Three largest numbers:

```text
max1 × max2 × max3
```

### Possibility 2

Two smallest numbers and the largest number:

```text
min1 × min2 × max1
```

There is no other combination that can produce a larger product.

Therefore, checking these two products is sufficient.

---

# Complexity Analysis

## Time Complexity

The array is traversed exactly once.

```text
O(n)
```

where `n` is the length of the array.

There is no sorting operation.

---

## Space Complexity

Only five variables are used:

```text
max1
max2
max3
min1
min2
```

No additional array or data structure is created.

Therefore:

```text
O(1)
```

### Final Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(1)
```

---

# Comparison with Sorting Approach

A simpler approach would be to sort the array.

After sorting:

```text
nums[0]
nums[1]
nums[n-1]
nums[n-2]
nums[n-3]
```

could be used to calculate the answer.

But sorting requires:

```text
O(n log n)
```

time.

Our one-pass approach requires:

```text
O(n)
```

time.

| Approach |       Time |            Space |
| -------- | ---------: | ---------------: |
| Sorting  | O(n log n) | O(log n) / O(1)* |
| One Pass |       O(n) |             O(1) |

`*` depends on the sorting implementation.

---

# Key Takeaway

The main trick is to recognize that the answer must be one of:

```text
1. Three largest numbers
   max1 × max2 × max3

2. Two smallest numbers + largest number
   min1 × min2 × max1
```

By maintaining these five values during a single traversal, we achieve:

```text
O(n) Time
O(1) Space
```

This is an efficient **one-pass greedy-style solution** without sorting.
