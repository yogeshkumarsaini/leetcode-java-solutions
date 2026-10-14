# Reverse Bits

## Problem

Reverse the bits of a given **32-bit unsigned/signed integer**.

### Example 1

```text
Input:  43261596

Binary:
00000010100101000001111010011100

Output: 964176192

Binary:
00111001011110000010100101000000
```

### Example 2

```text
Input:  2147483644

Binary:
01111111111111111111111111111100

Output: 1073741822

Binary:
00111111111111111111111111111110
```

---

## Java Solution

```java
class Solution {
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {

            // result ko left shift karo
            result = result << 1;

            // n ka last bit nikalo
            int bit = n & 1;

            // bit ko result me add karo
            result = result | bit;

            // n ko right shift karo
            n = n >> 1;
        }

        return result;
    }
}
```

---

# Approach

Hum 32-bit number ke bits ko **right se left** read karenge aur unhe `result` me **left se right** build karenge.

Main idea:

```text
n ka last bit
      ↓
    bit = n & 1
      ↓
result ko left shift
      ↓
bit ko result me add
      ↓
n ko right shift
```

Hum exactly **32 times** loop chalayenge because integer ke total 32 bits hote hain.

---

# Why `n & 1`?

`n & 1` se hume `n` ka **last/rightmost bit** milta hai.

Example:

```text
n = 1011

    1011
AND 0001
---------
    0001
```

Result `1` hai, so last bit `1` hai.

Agar:

```text
n = 1010

    1010
AND 0001
---------
    0000
```

Last bit `0` hai.

So:

```java
int bit = n & 1;
```

last bit extract karta hai.

---

# Why `result << 1`?

Result me next bit ke liye space banani hoti hai.

Example:

```text
result = 101
```

Left shift:

```text
101 << 1
= 1010
```

Ab last position par new bit add kar sakte hain.

```java
result = result << 1;
```

---

# Why `result | bit`?

Extract ki hui bit ko result ke end me add karne ke liye OR use karte hain.

Example:

```text
result = 1010
bit    = 1

1010
0001
----
1011
```

```java
result = result | bit;
```

---

# Why `n >> 1`?

Har iteration me n ka last bit already use ho chuka hai.

Isliye n ko right shift karke next bit ko last position par laate hain.

Example:

```text
n = 1011

n >> 1

0101
```

Next iteration me last bit `1` milega.

---

# Step-by-Step Traversal

Example:

```text
n = 1011
```

Actual problem me 32 iterations hongi, but understanding ke liye 4 bits ka example dekhte hain.

Initially:

```text
result = 0000
n      = 1011
```

### Iteration 1

Last bit:

```text
1011 & 0001 = 1
```

Result:

```text
0000 << 1 = 0000
0000 | 0001 = 0001
```

Now:

```text
result = 0001
n = 0101
```

### Iteration 2

Last bit:

```text
0101 & 0001 = 1
```

Result:

```text
0001 << 1 = 0010
0010 | 0001 = 0011
```

Now:

```text
result = 0011
n = 0010
```

### Iteration 3

Last bit:

```text
0010 & 0001 = 0
```

Result:

```text
0011 << 1 = 0110
0110 | 0000 = 0110
```

Now:

```text
result = 0110
n = 0001
```

### Iteration 4

Last bit:

```text
0001 & 0001 = 1
```

Result:

```text
0110 << 1 = 1100
1100 | 0001 = 1101
```

Final:

```text
Original: 1011
Reverse:  1101
```

So bits are processed from right to left and placed into the result from left to right.

---

# Algorithm

1. Create `result = 0`.
2. Run a loop exactly 32 times.
3. Left shift `result` by 1.
4. Extract the last bit of `n` using `n & 1`.
5. Add that bit to `result` using OR.
6. Right shift `n` by 1.
7. After 32 iterations, return `result`.

---

# Pattern Used

## Bit Manipulation

This problem uses the **Bit Manipulation** pattern.

Important operations:

```java
n & 1
```

Extracts the last bit.

```java
result << 1
```

Moves result left and creates space for a new bit.

```java
result | bit
```

Adds the extracted bit.

```java
n >> 1
```

Moves to the next bit.

### Why Bit Manipulation?

The problem specifically asks us to reverse the binary representation. We don't need to convert the number into a String or store all 32 bits in an array.

We can directly process each bit using bitwise operators.

This makes the solution simple and memory efficient.

---

# Complexity

## Time Complexity

```text
O(32)
```

Since 32 is constant:

```text
O(1)
```

We always process exactly 32 bits.

## Space Complexity

```text
O(1)
```

Only a few integer variables are used:

```text
result
bit
i
```

No array, String, or extra data structure is required.

---

# Follow-up: Many Function Calls

If `reverseBits()` is called many times, processing all 32 bits every time can be optimized using a **lookup table**.

For example, precompute the reversed bits of every possible 8-bit value:

```text
00000000 -> 00000000
00000001 -> 10000000
00000010 -> 01000000
...
11111111 -> 11111111
```

Then split the 32-bit integer into four 8-bit chunks and reverse each chunk using the lookup table.

Conceptually:

```text
32-bit number
    ↓
8 bits | 8 bits | 8 bits | 8 bits
    ↓       ↓       ↓       ↓
 lookup  lookup   lookup   lookup
    ↓       ↓       ↓       ↓
reversed  reversed reversed reversed
    ↓
combine them
```

This reduces the number of bit operations per call and is useful when the function is called a very large number of times.

---

# Key Takeaways

- `n & 1` → get the last bit.
- `n >> 1` → move to the next bit.
- `result << 1` → create space for the next reversed bit.
- `result | bit` → add the bit.
- Process exactly **32 bits**.
- Pattern: **Bit Manipulation**.
- Time: **O(1)**.
- Space: **O(1)**.
