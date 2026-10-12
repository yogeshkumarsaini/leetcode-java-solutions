# Hamming Weight - Number of 1 Bits

## Problem

Given a positive integer `n`, return the number of `1` bits in its binary representation.

This is also called the **Hamming Weight**.

### Example

```text
Input:  n = 11
Binary: 1011
Output: 3
```

There are three `1` bits in `1011`.

---

## Java Solution

```java
class Solution {
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }

            n = n >>> 1;
        }

        return count;
    }
}
```

---

## Approach

We process the binary representation of `n` one bit at a time.

The important operation is:

```java
n & 1
```

### Why `n & 1`?

The binary value of `1` is:

```text
0001
```

When we perform `n & 1`, only the **last/rightmost bit** of `n` is checked.

Examples:

```text
1011
0001
----
0001  -> 1
```

So the last bit is `1`.

Another example:

```text
1010
0001
----
0000  -> 0
```

So the last bit is `0`.

After checking the last bit, we move all bits one position to the right:

```java
n = n >>> 1;
```

`>>>` is Java's **unsigned right shift** operator.

Since the problem gives a positive integer, this safely moves us toward `0`.

---

## Algorithm

1. Create `count = 0`.
2. Repeat while `n != 0`.
3. Check the rightmost bit using:
   ```java
   (n & 1)
   ```
4. If the result is `1`, increment `count`.
5. Shift `n` right by one position using:
   ```java
   n >>> 1
   ```
6. Continue until `n` becomes `0`.
7. Return `count`.

---

## Step-by-Step Traversal

For:

```text
n = 11
```

Binary representation:

```text
1011
```

### Step 1

```text
n = 1011

1011 & 0001 = 0001
```

Last bit = `1`

```text
count = 1
```

Shift:

```text
1011 >>> 1 = 0101
```

### Step 2

```text
n = 0101

0101 & 0001 = 0001
```

Last bit = `1`

```text
count = 2
```

Shift:

```text
0101 >>> 1 = 0010
```

### Step 3

```text
n = 0010

0010 & 0001 = 0000
```

Last bit = `0`

```text
count = 2
```

Shift:

```text
0010 >>> 1 = 0001
```

### Step 4

```text
n = 0001

0001 & 0001 = 0001
```

Last bit = `1`

```text
count = 3
```

Shift:

```text
0001 >>> 1 = 0000
```

Now:

```text
n == 0
```

Loop ends.

### Final Answer

```text
count = 3
```

Therefore:

```text
Hamming Weight = 3
```

---

## Pattern Used

### Pattern: Bit Manipulation / Bitwise Operations

This problem uses the **Bit Manipulation** pattern.

The main operators are:

```java
n & 1
```

and

```java
n >>> 1
```

### Why use Bit Manipulation?

The number is stored internally in binary. Instead of converting the integer to a binary `String`, we can directly inspect its bits.

This avoids creating an additional binary string and makes the solution efficient.

---

## Complexity

Let `B` be the number of bits processed.

### Time Complexity

```text
O(B)
```

For a Java `int`, there are at most 32 bits, so practically this is:

```text
O(32) = O(1)
```

### Space Complexity

```text
O(1)
```

Only `count` and the integer variable are used. No extra data structure is required.

---

## Follow-up: If Function Is Called Many Times

Java provides a highly optimized built-in method:

```java
Integer.bitCount(n)
```

So we can write:

```java
class Solution {
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}
```

This is preferable when the function is called many times because Java's implementation is optimized for counting set bits.

---

## Alternative Efficient Bit Trick

Another classic bit-manipulation technique is:

```java
n = n & (n - 1);
```

This operation removes the **rightmost set bit (`1`)**.

Example:

```text
n     = 1011
n - 1 = 1010

1011
1010
----
1010
```

One `1` bit is removed.

Solution:

```java
class Solution {
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            n = n & (n - 1);
            count++;
        }

        return count;
    }
}
```

This version runs in:

```text
O(k)
```

where `k` is the number of set bits.

For sparse numbers, this can be faster than checking every bit.

---

## Key Takeaways

- `n & 1` checks the rightmost bit.
- `n >>> 1` moves to the next bit.
- `n & (n - 1)` removes the rightmost `1` bit.
- The main pattern is **Bit Manipulation**.
- Space complexity is `O(1)`.
- For repeated calls, `Integer.bitCount(n)` is a good optimized choice.

---

## LeetCode Pattern

```text
Bit Manipulation
    ↓
Check individual bits
    ↓
Count set bits
```
