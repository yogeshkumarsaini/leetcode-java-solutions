# Largest Perimeter Triangle

## Problem

Given an integer array `nums`, find the **largest perimeter of a triangle with non-zero area** that can be formed using any three elements from the array.

If no three elements can form a valid triangle, return `0`.

### Triangle Condition

For three sides:

```text
a <= b <= c
```

A valid triangle must satisfy:

```text
a + b > c
```

If:

```text
a + b == c
```

then the triangle has zero area and is not considered valid.

---

## Example 1

### Input

```text
nums = [2,1,2]
```

### Sorted Array

```text
[1,2,2]
```

Check:

```text
1 + 2 > 2
3 > 2
```

Valid triangle.

Perimeter:

```text
1 + 2 + 2 = 5
```

### Output

```text
5
```

---

## Example 2

### Input

```text
nums = [1,2,1,10]
```

### Sorted Array

```text
[1,1,2,10]
```

Start checking from the largest side:

```text
1 + 2 > 10
3 > 10 ❌
```

Next:

```text
1 + 1 > 2
2 > 2 ❌
```

No valid triangle exists.

### Output

```text
0
```

---

# Approach

The main idea is:

1. Sort the array.
2. Start from the largest element.
3. Take three consecutive elements.
4. Check whether they can form a triangle.
5. If valid, return their sum.
6. If not valid, move one position to the left.
7. If no valid combination is found, return `0`.

---

# Why Sorting?

After sorting, the array looks like:

```text
a <= b <= c
```

For these three sides, we only need to check:

```text
a + b > c
```

We don't need to separately check:

```text
a + c > b
b + c > a
```

because `c` is already the largest side.

If the sum of the two smaller sides is greater than the largest side, all other triangle conditions will automatically be satisfied.

---

# Why Greedy Approach?

We need the **largest perimeter**.

The perimeter is:

```text
a + b + c
```

Therefore, we want to use the largest possible values.

After sorting, we start from the right side:

```text
[1, 2, 3, 4, 5]
             ↑
             largest
```

We first try:

```text
3, 4, 5
```

If these form a valid triangle:

```text
3 + 4 > 5
7 > 5
```

then their perimeter is:

```text
3 + 4 + 5 = 12
```

Because we started with the largest possible values, this is the maximum perimeter.

Therefore, we can immediately return the answer.

This is the **Greedy Pattern**.

---

# Algorithm

### Step 1: Sort the Array

```java
Arrays.sort(nums);
```

Example:

```text
[2, 1, 5, 3, 4]
```

becomes:

```text
[1, 2, 3, 4, 5]
```

---

### Step 2: Start From the Largest Element

```java
for (int i = nums.length - 1; i >= 2; i--)
```

Initially:

```text
i = last index
```

We consider:

```text
nums[i - 2], nums[i - 1], nums[i]
```

These are three consecutive elements.

---

### Step 3: Check Triangle Condition

```java
if (nums[i - 2] + nums[i - 1] > nums[i])
```

If true, the three sides form a triangle.

---

### Step 4: Calculate Perimeter

```java
return nums[i - 2] + nums[i - 1] + nums[i];
```

Because we are checking from the largest values toward smaller values, the first valid triangle gives the largest possible perimeter.

---

### Step 5: No Valid Triangle

If the loop finishes without finding a valid triangle:

```java
return 0;
```

---

# Step-by-Step Traversal

Consider:

```text
nums = [3, 6, 2, 3]
```

### After Sorting

```text
[2, 3, 3, 6]
```

Indexes:

```text
 0  1  2  3
[2, 3, 3, 6]
         ↑
         i
```

### Iteration 1

```text
i = 3
```

Take:

```text
nums[i-2] = nums[1] = 3
nums[i-1] = nums[2] = 3
nums[i]   = nums[3] = 6
```

Check:

```text
3 + 3 > 6
6 > 6
```

False.

This is not a valid triangle because the area would be zero.

Move left.

---

### Iteration 2

```text
i = 2
```

Take:

```text
nums[0] = 2
nums[1] = 3
nums[2] = 3
```

Check:

```text
2 + 3 > 3
5 > 3
```

True.

Perimeter:

```text
2 + 3 + 3 = 8
```

Therefore:

```text
Answer = 8
```

---

# Code

```java
class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 2; i--) {

            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }

        return 0;
    }
}
```

---

# Pattern Used

## 1. Sorting

We use **Sorting** to arrange the sides in increasing order.

```java
Arrays.sort(nums);
```

This allows us to easily identify the largest side.

After sorting:

```text
a <= b <= c
```

So only this condition is required:

```text
a + b > c
```

---

## 2. Greedy

We use a **Greedy approach** because we want the largest possible perimeter.

We start from the largest three possible values:

```text
largest
   ↓
[a, b, c]
```

If they form a valid triangle, we immediately return the perimeter.

There is no need to check smaller combinations because they cannot produce a larger perimeter than the current combination.

---

# Why Only Consecutive Elements?

After sorting:

```text
[1, 2, 3, 4, 5]
```

Suppose we are checking `5` as the largest side.

The best possible candidates for the other two sides are the largest elements before it:

```text
3, 4, 5
```

If:

```text
3 + 4 <= 5
```

then using smaller values instead of `3` and `4` will only make the sum smaller:

```text
2 + 4 <= 3 + 4
2 + 3 <= 3 + 4
```

Therefore, if `3,4,5` cannot form a triangle, no combination ending with `5` can form one.

Then we move to the next largest possible side.

---

# Complexity Analysis

## Time Complexity

Sorting takes:

```text
O(n log n)
```

The loop takes:

```text
O(n)
```

Therefore:

```text
O(n log n) + O(n)
```

The dominant term is:

```text
O(n log n)
```

### Final Time Complexity

```text
O(n log n)
```

---

## Space Complexity

The loop itself uses only constant extra variables:

```text
i
```

Therefore, ignoring the internal space used by the sorting implementation:

```text
O(1)
```

For Java's `Arrays.sort(int[])`, the actual implementation details can use additional stack/work space, but the algorithm itself requires only constant extra space.

---

# Dry Run

### Input

```text
nums = [2, 1, 2]
```

### Sort

```text
[1, 2, 2]
```

### Loop

```text
i = 2
```

Three sides:

```text
1, 2, 2
```

Triangle condition:

```text
1 + 2 > 2
3 > 2
```

Valid.

Perimeter:

```text
1 + 2 + 2 = 5
```

Return:

```text
5
```

---

# Another Dry Run

### Input

```text
nums = [1, 2, 1, 10]
```

### Sort

```text
[1, 1, 2, 10]
```

### First Check

```text
1 + 2 > 10
3 > 10 ❌
```

Move left.

### Second Check

```text
1 + 1 > 2
2 > 2 ❌
```

No valid triangle.

Return:

```text
0
```

---

# Key Takeaway

The complete logic can be remembered in one line:

```text
Sort → Start from largest → Check a + b > c → First valid = maximum perimeter
```

### Important Formula

For sorted sides:

```text
a <= b <= c
```

Valid triangle:

```text
a + b > c
```

Perimeter:

```text
a + b + c
```

### Pattern

```text
Sorting + Greedy
```

### Complexity

```text
Time  : O(n log n)
Space : O(1) auxiliary space
```
