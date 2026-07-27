# 292. Nim Game

## Problem Statement

You are playing the **Nim Game** with your friend.

### Rules:
- There is a heap containing **n** stones.
- You always play **first**.
- On each turn, a player can remove **1, 2, or 3 stones**.
- Whoever removes the **last stone wins**.

Return **true** if you can win assuming both players play optimally; otherwise, return **false**.

**LeetCode:** Easy

---

# Solution

```java
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
```

---

# Approach

Instead of simulating every possible move, we can observe a mathematical pattern.

Let's analyze the first few values.

| Stones (n) | Can First Player Win? |
|------------|-----------------------|
| 1 | ✅ Yes |
| 2 | ✅ Yes |
| 3 | ✅ Yes |
| 4 | ❌ No |
| 5 | ✅ Yes |
| 6 | ✅ Yes |
| 7 | ✅ Yes |
| 8 | ❌ No |
| 9 | ✅ Yes |
| 10 | ✅ Yes |
| 11 | ✅ Yes |
| 12 | ❌ No |

A clear pattern appears:

- Every multiple of **4** is a losing position.
- Every non-multiple of **4** is a winning position.

Therefore,

- If `n % 4 == 0` → Lose
- Otherwise → Win

---

# Why Multiples of 4 are Losing?

Suppose there are **4 stones**.

Possible moves:

- Remove 1 → Leave 3
- Remove 2 → Leave 2
- Remove 3 → Leave 1

No matter what you do, your opponent takes the remaining stones and wins.

Now consider **8 stones**.

If you remove:

- 1 → Opponent gets 7
- 2 → Opponent gets 6
- 3 → Opponent gets 5

The opponent can always return the game back to **4 stones**, forcing you into the losing position.

The same happens for:

- 12
- 16
- 20
- ...

Hence every multiple of 4 is losing.

---

# Why Non-Multiples of 4 are Winning?

Suppose:

### n = 5

Remove 1 stone.

Remaining = 4

Now your opponent faces the losing position.

---

### n = 6

Remove 2 stones.

Remaining = 4

Opponent loses.

---

### n = 7

Remove 3 stones.

Remaining = 4

Opponent loses.

---

In general,

If `n % 4 != 0`, you can always remove:

```
n % 4 stones
```

to leave exactly a multiple of 4.

After that, whatever your opponent removes (1, 2, or 3), you remove the remaining stones to complete 4.

Example:

```
12 stones

Opponent removes 1
You remove 3

Opponent removes 2
You remove 2

Opponent removes 3
You remove 1
```

Each round removes exactly **4 stones**, so eventually your opponent is forced to face **4**, then **0**, and you take the last stone.

---

# Algorithm

1. Check whether `n` is divisible by 4.
2. If divisible, return `false`.
3. Otherwise, return `true`.

---

# Step-by-Step Traversal

### Example 1

Input

```
n = 4
```

Calculation

```
4 % 4 = 0
```

Return

```
false
```

---

### Example 2

Input

```
n = 5
```

Calculation

```
5 % 4 = 1
```

Remove one stone.

Opponent gets

```
4
```

Return

```
true
```

---

### Example 3

Input

```
n = 10
```

Calculation

```
10 % 4 = 2
```

Remove two stones.

Remaining

```
8
```

Opponent gets a losing position.

Return

```
true
```

---

# Dry Run

### Input

```
n = 8
```

```
8 % 4 = 0
```

Return

```
false
```

---

### Input

```
n = 15
```

```
15 % 4 = 3
```

Remove 3 stones.

Remaining

```
12
```

Opponent is forced into a losing position.

Return

```
true
```

---

# Correctness Proof

If `n` is a multiple of 4:

- Every possible move leaves your opponent with a non-multiple of 4.
- The opponent can always return another multiple of 4.
- Eventually you receive 4 stones and lose.

If `n` is not a multiple of 4:

- Remove `n % 4` stones.
- Leave a multiple of 4.
- Continue pairing your moves with your opponent's moves so that each round removes exactly 4 stones.
- Eventually your opponent receives 4 stones and loses.

Therefore, the algorithm is always correct.

---

# Complexity Analysis

### Time Complexity

```
O(1)
```

Only one modulo operation is performed.

---

### Space Complexity

```
O(1)
```

No extra memory is used.

---

# Pattern Used

## Mathematical Pattern (Game Theory)

This problem belongs to the **Game Theory** category.

Instead of exploring all possible moves using recursion or dynamic programming, we observe a repeating mathematical pattern.

The losing positions repeat every **4** numbers.

```
1  ✅
2  ✅
3  ✅
4  ❌
5  ✅
6  ✅
7  ✅
8  ❌
9  ✅
10 ✅
11 ✅
12 ❌
```

Thus,

```
Winning Position = n % 4 != 0
```

---

# Why This Pattern?

Because each player can remove **1, 2, or 3** stones.

A player who starts on a multiple of 4 can never avoid giving the opponent a winning move.

The winning strategy is to always leave a multiple of 4 after your turn.

---

# Key Takeaways

- No loops are needed.
- No recursion is needed.
- No dynamic programming is needed.
- No simulation is required.
- Only a mathematical observation is enough.

---

# Java Solution

```java
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
```

---

# Interview Follow-up

**Q1. Why does this work?**

Because every multiple of 4 is a losing position under optimal play.

**Q2. Can this be solved using DP?**

Yes, but it would take **O(n)** time and **O(n)** space, which is unnecessary once the mathematical pattern is recognized.

**Q3. What category does this problem belong to?**

- Game Theory
- Mathematical Pattern
- Observation
- Modulo Arithmetic

---