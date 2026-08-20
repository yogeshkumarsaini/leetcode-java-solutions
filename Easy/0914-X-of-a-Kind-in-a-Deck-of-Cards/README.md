# X of a Kind in a Deck

## Problem

You are given an integer array `deck`, where `deck[i]` represents the number written on the `i`th card.

We need to partition all cards into **one or more groups** such that:

* Every group contains exactly `X` cards.
* `X > 1`
* All cards inside a group have the same value.

Return `true` if such a partition is possible, otherwise return `false`.

### Example 1

```text
Input:
deck = [1,2,3,4,4,3,2,1]

Output:
true
```

Explanation:

```text
[1,1]
[2,2]
[3,3]
[4,4]
```

Every group has exactly `2` cards.

Therefore, `X = 2` is possible.

---

### Example 2

```text
Input:
deck = [1,1,1,2,2,2,3,3]

Output:
false
```

Frequency of each card:

```text
1 -> 3
2 -> 3
3 -> 2
```

There is no common `X > 1` that can divide all frequencies.

Therefore, the answer is `false`.

---

# Approach

The main idea is:

> Count how many times each card appears, then find the GCD of all frequencies.

Suppose the frequencies are:

```text
[4, 8, 12]
```

The GCD is:

```text
gcd(4, 8, 12) = 4
```

Since `4 >= 2`, we can create groups of exactly 4 cards:

```text
Frequency 4  -> 1 group of 4
Frequency 8  -> 2 groups of 4
Frequency 12 -> 3 groups of 4
```

So the answer is `true`.

---

# Why GCD Works?

Suppose a card appears `count` times.

For that card to be divided into groups of size `X`, we need:

```text
count % X == 0
```

This condition must be true for **every card frequency**.

Therefore, `X` must be a common divisor of all frequencies.

The largest possible common divisor is the **GCD**.

So:

```text
If GCD of all frequencies >= 2
        -> possible
Else
        -> impossible
```

### Example

Consider:

```text
deck = [1,1,1,1,2,2,2,2,2,2]
```

Frequencies:

```text
1 -> 4
2 -> 6
```

GCD:

```text
gcd(4, 6) = 2
```

Therefore:

```text
X = 2
```

is possible.

Groups can be:

```text
[1,1]
[1,1]

[2,2]
[2,2]
[2,2]
```

So the answer is:

```text
true
```

---

# Algorithm

### Step 1: Create a frequency array

Since:

```text
0 <= deck[i] < 10000
```

we can create:

```java
int[] freq = new int[10001];
```

---

### Step 2: Count frequency of every card

For every card:

```java
for(int card : deck){
    freq[card]++;
}
```

Example:

```text
deck = [1,1,1,2,2,3,3,3]

freq:

1 -> 3
2 -> 2
3 -> 3
```

---

### Step 3: Calculate GCD

Start with:

```java
int gcd = 0;
```

Then process every non-zero frequency:

```java
gcd = gcd(gcd, count);
```

For example:

```text
gcd(0, 3) = 3
gcd(3, 2) = 1
```

As soon as GCD becomes `1`, we know that no group size greater than `1` can divide all frequencies.

Therefore, we can immediately return:

```java
false
```

---

### Step 4: Check final GCD

After processing all frequencies:

```java
return gcd >= 2;
```

If:

```text
gcd >= 2
```

then at least one valid group size exists.

Otherwise:

```text
gcd = 1
```

and partitioning is impossible.

---

# Step-by-Step Traversal

Consider:

```text
deck = [1,1,1,1,2,2,2,2,2,2]
```

### Frequency Counting

```text
Card 1 -> 4
Card 2 -> 6
```

So:

```text
freq = [4, 6]
```

### GCD Traversal

Initially:

```text
gcd = 0
```

#### First frequency = 4

```text
gcd(0, 4)
```

Result:

```text
gcd = 4
```

#### Second frequency = 6

```text
gcd(4, 6)
```

Using Euclidean Algorithm:

```text
6 % 4 = 2
4 % 2 = 0
```

Therefore:

```text
gcd = 2
```

Finally:

```text
gcd >= 2
```

So:

```text
return true;
```

---

# GCD Algorithm

The GCD function uses the **Euclidean Algorithm**.

```java
private int gcd(int a, int b) {
    while (b != 0) {
        int temp = a % b;
        a = b;
        b = temp;
    }

    return a;
}
```

The main idea is:

```text
gcd(a, b) = gcd(b, a % b)
```

For example:

```text
gcd(12, 8)

12 % 8 = 4
8 % 4 = 0

GCD = 4
```

---

# Complete Java Solution

```java
class Solution {

    public boolean hasGroupsSizeX(int[] deck) {

        // Store frequency of every card
        int[] freq = new int[10001];

        for (int card : deck) {
            freq[card]++;
        }

        // Calculate GCD of all frequencies
        int gcd = 0;

        for (int count : freq) {

            if (count > 0) {

                gcd = gcd(gcd, count);

                // If GCD becomes 1,
                // no valid group size X > 1 exists
                if (gcd == 1) {
                    return false;
                }
            }
        }

        // GCD must be at least 2
        return gcd >= 2;
    }

    private int gcd(int a, int b) {

        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
```

---

# Pattern Used

## 1. Frequency Counting

The first pattern used is:

> **Frequency Counting**

We need to know how many times every card value occurs.

Instead of repeatedly searching the array, we store the count:

```text
Card -> Frequency

1 -> 4
2 -> 6
3 -> 8
```

This makes it easy to work with the number of occurrences.

---

## 2. GCD Pattern

The second important pattern is:

> **GCD / Common Divisor**

We need one group size `X` that divides **every frequency**.

For example:

```text
Frequencies = [6, 12, 18]
```

Possible group sizes include:

```text
2
3
6
```

The GCD gives the largest common divisor:

```text
GCD(6,12,18) = 6
```

Since `6 > 1`, partitioning is possible.

---

# Why Use GCD?

Without GCD, we could try every possible group size:

```text
X = 2
X = 3
X = 4
...
```

But this is unnecessary.

The important observation is:

> A valid `X` must divide every frequency.

The GCD represents all common divisors of the frequencies.

Therefore, checking:

```java
gcd >= 2
```

is enough.

---

# Early Exit Optimization

This part of the code is an optimization:

```java
if (gcd == 1) {
    return false;
}
```

Suppose frequencies are:

```text
4, 6, 9
```

First:

```text
gcd(4,6) = 2
```

Then:

```text
gcd(2,9) = 1
```

Once GCD becomes `1`, it can never become greater than `1` by adding more numbers.

Therefore, we immediately return:

```text
false
```

This avoids unnecessary processing.

---

# Complexity Analysis

Let:

```text
N = deck.length
```

and let `K` be the range of possible card values.

In this problem:

```text
K = 10001
```

## Time Complexity

### Frequency Counting

We traverse the deck once:

```text
O(N)
```

### Frequency Traversal

We traverse the frequency array:

```text
O(K)
```

### GCD Operations

For each non-zero frequency, GCD takes approximately:

```text
O(log N)
```

Therefore, overall:

```text
O(N + K + K log N)
```

Since `K = 10001` is fixed by the constraints, this is effectively:

```text
O(N)
```

for practical purposes.

---

## Space Complexity

We use:

```java
int[] freq = new int[10001];
```

Therefore:

```text
O(K)
```

Since `K = 10001` is fixed:

```text
O(1)
```

with respect to the input size `N`.

---

# Complexity Summary

| Operation             |                 Complexity |
| --------------------- | -------------------------: |
| Frequency Counting    |                     `O(N)` |
| Frequency Traversal   |                     `O(K)` |
| GCD Calculation       | `O(K log N)` approximately |
| Overall Time          |           `O(N + K log N)` |
| Space                 |                     `O(K)` |
| With fixed card range |  `O(N)` Time, `O(1)` Space |

---

# Key Insight

The most important thing to remember for this problem is:

```text
Count frequency of every number
            ↓
Find GCD of all frequencies
            ↓
GCD >= 2 ?
       ↙       ↘
     YES        NO
      ↓          ↓
    true       false
```

### One-Line Trick

> **If the GCD of all card frequencies is at least 2, the deck can be partitioned into equal-sized groups.**

---

# Interview Explanation

If asked to explain the solution in an interview:

> First, I count how many times each card value appears. For a valid grouping, the group size `X` must divide the frequency of every card value. Therefore, `X` must be a common divisor of all frequencies. Instead of checking every possible `X`, I calculate the GCD of all frequencies. If the GCD is at least 2, that GCD itself can be used as the group size, so I return `true`. If the GCD becomes 1, no valid group size greater than 1 exists, so I return `false`.

---
