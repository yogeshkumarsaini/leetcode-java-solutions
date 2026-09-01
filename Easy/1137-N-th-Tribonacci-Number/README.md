# 🔢 N-th Tribonacci Number

## Problem Statement

The **Tribonacci sequence** is defined as:

```text
T0 = 0
T1 = 1
T2 = 1
```

For every `n >= 0`:

```text
Tn+3 = Tn + Tn+1 + Tn+2
```

Given an integer `n`, return the value of `Tn`.

### Example 1

```text
Input: n = 4
Output: 4
```

Explanation:

```text
T3 = T0 + T1 + T2
   = 0 + 1 + 1
   = 2

T4 = T1 + T2 + T3
   = 1 + 1 + 2
   = 4
```

### Example 2

```text
Input: n = 25
Output: 1389537
```

### Constraints

```text
0 <= n <= 37
```

The answer fits within a 32-bit signed integer.

---

# 💡 Approach

We can solve this problem using **Dynamic Programming with Space Optimization**.

The Tribonacci sequence depends only on the **previous three values**:

```text
Tn = Tn-1 + Tn-2 + Tn-3
```

Therefore, there is no need to store the complete sequence.

We maintain only three variables:

```text
a = Tn-3
b = Tn-2
c = Tn-1
```

Then calculate:

```text
d = a + b + c
```

After calculating the next value, shift the variables:

```text
a = b
b = c
c = d
```

At the end, `c` contains `Tn`.

---

# 🧠 Pattern Used

## Pattern: Dynamic Programming + Rolling Variables

This solution follows the **Dynamic Programming (DP)** pattern because each Tribonacci value is calculated using previously calculated values.

```text
Tn = Tn-1 + Tn-2 + Tn-3
```

### Why Dynamic Programming?

There is a dependency on previously calculated states.

For example:

```text
T3 depends on T0, T1, T2

T4 depends on T1, T2, T3

T5 depends on T2, T3, T4
```

Instead of recalculating these values again and again, we calculate them once and keep the required previous values.

### Why Rolling Variables?

Normally, DP could use an array:

```java
int[] dp = new int[n + 1];
```

But every value only needs the previous **three** values.

So storing the entire array is unnecessary.

Instead:

```java
int a = 0;
int b = 1;
int c = 1;
```

We keep only the last three values.

This reduces Space Complexity from:

```text
O(n)
```

to:

```text
O(1)
```

---

# ⚙️ Algorithm

1. If `n == 0`, return `0`.
2. If `n == 1` or `n == 2`, return `1`.
3. Initialize the first three Tribonacci values:

   ```text
   a = 0
   b = 1
   c = 1
   ```
4. Run a loop from `3` to `n`.
5. Calculate the next Tribonacci value:

   ```text
   d = a + b + c
   ```
6. Shift the variables:

   ```text
   a = b
   b = c
   c = d
   ```
7. After the loop, return `c`.

---

# 🔄 Step-by-Step Traversal

Consider:

```text
n = 4
```

Initial values:

```text
a = T0 = 0
b = T1 = 1
c = T2 = 1
```

### Iteration 1: i = 3

Calculate:

```text
d = a + b + c
  = 0 + 1 + 1
  = 2
```

Shift:

```text
a = b = 1
b = c = 1
c = d = 2
```

Current values:

```text
a = 1
b = 1
c = 2
```

So:

```text
T3 = 2
```

---

### Iteration 2: i = 4

Calculate:

```text
d = a + b + c
  = 1 + 1 + 2
  = 4
```

Shift:

```text
a = b = 1
b = c = 2
c = d = 4
```

Current values:

```text
a = 1
b = 2
c = 4
```

So:

```text
T4 = 4
```

Finally:

```text
return c;
```

Output:

```text
4
```

---

# 📊 Dry Run

For:

```text
n = 7
```

The Tribonacci sequence is:

```text
T0 = 0
T1 = 1
T2 = 1
T3 = 2
T4 = 4
T5 = 7
T6 = 13
T7 = 24
```

Variable traversal:

| Iteration | `a` | `b` | `c` | `d = a+b+c` |
| --------: | --: | --: | --: | ----------: |
|   Initial |   0 |   1 |   1 |           - |
|     i = 3 |   0 |   1 |   1 |           2 |
|     i = 4 |   1 |   1 |   2 |           4 |
|     i = 5 |   1 |   2 |   4 |           7 |
|     i = 6 |   2 |   4 |   7 |          13 |
|     i = 7 |   4 |   7 |  13 |          24 |

Therefore:

```text
T7 = 24
```

---

# 💻 Java Solution

```java
class Solution {
    public int tribonacci(int n) {
        if (n == 0)
            return 0;

        if (n == 1 || n == 2)
            return 1;

        int a = 0;
        int b = 1;
        int c = 1;

        for (int i = 3; i <= n; i++) {
            int d = a + b + c;

            a = b;
            b = c;
            c = d;
        }

        return c;
    }
}
```

---

# 🔍 Code Explanation

### Base Cases

```java
if (n == 0)
    return 0;
```

Because:

```text
T0 = 0
```

And:

```java
if (n == 1 || n == 2)
    return 1;
```

Because:

```text
T1 = 1
T2 = 1
```

---

### Initialize Previous Three Values

```java
int a = 0;
int b = 1;
int c = 1;
```

These represent:

```text
a → T0
b → T1
c → T2
```

---

### Calculate Next Value

```java
int d = a + b + c;
```

This follows the Tribonacci formula:

```text
Tn = Tn-1 + Tn-2 + Tn-3
```

---

### Shift Values

```java
a = b;
b = c;
c = d;
```

After shifting:

```text
a → previous Tn-2
b → previous Tn-1
c → current Tn
```

This allows us to continue calculating the sequence without using an array.

---

# ⏱️ Complexity Analysis

## Time Complexity

```text
O(n)
```

The loop runs from `3` to `n`.

Therefore, approximately `n` iterations are performed.

Each iteration performs constant-time operations:

```text
Addition → O(1)
Assignment → O(1)
```

So overall:

```text
Time = O(n)
```

---

## Space Complexity

```text
O(1)
```

Only four integer variables are used:

```text
a
b
c
d
```

We don't create an array or any data structure dependent on `n`.

Therefore:

```text
Space = O(1)
```

---

# 🚀 Why This Approach Is Optimized

A simple DP solution could use an array:

```java
int[] dp = new int[n + 1];
```

That would require:

```text
Time  = O(n)
Space = O(n)
```

Our solution keeps only the last three values:

```text
a
b
c
```

Therefore:

```text
Time  = O(n)
Space = O(1)
```

The time complexity cannot be reduced to less than `O(n)` with this straightforward iterative approach because we need to progress through the sequence up to `n`.

---

# 🧩 Pattern Summary

| Concept              | Used |
| -------------------- | ---- |
| Dynamic Programming  | ✅    |
| Bottom-Up DP         | ✅    |
| Space Optimization   | ✅    |
| Rolling Variables    | ✅    |
| Recursion            | ❌    |
| Array                | ❌    |
| Extra Data Structure | ❌    |

### Final Pattern

```text
Bottom-Up Dynamic Programming
        +
Rolling Variables
        +
Space Optimization
```

---

# 📌 Key Takeaway

The important optimization is recognizing that **only the previous three Tribonacci values are required**.

Instead of:

```text
dp[0], dp[1], dp[2], ..., dp[n]
```

we maintain:

```text
a, b, c
```

and continuously update them.

This gives us an efficient:

```text
O(n) Time
O(1) Space
```

solution.

---

# 🔗 Related Concept

This problem is similar to the Fibonacci sequence, but instead of depending on the previous **two** values, Tribonacci depends on the previous **three** values.

### Fibonacci

```text
F(n) = F(n-1) + F(n-2)
```

### Tribonacci

```text
T(n) = T(n-1) + T(n-2) + T(n-3)
```

The same **rolling-variable / space-optimized DP** technique can be applied to both.
