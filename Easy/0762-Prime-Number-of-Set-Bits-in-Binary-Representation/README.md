# Count Numbers With Prime Number of Set Bits

## Problem

Given two integers `left` and `right`, return the count of numbers in the inclusive range `[left, right]` having a **prime number of set bits** in their binary representation.

A **set bit** means a bit whose value is `1`.

### Example

```text
21 → 10101
```

`21` contains `3` set bits, and `3` is a prime number.

Therefore, `21` satisfies the condition.

---

## Examples

### Example 1

```text
Input:
left = 6
right = 10

Output:
4
```

Explanation:

```text
6  → 110   → 2 set bits → Prime ✓
7  → 111   → 3 set bits → Prime ✓
8  → 1000  → 1 set bit  → Not Prime ✗
9  → 1001  → 2 set bits → Prime ✓
10 → 1010  → 2 set bits → Prime ✓
```

Total:

```text
4
```

### Example 2

```text
Input:
left = 10
right = 15

Output:
5
```

Explanation:

```text
10 → 1010 → 2 set bits → Prime ✓
11 → 1011 → 3 set bits → Prime ✓
12 → 1100 → 2 set bits → Prime ✓
13 → 1101 → 3 set bits → Prime ✓
14 → 1110 → 3 set bits → Prime ✓
15 → 1111 → 4 set bits → Not Prime ✗
```

Total:

```text
5
```

---

## Approach

The key observation is that the maximum value is:

```text
right <= 10^6
```

A number up to `10^6` requires at most **20 bits** in binary.

Therefore, the number of set bits can only be between:

```text
1 and 20
```

The prime numbers in this range are:

```text
2, 3, 5, 7, 11, 13, 17, 19
```

So instead of checking whether the number of set bits is prime every time, we can create a boolean lookup array.

```java
boolean[] prime = new boolean[21];

prime[2] = prime[3] = prime[5] = prime[7] =
prime[11] = prime[13] = prime[17] = prime[19] = true;
```

Then for every number from `left` to `right`:

1. Count its set bits using `Integer.bitCount(i)`.
2. Use that count as an index in the `prime` array.
3. If `prime[setBits]` is `true`, increment the answer.

---

## Algorithm

```text
1. Create a boolean array of size 21.
2. Mark all possible prime set-bit counts as true:
   2, 3, 5, 7, 11, 13, 17, 19.
3. Initialize answer = 0.
4. Traverse every number from left to right.
5. Calculate the number of set bits using Integer.bitCount().
6. Check whether that set-bit count is prime.
7. If prime, increment answer.
8. Return answer.
```

---

## Step-by-Step Traversal

For:

```text
left = 6
right = 10
```

### Step 1: Number 6

```text
6 → 110
```

Set bits:

```text
2
```

Since `2` is prime:

```text
answer = 1
```

### Step 2: Number 7

```text
7 → 111
```

Set bits:

```text
3
```

Since `3` is prime:

```text
answer = 2
```

### Step 3: Number 8

```text
8 → 1000
```

Set bits:

```text
1
```

`1` is not prime.

```text
answer = 2
```

### Step 4: Number 9

```text
9 → 1001
```

Set bits:

```text
2
```

`2` is prime.

```text
answer = 3
```

### Step 5: Number 10

```text
10 → 1010
```

Set bits:

```text
2
```

`2` is prime.

```text
answer = 4
```

Final result:

```text
4
```

---

## Java Solution

```java
class Solution {

    public int countPrimeSetBits(int left, int right) {

        // Prime numbers possible for set-bit count
        // because numbers <= 10^6 have at most 20 bits.
        boolean[] prime = new boolean[21];

        prime[2] = true;
        prime[3] = true;
        prime[5] = true;
        prime[7] = true;
        prime[11] = true;
        prime[13] = true;
        prime[17] = true;
        prime[19] = true;

        int ans = 0;

        // Traverse all numbers in the given range
        for (int i = left; i <= right; i++) {

            // Count number of set bits
            int setBits = Integer.bitCount(i);

            // Check whether set-bit count is prime
            if (prime[setBits]) {
                ans++;
            }
        }

        return ans;
    }
}
```

---

## Pattern Used

### Pattern: Lookup Table / Precomputation

This solution primarily uses the **Lookup Table (Precomputation) pattern**.

We precompute the possible prime values:

```text
2, 3, 5, 7, 11, 13, 17, 19
```

inside a boolean array.

Then checking whether a set-bit count is prime becomes:

```java
prime[setBits]
```

instead of running a separate prime-checking algorithm for every number.

### Why Use This Pattern?

The constraints tell us:

```text
right <= 10^6
```

Therefore, every number has at most 20 set bits.

So only 20 possible set-bit counts exist.

Instead of repeatedly checking:

```text
Is 2 prime?
Is 3 prime?
Is 4 prime?
...
```

we store the answers beforehand.

This makes the prime check effectively:

```text
O(1)
```

---

## Why `Integer.bitCount()`?

Java provides:

```java
Integer.bitCount(i)
```

which directly returns the number of `1` bits in an integer.

For example:

```java
Integer.bitCount(21)
```

returns:

```text
3
```

because:

```text
21 = 10101
```

Using the built-in method keeps the implementation short and efficient.

---

## Complexity Analysis

Let:

```text
N = right - left + 1
```

### Time Complexity

We traverse every number in the range:

```text
N
```

For every number, we calculate its set bits.

Since an integer contains at most 20 relevant bits for this problem, the bit-count operation is effectively bounded by the integer's bit width.

Therefore:

```text
Time Complexity: O(N × B)
```

where `B` is the number of bits.

For this constraint, `B <= 20`, so it is effectively:

```text
O(N)
```

Since:

```text
right - left <= 10^4
```

the maximum number of traversed values is:

```text
10,001
```

So the solution is easily efficient enough.

### Space Complexity

We create:

```java
boolean[] prime = new boolean[21];
```

The size is fixed and independent of the input range.

Therefore:

```text
Space Complexity: O(1)
```

---

## Complexity Summary

| Complexity     | Result                                |
| -------------- | ------------------------------------- |
| Time           | `O(N × B)`                            |
| Effective Time | `O(N)`                                |
| Space          | `O(1)`                                |
| `N`            | `right - left + 1`                    |
| `B`            | Maximum bit count / integer bit width |

---

## Key Takeaways

* A **set bit** is a binary digit equal to `1`.
* `Integer.bitCount()` is used to count set bits efficiently.
* Because `right <= 10^6`, only up to 20 bits are required.
* Possible prime set-bit counts are:
  `2, 3, 5, 7, 11, 13, 17, 19`.
* A boolean lookup table allows constant-time prime checking.
* The main pattern used is **Lookup Table / Precomputation**.
* The solution uses `O(1)` extra space.
* The overall traversal is effectively `O(N)` for the given constraints.